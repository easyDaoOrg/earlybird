
<style lang='scss' scoped>
@import "./sign-in.scss";
</style>
<style>
.sign-up-password .ivu-btn[disabled]{
  border-radius: 50px;
  background: #0096ff;
  border: 0 none;
  color: #fff;
}
</style>
<template>
  <div class="member">
  <div class="member-picture"></div>
  <div class="sign-up">
    <div class="sign-up-title">更换手机号码</div>
    <div class="sign-up-dont">&nbsp;</div>
    <div class="sign-up-content">
      <div class="sign-up-content-top">请输入您的新手机号码</div>
      <div class="sign-up-content-tab">
        <Form ref="formInline" :model="formInline" :rules="ruleInline" inline>
          <div v-if="passwordType == 0">
            <FormItem>
              <Select size="large" v-model="formInline.select">
                  <Option value="+86">中国大陆 (+86)</Option>
                  <!-- <Option value="+852">中国香港 (+852)</Option>
                  <Option value="+853">中国澳门 (+853)</Option>
                  <Option value="+886">中国台湾 (+886)</Option> -->
              </Select>
            </FormItem>
            <FormItem prop="user" class="sign-up-content-tab-phone">
                <Input size="large" type="text" v-model="formInline.user" placeholder="手机">
                </Input>
            </FormItem>
          </div>

          <FormItem prop="dtpassword" class="sign-up-password"  v-if="passwordType == 1">
              <Input size="large" type="text" v-model="formInline.dtpassword" placeholder="动态码">
                  <Icon type="ios-lock-outline" slot="prepend"></Icon>
              </Input>
              <div class="sign-up-getbtn">
                <Button type="primary" shape="circle" @click="getPassword('formInline')" v-if="dateTime==60">获取动态码</Button>
                <Button disabled v-if="dateTime<60">{{dateTime}}s</Button>
              </div>
          </FormItem>
        </Form>
      </div>
    </div>

    <div class="sign-up-public sign-up-bot">
      <div class="sign-up-public-fovpassword">
        <Button type="primary" shape="circle" @click="getPassword(tabName)"  v-if="passwordType == 0">提交</Button>
        <Button type="primary" shape="circle" @click="handleSubmit(tabName)"  v-if="passwordType == 1">完成</Button>
      </div>
    </div>
  </div>
    <div class="member-footer">
      <p>广东省广州市海珠区新港东路1022号 电话: 400-900-7280</p>
      <p>©2014 – 2017 广州市易道信息技术咨询服务有限公司版权所有。ICP:  粤ICP备14048872号-4</p>
    </div>
  </div>
</template>

<script>
import Config from './../config.js'

export default {
  mixins: [Config],
  data () {
    return {
      formInline: {
        user: '',
        dtpassword: '',
        select: '+86'
      },
      ruleInline: {
        user: [
          { required: true, message: '手机号不能为空', trigger: 'blur' },
          { type: 'string', min: 11, max: 11, message: '手机的长度必须为11位', trigger: 'blur' }
        ],
        dtpassword: [
          { required: true, message: '动态码不能为空', trigger: 'blur' }
        ]
      },
    }
  },
  components: {

  },
  methods: {
    //获取动态码
    getPassword(name){
      this.formInline.dtpassword = " ";
      this.$refs[name].validate((valid) => {
        if (valid) {
          this.formInline.dtpassword = "";
          this.getPasswordSign(this.formInline, 1)
        }
      })
    },
    handleSubmit (name) {
      let self = this;
      this.$refs[name].validate((valid) => {
        if (valid) {
          let obj = self.formInline,
              phoneObj = {};
          obj['password'] = self.formInline.dtpassword;
          phoneObj.data = self.formInline.user;
          phoneObj.url = '/user/editUserPhone?user_phone=';
          self.checkPasswordSign(obj,1,1,phoneObj);
        }
      })
    }
  },
  mounted () {}
}
</script>

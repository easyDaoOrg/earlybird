
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
.sign-change .ivu-form-item{
  margin-bottom: 10px;
}
</style>
<template>
  <div class="member">
  <div class="member-picture"></div>
  <div class="sign-up">
    <div class="sign-up-title">
      <p v-if="passwordType == 0">忘记密码</p>
      <p v-if="passwordType == 1">手机号码验证</p>
      <p v-if="passwordType == 2">重置密码</p>
    </div>

    <div class="sign-up-dont">
      <p v-if="passwordType == 0">别担心，输入您注册的<br>手机号码来重置密码</p>
      <p v-if="passwordType == 1">为了您的个人信息安全，我们已将验证码发送至<br><b>{{formInline.user}}</b>,请在5分钟内完成账户验证</p>
      <p v-if="passwordType == 2">请输入新的密码并确认</p>
      <!-- 邮箱或 -->
    </div>
    <div class="sign-up-content">
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

        <Form ref="formPwd" :model="formPwd" :rules="ruleInline" inline  v-if="passwordType == 2">
          <FormItem prop="password" class="sign-up-password sign-change">
              <Input size="large" type="password" v-model="formPwd.password" placeholder="密码">
                  <Icon type="ios-lock-outline" slot="prepend"></Icon>
              </Input>
          </FormItem>
          <FormItem prop="aglinpassword" class="sign-up-password sign-change">
              <Input size="large" type="password" v-model="formPwd.aglinpassword" placeholder="确认密码">
                  <Icon type="ios-lock-outline" slot="prepend"></Icon>
              </Input>
          </FormItem>
        </Form>
        <!-- <Tabs :animated="false" @on-click="handleTabIndex">
          <TabPane label="手机">

          </TabPane>
          <TabPane label="邮箱">
            <Form ref="formItem" :model="formInline" :rules="ruleInline" inline>
                <FormItem prop="email" class="sign-up-password">
                    <Input size="large" type="text" v-model="formItem.email" placeholder="邮箱">
                        <Icon type="ios-mail-outline" slot="prepend"></Icon>
                    </Input>
                </FormItem>
            </Form>
          </TabPane>
      </Tabs> -->
      </div>
    </div>
    <div class="sign-up-public sign-up-bot">
      <div class="sign-up-public-fovpassword">
        <Button type="primary" shape="circle" @click="getPassword(tabName)"  v-if="passwordType == 0">提交</Button>
        <Button type="primary" shape="circle" @click="handleSubmit(tabName)"  v-if="passwordType == 1">完成</Button>
        <Button type="primary" shape="circle" @click="handleCheck('formPwd')"  v-if="passwordType == 2">提交</Button>
      </div>
    </div>


  </div>
    <div class="member-footer">
      <p>广东省广州市海珠区新港东路1022号 电话: 400-900-7280</p>
      <p>©2014 – 2017 广州市易道游信息技术咨询服务有限公司版权所有。ICP:  粤ICP备14048872号-4</p>
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
      formPwd: {
        password: '',
        aglinpassword: '',
      },
      formItem: {
        email: ''
      },
      ruleInline: {
        user: [
          { required: true, message: '手机号不能为空', trigger: 'blur' },
          { type: 'string', min: 11, max: 11, message: '手机的长度必须为11位', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { type: 'string', min: 6, message: '密码的长度不能小于6位', trigger: 'blur' }
        ],
        aglinpassword: [
          { required: true, message: '确认密码不能为空', trigger: 'blur' },
          { type: 'string', min: 6, message: '确认密码的长度不能小于6位', trigger: 'blur' }
        ],
        dtpassword: [
          { required: true, message: '动态码不能为空', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '邮箱不能为空', trigger: 'blur' }
        ]
      }
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
      console.log(this.formInline)
      this.$refs[name].validate((valid) => {
        if (valid) {
          let obj = this.formInline;
          obj['password'] = this.formInline.dtpassword;
          this.checkPasswordSign(obj,1,2);
        }
      })
    },
    handleCheck (name) {
      if(this.formPwd.password == this.formPwd.aglinpassword){
        let self = this;
        this.$refs[name].validate((valid) => {
          if (valid) {
            let obj={};
            switch (self.tabIndex){
              case 0:
                obj.data= self.formPwd.password;
                self.handleCheckChange(obj,'/user/editPasswd?user_passwd=')
                break;
              case 1:
                obj.data= self.formItem.email;
                self.handleCheckChange(obj,'/user/editUserEmail?user_email=')
                break;
            }
          }
        })
      }else{
        this.$Message.error('两次密码必须相同')
      }
    }
  },
  mounted () {}
}
</script>

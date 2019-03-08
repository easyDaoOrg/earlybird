
<style lang='scss' scoped>
@import "./sign-in.scss";
</style>
<template>
  <div class="member">
  <div class="member-picture"></div>
  <div class="sign-up">
    <div class="sign-up-title">忘记密码</div>
    <div class="sign-up-dont">
      别担心，输入您注册的<br>邮箱或手机号码来重置密码
    </div>
    <div class="sign-up-content">
      <div class="sign-up-content-tab">
        <Tabs :animated="false" @on-click="handleTabIndex">
          <TabPane label="手机">
            <Form ref="formInline" :model="formInline" :rules="ruleInline" inline>
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
            </Form>
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
      </Tabs>
      </div>
    </div>
    <div class="sign-up-public sign-up-bot">
      <div class="sign-up-public-fovpassword">
        <Button type="primary" shape="circle" @click="handleCheck(tabName)">提交</Button>
      </div>
    </div>
  </div>
    <div class="member-footer">
      <p>广东省广州市海珠区新港东路1022号 电话: 400-900-7280</p>
      <p>©2014 – 2017 广州市骑鹅游信息技术咨询服务有限公司版权所有。ICP:  粤ICP备14048872号-4</p>
    </div>
  </div>
</template>

<script>
// import { wsGetProjectOview } from '@/models/project';

export default {
  data () {
    return {
      tabIndex: 0,
      tabName: 'formInline',
      formInline: {
        user: '',
        select: '+86'
      },
      formItem: {
        email: ''
      },
      ruleInline: {
        user: [
          { required: true, message: '手机号不能为空', trigger: 'blur' }
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
    handleTabIndex(index){
      this.tabIndex = index;
      this.tabName = this.tabIndex==0 ? 'formInline' : 'formItem';
    },
    handleCheck (name) {
      let userData = '';
      this.$refs[name].validate((valid) => {
        if (valid) {
          switch (this.tabIndex){
            case 0:
              userData = '/user/editUserPhone?user_phone=' + this.formInline.user;
              this.handleSubmit(userData);
              break;
            case 1:
              userData = '/user/editUserEmail?user_email=' + this.formItem.email;
              this.handleSubmit(userData);
              break;
          }
        }
      })
    },
    handleSubmit(userData){
      let url = this.loginUrl + userData
      let self = this
      this.axios
        .get(url)
        .then(data => {
          if(data.data.flag){
            this.$Message.success(data.data.msg)
          }else{
            this.$Message.error(data.data.msg)
          }
        })
        .catch(error => {
          console.log(error)
        })
    }
  },
  mounted () {}
}
</script>

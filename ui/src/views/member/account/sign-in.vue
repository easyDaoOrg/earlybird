
<style lang='scss' scoped>
@import "./sign-in.scss";
</style>
<template>
  <div class="member">
    <div class="member-picture"></div>
    <div class="sign-up">
    <Tabs type="card" :animated="false" @on-click="handleTabIndex">
        <TabPane label="密码登录">
          <div class="sign-up-content clearfix">
            <div class="sign-up-content-tab">
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
                  <FormItem prop="password" class="sign-up-password">
                      <Input size="large" type="password" v-model="formInline.password" placeholder="密码">
                          <Icon type="ios-lock-outline" slot="prepend"></Icon>
                      </Input>
                  </FormItem>
              </Form>

              <!-- <Tabs :animated="false">
                <TabPane label="手机">

                </TabPane>
                <TabPane label="邮箱">
                  <Form ref="formItem" :model="formInline" :rules="ruleInline" inline>
                      <FormItem prop="email" class="sign-up-password">
                          <Input size="large" type="text" v-model="formItem.email" placeholder="邮箱">
                              <Icon type="ios-mail-outline" slot="prepend"></Icon>
                          </Input>
                      </FormItem>
                      <FormItem prop="password" class="sign-up-password">
                          <Input size="large" type="password" v-model="formItem.emailpassword" placeholder="密码">
                              <Icon type="ios-lock-outline" slot="prepend"></Icon>
                          </Input>
                      </FormItem>
                  </Form>
                </TabPane>
            </Tabs> -->
            </div>
          </div>
        </TabPane>
        <TabPane label="动态码登录">
          <div class="sign-up-content clearfix">
            <div class="sign-up-content-tab sign-up-content-dt">
              <Form ref="formSola" :model="formSola" :rules="ruleInline" inline>
                  <FormItem>
                      <Select size="large" v-model="formSola.select">
                          <Option value="+86">中国大陆 (+86)</Option>
                          <!-- <Option value="+852">中国香港 (+852)</Option>
                          <Option value="+853">中国澳门 (+853)</Option>
                          <Option value="+886">中国台湾 (+886)</Option> -->
                      </Select>
                  </FormItem>
                  <FormItem prop="user" class="sign-up-content-tab-phone">
                      <Input size="large" type="text" v-model="formSola.user" placeholder="手机">
                      </Input>
                  </FormItem>
                  <FormItem prop="dtpassword" class="sign-up-password">
                      <Input size="large" type="text" v-model="formSola.dtpassword" placeholder="动态码">
                          <Icon type="ios-lock-outline" slot="prepend"></Icon>
                      </Input>
                      <div class="sign-up-getbtn">
                        <Button type="primary" shape="circle" @click="getPassword()">获取动态码</Button>
                      </div>
                  </FormItem>
              </Form>
            </div>
          </div>
        </TabPane>
    </Tabs>
    <div class="sign-up-public">
      <router-link :to="{path:'/member/account/forgot-password'}">
        <div class="sign-up-public-fover fr">忘记密码？</div>
      </router-link>
      <div class="sign-up-public-fovpassword">
        <Button type="primary" shape="circle" @click="checkPassword(tabName)">登录</Button>
      </div>
      <router-link :to="{path:'/member/account/sign-up'}">
      <div class="sign-up-public-tips sign-up-zc">
        新来的？去注册
      </div>
      </router-link>
      <!-- <div class="sign-up-public-also">
        您也可通过以下方式登录
      </div>
      <div class="sign-up-public-party">
        <a href="#">
          <i><img src="@/assets/images/weixin.svg"></i>
          <b><i class="iconfont icon-travel-wechat" /></b>
          <br>微信
        </a>
        <a href="#">
          <b class="sign-up-public-qq"><img src="../../../assets/images/qq.svg"></b><br>QQ
        </a>
      </div> -->
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
        password: '',
        select: '+86'
      },
      formItem: {
        email: '',
        emailpassword: ''
      },
      formSola: {
        user: '',
        dtpassword: '',
        select: '+86'
      },
      ruleInline: {
        user: [
          { required: true, message: '手机号不能为空', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { type: 'string', min: 6, message: '密码的长度不能小于6位', trigger: 'blur' }
        ],
        dtpassword: [
          { required: true, message: '动态码不能为空', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '邮箱不能为空', trigger: 'blur' }
        ]
      },
      isFlag: false
    }
  },
  components: {

  },
  methods: {
    //获取动态码
    getPassword(){
      let url = this.loginUrl + `/dynamic/addDynamic?user_account=`+this.formSola.user+`&flag=phone`
      let self = this
      this.axios
        .get(url)
        .then(data => {

        })
        .catch(error => {
          console.log(error)
        })
    },
    //验证动态码
    checkPassword(name){
      this.$refs[name].validate((valid) => {
        if (valid) {
          let url = this.loginUrl + `/dynamic/checkDynamic?user_account=`+this.formSola.user+`&dynamic_code=`+this.formSola.dtpassword
          let self = this
          this.axios
            .get(url)
            .then(data => {
              if(data.data.flag){
                self.handleCheck()
              }
            })
            .catch(error => {
              console.log(error)
            })
        }
      })

    },
    handleTabIndex(index){
      this.tabIndex = index;
      this.tabName = this.tabIndex==0 ? 'formInline' : 'formSola';
    },
    //登录
    handleCheck () {
      let userObj = {};
      switch (this.tabIndex){
        case 0:
          userObj['user_account'] = this.formInline.user;
          userObj['user_passwd'] = this.formInline.password;
          userObj['login_flag'] = 'passwd';
          this.handleSubmit(userObj);
          break;
        case 1:
          userObj['user_account'] = this.formSola.user;
          userObj['user_passwd'] = '';
          userObj['login_flag'] = 'dynamic';
          this.handleSubmit(userObj);
          break;
      }
    },
    //调用登录接口
    handleSubmit(userobj){
      let url = this.loginUrl + `/user/doLogin`
      let self = this
      this.axios
        .post(url, userobj)
        .then(data => {
          if(data.data.code == '200'){
            this.$router.push(`/flights/index`);
          }
        })
        .catch(error => {
          console.log(error)
        })
    },
  },
  mounted () {}
}
</script>

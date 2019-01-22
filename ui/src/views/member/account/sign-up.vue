
<style lang='scss' scoped>
@import "./sign-in.scss";
</style>
<template>
  <div class="member">
  <div class="member-picture"></div>
  <div class="sign-up">
    <div class="sign-up-title">注册</div>
    <div class="sign-up-content">
      <div class="sign-up-content-tab">
        <Tabs :animated="false">
          <TabPane label="手机">
            <Form ref="formInline" :model="formInline" :rules="ruleInline" inline>
                <FormItem>
                    <Select size="large" v-model="formInline.select">
                        <Option value="+86">中国大陆 (+86)</Option>
                        <Option value="+852">中国香港 (+852)</Option>
                        <Option value="+853">中国澳门 (+853)</Option>
                        <Option value="+886">中国台湾 (+886)</Option>
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
      </Tabs>
      </div>
    </div>
    <div class="sign-up-public">
      <div class="sign-up-public-fovpassword">
        <Button type="primary" shape="circle" @click="handleSubmit('formInline')">注册</Button>
      </div>
      <router-link :to="{path:'/member/account/sign-in'}">
        <div class="sign-up-public-tips">
          已有账号？
        </div>
      </router-link>
      <div class="sign-up-public-foot">
        点击“注册”即代表您接受 易到旅行的 <a @click="clauseModel = true">服务条款</a>
      </div>
      <Modal v-model="clauseModel" footer-hide>
        <div class="sign-up-public-model">
          <div class="sign-up-public-model-title">服务条款</div>
          <div class="sign-up-public-model-content">
            <h4>易到旅行会员服务条款的确认</h4>
            <p>易到旅行各项服务的所有权与运作权归广州市骑鹅游信息技术咨询服务有限公司所有（以下简称“易到旅行”）。本会员服务条款具有法律约束力。一旦您点选"注册"并通过注册程序，即表示您自愿接受本协议之所有条款，并已成为易到旅行的注册会员。 </p>
            <p>本协议内容包括协议正文、各单项服务协议及其他易到旅行已经发布的或将来可能发布的各类规则，包括但不限于免责声明、隐私政策、产品预订须知、旅游合同、账户协议等其他协议（“其他条款”），本协议正文与其他条款构成不可分割的整体。如果本协议正文与其他条款相冲突的，则以其他条款为准。除另行明确声明外，任何易到旅行提供的服务均受本协议约束。本协议正文与其他条款的中文版本与其他语言的版本不一致的，以中文版为准。</p>
            <h4>服务内容</h4>
            <p>易到旅行服务的具体内容由广州市骑鹅游信息技术咨询服务有限公司在法律允许的范围内拥有解释权。</p>
          </div>
        </div>
      </Modal>
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
      formInline: {
        user: '',
        password: '',
        select: '+86'
      },
      formItem: {
        email: '',
        emailpassword: ''
      },
      ruleInline: {
        user: [
          { required: true, message: '用户名不能为空', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { type: 'string', min: 6, message: '密码的长度不能小于6位', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '邮箱不能为空', trigger: 'blur' }
        ]
      },
      clauseModel: false
    }
  },
  components: {

  },
  methods: {
    handleSubmit (name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          this.$Message.success('Success!')
        } else {
          this.$Message.error('Fail!')
        }
      })
    }
  },
  mounted () {}
}
</script>

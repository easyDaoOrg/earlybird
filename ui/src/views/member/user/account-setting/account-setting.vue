
<style lang='scss' scoped>
@import "./account-setting.scss";
</style>
<style>
  .account-setting .ivu-form .ivu-form-item-label{
    font-size: 14px;
  }
</style>

<template>
  <div class="account-setting">
    <div class="account-setting-title">
      <b>个人信息</b>
    </div>
    <div class="account-setting-info">
      <p>
        <span>邮箱</span>
        <font>
          <router-link :to="{path:'/member/account/add-email'}">
          <b class="fr">[ 添加 ]</b>
          </router-link>
        </font>
      </p>
      <p>
        <span>手机号码</span>
        <font>
          <router-link :to="{path:'/member/account/change-phone'}">
          <b class="fr">[ 更改 ]</b>
          </router-link>
          <b>185****1204</b>
        </font>
      </p>
    </div>

    <div class="account-setting-title">
      <b>安全设置</b>
    </div>
    <div class="account-setting-safe">
      <p>
        <span class="fr">
          <Button type="primary" @click="changePassword()">更改</Button>
        </span>
        <i><Icon type="md-checkmark" /></i>
        密码
      </p>
      <div class="account-setting-safe-con" v-if="changePasswordBoolean">
        <Form ref="formValidate" :model="formValidate" :rules="ruleValidate">
          <FormItem label="当前密码"  prop="password1">
              <Input type="password" v-model="formValidate.password1"></Input>
          </FormItem>
          <FormItem label="新密码"  prop="password2">
              <Input type="password" v-model="formValidate.password2"></Input>
          </FormItem>
          <FormItem label="确认密码"  prop="password3">
              <Input type="password" v-model="formValidate.password3"></Input>
          </FormItem>
          <FormItem>
            <Button type="primary" @click="handleSubmit('formValidate')">确认</Button>
            <Button @click="handleReset('formValidate')" style="margin-left: 8px">重置</Button>
         </FormItem>
      </Form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      modelQuestion: false,
      changePasswordBoolean: false,
      formValidate: {
        password1: '',
        password2: '',
        password3: ''
      },
      ruleValidate: {
        password1: [
            { required: true, message: '密码不能为空', trigger: 'blur' }
        ],
        password2: [
            { required: true, message: '密码不能为空', trigger: 'blur' }
        ],
        password3: [
            { required: true, message: '请确认密码', trigger: 'blur' }
        ],
      }
    }
  },
  components: {

},
  methods: {
    handleSubmit (name) {
        this.$refs[name].validate((valid) => {
            if (valid) {
                this.$Message.success('Success!');
            } else {
                this.$Message.error('Fail!');
            }
            this.changePasswordBoolean = false;
        })
    },
    handleReset (name) {
        this.$refs[name].resetFields();
    },
    changePassword (){
      this.changePasswordBoolean = true;
    }
  },
  mounted () {}
}
</script>

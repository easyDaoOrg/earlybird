
<style lang='scss' scoped>
@import "./sign-in.scss";
</style>
<template>
  <div class="member">
  <div class="member-picture"></div>
  <div class="sign-up">
    <div class="sign-up-title">添加邮箱</div>
    <div class="sign-up-dont">&nbsp;</div>
    <div class="sign-up-content">
      <div class="sign-up-content-top">请输入您的邮箱</div>
      <div class="sign-up-content-tab">
        <Form ref="formItem" :model="formItem" :rules="ruleInline" inline>
            <FormItem prop="email" class="sign-up-password">
                <Input size="large" type="text" v-model="formItem.email" placeholder="邮箱">
                    <Icon type="ios-mail-outline" slot="prepend"></Icon>
                </Input>
            </FormItem>
        </Form>
      </div>
    </div>
    <div class="sign-up-public sign-up-bot">
      <div class="sign-up-public-fovpassword">
        <Button type="primary" shape="circle" @click="handleSubmit('formItem')">提交</Button>
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
      formItem: {
          email: '',
      },
      ruleInline: {
          email: [
              { required: true, message: '邮箱不能为空', trigger: 'blur' }
          ],
      },
    }
  },
  components: {

  },
  methods: {
    handleSubmit(name) {
        this.$refs[name].validate((valid) => {
            if (valid) {
                this.$Message.success('Success!');
            } else {
                this.$Message.error('Fail!');
            }
        })
    }
  },
  mounted () {}
}
</script>

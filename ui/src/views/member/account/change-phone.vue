
<style lang='scss' scoped>
@import "./sign-in.scss";
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
        <Form ref="formItem" :model="formItem" :rules="ruleInline" inline>
            <FormItem>
                  <Select size="large" v-model="formItem.select">
                      <Option value="+86">中国大陆 (+86)</Option>
                      <Option value="+852">中国香港 (+852)</Option>
                      <Option value="+853">中国澳门 (+853)</Option>
                      <Option value="+886">中国台湾 (+886)</Option>
                  </Select>
              </FormItem>
              <FormItem prop="user" class="sign-up-content-tab-phone">
                  <Input size="large" type="text" v-model="formItem.user" placeholder="手机">
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
          user: '',
          select: '+86',
      },
      ruleInline: {
          user: [
              { required: true, message: '内容不能为空', trigger: 'blur' }
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

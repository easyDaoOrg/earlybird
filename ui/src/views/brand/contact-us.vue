
<style lang='scss' scoped>
@import "./contact-us.scss";
</style>
<template>
  <div class="contact-us">
    <div class="blank01"></div>
    <div class="contact-us-title">联系我们</div>
    <div class="contact-us-content">
      <div class="contact-us-content-person">
        <span>
          人事部：<br>
          市场部：<br>
          商务拓展：<br>
          客服部：
        </span>
        <span>
          <a href="mailto:?subject='联系-'&amp;body='您好易到:'">
          hr@yidao.com<br>
          mkt@yidao.com<br>
          bdteam@yidao.com<br>
          support@yidao.com</a>
        </span>
      </div>
      <div class="contact-us-content-list">
        <dl>
          <dt>意见建议</dt>
          <dd>请告诉iGola我们如何更好地为您服务！iGola非常欢迎您提出意见建议。</dd>
          <button @click="handleQuestion('意见建议')">Go</button>
        </dl>
        <dl>
          <dt>价格准确性</dt>
          <dd>找到了一个错误的价格？请告诉我们及时纠正。</dd>
          <button @click="handleQuestion('价格准确性')">Go</button>
        </dl>
        <dl>
          <dt>招贤纳士</dt>
          <dd>觉得自己有才？发份简历给我们吧！iGola招贤纳士进行中。</dd>
          <button @click="handleQuestion('招贤纳士')">Go</button>
        </dl>

        <div class="contact-us-content-feedback" v-if="formFeedback"  style="background-image: url(&quot;https://content.igola.com/static/WEB/images/brand/aboutus/brand_feedback.jpg&quot;);">
          <div class="contact-us-content-feedback-form">
            <div class="contact-us-content-feedback-form-title">{{formTitle}}</div>
            <template>
              <Form :model="formTop" label-position="top">
                <FormItem label="">
                  <Input v-model="formTop.input1" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="Enter something..."></Input>
                </FormItem>
                <FormItem label="">
                 <Input v-model="formTop.input2" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="此处录入内容"></Input><br>
                </FormItem>
                <Button type="primary" @click="handleSubmit('formTop')">发送</Button>
            </Form>
          </template>
          </div>
        </div>

        <dl>
          <dt>合作伙伴</dt>
          <dd>无论是大公司还是小团队，无论是旅行、航空还是旅游局，iGola对合作伙伴随时敞开大门。</dd>
          <button @click="handleQuestion('合作伙伴')">Go</button>
        </dl>
        <dl>
          <dt>市场营销</dt>
          <dd>如果你有好创意或媒介资源，让iGola成功面向更多用户，欢迎发封邮件给我们聊聊看。</dd>
          <button @click="handleQuestion('市场营销')">Go</button>
        </dl>
        <dl>
          <dt>其它</dt>
          <dd>我们想知道你想说的一切！</dd>
          <button @click="handleQuestion('其它')">Go</button>
        </dl>
      </div>
    </div>
  </div>
</template>

<script>
// import { wsGetProjectOview } from '@/models/project';

export default {
  data () {
    return {
      formTop: {
          input1: '',
          input2: '',
      },
      formTitle: '',
      formFeedback: false,
    }
  },
  components: {

  },
  methods: {
    handleSubmit (name) {
        // this.$refs[name].validate((valid) => {
        //     if (valid) {
        //         this.$Message.success('Success!');
        //     } else {
        //         this.$Message.error('Fail!');
        //     }
        // })
    },
    handleQuestion(name){
      this.formTitle = name;
      this.formFeedback = true;
    }
  },
  mounted () {

  }
}
</script>

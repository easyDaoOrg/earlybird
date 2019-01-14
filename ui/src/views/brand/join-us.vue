
<style lang='scss' scoped>
@import "./join-us.scss";
</style>
<template>
  <div class="join-us">
    <div class="blank01"></div>
    <div class="join-us-title">为什么选择 易到 ？</div>
    <div class="join-us-content">
      <div class="join-us-content-choose">
        <dl>
          <dt>多元团队</dt>
          <dd>同事们有说英文的，德语的，荷兰语<br>的……</dd>
        </dl>
        <dl>
          <dt>诱人薪资</dt>
          <dd>极具竞争力的薪资和股权</dd>
        </dl>
        <dl>
          <dt>弹性工作</dt>
          <dd>自然醒，喝个早茶，再上班</dd>
        </dl>
        <dl>
          <dt>环境舒适</dt>
          <dd>累了就来杯咖啡，透过落地窗望望江景</dd>
        </dl>
        <dl>
          <dt>带薪年假</dt>
          <dd>还有可！报！销！的境外旅游，一共15<br>天！</dd>
        </dl>
        <dl>
          <dt>零食料理</dt>
          <dd>包餐，并提供来自世界各地健康的和不健<br>康的零食</dd>
        </dl>
        <dl>
          <dt>无限畅饮</dt>
          <dd>洋的？白的？啤的？来一杯放松下啦！</dd>
        </dl>
        <dl>
          <dt>无尽饮料</dt>
          <dd>汽水，果汁，现磨咖啡…… 老是喝不完！</dd>
        </dl>
      </div>
    </div>
    <div class="join-us-content-wall" style="background-image: url(&quot;https://content.igola.com/static/WEB/images/brand/joinus/joinus_wall.jpg&quot;);"></div>
    <div class="join-us-title">职位</div>
    <div class="join-us-notices">
      <Select v-model="model1" style="width:200px">
        <Option v-for="item in cityList" :value="item.value" :key="item.value" >{{ item.label }}</Option>
      </Select>
      <div class="fr"><span>简历发送至</span>&nbsp;:&nbsp;<a href="mailto:?subject='contact-'&amp;body='Hello iGola:'">hr@yidao.com</a></div>
    </div>
    <div class="join-us-part">
      <div class="join-us-part-list fl">
        <ul>
          <li v-for="(item,index) in partList" :key="index" v-bind:class="{joinactive: item.name == partName}" @click="joinPart(item.name)">{{item.name}}</li>
        </ul>
      </div>
      <div class="join-us-part-content fl">
        <div class="join-us-part-content-title">{{partName}}</div>
        <div class="join-us-part-content-intro">
          <b>岗位职责：</b>
          <p>
            1. 负责各产品的数据抓取需求；<br>
            2. 负责开发公司的爬虫系统；
          </p><br>
          <b>岗位要求：</b>
          <p>
            1. 熟悉http协议，xml相关技术（解析，dom数， xpath）；<br>
            2. 熟悉正则表达式；<br>
            3. 熟悉python/java 编程语言，数据结构和算法基础扎实。<br>
            4. 熟悉开源的爬虫系统，如scrapy等，爬虫相关工作经验者优先；
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import { wsGetProjectOview } from '@/models/project';

export default {
  data () {
    return {
      cityList: [
        {
          value: '不限',
          label: '不限'
        },
        {
          value: '成都',
          label: '成都'
        },
        {
          value: '广州',
          label: '广州'
        },
        {
          value: '北京',
          label: '北京'
        }
      ],
      model1: '',
      partList: [
        {
          name: '初级Java工程师'
        },
        {
          name: '中级Java工程师'
        },
        {
          name: '高级Java工程师'
        },
        {
          name: '资深Java工程师'
        },
        {
          name: 'Java爬虫工程师'
        },
        {
          name: '数据分析工程师'
        },
        {
          name: '前端开发工程师'
        },
        {
          name: '资深前端开发工程师'
        },
        {
          name: '初级测试工程师'
        },
        {
          name: '项目经理'
        }
      ],
      partName: '初级Java工程师'
    }
  },
  components: {},
  methods: {
    joinPart (name) {
      this.partName = name
    }
  },
  mounted () {}
}
</script>

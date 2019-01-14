
<style lang='scss' scoped>
@import "./our-team.scss";
</style>
<style>
.ivu-carousel-dots li button.radius{
    width: 12px;
    height: 12px;
}
.ivu-carousel-dots li.ivu-carousel-active>button.radius{
   width: 12px;
   background-color: #00b4ff;
}
.ivu-carousel-dots li{
  margin: 22px 12px 24px;
}
.ivu-carousel-item{
  height: 500px!important;
}
</style>

<template>
<div class='our-team'>
    <div class="container">

        <h2 class="brand-title">团队介绍</h2>

        <section class='container-carousel'>
            <Carousel v-model="value1" loop style='height:100%' dots='outside' arrow='never' :radius-dot="radiusDot" :easing='easing'>
                <CarouselItem v-for='(item,index) in seniorMember' :key='index'>
                  <div class='container-carousel-bg demo-carousel'>
                     <p>
                       <span class="team-item-info-name">{{item.name}}</span>
                       <span class="team-item-info-title">
                         <span>{{item.position}}</span>
                         <br>
                         <span>{{item.positionSystem}}</span>
                       </span>
                     </p>
                      <img :src="item.url" alt="">
                  </div>
                </CarouselItem>
            </Carousel>
        </section>

        <footer class='container-footer'>
          {{seniorMember[value1].data}}
        </footer>
   </div>
    <div class='mask'></div>
    <div class="employee-item-list-box">
      <img v-for='(src,index) in ordinaryMember' :key="index" :src="src" alt="" class="employee-item-list">
    </div>
    <div style='padding-top:40px'>
      <h2 class="brand-title">投资人</h2>
      <div class="team-bg-box center">
        <div class="inverstors-item-list-box">
          <img src="https://content.igola.com/igola-cms/prod/our-team/7544563cffc14bc1b3b63cb55db5d642.jpg" alt="" class="inverstors-item-list">
          <img src="https://content.igola.com/igola-cms/prod/our-team/03350ac5013840abaa2dea7b14961963.jpg" alt="" class="inverstors-item-list">
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
      value1: 0,
      radiusDot: true,
      easing: 'swing',
      ordinaryMember: [
        'https://content.igola.com/group1/M00/00/58/CgAAK1nCECCAexpZAABUAaoDrtQ802.jpg',
        'https://content.igola.com/group1/M00/00/42/CgAAKlnCECuAL-TbAABZgFraKFE888.jpg',
        'https://content.igola.com/group1/M00/00/58/CgAAK1nCEDiAAKRnAABabio5zmA008.jpg',
        'https://content.igola.com/group1/M00/00/42/CgAAKlnCEEmAMg2tAABU4SAFE8I592.jpg',
        'https://content.igola.com/group1/M00/00/42/CgAAKlnCEFiAWxDoAABYwnLMFjo455.jpg',
        'https://content.igola.com/group1/M00/00/58/CgAAK1nCEGiAUJ-SAABS7I0Wejw874.jpg',
        'https://content.igola.com/group1/M00/00/42/CgAAKlnCEIKAQqG4AABXO3xLDy8245.jpg',
        'https://content.igola.com/group1/M00/00/42/CgAAKlnCEJCAVBQWAABWQG_2qCk444.jpg',
        'https://content.igola.com/igola-cms/prod/our-team/76bb57f65c624d28996608e766f44e9d.jpg',
        'https://content.igola.com/igola-cms/prod/our-team/0ebc9443d2d64e8b870c8d88ae03daf3.jpg',
        'https://content.igola.com/igola-cms/prod/our-team/b5f3d619ac9248fbb3b55ee0e9eb062e.jpg',
        'https://content.igola.com/igola-cms/prod/our-team/77358070e5184e3ab031053d1afee29a.jpg',
        'https://content.igola.com/group1/M00/00/41/CgAAKlnCC9KAemAkAAAkF5Okbus471.jpg',
        'https://content.igola.com/group1/M00/00/42/CgAAKlnCEbqAS4m8AABz2Q-hdR8630.jpg'
      ],
      seniorMember: [
        {
          name: '张岩',
          position: '创始人',
          positionSystem: 'CEO',
          url: 'https://content.igola.com/igola-cms/prod/our-team/834401bbb2d34d54b3215e7a1d1553cd.png',
          data: '张岩，iGola创始人、CEO。张岩毕业于广州华南理工大学，获得计算机软件学士学位。他先后在Unisys、Cisco Systems、Google任职，带领中国区的团队创造每年过亿美金的销售佳绩。有感于自己在国际机票预订中遇到的各种不便，2014年创办iGola——一款引领更好旅行预订体验的产品，让用户少花时间搜索、多花时间探索。'
        },
        {
          name: '朱雷博士',
          position: 'CTO',
          positionSystem: '',
          url: 'https://content.igola.com/igola-cms/prod/our-team/8638643e28d548028890d6547947bef8.png',
          data: '朱雷博士先后以优异成绩毕业于中科大与港中大。本科期间，专业成绩在电子系百余人中名列前三；博士期间，以全A的成绩毕业，研究成果赢得了ACM的最佳论文奖。曾是香港应科院与联想实验室的科研精英。朱雷博士参与过香港政府多项的高科技项目，曾作为技术领队先后参与了惠普大数据平台与联想大数据一体机的研发。他凭突出的贡献获得了惠普香港颁发的“最高效员工”奖。'
        },
        {
          name: '李珊珊',
          position: '产品总监',
          positionSystem: '',
          url: 'https://content.igola.com/igola-cms/prod/our-team/2a6b617d2f044f57a98186a399cad2c9.png',
          data: '曾供职于宝洁公司信息决策与解决方案部，作为亚洲区产品负责人完成多个全球化项目在不同国家的上线和运营。也担任了三年的商业数据分析师，为宝洁公司最大的零售客户沃尔玛、华润集团创建了各自的商业分析与预测模型。毕业于中国科技大学，获得理学学士学位。'
        }

      ]
    }
  },
  components: {

  },
  methods: {},
  mounted () {}
}
</script>

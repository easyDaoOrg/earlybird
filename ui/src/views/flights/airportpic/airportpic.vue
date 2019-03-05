<style>
.airportpic b{
  padding: 0 5px;
  font-weight: normal
}
</style>

<template>
  <span class="airportpic">
    <span v-if="airportObj.carrier == '3U'" title="四川航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">四川航空</b></span>
    <span v-if="airportObj.carrier == '8L'" title="祥鹏航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">祥鹏航空</b></span>
    <span v-if="airportObj.carrier == '9C'" title="春秋航空"><img src="../../../assets/images/airportIcon/9c.png"><b v-if="airportTitle">春秋航空</b></span>
    <span v-if="airportObj.carrier == '9H'" title="长安航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">长安航空</b></span>
    <span v-if="airportObj.carrier == 'A6'" title="云南红土航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">云南红土航空</b></span>
    <span v-if="airportObj.carrier == 'AQ'" title="九元航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">九元航空</b></span>
    <span v-if="airportObj.carrier == 'BK'" title="奥凯"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">奥凯</b></span>
    <span v-if="airportObj.carrier == 'CA'" title="国航"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">国航</b></span>
    <span v-if="airportObj.carrier == 'CN'" title="大新华"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">大新华</b></span>

    <span v-if="airportObj.carrier == 'CZ'" title="南方航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">南方航空</b></span>
    <span v-if="airportObj.carrier == 'DR'" title="瑞丽航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">瑞丽航空</b></span>
    <span v-if="airportObj.carrier == 'DZ'" title="东海航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">东海航空</b></span>
    <span v-if="airportObj.carrier == 'EU'" title="成都航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">成都航空</b></span>
    <span v-if="airportObj.carrier == 'FM'" title="上海航空"><img src="../../../assets/images/airportIcon/fm.png"><b v-if="airportTitle">上海航空</b></span>
    <span v-if="airportObj.carrier == 'FU'" title="福州航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">福州航空</b></span>
    <span v-if="airportObj.carrier == 'G5'" title="华夏航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">华夏航空</b></span>
    <span v-if="airportObj.carrier == 'GJ'" title="长龙航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">长龙航空</b></span>

    <span v-if="airportObj.carrier == 'GS'" title="天津航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">天津航空</b></span>
    <span v-if="airportObj.carrier == 'GT'" title="桂林航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">桂林航空</b></span>
    <span v-if="airportObj.carrier == 'GX'" title="北部湾航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">北部湾航空</b></span>
    <span v-if="airportObj.carrier == 'GY'" title="多彩航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">多彩航空</b></span>
    <span v-if="airportObj.carrier == 'HO'" title="吉祥航空"><img src="../../../assets/images/airportIcon/ho.png"><b v-if="airportTitle">吉祥航空</b></span>
    <span v-if="airportObj.carrier == 'HU'" title="海南航空"><img src="../../../assets/images/airportIcon/hu.png"><b v-if="airportTitle">海南航空</b></span>
    <span v-if="airportObj.carrier == 'HX'" title="香港航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">香港航空</b></span>
    <span v-if="airportObj.carrier == 'JD'" title="首都航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">首都航空</b></span>
    <span v-if="airportObj.carrier == 'JR'" title="幸福航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">幸福航空</b></span>
    <span v-if="airportObj.carrier == 'KN'" title="联合航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">联合航空</b></span>
    <span v-if="airportObj.carrier == 'KY'" title="昆明航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">昆明航空</b></span>

    <span v-if="airportObj.carrier == 'MF'" title="厦门航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">厦门航空</b></span>
    <span v-if="airportObj.carrier == 'MU'" title="东方航空"><img src="../../../assets/images/airportIcon/mu.png"><b v-if="airportTitle">东方航空</b></span>
    <span v-if="airportObj.carrier == 'NS'" title="河北航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">河北航空</b></span>
    <span v-if="airportObj.carrier == 'OQ'" title="重庆航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">重庆航空</b></span>
    <span v-if="airportObj.carrier == 'PN'" title="西部航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">西部航空</b></span>
    <span v-if="airportObj.carrier == 'QW'" title="青岛航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">青岛航空</b></span>
    <span v-if="airportObj.carrier == 'RY'" title="江西航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">江西航空</b></span>
    <span v-if="airportObj.carrier == 'SC'" title="山东航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">山东航空</b></span>
    <span v-if="airportObj.carrier == 'TV'" title="西藏航空公司"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">西藏航空公司</b></span>
    <span v-if="airportObj.carrier == 'UQ'" title="乌鲁木齐航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">乌鲁木齐航空</b></span>
    <span v-if="airportObj.carrier == 'VD'" title="河南航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">河南航空</b></span>
    <span v-if="airportObj.carrier == 'Y8'" title="扬子江航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">扬子江航空</b></span>
    <span v-if="airportObj.carrier == 'YI'" title="英安航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">英安航空</b></span>
    <span v-if="airportObj.carrier == 'ZH'" title="深圳航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">深圳航空</b></span>

    <!-- <span v-if="airportObj.com == '南方航空'"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">四川航空</b></span>
    <span v-if="airportObj.com == '春秋航空'"><img src="../../../assets/images/airportIcon/9c.png"><b v-if="airportTitle">四川航空</b></span>
    <span v-if="airportObj.com == '中国航空'"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">四川航空</b></span>
    <span v-if="airportObj.com == '吉祥航空'"><img src="../../../assets/images/airportIcon/ho.png"><b v-if="airportTitle">四川航空</b></span>
    <span v-if="airportObj.com == '海南航空'"><img src="../../../assets/images/airportIcon/hu.png"><b v-if="airportTitle">四川航空</b></span>
    <span v-if="airportObj.com == '上海航空'"><img src="../../../assets/images/airportIcon/fm.png"><b v-if="airportTitle">四川航空</b></span>
    <span v-if="airportObj.com == '东方航空'"><img src="../../../assets/images/airportIcon/mu.png"><b v-if="airportTitle">四川航空</b></span> -->
  </span>
</template>

<script>

export default {
  props: ["airportObj","airportTitle"],
  data () {
    return {

    }
  },
  components: {

  },
  watch: {

  },
  methods: {

  },
  mounted () {
    console.log(this.airportTitle)
  }
}
</script>

<style>
.airportpic b{
  padding: 0 5px;
  font-weight: normal
}
</style>

<template>
  <span class="airportpic">
    <span v-if="carrier == '3U'" title="四川航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">四川航空</b></span>
    <span v-if="carrier == '8L'" title="祥鹏航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">祥鹏航空</b></span>
    <span v-if="carrier == '9C'" title="春秋航空"><img src="../../../assets/images/airportIcon/9c.png"><b v-if="airportTitle">春秋航空</b></span>
    <span v-if="carrier == '9H'" title="长安航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">长安航空</b></span>
    <span v-if="carrier == 'A6'" title="云南红土航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">云南红土航空</b></span>
    <span v-if="carrier == 'AQ'" title="九元航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">九元航空</b></span>
    <span v-if="carrier == 'BK'" title="奥凯"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">奥凯</b></span>
    <span v-if="carrier == 'CA'" title="国航"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">国航</b></span>
    <span v-if="carrier == 'CN'" title="大新华"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">大新华</b></span>

    <span v-if="carrier == 'CZ'" title="南方航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">南方航空</b></span>
    <span v-if="carrier == 'DR'" title="瑞丽航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">瑞丽航空</b></span>
    <span v-if="carrier == 'DZ'" title="东海航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">东海航空</b></span>
    <span v-if="carrier == 'EU'" title="成都航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">成都航空</b></span>
    <span v-if="carrier == 'FM'" title="上海航空"><img src="../../../assets/images/airportIcon/fm.png"><b v-if="airportTitle">上海航空</b></span>
    <span v-if="carrier == 'FU'" title="福州航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">福州航空</b></span>
    <span v-if="carrier == 'G5'" title="华夏航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">华夏航空</b></span>
    <span v-if="carrier == 'GJ'" title="长龙航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">长龙航空</b></span>

    <span v-if="carrier == 'GS'" title="天津航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">天津航空</b></span>
    <span v-if="carrier == 'GT'" title="桂林航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">桂林航空</b></span>
    <span v-if="carrier == 'GX'" title="北部湾航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">北部湾航空</b></span>
    <span v-if="carrier == 'GY'" title="多彩航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">多彩航空</b></span>
    <span v-if="carrier == 'HO'" title="吉祥航空"><img src="../../../assets/images/airportIcon/ho.png"><b v-if="airportTitle">吉祥航空</b></span>
    <span v-if="carrier == 'HU'" title="海南航空"><img src="../../../assets/images/airportIcon/hu.png"><b v-if="airportTitle">海南航空</b></span>
    <span v-if="carrier == 'HX'" title="香港航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">香港航空</b></span>
    <span v-if="carrier == 'JD'" title="首都航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">首都航空</b></span>
    <span v-if="carrier == 'JR'" title="幸福航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">幸福航空</b></span>
    <span v-if="carrier == 'KN'" title="联合航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">联合航空</b></span>
    <span v-if="carrier == 'KY'" title="昆明航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">昆明航空</b></span>

    <span v-if="carrier == 'MF'" title="厦门航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">厦门航空</b></span>
    <span v-if="carrier == 'MU'" title="东方航空"><img src="../../../assets/images/airportIcon/mu.png"><b v-if="airportTitle">东方航空</b></span>
    <span v-if="carrier == 'NS'" title="河北航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">河北航空</b></span>
    <span v-if="carrier == 'OQ'" title="重庆航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">重庆航空</b></span>
    <span v-if="carrier == 'PN'" title="西部航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">西部航空</b></span>
    <span v-if="carrier == 'QW'" title="青岛航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">青岛航空</b></span>
    <span v-if="carrier == 'RY'" title="江西航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">江西航空</b></span>
    <span v-if="carrier == 'SC'" title="山东航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">山东航空</b></span>
    <span v-if="carrier == 'TV'" title="西藏航空公司"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">西藏航空公司</b></span>
    <span v-if="carrier == 'UQ'" title="乌鲁木齐航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">乌鲁木齐航空</b></span>
    <span v-if="carrier == 'VD'" title="河南航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">河南航空</b></span>
    <span v-if="carrier == 'Y8'" title="扬子江航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">扬子江航空</b></span>
    <span v-if="carrier == 'YI'" title="英安航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">英安航空</b></span>
    <span v-if="carrier == 'ZH'" title="深圳航空"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">深圳航空</b></span>

    <!-- <span v-if="com == '南方航空'"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">四川航空</b></span>
    <span v-if="com == '春秋航空'"><img src="../../../assets/images/airportIcon/9c.png"><b v-if="airportTitle">四川航空</b></span>
    <span v-if="com == '中国航空'"><img src="../../../assets/images/airportIcon/cz.png"><b v-if="airportTitle">四川航空</b></span>
    <span v-if="com == '吉祥航空'"><img src="../../../assets/images/airportIcon/ho.png"><b v-if="airportTitle">四川航空</b></span>
    <span v-if="com == '海南航空'"><img src="../../../assets/images/airportIcon/hu.png"><b v-if="airportTitle">四川航空</b></span>
    <span v-if="com == '上海航空'"><img src="../../../assets/images/airportIcon/fm.png"><b v-if="airportTitle">四川航空</b></span>
    <span v-if="com == '东方航空'"><img src="../../../assets/images/airportIcon/mu.png"><b v-if="airportTitle">四川航空</b></span> -->
  </span>
</template>

<script>

export default {
  props: ['carrier', 'airportTitle'],
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

}
}
</script>

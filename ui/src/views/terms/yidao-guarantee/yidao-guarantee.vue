
<style lang='scss' scoped>
@import "./yidao-guarantee.scss";
</style>
<template>
  <div class="container">
    <div class="brand-content">
      <div class="simple-igola">
        <p class="igola-simple-title1">什么是 易道 智能拼接机票？</p>
        <p>易道智能拼接机票由多张机票组合而成，需要在换机票换乘地提取和重新托运行李，并可能需要重新出入关。当航班发生延误、变更或取消，导致乘客无法到达目的地，我们也会为您提供：</p>
        <strong class="igola-simple-title2">易道拼接机票保障您出行</strong>
        <p class="none-top">当前航班因时间调整或取消影响您后续航班正常衔接，我们为您提供以下任一保障服务：</p>
        <div class="tips-new-content">
          <div>
            <Icon type="ios-image-outline"/>
            <span>免费更换到合适航班</span>
          </div>
          <div>
            <Icon type="ios-image-outline"/>
            <span>未使用机票全额退款</span>
          </div>
          <div>
            <Icon type="ios-image-outline"/>
            <span>替换其他交通工具</span>
          </div>
        </div>

        <strong class="igola-simple-title2">我们还为您提供附加保障</strong>

        <p class="none-top">当前航班因时间调整或取消影响您后续航班衔接，我们为您提供以下任一保障服务：</p>

        <div class="tips-new-content">
          <div class="second-service-div">
            <Icon type="ios-image-outline"/>
            <span>免费住宿服务</span>
            <br>
            <span class="last-new-span">备选航班/其他交通在次日0点后，且您需要滞留超过8（含）小时</span>
          </div>
          <div class="second-service-div">
            <Icon type="ios-image-outline"/>
            <span>餐饮费用报销</span>
            <br>
            <span class="last-new-span">备选航班/其他交通在等待时间超过4（含）小时</span>
          </div>
        </div>

        <strong class="igola-simple-title2">提供保障服务时，我们需要您的配合</strong>

        <p class="none-top none-bottom">1、如遇航班时间调整或取消，请您及时联系我们沟通处理;</p>

        <p class="none-top">2、易道会及时为您提供保障方案，请您在收到方案后的24小时内或下一程航班/交通工具出发前4小时回应我们;</p>

        <Button @click='show=true'>阅读《易道保障》更多细节</Button>
      </div>
      <div class="main-new-content" v-if='show'>
        <table>
          <tr class="tr-one">
            <td colspan="2" rowspan="2" class="line">
              <table class="line-table">
                <tr>
                  <td></td>
                  <td>场景</td>
                </tr>
                <tr>
                  <td>保障与用户责任</td>
                  <td></td>
                </tr>
              </table>
            </td>
            <td>场景A</td>
            <td>场景B</td>
          </tr>

          <tr class="tr-one-other">
            <td>
              距离航班出发
              <span class="main-text">48（不含）小时</span>
              前收到航班时间调整或取消，且影响您后续航班的正常衔接
            </td>
            <td>
              距离航班出发
              <span class="main-text">48（不含）小时</span>
              前收到航班时间调整或取消，且影响您后续航班的正常衔接
            </td>
          </tr>

          <tr>
            <td rowspan="3">
              <strong>易道保障服务（三选一）</strong>
            </td>
            <td>
              <strong>免费更改到合适航班</strong>
            </td>
            <td class="common-justify">由易道为您更改到合适的航班，保障您顺利到达目的地。</td>
            <td
              class="common-justify"
            >由易道无法及时为您购买备选航班/其他交通工具，您可在双方统一的基础上自行购买（不限购买渠道），报销金额不超过影响航班全额票款。。</td>
          </tr>

          <tr>
            <td>
              <strong>替换其他交通工具</strong>
            </td>
            <td
              class="common-justify"
            >易道为您提供不仅限于航班的备选方案，您可在双方同意的基础上选择自行购买或由易道协助购买其他交通工具，报销金额不超过受影响航班全额票款。</td>
            <td
              class="common-justify"
            >由易道无法及时为您购买备选航班/易道为您提供不仅限于航班的备选方案，您可在双方同意的基础上选择自行购买或由易道协助购买其他交通工具，报销金额不超过受影响航班全额票款。</td>
          </tr>

          <tr>
            <td>
              <strong>未使用机票全额退款</strong>
            </td>
            <td
              class="common-justify"
            >易道无法为您提供以上两个备选方案时，将全额退还剩余未使用机票的费用。</td>
            <td
              class="common-justify"
            >易道无法为您提供以上两个备选方案时，将全额退还剩余未使用机票的费用。</td>
          </tr>

          <tr>
            <td rowspan="2">
              <strong>易道附加保障</strong>
            </td>
            <td>
              <strong>免费住宿服务</strong>
            </td>
            <td colspan="2" class="left-td">
              <strong>在满足以下全部条件下，易道为您提供免费住宿服务：</strong>
              <br>
              <span>1）备选航班/其他交通在次日0点后； </span>
              <span>2）需在中转地滞留超过<span class="main-text">8（含）小时</span>以上。</span>
              <p>除此之外，被通知航班时间调整或取消时间起至备选航班出发时间超过4-12小时的，且备选航班在次日0点后，易道仍为您提供免费住宿服务。</p>
            </td>
          </tr>

          <tr>
             <td>
              <strong>餐饮费用报销</strong>
            </td>
            <td colspan="2"> 备选航班/其他交通等待时间超过<span class="main-text">，易道为您提供最高不超过</span><span class="main-text">100元</span>人民币的餐饮报销。
            </td>
          </tr>

          <tr>
            <td class="none-border">
              <strong>注意：</strong>
            </td>
            <td colspan="3" class="none-border left-td">
              <ul>
                <li>场景 B 您可自由选择更换航班或退款，但场景 A 将由 易道 决定选择何种方案；</li>
                <li>易道附加保障由 易道 客服判定和提供，您在获得易道 客服确认后可以先行付款；</li>
                <li>若机场和航空公司提供了上述服务，易道 则不再补偿或者报销。</li>
              </ul>
            </td>
          </tr>
        </table>

        <table class="table-two">
          <tr>
            <td rowspan="3">
              <strong>您的责任</strong>
            </td>
            <td>
              <strong>及时通知易道</strong>
            </td>
            <td>距离航班出发<span class="main-text">48小时前</span>通知易道，否则不在保障范围。
            </td>
            <td class="common-justify">收到航班时间调整/取消消息<span class="main-text">4小时（含）</span>内通知易道，否则不在保障范围
            </td>
          </tr>

          <tr>
            <td>
              <strong>及时回应</strong>
            </td>
            <td colspan="2">收到方案后的<span class="main-text">24小时内</span>或下一程航班/交通工具出发<span class="main-text">前4小时</span>回应我们，若因此而造成的损失不在保障范围内。</td>
          </tr>

          <tr>
             <td>
              <strong>报销要求</strong>
            </td>
             <td colspan="2">抵达目的地日期起<span class="main-text">30天内</span>联系我们报销费用，报销时需您提供航班延误/取消证明、购票凭证。</td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      show: false
    }
  },
  components: {},
  methods: {},
  mounted () {}
}
</script>

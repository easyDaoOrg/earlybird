
<style lang='scss' scoped>
@import "./bookingForm.scss";
</style>
<style>
  .booking-form .ivu-form-item{
    width: calc(50% - 20px);
    margin-right: 20px;
    float: left;
  }
  .booking-form .ivu-form-item .ivu-form-item-label{
    font-size: 14px;
    color: #0096ff;
  }
  .booking-form .booking-form-card .ivu-select{
    width: 100px!important;
    float: left;
  }
  .booking-form .booking-form-date .ivu-select{
    width: 32%!important;
    float: left;
    margin-right: 1%;
  }
  .booking-form .booking-form-card .ivu-input-wrapper{
    width: calc(100% - 110px);
    margin-left: 10px;
    float: left;
  }
  .booking-form .booking-form-tel .ivu-select{
    width: 160px!important;
    float: left;
  }
  .booking-form .booking-form-tel .ivu-input-wrapper{
    width: calc(100% - 170px);
    margin-left: 10px;
    float: left;
  }
  .booking-form .booking-form-travel{
    width: 50%;
    float: left;
  }
  .booking-form .booking-form-travel .ivu-form-item:last-child{
    margin-right: 0;
  }
  .booking-form .booking-form-travel .ivu-form-item{
    margin-bottom: 2px;
  }
  .booking-form .booking-form-e .ivu-form-item{
    margin-bottom: 0;
  }
  .booking-form .ivu-form-item-label{
    height: 24px;
  }
</style>

<template>
  <div class="booking-form clearfix">
    <Form label-position="top" ref="ruleInline" :model="formTop" :rules="ruleInline">
      <div class="clearfix" v-if="bookingForm == 0">
          <div class="booking-form-title">证件信息</div>
          <FormItem label="旅行证件">
            <div class="booking-form-card">
              <bselect :bookingFormModel="'modelCard'" :bookingFormList="formTop.cardList" :bookingFormPlaceholder="'请选择'"></bselect>
              <binput :bookingFormModel="'modelNumber'" :bookingFormPlaceholder="'证件号码'"></binput>
            </div>
          </FormItem>

          <FormItem label="签发地">
            <div class="booking-form-address">
              <bselect :bookingFormModel="'modelAddress'" :bookingFormList="formTop.addressList" :bookingFormPlaceholder="'请选择'"></bselect>
            </div>
          </FormItem>

          <FormItem label="有效期">
            <div class="booking-form-date">
                <bselect :bookingFormModel="'modelYear'" :bookingFormList="formTop.yearList" :bookingFormPlaceholder="'年'"></bselect>
                <bselect :bookingFormModel="'modelMonth'" :bookingFormList="formTop.monthList" :bookingFormPlaceholder="'月'"></bselect>
                <bselect :bookingFormModel="'modelDay'" :bookingFormList="formTop.dayList" :bookingFormPlaceholder="'日'"></bselect>
            </div>
          </FormItem>

          <div class="booking-form-title">旅客信息</div>
          <div class="booking-form-all clearfix">
            <div class="booking-form-travel">
              <div>
                <FormItem label="姓">
                  <binput :bookingFormModel="'modelXing'" :bookingFormPlaceholder="'姓（拼音/英文）'"></binput>
                </FormItem>
                <FormItem label="名">
                    <binput :bookingFormModel="'modelMing'" :bookingFormPlaceholder="'名（拼音/英文）'"></binput>
                </FormItem>
              </div>
              <div class="booking-form-travel-tips">旅客姓名必须与所选择的证件上的姓名一致。</div>
            </div>
          </div>

          <FormItem label="出生日期">
            <div class="booking-form-date">
                <bselect :bookingFormModel="'modelYear2'" :bookingFormList="formTop.yearList" :bookingFormPlaceholder="'年'"></bselect>
                <bselect :bookingFormModel="'modelMonth2'" :bookingFormList="formTop.monthList" :bookingFormPlaceholder="'月'"></bselect>
                <bselect :bookingFormModel="'modelDay2'" :bookingFormList="formTop.dayList" :bookingFormPlaceholder="'日'"></bselect>
            </div>
          </FormItem>

          <FormItem label="性别">
            <RadioGroup v-model="formTop.modelAge">
                <Radio label="男">男</Radio>
                <Radio label="女">女</Radio>
            </RadioGroup>
          </FormItem>

          <FormItem label="国籍">
            <div class="booking-form-address">
              <bselect :bookingFormModel="'modelCountry'" :bookingFormList="formTop.addressList" :bookingFormPlaceholder="'请选择'"></bselect>
            </div>
          </FormItem>

        </div>

        <div class="clearfix" v-if="bookingForm == 1">
          <div class="booking-form-travel">
            <div>
              <FormItem label="联系人">
                <binput :bookingFormModel="'modelXing2'" :bookingFormPlaceholder="'姓（拼音/英文）'"></binput>
              </FormItem>
              <FormItem label=" ">
                  <binput :bookingFormModel="'modelMing2'" :bookingFormPlaceholder="'名（拼音/英文）'"></binput>
              </FormItem>
            </div>
          </div>

          <div class="booking-form-e">
            <FormItem label="邮箱">
              <div class="booking-form-email">
                <binput :bookingFormModel="'modelEmail'" :bookingFormPlaceholder="'必填（格式：a@b.cd）'"></binput>
                <div class="booking-form-travel-tips">提示：用于接收电子行程单与航班信息</div>
              </div>
            </FormItem>
          </div>

          <FormItem label="手机号">
            <div class="booking-form-tel">
              <bselect :bookingFormModel="'modelTelCountry'" :bookingFormList="formTop.telCountryList" :bookingFormPlaceholder="'请选择'"></bselect>
              <binput :bookingFormModel="'modelTel'" :bookingFormPlaceholder="'必填（7-11位手机号码）'"></binput>
            </div>
          </FormItem>
        </div>

    </Form>
  </div>
</template>

<script>
import bselect from './select/select'
import binput from './input/input'

export default {
  props: ['bookingForm'],
  data () {
    return {
      formTop: {
        modelXing: '',
        modelMing: '',
        modelXing2: '',
        modelMing2: '',
        modelCard: '',
        modelNumber: '',
        modelAddress: '',
        modelYear: '',
        modelMonth: '',
        modelDay: '',
        modelYear2: '',
        modelMonth2: '',
        modelDay2: '',
        modelCountry: '',
        modelEmail: '',
        modelTelCountry: '',
        modelTel: '',
        modelAge: '',
        cardList: [
          {
            value: '护照',
            label: '护照'
          },
          {
            value: '身份证',
            label: '身份证'
          },
          {
            value: '回乡证',
            label: '回乡证'
          },
          {
            value: '台胞证',
            label: '台胞证'
          },
          {
            value: '港澳台居民居住证',
            label: '港澳台居民居住证'
          }
        ],
        addressList: [
          {
            value: '中国',
            label: '中国'
          }
        ],
        yearList: [
          {
            value: '2019',
            label: '2019'
          }
        ],
        monthList: [
          {
            value: '1',
            label: '1'
          }
        ],
        monthList: [
          {
            value: '1',
            label: '1'
          }
        ],
        telCountryList: [
          {
            value: '中国大陆（+86）',
            label: '中国大陆（+86）'
          }
        ]
      },
      ruleInline: {
        // user: [
        //     { required: true, message: 'Please fill in the user name', trigger: 'blur' }
        // ],
        // password: [
        //     { required: true, message: 'Please fill in the password.', trigger: 'blur' },
        //     { type: 'string', min: 6, message: 'The password length cannot be less than 6 bits', trigger: 'blur' }
        // ]
      }
    }
  },
  components: {
    bselect,
    binput
  },
  watch: {

  },
  methods: {

  },
  mounted () {
    this.$bus.on('on-selectchange', (key, data) => {
      this.formTop[key] = data
      console.log(this.formTop)
    })
  }
}
</script>

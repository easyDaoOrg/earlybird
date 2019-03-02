
<style lang='scss' scoped>
@import "./bookingForm.scss";
</style>
<style>
  /* .booking-form .ivu-form-item{
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
  } */
</style>
<style>
.booking-form .ivu-input {
  height: 40px;
  line-height: 40px;
  font-size: 14px;
  padding-left: 13px;
  border-radius: 3px;
}
.inputLeft .ivu-input {
  border-radius: 3px 0 0 3px !important;
}
.borderRightNone .ivu-input {
  border-right: 0;
}
.inputRight .ivu-input {
  border-radius: 0 3px 3px !important;
}
.form-group .ivu-form-item{
   width: 100%;
}
.form-group .ivu-select-single .ivu-select-selection{
  height: 40px;
}
.form-group .ivu-select-single .ivu-select-selection .ivu-select-placeholder, .ivu-select-single .ivu-select-selection .ivu-select-selected-value{
    height: 40px;
    line-height: 40px
}
/* .form .ivu-row{
  height: 60px;
} */
.form-group .ivu-radio-wrapper{
  height: 40px;
  line-height: 40px;
  padding: 0 15px;
  border: 1px solid #dcdcdc;
  border-radius: 3px;
}
.rorderRight .ivu-select-selection{
  border-radius: 4px 0 0 4px;
  border-right: none;
}

</style>
<template>
  <div class="booking-form clearfix">
    <!-- <Form label-position="top" ref="ruleInline" :model="formTop" :rules="ruleInline">
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

    </Form> -->
    <Form ref="formInlineRef1" :model="formCustom" :rules="ruleCustom" :label-width="0" v-if="bookingForm == 0">
          <h6 class="booking-form-title">证件信息：</h6>
          <Row>
            <Col span="12">
                <div class="form-group">
                    <label for="">旅行证件</label>
                    <FormItem
                        prop="category"
                        >
                        <Select v-model="formCustom.category" style="width:100%" class='rorderRight'>
                          <Option v-for="item in identity" :value="item.value" :key="item.value">{{ item.label }}</Option>
                        </Select>
                    </FormItem>
                      <!-- :rules="{required: true,validator:identificationNumberType(formCustom.category)}" -->
                    <FormItem
                        prop="number"
                        :rules="{required: true,validator:identificationNumberType(formCustom.category)}"
                        >
                        <Input v-model="formCustom.number" placeholder="证件号码" style="width: 100%" class='inputRight'/>
                        <!-- <Input v-model="formCustom.number" placeholder="证件号码" style="width: 100%" class='inputRight'/> -->
                    </FormItem>
                </div>
              </Col>

          </Row>
           <div class="booking-form-title">旅客信息</div>
          <Row>
            <Col span="12">
                  <div class="form-group">
                      <label for="">名字</label>
                      <FormItem prop="familyNameZh">
                        <Input v-model="formCustom.familyNameZh" placeholder="姓（中文/拼音）" style="width: 100%;" class='inputLeft borderRightNone' @on-change='onNameChange'/>
                      </FormItem>
                      <FormItem prop="givenNameZh">
                        <Input v-model="formCustom.givenNameZh" placeholder="名（中文/拼音）" style="width: 100%" class='inputRight' @on-change='onNameChange'/>
                      </FormItem>
                      <div class="form-group-tips">旅客姓名必须与所选择的证件上的姓名一致。</div>
                  </div>

            </Col>
            <Col span="12">
                   <div class="form-group" v-if='formCustom.category==="PP"'>
                      <label for="">性别</label>
                        <FormItem prop="gender">
                          <RadioGroup v-model="formCustom.gender">
                              <Radio label="MALE"><span>男</span></Radio>
                              <Radio label="FEMALE"><span>女</span></Radio>
                          </RadioGroup>
                        </FormItem>
                  </div>
            </Col>
          </Row>
          <Row>
             <Col span="12" v-if='formCustom.category==="PP"'>
                  <div class="form-group" style='padding-bottom:0;margin-top:20px;' >
                    <label for="">出生日期</label>
                    <FormItem prop="birthday">
                        <DatePicker type="date" placeholder="请选择" style="width:50%" v-model="formCustom.birthday" format="yyyy年MM月dd日" ></DatePicker>
                    </FormItem>
                  </div>

              </Col>
          </Row>
    </Form>
  </div>
</template>

<script>
import bselect from './select/select'
import binput from './input/input'
import {user} from '../../../../assets/json/user.js'
export default {
  props: ['bookingForm'],
  data () {
    const empty = '必填项'
    // 非空
    const _onEmpty = (rule, value, callback) => {
      if (value === '') {
        callback(new Error(empty))
      } else {
        callback()
      }
    }
    const _familyNameZh = (rule, value, callback) => {
      if (value === '') {
        callback(new Error(empty))
      } else {
        let reg = /^[\u4e00-\u9fa5a-zA-Z]+$/
        if (!reg.test(value)) {
          callback(new Error('请输入中文或拼音'))
        }
        callback()
      }
    }
    // 身份证验证
    const idNumber = (rule, value, callback) => {
      var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/
      if (value === '') {
        callback(new Error(empty))
      } else if (!reg.test(value)) {
        callback(new Error('您填写的证件号可能有误，请仔细核对'))
      } else {
        callback()
      }
    }
    // 护照
    const _passport = (rule, value, callback) => {
      var reg = /^([a-zA-z]|[0-9]){5,17}$/
      if (value === '') {
        callback(new Error(empty))
      } else if (!reg.test(value)) {
        callback(new Error('您填写的证件号可能有误，请仔细核对'))
      } else {
        callback()
      }
    }
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
      },
      formCustom: {
        category: 'NI',
        familyNameZh: '',
        givenNameZh: '',
        gender: 'MALE',
        birthday: '',
        number: ''
        //  familyNameZh: '刘',
        // givenNameZh: '备',
        // birthday: '1977-01-27',
      },
      ruleCustom: {
        familyNameZh: [{ validator: _familyNameZh, trigger: 'blur' }],
        givenNameZh: [{ validator: _familyNameZh, trigger: 'blur' }],
        // 出生日期
        birthday: [{ validator: _onEmpty, type: 'date' }]
      },
      emptyNews: '必填项',
      identity: user.identity.slice(0, 2),
      // 证件 验证
      id_number: idNumber,
      passport: _passport
    }
  },
  components: {
    bselect,
    binput
  },
  watch: {

  },
  mounted () {
    this.$emit('on-emit-top', this)
    // this.$bus.on('on-selectchange', (key, data) => {
    //   this.formTop[key] = data
    //   console.log(this.formTop)
    // })
  },
  methods: {
    // 证件号码的校验
    identificationNumberType (type) {
      if (type === 'NI') { // 身份证
        return this.id_number
      } else if (type === 'PP') { // 护照
        return this.passport
      }
    },
    onSubmit () {
      let n = true
      this.$refs['formInlineRef1'].validate(valid => {
        n = valid
      })
      return n
    },
    clear () {
      this.$refs['formInlineRef1'].resetFields()
    },
    setFormCustom (data) {
      // {
      //   'guid': 'bac2bfcb-1e24-41c1-baad-8a6e75b64af5',
      //   'familyNameZh': '张',
      //   'givenNameZh': '飞',
      //   'familyName': 'DEI',
      //   'givenName': 'BU',
      //   'middleName': '',
      //   'nationality': 'CN',
      //   'gender': 'MALE',
      //   'birthday': '1991-01-27',
      //   'primary': false,
      //   'credentials': [
      //     {
      //       'guid': '54662ca3-3904-4412-ba86-d5d3d291b5e1',
      //       'category': 'NI',
      //       'number': '130706199201270313',
      //       'issueAt': null,
      //       'expiredAt': '',
      //       'issueDate': ''
      //     },
      //     {
      //       'guid': 'ce193f2b-f094-4713-b4a3-cd1d10216a60',
      //       'category': 'GA',
      //       'number': '123',
      //       'issueAt': 'HK',
      //       'expiredAt': '2022-05-04',
      //       'issueDate': '2016-01-01'
      //     },
      //     {
      //       'guid': '9a077e42-3127-4c13-b227-39b52bcf4771',
      //       'category': 'TW',
      //       'number': '123213',
      //       'issueAt': 'HK',
      //       'expiredAt': '2022-04-04',
      //       'issueDate': '2016-01-01'
      //     },
      //     {
      //       'guid': 'f1430854-296a-4988-bac0-9449977d4f44',
      //       'category': 'HX',
      //       'number': '123',
      //       'issueAt': 'CN',
      //       'expiredAt': '2020-01-02',
      //       'issueDate': '2016-01-01'
      //     },
      //     {
      //       'guid': '417ce847-1535-438b-9952-2ffdd13db35a',
      //       'category': 'TB',
      //       'number': '123QWEQWE',
      //       'issueAt': 'CN',
      //       'expiredAt': '2020-03-01',
      //       'issueDate': '2016-01-01'
      //     },
      //     {
      //       'guid': 'aa01ccf3-42dc-4ddb-961d-d2b9d750ba79',
      //       'category': 'PP',
      //       'number': 'P1234567',
      //       'issueAt': 'CN',
      //       'expiredAt': '2019-02-02',
      //       'issueDate': '2016-01-01'
      //     }
      //   ]
      // },
      console.log(this.formCustom)
      this.formCustom.familyNameZh = data.familyNameZh
      this.formCustom.givenNameZh = data.givenNameZh
      this.formCustom.gender = data.gender
      this.formCustom.birthday = data.birthday
      this.$emit('on-name-change', data.familyNameZh + data.givenNameZh)
      for (let j = 0; j < this.identity.length; j++) {
        let keep = null
        for (let i = 0; i < data.credentials.length; i++) {
          if (data.credentials[i].category === this.identity[j].value) {
            keep = data.credentials[i]
          }
        }
        if (keep) {
          this.formCustom.number = keep.number
          this.formCustom.category = keep.category
          break
        }
      }
    },
    onNameChange (data) {
      if (this.formCustom.familyNameZh !== '' && this.formCustom.givenNameZh !== '') {
        this.$emit('on-name-change', this.formCustom.familyNameZh + this.formCustom.givenNameZh)
      } else {
        this.$emit('on-name-change', '')
      }
    }
  },
  beforeDestroy () {
    this.$emit('on-form-item-remove', this)
  }
}
</script>

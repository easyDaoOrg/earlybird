
<style lang='scss' scoped>
@import "./bookingForm.scss";
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
    <!-- 常用联系人 -->
    <Form ref="formInlineRef" :model="formCustom" :rules="ruleCustom" :label-width="0">
          <h6 class="booking-form-title">证件信息：</h6>
          <Row>
            <Col span="12">
                <div class="form-group">
                    <label for="">旅行证件</label>
                    <FormItem
                        prop="category"
                        >
                        <Select v-model="formCustom.category" style="width:100%" class='rorderRight' @on-change='identityInformation'>
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
                        <Input v-model="formCustom.familyNameZh" :placeholder="identityPlaceholder" style="width: 100%;" class='inputLeft borderRightNone' @on-change='onNameChange'/>
                      </FormItem>
                      <FormItem prop="givenNameZh">
                        <Input v-model="formCustom.givenNameZh" :placeholder="identityPlaceholder" style="width: 100%" class='inputRight' @on-change='onNameChange'/>
                      </FormItem>
                      <div class="form-group-tips">旅客姓名必须与所选择的证件上的姓名一致。</div>
                  </div>

            </Col>
            <Col span="12">
                   <div class="form-group" v-if='formCustom.category==="PP"'>
                      <label for="">性别</label>
                        <FormItem prop="gender">
                          <RadioGroup v-model="formCustom.gender">
                              <Radio :label='genderObj.male'><span>男</span></Radio>
                              <Radio :label="genderObj.female"><span>女</span></Radio>
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
import {user} from '@/assets/json/user'
// 定义手机还是护照
var _identityType = 'NI'
export default {
  props: {
    formCustom: {
      type: Object
    }
  },
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
        let error = '请输入中文或拼音'
        // 护照只能输入英文
        if (_identityType === 'PP') {
          reg = /^[a-zA-Z]+$/
          error = '请输入英文或拼音'
        }
        if (!reg.test(value)) {
          callback(new Error(error))
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
      // 性别
      genderObj: {
        male: 1,
        female: 0
      },
      phoneList: user.phoneList,
      // formCustom: {
      //   category: 'NI',
      //   familyNameZh: '',
      //   givenNameZh: '',
      //   gender: 1,
      //   birthday: '',
      //   number: ''
      // },
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
      passport: _passport,
      identityPlaceholder: '姓（中文/拼音）'
    }
  },
  watch: {},
  mounted () {
    this.$emit('on-emit-top', this)
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
      this.$refs['formInlineRef'].validate(valid => {
        n = valid
      })
      return n
    },
    clear () {
      this.$refs['formInlineRef'].resetFields()
    },
    setFormCustom (data) {
      
      this.formCustom.id = data.id;
      this.formCustom.familyNameZh = data.familyNameZh
      this.formCustom.givenNameZh = data.givenNameZh
      this.formCustom.gender = data.gender === 'man' ? 1 : 0
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
          this.identityInformation(this.formCustom.category)
          break
        }
      }

      setTimeout(() => {
        this.$refs['formInlineRef'].validate(valid => {})
      }, 0)
    },
    onNameChange (data) {
      if (this.formCustom.familyNameZh !== '' && this.formCustom.givenNameZh !== '') {
        this.$emit('on-name-change', this.formCustom.familyNameZh + this.formCustom.givenNameZh)
      } else {
        this.$emit('on-name-change', '')
      }
    },
    identityInformation (data) {
      _identityType = data
      this.identityPlaceholder = this.formCustom.category === 'NI' ? '姓（中文/拼音）' : '姓（英文/拼音）'
    }
  },
  beforeDestroy () {
    this.$emit('on-form-item-remove', this)
  }
}
</script>

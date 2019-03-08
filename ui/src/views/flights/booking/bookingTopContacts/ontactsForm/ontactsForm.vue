
<style lang='scss' scoped>
@import "./ontactsForm.scss";
</style>
<style>
</style>
<style>
.ontactsForm .ivu-input {
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
  <div class="ontactsForm clearfix">
     <Form ref="ontactsForm" :model="comeObj" :rules="ruleCustom" :label-width="0">
         <Row>
              <Col span="12">
                    <div class="form-group">
                        <label for="">名字</label>
                        <FormItem prop="lastName">
                          <Input v-model="comeObj.lastName" placeholder="姓（中文/拼音）" style="width: 100%;" class='inputLeft borderRightNone'/>
                        </FormItem>
                        <FormItem prop="firstName">
                          <Input v-model="comeObj.firstName" placeholder="名（中文/拼音）" style="width: 100%" class='inputRight'/>
                        </FormItem>
                    </div>
              </Col>
              <Col span="12">
                      <div class="form-group paddingRight">
                          <label for="">邮箱</label>
                          <FormItem prop="email">
                              <Input v-model="comeObj.email" placeholder="必填（格式：a@b.cd）" style="width: 100%;" class='inputLeft'/>
                          </FormItem>
                      </div>
              </Col>
        </Row>
        <Row>
              <Col span="12">
                    <div class="form-group">
                        <label for="">手机</label>
                        <FormItem>
                             <Dropdown style='width:100%' @on-click='onPhone'>
                                <div class='phone'>
                                    {{comeObj.areaCode || '请选择'}}
                                    <Icon type="ios-arrow-down"></Icon>
                                </div>
                                <DropdownMenu slot="list">
                                    <DropdownItem v-for='(item,index) in phoneList' :key='index' :name='item.value'>{{item.label}}</DropdownItem>
                                </DropdownMenu>
                            </Dropdown>
                        </FormItem>
                        <FormItem prop="mobile">
                             <Input v-model="comeObj.mobile" placeholder="必填（7-11位手机号码）" style="width: 100%" class='inputRight'/>
                        </FormItem>
                    </div>
              </Col>
              <Col span="12">
                  <div class="form-group paddingRight">
                      <label for="">地址</label>
                      <FormItem prop="address">
                          <Input v-model="comeObj.address" placeholder="必填" style="width: 100%;" class='inputLeft'/>
                      </FormItem>
                  </div>
              </Col>
        </Row>

      </Form>
  </div>
</template>

<script>
import {user} from '@/assets/json/user'

export default {
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
    // 邮箱
    const _email = (rule, value, callback) => {
      if (value === '') {
        callback(new Error(empty))
      } else {
        let reg = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/
        if (!reg.test(value)) {
          callback(new Error('请输入正确的邮箱'))
        }
        callback()
      }
    }
    // 手机号
    const _phone = (rule, value, callback) => {
      if (value === '') {
        callback(new Error(empty))
      } else {
        let reg = /^1[0-9][0-9]\d{4,8}$/
        if (!reg.test(value)) {
          callback(new Error('手机号码长度为7-11位'))
        }
        callback()
      }
    }
    return {
      phoneList: user.phoneList,
      // 常用联系人
      comeObj: {
        lastName: '',
        firstName: '',
        email: '',
        areaCode: '86',
        mobile: '',
        address: ''
      },
      ruleCustom: {
        lastName: [{ validator: _familyNameZh, trigger: 'blur' }],
        firstName: [{ validator: _familyNameZh, trigger: 'blur' }],
        email: [{ validator: _email, trigger: 'blur' }],
        mobile: [{ validator: _phone, trigger: 'blur' }],
        address: [{ validator: _onEmpty, trigger: 'blur' }]
      }
    }
  },
  watch: {},
  mounted () {

  },
  methods: {
    onPhone (data) {
      this.comeObj.areaCode = data
    },
    onSubmit () {
      let type = true
      this.$refs['ontactsForm'].validate(valid => {
        type = valid
      })
      let obj = {}
      if (type) {
        obj = this.comeObj
      }
      return {
        type,
        obj
      }
    },
    clear () {
      this.$refs['ontactsForm'].resetFields()
    },
    setDefaultObj (data) {
      this.comeObj.lastName = data.lastName
      this.comeObj.firstName = data.firstName
      this.comeObj.email = data.email
      this.comeObj.areaCode = data.areaCode
      this.comeObj.mobile = data.mobile
      // 没有地址
      setTimeout(() => {
        this.$refs['ontactsForm'].validate(valid => {})
      }, 0)
    }
  },
  beforeDestroy () {

  }
}
</script>

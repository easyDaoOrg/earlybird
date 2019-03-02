
<style lang='scss' scoped>
@import "./passengers.scss";
</style>
<style>
.passengers .ivu-input {
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
  <main class="passengers">
    <h2>常用旅客</h2>
    <template v-if="!open">
      <div class="passengers-shops">
        <span>总计</span>
        <b>{{data.length}}</b>
        <span>个旅客</span>
        <p @click='addPassenger()'>
           <Icon type="ios-add-circle-outline" />
           <span>新增旅客</span>
        </p>
      </div>
      <div>
        <section class="card" v-for="(item,index) in data" :key="index">
          <h6>
            <strong>
              <span>{{index+1}}</span>
              {{item.familyNameZh}} {{item.givenNameZh}}
            </strong>
            <p>
              <span>{{item.gender==='FEMALE'?'女':'男'}}</span>
              <span class="line">|</span>
              <span>{{item.birthday}}</span>
            </p>
            <div class="card-edit card-delete" @click='onRemovePassenger(index)'>
              <Icon type="ios-remove-circle-outline" color="#ff484a" />
              <span>删除</span>
            </div>
            <div class="card-btn-line">|</div>
            <div class="card-edit" @click='onEditor(item)'>
              <Icon type="ios-create-outline" color="#3291eb"/>
              <span>编辑</span>
            </div>
          </h6>
          <ul>
            <li v-for="(option,i) in item['credentials']" :key="i">
              <label for>{{onIdentityFilter(option.category)}}</label>
              <span>{{option.number}}</span>
            </li>
          </ul>
        </section>
        <div class="new-card-add" @click='addPassenger()'>
          <Icon type="ios-add-circle-outline" />
          <span>新增旅客</span>
        </div>
      </div>
    </template>
    <div class="passenger-content" v-if="open">
      <header>
        <h5>新增</h5>
        <p v-if='editorOpen' @click='onRemovePassenger()'>
          <Icon type="ios-remove-circle-outline" />删除旅客
        </p>
      </header>
      <section class='form'>

          <Form ref="formInlineRef1" :model="formCustom" :rules="ruleCustom" :label-width="0">
            <h6>旅客信息：</h6>
            <Row>
              <Col span="12">
                    <div class="form-group">
                        <label for="">名字</label>
                        <FormItem prop="familyNameZh">
                          <Input v-model="formCustom.familyNameZh" placeholder="姓（中文/拼音）" style="width: 100%;" class='inputLeft borderRightNone'/>
                        </FormItem>
                        <FormItem prop="givenNameZh">
                          <Input v-model="formCustom.givenNameZh" placeholder="名（中文/拼音）" style="width: 100%" class='inputRight'/>
                        </FormItem>
                    </div>
              </Col>
              <Col span="12">
                      <div class="form-group">
                          <label for="">国籍</label>
                          <FormItem prop="nationality">
                              <Select v-model="formCustom.nationality" style="width:50%" :disabled='editorOpen'>
                                <Option v-for="item in nationalityList" :value="item.value" :key="item.value">{{ item.label }}</Option>
                              </Select>
                          </FormItem>
                      </div>
              </Col>
            </Row>
            <!-- 第二行 -->
            <Row>
              <Col span="12">
                    <div class="form-group" style='padding-bottom:0'>
                        <label for="">英文名</label>
                        <FormItem prop="familyName">
                          <Input v-model="formCustom.familyName" placeholder="姓（拼音/英文）" style="width: 100%;" class='inputLeft borderRightNone'/>
                        </FormItem>
                        <FormItem prop="givenName">
                          <Input v-model="formCustom.givenName" placeholder="名（拼音/英文）" style="width: 100%" class='inputRight'/>
                        </FormItem>
                        <FormItem prop="middleName" v-if='middleNameOpen'>
                          <Input v-model="formCustom.middleName" placeholder="中间名" style="width: 100%" class='inputRight'/>
                        </FormItem>
                    </div>
              </Col>
              <Col span="12">
                  <div class="form-group" style='padding-bottom:0'>
                    <label for="">出生日期</label>
                    <FormItem prop="birthday">
                        <DatePicker type="date" placeholder="请选择" style="width:50%" v-model="formCustom.birthday" format="yyyy年MM月dd日" :disabled='editorOpen'></DatePicker>
                     </FormItem>
                  </div>
              </Col>
            </Row>
            <div class='middleName'><span @click='onMiddleNameOpenChange()'>有中间名？</span></div>

            <!-- 第三行 -->
            <Row>
              <Col span="12">
                    <div class="form-group">
                        <label for="">性别</label>
                         <FormItem prop="gender">
                            <RadioGroup v-model="formCustom.gender">
                                <Radio label="MALE" :disabled='editorOpen'><span>男</span></Radio>
                                <Radio label="FEMALE" :disabled='editorOpen'><span>女</span></Radio>
                            </RadioGroup>
                         </FormItem>
                    </div>
              </Col>
            </Row>
          <p class='line' style='margin-top: 15px'></p>
          </Form>
           <!-- 第四行 form循环-->
           <Form ref="formDynamic" :model="formCustom" :label-width="0">
                <div v-for='(item,index) in formCustom.credentials' :key='index'>
                    <h6>
                      <Icon type="ios-remove-circle-outline" color="#ff484a" v-if='index!==0' @click='onRemoveTravel(index)'/>
                      ID: {{index+1}}
                    </h6>
                    <Row>
                        <Col span="12">
                          <div class="form-group">
                              <label for="">旅行证件</label>
                              <FormItem
                                  :prop="'credentials.' + index + '.category'"
                                  :rules="{required: true, message: emptyNews ,trigger: 'change'}">
                                  <Select v-model="item.category" style="width:100%" class='rorderRight' @on-open-change='selectedCategoryOpen($event,index)'>
                                    <Option v-for="item in identityOption[index]" :value="item.value" :key="item.value">{{ item.label }}</Option>
                                  </Select>
                              </FormItem>
                              <FormItem
                                 :prop="'credentials.' + index + '.number'"
                                :rules="{required: true,validator:identificationNumberType(item.category)}"
                                 >
                                <Input v-model="item.number" placeholder="证件号码" style="width: 100%" class='inputRight'/>
                              </FormItem>
                          </div>
                        </Col>
                        <template v-if='filterType(item.category)'>
                          <Col span="5">
                                  <div class="form-group">
                                      <label for="">签发地</label>
                                      <FormItem
                                          :prop="'credentials.' + index + '.issueAt'"
                                          :rules="{required: true, message: emptyNews}" >
                                          <Select v-model="item.issueAt" style="width:100%">
                                            <Option v-for="item in issueAtList" :value="item.value" :key="item.value">{{ item.label }}</Option>
                                          </Select>
                                      </FormItem>
                                  </div>
                            </Col>
                            <Col span="6">
                                  <div class="form-group">
                                      <label for="">有效期</label>
                                       <!-- :rules="{required: true, message: emptyNews}" -->
                                      <FormItem
                                          :prop="'credentials.' + index + '.expiredAt'"
                                          :rules="{required: true, message: emptyNews}"
                                          >
                                        <DatePicker type="date" placeholder="请选择" style="width:100%" v-model="item.expiredAt" format="yyyy年MM月dd日" ></DatePicker>
                                      </FormItem>
                                  </div>
                            </Col>
                        </template>
                    </Row>
                    <p class='line marginBottom'></p>
                </div>
                <div class='addCertificates' v-if='formCustom.credentials.length<6' @click='addCertificates()'>
                    <Icon type="ios-add-circle-outline" />添加证件
                </div>
                <div class='submitStyle'>
                    <Button type="primary" style='width:80px' @click='onSubmit()'>保存</Button>
                    <Button style="margin-left: 8px;width:80px" @click='onCancel()'>取消</Button>
                </div>
          </Form>
      </section>
    </div>
     <Modal v-model="modal1" width="360">
        <div style="text-align:center">
            <span>您确定要删除吗?</span>
        </div>
        <div slot="footer">
            <Button  size="large" long style='width:calc(50% - 10px)' @click='modal1=false'>取消</Button>
            <Button type="error" size="large" long style='width:calc(50% - 10px)' @click='confirmDeletion()'>删除</Button>
        </div>
    </Modal>
  </main>
</template>

<script>
import {user} from '../../../../assets/json/user.js'
import Util from '@/lib/utils'
export default {
  data () {
    const empty = '必填项'
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
    // 英文名
    const _familyName = (rule, value, callback) => {
      if (value === '') {
        callback(new Error(empty))
      } else {
        let reg = /^[_a-zA-Z]+$/
        if (!reg.test(value)) {
          callback(new Error('请输入英文或拼音'))
        }
        callback()
      }
    }
    // 非空
    const _onEmpty = (rule, value, callback) => {
      if (value === '') {
        callback(new Error(empty))
      } else {
        callback()
      }
    }
    // 身份证验证
    const idNumber = (rule, value, callback) => {
      var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/
      if (value === '') {
        callback(new Error(empty))
      } else if (!reg.test(value)) {
        callback(new Error("必须为18位数字或17位数字与'X'的组合"))
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
    // 台胞证
    const _taiwan_compatriots = (rule, value, callback) => {
      var reg = /^\d{8}|^[a-zA-Z0-9]{10}|^\d{18}$/
      if (value === '') {
        callback(new Error(empty))
      } else if (!reg.test(value)) {
        callback(new Error('您填写的证件号可能有误，请仔细核对'))
      } else {
        callback()
      }
    }
    return {
      open: false,
      editorOpen: false,
      emptyNews: '必填项',
      middleNameOpen: false, // 中间名
      modal1: false, // 删除
      // 证件 验证
      id_number: idNumber,
      onEmpty: _onEmpty,
      passport: _passport,
      taiwan_compatriots: _taiwan_compatriots,
      //
      formCustom: {
        guid: 'bac2bfcb-1e24-41c1-baad-8a6e75b64af5',
        familyNameZh: '刘',
        givenNameZh: '备',
        familyName: 'DEI',
        givenName: 'BU',
        middleName: '',
        nationality: '',
        gender: 'MALE',
        birthday: '1977-01-27',
        primary: false,
        // familyNameZh: '',
        // givenNameZh: '',
        // familyName: '',
        // givenName: '',
        // middleName: '',
        // nationality: '',
        // gender: '',
        // birthday: '',
        // primary: false,
        credentials: [
          {
            'guid': '54662ca3-3904-4412-ba86-d5d3d291b5e1',
            'category': '',
            // 'category': 'NI',
            // 'number': '130706199201270313',
            'number': '',
            'issueAt': null,
            'expiredAt': '',
            'issueDate': ''
          }
          // {
          //   'guid': 'ce193f2b-f094-4713-b4a3-cd1d10216a60',
          //   'category': 'GA',
          //   'number': '123',
          //   'issueAt': 'HK',
          //   'expiredAt': '2022-05-04',
          //   'issueDate': '2016-01-01'
          // },
          // {
          //   'guid': '9a077e42-3127-4c13-b227-39b52bcf4771',
          //   'category': 'TW',
          //   'number': '123213',
          //   'issueAt': 'HK',
          //   'expiredAt': '2022-04-04',
          //   'issueDate': '2016-01-01'
          // },
          // {
          //   'guid': 'f1430854-296a-4988-bac0-9449977d4f44',
          //   'category': 'HX',
          //   'number': '123',
          //   'issueAt': 'CN',
          //   'expiredAt': '2020-01-02',
          //   'issueDate': '2016-01-01'
          // },
          // {
          //   'guid': '417ce847-1535-438b-9952-2ffdd13db35a',
          //   'category': 'TB',
          //   'number': '123QWEQWE',
          //   'issueAt': 'CN',
          //   'expiredAt': '2020-03-01',
          //   'issueDate': '2016-01-01'
          // },
          // {
          //   'guid': 'aa01ccf3-42dc-4ddb-961d-d2b9d750ba79',
          //   'category': 'PP',
          //   'number': 'P1234567',
          //   'issueAt': 'CN',
          //   'expiredAt': '2019-02-02',
          //   'issueDate': '2016-01-01'
          // }
        ]
      },
      data: [
        {
          'guid': 'bac2bfcb-1e24-41c1-baad-8a6e75b64af5',
          'familyNameZh': '张',
          'givenNameZh': '飞',
          'familyName': 'DEI',
          'givenName': 'BU',
          'middleName': '',
          'nationality': 'CN',
          'gender': 'MALE',
          'birthday': '1991-01-27',
          'primary': false,
          'credentials': [
            {
              'guid': '54662ca3-3904-4412-ba86-d5d3d291b5e1',
              'category': 'NI',
              'number': '130706199201270313',
              'issueAt': null,
              'expiredAt': '',
              'issueDate': ''
            },
            {
              'guid': 'ce193f2b-f094-4713-b4a3-cd1d10216a60',
              'category': 'GA',
              'number': '123',
              'issueAt': 'HK',
              'expiredAt': '2022-05-04',
              'issueDate': '2016-01-01'
            },
            {
              'guid': '9a077e42-3127-4c13-b227-39b52bcf4771',
              'category': 'TW',
              'number': '123213',
              'issueAt': 'HK',
              'expiredAt': '2022-04-04',
              'issueDate': '2016-01-01'
            },
            {
              'guid': 'f1430854-296a-4988-bac0-9449977d4f44',
              'category': 'HX',
              'number': '123',
              'issueAt': 'CN',
              'expiredAt': '2020-01-02',
              'issueDate': '2016-01-01'
            },
            {
              'guid': '417ce847-1535-438b-9952-2ffdd13db35a',
              'category': 'TB',
              'number': '123QWEQWE',
              'issueAt': 'CN',
              'expiredAt': '2020-03-01',
              'issueDate': '2016-01-01'
            },
            {
              'guid': 'aa01ccf3-42dc-4ddb-961d-d2b9d750ba79',
              'category': 'PP',
              'number': 'P1234567',
              'issueAt': 'CN',
              'expiredAt': '2019-02-02',
              'issueDate': '2016-01-01'
            }
          ]
        },
        {
          'guid': '83644c96-07f6-45ca-9bdb-280bad3e335e',
          'familyNameZh': '大',
          'givenNameZh': '胖胖',
          'familyName': 'L',
          'givenName': 'R',
          'middleName': '',
          'nationality': 'CN',
          'gender': 'FEMALE',
          'birthday': '1904-01-01',
          'primary': false,
          'credentials': [
            {
              'guid': 'e59059fa-7e51-4fa7-8562-965c938fc8ef',
              'category': 'NI',
              'number': '130706199201270313',
              'issueAt': null,
              'expiredAt': '',
              'issueDate': ''
            }
          ]
        }
      ],
      // 旅行证件
      identity: user.identity,
      identityOption: [],
      // 签发地
      issueAtList: [
        {
          label: '中国香港',
          value: 'HK'
        },
        {
          label: '中国',
          value: 'CN'
        }
      ],
      ruleCustom: {
        //  'guid': 'bac2bfcb-1e24-41c1-baad-8a6e75b64af5',
        //   'familyNameZh': '刘',
        //   'givenNameZh': '鹏飞',
        //   'familyName': 'DEI',
        //   'givenName': 'BU',
        //   'middleName': '',
        //   'nationality': 'CN',
        //   'gender': 'MALE',
        //   'birthday': '1995-01-27',
        //   'primary': false,
        familyNameZh: [{ validator: _familyNameZh, trigger: 'blur' }],
        givenNameZh: [{ validator: _familyNameZh, trigger: 'blur' }],
        // 国籍
        nationality: [{ validator: _onEmpty, trigger: 'change' }],
        // 英文名
        familyName: [{ validator: _familyName, trigger: 'blur' }],
        givenName: [{ validator: _familyName, trigger: 'blur' }],
        // 中间名
        middleName: [{ validator: _familyName, trigger: 'blur' }],
        // 出生日期
        birthday: [{ validator: _onEmpty, type: 'date' }],
        // 男女
        gender: [{ validator: _onEmpty, trigger: 'blur' }]
        // middleName: [{ validator: validateAge, trigger: 'blur' }],

      },
      nationalityList: [
        {
          label: '中国',
          value: 'CN'
        },
        {
          label: '澳大利亚',
          value: 'CK'
        }
      ],
      deletionKeep: ''
    }
  },
  components: {},
  computed: {},
  watch: {},
  mounted () {
  },
  methods: {
    // 证件号码的校验
    identificationNumberType (type) {
      if (type === 'NI') { // 身份证
        return this.id_number
      } else if (type === 'PP') { // 护照
        return this.passport
      } else if (type === 'TB') { // 台胞证
        return this.taiwan_compatriots
      } else {
        return this.onEmpty
      }
    },
    // 提交
    onSubmit () {
      let a = false
      let b = false
      this.$refs['formInlineRef1'].validate(valid => {
        if (valid) {
          a = true
        }
      })
      this.$refs['formDynamic'].validate(valid => {
        if (valid) {
          b = true
        }
      })
      if (a && b) {
        this.formCustom.guid = Math.floor(Math.random() * 1000)
        this.formCustom.birthday = Util.timeStampLite(this.formCustom.birthday.getTime(), null)
        this.formCustom.credentials.forEach(item => {
          if (item.expiredAt) {
            item.expiredAt = Util.timeStampLite(item.expiredAt.getTime(), null)
          }
        })
        this.data.push(this.formCustom)
        this.onCancel()
      }
    },
    // 取消
    onCancel () {
      this.open = false
      this.editorOpen = false
      this.middleNameOpen = false
    },
    onIdentityFilter (data) {
      let n = this.identity.find(item => {
        return item.value === data
      })
      return n.label
    },
    // 身份证和港澳台居民居住证只能输入号
    filterType (value) {
      let n = this.identity.find((item) => {
        return item.value === value
      })
      if (n) {
        return n.open
      } else {
        return true
      }
    },
    // 删除常用旅客
    onRemovePassenger (i) {
      this.deletionKeep = i
      this.modal1 = true
    },
    confirmDeletion () {
      let i = this.deletionKeep
      if (i >= 0) {
        this.data.splice(i, 1)
      } else {
        this.data.forEach((item, index) => {
          if (item.guid === this.formCustom.guid) {
            this.data.splice(index, 1)
          }
        })
      }
      this.modal1 = false
      this.onCancel()
    },
    // 删除旅行证件
    onRemoveTravel (index) {
      this.formCustom.credentials.splice(index, 1)
    },
    // 添加证件
    addCertificates () {
      let arr = []
      this.formCustom.credentials.forEach(item => {
        arr.push(item.category)
      })
      let list = []
      this.identity.forEach((item) => {
        if (arr.indexOf(item.value) === -1) {
          list.push(item)
        }
      })
      this.identityOption.push(list)
      this.formCustom.credentials.push({
        'category': list[0].value,
        'number': '',
        'issueAt': null,
        'expiredAt': '',
        'issueDate': ''
      })
    },
    // 新增旅客
    addPassenger () {
      this.open = true
      this.formCustom = {
        familyNameZh: '',
        givenNameZh: '',
        familyName: '',
        givenName: '',
        middleName: '',
        nationality: '',
        gender: 'MALE',
        primary: false,
        credentials: [
          {
            'category': 'PP',
            'number': '',
            'issueAt': null,
            'expiredAt': '',
            'issueDate': ''
          }
        ]
      }
      this.identityOption = [this.identity]
    },
    // 旅行证件 点开
    selectedCategoryOpen (v, i) {
      if (!v) return false
      let arr = []
      this.formCustom.credentials.forEach((item, index) => {
        if (index !== i) {
          arr.push(item.category)
        }
      })
      let list = []
      this.identity.forEach((item) => {
        if (arr.indexOf(item.value) === -1) {
          list.push(item)
        }
      })
      this.identityOption[i] = list
      this.$set(this.identityOption, i, list)
    },
    // 编辑
    onEditor (option) {
      this.editorOpen = true
      this.formCustom = option
      this.identityOption = []
      option.credentials.forEach(() => {
        this.identityOption.push(this.identity)
      })
      // 中间名是否显示
      if (option.middleName) {
        this.middleNameOpen = true
      }
      this.open = true
    },
    // 中间名
    onMiddleNameOpenChange () {
      this.middleNameOpen = !this.middleNameOpen
      if (!this.middleNameOpen) {
        this.formCustom.middleName = ''
      }
    }
  }
}
</script>

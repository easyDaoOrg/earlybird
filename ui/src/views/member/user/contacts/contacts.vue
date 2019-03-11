
<style lang='scss' scoped>
@import "./contacts.scss";
</style>
<style>
.contacts .ivu-input{
    height: 40px;
    line-height: 40px;
    font-size: 14px;
    padding-left: 13px;
    border-radius:3px;
}
.inputLeft .ivu-input{
  border-radius: 3px 0 0 3px!important;
}
.borderRightNone .ivu-input{
   border-right: 0;
}
.inputRight .ivu-input{
  border-radius:  0 3px 3px!important;
}
.contacts .ivu-form-item{
  width:100%;
}
</style>

<template>
<main class="contacts">

    <h2>常用联系人</h2>
    <p class='contacts-shops'>
      <span>总计</span>
      <b>{{contactsList.length}}</b>
    </p>
    <section v-if='open'>
      <div class="card" v-for='(item,index) in contactsList' :key='index'>
        <h6>
          <strong>{{item.lastName}}{{item.firstName}}</strong>
          <span>
             <Icon type="ios-create-outline" @click='onUpdate(item)' />
          </span>
        </h6>
        <ul>
            <li>
              <label for="">手机</label>
              <span>{{item.mobile}}</span>
            </li>
            <li>
              <label for="">邮箱</label>
              <span>{{item.email}}</span>
            </li>
        </ul>
      </div>
      <div class="card-add" @click='onAdd()'>
        <Icon type="md-add-circle" />
        <span>新增联系人</span>
      </div>
    </section>
    <section v-if='!open' class='form'>
      <header>
        <h5>{{emitorOpen?'编辑':'新增'}}</h5>
        <div>
          <p>
            <span @click='onOk()'>保存</span>
            <span @click='onCancel()'>取消</span>
          </p>
          <b v-if='emitorOpen' @click='onRemove()'><Icon type="ios-close-circle-outline"/>删除</b>
        </div>
      </header>

      <Form ref="formInlineRef" :model="comeObj" :rules="ruleCustom" :label-width="0">
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
                              <Input v-model="comeObj.email" placeholder="邮箱" style="width: 100%;" class='inputLeft'/>
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
                             <Input v-model="comeObj.mobile" placeholder="手机" style="width: 100%" class='inputRight'/>
                        </FormItem>
                    </div>
              </Col>
        </Row>

      </Form>

    </section>

</main>
</template>
<script>
import {user} from '@/assets/json/user'
import Util from '@/lib/utils.js'

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
      user_id:Util.getCookie('userId'),
      contactsList:[],
      // contactsList: [
      //   {
      //     'guid': 'aadf7539-8d1b-46f1-bdca-4f5ec1f4a560',
      //     'firstName': '羽',
      //     'lastName': '关',
      //     'middleName': '',
      //     'email': '135723191@qq.com',
      //     'mobile': '19900012784',
      //     'areaCode': '86',
      //     'gender': '',
      //     'birthday': null,
      //     'address': {
      //       'street1': '',
      //       'street2': '',
      //       'houseNumber': '',
      //       'city': '',
      //       'country': '中国大陆,China',
      //       'zipCode': '',
      //       'province': ''
      //     },
      //     'phoneNumber': null,
      //     'phoneNumberCountryCode': null,
      //     'primary': false
      //   },
      //   {
      //     'guid': 'e0fb9511-9aa1-433b-ba7c-b89c4de2c4db',
      //     'firstName': '操',
      //     'lastName': '曹',
      //     'middleName': '',
      //     'email': '1345003191@qq.com',
      //     'mobile': '19910012784',
      //     'areaCode': '86',
      //     'gender': '',
      //     'birthday': null,
      //     'address': {
      //       'street1': '',
      //       'street2': '',
      //       'houseNumber': '',
      //       'city': '',
      //       'country': '中国大陆,China',
      //       'zipCode': '',
      //       'province': ''
      //     },
      //     'phoneNumber': null,
      //     'phoneNumberCountryCode': null,
      //     'primary': false
      //   }
      // ],
      open: true,
      emitorOpen: false,
      phoneList: user.phoneList,
      comeObj: {
        lastName: '',
        firstName: '',
        email: '',
        areaCode: '86',
        mobile: '',
        id:null,
      },
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
        lastName: [{ validator: _familyNameZh, trigger: 'blur' }],
        firstName: [{ validator: _familyNameZh, trigger: 'blur' }],
        email: [{ validator: _email, trigger: 'blur' }],
        mobile: [{ validator: _phone, trigger: 'blur' }]
        // // 国籍
        // nationality: [{ validator: _onEmpty, trigger: 'change' }],
        // // 英文名
        // familyName: [{ validator: _familyName, trigger: 'blur' }],
        // givenName: [{ validator: _familyName, trigger: 'blur' }],
        // // 中间名
        // middleName: [{ validator: _familyName, trigger: 'blur' }],
        // // 出生日期
        // birthday: [{ validator: _onEmpty, type: 'date' }],
        // // 男女
        // gender: [{ validator: _onEmpty, trigger: 'blur' }]
        // middleName: [{ validator: validateAge, trigger: 'blur' }],

      }
    }
  },
  components: {

  },
  watch: {

  },
  methods: {
    onPhone (data) {
      this.comeObj.areaCode = data
    },
    onOk () {
      if(!this.emitorOpen){
          this.$refs['formInlineRef'].validate(valid => {
          if (valid) {
            let url = this.loginUrl + `/contact/addCcontact`
            this.axios
              .post(url,{
                user_id:this.user_id,
                contact_name:this.comeObj.lastName+this.comeObj.firstName,
                contact_email:this.comeObj.email,
                contact_phone:this.comeObj.mobile
              })
              .then(data => {
                this.getData()
                this.onCancel()
              })
              .catch(error => {
                console.log(error)
              })
          
          }
        })
      }else{
         this.$refs['formInlineRef'].validate(valid => {
          if (valid) {
            let url = this.loginUrl + `/contact/updateContact`
            this.axios
              .post(url,{
                id:this.comeObj.id,
                contact_name:this.comeObj.lastName+this.comeObj.firstName,
                contact_email:this.comeObj.email,
                contact_phone:this.comeObj.mobile
              })
              .then(data => {
                this.getData()
                this.onCancel()
              })
              .catch(error => {
                console.log(error)
              })
          }
        })
      }
    },
    onCancel () {
      this.comeObj = {
        lastName: '',
        firstName: '',
        email: '',
        areaCode: '86',
        mobile: '',
        id:null
      }
      this.emitorOpen = false
      this.open = true
    },
    // 编辑
    onUpdate (data) {
      this.comeObj = {
        lastName: data.lastName,
        firstName: data.firstName,
        email: data.email,
        areaCode: data.areaCode,
        mobile: data.mobile,
        id:data.id
      }
      this.emitorOpen = true
      this.open = false
    },
    // 添加
    onAdd () {
      this.emitorOpen = false
      this.open = false
    },
    onRemove () {
      let url = this.loginUrl + `/contact/deleteContact/${this.comeObj.id}`
      this.axios
        .delete(url,{
　　         id:this.comeObj.id,
            contact_name:this.comeObj.lastName+this.comeObj.firstName,
            contact_email:this.comeObj.email,
            contact_phone:this.comeObj.mobile
        } )
        .then(data => {
          this.getData()
          this.onCancel()
        })
        .catch(error => {
          console.log(error)
        })
      // let idx = -1
      // for (let i = 0; i < this.contactsList.length; i++) {
      //   if (this.contactsList[i].email === this.comeObj.email) {
      //     idx = i
      //     break
      //   }
      // }
      // if (idx !== -1) {
      //   this.contactsList.splice(idx, 1)
      //   this.onCancel()
      // }
    },
    getData(){
      let url = this.loginUrl + `/contact/getContactList`
      this.axios
        .get(url,{
　　         params:{user_id:this.user_id}
        } )
        .then(data => {
          let list=data.data.contactList
          this.contactsList=[]
          if(list.length){
            list.forEach(item=>{
                let lastName=item.contact_name.slice(0,1)
                let firstName=item.contact_name.slice(1)
                this.contactsList.push({
                    firstName: firstName,
                    lastName: lastName,
                    email: item.contact_email,
                    mobile: item.contact_phone,
                    areaCode: '86',
                    id:item.id
                })
            })
          }
        })
        .catch(error => {
          console.log(error)
        })
    }
  },
  mounted () {
    this.getData()
  }
}
</script>

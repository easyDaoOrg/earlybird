
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
        <h5>新增</h5>
        <div>
          <p>
            <span @click='onOk()'>保存</span>
            <span @click='onCancel()'>取消</span>
          </p>
          <b v-if='removeIoin' @click='onRemove()'><Icon type="ios-close-circle-outline"/>删除</b>
        </div>
      </header>
      <div class="form-group">
          <label for="">名字</label>
          <Input v-model="comeObj.lastName" placeholder="姓（中文/拼音）" style="width: 50%;" class='inputLeft borderRightNone'/>
          <Input v-model="comeObj.firstName" placeholder="名（中文/拼音）" style="width: 50%" class='inputRight'/>
      </div>
       <div class="form-group paddingRight">
          <label for="">邮箱</label>
          <Input v-model="comeObj.email" placeholder="邮箱" style="width: 100%;" class='inputLeft'/>
      </div>

       <div class="form-group">
          <label for="">手机</label>
          <Dropdown style='width:50%' @on-click='onPhone'>
              <div class='phone'>
                  {{comeObj.areaCode || '请选择'}}
                  <Icon type="ios-arrow-down"></Icon>
              </div>
              <DropdownMenu slot="list">
                  <DropdownItem v-for='(item,index) in phoneList' :key='index' :name='item.value'>{{item.label}}</DropdownItem>
              </DropdownMenu>
          </Dropdown>
          <!-- <Input v-model="value" placeholder="姓（中文/拼音）" style="width: 50%;" class='inputLeft borderRightNone'/> -->
          <Input v-model="comeObj.mobile" placeholder="手机" style="width: 50%" class='inputRight'/>
      </div>
    </section>
</main>
</template>

<script>

export default {
  data () {
    return {
      contactsList: [
        {
          'guid': 'aadf7539-8d1b-46f1-bdca-4f5ec1f4a560',
          'firstName': '羽',
          'lastName': '关',
          'middleName': '',
          'email': '135723191@qq.com',
          'mobile': '19900012784',
          'areaCode': '86',
          'gender': '',
          'birthday': null,
          'address': {
            'street1': '',
            'street2': '',
            'houseNumber': '',
            'city': '',
            'country': '中国大陆,China',
            'zipCode': '',
            'province': ''
          },
          'phoneNumber': null,
          'phoneNumberCountryCode': null,
          'primary': false
        },
        {
          'guid': 'e0fb9511-9aa1-433b-ba7c-b89c4de2c4db',
          'firstName': '操',
          'lastName': '曹',
          'middleName': '',
          'email': '1345003191@qq.com',
          'mobile': '19910012784',
          'areaCode': '86',
          'gender': '',
          'birthday': null,
          'address': {
            'street1': '',
            'street2': '',
            'houseNumber': '',
            'city': '',
            'country': '中国大陆,China',
            'zipCode': '',
            'province': ''
          },
          'phoneNumber': null,
          'phoneNumberCountryCode': null,
          'primary': false
        }
      ],
      open: true,
      removeIoin: false,
      phoneList: [
        {
          label: '中国大陆 (+86)',
          value: '中国大陆 (+86)'
        },
        {
          label: '中国香港 (+852)',
          value: '中国香港 (+852)'
        },
        {
          label: '中国澳门 (+853)',
          value: '中国澳门 (+853)'
        }
      ],
      comeObj: {
        lastName: '',
        firstName: '',
        email: '',
        areaCode: '中国大陆 (+86)',
        mobile: ''
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
      this.contactsList.push(this.comeObj)
      this.onCancel()
    },
    onCancel () {
      this.comeObj = {
        lastName: '',
        firstName: '',
        email: '',
        areaCode: '中国大陆 (+86)',
        mobile: ''
      }
      this.removeIoin = false
      this.open = true
    },
    // 编辑
    onUpdate (data) {
      this.comeObj = {
        lastName: data.lastName,
        firstName: data.firstName,
        email: data.email,
        areaCode: data.areaCode,
        mobile: data.mobile
      }
      this.removeIoin = true
      this.open = false
    },
    // 添加
    onAdd () {
      this.open = false
    },
    onRemove () {
      let idx = -1
      for (let i = 0; i < this.contactsList.length; i++) {
        if (this.contactsList[i].email === this.comeObj.email) {
          idx = i
          break
        }
      }
      if (idx !== -1) {
        this.contactsList.splice(idx, 1)
        this.onCancel()
      }
    }
  },
  mounted () {

  }
}
</script>

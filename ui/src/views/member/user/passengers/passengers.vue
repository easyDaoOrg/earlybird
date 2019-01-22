
<style lang='scss' scoped>
@import "./passengers.scss";
</style>
<style>
.passengers .ivu-input{
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
<main class="passengers">
    <h2>常用联系人</h2>
    <p class='passengers-shops'>
      <span>总计</span>
      <b>{{data.length}}</b>
    </p>
    <div v-if='!open'>
        <section class='card' v-for='(item,index) in data' :key='index'>
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
            <div class="card-edit card-delete">
              <Icon type="ios-close-circle-outline" color='#ff484a'/>
              <span>删除</span>
            </div>
            <div class="card-btn-line">|</div>
            <div class="card-edit">
              <Icon type="ios-create-outline" color='#3291eb'/>
              <span>编辑</span>
            </div>
          </h6>
          <ul>
            <li v-for='(option,i) in item["credentials"]' :key='i'>
              <label for="">{{onIdentityFilter(option.category)}}</label>
              <span>{{option.number}}</span>
            </li>
          </ul>
        </section>
        <div class='new-card-add'>
          <Icon type="ios-add-circle-outline" />
          <span>新增旅客</span>
        </div>
    </div>
    <div>

    </div>
</main>
</template>

<script>

export default {
  data () {
    return {
      data: [
        {
          'guid': 'bac2bfcb-1e24-41c1-baad-8a6e75b64af5',
          'familyNameZh': '刘',
          'givenNameZh': '备',
          'familyName': 'DEI',
          'givenName': 'BU',
          'middleName': '',
          'nationality': 'CN',
          'gender': 'MALE',
          'birthday': '1977-01-27',
          'primary': false,
          'credentials': [
            {
              'guid': '54662ca3-3904-4412-ba86-d5d3d291b5e1',
              'category': 'ID',
              'number': '135206197701270313',
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
            }
          ]
        },
        {
          'guid': '83644c96-07f6-45ca-9bdb-280bad3e335e',
          'familyNameZh': '大',
          'givenNameZh': '桥',
          'familyName': 'L',
          'givenName': 'R',
          'middleName': '',
          'nationality': 'CN',
          'gender': 'FEMALE',
          'birthday': '1881-01-01',
          'primary': false,
          'credentials': [
            {
              'guid': 'e59059fa-7e51-4fa7-8562-965c938fc8ef',
              'category': 'ID',
              'number': '135206197701270313',
              'issueAt': null,
              'expiredAt': '',
              'issueDate': ''
            }
          ]
        }
      ],
      identity: [
        {
          label: '身份证',
          value: 'ID'
        },
        {
          label: '港澳通行证',
          value: 'GA'
        },
        {
          label: '台湾通行证',
          value: 'TW'
        }
      ],
      open: false
      // removeIoin: false,
      // phoneList: [
      //   {
      //     label: '中国大陆 (+86)',
      //     value: '中国大陆 (+86)'
      //   },
      //   {
      //     label: '中国香港 (+852)',
      //     value: '中国香港 (+852)'
      //   },
      //   {
      //     label: '中国澳门 (+853)',
      //     value: '中国澳门 (+853)'
      //   }
      // ],
      // comeObj: {
      //   lastName: '',
      //   firstName: '',
      //   email: '',
      //   areaCode: '中国大陆 (+86)',
      //   mobile: ''
      // }
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
      this.data.push(this.comeObj)
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
    onIdentityFilter (data) {
      let n = this.identity.find(item => {
        return item.value === data
      })
      return n.label
    }
  },
  mounted () {

  }
}
</script>

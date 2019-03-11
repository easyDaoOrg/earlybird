
<style lang='scss' scoped>
@import "./ontacts.scss";
</style>
<template>
  <div class="booking-contacts">
    <span class="fr">
      <Button @click='clear()'>清除</Button>
    </span>
    常用联系人：
    <Dropdown placement="bottom-start" trigger="custom" :visible="visible" @click.native='$event.stopPropagation();'>
        <Button type="primary" @click.stop='visible=true;'>
            选择
            <Icon type="ios-arrow-down"></Icon>
        </Button>
        <DropdownMenu slot="list">
          <div class="booking-contacts-title">请选择联系人</div>
          <div class="booking-contacts-list">
            <section class="common-passenger-item" v-for='(item,index) in peopleList' :key='index' @click='add(item)'>
              <b class="passenger-text">{{item.firstName}}{{item.lastName}}</b>
            </section>
          </div>
        </DropdownMenu>
    </Dropdown>
  </div>
</template>

<script>
import Util from '@/lib/utils.js'
export default {
  props: {
  },
  data () {
    return {
      user_id:Util.getCookie('userId'),
      visible: false,
      peopleList:[]
      // peopleList: [
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
      // ]
    }
  },
  components: {

  },
  watch: {

  },
  methods: {
    clear () {
      this.$emit('on-clear-ontacts', true)
    },
    add (data, index) {
      this.$emit('on-add-ontacts', data)
      this.visible = false
    },
    closeDropdownMenu () {
      this.visible = false
    },
    getData(){
      let url = this.loginUrl + `/contact/getContactList`
      this.axios
        .get(url,{
　　         params:{user_id:this.user_id}
        } )
        .then(data => {
          let list=data.data.contactList
          this.peopleList=[]
          //  'guid': 'aadf7539-8d1b-46f1-bdca-4f5ec1f4a560',
          // 'firstName': '羽',
          // 'lastName': '关',
          // 'middleName': '',
          // 'email': '135723191@qq.com',
          // 'mobile': '19900012784',
          // 'areaCode': '86',
          // 'gender': '',
          // 'birthday': null,
          if(list.length){
            list.forEach(item=>{
                let lastName=item.contact_name.slice(0,1)
                let firstName=item.contact_name.slice(1)
                this.peopleList.push({
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
    this.$bus.on('overall-close', this.closeDropdownMenu)
    this.getData()
  }
}
</script>

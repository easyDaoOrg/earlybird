
<style lang='scss' scoped>
@import "./bookingContacts.scss";
</style>
<template>
  <div class="booking-contacts">
    <span class="fr">
      <Button @click='clear()'>清除</Button>
      <Button icon="ios-close-circle" @click='remove()' :disabled='removeDisabled'>删除</Button>
    </span>
    常用联系人：
    <Dropdown placement="bottom-start" trigger="custom" :visible="visible" @click.native='$event.stopPropagation();'>
        <Button type="primary" @click.stop='visible=true;'>
            选择
            <Icon type="ios-arrow-down"></Icon>
        </Button>
        <DropdownMenu slot="list">
          <div class="booking-contacts-title">请选择旅客</div>
          <div class="booking-contacts-list">
            <section class="common-passenger-item" v-for='(item,index) in peopleList' :key='index' @click='!setDisabled(index)&&add(item,index)' :class='{"disabled":setDisabled(index)}'>
              <b class="passenger-text">{{item.familyNameZh}}{{item.givenNameZh}}</b>
              <span>(成人)</span>
            </section>
          </div>
        </DropdownMenu>
      </Dropdown>
  </div>
</template>

<script>

export default {
  props: {
    removeDisabled: {
      type: Boolean,
      default: false
    },
    current: {
      type: Array,
      default: function () {
        return []
      }
    }
  },
  data () {
    return {
      visible: false,
      peopleList: [
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
        },
        {
          'guid': '83644c96-07f6-45ca-9bdb-280bad3e335e',
          'familyNameZh': '小',
          'givenNameZh': '朋友',
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
      ]
    }
  },
  components: {

  },
  watch: {

  },
  methods: {
    remove () {
      this.$Modal.confirm({
        title: '删除',
        content: '<p>您确定要删除吗？</p>',
        onOk: () => {
          this.$emit('on-remove', true)
        }
      })
    },
    clear () {
      this.$emit('on-clear', true)
    },
    add (data, index) {
      this.$emit('on-add-member', [data, index])
      this.visible = false
    },
    // 设置禁用
    setDisabled (index) {
      let m = false
      this.current.forEach(item => {
        if (item.memberIndex === index) {
          m = true
        }
      })
      return m
    },
    closeDropdownMenu () {
      this.visible = false
    }
  },
  mounted () {
    this.$bus.on('overall-close', this.closeDropdownMenu)
  }
}
</script>

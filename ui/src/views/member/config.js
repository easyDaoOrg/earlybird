import Common from '@/lib/comm.js'

export default {
  mixins: [Common],
  data () {
    return {
      dateTime: 60,
      tabIndex: 0,
      tabName: 'formInline',
      passwordType: 0
    }
  },
  computed: {},
  watch: {},
  mounted () {

  },
  methods: {
    // 切换
    handleTabIndex (index) {
      this.tabIndex = index
      this.tabName = this.tabIndex === 0 ? 'formInline' : 'formSola'
    },

    // 获取动态码
    getPasswordSign (obj, type) {
      // 开始倒计时
      this.timeDown()
      let url = this.loginUrl + `/dynamic/addDynamic?user_account=` + obj.user + `&flag=phone`
      this.axios
        .get(url)
        .then(data => {
          if (data.data.flag) {
            this.$Message.success('验证码已发送至' + obj.user)
            this.checkChangeForgot(type)
          }
        })
        .catch(error => {
          console.log(error)
        })
    },

    // 是否切换
    checkChangeForgot (type) {
      if (type) {
        this.passwordType = type
      }
    },

    // 验证动态码
    checkPasswordSign (obj, index, type, phoneObj) {
      let url = this.loginUrl + `/dynamic/checkDynamic?user_account=` + obj.user + `&dynamic_code=` + obj.password
      let self = this
      this.axios
        .get(url)
        .then(data => {
          if (data.data.flag && !type && !phoneObj) {
            self.handleCheckSign(obj, index)
          } else if (type && !phoneObj) {
            self.checkChangeForgot(type)
          } else if (phoneObj) {
            self.handleCheckChange({data: phoneObj.data}, phoneObj.url)
          } else {
            this.$Message.error('动态码验证失败')
          }
        })
        .catch(error => {
          console.log(error)
        })
    },

    // 登录前验证
    handleCheckSign (obj, index) {
      let userObj = {}
      switch (index) {
        case 0:
          userObj['user_account'] = obj.user
          userObj['user_passwd'] = obj.password
          userObj['login_flag'] = 'passwd'
          this.loginSubmitSign(userObj)
          break
        case 1:
          userObj['user_account'] = obj.user
          userObj['user_passwd'] = ''
          userObj['login_flag'] = 'dynamic'
          this.loginSubmitSign(userObj)
          break
      }
    },

    // 登录
    loginSubmitSign (userObj) {
      let self = this
      let url = this.loginUrl + `/user/doLogin`
      this.axios
        .post(url, userObj)
        .then(data => {
          if (data.data.code === 200) {
            console.log(data.data)
            this.$Message.success(data.data.message)
            self.setCookie('token', data.data.token, 1)
            self.setCookie('userId', data.data.user.id, 1)
            this.$router.push(`/flights/index`)
          } else {
            this.$Message.error(data.data.message)
          }
        })
        .catch(error => {
          console.log(error)
          this.$Message.error('登录失败')
        })
    },

    // 倒计时
    timeDown () {
      if (this.dateTime === 0) {
        this.dateTime = 60
      } else {
        this.dateTime--
        setTimeout(() => {
          this.timeDown()
        },
        1000)
      }
    },

    // 忘记密码修改密码
    handleCheckChange (obj, user) {
      let url = this.loginUrl + user + obj.data
      let self = this
      this.axios
        .get(url)
        .then(data => {
          if (data.data.flag) {
            self.deleteCookie('token')
            this.$Message.success('重置成功')
            this.$router.push(`/member/account/sign-in`)
          } else {
            this.$Message.error(data.data.msg)
          }
        })
        .catch(error => {
          console.log(error)
        })
    }
  }

}

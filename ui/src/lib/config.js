
export default {
  computed: {},
  watch: {},
  mounted () {

  },
  methods: {
    // 获取动态码
    getPassword (obj) {
      // 开始倒计时
      // this.timeDown()
      let url = this.loginUrl + `/dynamic/addDynamic?user_account=` + obj.user + `&flag=phone`
      this.axios
        .get(url)
        .then(data => {

        })
        .catch(error => {
          console.log(error)
        })
    },

    // 验证动态码
    checkPassword (obj, index) {
      let url = this.loginUrl + `/dynamic/checkDynamic?user_account=` + obj.user + `&dynamic_code=` + obj.password
      let self = this
      this.axios
        .get(url)
        .then(data => {
          if (data.data.flag) {
            self.handleCheck(obj, index)
          }
        })
        .catch(error => {
          console.log(error)
        })
    },

    // 登录前验证
    handleCheck (obj, index) {
      let userObj = {}
      switch (index) {
        case 0:
          userObj['user_account'] = obj.user
          userObj['user_passwd'] = obj.password
          userObj['login_flag'] = 'passwd'
          this.loginSubmit(userObj)
          break
        case 1:
          userObj['user_account'] = obj.user
          userObj['user_passwd'] = ''
          userObj['login_flag'] = 'dynamic'
          this.loginSubmit(userObj)
          break
      }
    },

    // 登录
    loginSubmit (userObj) {
      let url = this.loginUrl + `/user/doLogin`
      this.axios
        .post(url, userObj)
        .then(data => {
          if (data.data.code === '200') {
            this.$router.push(`/flights/index`)
          }
        })
        .catch(error => {
          console.log(error)
        })
    }
  }

}

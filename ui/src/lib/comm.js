
export default {
  data () {
    return {
    }
  },
  computed: {},
  watch: {},
  mounted () {

  },
  methods: {
    // 设置cookies
    setCookie (name, value, days) {
      let expires = ''
      if (days) {
        let date = new Date()
        date.setTime(date.getTime() + (2 * 60 * 60 * 1000))
        // date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000))
        expires = `expires=${date.toGMTString()};`
      } else {
        expires = ''
      }
      document.cookie = `${name}=${value};expires=${expires};path=/`
    },

    // 删除cookie
    deleteCookie (name) {
      this.setCookie(name, '', -1)
    }
  }
}

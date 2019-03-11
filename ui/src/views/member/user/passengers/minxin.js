import Util from '@/lib/utils.js'
export default {
  data() {
    return {
      promiseGetList: [],
      user_id:Util.getCookie('userId'),
    }
  },
  mounted() {
  },
  methods: {
    getChildreninputData(id,resolve){
        let url = this.loginUrl + `/passenger/getPassenger`
        this.axios
          .get(url,{
  　　         params:{id:id}
          } )
          .then(data => {
            resolve(data.data)
          }).catch(() => {
             resolve([])
          })
    },
    getData() {
      let url = this.loginUrl + `/passenger/getPassengerList`
      this.axios
        .get(url, {
          params: {
            user_id: this.user_id
          }
        })
        .then(data => {
          let list = data.data.list
          this.peopleList = [];
          this.promiseGetList=[]
          list.forEach(item => {
            this.promiseGetList.push(new Promise((resolve, reject) => {
              this.getChildreninputData(item.id, resolve)
            }));
          })
          Promise.all(this.promiseGetList)
            .then(data => {
              let mac = []
              data.forEach(item => {
                let obj = item.passenger
                let credentials = []
                item.certificateList.forEach(option => {
                  credentials.push({
                    category: option.certificate_type,
                    number: option.certificate_no,
                    issueAt: option.certificate_issue_place, //null
                    expiredAt: option.certificate_valid_date,
                    'issueDate': ''
                  })
                })

                let familyNameZh = obj.passenger_name_cn.slice(0, 1)
                let givenNameZh = obj.passenger_name_cn.slice(1)
                mac.push({
                  id: obj.id,
                  familyNameZh: familyNameZh,
                  givenNameZh: givenNameZh,
                  familyName: obj.passenger_name_en.slice(0, 1),
                  givenName: obj.passenger_name_en.slice(1),
                  middleName: '', // 中间名
                  nationality: obj.passenger_nationality,
                  gender: obj.passenger_gender,
                  birthday: obj.passenger_birthday,
                  primary: false,
                  credentials
                })
              })
              this.peopleList = mac
            })
        })
        .catch(error => {
          console.log(error)
        })
    }
  }
}


<style lang='scss' scoped>
@import "./hotel-booking.scss";
</style>
<style>
.guest-content .ivu-tabs-nav {
  height: 45px;
  line-height: 31px;
}
.guest-content .ivu-tabs-ink-bar {
  width: 30px !important;
  height: 6px;
  position: absolute;
  background-image: linear-gradient(90deg, #28c8dc 0, #3296d2 100%);
  border-radius: 61px;

}
.guest-content .ivu-tabs-nav .ivu-tabs-tab-active {
  color: #00bedc !important;
  font-weight: 600;
}
.guest-content .ivu-tabs-tab {
  color: #464646!important;
  font-size: 16px;
  padding: 0;
  line-height: 12px;
}
.guest-content .ivu-tabs-bar {
  border: none;
  margin-bottom: -1px;
  padding-bottom:10px;
  box-shadow: 0 2px 14px 0 hsla(0,0%,71%,.6);
  bottom: 6px;
}
.edit-item .ivu-input{
    border-radius: 0;
    height: 36px;
    font-size: 16px;
    color: rgb(70, 70, 70);
    border-color: rgb(210, 210, 210);
}
.borderRightNone .ivu-input{
  border-right:none;
}
.booking .ivu-dropdown-menu{
  padding-left: 65px;
}
/* radio */
.booking .ivu-radio-wrapper{
  width: 130px;
  margin:0 0 20px;
  font-size: 14px;
}
.booking .ivu-radio{
  margin-right: 10px;
}
.booking .ivu-radio-checked .ivu-radio-inner{
  border-color:#00bedc;
}
.booking .ivu-radio-inner:after{
  background-color:#00bedc;
}
.booking .ivu-radio-wrapper span:last-child{
    width: 118px;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    display: inline-block;
    position: relative;
    top: 5px;
}
</style>

<template>
<main class='booking'>
  <div>
     <!-- 步揍 -->
      <div class="progress">
          <div class="progress-container">
            <div class="progress-container-item">
              <div class="item-main">
                <b class='selected borderColor'>
                 <Icon type="md-create" />
                </b>
                <p class='selected'>填写订单</p>
              </div>
              <div class="item-line line-checked"></div>
            </div>

            <div class="progress-container-item">
              <div class="item-main">
                <b>¥</b>
                <p>支付订单</p>
              </div>
              <div class="item-line line-checked"></div>
            </div>

            <div class="progress-container-item">
              <div class="item-main">
                <b>
                 <Icon type="md-checkmark" />
                </b>
                <p>确认订单</p>
              </div>
            </div>
          </div>
      </div>
      <!-- top -->
      <div class="booking-login" style='background-image: url("https://content.igola.com/static/WEB/images/hotel/hotel-booking-login-img.png");' v-if='loginOrder'>
        <p class="login-text">登录下单，便捷管理行程，享受更多优惠。<span class="login-btn">登录</span></p>
        <span class='login-off-icon' @click='loginOrder=false'>
          <Icon type="md-close" />
        </span>
      </div>

      <!-- 内容 -->
      <div class="mainBooking">
        <section class="mainBooking-left">
          <div class="info-box info-box-line">
              <div style='float:left'>
                <Dropdown trigger="click" class='childrenDropdown' @on-click='onRoomsChange($event)' :transfer='true'>
                      <div>
                          房间数1
                          <Icon type="md-arrow-dropdown" color='#323232' size='30' class='icon-dropdown'/>
                      </div>
                      <DropdownMenu slot="list" >
                          <DropdownItem v-for='(item,index) in roomsData' :key='index' :name="item">{{item}}</DropdownItem>
                      </DropdownMenu>
                 </Dropdown>
              </div>
              <span style='float:left;padding-left:40px'>入住时间</span>
              <div style='padding-top:27px'>
                <my-date></my-date>
              </div>
          </div>
          <!-- 旅客信息 -->
          <div class='info-box guest'>
            <h2 class="text-title">旅客信息</h2>
            <p class="text-small">
              所填姓名需与入住时所持证件一致
              <br>
              请按实际入住情况填写住客信息，否则入住时可能会被收取额外费用
            </p>

            <div class="guest-content">
                <Tabs :value='tabsSelected'>
                  <TabPane :label="item.label" :name="item.value" v-for='(item,index) in tabsList' :key='index'></TabPane>
                </Tabs>
                <span class="guest-clear">清除全部</span>
            </div>

            <div class='guest-content cardInput'>
              <section class="edit-item">
                <label for="">旅客1<span>（英文/拼音）</span></label>
                <div>
                   <Input v-model="value" :placeholder="input_placeholder" style="width: 50%" class='i-input borderRightNone'/>
                   <Input v-model="value" :placeholder="input_placeholder" style="width: 50%" class='i-input'/>
                </div>
              </section>
              <section class="edit-item" style='margin-left:28px;'>
                <label for="">旅客2<span>（英文/拼音）</span></label>
                <div>
                   <Input v-model="value" :placeholder="input_placeholder" style="width: 50%" class='i-input borderRightNone'/>
                   <Input v-model="value" :placeholder="input_placeholder" style="width: 50%" class='i-input'/>
                </div>
              </section>
            </div>
          </div>
          <!-- 联系人信息 -->
          <div class='info-box guest'>
             <h2 class="text-title">旅客信息</h2>
              <div class='guest-content cardInput'>
                 <section class="edit-item">
                  <label for="">联系人 （中文/拼音)<span>（英文/拼音）</span></label>
                  <div>
                    <Input v-model="value" :placeholder="input_placeholder" style="width: 50%" class='i-input borderRightNone'/>
                    <Input v-model="value" :placeholder="input_placeholder" style="width: 50%" class='i-input'/>
                  </div>
                </section>
                 <section class="edit-item" style='margin-left:28px;'>
                  <label for="">邮箱<span></span></label>
                  <div>
                    <Input v-model="value" placeholder="用于接收确认函" style="width: 100%" class='i-input'/>
                  </div>
                </section>
              </div>

              <div class='guest-content cardInput'>
                  <section class="edit-item">
                    <label for="">手机<span></span></label>
                    <div>
                        <Dropdown style='float:left;width: 50%;' trigger='click'>
                          <div class='mobilePhoneStyle'>
                              下拉菜单
                              <Icon type="ios-arrow-down"></Icon>
                          </div>
                          <DropdownMenu slot="list" >
                              <DropdownItem>中国大陆</DropdownItem>
                              <DropdownItem>中国香港</DropdownItem>
                          </DropdownMenu>
                      </Dropdown>
                      <Input v-model="value" placeholder="用于接受短信通知" style="width: 50%" class='i-input'/>
                    </div>
                  </section>
                  <div class="contact-clear float-right">清除全部</div>
             </div>
          </div>
          <!-- 住客偏好 -->
          <div class='info-box guest' style='border-bottom:none'>
              <h2 class="text-title">住客偏好</h2>
              <p class="text-small">您的要求能否实现取决于各酒店的情况，我们会传达给酒店，但不保证一定可以满足</p>
              <RadioGroup v-model="positionSelected">
                  <Radio :label="item.value" v-for='(item,i) in positionData' :key='i'>
                    <span>{{item.label}}</span>
                  </Radio>
              </RadioGroup>
          </div>
        </section>
        <section class="mainBooking-right">
          <div class="booking-detail">
            <div class="detail-box">
              <div class="height-80">
                <b class="detail-title fl">供应商</b>
                <img src="https://content.igola.com/static/HYBRIDAPP/images/Ctrip_Logo.png" alt="" style='width:60px'>
              </div>
            </div>

            <div class="detail-box">
                 <div class="height-80">
                   <b class="detail-title fl">预订信息</b>
                 </div>
                 <div class="detail-map">
                   <Icon type="md-pin" />
                   <span>广州</span>
                 </div>
                 <section class="hotel-base-info">
                   <h2>广州珠江帝景酒店</h2>
                   <p class='padding-bottom-10'>Regal Riviera Hotel Guangzhou</p>
                   <div class='info-star'>四星/高端型</div>
                   <p class='padding-bottom-10'>1 Haojing Street (Haojing Jie), Yizhou Road (Yizhou Lu), Guangzhou, China</p>
                 </section>
            </div>

            <div class="detail-box room-info">
                <p class='padding-bottom-15 text-normal'>标准双床房</p>
                <p class='padding-bottom-15 text-normal'>双床 | 无早餐</p>
                <p class='padding-bottom-15 text-normal'>
                   <span class="check-in-out">入店</span>
                   <span class="check-date">2019-01-21</span>
                   <span class="check-in-out">离店</span>
                   <span class="check-date">2019-01-22</span>
                   <span class="fr">共1晚</span>
                 </p>
                <p class='padding-bottom-15 text-normal'>共1间 | 每间2成人，0儿童</p>
            </div>

            <div style='padding-bottom: 30px;'>
              <div class="price-detail">
                <p>
                  <span>01月21日</span>
                  <span class='fr'>￥518.0</span>
                </p>
                <p>
                  <span>应付总价(含税)</span>
                  <span class='fr'>￥518.0</span>
                </p>
                <div class="total-price">
                  <b>实付总价</b>
                  <label for="">含税</label>
                  <span class='fr'>￥518.0</span>
                </div>
              </div>
              <div class="btn-pay">支付</div>
              <div class="pay-prompt">我们将在今天确认您的预订并收取费用</div>
            </div>
          </div>
          <div class="booking-policy">
            <p>取消政策</p>
            <span>不可取消</span>
          </div>
        </section>
      </div>
  </div>
</main>
</template>

<script>
// import axios from 'axios/dist/axios.min'
import myDate from '../source-panel/date/date'
import sourcePanel from '../source-panel/source-panel'
export default {
  components: {
    sourcePanel, myDate
  },
  data () {
    return {
      type: 'result-list',
      loginOrder: true,
      roomsData: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
      tabsSelected: '1',
      tabsList: [
        {
          label: '房间1',
          value: '1'
        }
      ],
      input_placeholder: '姓(如林楠应填 LIN)',
      value: '',
      positionSelected: '',
      positionData: [
        {
          label: '禁烟房',
          value: '禁烟房'
        },
        {
          label: '吸烟房',
          value: '吸烟房'
        },
        {
          label: '婴儿床',
          value: '婴儿床'
        },
        {
          label: '提前入住',
          value: '提前入住'
        },
        {
          label: '晚点入住',
          value: '晚点入住'
        },
        {
          label: '延迟退房',
          value: '延迟退房'
        },
        {
          label: '残疾设施房间',
          value: '残疾设施房间'
        },
        {
          label: '安静房间',
          value: '安静房间'
        },
        {
          label: '蜜月房',
          value: '蜜月房'
        },
        {
          label: '高层房间',
          value: '高层房间'
        },
        {
          label: '低楼层',
          value: '低楼层'
        }
      ]
    }
  },
  mounted () {

  },
  methods: {
    onRoomsChange (data, i) {}

  }
}
</script>

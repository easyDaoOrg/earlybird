
<style lang='scss' scoped>
@import "./bookingContacts.scss";
</style>
<template>
  <div class="booking-contacts">
    <span class="fr">
      <Button>清除</Button>
      <Button icon="ios-close-circle">删除</Button>
    </span>
    常用联系人：
    <Dropdown placement="bottom-start">
        <Button type="primary">
            选择
            <Icon type="ios-arrow-down"></Icon>
        </Button>
        <DropdownMenu slot="list">
          <div class="booking-contacts-title">请选择联系人</div>
          <div class="booking-contacts-list">

          </div>
        </DropdownMenu>
      </Dropdown>
  </div>
</template>

<script>


export default {
  data () {
    return {

    }
  },
  components: {

  },
  watch: {

  },
  methods: {

  },
  mounted () {}
}
</script>

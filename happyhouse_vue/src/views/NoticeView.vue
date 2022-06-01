<template>
  <b-container>
    <div class="notice-container-view">
      <div class="view-notice">공지사항</div>
      <router-view></router-view>
    </div>
  </b-container>
</template>

<script>
export default {
  name: "NoticeView",
};
</script>

<style scoped>
.notice-container-view {
  padding: 30px 0px 120px;
  vertical-align: center;
  margin: auto;
  width: 900px;
  min-height: fit-content;
}
.view-notice {
  font-family: "EliceDigitalBaeum-Bd";
  padding-left: 35px;
  text-align: left;
  height: 105px;
  line-height: 130px;
  font-size: 25px;
}
</style>

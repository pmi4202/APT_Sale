<template>
  <div>
    <div class="chart-container-view">
      <div class="view-chart" @click="$router.push({ name: 'chart' })">
        통계
      </div>
      <router-view></router-view>
      <!-- <router-link :to="{ name: 'chart' }"> 통계 </router-link>
      <h4>Chart!&nbsp;</h4> -->
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.chart-container-view {
  padding: 30px 0px 10px;
  vertical-align: center;
  margin: auto;
  width: 900px;
  min-height: 550px;
  height: wrap-content;
  /* max-height: 1200px; */
}
.view-chart {
  font-family: "EliceDigitalBaeum-Bd";
  padding-left: 35px;
  text-align: left;
  height: 105px;
  line-height: 130px;
  font-size: 25px;
  cursor: pointer;
}
/* .icon-big {
  width: 10em;
  height: 10em;
}
.header {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.recode-list {
  text-align: start;
  overflow-y: scroll;
  min-width: 300px;
  max-height: 600px;
}
.chart-box {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
} */
</style>

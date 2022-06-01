<template>
  <div class="dust-container">
    <div class="view-dust">대기정보</div>
    <dust-component></dust-component>
  </div>
</template>

<script>
import DustComponent from "@/components/DustComponent.vue";
export default {
  name: "DustView",
  components: { DustComponent },
};
</script>

<style scoped>
.dust-container {
  width: 900px;
  height: 74vh;
  min-height: 550px;
  margin: auto;
}
.view-dust {
  font-family: "EliceDigitalBaeum-Bd";
  padding-left: 35px;
  text-align: left;
  height: 100px;
  line-height: 130px;
  font-size: 25px;
}
</style>

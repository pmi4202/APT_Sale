<template>
  <div>
    <div class="board-container-view">
      <div class="view-board">게시판</div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import { mapMutations } from "vuex";

export default {
  name: "BoardView",
  methods: {
    ...mapMutations("boardStore", ["SEARCH_PARAM_INIT"]),
  },
  created() {
    this.SEARCH_PARAM_INIT();
  },
};
</script>

<style scoped>
.board-container-view {
  padding: 30px 0px 120px;
  vertical-align: center;
  margin: auto;
  width: 900px;
  min-height: fit-content;
  /* max-height: 1200px; */
}
.view-board {
  font-family: "EliceDigitalBaeum-Bd";
  padding-left: 35px;
  text-align: left;
  height: 105px;
  line-height: 130px;
  font-size: 25px;
}
</style>

import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

import userStore from "@/store/modules/userStore.js";
import houseStore from "@/store/modules/houseStore.js";
import boardStore from "@/store/modules/boardStore.js";
import noticeStore from "@/store/modules/noticeStore.js";
import statisticStore from "@/store/modules/statisticStore.js";

Vue.filter("dongCodeFilter", function (code) {
  return code.slice(0, 8);
});
const store = new Vuex.Store({
  modules: {
    userStore,
    houseStore,
    boardStore,
    noticeStore,
    statisticStore,
  },
  actions: {
    getLocalToken() {
      this.state.token = sessionStorage.getItem("access-token") ?? "없다";
    },
  },
  state: {
    token: "",
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});

export default store;

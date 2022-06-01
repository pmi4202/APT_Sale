const statisticStore = {
  namespaced: true,
  state: {
    code: "",
  },
  getters: {
    getCode: function (state) {
      return state.code;
    },
  },
  mutations: {},
  actions: {},
};

export default statisticStore;

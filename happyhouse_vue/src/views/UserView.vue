<template>
  <b-container class="user-container">
    <router-view />
  </b-container>
</template>
<script>
import { mapState, mapMutations } from "vuex";

const userStore = "userStore";

export default {
  name: "UserView",
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
  },
};
</script>
<style scpoed>
.user-container {
  height: 74vh;
  width: 900px;
}
</style>

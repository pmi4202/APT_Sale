<template>
  <div class="header">
    <b-navbar toggleable="lg" fixed="top" :sticky="true">
      <b-navbar-brand class="logo" href="#" to="/"> HappyHouse </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse class="header-nav-items" id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item
            class="header-nav-item"
            href="#"
            to="/house"
            v-if="loginState"
            >House</b-nav-item
          >
          <b-nav-item class="header-nav-item" href="#" to="/board"
            >Board</b-nav-item
          >
          <b-nav-item class="header-nav-item" href="#" to="/notice"
            >Notice</b-nav-item
          >
          <b-nav-item class="header-nav-item" href="#" to="/chart"
            >Chart</b-nav-item
          >
          <b-nav-item class="header-nav-item" href="#" to="/dust"
            >Dust</b-nav-item
          >
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-item-dropdown right v-if="!loginState">
            <!-- Using 'button-content' slot -->
            <template #button-content>
              <b-icon-key></b-icon-key>
              <em>USER</em>
            </template>
            <b-dropdown-item href="#" :to="{ name: 'signIn' }"
              ><b-icon-key></b-icon-key>Sign IN</b-dropdown-item
            >
            <b-dropdown-item href="#" :to="{ name: 'signUp' }"
              ><b-icon-plus></b-icon-plus> Sign UP</b-dropdown-item
            >
          </b-nav-item-dropdown>
          <b-nav-item-dropdown right v-else>
            <!-- Using 'button-content' slot -->
            <template #button-content>
              <b-icon-people></b-icon-people>
              <em>MyPage</em>
            </template>
            <b-dropdown-item href="#" :to="{ name: 'mypage' }"
              ><b-icon-person></b-icon-person> Profile</b-dropdown-item
            >
            <b-dropdown-item href="#" @click.prevent="onClickLogout"
              ><b-icon-outlet></b-icon-outlet> Logout</b-dropdown-item
            >
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";
const userStore = "userStore";
export default {
  computed: {
    ...mapGetters(userStore, ["loginState"]),
  },
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "main" });
    },
  },
};
</script>

<style scoped>
.logo {
  font-family: "Cafe24Ohsquare";
  /* font-family: "GothicA1-Light"; */
}
.header {
  width: 100vw;
  min-height: 70px;
  box-shadow: 0px 0px 3px #1d2140;
  border-radius: 0px 0px 5px 5px;
  z-index: 100;
}
.header nav {
  min-height: 70px;
}
.navbar {
  padding-left: 30px;
  padding-right: 30px;
}
.header-nav-items {
  padding-left: 8px;
}
.header-nav-item {
  font-family: "HallymGothic-Regular";
  padding-left: 8px;
  font-size: 15px;
}
</style>

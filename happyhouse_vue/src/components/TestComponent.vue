<template>
  <b-container>
    <h1>로그인 테스팅</h1>
    <b-container style="border: 1px solid #cecece">
      <label>아이디</label>
      <input type="text" v-model="username" />
      <label>비번</label>
      <input type="text" v-model="password" />
      <button @click="loginClick">로그인 전송</button>
    </b-container>
    <h1>로그인 후 상태확인</h1>
    <b-container style="border: 1px solid #cecece">
      <b-row>
        <b-col>로그인 상태 - </b-col>
        <b-col>{{ isLogin }}</b-col>
      </b-row>
      <b-row>
        <b-col>isLoginError - </b-col>
        <b-col>{{ isLoginError }}</b-col>
      </b-row>
      <b-row>
        <b-col>유저 정보 </b-col>
        <b-col>{{ userInfo == null ? "null이네" : userInfo }}</b-col>
      </b-row>
      <b-row>
        <b-col>토큰: {{ token }} </b-col>
        <b-button @click="getToken"></b-button>
      </b-row>
    </b-container>
    <TestRegistComponent></TestRegistComponent>
    <TestUpdateUser></TestUpdateUser>
    <TestStatistic></TestStatistic>
    <TestNotice></TestNotice>
    <b-button @click="logout">로그아웃</b-button>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import TestRegistComponent from "@/components/test/TestRegistCompoent.vue";
import TestUpdateUser from "@/components/test/TestUpdateUser.vue";
import TestStatistic from "@/components/test/TestStatistic.vue";
import TestNotice from "@/components/test/TestNotice.vue";

const userStore = "userStore";

export default {
  data() {
    return {
      username: "",
      password: "",
    };
  },
  components: {
    TestRegistComponent,
    TestUpdateUser,
    TestStatistic,
    TestNotice,
  },
  computed: {
    ...mapState(["token"]),
    ...mapState(userStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(userStore, [
      "userLogin",
      "testFunc",
      "getUserInfo",
      "logout",
    ]),
    async loginClick() {
      const user = {
        username: this.username,
        password: this.password,
      };
      console.log(user);
      await this.userLogin({
        username: this.username,
        password: this.password,
      });
      if (this.isLogin) {
        await this.getUserInfo();
      }
    },
    getToken() {
      console.log("토큰확인");
      console.log(this.$store);
      this.$store.dispatch("getLocalToken");
    },
  },
};
</script>

<style></style>

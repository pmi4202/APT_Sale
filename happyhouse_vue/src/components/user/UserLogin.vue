<template>
  <div class="login-container">
    <div class="login-form">
      <div class="login-title">
        <div class="login-row">로그인</div>
        <p class="login-row1">서비스 이용을 위해 로그인해주세요.</p>
      </div>
      <div class="login-row2">
        <div class="login-form-item">
          <input
            type="text"
            v-model="user.userid"
            required
            placeholder="ID"
            class="account"
            id="id"
            @keyup.enter="confirm"
          />
          <br />
          <input
            type="password"
            v-model="user.userpwd"
            required
            placeholder="Password"
            class="account"
            id="password"
            @keyup.enter="confirm"
          />
        </div>
        <div class="regist-link-box">
          <a
            class="regist-btn"
            href="#"
            @click.prevent="$router.push({ name: 'signUp' })"
            >회원가입</a
          >
        </div>
        <b-button class="account" id="login-button" @click="loginClick"
          >로그인</b-button
        >
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const userStore = "userStore";
export default {
  name: "UserLogin",
  data() {
    return {
      user: {
        userid: "",
        userpwd: "",
      },
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(userStore, ["userLogin", "getUserInfo"]),
    async loginClick() {
      const user = {
        userid: this.user.userid,
        password: this.user.userpwd,
      };
      console.log(user);
      await this.userLogin(user);
      if (this.isLogin) {
        await this.getUserInfo();
        this.$router.push({ name: "main" });
      }
    },
  },
};
</script>

<style scoped>
.login-container {
  margin: auto;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  border-radius: 5px;
}
.login-form {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  width: 550px;
  height: 450px;
  border: 1px solid rgba(211, 211, 211, 0.71);
  border-radius: 5px;
}
/* 상단 */
.login-row {
  font-family: "KoPubDotumMedium";
  font-size: 27px;
  margin-top: 0px;
}
.login-title {
  width: 100%;
  margin-bottom: 10px;
}
.login-row {
  font-family: "HallymGothic-Regular";
  margin-top: 0px;
  margin-bottom: 40px;
}
.login-row1 {
  font-size: 15px;
  margin-top: -30px;
}
/* 입력창 */
.login-form-item > input {
  height: 30px;
  width: 200px;
  margin-bottom: 5px;
  font-size: 14px;
  padding-left: 10px;
}
.login-row2 input {
  color: rgb(94, 94, 94);
  border-top-left-radius: 4px;
  border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
  border-bottom-left-radius: 4px;
  border-width: 1px 1px 1px 1px;
  border-style: solid;
  border-color: rgb(204, 204, 204);
}
#id {
  color: rgb(94, 94, 94);
  border-top-left-radius: 4px;
  border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
  border-bottom-left-radius: 4px;
  border-width: 1px 1px 1px 1px;
  border-style: solid;
  border-color: rgb(204, 204, 204);
}
.regist-link-box {
  height: 30px;
}
.regist-btn {
  font-size: 12px;
  float: right;
  text-decoration: underline;
}
#login-button {
  width: 50%;
  background-color: rgb(52, 152, 219);
  border: none;
  color: white;
}
</style>

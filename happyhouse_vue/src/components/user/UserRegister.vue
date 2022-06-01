<template>
  <div class="register-container">
    <div class="register-form">
      <div class="register-title">
        <div class="register-row">회원가입</div>
        <p class="register-row1">서비스 이용을 위해 회원가입해주세요.</p>
      </div>
      <div class="register-row2">
        <div class="register-form-item">
          <span>ID</span>
          <input
            type="text"
            v-model="userId"
            class="account"
            placeholder="아이디"
          />
        </div>
        <div class="register-form-item">
          <span>Password</span>
          <input
            type="password"
            v-model="userPassword"
            class="account"
            placeholder="비밀번호"
          />
        </div>
        <div class="register-form-item">
          <span>Name</span>
          <input
            type="text"
            v-model="userName"
            class="account"
            placeholder="이름"
          />
        </div>
        <div class="register-form-item">
          <span>Address</span>
          <input
            type="text"
            v-model="userAddress"
            class="account"
            placeholder="주소"
          />
        </div>
        <div class="register-form-item">
          <span>Phone</span>
          <input
            type="text"
            v-model="phoneNumber"
            class="account"
            placeholder="휴대폰 번호"
          />
        </div>
        <b-button class="register-button" @click="registClick"
          >회원가입</b-button
        >
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import regexes from "@/config/validRegexProperty";

const userStore = "userStore";
export default {
  data() {
    return {
      userId: "",
      userPassword: "",
      userName: "",
      userAddress: "",
      phoneNumber: "",
    };
  },
  methods: {
    ...mapActions(userStore, ["registUser"]),
    registClick() {
      //   if (
      //     !(
      //       regexes.IDCHECK.test(this.userId) &&
      //       regexes.PASSWORD.test(this.userPassword) &&
      //       regexes.USERNAME.test(this.userName) &&
      //       regexes.ADDRESS.test(this.userAddress) &&
      //       regexes.PHONENUMBER.test(this.phoneNumber)
      //     )
      //   ) {
      //     console.log("입력 포맷 에러");
      //     return;
      //   }

      console.log(regexes.IDCHECK.test(this.userId));
      console.log(regexes.PASSWORD.test(this.userPassword));
      console.log(regexes.USERNAME.test(this.userName));
      console.log(regexes.ADDRESS.test(this.userAddress));
      console.log(regexes.PHONENUMBER.test(this.phoneNumber));
      const user = {
        userId: this.userId,
        userPassword: this.userPassword,
        userName: this.userName,
        userAddress: this.userAddress,
        phoneNumber: this.phoneNumber,
      };
      this.registUser(user);
    },
  },
};
</script>

<style scoped>
.register-container {
  margin: auto;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  border-radius: 5px;
}
.register-form {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  width: 550px;
  height: 450px;
  border: 1px solid rgba(211, 211, 211, 0.71);
  border-radius: 5px;
}
.register-title {
  width: 100%;
  margin-bottom: 30px;
}
/* 입력창 */
.register-form-item {
  height: 35px;
  width: 350px;
}
.register-form-item > input {
  width: 250px;
  float: right;
}
.register-form-item > span {
  float: left;
}
.register-row {
  font-family: "KoPubDotumMedium";
  font-size: 27px;
  margin-top: 0px;
}
.register-row1 {
  font-family: "GothicA1-Light";
  font-size: 13px;
}

.register-row2 input {
  color: rgb(94, 94, 94);
  border-top-left-radius: 4px;
  border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
  border-bottom-left-radius: 4px;
  border-width: 1px 1px 1px 1px;
  border-style: solid;
  border-color: rgb(204, 204, 204);
}

.account {
  float: right;
  margin-bottom: 3px;
  padding: 3px;
  border: 1px solid lightgray;
  border-radius: 3px;
}
.register-button {
  margin-top: 10px;
  width: 100%;
  background-color: rgb(52, 152, 219);
  border-color: transparent;
  color: white;
}
</style>

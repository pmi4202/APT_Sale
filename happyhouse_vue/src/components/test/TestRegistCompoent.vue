<template>
  <b-container style="border: 1px solid #cecece">
    <h1>회원 가입 제공창</h1>
    <b-row>
      <b-col>아이디</b-col>
      <b-col><b-form-input type="text" v-model="userId"></b-form-input></b-col>
    </b-row>
    <b-row>
      <b-col>패스워드</b-col>
      <b-col
        ><b-form-input type="text" v-model="userPassword"></b-form-input
      ></b-col>
    </b-row>
    <b-row>
      <b-col>이름 </b-col>
      <b-col
        ><b-form-input type="text" v-model="userName"></b-form-input
      ></b-col>
    </b-row>
    <b-row>
      <b-col>주소 </b-col>
      <b-col
        ><b-form-input type="text" v-model="userAddress"></b-form-input
      ></b-col>
    </b-row>
    <b-row>
      <b-col>휴대폰 번호 </b-col>
      <b-col
        ><b-form-input type="text" v-model="phoneNumber"></b-form-input
      ></b-col>
    </b-row>
    <b-row>
      <b-button @click="registClick">회원가입</b-button>
    </b-row>
  </b-container>
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

<style></style>

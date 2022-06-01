<template>
  <b-container style="border: 1px solid #cecece" v-if="userInfo">
    <h1>회원 업데이트 제공창</h1>
    <template v-if="userInfo">
      <b-row>
        <b-col>아이디</b-col>
        <b-col
          ><b-form-input
            type="text"
            v-bind:value="userId"
            readonly
          ></b-form-input
        ></b-col>
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
        <b-button @click="userUpdateClick">수정</b-button>
      </b-row>
    </template>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

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
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  created() {
    if (this.userInfo) {
      this.userId = this.userInfo.userId;
      this.userPassword = this.userInfo.userPassword;
      this.userName = this.userInfo.userName;
      this.userAddress = this.userInfo.userAddress;
      this.phoneNumber = this.userInfo.phoneNumber;
    }
  },
  methods: {
    ...mapActions(userStore, ["updateUser"]),
    userUpdateClick() {
      const modifyedUser = {
        userId: this.userId,
        userPassword: this.userPassword,
        userName: this.userName,
        userAddress: this.userAddress,
        phoneNumber: this.phoneNumber,
      };
      console.log(modifyedUser);
      if (this.userInfo.userId != this.userId) {
        console.log("어케 했누?");
        return;
      }
      this.updateUser(modifyedUser);
    },
  },
};
</script>

<style></style>

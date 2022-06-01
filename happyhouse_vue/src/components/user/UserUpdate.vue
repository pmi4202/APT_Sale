<template>
  <div class="mypage-update-container">
    <div class="mypage-update-row">정보 수정하기</div>
    <div class="mypage-update-row2">
      <table class="update-table">
        <tbody>
          <tr>
            <th>아이디</th>
            <td><input type="text" v-bind:value="userId" readonly /></td>
          </tr>
          <tr>
            <th>이름</th>
            <td><input type="text" v-model="userName" /></td>
          </tr>
          <tr>
            <th>주소</th>
            <td><input type="text" v-model="userAddress" /></td>
          </tr>
          <tr>
            <th>휴대폰</th>
            <td><input type="text" v-model="phoneNumber" /></td>
          </tr>
          <tr>
            <th>가입일</th>
            <td>{{ userInfo.registerDate }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div>
      <b-button class="updateclick-button" @click="userUpdateClick"
        >수정</b-button
      >
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const userStore = "userStore";
export default {
  data() {
    return {
      userId: "",
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

<style scoped>
.mypage-update-container {
  overflow: hidden;
  position: relative;
  width: 600px;
  height: 100%;
  margin: auto;
  padding-top: 25px;
}
input {
  padding-left: 5px;
  width: 85%;
}
.mypage-update-row {
  font-family: "EliceDigitalBaeum-Bd";
  font-weight: bold;
  font-size: 25px;
  text-align: left;
  height: 100px;
  line-height: 130px;
  margin-bottom: 20px;
}
.mypage-update-row2 {
  width: 600px;
  height: 230px;
}
.update-table {
  width: 100%;
  max-width: 100%;
  height: 100%;
  border-spacing: 0;
  border-collapse: collapse;
  border: 2px solid #ccc;
  table-layout: fixed;
}
.update-table th {
  background: #eaeaea;
  border: 1px solid #ccc;
}
.update-table td {
  border: 1px solid #ccc;
}
.updateclick-button {
  position: absolute;
  top: 390px;
  left: 452px;
  width: 151px;
  height: 40px;
  font-size: 14px;
  color: rgb(255, 255, 255);
  text-align: center;
  line-height: 2em;
  border-radius: 4px;
  background-color: rgb(52, 152, 219);
  border: none;
}
</style>

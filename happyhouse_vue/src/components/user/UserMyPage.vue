<template>
  <div class="mypage-container" v-if="userInfo">
    <div class="mypage-content">
      <div class="mypage-row">회원정보</div>
      <div class="mypage-row2">
        <div class="mypage-infos">
          <div class="mypage-info-left">
            <div class="profile">
              <img rounded="circle" src="@/assets/profile.jpg" />
            </div>
          </div>
          <div class="mypage-table-box">
            <table class="mypage-table">
              <tbody>
                <tr class="mypage-table-row">
                  <th>아이디</th>
                  <td>{{ userInfo.userId }}</td>
                </tr>
                <tr class="mypage-table-row">
                  <th>이름</th>
                  <td>{{ userInfo.userName }}</td>
                </tr>
                <tr class="mypage-table-row">
                  <th>주소</th>
                  <td>{{ userInfo.userAddress }}</td>
                </tr>
                <tr class="mypage-table-row">
                  <th>휴대폰</th>
                  <td>{{ userInfo.phoneNumber }}</td>
                </tr>
                <tr class="mypage-table-row">
                  <th>가입일</th>
                  <td>{{ userInfo.registerDate }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <div class="mypage-btns">
          <b-button :to="{ name: 'update' }" class="update-button mypage-btn"
            >회원 정보 수정</b-button
          >
          <b-button
            @click="disjointClick(userInfo.userId)"
            class="secession-button mypage-btn"
            >회원 탈퇴</b-button
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const userStore = "userStore";

export default {
  name: "UserMyPage",
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["disjoint"]),
    disjointClick(userId) {
      let decision = confirm("정말로 탈퇴하시겠습니까?");
      if (decision) {
        console.log(userId);
        this.disjoint(userId);
      }
    },
  },
};
</script>

<style scoped>
/* .button-box {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;
} */
.mypage-container {
  font-size: 15px;
  overflow: hidden;
  position: relative;
  height: 100%;
  margin: auto;
}
.mypage-content {
  width: 600px;
  height: 100%;
  padding-top: 25px;
  margin: auto;
}
.mypage-row {
  font-family: "EliceDigitalBaeum-Bd";
  font-weight: bold;
  font-size: 25px;
  text-align: left;
  height: 100px;
  line-height: 130px;
  margin-bottom: 20px;
}
.mypage-row2 {
  width: 600px;
  height: 260px;
}
.mypage-infos {
  width: fit-content;
  margin: auto;
  display: flex;
}
.mypage-table {
  /*position: absolute;
  left: 209px;*/
  border-spacing: 0;
  border-collapse: collapse;
  border: 2px solid #ccc;
  width: 100%;
  border-collapse: collapse;
  /*table-layout: fixed;*/
}
.mypage-table-row {
  height: 40px;
}
.mypage-table th {
  border-collapse: collapse;
  background: #eaeaea;
  border: 1px solid #ccc;
  width: 170px;
}

.mypage-table td {
  border-collapse: collapse;
  border: 1px solid #ccc;
}
.mypage-table-box {
  margin-left: 25px;
  width: 400px;
}
/* 버튼 */
.mypage-btns {
  margin-top: 10px;
  padding-right: 10px;
  height: 40px;
  text-align: right;
}
.mypage-btn {
  margin-right: 5px;
}
.update-button {
  width: 160px;
  height: 40px;
  font-size: 14px;
  color: rgb(255, 255, 255);
  text-align: center;
  line-height: 2em;
  border-radius: 4px;
  background-color: rgb(52, 152, 219);
  border: none;
}
.secession-button {
  width: 160px;
  height: 40px;
  font-size: 14px;
  color: rgb(255, 255, 255);
  text-align: center;
  line-height: 1em;
  border-radius: 4px;
  background-color: rgb(52, 152, 219);
  border: none;
}
.profile {
  width: 150px;
  height: 150px;
}
.mypage-info-left {
  padding-top: 25px;
  height: 202px;
}
img {
  left: 0;
  width: 100%;
  height: 100%;
  border-top-left-radius: 50%;
  border-top-right-radius: 50%;
  border-bottom-right-radius: 50%;
  border-bottom-left-radius: 50%;
}
</style>

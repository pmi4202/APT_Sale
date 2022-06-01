<template>
  <b-container class="notice-form" fluid="md">
    <div>
      <div class="notice-form-item">
        <label for="title">제목</label>
        <b-form-input
          id="title"
          v-model="title"
          placeholder="제목을 입력해 주세요"
          class="notice-form-input"
        ></b-form-input>
      </div>
      <div class="notice-form-item">
        <label for="content">본문</label>
        <b-form-textarea
          id="content"
          v-model="content"
          placeholder="내용을 입력해주세요"
          rows="10"
          class="notice-form-input"
        ></b-form-textarea>
      </div>
      <div class="notice-list-btns">
        <button
          class="notice-btn notice-cancel"
          @click="$router.push({ name: 'notice' })"
        >
          취소
        </button>
        <button class="notice-btn notice-regist" @click="registnotice">
          등록
        </button>
      </div>
    </div>
  </b-container>
</template>

<script>
import { mapActions } from "vuex";

const noticeStore = "noticeStore";

export default {
  name: "NoticeRegister",
  data() {
    return {
      title: "",
      content: "",
    };
  },
  methods: {
    ...mapActions(noticeStore, ["registerNotice"]),
    registnotice() {
      if (this.title === "") {
        alert("제목을 입력해주세요.");
        return;
      } else if (this.content === "") {
        alert("내용을 입력해주세요.");
        return;
      }

      const notice = {
        title: this.title,
        text: this.content,
      };
      this.registerNotice(notice);
    },
  },
};
</script>

<style scoped>
.board-form {
  text-align: left;
}
label {
  font-weight: bold;
  font-size: large;
  margin: 10px;
}
/* 입력창 */
.notice-form {
  padding-top: 15px;
  text-align: left;
  width: 860px;
}
.notice-form-item {
  margin-bottom: 15px;
}
.notice-form-item > label {
  font-family: "Noto Sans KR", sans-serif;
  font-weight: normal;
  font-size: 17px;
}
/* 버튼 */
.notice-list-btns {
  text-align: center;
  margin-top: 40px;
}
.notice-btn {
  border: none;
  width: 100px;
  height: 40px;
  font-size: 14px;
  font-weight: bold;
  color: white;
  margin: 0px 3px;
}
.notice-cancel {
  background-color: gray;
}
.notice-regist {
  background-color: rgb(52, 152, 219);
}
</style>

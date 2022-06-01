<template>
  <b-container class="board-form">
    <div>
      <div class="board-form-item">
        <label for="title">제목</label>
        <b-form-input
          id="title"
          v-model="title"
          placeholder="제목을 입력해 주세요"
          class="board-form-input"
        ></b-form-input>
      </div>
      <div class="board-form-item">
        <label for="content">본문</label>
        <b-form-textarea
          id="content"
          v-model="content"
          placeholder="내용을 입력해주세요"
          rows="10"
          class="board-form-input"
        ></b-form-textarea>
      </div>

      <div class="board-file-form">
        <label>첨부 파일 </label>
        <input type="file" ref="file" multiple @change="uploadFile" />
      </div>
      <div class="board-list-btns">
        <button
          class="board-btn board-cancel"
          @click="$router.push({ name: 'board' })"
        >
          취소
        </button>
        <button class="board-btn board-regist" @click="register">등록</button>
      </div>
    </div>
  </b-container>
</template>

<script>
import { mapActions } from "vuex";

const boardStore = "boardStore";
export default {
  name: "BoardRegister",
  data() {
    return {
      title: "",
      content: "",
      files: null,
    };
  },
  methods: {
    ...mapActions(boardStore, ["registerBoard"]),
    uploadFile() {
      console.log("파일 업로드");
      this.files = this.$refs.file.files;
    },
    register() {
      if (this.title === "") {
        alert("제목을 입력해주세요.");
        return;
      } else if (this.content === "") {
        alert("내용을 입력해주세요.");
        return;
      }

      let board = {
        title: this.title,
        text: this.content,
        files: this.files,
      };
      console.log("레지스터 보드 호출");
      this.registerBoard(board);
    },
  },
};
</script>

<style scoped>
.board-form {
  padding-top: 15px;
  text-align: left;
  width: 860px;
}
.board-form-item {
  margin-bottom: 15px;
}
.board-form-item > label {
  font-family: "Noto Sans KR", sans-serif;
  font-weight: normal;
  font-size: 17px;
}
.board-list-btns {
  text-align: center;
  margin-top: 40px;
}
.board-btn {
  border: none;
  width: 100px;
  height: 40px;
  font-size: 14px;
  font-weight: bold;
  color: white;
  margin: 0px 3px;
}
.board-cancel {
  background-color: gray;
}
.board-regist {
  background-color: rgb(52, 152, 219);
}
.board-file-form > label {
  font-family: "Noto Sans KR", sans-serif;
  font-size: 16px;
}
.board-file-form > input {
  font-size: 15px;
}
label {
  font-weight: bold;
  font-size: large;
  margin: 10px;
}
</style>

<template>
  <div class="comment-write-container" v-if="checkUserInfo">
    <textarea cols="105" rows="3" @keyup.enter="edit" v-model="text"></textarea>
    <button @click="edit">등록</button>

    <!-- <b-input-group :prepend="checkUserInfo.userName" class="mt-3">
      <b-form-input @keyup.enter="edit" v-model="text"></b-form-input>
      <b-input-group-append>
        <b-button variant="outline-success" @click="edit">작성</b-button>
      </b-input-group-append>
    </b-input-group> -->
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
const userStore = "userStore";
const boardStore = "boardStore";
export default {
  data() {
    return {
      text: "",
    };
  },
  computed: {
    ...mapGetters(userStore, ["checkUserInfo"]),
    ...mapGetters(boardStore, ["getSelectedBoardNo"]),
  },
  methods: {
    ...mapActions(boardStore, ["editComment"]),
    edit() {
      const comment = {
        text: this.text,
        boardno: this.getSelectedBoardNo,
      };
      this.editComment(comment);
      this.text = "";
    },
  },
};
</script>

<style scoped>
.comment-write-container {
  margin: 0px auto 30px;
  padding: 20px;
  height: 165px;
  border: 1px solid rgb(231, 231, 231);
  background-color: rgb(245, 245, 245);
}
.comment-write-container textarea {
  border: 1px solid rgb(231, 231, 231);
  font-size: 14px;
  padding: 7px;
}
.comment-write-container button {
  border: none;
  width: 100px;
  height: 30px;
  font-size: 14px;
  font-weight: bold;
  color: white;
  float: right;
  background-color: rgb(52, 152, 219);
  margin-top: 10px;
}
</style>

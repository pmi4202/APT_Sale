<template>
  <b-form>
    <div>제목</div>
    <b-input type="text" v-model="title"></b-input>
    <div>본문</div>
    <b-input type="text" v-model="text"></b-input>
    <b-button @click="write">작성</b-button>
  </b-form>
</template>

<script>
import { mapActions } from "vuex";
const noticeStore = "noticeStore";

export default {
  data() {
    return {
      title: "",
      text: "",
    };
  },
  methods: {
    ...mapActions(noticeStore, ["writeNotice"]),
    write() {
      const notice = {
        title: this.title,
        text: this.text,
      };
      this.writeNotice(notice);
    },
  },
};
</script>

<style></style>

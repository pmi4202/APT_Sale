<template>
  <b-container>
    <div class="notice-list-header">
      <button
        class="notice-regist-btn"
        @click="$router.push({ name: 'noticeregister' })"
      >
        등록
      </button>
    </div>
    <table class="notice-table">
      <colgroup>
        <col style="width: 150px" />
        <col style="width: 400px" />
        <col style="width: 150px" />
        <col style="width: 150px" />
      </colgroup>
      <thead>
        <tr>
          <th scope="col">번호</th>
          <th scope="col">제목</th>
          <th scope="col">작성자</th>
          <th scope="col">작성일</th>
        </tr>
      </thead>
      <tbody>
        <NoticeListItem
          v-for="notice in notices"
          :key="notice.no"
          :notice="notice"
        ></NoticeListItem>
      </tbody>
    </table>
    <ul class="pagination justify-content-md-center">
      <li class="page-item">
        <a class="page-link" @click="pageMove(navigation.prevPageNum)" href="#"
          >Previous</a
        >
      </li>

      <li
        v-bind:class="{
          'page-item': true,
          active: index == navigation.curPageNum,
        }"
        v-for="index in navigation.endPageNum - navigation.startPageNum + 1"
        :key="index"
      >
        <a
          class="page-link"
          href="#"
          @click="pageMove(index + navigation.startPageNum - 1)"
          >{{ index + navigation.startPageNum - 1 }}</a
        >
      </li>

      <li class="page-item">
        <a class="page-link" href="#" @click="pageMove(navigation.nextPageNum)"
          >Next</a
        >
      </li>
    </ul>
  </b-container>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
const noticeStore = "noticeStore";

import NoticeListItem from "@/components/notice/NoticeListItem.vue";

export default {
  name: "NoticeList",
  components: {
    NoticeListItem,
  },
  computed: {
    ...mapState(noticeStore, ["notices", "navigation"]),
  },
  methods: {
    ...mapMutations(noticeStore, ["SET_SELECTED_NOTICE"]),
    ...mapActions(noticeStore, ["getNotices"]),
    pageMove(pageNum) {
      // console.log(pageNum);
      this.getNotices(pageNum);
    },
  },

  created() {
    this.getNotices();
    this.SET_SELECTED_NOTICE(null);
    console.log("qwe");
  },
};
</script>

<style scoped>
/* 등록버튼 */
.notice-list-header {
  height: 60px;
  text-align: right;
  padding-right: 10px;
}
.notice-regist-btn {
  border: none;
  width: 100px;
  height: 40px;
  font-size: 14px;
  font-weight: bold;
  background-color: rgb(52, 152, 219);
  color: white;
}
/* 리스트 테이블 */
.notice-table {
  margin: 0 auto 45px;
  font-size: 15px;
  border-collapse: collapse;
}
.notice-table > thead > tr {
  height: 55px;
  background-color: rgb(241, 241, 241);
}
</style>

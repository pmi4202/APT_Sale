<template>
  <b-container>
    <div class="board-list-header">
      <button
        class="board-regist-btn"
        @click="$router.push({ name: 'boardregister' })"
      >
        글쓰기
      </button>
    </div>
    <table class="board-table">
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
        <BoardListItem
          v-for="board in boards"
          :key="board.no"
          :board="board"
        ></BoardListItem>
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
    <div class="board-footer">
      <div class="search-input">
        <b-form-select
          v-model="selectedOption"
          :options="options"
          class="select-option"
        ></b-form-select>
        <b-form-input
          :disabled="selectedOption == ''"
          v-model="selectedSearchWord"
          placeholder="입력하세요"
        ></b-form-input>
        <b-button @click="searchConditionClick">검색</b-button>
      </div>
    </div>
  </b-container>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
const boardStore = "boardStore";

import BoardListItem from "@/components/board/BoardListItem.vue";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
  },
  data() {
    return {
      selectedOption: "",
      selectedSearchWord: "",
      options: [
        { value: "", text: "조건 없음" },
        { value: "title", text: "제목" },
        { value: "username", text: "이름" },
        { value: "no", text: "no" },
      ],
    };
  },
  computed: {
    ...mapState(boardStore, ["boards", "navigation"]),
  },
  methods: {
    ...mapMutations(boardStore, [
      "SET_SELECTED_BOARD",
      "SET_BOARD_COMMENT",
      "SEARCH_PARAM_SET",
    ]),
    ...mapActions(boardStore, ["getBoards"]),
    pageMove(pageNum) {
      console.log(pageNum);
      this.getBoards(pageNum);
    },
    searchConditionClick() {
      this.SEARCH_PARAM_SET({
        selectedOption: this.selectedOption,
        selectedSearchWord: this.selectedSearchWord,
      });
      this.getBoards(1);
    },
  },

  created() {
    this.getBoards();
    this.SET_SELECTED_BOARD("aaa");
    this.SET_BOARD_COMMENT(null);
    console.log("qwe");
  },
};
</script>

<style scoped>
/* 글쓰기 버튼 */
.board-list-header {
  height: 60px;
  text-align: right;
  padding-right: 10px;
}
.board-regist-btn {
  border: none;
  width: 100px;
  height: 40px;
  font-size: 14px;
  font-weight: bold;
  background-color: rgb(52, 152, 219);
  color: white;
}
.board-table {
  margin: 0 auto 45px;
  font-size: 15px;
  border-collapse: collapse;
}
.board-table > thead > tr {
  height: 55px;
  background-color: rgb(241, 241, 241);
}
.search-input {
  display: grid;
  grid-template-columns: 0.5fr 1fr 0.5fr;
  gap: 10px;
  width: 500px;
  margin: auto;
}
.search-input > * {
  padding: 0 10px;
}
.board-footer {
  padding-top: 20px;
  height: 60px;
  /* margin-bottom: 90px; */
  font-size: 15px;
}
.select-option {
  padding-left: 10px;
}
</style>

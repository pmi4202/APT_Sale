<template>
  <b-tr class="board-item-tr">
    <b-td class="board-item-td">{{ board.no }} </b-td>
    <b-td class="text-left board-item-td board-item-title">
      <router-link
        v-if="board.deleted == 0"
        :to="{ name: 'boardDetail', params: { boardno: board.no } }"
        >{{ board.title }}</router-link
      >
      <span v-else>{{ board.title }}</span>
    </b-td>
    <b-td class="board-item-td">{{ board.userName }}</b-td>

    <b-td class="board-item-td">{{ board.createDate | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardListItem",
  props: {
    board: Object,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style scoped>
.board-item-tr {
  height: 55px;
  border-bottom: 0.5px solid rgb(241, 241, 241);
}
.board-item-tr,
.board-item-td {
  border-collapse: collapse;
}
.board-item-title > a {
  color: #2c3e50;
}
</style>

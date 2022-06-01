<template>
  <b-tr class="notice-item-tr">
    <b-td class="notice-item-td">{{ notice.no }}</b-td>
    <b-td class="notice-item-td notice-item-title text-left">
      <router-link
        :to="{ name: 'noticeDetail', params: { noticeno: notice.no } }"
        >{{ notice.title }}</router-link
      >
    </b-td>
    <b-td class="notice-item-td">{{ notice.userName }}</b-td>
    <b-td class="notice-item-td">{{ notice.createDate | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "NoticeListItem",
  props: {
    notice: Object,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style scoped>
.notice-item-tr {
  height: 55px;
  border-bottom: 0.5px solid rgb(241, 241, 241);
}
.notice-item-tr,
.notice-item-td {
  border-collapse: collapse;
}
.notice-item-title > a {
  color: #2c3e50;
}
</style>

<template>
  <div>
    <b-list-group v-if="comments">
      <b-list-group-item
        v-for="(comment, idx) in pagedList"
        :key="idx"
        href="#"
        id="list"
        class="flex-column align-items-start"
      >
        <div class="d-flex w-100 justify-content-between">
          <div class="d-flex">
            <h6 class="mb-1" style="margin-right: 10px">
              {{ comment.userName }}
            </h6>
            <small class="left">{{ comment.text }}</small>
          </div>
          <small
            >{{ comment.editDate | dayOrHour }}
            <b-link
              href="#"
              v-if="comment.userId == checkUserInfo.userId"
              @click="removeClick(comment.no)"
              >[삭제]</b-link
            ></small
          >
        </div>
      </b-list-group-item>
      <div class="comment-pagination">
        <b-pagination
          v-model="currentPage"
          :total-rows="comments.length"
          :per-page="10"
          aria-controls="list"
        ></b-pagination>
      </div>
    </b-list-group>
    <small v-else class="comment-list">등록된 댓글이 아직 없습니다.</small>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

const boardStore = "boardStore";
const userStore = "userStore";
export default {
  data() {
    return {
      currentPage: 1,
      perPage: 10,
    };
  },
  props: {
    comments: {
      type: Array,
    },
  },
  computed: {
    pagedList() {
      let start = (this.currentPage - 1) * this.perPage;
      let end = start + this.perPage;
      return this.comments.slice(start, end);
    },
    ...mapGetters(userStore, ["checkUserInfo"]),
  },
  methods: {
    ...mapActions(boardStore, ["removeComment"]),
    removeClick(no) {
      this.removeComment(no);
    },
  },
  filters: {
    dayOrHour(value) {
      //https://kdinner.tistory.com/68
      const today = new Date();
      const timeValue = new Date(value);
      const betweenTime = Math.floor(
        (today.getTime() - timeValue.getTime()) / 1000 / 60,
      );
      if (betweenTime < 1) return "방금전";
      if (betweenTime < 60) {
        return `${betweenTime}분전`;
      }

      const betweenTimeHour = Math.floor(betweenTime / 60);
      if (betweenTimeHour < 24) {
        return `${betweenTimeHour}시간전`;
      }

      const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
      if (betweenTimeDay < 365) {
        return `${betweenTimeDay}일전`;
      }

      return `${Math.floor(betweenTimeDay / 365)}년전`;
    },
  },
};
</script>

<style scoped>
.left {
  text-align: left;
}
.comment-pagination {
  margin: auto;
}
.comment-pagination {
  margin-top: 15px;
}
</style>

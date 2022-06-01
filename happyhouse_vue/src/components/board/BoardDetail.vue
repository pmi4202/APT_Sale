<template>
  <div class="board-detail-container">
    <b-container class="board-detail-inner-container" v-if="selectedBoard">
      <b-row class="header">
        <b-col class="board-no" cols="2"> {{ selectedBoard.no }} </b-col>
        <b-col cols="9" v-if="editMode">
          <b-form-input type="text" v-model="editTitle"></b-form-input>
        </b-col>
        <b-col cols="9" v-else> {{ selectedBoard.title }}</b-col>
      </b-row>
      <div class="board-info">
        <div class="board-info-writer">
          <p class="board-info-label">작성자</p>
          <p>{{ selectedBoard.userName }} ( {{ selectedBoard.userId }} )</p>
        </div>
        <div class="board-info-date">
          <p class="board-info-label">작성일</p>
          <p>{{ selectedBoard.createDate }}</p>
        </div>
      </div>
      <!-- <b-row class="meta">
        <b-col cols="3" class="no">작성자</b-col>
        <b-col cols="3">
          {{ selectedBoard.userName }} ( {{ selectedBoard.userId }} )</b-col
        >
        <b-col cols="3" class="no">작성일</b-col>
        <b-col cols="3"> {{ selectedBoard.createDate }}</b-col>
      </b-row> -->
      <div class="board-detail-content">
        <div v-if="editMode">
          <b-form-textarea
            class="board-detail-textarea textarea-edit-mode"
            type="textarea"
            v-model="editText"
          ></b-form-textarea>
        </div>
        <div
          class="board-detail-textarea"
          v-else
          v-html="selectedBoard.text"
        ></div>
      </div>
      <b-row class="board-detail-file">
        <b-list-group-item
          v-for="(item, index) in selectedBoard.filelist"
          :key="index"
          class="d-flex justify-content-between align-items-center"
          style="width: 100%"
        >
          <span> {{ item.originalFileName }}</span>
          <b-link href="#" @click="fileDownloadClick(item)"
            >다운로드 &#8615;</b-link
          >
        </b-list-group-item>
      </b-row>
      <div
        class="board-edit-btn"
        v-if="selectedBoard.userId == checkUserInfo.userId"
      >
        <b-button v-b-toggle.sidebar-1
          ><b-icon-pencil></b-icon-pencil
        ></b-button>
      </div>
      <div class="board-detail-comment">
        <b-row>
          <p class="board-comment-label">댓글</p>
        </b-row>
        <CommentWriteComponent></CommentWriteComponent>

        <CommentListComponetent
          :comments="selectedBoardComment"
          v-if="selectedBoardComment"
        ></CommentListComponetent>
      </div>
      <b-sidebar id="sidebar-1" shadow right>
        <div class="px-3 py-2">
          <b-input-group
            prepend="수정하기"
            class="mt-3"
            v-if="editMode == false"
          >
            <b-input-group-append>
              <b-button variant="outline-success">
                <b-icon-receipt @click="editClick"></b-icon-receipt>
              </b-button>
            </b-input-group-append>
          </b-input-group>
          <div v-else>
            <b-input-group prepend="수정적용" class="mt-3">
              <b-input-group-append>
                <b-button variant="outline-success">
                  <b-icon-receipt @click="editBoard"></b-icon-receipt>
                </b-button>
              </b-input-group-append>
            </b-input-group>
            <b-input-group prepend="수정취소" class="mt-3">
              <b-input-group-append>
                <b-button variant="outline-success">
                  <b-icon-receipt @click="editClick"></b-icon-receipt>
                </b-button>
              </b-input-group-append>
            </b-input-group>
          </div>
          <b-input-group prepend="삭제하기" class="mt-3">
            <b-input-group-append>
              <b-button variant="outline-danger" @click="deleteClick">
                <b-icon-trash-fill></b-icon-trash-fill>
              </b-button>
            </b-input-group-append>
          </b-input-group>
        </div>
      </b-sidebar>
    </b-container>
    <div class="text-center" v-else>
      <b-spinner label="Spinning"></b-spinner>
      <b-spinner type="grow" label="Spinning"></b-spinner>
      <b-spinner variant="primary" label="Spinning"></b-spinner>
      <b-spinner variant="primary" type="grow" label="Spinning"></b-spinner>
      <b-spinner variant="success" label="Spinning"></b-spinner>
      <b-spinner variant="success" type="grow" label="Spinning"></b-spinner>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapGetters } from "vuex";
import CommentListComponetent from "@/components/board/CommentListComponetent.vue";
import CommentWriteComponent from "@/components/board/CommentWriteComponent.vue";

const boardStore = "boardStore";
const userStore = "userStore";
export default {
  data() {
    return {
      editMode: false,
      editTitle: "",
      editText: "",
    };
  },
  computed: {
    ...mapGetters(userStore, ["checkUserInfo"]),
    ...mapState(boardStore, ["selectedBoard", "selectedBoardComment"]),
  },
  methods: {
    ...mapActions(boardStore, [
      "getDetailBoard",
      "fileDownload",
      "deleteBoard",
      "editBoardAction",
    ]),
    editBoard() {
      const board = {
        title: this.editTitle,
        text: this.editText,
      };
      this.editBoardAction(board);
    },
    fileDownloadClick(file) {
      const fileDto = {
        no: file.no,
        originalFileName: file.originalFileName,
        saveFileName: file.saveFileName,
        saveFolder: file.saveFolder,
      };
      this.fileDownload(fileDto);
      console.log(fileDto);
    },
    editClick() {
      // console.log("수정");
      if (this.editMode) {
        this.editMode = false;
      } else {
        this.editTitle = this.selectedBoard.title;
        this.editText = this.selectedBoard.text;
        this.editMode = true;
      }
    },
    deleteClick() {
      console.log("삭제하기 클릭");
      this.deleteBoard(this.selectedBoard.no);
    },
  },
  created() {
    let boardno = this.$route.params["boardno"];
    this.getDetailBoard(boardno);
  },

  components: { CommentListComponetent, CommentWriteComponent },
};
</script>

<style scoped>
.board-detail-container {
  padding: 15px 15px 5px;
  width: 860px;
}
.header {
  text-align: center;
  font-size: 18px;
  height: 40px;
  line-height: 50px;
  font-family: "KoPubDotumMedium";
}
.board-info {
  margin-top: 10px;
  height: 30px;
  font-size: 14px;
  color: gray;
  border-bottom: 2px solid rgb(242, 242, 242);
  margin-bottom: 5px;
}
.board-info > div {
  display: flex;
}
.board-info-writer {
  float: left;
}
.board-info-date {
  float: right;
}
.board-info-label {
  margin-right: 10px;
}
/* .meta {
  text-align: left;
} */
.board-detail-content {
  text-align: left;
  /* border: 2px solid rgb(242, 242, 242); */
  height: fit-content;
  min-height: 300px;
}
.board-detail-textarea {
  padding: 20px;
}
.textarea-edit-mode {
  height: fit-content;
  min-height: 290px;
  width: 830px;
}
.board-detail-content,
.board-detail-file {
  margin: 0px 0px;
}
.board-detail-file {
  border-top: 1px solid gainsboro;
  border-bottom: 1px solid gainsboro;
  font-size: 15px;
}
.board-detail-file > .list-group-item {
  border: none;
}

.board-detail-inner-container {
  padding: 5px 0px 50px;
  border-top: 1px solid black;
  border-bottom: 1px solid gainsboro;
}

.board-container > * {
  margin: 5px;
  border-bottom: 1px solid gainsboro;
}
/* 댓글 */
.board-detail-comment {
  margin-top: 60px;
}
.board-comment-label {
  font-size: 18px;
  margin-left: 15px;
  font-family: "GongGothicMedium";
}
.comment-header {
  display: flex;
}
/* 수정버튼 */
.board-edit-btn {
  margin-top: 10px;
  text-align: right;
}
/* 사이드바 */
.b-sidebar {
  width: fit-content;
}
</style>

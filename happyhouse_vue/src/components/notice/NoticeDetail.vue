<template>
  <b-container class="notice-detail-container" v-if="selectedNotice">
    <b-row class="header">
      <b-col class="notice-no" cols="2"> {{ selectedNotice.no }} </b-col>
      <b-col cols="9" v-if="editMode">
        <b-form-input type="text" v-model="editTitle"></b-form-input>
      </b-col>
      <b-col cols="9" v-else> {{ selectedNotice.title }}</b-col>
    </b-row>
    <div class="notice-info">
      <div class="notice-info-writer">
        <p class="notice-info-label">작성자</p>
        <p>{{ selectedNotice.userName }} ( {{ selectedNotice.userId }} )</p>
      </div>
      <div class="notice-info-date">
        <p class="notice-info-label">작성일</p>
        <p>{{ selectedNotice.createDate }}</p>
      </div>
    </div>
    <div class="notice-detail-content">
      <div v-if="editMode">
        <b-form-textarea
          class="notice-detail-textarea textarea-edit-mode"
          type="textarea"
          v-model="editText"
        ></b-form-textarea>
      </div>
      <div class="notice-detail-textarea" v-else>{{ selectedNotice.text }}</div>
    </div>
    <b-col
      class="notice-edit-btn"
      v-if="checkUserInfo != null && checkUserInfo.grade == 0"
      ><b-button v-b-toggle.sidebar-1><b-icon-pencil></b-icon-pencil></b-button
    ></b-col>

    <b-sidebar
      id="sidebar-1"
      title="Sidebar"
      shadow
      right
      v-if="checkUserInfo != null && checkUserInfo.grade == 0"
    >
      <div class="px-3 py-2">
        <b-input-group prepend="수정하기" class="mt-3" v-if="editMode == false">
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
                <b-icon-receipt @click="editNotice"></b-icon-receipt>
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
</template>

<script>
import { mapState, mapActions, mapGetters } from "vuex";

const noticeStore = "noticeStore";
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
    ...mapState(noticeStore, ["selectedNotice"]),
  },
  methods: {
    ...mapActions(noticeStore, [
      "getDetailNotice",
      "fileDownload",
      "deleteNotice",
      "editNoticeAction",
    ]),
    editNotice() {
      const notice = {
        title: this.editTitle,
        text: this.editText,
      };
      this.editNoticeAction(notice);
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
      console.log("수정");
      if (this.editMode) {
        this.editMode = false;
      } else {
        this.editTitle = this.selectedNotice.title;
        this.editText = this.selectedNotice.text;
        this.editMode = true;
      }
    },
    deleteClick() {
      console.log("삭제하기 클릭");
      this.deleteNotice(this.selectedNotice.no);
    },
  },
  created() {
    let noticeno = this.$route.params["noticeno"];
    this.getDetailNotice(noticeno);
  },
};
</script>

<style scoped>
.notice-detail-container {
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
.notice-info {
  margin-top: 10px;
  height: 30px;
  font-size: 14px;
  color: gray;
  border-bottom: 2px solid rgb(242, 242, 242);
  margin-bottom: 5px;
}
.notice-info > div {
  display: flex;
}
.notice-info-writer {
  float: left;
}
.notice-info-date {
  float: right;
}
.notice-info-label {
  margin-right: 10px;
}
.content {
  text-align: left;
}
/* 내용 */
.notice-detail-content {
  text-align: left;
  height: fit-content;
  min-height: 300px;
  margin: 0px;
  padding-bottom: 10px;
  border-bottom: 1px solid gainsboro;
}
.notice-detail-textarea {
  padding: 20px;
}
.textarea-edit-mode {
  height: fit-content;
  min-height: 290px;
  width: 830px;
}

.board-container > * {
  margin: 5px;
  border-bottom: 1px solid gainsboro;
}
/* .comment-header {
  display: flex;
} */

.notice-edit-btn {
  margin-top: 10px;
  text-align: right;
}
</style>

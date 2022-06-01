import {
  noticeList,
  registNotice,
  detailNotice,
  fileDownload,
  deleteNotice,
  editNotice,
} from "@/api/notice.js";
import router from "../../router";

const noticeStore = {
  namespaced: true,
  state: {
    notices: [],
    selectedNotice: null,
    navigation: null,
  },
  getters: {
    getNoticeList: function (state) {
      return state.notices;
    },
    getSelectedNoticeNo: function (state) {
      return state.selectedNotice.no;
    },
  },
  mutations: {
    SET_NOTICE_LIST(state, notices) {
      state.notices = [];
      notices.forEach((notice) => {
        state.notices.push({
          no: notice.no,
          title: notice.title,
          userName: notice.userName,
          createDate: notice.createDate,
        });
      });
    },
    SET_SELECTED_NOTICE(state, notice) {
      state.selectedNotice = notice;
    },
    SET_NAVIGATION(state, navigation) {
      state.navigation = navigation;
    },
  },
  actions: {
    editNoticeAction({ state }, notice) {
      let token = sessionStorage.getItem("access-token");
      notice["no"] = state.selectedNotice["no"];
      console.log(notice);
      editNotice(
        notice,
        token,
        (response) => {
          console.log(response);
          if (response.data.success) {
            alert("공지사항 수정 완료");
            router.push("/notice");
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    deleteNotice(context, no) {
      let token = sessionStorage.getItem("access-token");
      deleteNotice(
        no,
        token,
        (response) => {
          console.log(response);
          if (response.data.success) {
            alert("공지사항이 삭제되었습니다.");
            router.push("/notice");
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    fileDownload(context, file) {
      fileDownload(
        file,
        (response) => {
          console.log(response);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getNotices({ commit }, pageNum) {
      if (pageNum == undefined || pageNum == null) {
        pageNum = 1;
      }
      const navigationParam = {
        curPageNum: pageNum,
        itemPerPage: 10,
        keyWord: "",
        searchWord: "",
      };
      console.log("공지사항 가져오기");
      noticeList(
        navigationParam,
        ({ data }) => {
          commit("SET_NOTICE_LIST", data["notices"]);
          commit("SET_NAVIGATION", data["navigation"]);
        },
        (e) => {
          console.log(e);
        },
      );
    },
    registerNotice(context, notice) {
      let token = sessionStorage.getItem("access-token");
      registNotice(
        notice,
        token,
        (response) => {
          router.push("/notice");
          console.log(response);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getDetailNotice({ commit }, noticeno) {
      detailNotice(
        noticeno,
        ({ data }) => {
          commit("SET_SELECTED_NOTICE", data.data);
        },
        (e) => {
          console.log(e);
        },
      );
    },
  },
};
export default noticeStore;

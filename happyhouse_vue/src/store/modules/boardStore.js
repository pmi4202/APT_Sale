import {
  boardList,
  detailBoard,
  getComment,
  registBoard,
  registComment,
  fileDownload,
  removeComment,
  deleteBoard,
  editBoard,
} from "@/api/board.js";
import router from "../../router";
const boardStore = {
  namespaced: true,
  state: {
    selectedBoard: null,
    selectedBoardComment: [],
    boards: [],
    navigation: null,
    curPageNum: 1,
    keyWord: "",
    searchWord: "",
  },
  getters: {
    getSelectedBoardNo: function (state) {
      return state.selectedBoard.no;
    },
    getBoardList: function (state) {
      return state.boards;
    },
  },
  mutations: {
    SET_BOARD_LIST(state, boards) {
      state.boards = [];
      boards.forEach((board) => {
        state.boards.push({
          no: board.no,
          title: board.title,
          userName: board.userName,
          createDate: board.createDate,
          deleted: board.deleted,
        });
      });
    },
    SET_SELECTED_BOARD(state, board) {
      state.selectedBoard = board;
    },
    SET_BOARD_COMMENT(state, comments) {
      state.selectedBoardComment = comments;
    },
    SET_NAVIGATION(state, navigation) {
      state.navigation = navigation;
    },
    SEARCH_PARAM_INIT(state) {
      state.curPageNum = 1;
      state.keyWord = "";
      state.searchWord = "";
    },
    SEARCH_PARAM_SET(state, setedData) {
      state.curPageNum = 1;
      state.keyWord = setedData.selectedOption;
      state.searchWord = setedData.selectedSearchWord;
    },
  },
  actions: {
    editBoardAction({ state }, board) {
      let token = sessionStorage.getItem("access-token");
      board["no"] = state.selectedBoard["no"];
      console.log(board);
      editBoard(
        board,
        token,
        (response) => {
          console.log(response);
          if (response.data.success) {
            alert("게시글이 수정되었습니다.");
            router.push("/board");
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    deleteBoard(context, no) {
      let token = sessionStorage.getItem("access-token");
      deleteBoard(
        no,
        token,
        (response) => {
          console.log(response);
          if (response.data.success) {
            alert("게시글이 삭제되었습니다.");
            router.push("/board");
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
    removeComment({ dispatch, getters }, no) {
      let token = sessionStorage.getItem("access-token");
      removeComment(
        no,
        token,
        (response) => {
          console.log(response);
          alert(response.data.data, ":", response.data.cnt);
          if (response.data.success) {
            dispatch("getDetailBoard", getters.getSelectedBoardNo);
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getBoards({ commit, state }, pageNum) {
      if (pageNum == undefined || pageNum == null) {
        pageNum = 1;
      }
      state.curPageNum = pageNum;
      const navigationParam = {
        curPageNum: state.curPageNum,
        itemPerPage: 10,
        keyWord: state.keyWord,
        searchWord: state.searchWord,
      };
      console.log("게시글 가져오기");
      boardList(
        navigationParam,
        ({ data }) => {
          commit("SET_BOARD_LIST", data["boards"]);
          commit("SET_NAVIGATION", data["navigation"]);
        },
        (e) => {
          console.log(e);
        },
      );
    },
    registerBoard(context, board) {
      console.log(board);
      const formData = new FormData();
      formData.append("title", board.title);
      formData.append("text", board.text);
      let token = sessionStorage.getItem("access-token");
      if (board.files) {
        for (let i = 0; i < board.files.length; i++) {
          let file = board.files[i];
          formData.append(`files`, file);
        }
      }
      registBoard(
        formData,
        token,
        (response) => {
          alert(response.data.message);
          if (response.data.success) {
            router.push("/board");
          }
          console.log(response);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    editComment({ dispatch }, comment) {
      const token = sessionStorage.getItem("access-token");
      registComment(
        comment,
        token,
        (response) => {
          console.log(response);
          if (response.data.success) {
            dispatch("getDetailBoard", comment.boardno);
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getDetailBoard({ commit }, boardno) {
      detailBoard(
        boardno,
        ({ data }) => {
          if (!data.success) {
            alert("알 수 없는 에러");
          }
          commit("SET_SELECTED_BOARD", data.board);
        },
        (e) => {
          console.log(e);
        },
      ).then(
        getComment(
          boardno,
          ({ data }) => {
            console.log(data);
            commit("SET_BOARD_COMMENT", data.data);
          },
          (error) => {
            console.log(error);
          },
        ),
      );
    },
  },
};
export default boardStore;

import jwt_decode from "jwt-decode";
import { login, findById, registUser, update, deleteUser } from "@/api/user.js";
import router from "@/router";

const userStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
  },
  getters: {
    loginState(state) {
      return state.isLogin && state.userInfo != null;
    },
    checkUserInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
  },
  actions: {
    async disjoint({ dispatch }, userId) {
      let token = sessionStorage.getItem("access-token");
      console.log(jwt_decode(token));
      if (userId != jwt_decode(token).sub) {
        alert("위험감지 탈퇴 취소");
        return;
      }
      deleteUser(
        token,
        (response) => {
          console.log(response);
          if (response.data["success"]) {
            alert("이용해주셔서 감사합니다.");
            dispatch("logout");
            router.push({ name: "main" });
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    logout({ commit }) {
      commit("SET_IS_LOGIN", false);
      commit("SET_IS_LOGIN_ERROR", false);
      commit("SET_USER_INFO", null);
      sessionStorage.removeItem("access-token");
      router.push("/");
    },
    updateUser({ dispatch }, modifyedUser) {
      update(
        modifyedUser,
        (response) => {
          console.log(response);
          if (response.data["success"]) {
            alert("수정되었습니다.");
          }
          dispatch("getUserInfo");
          router.push({ name: "mypage" });
        },
        (error) => {
          console.log(error);
        },
      );
    },
    async userLogin({ commit }, user) {
      await login(
        user,
        (response) => {
          //성공시 받아온 토큰을 세션에 저장한다.
          if (response.data.success == true) {
            console.log(response.data["msg"]);
            let jwt_token = response.data["JWT-AUTHENTICATION"];

            sessionStorage.setItem("access-token", jwt_token);
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            router.push({ name: "main" });
          } else {
            //실패시
            console.log(response.data["msg"]);
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        (errorResponse) => {
          console.log(errorResponse);
          console.log("네트워크 에러로 예상된다.");
        },
      );
    },
    getUserInfo({ commit }) {
      //유저의 상세정보를 가져오기 위해 인증 받은 토큰과 아이디를 전송한다.
      let token = sessionStorage.getItem("access-token");
      let decode_token = jwt_decode(token);
      console.log(decode_token);
      findById(
        decode_token.sub,
        (response) => {
          //성공 시 받아온 사용자의 유저데이터를 클라이언트 단에 저장한다.
          if (response.data.success === true) {
            commit("SET_USER_INFO", response.data.user);
          } else {
            console.log(response.data.message);
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    registUser({ commit }, user) {
      //애초에 로그인한 상태에서 회원가입하는 거도 이상하지만
      //혹시 모르니
      commit("SET_IS_LOGIN", false);
      commit("SET_IS_LOGIN_ERROR", false);
      commit("SET_USER_INFO", null);
      sessionStorage.removeItem("access-token");

      registUser(user, (response) => {
        console.log(response);
        if (response.data) {
          alert("회원 가입 성공");
          router.push("/user");
        } else {
          alert("회원 가입 실패");
        }
      }),
        (error) => {
          console.log(error);
        };
    },
  },
};

export default userStore;

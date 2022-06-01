import Vue from "vue";
import VueRouter from "vue-router";
import MainView from "@/views/MainView.vue";
import HouseView from "@/views/HouseView.vue";
import ChartView from "@/views/ChartView.vue";
import DustView from "@/views/DustView.vue";
import store from "@/store";
Vue.use(VueRouter);

const checkLoginState = async (to, from, next) => {
  const checkUserInfo = store.getters["userStore/checkUserInfo"];
  const getUserInfoAction = store._actions["userStore/getUserInfo"];
  //
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    //유저 정보가 없고 토큰이 있으면 서버로 부터 가져온다.
    await getUserInfoAction();
  }

  if (checkUserInfo === null || token == null) {
    alert("로그인 필요");
    router.push({ name: "signIn" });
  } else {
    next();
  }
};

const checkAdminAuthority = async (to, from, next) => {
  const checkUserInfo = store.getters["userStore/checkUserInfo"];
  const getUserInfoAction = store._actions["userStore/getUserInfo"];
  //
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    //유저 정보가 없고 토큰이 있으면 서버로 부터 가져온다.
    await getUserInfoAction();
  }

  if (checkUserInfo === null || token == null || checkUserInfo.grade != 0) {
    alert("관리자 권한이 없습니다.");
  } else {
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "main",
    component: MainView,
  },
  {
    path: "/dust",
    name: "dust",
    component: DustView,
  },
  //house code 시작
  {
    path: "/house",
    name: "house",
    beforeEnter: checkLoginState,
    component: HouseView,
  },
  //house code 끝
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/UserView.vue"),
    redirect: "/user/signin",
    children: [
      {
        path: "signin",
        name: "signIn",
        component: () => import("@/components/user/UserLogin.vue"),
      },
      {
        path: "signup",
        name: "signUp",
        component: () => import("@/components/user/UserRegister.vue"),
      },
      {
        path: "mypage",
        name: "mypage",
        beforeEnter: checkLoginState,
        component: () => import("@/components/user/UserMyPage.vue"),
      },
      {
        path: "udpate",
        name: "update",
        beforeEnter: checkLoginState,
        component: () => import("@/components/user/UserUpdate.vue"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/BoardView.vue"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardList",
        component: () => import("@/components/board/BoardList.vue"),
      },
      {
        path: "register",
        name: "boardregister",
        beforeEnter: checkLoginState,
        component: () => import("@/components/board/BoardRegister.vue"),
      },
      {
        path: "/detail/:boardno",
        name: "boardDetail",
        beforeEnter: checkLoginState,
        component: () => import("@/components/board/BoardDetail.vue"),
      },
    ],
  },
  {
    path: "/notice",
    name: "notice",
    component: () => import("@/views/NoticeView.vue"),
    redirect: "/notice/list",
    children: [
      {
        path: "list",
        name: "noticeList",
        component: () => import("@/components/notice/NoticeList.vue"),
      },
      {
        path: "register",
        name: "noticeregister",
        beforeEnter: checkAdminAuthority,
        component: () => import("@/components/notice/NoticeRegister.vue"),
      },
      {
        path: "detail/:noticeno",
        name: "noticeDetail",
        component: () => import("@/components/notice/NoticeDetail.vue"),
      },
    ],
  },
  {
    path: "/chart",
    name: "chart",
    component: ChartView,
    redirect: "/chart/home",
    children: [
      {
        path: "home",
        name: "chartHome",
        component: () => import("@/components/chart/ChartHome.vue"),
      },
      {
        path: "deal",
        name: "dealChart",
        component: () => import("@/components/chart/DealGraph.vue"),
      },
      {
        path: "like",
        name: "likeChart",
        component: () => import("@/components/chart/LikeGraph.vue"),
      },
      {
        path: "dealcount",
        name: "dealCount",
        component: () => import("@/components/chart/DealCountGraph.vue"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;

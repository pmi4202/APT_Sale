<template>
  <div id="app">
    <HeaderComponent></HeaderComponent>

    <router-view />
    <FooterComponent></FooterComponent>
  </div>
</template>
<script>
import HeaderComponent from "@/components/common/HeaderComponent.vue";
import FooterComponent from "@/components/common/FooterComponent.vue";
export default {
  components: {
    HeaderComponent,
    FooterComponent,
  },
};
</script>

<style>
/* 헤더 */
@font-face {
  font-family: "Cafe24Ohsquare";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/Cafe24Ohsquare.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "HallymGothic-Regular";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2204@1.0/HallymGothic-Regular.woff2")
    format("woff2");
  font-weight: 400;
  font-style: normal;
}
/* 메인페이지 폰트 */
@font-face {
  font-family: "GothicA1-Light";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2205@1.0/GothicA1-Light.woff2")
    format("woff2");
  font-weight: 300;
  font-style: normal;
}
/* 게시판&공지사항 폰트 */
@font-face {
  font-family: "EliceDigitalBaeum-Bd";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_elice@1.0/EliceDigitalBaeum-Bd.woff2")
    format("woff2");
  font-weight: normal;
  font-style: normal;
}
/* 게시판&공지사항 상세페이지 폰트 */
@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);
@font-face {
  font-family: "KoPubDotumMedium";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/KoPubDotumMedium.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "GongGothicMedium";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10@1.0/GongGothicMedium.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
/* 회원가입&로그인 폰트 */
@font-face {
  font-family: "KoPubDotumMedium";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/KoPubDotumMedium.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
/* 대기 */
@font-face {
  font-family: "NanumSquareRound";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/NanumSquareRound.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "S-CoreDream-3Light";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-3Light.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
html,
body {
  height: 100%;
  margin: 0;
  box-sizing: border-box;
  overflow: overlay;
}
body::-webkit-scrollbar {
  width: 1vw;
  background-color: hsla(0, 0%, 42%, 0);
  border-radius: 100px;
}
#app {
  position: relative;
  /* padding-bottom: 114px; */
  min-height: 100%;
}
.content {
  min-height: 100%;
}
/* .footer {
  position: relative;
  bottom: 0;
} */
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>

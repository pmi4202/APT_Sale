<template>
  <div class="main-container">
    <div class="main-content-left">
      <div class="main-content-left-inner">
        <div class="main-content-msg">
          <div class="main-content-title">어떤 집을 찾고 계신가요?</div>
          <div class="main-content-body">
            이제, <span>HappyHouse</span>에서 빠르고 쉽게 당신의 집을
            찾아보세요. <br />매매정보부터 상권정보까지 모두 확인해볼 수
            있습니다.
          </div>
        </div>
        <button
          class="main-content-btn"
          @click="$router.push({ name: 'house' })"
        >
          집 보러가기
        </button>
      </div>
    </div>
    <div class="main-content-right">
      <img
        src="@/assets/mainImg.jpg"
        alt="이미지없음"
        width="500px"
        height="250px"
      />
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
@font-face {
  font-family: "ChosunGu";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.0/ChosunGu.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
.main-container {
  width: 1200px;
  height: 74vh;
  min-height: 550px;
  margin: auto;
  display: flex;
}
.main-content-left {
  width: 600px;
}
.main-content-right {
  width: 600px;
  padding-top: 15vh;
}
.main-content-left-inner {
  width: 400px;
  margin: 20vh 50px auto auto;
}
.main-content-msg {
  text-align: left;
  height: 150px;
}
.main-content-title {
  font-family: "ChosunGu";
  line-height: 60px;
  font-size: 32px;
}
.main-content-body {
  font-family: "GothicA1-Light";
  font-size: 13px;
  line-height: 20px;
}
.main-content-body span {
  color: #e74c3c;
  font-weight: bold;
  padding: 0px 2px;
}
.main-content-btn {
  font-family: "GongGothicMedium";
  font-size: 13px;
  border-radius: 4px;
  background-color: rgb(52, 152, 219);
  color: white;
  border: none;
  width: 90px;
  height: 35px;
  line-height: 35px;
}
</style>

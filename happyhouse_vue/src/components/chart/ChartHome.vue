<template>
  <b-container>
    <div class="chart-home">
      <div class="main">
        <b-card
          :img-src="chartImg3"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 20rem"
          class="mb-2"
        >
          <div class="card-infos">
            <div class="card-title">거래액</div>
            <b-card-text> 최고 거래액 상위 그래프틀 보여줍니다.</b-card-text>
          </div>
          <button class="move-btn" @click="$router.push({ name: 'dealChart' })">
            이동
          </button>
        </b-card>
        <b-card
          :img-src="chartImg2"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 20rem"
          class="mb-2"
        >
          <div class="card-infos">
            <div class="card-title">좋아요</div>
            <b-card-text>
              사용자들이 좋아요를 많이한 순으로 보여줍니다.
            </b-card-text>
          </div>

          <button class="move-btn" @click="$router.push({ name: 'likeChart' })">
            이동
          </button>
        </b-card>

        <b-card
          :img-src="chartImg1"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 20rem"
          class="mb-2"
        >
          <div class="card-infos">
            <div class="card-title">거래량</div>
            <b-card-text> 거래량이 많은 하우스를 보여줍니다. </b-card-text>
          </div>
          <button class="move-btn" @click="$router.push({ name: 'dealCount' })">
            이동
          </button>
        </b-card>
      </div>
    </div>
  </b-container>
</template>

<script>
export default {
  data() {
    return {
      chartImg1: require("@/assets/chartimage.png"),
      chartImg2: require("@/assets/chartImage2.jpg"),
      chartImg3: require("@/assets/chartImage3.png"),
    };
  },
};
</script>

<style scoped>
img {
  width: 230px;
  height: 150px;
}
article {
  width: 232px;
}
.card-body {
  height: 150px;
  padding: 15px;
}
.card-infos {
  height: 90px;
}
.card-title {
  font-family: "KoPubDotumMedium";
  font-size: 17px;
}
.card-text {
  font-size: 12px;
}

.chart-home {
  width: 750px;
  padding: 10px;
  margin: auto;
}
.back-img {
  opacity: 90%;
  position: absolute;
}
.main {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
}
.move-btn {
  width: 100px;
  height: 35px;
  background-color: rgb(52, 152, 219);
  color: white;
  border: none;
}
</style>

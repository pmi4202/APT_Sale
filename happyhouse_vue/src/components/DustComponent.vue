<template>
  <div v-if="datas && datas.length > 0" class="dust-box">
    <select class="region-select" v-model="selectSiName">
      <option
        v-for="region in regions"
        :key="region.value"
        :value="region.value"
      >
        {{ region.text }}
      </option>
    </select>
    <button class="region-search-btn" @click="getDustInfos">검색</button>
    <div class="meta-header">
      {{ selectSiName }}&nbsp;{{ datas[0].dataTime }} 검색결과
    </div>
    <!-- <div class="dust-list-box">
      <table class="dust-list-table">
        <thead>
          <b-row>
            <td>지역명</td>
            <td>통합수치</td>
          <b-row/>
        </thead>
        <tbody>
          <tr v-for="(data, idx) in datas" :key="idx" class="dust-list">
            <td>{{ data.stationName }}</td>
            <td>
              <b-button
                v-b-toggle.sidebar-right
                class="khai-circle"
                @click="dustClick(idx)"
                v-bind:style="{
                  background: color[data.khaiGrade - 1],
                }"
                >{{ data.khaiValue }}
              </b-button>
            </td>
          </tr>
        </tbody>
      </table>
    </div> -->

    <b-list-group class="dust-list-box">
      <b-list-group-item
        v-for="(data, idx) in datas"
        :key="idx"
        class="dust-list"
      >
        <div class="dust-list-item">
          <div class="dust-list-item-text">
            {{ data.stationName }}
          </div>
        </div>
        <div class="dust-list-item">
          <b-button
            v-b-toggle.sidebar-right
            class="khai-circle"
            @click="dustClick(idx)"
            v-bind:style="{
              background: color[data.khaiGrade - 1],
            }"
            >{{ data.khaiValue }}
          </b-button>
        </div>
      </b-list-group-item>
    </b-list-group>

    <b-sidebar id="sidebar-right" right shadow>
      <div class="px-3 py-2">
        <b-list-group>
          <b-list-group-item href="#some-link"
            >통합 대기 환경 수치 :
            <span :style="textcolor[`${datas[selectedIdx].khaiGrade}`]">{{
              text[datas[selectedIdx].khaiGrade]
            }}</span></b-list-group-item
          >
          <b-list-group-item href="#foobar" disabled
            >아황산 가스 :
            <span :style="textcolor[`${datas[selectedIdx].so2Grade}`]">{{
              text[datas[selectedIdx].so2Grade]
            }}</span>
          </b-list-group-item>
          <b-list-group-item href="#foobar" disabled
            >일산화 지수 :
            <span :style="textcolor[`${datas[selectedIdx].coGrade}`]">{{
              text[datas[selectedIdx].coGrade]
            }}</span>
          </b-list-group-item>
          <b-list-group-item href="#foobar" disabled
            >오존 지수 :
            <span :style="textcolor[`${datas[selectedIdx].o3Grade}`]">{{
              text[datas[selectedIdx].o3Grade]
            }}</span>
          </b-list-group-item>
          <b-list-group-item href="#foobar" disabled
            >이산화질소 지수 :
            <span :style="textcolor[`${datas[selectedIdx].no2Grade}`]">{{
              text[datas[selectedIdx].no2Grade]
            }}</span>
          </b-list-group-item>
          <b-list-group-item href="#foobar" disabled
            >미세먼지(PM10) :
            <span :style="textcolor[`${datas[selectedIdx].pm10Grade}`]">{{
              text[datas[selectedIdx].pm10Grade]
            }}</span>
          </b-list-group-item>
        </b-list-group>
      </div>
    </b-sidebar>
  </div>
  <div v-else>
    <b-alert show>대기 정보를 불러 올 수 없습니다.</b-alert>
  </div>
</template>

<script>
import { getDustInfo } from "@/api/statistic.js";
export default {
  data() {
    return {
      selectSiName: "서울",
      datas: [],
      selectedIdx: 0,
      color: [
        "rgb(33, 136, 214)",
        "rgb(135, 220, 99)",
        "rgb(154, 174, 40)",
        "rgb(124, 36, 36)",
      ],
      text: ["좋음", "보통", "나쁨", "매우나쁨"],
      textcolor: ["color:green", "color:blue", "color:#F14A00", "color:red"],
      regions: [
        { value: "서울", text: "서울특별시" },
        { value: "부산", text: "부산광역시" },
        { value: "대구", text: "대구광역시" },
        { value: "인천", text: "인천광역시" },
        { value: "광주", text: "광주광역시" },
        { value: "대전", text: "대전광역시" },
        { value: "울산", text: "울산광역시" },
        { value: "경기", text: "경기도" },
        { value: "강원", text: "강원도" },
        { value: "충북", text: "충청북도" },
        { value: "충남", text: "충청남도" },
        { value: "전북", text: "전라북도" },
        { value: "전남", text: "전라남도" },
        { value: "경북", text: "경상북도" },
        { value: "경남", text: "경상남도" },
        { value: "제주", text: "제주특별자치도" },
        { value: "세종", text: "세종특별자치시" },
      ],
    };
  },
  methods: {
    getDustInfos() {
      getDustInfo(
        this.selectSiName,
        ({ data }) => {
          console.log(data);
          this.datas = data;
        },
        (error) => {
          console.log(error);
        },
      );
    },
    dustClick(idx) {
      this.selectedIdx = idx;
    },
  },
  created() {
    this.selectSiName = "서울";
    this.getDustInfos();
  },
};
</script>

<style scoped>
.region-select {
  height: 35px;
  width: 250px;
  line-height: 35px;
  padding-left: 10px;
  border-radius: 5px;
  font-family: "NanumSquareRound";
}
.region-search-btn {
  height: 35px;
  width: 100px;
  font-size: 13px;
  margin-left: 5px;
  background-color: rgb(52, 152, 219);
  color: white;
  border: none;
  border-radius: 3px;
}
/* 검색결과 */
.meta-header {
  font-family: "S-CoreDream-3Light";
  font-size: 14px;
  height: 50px;
  line-height: 50px;
}
.dust-list-box {
  overflow-y: auto;
  width: 700px;
  max-height: 40vh;
  margin: auto;
}
.dust-list {
  line-height: 50px;
  height: 50px;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  grid-template-columns: 1fr 1fr;
  border-left: none;
  border-right: none;
  font-family: "NanumSquareRound";
}
.dust-list-item {
  width: 50%;
}
.dust-list-item-text {
  line-height: 50px;
}
.khai-circle {
  border: none;
  border-radius: 50%;
  height: 40px;
  width: 40px;
  font-weight: bold;
  font-size: 14px;
  color: white;
}
.dust-box {
  margin: 10px;
  /* max-height: 55vh; */
  /* overflow-y: scroll; */
}
</style>

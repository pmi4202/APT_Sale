<template>
  <div class="house-list-container">
    <div v-if="houses.length == 0">
      <br />
      <p>검색 결과가 없습니다...</p>
    </div>
    <div v-else>
      <div class="house-element" v-for="(house, index) in houses" :key="index">
        <b-button
          :id="house.aptCode"
          :class="{
            'modal-btn': true,
            selected: house.aptCode == selectedHouse,
          }"
          variant="link"
          v-b-modal.modal-multi-1
          @click="showDetail(house.aptCode)"
        >
          <div class="list-house-infos">
            <div class="list-house-name">{{ house.aptName }}</div>
            <div class="list-house-info">
              {{ house.dongName }} {{ house.jibun }}, {{ house.buildYear }}년
            </div>
          </div>
          <div class="like-btn">
            <img
              :src="likeImgs[`${house.liked}`]"
              alt="이미지없음"
              width="30px"
              height="33px"
              @click.stop="clickChangeLike(house.aptCode, index)"
            />
          </div>
        </b-button>
      </div>
    </div>

    <b-modal
      id="modal-multi-1"
      size="md"
      :title="house.houseInfo.aptName"
      hide-footer
      no-stacking
    >
      <div class="my-modal">
        <div class="house-info">
          <img
            width="200px"
            heigth="400px"
            src="@/assets/apt.png"
            alt="이미지 없음"
          />
          <div class="house-info-list">
            <ul>
              <li>{{ house.houseInfo.aptName }}</li>
              <li>{{ house.houseInfo.dongName }}</li>
              <li>{{ house.houseInfo.buildYear }}년 준공</li>
              <li>지번: {{ house.houseInfo.jibun }}</li>
            </ul>
          </div>
        </div>
        <div class="line"></div>
        <div class="house-deal-info">
          <p class="house-deal-info-menu">거래정보</p>
          <table class="house-detail-table">
            <thead>
              <tr>
                <th>계약일</th>
                <th>가격</th>
                <th>층</th>
                <th>면적</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(deal, i) in dealList" :key="i">
                <td>
                  {{ deal.dealYear }}.{{ deal.dealMonth }}.{{ deal.dealDay }}
                </td>
                <td>{{ deal.dealAmount }}</td>
                <td>{{ deal.floor }}</td>
                <td>{{ deal.area }}</td>
              </tr>
            </tbody>
          </table>
          <div
            v-if="house.houseDealInfo.length > 3"
            class="list-open-btn-container"
          >
            <button class="list-open-btn" @click="changeListSize">
              {{ btnMsg[`${is_list_open}`] }}
            </button>
          </div>
        </div>
        <div class="line"></div>
        <div class="commercial-area-info">
          <p class="house-deal-info-menu">상권정보</p>
          <table class="commercial-area-info-nav">
            <thead>
              <tr>
                <td
                  :class="{
                    'market-selected': selectedMarketName == '편의점/마트',
                  }"
                  @click="clickMarketCategory('편의점/마트')"
                >
                  <p>편의점/마트</p>
                </td>
                <td
                  :class="{
                    'market-selected': selectedMarketName == '운동시설',
                  }"
                  @click="clickMarketCategory('운동시설')"
                >
                  <p>운동시설</p>
                </td>
                <td
                  :class="{
                    'market-selected': selectedMarketName == '기타',
                  }"
                  @click="clickMarketCategory('기타')"
                >
                  <p>기타</p>
                </td>
              </tr>
            </thead>
          </table>
          <table class="commercial-area-info-list">
            <thead>
              <tr>
                <th>업종</th>
                <th>가게명</th>
              </tr>
            </thead>
            <tbody v-if="selectedMarket.length > 0">
              <tr v-for="(market, i) in selectedMarket" :key="i">
                <td>{{ market.categoryName }}</td>
                <td>{{ market.marketName }}</td>
              </tr>
            </tbody>
            <tbody v-else>
              <tr>
                <td colspan="2">가게가 존재하지 않습니다...</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </b-modal>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
const houseStore = "houseStore";

export default {
  name: "HouseList",
  props: ["selectedHouse"],
  data() {
    return {
      is_show: false,
      likeImgs: {
        true: require("@/assets/like.png"),
        false: require("@/assets/unlike.png"),
      },
      selectedMarket: [],
      selectedMarketName: "편의점/마트",
      is_list_open: false,
      dealList: [],
      btnMsg: { true: "접기", false: "더보기" },
    };
  },
  watch: {
    selectedHouse() {
      document
        .getElementById(this.selectedHouse)
        .scrollIntoView({ behavior: "smooth" });
    },
    house() {
      this.getMarketList(this.house.houseInfo.dongCode);
      this.selectedMarket = this.marketList["편의점/마트"];
      this.dealList = this.house.houseDealInfo.slice(
        0,
        Math.min(3, this.house.houseDealInfo.length),
      );
    },
  },
  computed: {
    ...mapState(houseStore, ["houses", "house", "is_attention", "marketList"]),
  },
  methods: {
    ...mapActions(houseStore, [
      "getHouse",
      "likeRegist",
      "likeCancel",
      "getMarketList",
    ]),
    showDetail(aptCode) {
      this.getHouse(aptCode);
      this.selectedMarketName = "편의점/마트";
    },
    clickChangeLike(aptCode, i) {
      // alert(aptCode);
      if (this.houses[i].liked) {
        const isAttention = this.is_attention;
        this.likeCancel({ aptCode, i, isAttention });
      } else {
        this.likeRegist({ aptCode, i });
      }
    },
    clickMarketCategory(categoryName) {
      this.selectedMarketName = categoryName;
      this.selectedMarket = this.marketList[categoryName];
    },
    changeListSize() {
      this.is_list_open = !this.is_list_open;
      if (this.is_list_open) {
        //더보기 : 최대 10개
        this.dealList = this.house.houseDealInfo.slice(
          0,
          Math.min(10, this.house.houseDealInfo.length),
        );
      } else {
        //접기
        this.dealList = this.house.houseDealInfo.slice(0, 3);
      }
    },
  },
};
</script>

<style scoped>
.house-list-container {
  height: 100%;
  width: 100%;
  overflow-y: auto;
}
.house-element {
  text-align: left;
  height: 60px;
}
.house-element:hover {
  background-color: rgba(61, 115, 151, 0.04);
}
.list-house-item {
  padding: 0 20px;
}
.list-house-name {
  height: 35px;
  line-height: 45px;
  font-size: 16px;
}
.list-house-info {
  height: 25px;
  font-size: 13px;
  color: gray;
}
.list-house-infos {
  width: 300px;
}
.like-btn {
  height: 60px;
  width: 40px;
  line-height: 60px;
  text-align: center;
}
/* 팝업창 */
.modal-btn {
  width: 100%;
  height: 100%;
  padding: 0 20px;
  margin: 0;
  text-align: left;
  color: black;
  border: 0;
  outline: none;
  text-decoration: none;
  display: flex;
}
.modal-btn:hover,
.modal-btn:active,
.modal-btn:visited,
.modal-btn:focus,
.modal-btn::after {
  color: black;
  text-decoration: none;
  border: 0;
  outline: none;
  box-shadow: none;
}
.my-modal {
  height: 500px;
  overflow-y: scroll;
  cursor: pointer;
}
/* 구분선*/
.line {
  margin: 30px 0px;
  height: 1px;
  background-color: rgb(241, 241, 241);
}
/* 아파트 정보 */
.house-deal-info-menu {
  font-family: "GongGothicMedium";
  /* font-weight: bold; */
  font-size: 16px;
}
.house-info {
  display: flex;
  padding: 25px 10px 0px;
  margin: 0;
}
.house-info-list {
  font-family: "GothicA1-Light";
  padding: 5px 0px;
  font-size: 15px;
}
.house-info-list li {
  margin: 10px 0px;
}
.modal-body {
  padding: 0rem;
}
/* 아파트 거래 정보 */
.house-deal-info {
  padding: 0px 35px;
}
.house-detail-table {
  border-collapse: collapse;
  margin: auto;
  font-size: 14px;
}
.house-detail-table > thead {
  background-color: rgb(61, 115, 151);
  color: white;
}
.house-detail-table tr,
.house-detail-table td {
  width: 103px;
  height: 30px;
  text-align: center;
  border-collapse: collapse;
}
.house-detail-table td {
  border-bottom: 1px solid lightgray;
}
.list-open-btn-container {
  text-align: center;
}
.list-open-btn {
  background-color: rgba(61, 115, 151, 0.116);
  border: none;
  border-radius: 20px;
  width: 100px;
  height: 35px;
  font-size: 14px;
  margin: 15px auto 5px;
}
/* 상권정보 */
.market-selected p {
  /* background-color: rgba(61, 115, 151, 0.116); */
  border: 1px solid rgb(61, 115, 151);
  color: rgb(61, 115, 151);
  border-radius: 10px;
}
.commercial-area-info {
  padding: 0px 35px;
  height: 300px;
  padding-bottom: 15px;
}
.commercial-area-info-nav {
  margin: 10px, auto;
}
.commercial-area-info-nav td {
  height: 35px;
  width: 150px;
  text-align: center;
  padding: 3px;
}
.commercial-area-info-nav td p {
  margin: 0px;
  height: 100%;
  line-height: 35px;
}
.commercial-area-info-list {
  font-size: 14px;
  margin: 10px auto;
  border-collapse: collapse;
}
.commercial-area-info-list th {
  background-color: rgb(61, 115, 151);
  color: white;
}
.commercial-area-info-list td {
  border-bottom: 1px solid lightgray;
}
.commercial-area-info-list th,
.commercial-area-info-list td {
  width: 200px;
  height: 30px;
  text-align: center;
  border-collapse: collapse;
}
.commercial-area-info-list tbody {
  text-align: center;
}
/* 마커 => 리스트 하이라이트 */
.selected {
  background-color: rgba(61, 115, 151, 0.116);
}
</style>

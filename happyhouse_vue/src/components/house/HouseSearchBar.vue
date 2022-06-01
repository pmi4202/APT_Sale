<template>
  <div class="house-search-bar-container">
    <div class="search-bar-selects">
      <select v-model="sidoCode" @change="gugunList">
        <option v-for="sido in sidos" :key="sido.value" :value="sido.value">
          {{ sido.text }}
        </option>
      </select>
      <select v-model="gugunCode" @change="dongList">
        <option v-for="gugun in guguns" :key="gugun.value" :value="gugun.value">
          {{ gugun.text }}
        </option>
      </select>
      <select v-model="dongCode">
        <option v-for="dong in dongs" :key="dong.value" :value="dong.value">
          {{ dong.text }}
        </option>
      </select>
    </div>
    <div>
      <button class="search-bar-btn" @click="houseSearch">검색하기</button>
    </div>
    <div>
      <button class="search-bar-btn" @click="likeHouseSearch">
        관심 등록한 아파트 보기
      </button>
    </div>
    <div class="search-bar-msg">{{ msg }}</div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      msg: "원하는 지역을 선택하세요.",
    };
  },
  computed: { ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]) },
  created() {
    this.getSidoList();
    this.clearGugunList();
    this.clearDongList();
    this.SET_IS_NOT_ATTENTION();
  },
  methods: {
    ...mapActions(houseStore, [
      "getSidoList",
      "getGugunList",
      "getDongList",
      "getHouseList",
      "clearGugunList",
      "clearDongList",
      "getLikeList",
      // "getLikeListByHouses",
    ]),
    ...mapMutations(houseStore, ["SET_IS_ATTENTION", "SET_IS_NOT_ATTENTION"]),
    gugunList() {
      this.gugunCode = null;
      this.dongCode = null;
      if (this.sidoCode) this.getGugunList(this.sidoCode);
    },
    dongList() {
      this.dongCode = null;
      if (this.gugunCode) this.getDongList(this.gugunCode);
    },
    houseSearch() {
      this.SET_IS_NOT_ATTENTION();
      let code = this.dongCode;
      if (this.sidoCode == null) {
        alert("시/도를 선택해주세요.");
        return;
      } else {
        //검색한 시도 이름 세팅
        this.sidos.forEach((sido) => {
          if (this.sidoCode == sido.value) {
            this.msg = sido.text;
          }
        });
      }

      if (this.gugunCode == null) {
        code = this.sidoCode.substr(0, 2);
      } else {
        //검색한 구군 이름 세팅
        this.guguns.forEach((gugun) => {
          if (this.gugunCode == gugun.value) {
            this.msg = this.msg + " " + gugun.text;
          }
        });
        //
        if (this.dongCode == null) {
          code = this.gugunCode.substr(0, 5);
        } else {
          //검색한 동 이름 세팅
          this.dongs.forEach((dong) => {
            if (this.dongCode == dong.value) {
              this.msg = this.msg + " " + dong.text;
            }
          });
        }
      }
      this.msg = this.msg + " 검색결과";
      this.getHouseList(code);
    },
    likeHouseSearch() {
      this.SET_IS_ATTENTION();
      this.msg = "관심 등록한 아파트 검색 결과";
      //드롭바 리셋
      this.sidoCode = null;
      this.gugunCode = null;
      this.dongCode = null;
      this.clearGugunList();
      this.clearDongList();
      //좋아요 목록을 houses로 불러옴
      this.getLikeList();
    },
  },
};
</script>

<style scoped>
.house-search-bar-container {
  background-color: rgb(61, 115, 151);
  height: 100%;
  padding: 5px;
}
.search-bar-selects > select {
  height: 40px;
  width: 115px;
  margin: 5px;
  border-radius: 5px;
  font-size: 13px;
}
.search-bar-selects {
  margin-bottom: 3px;
}
.search-bar-btn {
  height: 35px;
  width: 370px;
  font-size: 13px;
  margin: 7px auto;
  background-color: rgb(52, 152, 219);
  color: white;
  border: none;
  border-radius: 3px;
}
.search-bar-msg {
  color: white;
  font-size: 15px;
  height: 40px;
  line-height: 40px;
}
</style>

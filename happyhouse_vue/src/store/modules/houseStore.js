import {
  sidoList,
  gugunList,
  dongList,
  houseList,
  houseDetail,
  likeListOfUser,
  like,
  unlike,
  marketList,
} from "@/api/house.js";

const houseStore = {
  namespaced: true,
  state: {
    is_attention: false, //관심지역리스트인가?
    sidos: [{ value: null, text: "시/도" }],
    guguns: [{ value: null, text: "구/군" }],
    dongs: [{ value: null, text: "동" }],
    houses: [],
    house: {
      houseInfo: {
        aptCode: 0,
        aptName: "",
        dongCode: "",
        dongName: "",
        buildYear: 0,
        jibun: "",
        lat: "",
        lng: "",
        img: null,
        liked: false,
      },
      houseLikeCount: 0,
      houseDealInfo: [],
    },
    likeList: [],
    marketList: { "편의점/마트": [], 운동시설: [], 기타: [] },
  },
  getters: {},
  mutations: {
    SET_IS_ATTENTION(state) {
      state.is_attention = true;
    },
    SET_IS_NOT_ATTENTION(state) {
      state.is_attention = false;
    },
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.sidoCode, text: gugun.sidoName });
      });
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "시/도" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "구/군" }];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [{ value: null, text: "동" }];
    },
    CLEAR_HOUSE_LIST: (state) => {
      state.houses = [];
    },
    SET_HOUSE: (state, house) => {
      state.house = house;
    },
    //좋아요 시작
    SET_LIKE_LIST: (state, likeList) => {
      likeList.forEach((like) => {
        state.houses.push({
          aptCode: like.aptCode,
          aptName: like.aptName,
          dongCode: like.dongCode,
          dongName: like.dongName,
          buildYear: like.buildYear,
          jibun: like.jibun,
          lat: like.lat,
          lng: like.lng,
          img: like.img,
          liked: true,
        });
      });
      state.likeList = likeList;
    },
    CLEAR_LIKE_LIST: (state) => {
      state.houses = [];
    },
    LIKE_REGIST: (state, i) => {
      state.houses[i].liked = true;
      state.houses = [...state.houses];
    },
    LIKE_CANCEL: (state, i) => {
      state.houses[i].liked = false;
      state.houses = [...state.houses];
    },
    DELETE_ONE_HOUSE: (state, i) => {
      state.houses.splice(i, 1);
      state.houses = [...state.houses];
    },
    SET_MARKET_LIST: (state, { markets, mainName, categoryName }) => {
      for (let i = 0; i < Math.min(1, markets.length); i++) {
        state.marketList[mainName].push({
          categoryName,
          marketName: markets[i].marketName,
        });
      }
      // markets.forEach((market) => {
      //   state.marketList[mainName].push({
      //     categoryName,
      //     marketName: market.marketName,
      //   });
      // });
    },
    CLEAR_MARKET_LIST: (state) => {
      state.marketList = { "편의점/마트": [], 운동시설: [], 기타: [] };
    },
  },
  actions: {
    getSidoList({ commit }) {
      sidoList(
        ({ data }) => {
          // console.log(data.regcodes);
          commit("CLEAR_SIDO_LIST");
          commit("SET_SIDO_LIST", data.regcodes);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getGugunList({ commit }, sidoCode) {
      const params = { sidocode: sidoCode.substr(0, 2) };
      gugunList(
        params,
        ({ data }) => {
          // console.log(data.regcodes);
          commit("CLEAR_GUGUN_LIST");
          commit("SET_GUGUN_LIST", data.regcodes);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getDongList({ commit }, gugunCode) {
      const params = { guguncode: gugunCode.substr(0, 5) };
      dongList(
        params,
        ({ data }) => {
          // console.log(data.regcodes);
          commit("CLEAR_DONG_LIST");
          commit("SET_DONG_LIST", data.regcodes);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getHouseList({ commit }, code) {
      // console.log(code);
      const params = { code: code };
      houseList(
        params,
        ({ data }) => {
          // console.log(data.regcodes);
          commit("CLEAR_HOUSE_LIST");
          commit("SET_HOUSE_LIST", data.regcodes);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getHouse({ commit }, aptCode) {
      const params = { aptCode: aptCode };
      houseDetail(
        params,
        ({ data }) => {
          // console.log(data);
          commit("SET_HOUSE", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    clearGugunList({ commit }) {
      commit("CLEAR_GUGUN_LIST");
    },
    clearDongList({ commit }) {
      commit("CLEAR_DONG_LIST");
    },
    clearHouse({ commit }) {
      commit("CLEAR_HOUSE_LIST");
    },
    getLikeList({ commit }) {
      likeListOfUser(
        ({ data }) => {
          // console.log(data.regcodes);
          commit("CLEAR_LIKE_LIST");
          commit("SET_LIKE_LIST", data.regcodes);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    likeRegist({ commit }, { aptCode, i }) {
      const params = { aptcode: aptCode };
      like(
        params,
        () => {
          //state.houses에서 i번째를 true로
          commit("LIKE_REGIST", i);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    likeCancel({ commit }, { aptCode, i, isAttention }) {
      const params = { aptcode: aptCode };
      unlike(
        params,
        () => {
          if (isAttention) {
            //관심지역이면 house 리스트에서 없애기
            commit("DELETE_ONE_HOUSE", i);
          } else {
            //검색한 지역이면 true => false로 전환
            commit("LIKE_CANCEL", i);
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    //상권정보
    /*
    필요한 상권정보만 가져오자..
    1) 편의점/마트
    D03A01 : 편의점 / D03A02 : 슈퍼마켓 / D03A04 : 생활용품 판매 / D03B01 : 백화점 / D03B04 : 시장/종합상가
    2) 운동시설
    N04A03 : 실외야구연습장 / N04A07 : 스케이트장 / N07A02 : 낚시터 / P01A07 : 프로야구 / P01A09 : 프로축구 
    P01A10 : 프로골프 / P02A01 : 체육관 / P02A02 : 실내테니스장 / P02A03 : 실내수영장 / P02A10 : 실내골프연습장
    P03A03 : 축구장 / P03A04 : 스키장 / P03A09 : 골프장 / P03A10 : 테니스장 / P03A11 : 수영장
    3) 그 외
    D13A01 : 서점
    F02A01 : 세탁소/빨래방
    R08A02 : 어린이집
    */
    getMarketList({ commit }, dongcode) {
      dongcode = dongcode.substr(0, 8); //TODO: 지우기 (Spring에서 처리)
      const markets = [
        { key: "D03A01", value: "편의점" },
        { key: "D03A02", value: "슈퍼마켓" },
        { key: "D03A04", value: "생활용품 판매" },
        { key: "D03B01", value: "백화점" },
        { key: "D03B04", value: "시장/종합상가" },
      ];
      const exercises = [
        { key: "N04A03", value: "실외야구연습장" },
        { key: "N04A07", value: "스케이트장" },
        { key: "N07A02", value: "낚시터" },
        { key: "P01A07", value: "프로야구" },
        { key: "P01A09", value: "프로축구" },
        { key: "P01A10", value: "프로골프" },
        { key: "P02A01", value: "체육관" },
        { key: "P02A02", value: "실내테니스장" },
        { key: "P02A03", value: "실내수영장" },
        { key: "P02A10", value: "실내골프연습장" },
        { key: "P03A03", value: "축구장" },
        { key: "P03A04", value: "스키장" },
        { key: "P03A09", value: "골프장" },
        { key: "P03A10", value: "테니스장" },
        { key: "P03A11", value: "수영장" },
      ];
      const etcs = [
        { key: "D13A01", value: "서점" },
        { key: "F02A01", value: "세탁소/빨래방" },
        { key: "R08A02", value: "어린이집" },
      ];

      commit("CLEAR_MARKET_LIST");
      markets.forEach((market) => {
        const params = { dongCode: dongcode, categorycode: market.key };
        // console.log(market);
        marketList(
          params,
          ({ data }) => {
            // console.log(data.markets);
            commit("SET_MARKET_LIST", {
              markets: data.markets,
              mainName: "편의점/마트",
              categoryName: market.value,
            });
          },
          (error) => {
            console.log(error);
          },
        );
      });
      //
      exercises.forEach((exercise) => {
        const params = { dongCode: dongcode, categorycode: exercise.key };
        marketList(
          params,
          ({ data }) => {
            // console.log(data);
            commit("SET_MARKET_LIST", {
              markets: data.markets,
              mainName: "운동시설",
              categoryName: exercise.value,
            });
          },
          (error) => {
            console.log(error);
          },
        );
      });
      //
      etcs.forEach((etc) => {
        const params = { dongCode: dongcode, categorycode: etc.key };
        marketList(
          params,
          ({ data }) => {
            // console.log(data);
            commit("SET_MARKET_LIST", {
              markets: data.markets,
              mainName: "기타",
              categoryName: etc.value,
            });
          },
          (error) => {
            console.log(error);
          },
        );
      });
    },
  },
};

export default houseStore;

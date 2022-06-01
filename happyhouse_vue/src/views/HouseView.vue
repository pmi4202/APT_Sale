<template>
  <div class="house-container">
    <div class="map">
      <div :style="mapSize" id="map"></div>
      <!-- <button @click="displayMarker">버튼</button> -->
    </div>
    <div class="search-module">
      <div class="search-bar"><house-search-bar></house-search-bar></div>
      <div :style="heightSize" class="search-list">
        <house-list :selectedHouse="selectedHouse"></house-list>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";

const houseStore = "houseStore";

export default {
  name: "HouseView",
  components: { HouseSearchBar, HouseList },
  data() {
    return {
      mapSize:
        "width: " +
        (screen.width - 400) +
        "px; height: " +
        (screen.height - 205) +
        "px;",
      heightSize: "height: " + (screen.height - 205 - 200) + "px;",
      places: [],
      markers: [],
      selectedMarker: null,
      selectedHouse: -1,
    };
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
  },
  watch: {
    houses() {
      this.selectedMarker = null;
      this.selectedHouse = -1;
      if (this.houses.length == 0) {
        return;
      }
      this.places = [];
      this.houses.forEach((house) => {
        this.places.push(house); //lat, lng
      });
      this.displayMarker(this.places);
    },
  },
  methods: {
    ...mapActions(houseStore, ["clearHouse"]),
    initMap() {
      //지도 띄우는 코드
      const container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      const options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
        level: 3, //지도의 레벨(확대, 축소 정도)
      };

      this.map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
    },

    displayMarker(places) {
      //markers배열에 생성할 marker길이만큼 map 자료형으로 초기화
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => {
          marker.setMap(null);
        });
      }

      const positions = []; //위도, 경도
      places.forEach((place) => {
        positions.push(new kakao.maps.LatLng(place.lat, place.lng));
      });

      var selectedImage = this.createMarkerImage(
        require("@/assets/selected.png"),
      );
      if (positions.length > 0) {
        this.markers = [];
        for (let i = 0; i < positions.length; i++) {
          const marker = new kakao.maps.Marker({
            map: this.map,
            position: positions[i],
            // image: selectedImage,
          });
          //마커에 표시할 infowindow
          // var infowindow = this.getInfoWindow(places[i]);
          kakao.maps.event.addListener(marker, "click", () => {
            // console.log(this.selectedMarker);
            if (!this.selectedMarker || this.selectedMarker !== marker) {
              if (this.selectedMarker) {
                //선택된 마커가 있었다면 이건 기본이미지로
                this.selectedMarker.setImage(null);
              }
              marker.setImage(selectedImage);
              this.selectedMarker = marker;
              this.selectedHouse = places[i].aptCode;
            }
          });
          // kakao.maps.event.addListener(
          //   marker,
          //   "click",
          //   this.makeOverListener(this.map, marker, infowindow),
          // );
          this.markers.push(marker);
        }

        //marker에 맞게 지도 보여지는 범위 설정
        const bounds = new kakao.maps.LatLngBounds();
        positions.forEach((position) => {
          bounds.extend(position);
        });
        this.map.setBounds(bounds);
      }
    },
    // getInfoWindow(place) {
    //   console.log(place);
    //   var infowindow = new kakao.maps.InfoWindow({
    //     content: `
    //     <div style="height:50px; font-size:15px; border-radius:3px;">
    //       <div>${place.aptName}</div>
    //       <div>${place.dongName} ${place.jibun}번지, ${place.buildYear}</div>
    //     </div>
    //     `, //인포윈도우에 표시할 내용
    //   });
    //   return infowindow;
    // },
    // makeOverListener(map, marker, infowindow) {
    //   return function () {
    //     infowindow.open(map, marker);
    //   };
    // },
    createMarkerImage(imgUrl) {
      const imgSize = new kakao.maps.Size(40, 40);
      const offset = new kakao.maps.Point(20, 40);
      var markerImage = new kakao.maps.MarkerImage(
        imgUrl, // 마커 이미지 URL
        imgSize, // 마커의 크기
        {
          offset: offset, // 마커 이미지에서의 기준 좌표
        },
      );

      return markerImage;
    },
  },
  created() {
    this.clearHouse();
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
      // this.displayMarker();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=" +
        process.env.VUE_APP_MAP_API_KEY +
        "&libraries=services";
      document.head.appendChild(script);
    }
  },
};
</script>

<style scoped>
.house-container {
  display: flex;
  flex-direction: row;
  height: fit-content;
}
.search-bar {
  height: 200px;
  min-width: 400px;
}
.search-list {
  min-height: 300px;
  min-width: 400px;
}
</style>

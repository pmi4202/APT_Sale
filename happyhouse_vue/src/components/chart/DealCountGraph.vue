<template>
  <b-container class="chart-box">
    <div class="chart-title">거래량 TOP10</div>
    <div class="dealcnt-chart-container">
      <div class="dealcnt-chart-element dealcnt-chart">
        <Bar
          :chart-options="chartOptions"
          :chart-data="chartData"
          :chart-id="chartId"
          :width="width"
          :height="height"
        />
      </div>
      <div class="dealcnt-chart-element dealcnt-chart-list">
        <b-list-group class="recode-list">
          <b-list-group-item
            class="chart-list-item"
            v-for="(recode, indx) in datas"
            :key="indx"
          >
            <div>{{ recode.ranking }}위</div>
            <div>아파트명 : {{ recode.aptName }} ({{ recode.aptCode }})</div>
            <div>거래횟수 : {{ recode.count }}</div>
            <!-- <div>동 코드 : {{ recode.dongCode | dongCodeFilter }}</div> -->
          </b-list-group-item>
        </b-list-group>
      </div>
    </div>
  </b-container>
</template>

<script>
import { Bar } from "vue-chartjs/legacy";

import { dealCount } from "@/api/statistic";
// import { mapGetters } from "vuex";

import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
} from "chart.js";

ChartJS.register(
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
);
// const statisticStore = "statisticStore";
export default {
  name: "BarChart",
  components: { Bar },

  props: {
    cssClasses: {
      default: "",
      type: String,
    },
    styles: {
      type: Object,
      default: () => {},
    },
    plugins: {
      type: Object,
      default: () => {},
    },
  },
  // computed: {
  //   ...mapGetters(statisticStore, ["getCode"]),
  // },
  data() {
    return {
      datas: [],
      chartData: {
        labels: [],
        datasets: [],
      },
      chartOptions: {
        responsive: true,
      },
      chartId: "dealChart",
      width: 500,
      height: 500,
    };
  },

  methods: {
    async GetData() {
      dealCount(
        (response) => {
          console.log(response);
          this.chartData.labels = [];
          this.chartData.datasets = [];
          let data = [];
          let labels = [];
          for (let recode of response.data.regcodes) {
            this.datas.push(recode);
            data.push(recode.count);
            labels.push(recode.aptCode);
          }
          this.chartData.labels = labels;
          this.chartData.datasets.push({ data, label: "거래 횟수" });
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
  created() {
    this.GetData();
  },
};
</script>
<style scoped>
.chart-title {
  font-family: "KoPubDotumMedium";
  font-size: 20px;
  height: 40px;
  line-height: 40px;
  margin-top: 15px;
  margin-bottom: 30px;
  background-color: rgb(61, 115, 151);
  color: white;
  border-radius: 10px;
}
.recode-list {
  text-align: start;
  overflow-y: scroll;
  min-width: 300px;
  max-height: 500px;
}
.dealcnt-chart-container {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.dealcnt-chart-element {
  height: 550px;
  margin: auto 10px;
}
.dealcnt-chart {
  padding: 10px;
  /* width: 500px;
  height: 500px; */
}
.chart-list-item {
  border-left: none;
  border-right: none;
  font-size: 15px;
}
</style>

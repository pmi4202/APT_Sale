<template>
  <b-container>
    <div>{{ data }}</div>
    <b-button @click="likeClick">좋아요</b-button>
    <b-button @click="DealClick">거래금액</b-button>
  </b-container>
</template>

<script>
import { likeTop10, dealTop10 } from "@/api/statistic";

export default {
  data() {
    return {
      data: [],
      code: "",
    };
  },
  methods: {
    likeClick() {
      likeTop10(
        (response) => {
          this.code, console.log(response);
          this.data = response.data;
        },
        (error) => {
          console.log(error);
        },
      );
    },
    DealClick() {
      dealTop10(
        this.code,
        (response) => {
          console.log(response);
          this.data = response.data;
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};
</script>

<style></style>

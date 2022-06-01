<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png" />
    <HelloWorld msg="Welcome to Your Vue.js App" />
    <TestComponent></TestComponent>
  </div>
</template>

<script>
import HelloWorld from "@/components/HelloWorld.vue";
// @ is an alias to /src
import TestComponent from "@/components/TestComponent.vue";

export default {
  name: "HomeView",
  components: {
    HelloWorld,
    TestComponent,
  },
};
</script>

<template>
  <b-container id="container" fluid>
    <div id="contacts">
      <div id="contacts--contact">
        <div v-for="(person, idx) in developer" :key="idx" class="left">
          <div class="developer-info">
            <b-icon-mailbox></b-icon-mailbox> {{ person.mail }} |
            {{ person.phone }}
          </div>
        </div>
      </div>
      <div>문의하기</div>
      <div id="contacts--social">
        <div><img width="30px" rounded="circle" src="@/assets/logo.png" /></div>
        <div>
          <img rounded="circle" width="30px" src="@/assets/mybatis_logo.jpg" />
        </div>
        <div>
          <img rounded="circle" width="30px" src="@/assets/spring_logo.png" />
        </div>
      </div>
    </div>
    <div id="mentions">
      <p>{{ mention }}</p>
    </div>
  </b-container>
</template>

<script>
export default {
  data() {
    return {
      mail: ["pyr123@naver.com", "jdg987@koko.com", "start_so@naver.com"],
      phone: ["06.00.00.00.00", "123-123-4-12", "123123.12312,12,3"],
      mention: "2022-05 | lucky seven team",
    };
  },
  computed: {
    developer() {
      let count = this.mail.length;
      let value = [];
      for (let i = 0; i < count; i++) {
        value.push({
          mail: this.mail[i],
          phone: this.phone[i],
        });
      }
      return value;
    },
  },
};
</script>

<style scoped>
.left {
  text-align: start;
}
#container {
  padding-top: 20px;
  /* margin-top: 20px; */
  width: 100vw;
  color: white;
  /* background-color: #292354; */
  background-color: #1d2140;
  display: flex;
  flex-direction: column;
  align-items: center;
}
#contacts {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  align-items: center;
  width: 100%;
}
#contacts--contact {
  display: flex;
  flex-direction: column;
  text-align: right;
}
#contacts--social {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
}
#contacts--social div {
  padding: 5px;
  height: 40px;
  width: 40px;
  background-color: white;
  border-radius: 50%;
}
#mentions p {
  font-size: 0.5em;
}
</style>

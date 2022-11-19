<template>
  <div class="userMainPk">
    <Login></Login>
    <router-view></router-view>
  </div>
</template>

<script>
import Login from "@/views/Login/Login";
export default {
  name: "userMainPK",
  components: {Login},
}
</script>

<style scoped>
  .userMainPk{
    position:absolute;
    top: 0;
    left: 12%;
    width: 86%;
    height: 100%;
  }
</style>
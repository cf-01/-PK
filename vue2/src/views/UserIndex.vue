<template>
  <div class="userIndex">
      <div class="aside"><UserAside></UserAside></div>
      <div class="userMain"><UserMainPK></UserMainPK></div>
  </div>
</template>

<script>
import UserAside from "@/components/UserAside";
import UserMainPK from "@/views/UserMainPK";
export default {
  name: "Index",
  components: {UserMainPK, UserAside}
}
</script>

<style scoped>
.aside{
  width: 20%
}
.userMain{
  width: 80%;
}
</style>
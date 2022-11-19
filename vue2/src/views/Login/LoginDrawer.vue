<template>
  <div class="login">
    <el-drawer
        title="我是标题"
        :visible.sync="drawer"
        :before-close="handleClose"
        :with-header="false"
        size="40%">
      <el-row class="login-center">
        <el-col :offset="4" :span="16" >
            <router-view></router-view>
        </el-col>
      </el-row>

    </el-drawer>
  </div>
</template>

<script>
import Login from "@/views/Login/Login";
import LoginByEmail from "@/views/Login/LoginByEmail";
import LoginByAccount from "@/views/Login/LoginByAccount";
export default {
  name: "LoginDrawer",
  components: {LoginByEmail, Login,LoginByAccount},
  data(){
    return{
      drawer: this.$store.state.flag,
      input: ''
    }
  },
  watch:{
    '$store.state.flag'(){
      this.drawer = this.$store.state.flag
    },

  },
  methods:{
    handleClose(){
      this.$store.state.flag = false
    }
  },

}
</script>

<style scoped>

  .login-center {
    min-width: 350px;
  }

  /deep/.el-drawer{
    padding: 30px;
  }


</style>
<template>
  <div class="userAside">
    <div class="setAside">
      <el-row class="tac">
        <el-col :span="24">
          <el-menu
              style="min-height:100vh; width: 100%;background-color:#f7f7f7;"
              class="el-menu-vertical-demo"
              @open="handleOpen"
              default-active="1"
          >
            <div class="logo" >
              <img src="../assets/img/camera.svg" alt="">
            </div>
            <el-menu-item index="0" @click="toRandomPK">
              <i class="el-icon-refresh"></i>
              <span slot="title">随机匹配</span>
            </el-menu-item>
            <el-menu-item index="1" @click="toLevelRanking">
              <i class="el-icon-trophy"></i>
              <span slot="title">颜值排行棒</span>
            </el-menu-item>
            <el-menu-item index="2" @click="toMyPK">
              <i class="el-icon-camera"></i>
              <span slot="title">我的PK</span>
            </el-menu-item>
            <el-menu-item index="3" @click="toMyUpload">
              <i class="el-icon-upload2"></i>
              <span slot="title">我的上传</span>
            </el-menu-item>

            <el-menu-item index="4" @click="toMyProfile">
              <i class="el-icon-user"></i>
              <span slot="title">我的资料</span>
            </el-menu-item>

          </el-menu>
        </el-col>
      </el-row>
    </div>

  </div>
</template>

<script>
export default {
  name: "UserAside",
  data(){
    return{
      url:''
    }
  },
  methods:{
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    toLevelRanking(){
      this.$router.replace('levelRanking')
    },
    toMyPK(){
      this.$router.replace('myPK')
    },
    toMyUpload(){
      this.$router.replace('myUpload')
    },
    toMyProfile(){
      this.$router.replace('myProfile')
    },
    toRandomPK(){
      this.$router.replace('randomPK')
    }
  }
}
</script>

<style scoped>

.userAside {
  position: fixed;
  top: 0px;
  left: 0;
  width: 200px;
  background-color: #f2f2f2;
}

.setAside {
  width: 200px;
  height: 100%;
}
.logo {
  width: 170px;
  background-color:#f7f7f7;
  padding-left: 30px;
  margin-bottom: 20px;
}
.logo img{
  width: 120px;
  height: 120px;
  margin: auto;
}


</style>
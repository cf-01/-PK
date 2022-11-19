<template>
  <div class="login" :class="{login_change:isLogin}">
    <ul class="login-ul">
      <li><a href="javascript:;" @click="changeType('zh')"><img src="../../assets/img/zh-en.svg" alt=""></a></li>
      <li><a href="javascript:;" @click="toLevelRanking">排行榜</a></li>
      <li><a href="#">{{$t("help")}}</a> </li>
      <li>|</li>
      <li v-show="!isLogin"><a href="javascript:;" @click="login">{{$t("sign")}}</a></li>
      <li v-show="isLogin" >
        <a href="javascript:;" @click="toUserIndex">
          <el-dropdown>
            <el-avatar icon="el-icon-user-solid" size="small" style="vertical-align: middle;"></el-avatar>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="toMyProfile">个人资料</el-dropdown-item>
              <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </a>
      </li>
      <li v-show="isLogin&&isIndex" style="margin-left: 0"><a href="javascript:;" @click="toFacePK"><img src="../../assets/img/close.svg" alt=""></a></li>
    </ul>
  </div>

</template>

<script>
import axios from "axios";

export default {
  name: "Login",
  data() {
    return {
      flag:true,
      isLogin:false,
      isIndex:false,
    }
  },
  created() {

    if(JSON.parse(window.localStorage.getItem('access-admin')) != null){
      let admin = JSON.parse(window.localStorage.getItem('access-admin'))
      axios({
        url:'http://test.xhixm.com:9000/ssm/checkToken',
        method:'post',
        headers:{
          token:admin.token
        }
      }).then(response=>{
        if (response)this.isLogin=true
      })
    }
    if(this.$route.path == '/'){
      this.isIndex = false;
    }else{
      this.isIndex = true;
    }
    console.log()
  },
  methods:{
    login(){
      this.$store.dispatch('change')
    },
    // 中英文
    changeType(type) {
      this.$nextTick(() => {
        sessionStorage.setItem("locale", type);
        this.$i18n.locale = type;
        localStorage.setItem("lang", this.$t("lang"));
      });
    },
    toUserIndex(){
      this.$router.push('/index')
    },
    logout(){
      this.$router.push('/');
      //退出登陆，清空token
      localStorage.removeItem('access-admin');
      this.$router.push('/loginByAccount');
      this.isLogin= false;
    },
    toMyProfile(){
      this.$router.push('/index/myProfile')
    },
    toFacePK(){
      this.$router.push('/')
    },
    toLevelRanking(){
      if (this.isLogin){
        if (this.$route.path == '/'){
          this.$router.push('index/levelRanking')
        }else{
          this.$router.push('levelRanking')
        }

      }else {
        this.$message({
          message: '登陆后才能查看排行榜哦',
          type: 'warning'
        });
      }
    }
  }
}
</script>

<style scoped>
.login{
  position: absolute;
  top: 0;
  right: 0;
  background-color: #fff;
  border-radius: 4px;
  height: 36px;
  margin-top: 25px;
  min-width: 240px;
  z-index: 999;
}

.login_change{
  min-width: 210px;
}

.login-ul{
  display: flex;
  height: 36px;
  line-height: 36px;
}

.login-ul li{
  height: 36px;
  line-height: 36px;
  font-size: 14px;
  font-weight: 700;
  float: left;
  margin-left: 10px;
}

.login-ul li img{
  width: 25px;
  height: 25px;
  vertical-align: middle;
  margin-left: 10px;
}
</style>
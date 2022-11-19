<template>
  <div class="login-center">
    <div class="login-title">{{$t("sign")}}</div>
    <div class="login-title-desc">{{$t("login_title_desc")}}</div>
    <div class="login-form">
      <el-form>
        <el-input
            placeholder="六位邮箱验证码"
            v-model="input"
            clearable>
        </el-input>
        <el-link type="info" @click="toLoginByEmail" style="margin-top: 50px;font-size: 14px">返回</el-link>
        <el-button style="float: right; margin-top: 40px;margin-bottom: 20px">下一步</el-button>


      </el-form>
    </div>
    <div class="login-info">
      <div class="login-info-title">
        颜值PK平台
      </div>
      <ul>
        <li>登录可享受无限次颜值PK</li>
        <li>登录可查看颜值排行榜</li>
        <li>详细的颜值分析报告</li>
      </ul>
      <el-button round>了解更多</el-button>
    </div>
    <div class="login-copyright">未注册账号自动创建为新账号<br />
      登录注册均视为已同意 <a href="#">用户协议</a> 和 <a href="#">隐私政策</a>。</div>
  </div>
</template>

<script>
export default {
  name: "LoginByEmail",
  data(){
    return{
      input: ''
    }
  },
  methods:{
    toLoginByEmail(){
      this.$router.push("/")
    }
  }
}
</script>

<style scoped>
  .login-center {
    position:relative;
    min-height: 600px;
    margin-top: 100px;
    border-radius: 10px;
    border: rgba(134, 128, 128, 0.93) solid 1px;
    padding: 20px;
  }

  .login-top {
    position: absolute;
    top: 0;
    right: 0;
  }
  .login-top span {
    position: absolute;
    top: -25px;
    right: 15px;
    color:#cdcdcd ;
    width: 100px;
    font-size: 14px;
  }
  .login-top span svg {
    width: 12px;
    vertical-align: bottom;
  }


  .login-top img {
    width: 50px;
  }

  .login-title {
    text-align: center;
    font:700 40px 微软雅黑 ;
    color: #333;
    margin-top: 30px;
  }

  .login-title-desc {
    text-align: center;
    font:12px 微软雅黑;
    color: #a1a1a1;
    margin-bottom: 35px;
  }

  .login-form {
    padding:0 50px;
  }
  /deep/ .el-input__inner {
    height: 50px ;
  }

  .login-info {
    background-color: #f7f7f7;
    width: 90%;
    position: absolute;
    top: 330px;
    left: 0;
    padding: 5%;
    text-align: center;
  }

  .login-info-title {
    font:700 30px 微软雅黑 ;

  }

  .login-info ul {
    padding: 20px;
    color: #808080;
  }

  .login-info ul li{
    margin-top: 5px;
    font-size: 14px;
  }

  .login-copyright {
    position: absolute;
    bottom: 0;
    left: 0;
    padding: 5%;
    font-size: 12px;
    width: 90%;
    text-align: center;
    color: #808080;
  }

  .login-copyright a {
    color:#38b48f ;
  }
</style>
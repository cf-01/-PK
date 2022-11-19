<template>
  <div class="login-center">
    <div class="login-top">
      <span>邮箱登录<svg class="login-top-icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 477.9 477.9"><path d="M17.1 0C8.7 0 1.6 6.1.2 14.3c-.1.9-.2 1.9-.2 2.8C0 24.4 4.7 31 11.7 33.3c113.8 37.9 171.4 122.1 175.8 256.9h-85c-5.1 0-9.9 2.3-13.2 6.3-6 7.3-4.9 18 2.4 24L279.4 474c6.5 5.3 15.9 5.1 22.2-.5l170.7-153.6c3.6-3.2 5.6-7.8 5.6-12.7 0-9.4-7.6-17.1-17.1-17.1H375C365.7 121 218 0 17.1 0z" fill="#e4e4e4"></path></svg></span>
      <a href="javascript:;" @click="toLoginByEmail"><img src="../../assets/img/login-top.png" alt=""></a>
    </div>
    <div class="login-title">注册</div>
    <div class="login-title-desc">{{$t("login_title_desc")}}</div>
    <div class="login-form">
      <el-form>
        <el-input
            placeholder="请输入用户名"
            v-model="input.username"
            clearable>
        </el-input>
        <el-input
            placeholder="请输入密码"
            v-model="input.password"
            clearable>
        </el-input>
<!--        <el-input-->
<!--            placeholder="确认密码"-->
<!--            v-model="input"-->
<!--            clearable>-->
<!--        </el-input>-->
        <el-row :gutter="20">
          <el-col :span="16">
            <el-input
                placeholder="请输入邮箱"
                v-model="input.email"
                clearable>
            </el-input>
          </el-col>


          <el-col :span="8">
            <el-button style="width: 100%">
              <span v-if="codeShow" class="getcold" @click="sendCode">验证码</span>
              <span v-if="!codeShow" class="count">{{count}}秒</span>
            </el-button>
          </el-col>
        </el-row>
        <el-input
            placeholder="请输入验证码"
            v-model="input.code"
            clearable>
        </el-input>
        <el-button style="float: right; margin:10px 0 0 20px" @click="UserRegister">注册</el-button>
        <el-button style="float: right; margin: 10px 0" @click="toLoginByAccount">已有账号</el-button>
      </el-form>
    </div>
    <div class="login-info">
      <div class="login-info-title">
        颜值PK平台
      </div>
    </div>
    <div class="login-copyright">
      登录注册均视为已同意 <a href="#">用户协议</a> 和 <a href="#">隐私政策</a>。</div>
  </div>
</template>

<script>
import {getCode, userRegister} from "@/api/allApi";

export default {
  name: "RegisterByAccount",
  data(){
    return{
      input:{
        username:'',
        password:'',
        email:'',
      },
      codeShow: true, //获取验证码倒计时
      timer: null, //定时器
      count: '', //倒计时数字
    }
  },
  methods:{
    toLoginByEmail(){
      this.$router.push("/")
    },
    toLoginByAccount(){
      this.$router.push("/loginByAccount")
    },
    sendCode(){
      const TIME_COUNT = 60;
      if (!this.timer) {
        this.count = TIME_COUNT;
        this.codeShow = false;
        this.timer = setInterval(() => {
          if (this.count > 0 && this.count <= TIME_COUNT) {
            this.count--;
          } else {
            this.codeShow = true;
            clearInterval(this.timer);
            this.timer = null;
          }
        }, 1000)
      }
      getCode({
        email:this.input.email
      }).then(res=>{
        if (res.data == true) {
          alert("发送成功")
        }
        else{
          alert('发送失败')
        }
      }).catch(err=>{
        console.log(err.message)
      })
    },
    UserRegister(){
      userRegister({
        username:this.input.username,
        password:this.input.password,
        email:this.input.email,
        code:this.input.code
      }).then(res=>{
        if (res.data.message == '注册成功') {
          this.$router.replace({path:'/loginByAccount'})
        }
        else{
          alert('注册失败')
        }
      }).catch(err=>{
        console.log(err.message)
      })
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
    right: -10px;
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
    min-width: 250px;
  }
  /deep/ .el-input__inner {
    margin-bottom: 10px;
  }

  .login-info {
    background-color: #f7f7f7;
    width: 90%;
    position: absolute;
    top: 480px;
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
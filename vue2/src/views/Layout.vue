<template>
  <div class="layout">
    <el-row >
      <el-col :span="2"><div class="logo">
        <svg t="1666246039092" class="icon" viewBox="0 0 1028 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="7578" width="80" height="80"><path d="M458.565818 300.730182H85.899636L4.398545 764.951273h158.068364l32.930909-147.991273h277.597091l46.242909-263.540364-60.672-52.689454z m-114.013091 231.773091h-141.265454l25.739636-146.618182h141.265455l-25.739637 146.618182z m448.930909-16.896l234.938182-204.078546h-159.418182l-189.486545 163.514182 31.069091-177.035636h-141.242182l-81.966545 466.920727h141.265454l36.165818-206.103273 150.853818 202.053818h131.84l37.143273-62.161454h-52.456727l-138.705455-183.109818z" p-id="7579" fill="#fff"></path></svg>
      </div>
      </el-col>
      <el-col :span="3" :offset="19">
        <Login></Login>
      </el-col>

    </el-row>
    <el-row>
      <el-col :span="10" :offset="7"><div class="title">{{$t("title")}}</div></el-col>
    </el-row>
    <PkMain></PkMain>
    <LoginDrawer></LoginDrawer>

    </div>
</template>

<script>
import Login from "@/views/Login/Login";
import PkMain from "@/views/PkMain";
import LoginDrawer from "@/views/Login/LoginDrawer";
export default {
  name: "Layout",
  components: {LoginDrawer, PkMain, Login},
  data(){
    return{

    }
  },
  methods: {


  }
}
</script>

<style type="text/css" scoped>


  .layout{
    /*修复多余滚动条*/
    position: relative;
    overflow-y: hidden;
    padding: 0 30px;
    box-sizing: border-box;
  }

  .logo{
    height: 100px;
    min-width: 100px;
  }


  .title{
    height: 50px;
    line-height: 50px;
    font-family: ALMM;
    font-size: 50px;
    text-align: center;
    color: #fff;
    margin-bottom: 40px;
  }


</style>


<template>
  <div class="randomPK">
    <div class="login-top">
      <!--      <el-col :span="3" :offset="19"><Login></Login></el-col>-->
    </div>
    <div class="randomPK-center">
      <el-alert
          title="随机匹配PK"
          show-icon
          center
          type="success"
          description="仅需上传自己的照片即可在数据库中进行PK"
          :closable="false">
      </el-alert>
      <div class="photograph">
        <el-upload
            v-show="isShowUpload"
            class="upload-demo"
            drag
            ref="upload"
            :data="{userId:userId}"
            action="http://test.xhixm.com:9000/ssm/files/upload"
            :on-change="imgSaveToUrl"
            :on-success="uploadSuccess"
            :auto-upload="false"
            :show-file-list="false"
        >
          <i class="el-icon-upload" style="color:#38B48F"></i>
          <div
              class="el-upload__tip text" style="padding: 30px"
          >提示：可支持PNG、JPG、BMP，图片大小不超过4M,长边不大于4096像素，请保证识别的部分为图片的主题部分</div>
        </el-upload>
        <!-- 本地预览需要上传处理的图片-->
        <el-row v-show="isShowImgUpload">
          <div class="showPreview">
            <el-image
                :src="localUrl"
                :preview-src-list="[localUrl]"
                style="width:400px;height:100%;max-height: 400px"
                fit="scale-down"
            ></el-image>
          </div>
          <div style="padding: 5px;">
            <el-button type="primary" :loading="false" size="small" @click="uploadButtonClick">重新上传</el-button>
            <el-button type="primary" :loading="false" size="small" @click="processButtonClick">立即PK</el-button>
          </div>
        </el-row>
      </div>
    </div>

    <el-dialog
        title="获胜者"
        :visible.sync="centerDialogVisible"
        width="30%"
        center>
      <div style="margin: auto;text-align: center">
        <el-image
            style="width: 300px; height: 100%"
            :src="beautyResult.winnerUrl"
            fit="contain">
        </el-image>
        <br>
        <el-tag type="success">得分为{{beautyResult.beauty}}</el-tag>
      </div>

      <span slot="footer" class="dialog-footer">
              <el-button @click="centerDialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="centerDialogVisible = false">确 定</el-button>
            </span>
    </el-dialog>

  </div>
</template>

<script>
import PkMain from "@/views/PkMain";
import {getFaceRelevance, insertFaceRelevance, randomPK} from "@/api/allApi";
export default {
  name: "randomPK",
  components: {PkMain},
  data(){
    return {
      localUrl:'',
      isShowImgUpload:false,
      isShowUpload:true,
      serverUrl:'',
      beauty: '',
      base64_1:'',
      userId:'',
      centerDialogVisible: false,
      beautyResult:{
        //分值
        beauty:'',
        //获胜者url
        winnerUrl:''
      }
    }
  },
  created(){
    if(JSON.parse(window.localStorage.getItem('access-admin')) != null){
      let admin = JSON.parse(window.localStorage.getItem('access-admin'))
      this.userId = admin.userId
    }
  },
  methods: {
    imgSaveToUrl(event) {
      // 获取上传图片的本地URL，用于上传前的本地预览
      var URL = null;
      if (window.createObjectURL != undefined) {
        // basic
        URL = window.createObjectURL(event.raw);
      } else if (window.URL != undefined) {
        // mozilla(firefox)
        URL = window.URL.createObjectURL(event.raw);
      } else if (window.webkitURL != undefined) {
        // webkit or chrome
        URL = window.webkitURL.createObjectURL(event.raw);
      }
      // 转换后的地址为 blob:http://xxx/7bf54338-74bb-47b9-9a7f-7a7093c716b5
      this.localUrl = URL;
      this.isShowImgUpload = true;//呈现本地预览组件
      this.isShowUpload = false;//隐藏上传组件
    },
    processButtonClick(){
      this.$refs.upload.submit();

    },
    uploadButtonClick(){
      this.localUrl='';
      this.isShowImgUpload = false;//呈现本地预览组件
      this.isShowUpload = true;//隐藏上传组件
    },
    uploadSuccess(response){
      if(response.code === '400'){
        alert(response.msg)
      }else if(response.code === '200'){
        this.faceId = response.faceId
        randomPK({
          faceId:this.faceId,
          userId:this.userId
        }).then(res=>{
          this.beautyResult.beauty = res.data.beauty
          this.beautyResult.winnerUrl = res.data.imgPath
        }).catch(err=>{
          console.log(err)
        })
      }
      this.centerDialogVisible=true
    },
    }
  }
</script>

<style scoped>
  .login-top {
    width: 100%;
    height: 100px;
    position: relative;
  }
  .randomPK-center {
    text-align: center;
    padding:20px 50px;
  }
  .photograph{
    margin-top: 50px;
  }
  /deep/ .el-upload-dragger {
    width: 400px;
    height: 400px;
  }



</style>
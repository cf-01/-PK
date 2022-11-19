<template>
  <div class="pkmain">
    <el-row>
      <el-col :span="12" :offset="6">
        <div class="pk-main">
          <el-row :gutter="20">
            <el-col :span="12">
              <div class="photograph">
                  <el-upload
                      v-show="isShowUpload1"
                      class="upload-demo"
                      drag
                      ref="upload1"
                      :data="{userId:userId}"
                      action="http://test.xhixm.com:9000/ssm/files/upload"
                      :on-change="imgSaveToUrl"
                      :on-success="uploadSuccess1"
                      :auto-upload="false"
                      :show-file-list="false"
                  >
                    <i class="el-icon-upload" style="color:#38B48F"></i>
                    <div
                        class="el-upload__tip text" style="padding: 30px"
                    >提示：可支持PNG、JPG、BMP，图片大小不超过4M,长边不大于4096像素，请保证识别的部分为图片的主题部分</div>
                  </el-upload>
                <!-- 本地预览需要上传处理的图片-->
                <el-row v-show="isShowImgUpload1">
                    <div style="position:relative;background-color: #fff;">
                      <el-image
                          :src="localUrl1"
                          :preview-src-list="[localUrl1]"
                          style="width:320px;height:100%;max-height: 320px"
                          fit="scale-down"
                      ></el-image>
                    </div>
                    <div style="padding: 5px;">
                      <el-button type="primary" :loading="false" size="small" @click="uploadButtonClick1">重新上传</el-button>
                      <el-button type="primary" :loading="false" size="small" @click="processButtonClick1">立即处理</el-button>
                    </div>
                </el-row>

              </div>
            </el-col>
            <el-col :span="12">
              <div class="photograph">
                <el-upload
                    v-show="isShowUpload2"
                    class="upload-demo"
                    drag
                    ref="upload2"
                    action="http://test.xhixm.com:9000/ssm/files/upload"
                    :data="{userId:userId}"
                    :on-change="imgSaveToUrl2"
                    :on-success="uploadSuccess2"
                    :show-file-list="false"
                    :auto-upload="false"
                    accept=".png,.jpg,.jpeg"
                >
                  <i class="el-icon-upload" style="color:#38B48F"></i>
                  <div
                      class="el-upload__tip text" style="padding: 30px"
                  >提示：可支持PNG、JPG、BMP，图片大小不超过4M,长边不大于4096像素，请保证识别的部分为图片的主题部分</div>
                </el-upload>
                <!-- 本地预览需要上传处理的图片-->
                <el-row v-if="isShowImgUpload2">
                  <div style="position:relative;background-color: #fff;">
                    <el-image
                        :src="localUrl2"
                        :preview-src-list="[localUrl2]"
                        style="width:320px;height:100%;max-height: 320px"
                        fit="scale-down"
                    ></el-image>
                  </div>
                  <div style="padding: 5px;">
                    <el-button type="primary" :loading="false" size="small" @click="uploadButtonClick2">重新上传</el-button>
                    <el-button type="primary" :loading="false" size="small" @click="processButtonClick2">点击上传</el-button>
                  </div>
                </el-row>
              </div>
            </el-col>
          </el-row>
          <div class="pk-click">
            <el-button type="primary" round size="medium" @click="submitPK">点击PK</el-button>
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
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Login from "@/views/Login/Login";
import axios from "axios";
import {getFaceRelevance, getFaceScore, insertFaceRelevance, upload} from "@/api/allApi";
export default {
  name: "PkMain",
  components: {Login},
  data() {
    let fd = new FormData();
    return {
      localUrl1:'',
      localUrl2:'',
      isShowImgUpload1:false,
      isShowUpload1:true,
      isShowImgUpload2:false,
      isShowUpload2:true,
      serverUrl1:'',
      serverUrl2:'',
      beauty1: '',
      beauty2: '',
      base64_1:'',
      base64_2:'',
      userId:'',
      faceId1:'',
      faceId2:'',
      centerDialogVisible: false,
      url: '',
      //颜值PK结果
      beautyResult:{
        //分值
        beauty:'',
        //获胜者url
        winnerUrl:''
      }
    };
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
      this.localUrl1 = URL;
      this.isShowImgUpload1 = true;//呈现本地预览组件
      this.isShowUpload1 = false;//隐藏上传组件
    },
    imgSaveToUrl2(event) {
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
      this.localUrl2 = URL;
      this.isShowImgUpload2 = true;//呈现本地预览组件
      this.isShowUpload2 = false;//隐藏上传组件
    },
    processButtonClick1(){
      this.$refs.upload1.submit();
    },
    processButtonClick2(){
      this.$refs.upload2.submit();
    },
    uploadButtonClick1(){
      this.localUrl1='',
      this.isShowImgUpload1 = false;//呈现本地预览组件
      this.isShowUpload1 = true;//隐藏上传组件
    },
    uploadButtonClick2(){
      this.localUrl2='',
      this.isShowImgUpload2 = false;//呈现本地预览组件
      this.isShowUpload2 = true;//隐藏上传组件
    },
    uploadSuccess1(response1){
      if(response1.code === '400'){
        alert(response1.msg)
      }else if(response1.code === '200'){
        this.faceId1 = response1.faceId
        this.beauty1 = response1.beauty
        this.$message({
          showClose: true,
          message: '上传成功',
          type: 'success'
        });
      }
    },
    uploadSuccess2(response2){
      if(response2.code === '400'){
        alert(response2.msg)
      }else if(response2.code === '200'){
        this.faceId2 = response2.faceId
        this.beauty2 = response2.beauty
        this.$message({
          showClose: true,
          message: '上传成功',
          type: 'success'
        });
      }
    },
    submitPK(){
      if (this.userId == null || this.userId == ''){
          if (this.beauty1 > this.beauty2){
            this.beautyResult.beauty = this.beauty1
            this.beautyResult.winnerUrl = this.localUrl1

          }else{
            this.beautyResult.beauty = this.beauty2
            this.beautyResult.winnerUrl = this.localUrl2
          }
        this.centerDialogVisible = true
      }
      else{
        if (this.faceId1 !== '' && this.faceId2 !== '' ){
          // 判断谁的颜值高（改成后端判断）
          insertFaceRelevance({
            faceId1:this.faceId1,
            faceId2:this.faceId2,
            userId:this.userId
          }).then(response=>{
            getFaceRelevance({
              faceId1:this.faceId1
            }).then(res=>{
              this.beautyResult.beauty = res.data.beauty
              this.beautyResult.winnerUrl = res.data.winnerUrl
            })
          })
          if (this.beautyResult.beauty == 999.0){
            this.$message({
              showClose: true,
              message: '颜值一样高',
              type: 'success'
            });
          }else{
            this.centerDialogVisible = true
          }
        }else{
          this.$message({
            showClose: true,
            message: '请上传两张照片再PK',
            type: 'error'
          });
        }
      }
      this.localUrl1 = ''
      this.localUrl2 = ''
      this.isShowImgUpload1=false,
      this.isShowUpload1=true,
      this.isShowImgUpload2=false,
      this.isShowUpload2=true
    }

  }
}

</script>

<style scoped>
.pk-main {
  height: 450px;
  background-color: rgba(255,255,255);
  padding: 30px;
  border-radius: 20px;
}

.photograph{
  text-align: center;
  margin: auto;
  height: 320px;
}

.pk-click {
  display: flex;
  margin-top: 50px;
  width: 100%;
  justify-content:center;
}

.pk-click .el-button {
  height: 50px;
  width: 200px;
  border-radius: 30px;
  font-family: ALMM;
  font-size: 25px;
}

/deep/ .el-upload-dragger {
  width: 320px;
  height: 300px;
}



</style>
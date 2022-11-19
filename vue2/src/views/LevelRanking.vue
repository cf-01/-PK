<template>
  <div class="levelRanking">
    <div class="login-top">
<!--      <el-col :span="3" :offset="19"><Login></Login></el-col>-->
    </div>
    <div class="PK-carousel">
      <el-carousel :interval="4000" type="card" height="500px">
        <el-carousel-item v-for="imgPath in photoSrc">
          <div class="photoTop">
            <el-image :src="imgPath.imgPath" fit="cover "></el-image>
          </div>
        </el-carousel-item>
      </el-carousel>
    </div>
    <div class="ranking-list">
      <el-table
          :data="tableData"
          stripe
          style="width: 100%">
        <el-table-column
            prop="imgPath"
            label="照片">
          <template slot-scope="scope">
            <el-image
                v-if="scope.row.imgPath !== undefined"
                style="width: 100px;height: 100%"
                fit="contain"
                :src="scope.row.imgPath"
                lazy
                :preview-src-list="srcList">
            </el-image>
          </template>
        </el-table-column>
        <el-table-column
            prop="beauty"
            label="颜值打分">
        </el-table-column>
        <el-table-column
            prop="age"
            label="年龄">
        </el-table-column>
        <el-table-column
            prop="gender"
            label="性别">
          <template slot-scope="scope">
            <span v-if="scope.row.gender == 'male'">男士</span>
            <span v-else>女士</span>
          </template>
        </el-table-column>
        <el-table-column
            prop="glasses"
            label="眼镜类型">
          <template slot-scope="scope">
            <span v-if="scope.row.glasses == 'none'">未戴眼镜</span>
            <span v-else-if="scope.row.glasses == 'common'">普通眼镜</span>
            <span v-else-if="scope.row.glasses == 'sun'">墨镜</span>
            <span v-else>无</span>
          </template>
        </el-table-column>
        <el-table-column
            prop="emotion"
            label="情绪">
          <template slot-scope="scope">
            <span v-if="scope.row.emotion == 'angry'">愤怒</span>
            <span v-else-if="scope.row.emotion == 'disgust'">厌恶</span>
            <span v-else-if="scope.row.emotion == 'fear'">恐惧</span>
            <span v-else-if="scope.row.emotion == 'happy'">高兴</span>
            <span v-else-if="scope.row.emotion == 'sad'">伤心</span>
            <span v-else-if="scope.row.emotion == 'surprise'">惊讶</span>
            <span v-else-if="scope.row.emotion == 'neutral'">无表情</span>
            <span v-else-if="scope.row.emotion == 'pouty'">撅嘴</span>
            <span v-else-if="scope.row.emotion == 'grimace'">鬼脸</span>
            <span v-else>无</span>
          </template>
        </el-table-column>
        <el-table-column
            prop="faceShape"
            label="脸型">
          <template slot-scope="scope">
            <span v-if="scope.row.faceShape == 'square'">正方形</span>
            <span v-else-if="scope.row.faceShape == 'triangle'">三角形</span>
            <span v-else-if="scope.row.faceShape == 'oval'">椭圆</span>
            <span v-else-if="scope.row.faceShape == 'round'">圆形</span>
            <span v-else-if="scope.row.faceShape == 'heart'">心形</span>
            <span v-else>无</span>
          </template>
        </el-table-column>
        <el-table-column
            prop="faceType"
            label="照片类型">
          <template slot-scope="scope">
            <span v-if="scope.row.faceType == 'human'">真实人物</span>
            <span v-else>卡通人物</span>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import {getAllFace, getTopFace} from "@/api/allApi";

export default {
  name: "LevelRanking",
    data(){
    return{
      tableData: [{

      }],
      srcList: [],
      photoSrc:[],
    }
  },
  created() {
    this.getTopFace()
    this.getAllFace()
  },
  methods:{
    getTopFace(){
      getTopFace({
        num:6
      }).then(response=>{
        this.photoSrc=response.data
      })
    },
    getAllFace(){
        getAllFace().then(response=>{
        this.tableData=response.data
         for (let i = 0; i < this.tableData.length; i++) {
           this.srcList[i] = this.tableData[i].imgPath
         }
      }).catch(error=>{
        alert(error.message)
      })
    }
  }
}
</script>

<style scoped>
  .levelRanking{

  }

  .login-top {
    width: 100%;
    height: 100px;
    position: relative;
  }

  .ranking-list{
    padding:20px 50px;
  }

  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }

  .PK-carousel {
    padding:0 100px;
  }

  .photoTop {

  }
</style>
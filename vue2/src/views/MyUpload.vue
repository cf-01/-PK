<template>
  <div class="myUpload">
    <div class="login-top">
    </div>
    <div class="ranking-list">
      <el-table
          :data="tableData"
          stripe
          style="width: 100%"
          height="800">
        <el-table-column type="index" label="序号"></el-table-column>
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
            label="颜值打分"
            sortable>
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
        <el-table-column
            prop="isShow"
            label="不公开/公开">
          <template slot-scope="scope">
            <el-switch
                v-model="scope.row.isShow"
                :active-value="1"
                :inactive-value="0"
                @change="switchChange(scope.row)"
            ></el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
                size="mini"
                type="danger"
                @click="deleteRow(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import {getFaceByUser, updateISDelete, updateIsShow} from "@/api/allApi";

export default {
  name: "MyUpload",
  data(){
    return{
      userId:'',
      tableData: [{}],
      srcList:[],
    }
  },
  methods:{
    deleteRow(row) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        updateISDelete({
          isDelete: 0,
          id:row.id
        }).then(res=>{
          if (res.data.code == "200"){
            this.$message({
              message: '删除成功',
              type: 'error'
            });
            this.getFaceByUser()
          }else {
            this.$message({
              message: '删除失败',
              type: 'error'
            });
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    getFaceByUser(){
      getFaceByUser({
        userId:this.userId
      }).then(res=>{
        this.tableData = res.data
        for (let i = 0; i < this.tableData.length; i++) {
          this.srcList[i] = this.tableData[i].imgPath
        }
      })
    },
    switchChange(row){
      updateIsShow({
        isShow: row.isShow,
        id:row.id
      }).then(res=>{
        if (res.data.code == "200"){
          this.$message({
            message: '修改成功',
            type: 'warning'
          });
        }else {
          this.$message({
            message: '修改失败',
            type: 'warning'
          });
        }
      })
    }
  },
  created() {
    if(JSON.parse(window.localStorage.getItem('access-admin')) != null){
      let admin = JSON.parse(window.localStorage.getItem('access-admin'))
      this.userId = admin.userId
    }
    this.getFaceByUser()
  },
}
</script>

<style scoped>

  .login-top {
    width: 100%;
    height: 100px;
    position: relative;
  }
  .ranking-list{
    padding:20px 50px;
  }
</style>
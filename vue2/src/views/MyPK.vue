<template>
  <div class="myPK">
    <div class="login-top">
      <!--      <el-col :span="3" :offset="19"><Login></Login></el-col>-->
    </div>
    <div class="PK-record">
      <el-alert
          title="PK记录"
          show-icon
          center
          type="success"
          description="这里是PK结果的记录"
          :closable="false">
      </el-alert>
      <el-collapse v-model="activeNames" @change="handleChange" accordion>
        <!--  v-for遍历后台数据    -->
        <el-collapse-item :title="'PK记录'+index" v-for="(item,index) in PKLeft" :key="index">
          <el-row>
            <el-col :span="10" :offset="1" >
              <el-card :body-style="{ padding: '0px',margin:'auto'}">
                <div class="imgbox">
                  <el-image
                      v-if="srcList2[index] !== undefined"
                      style="width: 500px; height: 500px"
                      :src="srcList1[index]"
                      :preview-src-list="srcList1"
                      fit="cover"></el-image>
                  <div style="padding: 14px;">
                    <el-descriptions title="颜值分析">
                      <el-descriptions-item label="颜值打分">
                        <el-tag type="warning">{{PKLeft[index].beauty}}</el-tag>
                      </el-descriptions-item>
                      <el-descriptions-item label="预测年龄">{{PKLeft[index].age}}</el-descriptions-item>
                      <el-descriptions-item label="预测性别">
                        <el-tag size="small" v-if="PKLeft[index].gender =='male'">男士</el-tag>
                        <el-tag size="small" type="danger" v-else>女士</el-tag>
                      </el-descriptions-item>
                      <el-descriptions-item label="情绪">
                        <el-tag size="small" type="danger" v-if="PKLeft[index].emotion =='angry'">愤怒</el-tag>
                        <el-tag size="small" type="warning" v-else-if="PKLeft[index].emotion =='disgust'">厌恶</el-tag>
                        <el-tag size="small" type="danger" v-else-if="PKLeft[index].emotion =='fear'">恐惧</el-tag>
                        <el-tag size="small" type="success" v-else-if="PKLeft[index].emotion =='happy'">高兴</el-tag>
                        <el-tag size="small" type="warning" v-else-if="PKLeft[index].emotion =='sad'">伤心</el-tag>
                        <el-tag size="small" type="warning" v-else-if="PKLeft[index].emotion =='surprise'">惊讶</el-tag>
                        <el-tag size="small" type="info" v-else-if="PKLeft[index].emotion =='neutral'">无表情</el-tag>
                        <el-tag size="small" type="warning" v-else-if="PKLeft[index].emotion =='pouty'">撅嘴</el-tag>
                        <el-tag size="small" v-else-if="PKLeft[index].emotion =='grimace'">鬼脸</el-tag>
                        <el-tag size="small" type="info" v-else>无</el-tag>
                      </el-descriptions-item>
                      <el-descriptions-item label="脸型">
                        <span v-if="PKLeft[index].faceShape == 'square'">正方形</span>
                        <span v-else-if="PKLeft[index].faceShape == 'triangle'">三角形</span>
                        <span v-else-if="PKLeft[index].faceShape == 'oval'">椭圆</span>
                        <span v-else-if="PKLeft[index].faceShape == 'round'">圆形</span>
                        <span v-else-if="PKLeft[index].faceShape == 'heart'">心形</span>
                        <span v-else>无</span>
                      </el-descriptions-item>
                      <el-descriptions-item label="照片类型">
                        <span v-if="PKLeft[index].faceType == 'human'">真实人物</span>
                        <span v-else>卡通人物</span>
                      </el-descriptions-item>
                      <el-descriptions-item label="眼镜类型">
                        <span v-if="PKLeft[index].glasses == 'none'">未戴眼镜</span>
                        <span v-else-if="PKLeft[index].glasses == 'common'">普通眼镜</span>
                        <span v-else-if="PKLeft[index].glasses == 'sun'">墨镜</span>
                        <span v-else>无</span>
                      </el-descriptions-item>
                    </el-descriptions>
                  </div>
                </div>

              </el-card>
            </el-col>
            <el-col :span="10" :offset="1" >
              <el-card :body-style="{ padding: '0px' }">
                <div class="imgbox">
                  <el-image
                      v-if="srcList2[index] !== undefined"
                      style="width: 500px; height: 500px"
                      :src="srcList2[index]"
                      :preview-src-list="srcList2"
                      fit="cover"></el-image>
                  <div style="padding: 14px;">
                    <el-descriptions title="颜值分析">
                      <el-descriptions-item label="颜值打分">
                        <el-tag type="warning">{{PKRight[index].beauty}}</el-tag>
                      </el-descriptions-item>
                      <el-descriptions-item label="预测年龄">{{PKRight[index].age}}</el-descriptions-item>
                      <el-descriptions-item label="预测性别">
                        <el-tag size="small" v-if="PKRight[index].gender =='male'">男士</el-tag>
                        <el-tag size="small" type="danger" v-else>女士</el-tag>
                      </el-descriptions-item>
                      <el-descriptions-item label="情绪">
                        <el-tag size="small" type="danger" v-if="PKRight[index].emotion =='angry'">愤怒</el-tag>
                        <el-tag size="small" type="warning" v-else-if="PKRight[index].emotion =='disgust'">厌恶</el-tag>
                        <el-tag size="small" type="danger" v-else-if="PKRight[index].emotion =='fear'">恐惧</el-tag>
                        <el-tag size="small" type="success" v-else-if="PKRight[index].emotion =='happy'">高兴</el-tag>
                        <el-tag size="small" type="warning" v-else-if="PKRight[index].emotion =='sad'">伤心</el-tag>
                        <el-tag size="small" type="warning" v-else-if="PKRight[index].emotion =='surprise'">惊讶</el-tag>
                        <el-tag size="small" type="info" v-else-if="PKRight[index].emotion =='neutral'">无表情</el-tag>
                        <el-tag size="small" type="warning" v-else-if="PKRight[index].emotion =='pouty'">撅嘴</el-tag>
                        <el-tag size="small" v-else-if="PKRight[index].emotion =='grimace'">鬼脸</el-tag>
                        <el-tag size="small" type="info" v-else>无</el-tag>
                      </el-descriptions-item>
                      <el-descriptions-item label="脸型">
                        <span v-if="PKRight[index].faceShape == 'square'">正方形</span>
                        <span v-else-if="PKRight[index].faceShape == 'triangle'">三角形</span>
                        <span v-else-if="PKRight[index].faceShape == 'oval'">椭圆</span>
                        <span v-else-if="PKRight[index].faceShape == 'round'">圆形</span>
                        <span v-else-if="PKRight[index].faceShape == 'heart'">心形</span>
                        <span v-else>无</span>
                      </el-descriptions-item>
                      <el-descriptions-item label="照片类型">
                        <span v-if="PKRight[index].faceType == 'human'">真实人物</span>
                        <span v-else>卡通人物</span>
                      </el-descriptions-item>
                      <el-descriptions-item label="眼镜类型">
                        <span v-if="PKRight[index].glasses == 'none'">未戴眼镜</span>
                        <span v-else-if="PKRight[index].glasses == 'common'">普通眼镜</span>
                        <span v-else-if="PKRight[index].glasses == 'sun'">墨镜</span>
                        <span v-else>无</span>
                      </el-descriptions-item>

                    </el-descriptions>
                  </div>
                </div>

              </el-card>
            </el-col>
          </el-row>
        </el-collapse-item>
      </el-collapse>
    </div>

  </div>
</template>

<script>
import {getFacePKLeft, getFacePKRight} from "@/api/allApi";

export default {
  name: "MyPK",
  data() {
    return {
      activeNames: ['1'],
      userId:'',
      PKLeft:[],
      PKRight:[],
      srcList1: [],
      srcList2: [],
    }
  },
  methods:{
    handleChange(val) {

    }
  },
  created() {
    if(JSON.parse(window.localStorage.getItem('access-admin')) != null){
      let admin = JSON.parse(window.localStorage.getItem('access-admin'))
      this.userId = admin.userId
    }
    getFacePKLeft({
      userId: this.userId
    }).then(res=>{
      this.PKLeft = res.data
      for (let i = 0; i < this.PKLeft.length; i++) {
        this.srcList1[i] = this.PKLeft[i].imgPath
      }
    }).catch(error=>{
      alert(error.message)
    })
    getFacePKRight({
      userId: this.userId
    }).then(res=>{
      this.PKRight = res.data
      for (let i = 0; i < this.PKRight.length; i++) {
        this.srcList2[i] = this.PKRight[i].imgPath
      }
    }).catch(error=>{
      alert(error.message)
    })
  }
}
</script>

<style scoped>
  .myPK {

  }
  .login-top {
    width: 100%;
    height: 100px;
    position: relative;
  }

  .PK-record{
    padding:20px 50px;
  }

  /**/
  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
  /**/

  .imgbox {
    margin: auto;
    padding: 20px;
    text-align: center;
  }
</style>
<template>
  <div class="myProfile">
    <div class="myProfile-form">
      <el-form ref="form" :model="memberForm" label-width="80px">
        <el-form-item label="头像">
          <el-avatar shape="square" :size="100" :src="url"></el-avatar>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="memberForm.memberUsername"></el-input>
        </el-form-item>
        <el-form-item label="邮箱号">
          <el-input v-model="memberForm.memberPhone"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model="memberForm.memberGender" placeholder="性别">
            <el-option label="男" value="M"></el-option>
            <el-option label="女" value="F"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">修改资料</el-button>
        </el-form-item>
      </el-form>
    </div>

  </div>

</template>

<script>

export default {
  data() {
    return {
      memberForm: {

      },
      admin: {

      },
      url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'
    }
  },
  created() {
    this.admin = JSON.parse(window.localStorage.getItem('access-member'))
  },
  mounted() {
    this.getMemberByMemberNo()
  },
  filters:{
    dataFormat(value){
      return moment(value).format("YYYY-MM-DD")
    }
  },
  methods: {
    onSubmit() {
      updateMemberByMemberNo({
        memberName: this.memberForm.memberName,
        memberGender: this.memberForm.memberGender,
        memberAge: this.memberForm.memberAge,
        memberPhone: this.memberForm.memberPhone,
        memberHeight: this.memberForm.memberHeight,
        memberWeight: this.memberForm.memberWeight,
        personalizedSignature:this.memberForm.personalizedSignature,
        memberNo: this.admin.memberNo
      }).then(res => {
        if (res.data.code === 200) {
          alert(res.data.message)
        } else {
          alert(res.data.message)
        }
      }).catch(err => {
        console.log(err.message)
      })
    },
    getMemberByMemberNo() {
      let _this = this
      getMemberByMemberNo({
        memberNo: _this.admin.memberNo
      }).then(res=>{
        this.memberForm = res.data
      }).catch(err=>{
        console.log(err.message)
      })
    }
  }
}
</script>

<style scoped>
.myProfile {
  padding: 40px;
}

.myProfile-form {
  padding: 20px;
  width: 500px;
}
</style>
<template>
  <div>
    <div class="bb">
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm f">
        <el-form-item label="名字" prop="faceSetName" style="margin-left: 1050px;color: white">
          <el-input v-model.number="ruleForm.faceSetName" style="width: 300px"></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="faceSetDescription" style="margin-left: 1050px;color: white">
          <el-input v-model.number="ruleForm.faceSetDescription"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pass" style="margin-left: 1050px;">
          <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass" style="margin-left: 1050px;">
          <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')" style="margin-left: 1050px;">提交</el-button>
          <el-button @click="resetForm('ruleForm')" >重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>

</template>

<script>
import * as axios from 'axios'

export default {
  name: 'Add',
  data() {
    var checkFaceSetName = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('名称不能为空'));
      }
    };
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        faceSetName: '',
        faceSetDescription: '',
        pass: '',
        checkPass: '',
      },
      rules: {
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ],
        FaceSetName: [
          { validator: checkFaceSetName, trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate( async valid => {
        if (valid) {
          this.param = new FormData();
          this.param.append('username',this.$store.getters.getUser.username);
          this.param.append('faceSetName',this.ruleForm.faceSetName);
          this.param.append('faceSetDescription',this.ruleForm.faceSetDescription);
          this.param.append('appkey',this.ruleForm.pass);
          const _this = this;
          axios.post('http://localhost:8086/addfaceset',_this.param,{
            headers:{
              authorization:localStorage.getItem('token')
            }
          })
            .then(function (resp) {
              console.log(resp);
              _this.imgData = resp.data;
            })
          // const {data: res} = await this.$http.post("http://localhost:8086/userlogin",this.form);
          // console.log(res);
          alert('submit success!');

        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style>
.bb{
  width:100%;
  height: 600px;
  /*background-image: url("../../assets/bj/bj.jpg");*/
  background-image: url(../../assets/beijing16.jpg);

}
.f{
  position: fixed;
  margin-top: 80px;
}
.el-form-item{
  color: white;
}
</style>

<template>
  <div class="beijing">
    <!--//图片上传upload-->
    <div style="background-color:rgba(174,191,229,0.21);" class="bb">
      <div class="center">
        <div class="bd">
<!--          <div>{{this.$route.query.facesetid}}</div>-->
          <el-row  type="flex" justify="center">
            <el-col :span="12" :offset="6">
              <div id="upload">
                <div class="upload-image">
                  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="图片名称" prop="faceName">
                      <el-input v-model="ruleForm.faceName" style="width: 100%"></el-input>
                    </el-form-item>
                    <el-form-item label="图片描述" prop="details">
                      <el-input v-model="ruleForm.details" style="width: 100%"></el-input>
                    </el-form-item>
                    <el-form-item label="添加图片" ref="ruleForm" prop="imageUrl">
                      <el-input type="file" v-model="ruleForm.imageUrl" v-if="false"></el-input>
                      <el-upload
                        action="https://jsonplaceholder.typicode.com/posts/"
                        list-type="picture-card"
                        :on-preview="handlePictureCardPreview"
                        :on-remove="handleRemove"
                        :multiple="true"
                        class="avatar-uploader"
                        ref="upload"
                        :before-upload="beforeUpload"
                        :on-change="handleChange"
                        :auto-upload="false"
                        :data="ruleForm">
                        <i class="el-icon-plus"></i>
                      </el-upload>
                      <el-dialog :visible.sync="dialogVisible">
                        <img width="100%" :src="dialogImageUrl" alt="">
                      </el-dialog>
                    </el-form-item>
                    <el-form-item>
                      <el-button @click="beforeupload('ruleForm')">保存</el-button>
                      <el-button type="primary" @click="submitForm('ruleForm')">立即添加</el-button>
                      <el-button @click="resetForm('ruleForm')">重置</el-button>
                    </el-form-item>
                  </el-form>
                </div>
              </div>
            </el-col>

          </el-row>
        </div></div>
    </div>

  </div>
</template>

<script>
import * as axios from "axios";
export default {
  name: "AddFace",
  inject:['reload'],
  data() {
    return{
      fileData:'',
      fileList:[],
      dialogImageUrl: '',
      dialogVisible: false,
      ruleForm: {
        details: '',
        imageUrl:'',
        faceName:'',
      },
      rules: {
        imageUrl: [
          {required: true, message: '请上传图片', trigger: 'blur'},
        ],
      }
    }
  },
  // created() {
  //   获取默认显示的图片
  // this.baseImg = require('../../assets/yun.jpg')
  // },
  created(){
    this.fileData = new FormData();
  },
  methods: {
    handleImport(file,fileList){
      this.fileList=fileList
    },
    handleRemove(file, fileList) {
      // return this.$confirm("确定移除" +file.name+"？");
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    beforeupload(formName){
      console.log(this.ruleForm);
    },
    submitForm(formName,file,fileList) {
      this.param = new FormData();
      for(let item of document.querySelector('input[type=file]').files){
        this.param.append('files',item);
      }
      this.param.append('username', this.$store.getters.getUser.username);
      this.param.append('faceSetId',this.$route.query.facesetid);
      this.param.append('faceInfo',this.ruleForm.details);
      this.param.append('faceName',this.ruleForm.faceName);
      let config = {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      };
      const _this = this;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:8086/addface',this.param,{
            headers:{
              authorization:localStorage.getItem('token')
            }
          })
            .then(function (resp){
              console.log(resp);
              if(resp.data === 'success'){
                _this.$alert('信息已经添加成功！','OK',{
                  confirmButtonText:'确定',
                  callback: action => {
                    _this.reload()
                  }
                });
              }
              else{
                _this.$message({
                  message: '出错啦，请重新上传',
                  type: 'warning'
                });
                _this.reload()
              }
            });
          console.log(this.ruleForm);
          _this.$refs['ruleForm'].resetFields();
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.ruleForm.imageUrl = '';
      this.reload()
    },
    handleChange (file, fileList) {
      this.ruleForm.imageUrl = URL.createObjectURL(file.raw);
    },

    beforeUpload(file) {
      return true;
    },
  }
}

</script>

<style scoped>
  .beijing{
    background-image: url(../../assets/beijing12.jpg);
  }
.bb{
  width:100%;
  height: 600px;
  /*background-image: url("../../assets/bj/bj.jpg");*/
}
.center{
  text-align: center;
}
.bd{

  margin: 0;
  text-align: left;
  padding-top: 100px;
  padding-bottom: 100px;
  padding-right: initial;
  padding-left: initial;
  width: 100%;
}


input[type="file"] {
  display: none;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar {
  width: 178px;
  height: 178px;
  display: none;
}

.upload-image{
  float: left;
}
.el-form{
  margin-left:380px;
  margin-top: 30px;
}
</style>

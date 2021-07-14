<template>
  <div>
    <!--//图片上传upload-->
    <div class="body">
      <div class="center">
        <div class="bd">

          <el-row  type="flex" justify="center">
            <el-col :span="20" :offset="2">
              <div id="upload">
                <div class="upload-image">
                  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="图片描述" prop="details">
                      <el-input v-model="ruleForm.details" style="width: 100%"></el-input>
                    </el-form-item>
                    <el-form-item label="上传图片" ref="ruleForm" prop="imageUrl">
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

                    <el-form-item label="图片类别" prop="type">
                      <el-select v-model="ruleForm.type" placeholder="请选择图片类别">
                        <el-option label="人物" value="1"></el-option>
                        <el-option label="动物" value="2"></el-option>
                        <el-option label="风景" value="3"></el-option>
                        <el-option label="交通" value="4"></el-option>
                        <el-option label="建筑" value="5"></el-option>
                        <el-option label="其他" value="6"></el-option>
                      </el-select>
                    </el-form-item>
                    <el-form-item>
                      <el-button @click="beforeupload('ruleForm')">保存</el-button>
                      <!--<el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>-->

                      <el-button
                        type="primary"
                        @click="submitForm('ruleForm')">
                        立即创建
                      </el-button>

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
  import { Loading } from 'element-ui';

  export default {
    name: "Page1",
    inject:['reload'],
    data() {
      return{
        fullscreenLoading: false,

        fileData:'',
        fileList:[],
        dialogImageUrl: '',
        ruleForm: {
          details: '',
          imageUrl:'',
          type:'',
        },
        rules: {
          imageUrl: [
            {required: true, message: '请上传图片', trigger: 'blur'},
          ],
          type: [
            {required: true, message: '请选择图片类别', trigger: 'change'}
          ],
        }
      }
    },
    // created(){
    //   this.fileData = new FormData();
    // },
    methods: {
      openFullScreen2() {

      },
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
        const loading = this.$loading({
          lock: true,
          text: 'Loading',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        this.param = new FormData();
        for(let item of document.querySelector('input[type=file]').files){
          this.param.append('files',item);
        }
        this.param.append('user', this.$store.getters.getUser.username);
        this.param.append('details',this.ruleForm.details);
        this.param.append('type',this.ruleForm.type);
        let config = {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        };
        const _this = this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios.post('http://localhost:8086/picupload',this.param,{
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
                      _this.reload();
                      loading.close(); // 关闭加载
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
  .body{
    /*background-color:rgba(174,191,229,0.21);*/
    /*background-color: rgb(235, 235, 235);*/
    padding-top: 80px;
    padding-bottom: 100px;
    background-image: url(../../assets/beijing3.jpg);
  }
  .center{
    text-align: center;
  }
  .bd{
    padding-top: 70px;
    padding-bottom: 80px;
    background-color: #fff;
    margin:0 auto;
    text-align: left;
    width: 900px;
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
  .upload-image{
    float: left;
  }

  img{
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
</style>

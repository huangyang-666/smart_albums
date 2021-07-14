<template>
  <div style="width: 100%; height: 2000px;" class="qwe">
    <!--//图片上传upload-->
    <div style="">
      <div class="center">
        <div class="bd">
          <el-row  type="flex" justify="center">
            <el-col :span="10" :offset="14">
              <div id="upload">
                <div class="upload-image">
                  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="美好心情" prop="details">
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

                    <el-form-item>
                      <el-button @click="beforeupload('ruleForm')">保存</el-button>
                      <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                      <el-button @click="resetForm('ruleForm')">重置</el-button>
                    </el-form-item>
                  </el-form>
                </div>
              </div>
            </el-col>
          </el-row>
        </div>
      </div>
    </div>


    <div v-for="(item, index) in showtimes" :key="index">
      <div v-for="it of 1" :key="it">
        <div class="ll">
          <div class="photo-wrapper">
            <div class="photo-img">
                <img :src="'../../../static/smart_photos/discovery/' + item.name" alt="人物">
            </div>
            <h2 class="photo-title">
                {{item.mood}}
            </h2>
            <br>
            <h2 class="photo-title">
              {{item.master}}
            </h2>
            <ul class="photo-list">
              <li>
                <br>
              <a href="#">{{item.date}}</a>
<!--              <el-popover-->
<!--                placement="right"-->
<!--                width="400"-->
<!--                trigger="click">-->
<!--                <el-input></el-input>-->
<!--                <el-button slot="reference" style="float: right"  @click="open"> 评论</el-button>-->
<!--              </el-popover>-->
              <el-button slot="reference" style="float: right"  @click="open"> 评论</el-button>
            </li>
            </ul>
          </div></div>
      </div>
    </div>



  </div>
</template>

<script>
import * as axios from "axios";
export default {
  name: "Page1",
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
        type:'',
      },
      rules: {
        imageUrl: [
          {required: true, message: '请上传图片', trigger: 'blur'},
        ],
        type: [
          {required: true, message: '请选择图片类别', trigger: 'change'}
        ],
      },
      currentDate: new Date(),
      showtimes:[

      ],
    }
  },

  created(){
    this.param = new FormData();
    this.param.append('user',this.$store.getters.getUser.username);
    const _this = this;
    axios.post('http://localhost:8086/queryallshowtimes',_this.param,{
      headers:{
        authorization:localStorage.getItem('token')
      }
    })
      .then(function (resp) {
        console.log(resp);
        _this.showtimes = resp.data;
      })
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
        this.param.append('imgFile',item);
      }
      this.param.append('user', this.$store.getters.getUser.username);
      this.param.append('mood',this.ruleForm.details);
      let config = {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      };
      const _this = this;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:8086/addshowtime',this.param,{
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


    open() {
      this.$prompt('请输入内容', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
      }).then(( ) => {
        this.$message({
          type: 'success',
          message: '你已评论成功 '
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });
      });
    }
  }
}

</script>

<style scoped>
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



.ll{
  float: left;
  border-radius: 10px;
  border: 1px solid #ffffff;
  width: 320px;
  margin: 20px;
  display: inline-block;
  background-color: rgb(255, 255, 255);
}

.photo-wrapper{
  border-radius: 10px;
  padding:0;
  width: 100%;
  background-color: #fff;
  display: inline-block;
  border: 1px rgba(255, 255, 255, 0) solid;
}
.photo-wrapper:hover{
  border-radius: 10px;
  border: 1px solid black;
  padding:0;
  width: 100%;
  background-color: #fff;
  display: inline-block;
}
.photo-title{
  display: inline-block;
  height: 15px;
  line-height: 15px;
  border-left: 3px #19285d solid;
  padding-left: 10px;
  padding-top: 2px;
  padding-bottom: 3px;
  font-size: 20px;
}

.photo-title a{
  color: #404606;
  font-weight: bold;
  text-decoration: none;
}

.photo-img{
  height: 260px;
}

img{
  width: 100%;
  height: 250px;
  border-radius: 10px;
}

.photo-list li{
  margin-left: 20px;
  list-style: none;
  line-height: 15px;
  text-align: left;
  margin-bottom: 10px;
}

.photo-list li::before{
  content: '■';
  color: rgb(218,218,218);
  font-size:14px;
  margin-right: 4px;
}

.photo-list li a{
  font-size: 16px;
  text-decoration: none;
  color: #666;
}
.photo-list li a:hover{
  color: rgb(186, 188, 229);
  list-style: none;
}
  .qwe{
    background: url(../../assets/beijing11.jpg)no-repeat;
  }
</style>

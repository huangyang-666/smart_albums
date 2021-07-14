<template>
  <div class="body">

    <!--//图片上传upload-->
    <div class="body">
      <div class="center">
        <div class="bd">

          <el-row>
            <el-col :span="20" :offset="3">
              <div id="upload">

                <div class="upload-image">
                  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <!--<el-form-item label="图片描述" prop="details">-->
                      <!--<el-input v-model="ruleForm.details" style="width: 80%"></el-input>-->
                    <!--</el-form-item>-->
                    <el-form-item label="上传图片" ref="uploadElement" prop="imageUrl">
                      <el-input v-model="ruleForm.imageUrl" v-if="false"></el-input>
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
                      <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                      <el-button @click="resetForm('ruleForm')">重置</el-button>
                      <el-button @click="downloadexcel('ruleForm')">下载excel表</el-button>
                    </el-form-item>
                  </el-form>
                </div>
                <div>

                </div>

              </div>
            </el-col>

          </el-row>
        </div>
      </div>
    </div>

    <el-table
      highlight-current-row
      :data="tableData"
      tooltip-effect="dark"
      height="450"
      style="width: 300px;margin-left: 200px;float: left;display: inline-block"
      @cell-click="showtable">
      <el-table-column
        prop="name"
        label="图片名称"
        width="300">
        <template slot-scope="scope">
          {{scope.row.originname}}
        </template>
      </el-table-column>
    </el-table>

    <div class="tpzs">
      <!--<img :src="'../../../static/smart_photos/allimgs/'+item.nowname ? '../../../static/smart_photos/allimgs/'+item.nowname : squareUrl" style="width: 100%">-->
      <img :src="'../../../static/smart_photos/allimgs/'+item.nowname ? '../../../static/smart_photos/allimgs/'+item.nowname : squareUrl" style="width: 100%">
    </div>

    <div class="typeouter">
      <div class="type">图片类别</div>
      <div class="type1">{{item.type}}</div>
    </div>
    <div class="typeouter">
    <!--<div class="type1">-->
      <!--<el-button @click="downloadexcel()">下载excel表</el-button>-->
    <!--</div>-->
    </div>

    <!--<div class="typeouter">-->
      <!--<div class="type">图片类别</div>-->
      <!--<div class="type1">{{item.type}}</div>-->
    <!--</div>-->

    <div class="all"></div>
  </div>
</template>


<script>
  import * as axios from "axios";

  export default {
    name: "Page1_2",
    inject: ['reload'],
    data() {
      return {
        item:'',
        items:'',
        squareUrl:'',
        imgshow:'',
        tableData: [
          {
          nowname:'bz1.jpg',type:'风景',originname:'bz1.jpg',
        },
          {nowname:'002.jpg',type:'动物'},{nowname:'003.jpg',type:'建筑'},{nowname:'004.jpg',type:'风景'},{nowname:'005.jpg',type:'风景'},
          {
            nowname:'bz2.jpg',type:'风景'
          },
          {nowname:'bz3.jpg',type:'建筑'},{nowname:'bz4.jpg',type:'建筑'},{nowname:'bz5.jpg',type:'动物'},{nowname:'bz6.jpg',type:'动物'},
          {
            name:'edcde',type:'风景'
          },
          {name:'2.jpg',type:'风景'},{name:'56789.png',type:'风景'},{name:'345678987ytdxdfghjkuytr4esxcvhuytfcvb',type:'风景'},{name:'r56tgjki8ikmn',type:'风景'},
        ],
        search: '',
        arr: [],
        imgPath: [],



        fileData: '',
        fileList: [],
        dialogImageUrl: '',
        dialogVisible: false,
        ruleForm: {
          details: '',
          imageUrl: '',
        },
        rules: {
          imageUrl: [
            {required: true, message: '请上传图片', trigger: 'blur'},
          ],
        },
        coomonlist: {
          filename:'',
          type:'',
          fileSize:'',
          master:'',
          date:'',
          faceSetId:'',
          faceId:'',
        },
        face: {
          filename:'',
          type:'',
          fileSize:'',
          master:'',
          date:'',
          faceSetId:'',
          faceId:'',
        },
        newface:{
          filename:'',
          type:'',
          fileSize:'',
          master:'',
          date:'',
          faceSetId:'',
          faceId:'',
        },

      }
    },
    created() {
      // 获取默认显示的图片
      this.squareUrl = require('../../assets/tubiao/touxiang.png')
    },
    methods: {
      showtable(row){
        console.log(row.originname);
        console.log(row.nowname);
        console.log(row.type);
        this.item = row;
        this.items = row.type;
        // showtype();
      },
      showtype(row){
        console.log(row.name);
        console.log(row.type);
        console.log(row.name);
        this.item = row;
        // showtype(row);
      },
      handleRemove(file, fileList) {
        // return this.$confirm("确定移除" +file.name+"？");
        console.log(file, fileList);
        this.reload()
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      },
      downloadexcel(formName) {
        this.param = new FormData();
        this.param.append('user', this.$store.getters.getUser.username);
        const _this = this;
        _this.$refs[formName].validate((valid) => {
          if (valid) {
            axios.post('http://localhost:8086/getExcelDownload', _this.param,{responseType:'blob'})
              .then(resp=> {
                console.log(resp);
                let data = resp.data;
                let url = window.URL.createObjectURL(new Blob([data]));
                let link = document.createElement('a');
                link.style.display = 'none';
                link.href = url;
                link.setAttribute('download', 'outcome.xls');
                document.body.appendChild(link);
                link.click()
              });
          }
        })
      },
      submitForm(formName) {
        this.param = new FormData();
        this.param.append('user', this.$store.getters.getUser.username);
        for (let item of document.querySelector('input[type=file]').files) {
          this.param.append('files', item);
        }// 'files' 这个名字要和后台获取文件的名字一样;
        // this.param.append('details', this.ruleForm.details);
        let config = {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        };
        console.log(this.param);
        const _this = this;
        this.$refs[formName].validate((valid) => {
            if (valid) {
              axios.post('http://localhost:8086/facedetect', _this.param)
                .then(function (resp) {
                  console.log(resp);

                  _this.tableData = resp.data;
                  // _this.coomonlist = resp.data.common;
                  // _this.face = resp.data.face;
                  // _this.newface = resp.data.newface;
                  console.log(resp);
                  // if (resp.status === 200) {
                  //   try {
                  //     console.log("../" + resp.data);
                  //     if (typeof this.loadImage !== "undefined") {
                  //       this.loadImage =
                  //         "http:.../getImage/" + resp.data;
                  //     }
                  //   } catch (e) {
                  //     console.log("捕获到异常：", e);
                  //   }
                  // } else {
                  //   console.log("失败" + 400);
                  // }

                  // });
                  // _this.$refs['ruleForm'].resetFields();
                  // } else {
                  //   return false;
                  // }
                });
            }
          });
      },

      resetForm(formName) {
        this.$refs[formName].resetFields();
        this.ruleForm.imageUrl = '';
        this.reload()
      },
      handleChange(file, fileList) {
        this.ruleForm.imageUrl = URL.createObjectURL(file.raw);
      },

      beforeUpload(file) {
        return true;
      },
    }
  }

</script>

<style scoped>
  .all{
    clear: both;
    width: 100%;
  }
  .body{
    background-color:rgba(155,200,229,0.21);
    padding-top: 60px;
    padding-bottom: 60px;
    /*background-image: url('../../assets/001.jpg');*/
  }
  .center {
    text-align: center;
  }

  .bd {
    padding-top: 60px;
    padding-bottom: 60px;
    background-color: #fff;
    margin-left: 24%;
    margin-bottom:100px;
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

  .tpzs{
    border-radius: 10px;
    width: 400px;
    height: 300px;
    float: left;
    display: inline-block;
    background-color: #9bb7ff;
    margin-left: 20px;
    overflow: hidden;
  }
  .tpzs img{
    height: 100%;
    width: 100%;
    object-fit:cover;
    border-radius: 10px;
  }
  .typeouter{
    margin-top: 20px;
    width: 200px;
    float: left;
    margin-left: 10px;
    border: 1px solid black;
    border-radius: 6px;
    background-color: #fff;
  }
  .type{
    padding-left: 20px;
    width: 180px;
    line-height: 40px;
    color: #666666;
    font-size: 16px;
    border-bottom: 1px solid gainsboro;
  }
  .type1{

    padding-left: 20px;
    width: 200px;
    line-height: 40px;
    color: #000000;
    font-size: 20px;
  }

</style>


<template>
  <div>
    <!--<HelloWorld/>-->
    <!--<video id="myVedio" autoplay="autoplay" controls="controls" width="100%" src="../../assets/video/videoo.mp4">-->
      <!---->
    <!--</video>-->
    <div class="image_box">
      <div class="center">
        <div class="tbox1">
          <el-carousel height="200px">
            <el-carousel-item v-for="item in imglbList" :key="item.id">
              <img :src="item.url" alt="" class="image">
              <div class="font">
                <h5>{{item.title}}</h5>
              </div>
            </el-carousel-item>
          </el-carousel>
        </div>
      </div>
    </div>

    <el-menu
      :default-active="activeIndex2"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#03b0ee">
      <el-menu-item index="0"><router-link to="/Home1"><i class="el-icon-picture-outline-round"></i><strong>零点云相册</strong></router-link></el-menu-item>
      <el-menu-item index="0-1"><router-link to="/Home1"><i class="el-icon-s-home"></i>回到首页</router-link></el-menu-item>
      <el-menu-item index="1"><router-link to="/all"><i class="el-icon-copy-document"></i>图片管理</router-link></el-menu-item>
      <!--<el-submenu index="2">-->
        <!--<el-menu-item index="2"><router-link to="/all"><i class="el-icon-copy-document"></i>图片管理</router-link></el-menu-item>-->
      <!--</el-submenu>-->
      <el-submenu index="2">
        <template slot="title">图片上传</template>
          <el-menu-item index="2-2-1"><router-link :to="{name:'Page1'}">
            <i class="el-icon-upload"></i>普通上传</router-link></el-menu-item>
          <el-menu-item index="2-2-2"><router-link to="/loads">
            <i class="el-icon-s-management"></i>智能识别 </router-link></el-menu-item>
      </el-submenu>
        <el-menu-item index="3"><router-link to="/type">
          <i class="el-icon-picture"></i>相册类别</router-link></el-menu-item>
        <el-menu-item index="4"><router-link to="/recognition"><i class="el-icon-s-custom"></i>人脸库</router-link></el-menu-item>
        <el-menu-item index="5"><router-link to="/moment"><i class="el-icon-video-camera-solid"></i>精彩时刻</router-link></el-menu-item>
        <el-menu-item index="6"><router-link to="/test"><i class="el-icon-magic-stick"></i>发现动态</router-link></el-menu-item>
      <!--<el-menu-item index="3"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>-->
      <!--<el-menu-item index="7"><router-link to="/test"><i class="el-icon-magic-stick"></i>发现动态</router-link></el-menu-item>-->
      <el-menu-item index="11"></el-menu-item>
      <el-menu-item index="11"></el-menu-item>
      <el-menu-item index="11"></el-menu-item>
      <el-menu-item index="11"></el-menu-item>
      <el-menu-item index="11"></el-menu-item>
      <el-menu-item index="11"></el-menu-item>
      <el-menu-item index="11"></el-menu-item>
      <el-menu-item index="11"></el-menu-item>
      <el-menu-item index="11"></el-menu-item><el-menu-item index="11"></el-menu-item>

      <el-submenu index="7">
        <template slot="title">设置</template>
        <el-menu-item index="7-1"><router-link to="/password">修改密码</router-link></el-menu-item>
        <el-menu-item index="7-2"><router-link to="/Home1">回到首页</router-link></el-menu-item>
        <el-menu-item index="7-3"><router-link to="/logout">退出登录</router-link></el-menu-item>
      </el-submenu>
      <el-menu-item index="8"><span>{{$store.getters.getUser.username}}</span></el-menu-item>
      <el-menu-item index="9"> <div class="demo-type">
        <!--<el-avatar :size="46" src="https://empty" @error="errorHandler">-->
        <el-avatar :size="46" :src="squareUrl?squareUrl:imgUrl" class="img">
          <!--<img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>-->
        </el-avatar>
      </div></el-menu-item>
      <el-menu-item index="10"> </el-menu-item>
    </el-menu>
      <!--<div class="pg-header">-->
        <!--<div class="w">-->
          <!--<ul>-->
            <!--<li class="l1"><i class="el-icon-picture-outline-round"></i><strong>零点云相册</strong></li>-->
            <!--<li><router-link to="/Home1"><i class="el-icon-s-home"></i>回到首页</router-link></li>-->
            <!--<li><router-link to="/all"><i class="el-icon-copy-document"></i>图片管理</router-link></li>-->
            <!--<li><router-link :to="{name:'Page1'}">-->
              <!--<i class="el-icon-upload"></i>图片上传 </router-link></li>-->
            <!--<li><router-link to="/loads">-->
            <!--<i class="el-icon-s-management"></i>智能识别 </router-link></li>-->
            <!--&lt;!&ndash;<li><router-link to="/show">&ndash;&gt;-->
              <!--&lt;!&ndash;<i class="el-icon-s-management"></i>show</router-link></li>&ndash;&gt;-->
            <!--<li><router-link to="/type">-->
              <!--<i class="el-icon-picture"></i>相册类别</router-link></li>-->
            <!--<li><router-link to="/recognition"><i class="el-icon-s-custom"></i>人体识别</router-link></li>-->
            <!--<li><router-link to="/moment"><i class="el-icon-video-camera-solid"></i>精彩时刻</router-link></li>-->
            <!--<li><router-link to="/test"><i class="el-icon-magic-stick"></i>发现</router-link></li>-->
          <!--</ul>-->
          <!--<ul class="il">-->
            <!--<li style="width: 16px;padding:0 30px">-->
              <!--<el-dropdown>-->
                <!--<i class="el-icon-setting" style="margin-right: 15px"></i>-->
                <!--<el-dropdown-menu slot="dropdown">-->
                  <!--<el-dropdown-item class="el-aside">-->
                    <!--<router-link to="/Home1">-->
                      <!--<el-button type="text">回到首页</el-button>-->
                    <!--</router-link>-->
                  <!--</el-dropdown-item>-->
                  <!--<el-dropdown-item>-->
                    <!--<router-link to="/logout">-->
                      <!--<el-button type="text">退出登录</el-button>-->
                    <!--</router-link>-->
                  <!--</el-dropdown-item>-->
                <!--</el-dropdown-menu>-->
              <!--</el-dropdown>-->
            <!--</li>-->
            <!--<li><a href="#">-->
              <!--<span>{{$store.getters.getUser.username}}</span>-->
            <!--</a></li>-->

            <!--<li>-->
              <!--<div class="demo-type">-->
                <!--&lt;!&ndash;<el-avatar :size="46" src="https://empty" @error="errorHandler">&ndash;&gt;-->
                  <!--<el-avatar :size="46" :src="squareUrl?squareUrl:imgUrl" class="img">-->
                  <!--&lt;!&ndash;<img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>&ndash;&gt;-->
                  <!--</el-avatar>-->
              <!--</div>-->
            <!--</li>-->
            <!--<li>-->
              <!--<el-button type="text" @click="dialog = true">上传头像</el-button>-->
              <!--<el-drawer-->
                <!--title="上传图片"-->
                <!--:before-close="handleClose"-->
                <!--:visible.sync="dialog"-->
                <!--direction="rtl"-->
                <!--custom-class="demo-drawer"-->
                <!--ref="drawer"-->
              <!--&gt;-->
                <!--<div class="demo-drawer__content">-->
                  <!--<el-form :model="form">-->

                    <!--<el-form-item label="上传图片" ref="form" prop="imageUrl">-->
                      <!--<el-input type="file" v-model="form.imageUrl" v-if="false"></el-input>-->
                      <!--<el-upload-->
                        <!--action="https://jsonplaceholder.typicode.com/posts/"-->
                        <!--list-type="picture-card"-->
                        <!--:on-preview="handlePictureCardPreview"-->
                        <!--:on-remove="handleRemove"-->
                        <!--class="avatar-uploader"-->
                        <!--ref="upload"-->
                        <!--:before-upload="beforeUpload"-->
                        <!--:on-change="handleChange"-->
                        <!--:auto-upload="false"-->
                        <!--:data="form">-->
                        <!--<i class="el-icon-plus"></i>-->
                      <!--</el-upload>-->
                      <!--<el-dialog :visible.sync="dialogVisible">-->
                        <!--<img width="100%" :src="dialogImageUrl" alt="">-->
                      <!--</el-dialog>-->
                    <!--</el-form-item>-->
                    <!--&lt;!&ndash;<el-form-item label="活动名称" :label-width="formLabelWidth">&ndash;&gt;-->
                      <!--&lt;!&ndash;<el-input v-model="form.name" autocomplete="off"></el-input>&ndash;&gt;-->
                    <!--&lt;!&ndash;</el-form-item>&ndash;&gt;-->
                    <!--&lt;!&ndash;<el-form-item label="活动区域" :label-width="formLabelWidth">&ndash;&gt;-->
                      <!--&lt;!&ndash;<el-select v-model="form.region" placeholder="请选择活动区域">&ndash;&gt;-->
                        <!--&lt;!&ndash;<el-option label="区域一" value="shanghai"></el-option>&ndash;&gt;-->
                        <!--&lt;!&ndash;<el-option label="区域二" value="beijing"></el-option>&ndash;&gt;-->
                      <!--&lt;!&ndash;</el-select>&ndash;&gt;-->
                    <!--&lt;!&ndash;</el-form-item>&ndash;&gt;-->
                  <!--</el-form>-->
                  <!--<div class="demo-drawer__footer">-->
                    <!--&lt;!&ndash;<el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>&ndash;&gt;-->
                    <!--&lt;!&ndash;<el-button @click="resetForm('ruleForm')">重置</el-button>&ndash;&gt;-->

                    <!--<el-button @click="cancelForm">取 消</el-button>-->
                    <!--<el-button type="primary" @click="$refs.drawer.closeDrawer()" :loading="loading">{{ loading ? '提交中 ...' : '确 定' }}</el-button>-->
                  <!--</div>-->
                <!--</div>-->
              <!--</el-drawer>-->
            <!--</li>-->
            <!--&lt;!&ndash;<el-image&ndash;&gt;-->
            <!--&lt;!&ndash;style="width: 100px; height: 100px"&ndash;&gt;-->
            <!--&lt;!&ndash;:src="'../../../static/smart_photos/allimgs/'+item.filename">&ndash;&gt;-->
            <!--&lt;!&ndash;&lt;!&ndash;:fit="fit"&ndash;&gt;&ndash;&gt;-->
            <!--&lt;!&ndash;</el-image>&ndash;&gt;-->
          <!--</ul>-->
        <!--</div>-->
      <!--</div>-->

    <div class="body">
      <router-view/>
    </div>

    <div class="all">

    </div>
    <div class="footer">
      <div><br></div>
      <div>
        <nav class="left-nav">
          <div class="item">
            <div class="cp"><a href="#">回到首页</a></div>
          </div>
        </nav>
      </div>

      <div>
        <nav class="left-nav">
          <div class="item">
            <div class="jia"><a href="#">功能分类</a></div>
          </div>
        </nav>
      </div>
      <div>
        <nav class="left-nav">
          <div class="item">
            <div class="cp"><a href="#">关于我们</a></div>
          </div>

        </nav>
      </div>
      <div>
        <nav class="left-nav">
          <div class="item">
            <div class="jia"><a href="">联系我们</a></div>
          </div>
        </nav>
      </div>
      <div class="look1">
        <img class="look" id="dy" src=../../assets/tubiao/dy1.png>
        <img class="look" id="qq" src=../../assets/tubiao/qq1.png>
        <img class="look" id="tuit" src=../../assets/tubiao/tuit1.png>
        <img class="look" id="wb" src=../../assets/tubiao/wb1.png>
        <img class="look" id="wx" src=../../assets/tubiao/wx1.png>
      </div>
      <div class="center">
        <div class="foot1">
          <div class="foot">
            <div class="footwz">copyright © Cloud photo album Terminal Co., Ltd 2021-2021.All Rights Reserved.</div>
            <div class="footwz1">网站地图</div>
            <div class="footwz1">使用条款</div>
            <div class="footwz1">隐私声明</div>
            <div class="footwz1">关于cookies</div>
            <div class="footwz1">法律信息</div>
          </div>
        </div>
      </div>
      <section>
        <div class="wave wave1"></div>
        <div class="wave wave2"></div>
        <div class="wave wave3"></div>
        <div class="wave wave4"></div>
      </section>
    </div>
  </div>
</template>

<script>
  import * as axios from "axios";
    export default {
      name: "Home.vue",
      data() {
        // return{
        //   fileData:'',
        //   fileList:[],
        //   dialogImageUrl: '',
        //   ruleForm: {
        //     details: '',
        //     imageUrl:'',
        //     type:'',
        //   },
        //   rules: {
        //     imageUrl: [
        //       {required: true, message: '请上传图片', trigger: 'blur'},
        //     ],
        //     type: [
        //       {required: true, message: '请选择图片类别', trigger: 'change'}
        //     ],
        //   }
        // }
        return {
          imglbList: [
            {
              id: '1',
              url: require('../../assets/lunbo/lbtt.jpg'),
              title:'Focus on deep learning algorithm, ' +
              'develop industry data applications based on artificial intelligence, ' +
              'big data, cloud computing and other new technologies',
            },
            {
              id: '2',
              url: require('../../assets/lunbo/lunbo00.jpg'),
              title:'Focus on deep learning algorithm, develop industry data applications based on artificial intelligence, big data, cloud computing and other new technologies',
            },
            {
              id: '3',
              url: require('../../assets/lunbo/hua.jpg'),
              title:'Focus on deep learning algorithm, develop industry data applications based on artificial intelligence, big data, cloud computing and other new technologies',
            }
          ],
          // squareUrl: "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
          squareUrl: '',
          imgUrl: '',

          fileData: '',
          fileList: [],
          dialogImageUrl: '',
          table: false,
          dialog: false,
          loading: false,
          form: {
            imageUrl:'',
          },
          formLabelWidth: '80px',
          timer: null,
        }
      },
      created() {
        // 获取默认显示的图片
        this.imgUrl = require('../../assets/tubiao/mrtouxiang.png')
      },
      methods: {
        handleClose(done) {
          if (this.loading) {
            return;
          }
          this.$confirm('确定要提交表单吗？')
            .then(_ => {

              // this.param = new FormData();
              // for(let item of document.querySelector('input[type=file]').files){
              //   this.param.append('files',item);
              // }
              // this.param.append('user', this.$store.getters.getUser.username);
              // let config = {
              //   headers: {
              //     'Content-Type': 'multipart/form-data'
              //   }
              // };
              // const _this = this;
              // this.$refs[formName].validate((valid) => {
              //   if (valid) {
              //     axios.post('http://localhost:8086/picupload',this.param)
              //       .then(function (resp){
              //         console.log(resp);
              //         if(resp.data === 'success'){
              //           _this.$alert('信息已经添加成功！','OK',{
              //             confirmButtonText:'确定',
              //             callback: action => {
              //               _this.reload()
              //             }
              //           });
              //         }
              //         else{
              //           _this.$message({
              //             message: '出错啦，请重新上传',
              //             type: 'warning'
              //           });
              //           _this.reload()
              //         }
              //       });
              //     console.log(this.ruleForm);
              //     _this.$refs['ruleForm'].resetFields();
              //   } else {
              //     return false;
              //   }
              // });

              this.loading = true;
              this.timer = setTimeout(() => {
                done();
                // 动画关闭需要一定的时间
                setTimeout(() => {
                  this.loading = false;
                }, 400);
              }, 2000);
            })
            .catch(_ => {});
        },

        cancelForm() {
          this.loading = false;
          this.dialog = false;
          clearTimeout(this.timer);
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


      }
    }

</script>

<style scoped>
  .tbox1{
    height: 180px;
    overflow: hidden;
  }
  .image{
    width: 100%;
  }
  .font{
    position: absolute;
    left: 10rem;
    bottom: 3rem;
    color: #ffffff;
    font-size: 20px;
  }
  .pg-header{
    margin: 0;
    height: 65px;
    background-color: rgb(66, 66, 66);
    line-height: 40px;
    width: 100%;
    border-bottom: 2px solid white;
  }
  .w{
    width: 90%;
    margin: 0 auto;
    /* background-color: rgb(8, 64, 246); */
  }
  .w ul{
    margin: 0;
    list-style-type: none;
  }
  .w ul li{
    border-radius: 8%;
    height: 50px;
    line-height: 50px;
    float: left;
    margin: 7px 2px 0 2px;
    padding: 0 10px 0 10px;
    color: rgb(95, 148, 226);
    cursor: pointer;
    font-size: 15px;
    font-family: Arial, Helvetica, sans-serif;
  }
  .l1{
    font-size: 20px;
  }
  .il{
    float: right;
    padding: 0 10px 0 10px;
    color: #ffffff;
    cursor: pointer;
  }
  .w ul li:hover{
    background-color: rgba(198, 218, 241, 0.22);
    color: #b0e0ff;
  }

  .pg-header .w ul li a{
    color: rgb(226, 226, 226);
    text-decoration: none;
  }
  .w ul li a:hover{
    background-color: rgba(255, 255, 255, 0);
    color: #ffffff;
  }

  .center{
    text-align: center;
  }
  .footer{
    clear: both;
    float: none;
    margin: 0;
    padding: 0;
    width: 100%;
    background-color: rgb(55, 55, 55);
    border:1px solid #ffffff;
  }
  .look1{
    margin:3px 50px;
    float: right;
  }
  .look{
    float: right;
    width: 22px;
    margin: 2px;
    padding: 2px;
  }
  #dy:hover{
    content: url(../../assets/tubiao/dy2.png);
  }
  #qq:hover{
    content: url(../../assets/tubiao/qq2.png);
  }
  #tuit:hover{
    content: url(../../assets/tubiao/tuit2.png);
  }
  #wb:hover{
    content: url(../../assets/tubiao/wb2.png);
  }
  #wx:hover{
    content: url(../../assets/tubiao/wx2.png);
  }
  .foot1{
    width: 100%;
  }
  .foot{
    width: 96%;
    clear: both;
    overflow: hidden;
    margin: 0;
    display: inline-block;
    border-top: 1px solid whitesmoke;
  }
  .footwz{
    float: left;
    margin: 3px;
    color: white;
    font-size: 14px;
  }
  .footwz1{
    float: right;
    margin: 3px 10px;
    color: white;
    font-size: 14px;
  }
  .cp{
    display: inline-block;
    margin-left:0;
    padding-left: 2px;
    width: 70px;
    height: 20px;
    background-color: rgb(55, 55, 55);
  }
  .jia{
    display: inline-block;
    margin-left:0;
    width: 70px;
    height: 20px;
    background-color: rgb(55, 55, 55);
  }

  /* 设置菜单外部容器 */
  .left-nav{
    width: 100px;
    height: 20px;
    padding-top: 0;
    padding-left: 25px;
    padding-right: 25px;
    margin: 0;
    background-color: rgb(55, 55, 55);
    float: left;
    clear: none;
  }
  /* 设置菜单内部item */
  .left-nav .item{
    height: 20px;
    /* 使文字在父元素中居中：父元素line-height设置与height一样 */
    line-height: 20px;
    padding-left: 18px;
  }
  /* 鼠标移入 */
  .item:hover{
    cursor: text;
  }
  a{
    font-size: 16px;
    color: rgb(255, 252, 255);
    /* 去除下划线 */
    text-decoration: none;
  }
  a:hover{
    color: rgb(255, 255, 255);
    cursor: pointer;
    font-size: 17px;
  }
  a:active{
    color: rgba(251, 251, 251, 0.917);
  }

  ul li a{
    font-size: 16px;
    color: rgba(255, 255, 255, 0.8);
    /* 去除下划线 */
    text-decoration: none;
  }
  ul li a:hover{
    color: rgb(255, 255, 255);
    cursor: pointer;
  }
  ul li a:active{
    color: rgba(255, 255, 255, 0.91);
  }


  section {
    position: relative;
    width: 100%;
    height: 30px;
    background-color: rgb(55, 55, 55);
    /*background-color: rgb(186, 213, 220);*/
    overflow: hidden;
  }

  section .wave {
    width: 100%;
    height: 20px;
    position: absolute;
    bottom: 0;
    left: 0;
    background: url(../../assets/wave.png);
    background-size: 1000px 20px;
  }

  section .wave.wave1 {
    animation: animate1 20s linear infinite;
    z-index: 1000;
    opacity: 1;
    animation-delay: 0s;
    bottom: 0;
  }

  section .wave.wave2 {
    animation: animate2 15s linear infinite;
    z-index: 999;
    opacity: 0.5;
    animation-delay: -5s;
    bottom: 3px;
  }

  section .wave.wave3 {
    animation: animate1 10s linear infinite;
    z-index: 998;
    opacity: 0.2;
    animation-delay: -7s;
    bottom: 5px;
  }

  section .wave.wave4 {
    animation: animate2 2s linear infinite;
    z-index: 997;
    opacity: 0.71;
    animation-delay: -5s;
    bottom: 6px;
  }

  @keyframes animate1 {
    0% {
      background-position-x: 0;
    }
    100% {
      background-position-x: 1000px;
    }
  }

  @keyframes animate2 {
    0% {
      background-position-x: 0;
    }
    100% {
      background-position-x: -1000px;
    }
  }
  .body{
    background-color: #f9f9f9;
  }
  .all{
    width: 100%;
    clear: both;
  }
</style>

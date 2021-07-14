<!--全部图片展示-->
<template>
  <!--全部图片-->
  <div style="background-color: rgb(235, 235, 235);">

    <div class="center">
      <table border="1">
        <tr>
          <div class="demo-image">
            <el-tooltip effect=" " placement="right"
                        v-for="item in imgData.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                        :key="item"
            >
              <!--              <div class="block" v-for="(item,index) in imgData" :key="index">-->
              <div class="left">
                <el-col :span="5" v-for="(o, index) in 1" :key="o" :offset="index > 0 ? 4 : 0">
                  <el-image
                    style="width: 200px; height: 200px"
                    :src="'../../../static/smart_photos/allimgs/'+item"
                    :preview-src-list="['../../../static/smart_photos/allimgs/'+item]"
                    :fit="fit"></el-image>
                  <!--<div class="caozuo">-->
                    <!--<i class="el-icon-edit"></i>-->
                    <!--<i class="el-icon-delete"></i>-->
                  <!--</div>-->
                </el-col>
              </div>
              <!--              </div>-->
            </el-tooltip>
          </div>
        </tr>
      </table>
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="pagesize"
        :total="imgData.length">
      </el-pagination>
    </div>


    <!--设置底部距离的-->
    <el-backtop :bottom="230"> </el-backtop>
    <!--带文字的-->
    <el-backtop :bottom="275">
      <div class="el1">
        top
      </div>
    </el-backtop>
  </div>
</template>

<script>
  import * as axios from "axios";
  export default {
    name: "Page0",
    data() {
      return {
        fit: 'cover',
        dialogImageUrl: '',
        dialogVisible: false,
        imgData:[],
        currentPage: 1,
        pagesize: 24

      };
    },
    created() {
      this.param = new FormData();
      this.param.append('user',this.$store.getters.getUser.username);
      const _this = this;
      axios.post('http://localhost:8086/showallimgs',_this.param,{
        headers:{
          authorization:localStorage.getItem('token')
        }
      })
        .then(function (resp) {
          console.log(resp);
          _this.imgData = resp.data;
        })
    },
    mounted: function () {
      this.loadPics()
    },
    methods: {
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      },
      loadPics () {
        var _this = this
        this.$axios.get('/picsList').then(resp => {
          if (resp && resp.status === 200) {
            _this.pics = resp.data
          }
        })
      },
      handleCurrentChange: function (currentPage) {
        this.currentPage = currentPage
        console.log(this.currentPage)
      }
    }
  }
</script>

<style scoped>
  /*相册*/
  .el-image{
    margin-top: 20px;
    margin-right:30px ;
    /*margin-bottom: 20px;*/
    border-radius: 30px;
  }
  .left{
    margin-left: 180px;
  }
  div.img {
    border: 1px solid #ccc;
  }

  div.img:hover {
    border: 1px solid #777;
  }

  div.img img {
    width: 100%;
    height: auto;
  }

  div.desc {
    padding: 15px;
    text-align: center;
  }

  * {
    box-sizing: border-box;
  }


  @media only screen and (max-width: 1080px){
    .responsive {
      width: 49.99999%;
      margin: 6px 0;
    }
  }
  @media only screen and (max-width: 700px){
    .responsive {
      width: 49.99999%;
      margin: 6px 0;
    }
  }

  @media only screen and (max-width: 500px){
    .responsive {
      width: 100%;
    }
  }

  .clearfix:after {
    content: "";
    display: table;
    clear: both;
  }

  table{
    border:1px solid transparent;
    width: 80%;
    text-align: center;
    margin:auto;
  }
  /*td{*/
  /*height: 300px;*/
  /*width: 30%;*/
  /*}*/
  .center{
    text-align: center;
  }
  .el1{
    height: 100%;
    width: 100%;
    background-color: #f2f5f6;
    box-shadow: 0 0 6px rgba(0,0,0, .12);
    text-align: center;
    line-height: 40px;
    color: #1989fa;
  }
  .demo-image{
    background-color: #fff;
  }
  .caozuo{
    background-color: rgba(255, 255, 255, 0);
    line-height: 20px;
    font-size: 20px;
  }
</style>

<template>
  <!--//相册类别-->
  <!--path: '/type',-->
  <div style="    background-color: rgb(235, 235, 235);">
    <div class="center">
      <div id="all">
        <div v-for="(item, index) in faceSetIds" :key="index">
          <div v-for="it of 1" :key="it">
            <div class="ll">
              <div class="photo-wrapper">
                <div class="photo-img">
                  <router-link :to="'/face?facesetid=' + item.facesetid">
                    <img src="../../assets/image/face.jpg" alt="人物">
                  </router-link>
                </div>
                <h2 class="photo-title">
                  <router-link :to="'/face?facesetid=' + item.facesetid">
                    {{item.faceSetName}}
                  </router-link>
                </h2>
                <ul class="photo-list">
                  <li>
                    <a href="#">{{item.details}}</a>
                  </li><li>
                  <a href="#">{{item.date}}</a>
                </li>
                </ul>
              </div></div>
          </div>

        </div>


        <div class="ll">
          <div class="photo-wrapper">
            <div class="photo-img">
              <router-link to="/Add">
                <img src="../../assets/bz1.jpg" alt="更多">
              </router-link>
            </div>
            <h2 class="photo-title">
              <router-link to="/Add">
                添加人脸库
              </router-link>
            </h2>
            <ul class="photo-list">
              <li><a href="#">添加</a></li>
              <li><a href="#">更多</a></li>
            </ul>
          </div></div>
      </div>

    </div>
  </div>
</template>

<script>
import * as axios from 'axios'

export default {
  name: "Page3",
  data(){
    return{
      // list: [{
      //   date: '2016-05-02',
      //   name: 'AAA',
      //   address: '上海市普陀区金沙江路 1518 弄'
      // }, {
      //   date: '2016-05-04',
      //   name: 'BBB',
      //   address: '上海市普陀区金沙江路 1517 弄'
      // }, {
      //   date: '2016-05-01',
      //   name: 'CCCC',
      //   address: '上海市普陀区金沙江路 1519 弄'
      // }, {
      //   date: '2016-05-03',
      //   name: 'DDDD',
      //   address: '上海市普陀区金沙江路 1516 弄'
      // }],
      faceSetIds:[],
    }
  },
  created() {
    this.param = new FormData();
    this.param.append('username',this.$store.getters.getUser.username);
    const _this = this;
    axios.post('http://localhost:8086/queryfaceSets',_this.param,{
      headers:{
        authorization:localStorage.getItem('token')
      }
    })
      .then(function (resp) {
        console.log(resp);
        _this.faceSetIds = resp.data;
      })
  },
}
</script>

<style scoped>
.center{
  text-align: center;
  padding-left: 10%;
  padding-right: 10%;
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
  padding-bottom: 13px;
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
  border-radius: 10px;
}
.photo-list li{
  margin-left: 20px;
  list-style: none;
  line-height: 15px;
  text-align: left;
  margin-bottom: 10px;
  margin-top: 10px;
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
#all{
  width: 100%;
  margin-left: 120px;
  height:3000px
}
</style>

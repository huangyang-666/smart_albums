<template>
  <!--//精彩时刻-->
  <!--path: '/type',-->
  <div style="    background-color: rgb(235, 235, 235);">
    <div class="center">
      <div class="ll">
        <div class="photo-wrapper">
          <div class="photo-img">
            <router-link to="/Character1">
              <img src="../../assets/video/video1.jpg" alt="我的生活">
            </router-link>
          </div>
          <h2 class="photo-title">
            <router-link to="/Character1">
              我的生活
            </router-link>
          </h2>
          <ul class="photo-list">
            <li>
              <a href="#">My life</a>
            </li>
          </ul>
        </div></div>
      <div class="ll">
        <div class="photo-wrapper">
          <div class="photo-img">
            <router-link to="/Sceneries1">
              <img src="../../assets/video/video2.jpg" alt="怡人景色">
            </router-link>
          </div>
          <h2 class="photo-title">
            <router-link to="/Sceneries1">
              怡人景色
            </router-link>
          </h2>
          <ul class="photo-list">
            <li>
              <a href="#">Pleasant scenery</a>
            </li>
          </ul>
        </div></div>

      <div class="ll">
        <div class="photo-wrapper">
          <div class="photo-img">
            <router-link to="/Vehicles1">
              <img src="../../assets/video/video3.jpg" alt="愉快旅途">
            </router-link>
          </div>
          <h2 class="photo-title">
            <router-link to="/Vehicles1">
              愉快旅途
            </router-link>
          </h2>
          <ul class="photo-list">
            <li><a href="#">Happy journey</a></li></ul>
        </div></div>

      <div class="ll">
        <div class="photo-wrapper">
          <div class="photo-img">
            <router-link to="/Animals1">
              <img src="../../assets/video/video4.jpg" alt="可爱动物">
            </router-link>
          </div>
          <h2 class="photo-title">
            <router-link to="/Animals1">
              可爱动物
            </router-link>
          </h2>
          <ul class="photo-list">
            <li><a href="#">Lovely animals</a></li></ul>
        </div></div>

      <div class="ll">
        <div class="photo-wrapper">
          <div class="photo-img">
            <router-link to="/Constructs1">
              <img src="../../assets/video/video5.jpg" alt="特色建筑">
            </router-link>
          </div>
          <h2 class="photo-title">
            <router-link to="/Constructs1">
              特色建筑
            </router-link>
          </h2>
          <ul class="photo-list">
            <li><a href="#">Characteristic architecture</a></li></ul>
        </div></div>
      <div class="ll">
        <div class="photo-wrapper">
          <div class="photo-img">
            <router-link to="/more1">
              <img src="../../assets/bz1.jpg" alt="更多">
            </router-link>
          </div>
          <h2 class="photo-title">
            <router-link to="/more1">
              更多
            </router-link>
          </h2>
          <ul class="photo-list">
            <li><a href="#">More</a></li></ul>
        </div></div>
    </div>

  </div>
</template>

<script>
export default {
  name: "Page2"
}
</script>

<style scoped>
.center{
  text-align: center;
  padding-left: 10%;
  padding-right: 10%;
}
.ll{
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
</style>

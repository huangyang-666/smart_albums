<template>
  <!--//相册类别-->
  <!--path: '/type',-->
    <div style="    background-color: rgb(235, 235, 235);" class="bj">
    <div class="center">
      <div class="ll">
        <div class="photo-wrapper">
          <div class="photo-img">
            <router-link to="/humans">
              <img src="../../assets/image/ren.png" alt="人物">
            </router-link>
          </div>
          <h2 class="photo-title">
            <router-link to="/humans">
              人物
            </router-link>
          </h2>
          <ul class="photo-list">
            <li>
              <a href="#">character</a>
            </li>
          </ul>
        </div></div>
        <div class="ll">
        <div class="photo-wrapper">
          <div class="photo-img">
            <router-link to="/animals">
              <img src="../../assets/image/dong.png" alt="动物">
            </router-link>
          </div>
          <h2 class="photo-title">
            <router-link to="/animals">
              动物
            </router-link>
          </h2>
          <ul class="photo-list">
            <li>
              <a href="#">animal</a>
            </li>
          </ul>
        </div></div>

      <div class="ll">
        <div class="photo-wrapper">
          <div class="photo-img">
            <router-link to="/sceneries">
              <img src="../../assets/image/feng.png" alt="风景">
            </router-link>
          </div>
          <h2 class="photo-title">
            <router-link to="/sceneries">
              风景
            </router-link>
          </h2>
          <ul class="photo-list">
            <li><a href="#">scenery</a></li></ul>
        </div></div>

      <div class="ll">
        <div class="photo-wrapper">
          <div class="photo-img">
            <router-link to="/vehicles">
              <img src="../../assets/image/jiaotong.png" alt="交通工具">
            </router-link>
          </div>
          <h2 class="photo-title">
            <!--            <a href="#">交通</a>-->
            <router-link to="/vehicles">
              交通工具
            </router-link>
          </h2>
          <ul class="photo-list">
            <li><a href="#">vehicle</a></li></ul>
        </div></div>

      <div class="ll">
        <div class="photo-wrapper">
          <div class="photo-img">
            <router-link to="/constructs">
              <img src="../../assets/image/jianzhu.png" alt="建筑">
            </router-link>
          </div>
          <h2 class="photo-title">
            <router-link to="/constructs">
              建筑
            </router-link>
          </h2>
          <ul class="photo-list">
            <li><a href="#">architecture</a></li></ul>
        </div></div>
      <div class="ll">
        <div class="photo-wrapper">
          <div class="photo-img">
            <router-link to="/more">
              <img src="../../assets/bz1.jpg" alt="更多">
            </router-link>
          </div>
          <h2 class="photo-title">
            <router-link to="/more">
              全部
            </router-link>
          </h2>
          <ul class="photo-list">
            <li><a href="#">all pictures</a></li>
          </ul>
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
  .bj{
  }
</style>

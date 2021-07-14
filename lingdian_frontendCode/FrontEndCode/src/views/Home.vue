<template>
  <div>
    <!--<HelloWorld/>-->
      <div class="pg-header">
        <div class="w">
          <ul>
            <li class="l1"><i class="el-icon-picture-outline-round"></i>云相册</li>
            <li><router-link to="/Home1"><i class="el-icon-s-home"></i>回到首页</router-link></li>
            <li><router-link to="/all"><i class="el-icon-copy-document"></i>全部图片</router-link></li>
            <li><router-link :to="{name:'Page1'}">
              <i class="el-icon-upload"></i>图片上传 </router-link></li>
            <li><router-link to="/loads">
            <i class="el-icon-s-management"></i>图片智能识别分类上传 </router-link></li>
            <li><router-link to="/type">
              <i class="el-icon-picture"></i>相册类别</router-link></li>
            <li><router-link to="/recognition"><i class="el-icon-s-custom"></i>人脸库</router-link></li>
            <li><router-link to="/moment"><i class="el-icon-video-camera-solid"></i>精彩时刻</router-link></li>
            <li><router-link to="/test"><i class="el-icon-video-camera-solid"></i>test</router-link></li>
          </ul>
          <ul class="il">
            <li style="width: 16px;padding:0 30px">
              <el-dropdown>
                <i class="el-icon-setting" style="margin-right: 15px"></i>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item class="el-aside">
                    <router-link to="/Home1">
                      <el-button type="text">回到首页</el-button>
                    </router-link>
                  </el-dropdown-item>
                  <el-dropdown-item>
                    <router-link to="/logout">
                      <el-button type="text">退出登录</el-button>
                    </router-link>
                  </el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </li>
            <li><a href="#">
              <span>{{$store.getters.getUser.username}}</span>
            </a></li>
          </ul>
        </div>
      </div>

    <router-view/>

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
          <div class="item">
            <a href="#">图片上传</a>
          </div>
          <div class="item">
            <a href="#">相册类别</a>
          </div>
          <div class="item">
            <a href="#">人体识别</a>
          </div><div class="item">
          <a href="#">精彩时刻</a>
        </div>

        </nav>
      </div>
      <div>
        <nav class="left-nav">
          <div class="item">
            <div class="cp"><a href="#">关于我们</a></div>
          </div>
          <div class="item">
            <a href="#">我们的产品</a>
          </div>
          <div class="item">
            <a href="#">我们的</a>
          </div>
          <div class="item">
            <a href="#">我们的</a>
          </div><div class="item">
          <a href="#">我们的</a>
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
        <img class="look" id="dy" src=../assets/tubiao/dy1.png>
        <img class="look" id="qq" src=../assets/tubiao/qq1.png>
        <img class="look" id="tuit" src=../assets/tubiao/tuit1.png>
        <img class="look" id="wb" src=../assets/tubiao/wb1.png>
        <img class="look" id="wx" src=../assets/tubiao/wx1.png>
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
    export default {
        name: "Home.vue"
    }

</script>

<style scoped>
  html, body, div, span, applet, object, iframe,
  h1, h2, h3, h4, h5, h6, p, blockquote, pre,
  a, abbr, acronym, address, big, cite, code,
  del, dfn, em, img, ins, kbd, q, s, samp,
  small, strike, strong, sub, sup, tt, var,
  b, u, i, center,
  dl, dt, dd, ol, ul, li,
  fieldset, form, label, legend,
  table, caption, tbody, tfoot, thead, tr, th, td,
  article, aside, canvas, details, embed,
  figure, figcaption, footer, header, hgroup,
  menu, nav, output, ruby, section, summary,
  time, mark, audio, video {
    margin: 0;
    padding: 0;
    border: 0;
    font-size: 100%;
    vertical-align: baseline;
  }
  /* HTML5 display-role reset for older browsers */
  article, aside, details, figcaption, figure,
  footer, header, hgroup, menu, nav, section {
    display: block;
  }
  ol, ul {
    list-style: none;
  }
  blockquote, q {
    quotes: none;
  }
  blockquote:before, blockquote:after,
  q:before, q:after {
    content: '';
    content: none;
  }
  table {
    border-collapse: collapse;
    border-spacing: 0;
  }

  .pg-header{
    margin: 0;
    height: 65px;
    background-color: rgba(180, 219, 255, 0.24);
    line-height: 40px;
    width: 100%;
    border-bottom: 2px solid white;
  }
  .w{
    width: 1300px;
    margin: 0 auto;
    /* background-color: rgb(8, 64, 246); */
  }
  ul{
    margin: 0;
    list-style-type: none;
  }
  ul li{
    border-radius: 8%;
    height: 50px;
    line-height: 50px;
    float: left;
    margin: 7px 2px 0 2px;
    padding: 0 10px 0 10px;
    color: rgb(87, 171, 250);
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
    color: floralwhite;
    cursor: pointer;
  }

  ul li:hover{
    background-color: rgb(198, 218, 241);
  }

  .pg-header .w ul li a{
    color:rgb(85, 86, 90);
    text-decoration: none;
  }
  .w ul li a:hover{
    color: rgb(0, 0, 0);
  }


  .center{
    text-align: center;
  }
  .footer{
    margin: 0;
    padding: 0;
    width: 100%;
    background-color: rgb(180, 209, 229);
    border:1px solid #e8ffff;
  }
  .look1{
    margin-top: 20px;
    margin-bottom: 10px;
    margin-right: 50px;
    float: right;
  }
  .look{
    float: right;
    width: 22px;
    margin: 2px;
    padding: 2px;
  }
  #dy:hover{
    content: url(../assets/tubiao/dy2.png);
  }
  #qq:hover{
    content: url(../assets/tubiao/qq2.png);
  }
  #tuit:hover{
    content: url(../assets/tubiao/tuit2.png);
  }
  #wb:hover{
    content: url(../assets/tubiao/wb2.png);
  }
  #wx:hover{
    content: url(../assets/tubiao/wx2.png);
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
    margin: 16px;
    color: white;
    font-size: 14px;
  }
  .footwz1{
    float: right;
    margin: 16px;
    color: white;
    font-size: 14px;
  }
  .cp{
    margin-left:0;
    padding-left: 2px;
    width: 70px;
    height: 40px;
    background-color: rgba(221, 219, 219, 0);
    border-bottom: 3px rgba(4, 198, 202, 0.75) solid;
  }
  .jia{
    margin-left:0;
    width: 70px;
    height: 40px;
    background-color: rgba(221, 219, 219, 0);
    border-bottom: 3px rgba(245, 255, 9, 0.75) solid;
  }

  /* 设置菜单外部容器 */
  .left-nav{
    width: 100px;
    height: 270px;
    padding-top: 5px;
    padding-left: 25px;
    padding-right: 25px;
    margin: 2px;
    background-color: rgb(180, 209, 229);
    float: left;
    clear: none;
  }
  /* 设置菜单内部item */
  .left-nav .item{
    height: 50px;
    /* 使文字在父元素中居中：父元素line-height设置与height一样 */
    line-height: 50px;
    padding-left: 18px;
  }
  /* 鼠标移入 */
  .item:hover{
    cursor: text;
  }
  a{
    font-size: 16px;
    color: rgba(255, 252, 255, 0.8);
    /* 去除下划线 */
    text-decoration: none;
  }
  a:hover{
    color: rgb(159, 9, 255);
    cursor: pointer;
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
    height: 70px;
    background:rgb(180, 209, 229);
    overflow: hidden;
  }

  section .wave {
    width: 100%;
    height: 50px;
    position: absolute;
    bottom: 0;
    left: 0;
    background: url(../assets/wave.png);
    background-size: 1000px 50px;
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
    bottom: 10px;
  }

  section .wave.wave3 {
    animation: animate1 10s linear infinite;
    z-index: 998;
    opacity: 0.2;
    animation-delay: -7s;
    bottom: 15px;
  }

  section .wave.wave4 {
    animation: animate2 2s linear infinite;
    z-index: 997;
    opacity: 0.71;
    animation-delay: -5s;
    bottom: 20px;
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
</style>

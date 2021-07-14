<template>
  <div>
    <div class="center">

      <div class="div0">
        <div class="div01">
          <div class="div0-font">测试次数</div>
          <!--startVal为开始数字，startVal为结束数字，duration为滚动时长-->
          <countTo :startVal='startVal' :endVal='999' :duration='3000'></countTo>+
        </div>
        <div class="div01">
          <div class="div0-font">可容用户</div>
          <!--startVal为开始数字，startVal为结束数字，duration为滚动时长-->
          <countTo :startVal='startVal' :endVal='9999' :duration='3000'></countTo>+
        </div>
        <div class="div01">
        <div class="div0-font">存储图片</div>
        <!--startVal为开始数字，startVal为结束数字，duration为滚动时长-->
        <countTo :startVal='startVal' :endVal='999999' :duration='4000'></countTo>+
        </div>
        <div style="width: 100px;float: left;display: inline-block;margin: 20px"><img src="../../assets/tubiao/shou.png"></div>
        <div class="all"></div>
      </div>

      <div class="div1">
        <div class="div11">
          <img src="../../assets/image/face.jpg"/></div>
        <div class="div12">
          <div class="font-title1">管理你的相册</div>
          <div class="font-title2">便捷云服务，智能化处理</div>
          <div class="font-content">上传存放到零点云相册中的照片可进行智能分类，无限免费存储空间，可完好无损的保存所有重要内容，特殊回忆。</div>
          <div class="font-content">用户随时都能登录查看相册的所有类别，轻松管理，不遗漏生活的点点滴滴。</div>
          <div class="tplb">
            <div class="tp">
              <img src="../../assets/tubiao/tb1.png">
            </div>
            <div class="tp1">
              大图预览
            </div>
          </div>
          <div class="tplb">
            <div class="tp">
              <img src="../../assets/tubiao/tb2.png">
            </div>
            <div class="tp1">
              安全保障
            </div>
          </div>
          <div class="tplb">
            <div class="tp">
              <img src="../../assets/tubiao/tb3.png">
            </div>
            <div class="tp1">
              永久保存
            </div>
          </div><div class="tplb">
          <div class="tp">
            <img src="../../assets/tubiao/tb4.png">
          </div>
          <div class="tp1">
            随意操作
          </div>
        </div>
        </div>
        <div class="banner1">    <a href="javascript:;"></a>    <span>零点云相册</span></div>
        <div class="all"></div>
      </div>

      <div class="div2">
        <div class="font-title1">编辑你的图片</div>
        <div class="font-title2">便捷云操作，批量化处理</div>
        <div class="font-content">为图片编写专属标签</div>
        <div class="font-content">用户可以对上传的照片进行编辑查找,便于用户个性化管理。</div>
        <div class="tbox2">
          <div class="border-animation">
            <a href="/all" class="box-link">
              点击管理相册
              <span class="line line_top"></span>
              <span class="line line_right"></span>
              <span class="line line_bottom"></span>
              <span class="line line_left"></span>
            </a>
          </div>
        </div>
      </div>

      <div class="div3">
        <div class="div11">
          <div class="font-title1">相册分类</div>
          <div class="font-title2"></div>
          <div class="font-content">精细化分类,操作高效</div>
          <a href="/type">
            <div class="box">
              <span class="shake">点</span>
              <span class="shake">击</span>
              <span class="shake">了</span>
              <span class="shake">解</span>
            </div>
          </a>

        </div>
        <div class="flower">
          <div class="f0">
          </div>
          <div class="f1"></div>
          <div class="f2"></div>
          <div class="f3"></div>
          <div class="f4"></div>
          <div class="f5"></div>
          <div class="f6"></div>
          <div class="f7"></div>
          <div class="f8"></div>
          <div class="f9"></div>
          <div class="f10"></div>
          <div class="f11"></div>
          <div class="f12"></div>
          <div class="f13"></div>
          <div class="f14"></div>
          <div class="f15"></div>
          <div class="f16"></div>
          <div class="f17"></div>
        </div>
        <div class="div32">
          <div class="image_box">
          <div class="center">
          <div class="tbox11">
          <el-carousel height="300px">
          <el-carousel-item v-for="item in imglbList1" :key="item.id">
          <img :src="item.url" height="300" alt="" class="image">
          <div class="font">
          <h5>{{item.title}}</h5>
          </div>
          </el-carousel-item>
          </el-carousel>
          </div>
          </div>
          </div>
        </div>
      </div>

      <div class="div4">
        <div class="tbox3">
        <div class="container">
        <button class="corner-button">
        <span>更多功能,尽在探索</span>
        </button>
        </div></div>
        <!--<div class="font-title1">更多功能</div>-->
        <div></div>
      </div>


      <!--<div class="body">-->
        <!--&lt;!&ndash;<div class="tbox">&lt;!&ndash;线条沿边框运动&ndash;&gt;&ndash;&gt;-->
        <!--&lt;!&ndash;<div class="line-run"></div>&ndash;&gt;-->
        <!--&lt;!&ndash;</div>&ndash;&gt;-->

        <!--&lt;!&ndash;<div class="tbox1">&ndash;&gt;-->
        <!--&lt;!&ndash;</div>&ndash;&gt;-->


        <!--</div>-->
      <!--</div>-->

      <!--<div class="waterfull">-->
        <!--<div class="v-waterfall-content" id="v-waterfall">-->
          <!--<div v-for="(img, index) in waterfallList" :key="index" class="v-waterfall-item" :style="{top:img.top+'px',left:img.left+'px',width:waterfallImgWidth+'px',height:img.height}">-->
            <!--<a target="_blank" href="">-->
              <!--<img :src="img.src" alt="">-->
            <!--</a>-->
            <!--<p class="ms" style="">{{img.details}}</p>-->
          <!--</div>-->
        <!--</div>-->
      <!--</div>-->

    </div>
  </div>
</template>

<script>
  //直接引入组件'vue-count-to'
  import countTo from 'vue-count-to';
  export default {
    name: "Home1",
    //注册组件
    components: { countTo },
    data() {
      return {
        currentDate: new Date(),
        img: [
          {
            id: '1',
            url: require('../../assets/lunbo/lb01.png')
          },
          {
            id: '2',
            url: require('../../assets/lunbo/lb02.png')
          },
          {
            id: '3',
            url: require('../../assets/lunbo/lb04.png')
          }
        ],
        waterfallList: [],
        imgArr: [
          // require('../../assets/lunbo/banner.jpg'),
          // require('../../assets/lunbo/about.jpg'),
          require('../../assets/lunbo/blog1.jpg'),
          require('../../assets/lunbo/ee.jpeg'),
          // require('../../assets/lunbo/lb1.png'),
          // require('../../assets/lunbo/lb2.png'),
          require('../../assets/lunbo/lb3.png'),
          require('../../assets/lunbo/ll.jpg'),
          require('../../assets/lunbo/mm.jpg'),
          // require('../../assets/003.jpg'),
          require('../../assets/004.jpg'),
          // require('../../assets/005.jpg'),
          // require('../../assets/006.jpg'),
          // require('../../assets/yun.jpg'),
        ],
        waterfallImgWidth: 450,// 每个盒子的宽度
        waterfallImgCol: 3,// 瀑布流的列数
        waterfallImgRight: 15,// 每个盒子的右padding
        waterfallImgBottom: 15,// 每个盒子的下padding
        waterfallDeviationHeight: [1000],
        imgList: [],
        //数字开始
        startVal: 0,
        //数字结束
        endVal: 50000,
        imglbList: [
          {
            id: '1',
            url: require('../../assets/lunbo/lb11.jpg'),
            title:'Focus on deep learning algorithm, ' +
            'develop industry data applications based on artificial intelligence, ' +
            'big data, cloud computing and other new technologies',
          },
          {
            id: '2',
            url: require('../../assets/lunbo/lbt1.jpg'),
            title:'Focus on deep learning algorithm, develop industry data applications based on artificial intelligence, big data, cloud computing and other new technologies',
          },
          {
            id: '3',
            url: require('../../assets/lunbo/lbtt.jpg'),
            title:'Focus on deep learning algorithm, develop industry data applications based on artificial intelligence, big data, cloud computing and other new technologies',
          }
        ],
        imglbList1: [
          {
            id: '1',
            url: require('../../assets/class1.png'),
            title:'Cute Animals'
          },
          {
            id: '2',
            url: require('../../assets/class2.png'),
            title:'Beautiful Sceneries ,You Will Love It',
          },
          {
            id: '3',
            url: require('../../assets/class3.png'),
            title:'new technologies',
          },
          {
            id: '4',
            url: require('../../assets/class5.jpg'),
            title:'Myself',
          }
        ],
        List:[
          {name:'bz5.jpg'},{name:'002.jpg'},{name:'003.jpg'},{name:'004.jpg'},
          {name:'005.jpg'},{name:'006.jpg'},{name:'bz1.jpg'},{name:'bz2.jpg'},
        ],
      }
    },
    created() {
      // 触发入口
      for (let i = 0; i < this.imgArr.length; i++) {
        this.imgList.push(this.imgArr[i]);
      }
    },
    mounted() {
      this.calculationWidth();
    },
    methods: {
      //计算每个图片的宽度或者是列数
      calculationWidth() {
        let domWidth = document.getElementById("v-waterfall").offsetWidth;
        if (!this.waterfallImgWidth && this.waterfallImgCol) {
          this.waterfallImgWidth = (domWidth - this.waterfallImgRight * this.waterfallImgCol) / this.waterfallImgCol;
        } else if (this.waterfallImgWidth && !this.waterfallImgCol) {
          this.waterfallImgCol = Math.floor(domWidth / (this.waterfallImgWidth + this.waterfallImgRight))
        }
        //初始化偏移高度数组
        this.waterfallDeviationHeight = new Array(this.waterfallImgCol);
        for (let i = 0; i < this.waterfallDeviationHeight.length; i++) {
          this.waterfallDeviationHeight[i] = 0;
        }
        this.imgPreloading()
      },
      //图片预加载
      imgPreloading() {
        for (let i = 0; i < this.imgList.length; i++) {
          let aImg = new Image();
          aImg.src = this.imgList[i];
          aImg.onload = aImg.onerror = (e) => {
            let imgData = {};
            imgData.height = this.waterfallImgWidth / aImg.width * aImg.height;
            imgData.src = this.imgList[i];
            imgData.details = 'picture';
            this.waterfallList.push(imgData);
            this.rankImg(imgData);
          }
        }
      },
      //瀑布流布局
      rankImg(imgData) {
        let {
          waterfallImgWidth,
          waterfallImgRight,
          waterfallImgBottom,
          waterfallDeviationHeight,
          waterfallImgCol
        } = this;
        let minIndex = this.filterMin();
        imgData.top = waterfallDeviationHeight[minIndex];
        imgData.left = minIndex * (waterfallImgRight + waterfallImgWidth);
        // waterfallDeviationHeight[minIndex] += imgData.height + waterfallImgBottom;// 不加文字的盒子高度
        waterfallDeviationHeight[minIndex] += imgData.height + waterfallImgBottom + 56;// 加了文字的盒子高度，留出文字的地方（这里设置56px）
        console.log(imgData);
      },
      /**
       * 找到最短的列并返回下标
       * @returns {number} 下标
       */
      filterMin() {
        const min = Math.min.apply(null, this.waterfallDeviationHeight);
        return this.waterfallDeviationHeight.indexOf(min);
      }

    }
  }

</script>

<style scoped>
  .all{
    width: 100%;
    clear: both;
  }
  img{
    width: 100%;
    border-radius: 10px;
  }
  .div0{
    background-color: #575757;
    font-size: 50px;
    color: white;
    padding: 30px 200px;
  }
  .div0-font{
    font-size: 18px;
    color: gainsboro;
  }
  .div01{
    margin: 20px 50px;
    float: left;
    display: inline-block;
    width: 300px;
  }
  .div11 img{
     cursor: pointer;
     transition: all 0.6s;
   }
  .div11 img:hover{
    transform: scale(1.1);
  }
  .div1{
    background-color: #f1f1f1;
    width: 100%;
    padding-left: 16%;
    padding-right: 10%;
    padding-top: 40px;
    padding-bottom: 30px;
  }
  .div11{
    margin-top: 40px;
    width: 500px;
    float: left;
    display: inline-block;
  }
  .div12{
    padding-left: 150px;
    text-align: left;
    width: 700px;
    float: left;
    display: inline-block;
  }
  .div32{
      padding-left:0px;
      text-align: left;
      width: 400px;
      float: left;
      display: inline-block;
    }
  .div2{
    /*background-color: #ffffff;*/
    width: 100%;
    padding-left: 16%;
    padding-right: 10%;
    padding-top: 40px;
    padding-bottom: 30px;
    background-image: url(../../assets/2beijing.jpg);
    height: 400px;
    overflow: hidden;
  }
  .div3{
     background-color: #eaeaea;
     width: 100%;
     padding-left: 16%;
     padding-right: 10%;
     padding-top: 40px;
     padding-bottom: 30px;
     height: 400px;
     overflow: hidden;
   }
  .div4{
    background-color: #d6d6d6;
    width: 100%;
    padding-left: 16%;
    padding-right: 10%;
    padding-top: 40px;
    padding-bottom: 30px;
    height: 400px;
    overflow: hidden;
  }
  .font-title1{
    margin: 3px;
    font-size: 26px;
    line-height: 50px;
  }
  .font-title2{
    margin: 3px;
    font-size: 28px;
     line-height: 50px;
    font-style: unset;
   }
  .font-content{
    margin: 5px;
    font-size: 18px;
    line-height: 30px;
  }
  .tplb{
    margin-left: 20px;
    margin-top: 60px;
    width: 100px;
    float: left;
    display: inline-block;
  }
  .tp{
    margin-left: 34px;
    margin-right: 30px;
    text-align: center;
    float: left;
    width: 50px;
    display: inline-block;
  }
  .tp1{
     font-size: 18px;
     color: #404040;
     margin: 10px;
     text-align: center;
     float: left;
     width: 100px;
     display: inline-block;
   }
  /*花朵效果*/
  .flower{
    padding: 0;
    width: 300px;
    height:250px;
    /*margin:0 auto;!*将盒子设置在中心位置*!*/
    margin: 0;
    float: left;
    position: relative;
    display: inline-block;
  }
  /*设置每一朵花瓣的形状*/
  .f0,.f1,.f2,.f3,.f4,.f5,.f6,.f7,.f8,.f9,.f10
  ,.f11,.f12,.f13,.f14,.f15,.f16,.f17{
    width:50px;
    height:50px;
    background: #666666;
    opacity: 0.6; /*设置透明度*/
    left:50px;
    top:100px;
    position:absolute;
    border-radius: 100% 0; /*设置花瓣形式*/
    transform-origin: 0 50px;/*设置旋转中心点*/
    transition:3s; /*设置变化时间*/
  }
  .flower:hover .f0{
    transform: rotate(20deg);/*给每片花瓣设置旋转角度*/
  }
  .flower:hover .f1{
    transform: rotate(40deg);
  }
  .flower:hover .f2{
    transform: rotate(60deg);
  }
  .flower:hover .f3{
    transform: rotate(80deg);
  }
  .flower:hover .f4{
    transform: rotate(100deg);
  }
  .flower:hover .f5{
    transform: rotate(120deg);
  }
  .flower:hover .f6{
    transform: rotate(140deg);
  }
  .flower:hover .f7{
    transform: rotate(160deg);
  }
  .flower:hover .f8{
    transform: rotate(180deg);
  }
  .flower:hover .f9{
    transform: rotate(200deg);
  }
  .flower:hover .f10{
    transform: rotate(220deg);
  }
  .flower:hover .f11{
    transform: rotate(240deg);
  }
  .flower:hover .f12{
    transform: rotate(260deg);
  }
  .flower:hover .f13{
    transform: rotate(280deg);
  }
  .flower:hover .f14{
    transform: rotate(300deg);
  }
  .flower:hover .f15{
    transform: rotate(320deg);
  }
  .flower:hover .f16{
    transform: rotate(340deg);
  }
  .flower:hover .f17{
    transform: rotate(360deg);
  }
  /*鼠标移动抖动*/
  .box{
    width: 200px;
    height: 50px;
    margin-left: 150px;
    margin-top: 50px;
  }
  .shake{
    float: left;
    display: inline-block;
    width:40px;
    height:40px;
    background: lightblue;
    border-radius:50%;
    margin:5px;
    color:#fff;
    font-size:24px;
    text-align:center;
    line-height:40px;
    cursor:pointer;
    -webkit-transition:all 0.25s;}
  .shake:hover{
    -webkit-animation:shake 0.25s;
    background: #8fb0c9;}
  @-webkit-keyframes shake{
    0%,10%,55%,90%,94%,98%,100%{
      -webkit-transform:scale(1,1);
    }
    30%{
      -webkit-transform:scale(1.14,0.86);
    }
    75%{
      -webkit-transform:scale(0.92,1.08);
    }
    92%{
      -webkit-transform:scale(1.04,0.96);
    }
    96%{
      -webkit-transform:scale(1.02,0.98);
    }
    99%{
      -webkit-transform:scale(1.01,0.99);
    }}
  /*鼠标移动弹出*/
  .banner1{
    width:400px;
    height:34px;
    border-radius:40px;
    position:absolute;
    top:528px;
    left:1300px;}
  .banner1 a{
    display:inline-block;
    width:30px;
    height:30px;
    line-height:30px;
    border-radius:50%;
    border:2px solid #ffffff;
    position:absolute;
    left:0px;top:0px;
    background: #49c5ee;
    color:#fff;
    text-align:center;
    text-decoration:none;
    cursor:pointer;
    z-index:2;}
  .banner1 a:hover + span{
    -webkit-transform:translateX(40px);
    opacity:1;}
  .banner1 span{
    display:inline-block;
    width:auto;
    padding:0 20px;
    height:30px;line-height:30px;
    background:lightblue;
    border-radius:30px;
    text-align: center;
    color:#fff;
    position:absolute;
    top:2px;
    opacity:0;
    -webkit-transition:all 1s;
    -webkit-transform:translateX(80px);
  }



  .image{
  height: 100%;
}
  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
  }
  .center{
    text-align: center;
  }
  .body{
    width: 90%;
    text-align: center;
    display: inline-block;
    clear: both;
    margin: 0;
    padding-top: 60px;
  }
  .tbox{
    height: 500px;
    border: 1px solid rosybrown;
    position: absolute;
    width: 90%;
  }
  /*线条沿着边框运动*/
  .line-run, .line-run::before, .line-run::after {
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
  }
  .line-run {
    width: 300px;
    height: 300px;
    margin: auto;
    color: red;
    box-shadow: inset 0 0 0 1px rgba(105, 202, 98, 0.5);
    float: left;
  }
  .line-run::before, .line-run::after {
    content: '';
    z-index: -1;
    margin: -5%;
    box-shadow: inset 0 0 0 2px;
    animation: myAni 8s linear infinite;
  }
  .line-run::before {
    animation-delay: -4s;
  }
  .line-run:hover::after, .line-run:hover::before {
    /*background-color: rgba(255, 0, 0, 0.3);*/
  }
  .line-run:hover{
    background-color: rgba(88, 255, 48, 0.3);
  }

  @keyframes myAni {
    0%, 100% {
      clip: rect(0px, 340.0px, 4px, 0px);
    }
    25% {
      clip: rect(0px, 4px, 320.0px, 0px);
    }
    50% {
      clip: rect(318.0px, 340.0px, 340.0px, 0px);
    }
    75% {
      clip: rect(0px, 340.0px, 340.0px, 318.0px);
    }
  }
  .tbox1{
    height: 600px;
  }
  .tbox11{
     height: 300px;
   }

  .border-animation {
    background: rgba(145, 202, 248, 0);
    width:902px;
    margin:10px auto 0 auto;
  }
  .border-animation .box-link {
    display:inline-block;
    position:relative;
    text-decoration:none;
    font-size:20px;
    color: #0d2613;
    font-weight:bold;
    width:60%;
    text-align:center;
    height:100px;
    line-height:56px;
    border:2px solid rgba(145, 166, 255, 0.8);
    margin:20px 96px;
    padding:20px;
    -webkit-box-sizing:border-box;
    -moz-box-sizing:border-box;
    box-sizing:border-box;
    -webkit-transition:0.4s;
    -o-transition:0.4s;
    transition:0.4s;
  }
  .box-link:hover{
    border:2px solid rgb(187, 214, 255);
  }
  .box-link .line{
    display:inline-block;
    background-color: #9ac4ff;
    position:absolute;
    -webkit-transition:0.5s ease;
    -o-transition:0.5s ease;
    transition:0.5s ease;
  }
  .box-link .line_top{
    height:2px;
    width:0;
    left:-50%;
    top:-2px;
  }
  .box-link:hover .line_top{
    width:100%;
    left:-2px;
  }
  .box-link .line_right{
    height:0;
    width:2px;
    top:-50%;
    right:-2px;
  }
  .box-link:hover .line_right{
    height:100%;
    top:-2px;
  }
  .box-link .line_bottom{
    width:2px;
    height:0;
    bottom:-50%;
    left:-2px;
  }
  .box-link:hover .line_bottom{
    height:100%;
    bottom:-2px;
  }
  .box-link .line_left{
    height:2px;
    width:0;
    right:-50%;
    bottom:-2px;
  }
  .box-link:hover .line_left{
    width:100%;
    right:-2px;
  }
  .tbox2{
    height: 260px;
  }

  .container {
    background: rgba(219, 219, 219, 0);
    width: 90%;
    height: 200px;
    box-sizing: border-box;
    margin: 0 auto;
    text-align: center;
    padding-top: 5%;
  }
  .corner-button {
    letter-spacing: 0.32px;
    cursor: pointer;
    background: transparent;
    border: 8px solid currentColor;
    padding: 24px 32px;
    font-size: 35px;
    color: #575757;
    position: relative;
    transition: color 0.3s;
  }
  .corner-button:hover {
    color: #4e629c;
  }
  .corner-button:hover::before {
    width: 0;
  }
  .corner-button:hover::after {
    height: 0;
  }
  .corner-button:active {
    border-width: 4px;
  }
  .corner-button span {
    position: relative;
    z-index: 2;
  }
  .corner-button::before,
  .corner-button::after {
    content: '';
    position: absolute;
    background:rgb(219, 219, 219);
    z-index: 1;
    transition: all 0.3s;
  }
  .corner-button::before {
    width: calc(100% - 48px);
    height: calc(100% + 16px);
    top: -8px;
    left: 50%;
    -webkit-transform: translateX(-50%);
    transform: translateX(-50%);
  }
  .corner-button::after {
    height: calc(100% - 48px);
    width: calc(100% + 16px);
    left: -8px;
    top: 50%;
    -webkit-transform: translateY(-50%);
    transform: translateY(-50%);
  }


  .waterfull{
    width: 90%;
    margin-right: 5%;
    margin-left: 5%;
    margin-top: 100px;
  }

  .v-waterfall-content {
    /* 主要 */
    width: 100%;
    height: 800px;
    /*padding-left: 6%;*/
    position: relative;
    /* 次要：设置滚动条，要求固定高度 */
    /*overflow-y: auto;*/
  }

  .v-waterfall-item {
    /* 主要 */
    float: left;
    position: absolute;
    background-color: #fff;
    border: 1px solid #dddddd;
    border-radius: 5px;
  }
  .v-waterfall-item:hover {
    border: 1px solid #000000;
    border-radius: 10px;
  }
  .v-waterfall-item img {
    width: 100%;
    height: auto;
    border-radius: 10px;
  }
  .ms{
    font-size: 14px;
    color: #9e9e9e;
    margin: 4px;
    padding-bottom: 6px;
  }
  /*div{*/
    /*padding: 15px;*/
    /*text-align: center;*/
  /*}*/

  * {
    box-sizing: border-box;
  }



  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
  .cardBanner ul{
    display: flex;
    overflow: scroll;    /*设置滚动条*/
  }
  .cardBanner ul::-webkit-scrollbar{    /*隐藏滚动条*/
    display: none;
  }
  .cardBanner ul>li{ //高能部分，flex不太好解释
  width: 31.33333%;
    flex-shrink: 0;
    padding-left: 3%;
    text-align: center;
  }
  .cardBanner ul>li:first-child{
    padding-left: 0;
  }
  .cardBanner ul>li a{
    display: block;
    width: 100%;
    height: 100%;
  }
  .cardBanner ul>li img{
    width: 100%;
    height: 170px;
    border-radius: 5px;
  }
  .cardBanner ul>li p{
    margin: 0;
  }
  [class^='arrow']{
    font-size: 30px;
    transform: scaleX(.7);
    color: #ddd;
  }

.font{
  position: absolute;
  left: 10rem;
  bottom: 3rem;
  color: #ffffff;
  font-size: 20px;
}
</style>

<template>
  <div class="class">
    <h1 class="font"> My Happy Days</h1>
    <div>
      <el-button style="float: right;  margin-top: 15px ;background-color: rgb(235, 235, 235);" class="small" type=" " size="large" @click="fusionVideo()">更多</el-button>
    </div>
    <div class="main">

      <div class="waterfull-continer">
        <div v-for="(item,i) in VideoData" :key="i" style="margin-bottom: 30px">
          <video :id="'myVideo'+item.id" class="video-js" controls>
            <source :src="'../../static/video/finalvideo/'+item.name" type="video/mp4">
          </video>
        </div>
      </div>
    </div>


  </div>

</template>

<script>
import * as axios from 'axios'
export default {
  name: 'HelloWorld',
  inject: ['reload'],
  data(){
    return {
      // list:[
      //   {
      //     src:'../../../static/video/admin/finalvideo/20210626154258happymoments.mp4',
      //     id:0,
      //     pic:"",
      //   },
      //   {
      //     src:'../../../static/video/1211.mp4 ',
      //     id:1,
      //     pic:"",
      //   },
      //   {
      //     src:'../../../static/video/1325_1.mp4 ',
      //     id:2,
      //     pic:"",
      //   }
      // ],
      VideoData:[],
      type:'vehicle'
    }
  },
  created() {

    this.param = new FormData();
    this.param.append('type',this.type);
    this.param.append('user',this.$store.getters.getUser.username);
    const _this = this;
    axios.post('http://localhost:8086/showmoments',_this.param,{
      headers:{
        authorization:localStorage.getItem('token')
      }
    })
      .then(function (resp) {
        console.log(resp);
        _this.VideoData = resp.data;
      })
  },
  mounted() {
    this.initVideo();
    console.log("ok");
  },
  methods: {
    initVideo() {
      //初始化视频方法 循环列表获取每个视频的id
      this.VideoData.map((item,i)=>{
        let myPlayer = this.$video('myVideo'+item.id, {
          // //确定播放器是否具有用户可以与之交互的控件。没有控件，启动视频播放的唯一方法是使用autoplay属性或通过Player API。
          // controls: true,
          // //自动播放属性,muted:静音播放
          // autoplay: "muted",
          // //建议浏览器是否应在<video>加载元素后立即开始下载视频数据。
          // preload: "auto",
          //设置视频播放器的显示宽度（以像素为单位）
          width: "350px",
          //设置视频播放器的显示高度（以像素为单位）
          height: "200px",
          //封面图
          poster:item.pic
        });
        console.log(item.id);
      })
      console.log("ok");
    },
    fusionVideo(){
      const loading = this.$loading({
        lock: true,
        text: 'Loading',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      });
      this.param = new FormData();
      this.param.append('type',this.type);
      this.param.append('user',this.$store.getters.getUser.username);
      console.log(this.type)
      const _this = this
      axios.post('http://localhost:8086/happymoments',_this.param,{
        headers:{
          authorization:localStorage.getItem('token')
        }
      }).then(function (resp){
        console.log("okokok")
        console.log(resp)
        loading.close();
        setTimeout(() =>{
          _this.VideoData = resp.data;
          _this.reload()
        },800);
      });
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.font{
  text-align: center;
  font-size: xx-large;
}
.class{
  height: 700px;
  width: 100%;
  /*object-fit: cover;*/
  background-image: url(../../assets/beijing22.jpg);

}
.waterfull-continer{
  column-count: 3;
  column-gap: 100px;
}
.main{
  text-align: center;
  width: 1200px;
  height: 500px;
  margin: auto;
  position: absolute;
  top: 300px;
  left: 10px;
  right: 10px;
  bottom: 10px;
}
</style>

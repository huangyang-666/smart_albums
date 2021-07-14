<template>
  <div class="bg">
    <div class="z">
      <div class="q">
        ■■■
      </div>
      <div class="wz">
        零点云相册 随意上传 无限空间
      </div>
      <div class="wz1">
        智能识别整理 为您贴心管理相册
      </div>
      <div class="q1">
        ■■■
      </div>
      <div class="tplb">
        <div class="tp">
          <img src="../../assets/tubiao/1tb.png">
        </div>
        <div class="tp">
          大图预览
        </div>
      </div>
      <div class="tplb">
        <div class="tp">
          <img src="../../assets/tubiao/2tb.png">
        </div>
        <div class="tp">
          原图保存
        </div>
      </div>
      <div class="tplb">
        <div class="tp">
          <img src="../../assets/tubiao/3tb.png">
        </div>
        <div class="tp">
          下载方便
        </div>
      </div>
    </div>
    <div class="login-box1">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px;" class="login-box">
        <div class="login-title">账号密码登录</div>
        <!--  用户名-->
        <el-form-item label=" " prop="username"><h2>账号</h2>
          <el-input type="text" placeholder="请输入账号" v-model="form.username"/>
        </el-form-item>
        <!--      密码-->
        <el-form-item label=" " prop="password"><h2>密码</h2>
          <el-input type="password" placeholder="请输入密码" v-model="form.password"/>
        </el-form-item>
        <!--<el-input v-model="form.code" class="code" placeholder="验证码"></el-input>-->

        <!--<el-checkbox v-model="form.rememberMe" label="记住我" class="rememberMe"></el-checkbox>-->
        <!--      按钮-->
        <el-form-item class="login-button">
          <el-button type="primary" plain @click="submitForm('form')">登录</el-button>
          <div class="zc">
            <div class="wzw">没有账号？点击注册-></div>
            <el-button type="primary" plain @click="gotolink()">注册</el-button>
          </div>
        </el-form-item>
      </el-form>
    </div>


  </div>
</template>

<script>
export default {
  name: "Login",
  data: function () {
    return {
      vedioCanPlay: false,
      fixStyle: '',
      form: {
        username: '',
        password: '',
        rememberMe: false,
        code: '',
        uuid: '',
      },
      rules: {
        username: [
          {required: true, message: '请输入账号', trigger: 'blur'},
          {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
    gotolast(){
      // 点击跳转至上次浏览页面
      this.$router.go(-1);
    },
    gotolink(){
      //指定跳转地址
      this.$router.replace('/register');
    },
    submitForm: function (formName) {
      this.$refs[formName].validate(async valid => {
        if (valid) {
          //测试数据交互
          this.$http.post("http://localhost:8086/login",{
            username: this.form.username,
            password: this.form.password
          })
            .then(res => {
              // 登录成功
              console.log("登录成功！");
              console.log(res.data);
              /** 将Token保存到localStorage*/
              const authorization = res.data.Authorization;
              localStorage.token = authorization;
              this.msg = authorization;
              //是否登录
              sessionStorage.setItem('isLogin', 'true');
              this.$store.dispatch('asyncUpdateUser', {username: this.form.username});
              // alert('submit!');1
              this.$message({
                message: '恭喜你，验证通过！',
                duration: 500,
                type: 'success'
              });
              //编程式导航，以代码方式跳转
              // this.$router.push("/main");
              this.$router.push({name: 'Home1', params: {username: this.form.username}});
            })
            .catch(error => {
              this.$message({
                message: '账号或密码错误！',
                duration: 500,
                type: 'warning'
              });
              return false;
            });

        }
      });
    }
  },

}

</script>

<style scoped>
  .z{
    display: inline-block;
    width: 34%;
    padding-top: 200px;
    padding-left: 180px;
    text-align: left;
  }
  .q{
    color: rgba(255, 255, 255, 0.78);
    font-size: 30px;
  }
  .q1{
    color: rgba(255, 255, 255, 0.78);
    font-size: 30px;
    float: right;
  }
  .wz{
    color: whitesmoke;
    font-size: 40px;
    margin-left: 30px;
    margin-bottom: 10px;
    margin-top: 30px;
  }
  .wz1{
    color: #ffffff;
    font-size: 26px;
    margin-left: 30px;
    line-height: 60px;
  }
  .bg{
    width: 100%;
    height: 100%;
    position:fixed;
    min-width: 1000px;
    z-index:-10;
    zoom: 1;
    background-image: url("../../assets/bj/bjbjbjbjbj.jpg");
    background-repeat: no-repeat;
    background-size: cover;
    -webkit-background-size: cover;
    -o-background-size: cover;
    background-position: center 0;
  }

  .login-box1{
    color: #000000;
    width:50%;
    margin-top: 100px;
    float: right;
    display: inline-block;
    align-content: center;
    position: fixed;
  }
  .login-box{
    position: relative;
    width: 450px;
    margin:170px auto;
    border: 1px solid #DCDFE6;
    padding: 35px;
    border-radius: 5px;
    box-shadow: 0 0 45px #9899a0;
    background-color: rgba(255, 255, 255, 0.88);

  }
  .login-title{
    text-align: center;
    font-size: 20px;
  }
  .login-button{
    margin:50px 10px 30px;
  }
  .wzw{
    color: #000000;
    display: inline-block;
  }
  .zc{
    display: inline-block;
    float: right;
  }
  .tp{
    font-size: 18px;
    color: white;
    margin: 20px;
    text-align: center;
    float: left;
    width: 100px;
    display: inline-block;
  }
  img{
    width: 56%;
  }
  .tplb{
    margin-left: 40px;
    margin-top: 100px;
    width: 120px;
    float: left;
    display: inline-block;
  }
</style>

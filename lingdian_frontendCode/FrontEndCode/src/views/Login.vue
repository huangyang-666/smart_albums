<template>
  <div class="bg">
    <el-form ref="form" :model="form" :rules="rules" label-width="80px;" class="login-box">
      <div class="login-title">欢迎登录</div>
      <!--      用户名-->
      <el-form-item label="账号" prop="username">
        <el-input type="text" placeholder="请输入账号" v-model="form.username"/>
      </el-form-item>
      <!--      密码-->
      <el-form-item label="密码" prop="password">
        <el-input type="password" placeholder="请输入密码" v-model="form.password"/>
      </el-form-item>

      <!--<el-input v-model="form.code" class="code" placeholder="验证码"></el-input>-->

      <!--<el-checkbox v-model="form.rememberMe" label="记住我" class="rememberMe"></el-checkbox>-->
      <!--      按钮-->
      <el-form-item class="login-button">
        <el-button type="primary" plain @click="submitForm('form')">登录</el-button>
        <div class="zc">
          <div class="wz">没有账号？点击注册-></div>
          <el-button type="primary" plain @click="submitForm('form')">注册</el-button>
        </div>
      </el-form-item>
    </el-form>

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
    submitForm: function (formName) {
      this.$refs[formName].validate(async valid => {
        if (valid) {
          //测试数据交互
          const {data: res} = await this.$http.post("http://localhost:8080/userlogin",this.form);
          console.log(res);
          //查询到了就登录
          if (res === "success"){
            //是否登录
            sessionStorage.setItem('isLogin', 'true');
            this.$store.dispatch('asyncUpdateUser', {username: this.form.username});
            // alert('submit!');1
            this.$message({
              message: '恭喜你，验证通过！',
              type: 'success'
            });
            //编程式导航，以代码方式跳转
            // this.$router.push("/main");
            this.$router.push({name: 'Home1', params: {username: this.form.username}});
          }else {
            this.$message({
              message: '账号或密码错误！',
              type: 'warning'
            });
            return false;
          }
        }
      });
    }
  },
}

</script>

<style scoped>
.bg{
  width: 100%;
  height: 100%;
  position:fixed;
  min-width: 1000px;
  z-index:-10;
  zoom: 1;
  background-image: url("../assets/bj/denglu.jpg");
  background-repeat: no-repeat;
  background-size: cover;
  -webkit-background-size: cover;
  -o-background-size: cover;
  background-position: center 0;
}

.login-box{
  position: relative;
  width: 450px;
  margin: 170px auto;
  border: 1px solid #DCDFE6;
  padding: 35px;
  border-radius: 5px;
  box-shadow: 0 0 45px #9899a0;
}
.login-title{
  text-align: center;
  font-size: 20px;
}
.login-button{
  margin:50px 10px 30px;
}
.wz{
  color: #777777;
  display: inline-block;
}
.zc{
  display: inline-block;
  float: right;
}

</style>

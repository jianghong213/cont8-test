<template>
  <body id="login-page">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-container" label-position="left" label-width="0px">
      <h3 class="login_title">Log in</h3>
      <el-form-item>
        <el-input
          type="text"
          v-model="loginForm.loginName"
          auto-complete="off"
          placeholder="Username"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-input
          type="password"
          v-model="loginForm.password"
          auto-complete="off"
          placeholder="Password"
        ></el-input>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button
          type="primary"
          style="width: 100%; border: none"
          @click="login"
          >Login</el-button
        >
      </el-form-item>
      <el-button
          type="primary"
          style="width: 100%; border: none"
          @click="register"
          >Register</el-button>
    </el-form>
  </body>
</template>

<script>
import { userLogin } from "@/api/user";
export default {
  name: "Login",
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) {
        callback(new Error('please enter a valid username'))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('please enter a valid password, not less than 6 characters'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        username: "zhangsan",
        password: "123456"
      },
      rule: {
        username: [
          {required: true, message: 'please enter your username', trigger: 'blur' },
          { validator: validateUsername, trigger: 'blur' }
        ],
        password: [
          {required: true, message: 'please enter your password', trigger: 'blur' },
          { validator: validatePassword, trigger: 'blur' }]
      },
      passwordType: 'password',
      capsTooltip: false,
      loading: false,
      showDialog: false,
      redirect: undefined,
      otherQuery: {}
    }
  },

  methods: {
    register() {
      this.$router.push({path: '/register'})
    },
    login() {
      var _this = this;
      userLogin({
        loginName: this.loginForm.loginName,
        password: this.loginForm.password,
      }).then((resp) => {
        let code=resp.data.code;
        if(code==="200"){
          console.log(resp.data.code)
          var path = this.$route.query.redirect
          this.$router.push({path: '/index'})
        }else{
          alert(resp.data.msg);
          }
      });
    },
  },
};


</script>

<style scoped>
#login-page {
  background: url("../assets/img/bg.jpg") no-repeat;
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;

}
body {
  margin: 0px;
}
.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}
</style>


<template>
  <div class="login clearfix">
    <div class="login-wrap">
      <el-row type="flex" justify="center">
        <el-form class="login-container" label-position="left" label-width="0px" rules="registerRules">
          <h3>Sign Up
          </h3>
          <hr>
          <el-form-item prop="username" label="Username">
            <el-input v-model="user.username" placeholder="User Name"></el-input>
          </el-form-item>
          <el-form-item prop="email" label="Email">
            <el-input v-model="user.email" placeholder="Email"></el-input>
          </el-form-item>
          <el-form-item prop="password" label="Password">
            <el-input v-model="user.password" show-password placeholder="Password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon @click="doRegister()">Register</el-button>
            
          </el-form-item>
          <el-button type="primary" icon @click="login()">Back to login</el-button>
        </el-form>
      </el-row>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Register",
  data() {
    let reg = /(?!^(\d+|[a-zA-Z]+|[~!@#$%^&*?]+)$)^[\w~!@#$%^&*?]{6,12}$/
    var validateNewPwd = (rule, value, callback) => {
        if (!reg.test(value)) {
            callback(new Error('the password must be between 6 and 12 characters or numbers'))
        } else if (this.form.oldPasswd === value) {
            callback(new Error('new password can not same with the old one！'))
        } else {
            callback()
        }
    }
    var checkEmail = (rule, value, callback) => {
    const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
    if (!value) {
      return callback(new Error('email address can not be empty'))
    }
    setTimeout(() => {
      if (mailReg.test(value)) {
        callback()
      } else {
        callback(new Error('please enter the right email address'))
      }
    }, 100)
    }
    return {
      user: {
        username: "",
        email: "",
        password: ""
      },
      rule:{
        username:[
          { required: true, message: 'please put in your user name', trigger: 'blur' },
               { min: 2, max: 10, message: 'length between 2 and 10', trigger: 'blur' },
               {
                        required: true,
                        pattern: /^[\u4e00-\u9fa5_a-zA-Z0-9.·-]+$/,
                        message: 'Name does not support special characters',
                        trigger: 'blur'
               }
        ],
        email: [
                { required: true, message: 'please put in your email address' },
                { validator: checkEmail, trigger: 'blur' }
        ],
        password: [
                { required: true, message: 'please enter your new password', trigger: 'blur' },
                { validator: validateNewPwd, trigger: 'blur' }
            ],
        
      }

    };
  },
  created() {
    // console.log($);
    // console.log("1111");
  },
  methods: {
    login() {
      this.$router.push({path: '/login'})
    },
    doRegister() {
      if (!this.user.username) {
        this.$message.error("Please put in your username");
        return;
      } else if (!this.user.email) {
        this.$message.error("Please put in your email");
        return;
      } else if (this.user.email != null) {
        var reg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        if (!reg.test(this.user.email)) {
          this.$message.error("Please put in your email");
        } else if (!this.user.password) {
          this.$message.error("Please put in your password");
          return;
        } else {
          // this.$router.push({ path: "/" }); //无需向后台提交数据，方便前台调试
          axios
            .post("/enroll", {
              loginName: this.user.username,
              email: this.user.email,
              password: this.user.password
            })
            .then(res => {
              console.log("输出response.data", res.data);
              // console.log("输出response.data.status", res.data.status);
              if (res.data.code === "200") {
                this.$router.push({ path: "/login" });
                alert("Signed up! Back to Login!")
              } else {
                alert("Exist User！");
              }
            });
        }
      }
    }
  }
};
</script>
 
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.login {
  background: url("../assets/img/bg.jpg") no-repeat;
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}

.login-wrap {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
  width: 300px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
 
h3 {
  color: #0babeab8;
  font-size: 24px;
}
hr {
  background-color: #444;
  margin: 20px auto;
}
 
.el-button {
  width: 80%;
}
</style>
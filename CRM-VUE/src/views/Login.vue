<template>
  <div class="login-container">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" autocomplete="on" label-position="left">

      <div class="title-container">
        <h3 class="title">欢迎登录CRM系统</h3>
      </div>

      <el-form-item prop="username">
        <el-input
          ref="username"
          v-model="loginForm.username"
          placeholder="请输入用户名称"
          name="username"
          type="text"
          tabindex="1"
          autocomplete="on"
        />
      </el-form-item>

      <el-form-item prop="password">
         <el-input  tabindex="1" 
                    name="password"
                    ref="password"
                    placeholder="请输入密码" 
                    v-model="loginForm.password" 
                    autocomplete="on"
                    show-password></el-input>
      </el-form-item>
      
    

      <el-button  type="primary" style="width:100%;margin-bottom:30px;" @click="submit('loginForm')">登录</el-button>
      <!-- <el-button  type="primary" style="width:100%;margin-bottom:30px;" @click="resetForm('loginForm')">重置</el-button> -->
       <div class="tip">
         <span id="tip">Tip:请输入登录用户名称和登录密码！</span>
       </div>
      
    </el-form>
  </div>
</template>



<script>
import { validUsername } from '@/utils/validate'
// import { post } from '@/utils/http'

export default {
  name: 'Login',
  data(){
    const validateUsername  = ( rule, value, callback ) =>{
      if (value.trim().length === 0 ) {
        console.log(!validUsername(value));
        console.log(validUsername(value));
        callback(new Error('请输入用户名称'))
      } else {
        callback()
      }
    }
     const validatePassword  = ( rule, value, callback ) =>{
      if (value.trim().length === 0 ) {
        callback(new Error('请输入登录密码'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
          username: 'admin',
          password: 'admin'
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword}]
      }
    }

  },
  created() {
    // 适用用第三方登录
    // window.addEventListener('storage', this.afterQRScan)
  },
  mounted() {
    // if (this.loginForm.username === '') {
    //   this.$refs.username.focus()
    // } else if (this.loginForm.password === '') {
    //   this.$refs.password.focus()
    // }
  },
  destroyed() {
    // 销毁用第三方登录
    // window.removeEventListener('storage', this.afterQRScan)
  },
   methods:{
     //使用axios发送登录请求
     submit(formName){
        const params = {
          "userName":this.loginForm.username,
          "userPwd":this.loginForm.password,
          "newPwd":''
        }

       this.$refs[formName].validate((valid) => {
            if (valid) {
            this.$store.dispatch('Login/login',params)
            .then(()=>{
                if(this.$store.state.Login.loginInfo.data.pwdIsTrue === true){
                  this.$message({
                  message: this.$store.state.Login.loginInfo.data.loginTip,
                  type: 'success'
                });
                  localStorage.setItem('token',this.$store.state.Login.loginInfo.data.token);
                  localStorage.setItem('id',this.$store.state.Login.loginInfo.data.id)
                  localStorage.setItem('trueName',this.$store.state.Login.loginInfo.data.trueName);
                  localStorage.setItem('userName',this.$store.state.Login.loginInfo.data.userName);
                  this.$router.push("/home");
                }
              else{
                  this.$message.error(this.$store.state.Login.loginInfo.data.loginTip);
                // this.$route.push({ path: this.redirect || '/' , query: this.otherQuery})
                // this.loading = false
              }
            })
            .catch(e=>{
               this.$message.error("错误内容："+e);
                // this.loading = false
            })
        } else {
            return false;
        }
      })
     }
  }
}

</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */
$bg:rgb(33 72 105);
$light_gray:#fff;
$cursor: #fff;
@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}
/* reset element-ui css */
.login-container {
  height: 100vh;
  background: url('../assets/3.jpg') no-repeat 100%;

  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;
    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      // padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;
      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }
  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgb(33 72 105);
    border-radius: 5px;
    color: #454545;

  }
}
</style>

<style lang="scss" scoped>
$bg:#375e80;
$dark_gray:#889aa4;
$light_gray:#eee;
.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;
  background-size: 100%;
  .login-form {
    position: relative;
    width: 450px;
    max-width: 100%;
    padding: 50px 50px;
    margin: 0 auto;
    overflow: hidden;
    background: -webkit-gradient(linear, 0 0, 0 100%, from(#558ab9), to(#264a69e6));
    background: -moz-linear-gradient(top, #558ab9 0%, #264a69e6 100%);
    background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#558ab9), color-stop(100%,#264a69e6));
    background: -webkit-linear-gradient(top, #558ab9 0%,#264a69e6 100%);
    background: -o-linear-gradient(top, #558ab9 0%,#264a69e6 100%);
    background: -ms-linear-gradient(top, #558ab9 0%,#264a69e6 100%);
    background: linear-gradient(to bottom, #558ab9 0%, rgba(38, 74, 105, 0.86) 80%);
    -ms-filter: "progid:DXImageTransform.Microsoft.Gradient(startColorStr='#558ab9',endColorStr='#264a69e6',gradientType='0')";
    // background: linear-gradient(to bottom, #000000 0%,#ffffff 100%);
    margin-top: 12%;
  }
  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;
    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }
  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }
  .title-container {
    position: relative;
    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }
  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
  .thirdparty-button {
    position: absolute;
    right: 0;
    bottom: 6px;
  }
  @media only screen and (max-width: 470px) {
    .thirdparty-button {
      display: none;
    }
  }
}
.tip {
  color: #48bcd5;
  font-size: 12px;
  float: left;
}
</style>
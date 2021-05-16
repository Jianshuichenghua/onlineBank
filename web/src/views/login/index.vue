<template>
  <div class="login">
    <el-container class="container">
      <el-header>
        <h3 class="header-title">Online Banking</h3>
      </el-header>
      <el-container>
        <el-main>
          <div class="swith-container">
            <h1 class="swith-container-title">XYZ Online Banking App</h1>
            <hr />
            <p class="swith-container-content">
              This portal allows you to conduct most of your banking
              transactions such funds transfer, utility bills payment, as well
              as generate a list of all your transactions
            </p>

            <el-button
              class="swith-container-btn"
              type="primary"
              @click="_goToRegister()"
              >Not registered? Sign Up</el-button
            >
            <!-- <img src="../../assets/personal/login.png" alt="user" /> -->
          </div>
        </el-main>
        <el-aside width="40%">
          <div class="login-form">
            <h3 class="login-title">Login Here</h3>
            <el-form
              ref="loginForm"
              label-position="top"
              :rules="rules"
              :model="ruleForm"
            >
              <el-form-item prop="user" label="UserName:">
                <el-input
                  placeholder="Enter your preferred username"
                  v-model="ruleForm.user"
                ></el-input>
              </el-form-item>

              <el-form-item prop="password" label="Password:">
                <el-input
                  placeholder="Enter your password"
                  prefix-icon="el-icon-lock"
                  v-model="ruleForm.password"
                  show-password
                ></el-input>
              </el-form-item>

              <el-button
                type="primary"
                class="loginBtn"
                @click="loginYz('loginForm')"
                >Login</el-button
              >
            </el-form>
          </div>
        </el-aside>
      </el-container>
    </el-container>

    <!-- <div class="slideShadow" v-show="showSlide">
      <transition>
        <div class="slideSty animated bounce">
          <slide-verify
            @success="onSuccess"
            @fail="onFail"
            :sliderText="text"
            :w="350"
            :h="175"
            ref="slideDiv"
          ></slide-verify>
          <div class="iconBtn">
            <i class="el-icon-circle-close" @click="showSlide = false"></i
            ><i class="el-icon-refresh" @click="refresh"></i>
          </div>
        </div>
      </transition>
    </div> -->
  </div>
</template>

<script>
import SlideVerify from "@/components/SlideVerify";
export default {
  data() {
    return {
      notifyObj: null,
      text: "slide right",
      showSlide: false,
      ruleForm: {
        user: "admin",
        password: "123456",
      },
      rules: {
        user: [
          {
            required: true,
            message: "please Enter your password",
            trigger: "blur",
          },
          {
            min: 4,
            max: 15,
            message: "the length should be between 6 and 15 characters",
            trigger: "blur",
          },
        ],
        password: [
          {
            required: true,
            message: "pelase enter your password again",
            trigger: "blur",
          },
        ],
      },
    };
  },
  mounted() {},
  methods: {
    onSuccess() {
      // this.showSlide = false;
      this._login();
    },
    onFail() {
      this.$message.error("验证失败");
    },
    refresh() {
      this.$refs.slideDiv.reset();
    },
    loginYz(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
           this._login();
          // this.showSlide = true;
        } else {
          return;
        }
      });
    },
    _goToRegister() {
      this.$router.replace({ path: "/register" });
    },
    _login() {
      this.$store
        .dispatch("user/_login", this.ruleForm)
        .then((res) => {
        this.$router.push({ path: "/dashbord" });
            if (this.notifyObj) {
              this.notifyObj.close();
            }
            this.notifyObj = null;
        })
        .catch((error) => {
          this.refresh();
          this.$message.error(error);
        });
    },
    shopTip() {
      this.notifyObj = this.$notify({
        title: "提示",
        message:
          "目前有两个登陆角色，管理员和普通用户，账号分别为：admin、user,密码都为：123456",
        duration: 0,
        iconClass: "el-icon-s-opportunity",
      });
    },
  },
  components: {
    SlideVerify,
  },
};
</script>
<style scoped lang="scss">
.login {
  height: 100%;
  width: 100%;
  overflow: hidden;
}
.loginBox {
  height: 455px;
  width: 550px;
  margin: 0 auto;
}
.loginH2 {
  font-size: 38px;
  color: #fff;
  text-align: center;
}
.loginCon {
  margin-top: 30px;
  background: #eee;
  border-radius: 4px;
  .titleDiv {
    padding: 0 28px;
    background: #fff;
    position: relative;
    height: 120px;
    border-radius: 4px 4px 0 0;
    h3 {
      font-size: 22px;
      color: #555;
      font-weight: initial;
      padding-top: 23px;
    }
    p {
      font-size: 16px;
      color: #888;
      padding-top: 12px;
    }
    i {
      font-size: 65px;
      color: #ddd;
      position: absolute;
      right: 27px;
      top: 29px;
    }
  }
  .el-form {
    padding: 25px 25px 30px 25px;
    background: #eee;
    border-radius: 0 0 4px 4px;
  }
}

.loginBtn {
  width: 100%;
  background-color: #48aee9;
}
.slideShadow {
  top: 0;
  position: fixed;
  z-index: 999;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.6);
}
.slideSty {
  position: absolute;
  width: 380px;
  height: 311px;
  background: #e8e8e8;
  border: 1px solid #dcdcdc;
  left: 50%;
  top: 50%;
  margin-left: -188px;
  margin-top: -176px;
  z-index: 99;
  border-radius: 5px;
}
.iconBtn {
  padding: 9px 0 0 19px;
  color: #5f5f5f;
  border-top: 1px solid #d8d8d8;
  margin-top: 17px;
  i {
    font-size: 22px;
    cursor: pointer;
  }
  i:last-child {
    margin-left: 7px;
  }
}
</style>
<style>
.container {
  height: 100vh;
}

.el-header,
.el-footer {
  color: #fff;
  font-size: 20px;
  background-color: #48aee9;
}
.header-title {
  position: relative;
  left: 15%;
  top: 15px;
}
.el-aside {
  color: #333;
}

.el-main {
  color: #333;
}

.el-form--label-top .el-form-item__label {
  padding: 0;
}

.slideSty .slide-verify {
  margin: 13px auto 0 auto;
  width: 350px !important;
}
.slideSty .slide-verify-slider {
  width: 100% !important;
}
.slideSty .slide-verify-refresh-icon {
  display: none;
}
.login-title {
  color: #48aee9;
  font-size: 40px;
}
.login-form {
  margin-top: 40px;
  width: 360px;
  margin-left: 30px;
}

.login-item-tip {
  margin-top: 5px;
  font-size: 12px;
  color: #666;
}

.swith-container {
  padding: 20px;
  float: right;
  width: 70%;
  background-color: #eee;
}

.swith-container-title {
  padding: 30px 0;
  font-size: 45px;
  font-weight: lighter;
  color: #48aee9;
}

.swith-container-content {
  padding-top: 20px;
  color: blue;
}

.swith-container-btn {
  margin: 20px 0 50px 0;
  background-color: #48aee9;
}
</style>

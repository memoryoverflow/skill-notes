<template>
  <div class="login-box" id="app">
    <el-form>
      <el-input id="name" v-model="loginName" placeholder="请输入帐号">
        <template slot="prepend">帐号</template>
      </el-input>
      <el-input id="password" v-model="password" type="password" placeholder="请输入密码">
        <template slot="prepend">密码</template>
      </el-input>
      <el-button id="login" @click="check" style="width:100%" type="primary">登录</el-button>
    </el-form>
  </div>
</template>
<script type="text/javascript">
export default {
  data() {
    return {
      loginName: "",
      password: ""
    };
  },
  methods: {
    check: function(event) {
      var _this = this;
      //获取值
      var loginName = this.loginName;
      var password = this.password;
      if (loginName == "" || password == "") {
        this.$notify.error("必填项不能留空");
        return;
      }

      _this.$post("/login", {loginName:loginName,password:password}).then(res => {
        if (res.code == 0) {
          this.$notify.success("登陆成功");
          window.localStorage.removeItem("memoryoverflow.login");
          window.localStorage.setItem("memoryoverflow.login", res.data.token);
          _this.$router.push({
            name: "home"
          });
        }
      });
    }
  }
};
</script>
<style scoped>
.el-row {
  margin-bottom: 20px;
}
.el-input{
    margin: 20px 0;
}
.login-box {
  width: 500px !important;
  height: 300px;
  margin: 0 auto;
  margin-top: 12%;
}
</style>

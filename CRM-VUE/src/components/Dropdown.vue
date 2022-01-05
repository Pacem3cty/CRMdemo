<template>
  <el-row :gutter="24">
    <div class="el-main-font-left">
      <img alt="icon" width="40" height="40" src="../assets/icon.png" />CRM
    </div>
    <div class="el-main-font-right">
      <el-dropdown>
        <span class="el-dropdown-link">
          {{ roleName }}:{{ trueName
          }}<i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item
            ><span @click="turnToHome">首页</span></el-dropdown-item
          >
          <el-dropdown-item>
            <span @click="Logout">退出</span></el-dropdown-item
          >
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </el-row>
</template>

<script>
export default {
  data() {
    return {
      trueName: localStorage.getItem("trueName"),
      roleName: localStorage.getItem("roleName"),
    };
  },
  created(){
      },
  methods: {
    turnToHome() {
      this.$router.push("/home");
    },
    Logout() {
      localStorage.removeItem("trueName");
      localStorage.removeItem("id");
      localStorage.removeItem("roleName");
      localStorage.removeItem("token");
      localStorage.removeItem("userName");
      this.$cookie.delete('token');
      this.$router.push("/");
    },
    reload() {
      this.isRouterAlive = false;
      this.$nextTick(function () {
        this.isRouterAlive = true;
      });
    },
  },
};
</script>

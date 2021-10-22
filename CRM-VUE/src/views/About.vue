<template>
  <el-container style="width: 100%;height: 100vh;">
  <el-header height="46px">
     <Dropdown></Dropdown>

   
  </el-header>
  <el-container style="width: 100%;height: 95vh;">
    <el-aside style="width: 11%;height: 95vh;">
      <Menu></Menu>

    </el-aside>
    <el-main style="width: 89%;height: 95vh;">
      <el-breadcrumb separator="/" class="main-nav">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item><a href="/">活动管理</a></el-breadcrumb-item>
        <el-breadcrumb-item>活动列表</el-breadcrumb-item>
        <el-breadcrumb-item>活动详情</el-breadcrumb-item>
      </el-breadcrumb>


         
      <el-tabs type="border-card" style="margin-top: 0.5em;">
        <el-tab-pane label="1">1</el-tab-pane>
        <el-tab-pane label="2">2</el-tab-pane>
        <el-tab-pane label="3">3</el-tab-pane>
        <el-tab-pane label="4">4</el-tab-pane>
      </el-tabs>

     
    </el-main>
  </el-container>
</el-container>
</template>

<script>
  import Menu from "../components/Menu";
  import Dropdown from "../components/Dropdown";
  export default {
     //注册组件,
    components: { Menu,Dropdown },
   
  }
</script>



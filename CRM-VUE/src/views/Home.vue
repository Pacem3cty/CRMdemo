<template>
  <el-container style="width: 100%;height: 100vh;">
  <el-header height="46px">
      <Dropdown></Dropdown>

   
  </el-header>
  
  <el-container style="width: 100%;height: 95vh;background: #e9eef3;">
    <el-aside style="width: 11%;height: 95vh;float:left;">
      <Menu></Menu>
        

    </el-aside>
    <el-main style="width: 89%;height: 95vh;margin-left: 6px;">
      
      <router-view></router-view>

     
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



<!-- 菜单栏组件 -->
<template>
    <el-menu
      :default-active="this.$router.path"
      
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      unique-opened
      router
      
      >
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-s-marketing"></i>
          <span>营销管理</span>
        </template>
        <el-menu-item index="/SaleChance">
           <span slot="title" >营销机会管理</span>
        </el-menu-item>
        <el-menu-item index="/customerCenter">
            <span slot="title" >客户开发计划</span>
        </el-menu-item>
      </el-submenu>
      
      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-s-custom"></i>
          <span >客户管理</span>
        </template>

        <el-menu-item index="/equipment">
          <span slot="title">客户信息管理</span>
        </el-menu-item>

         <el-menu-item index="/dict">
          <span slot="title">客户流失管理</span>
        </el-menu-item>
      </el-submenu>

      <el-submenu index="3">
        <template slot="title">
          <i class="el-icon-s-order"></i>
          <span >服务管理</span>
        </template>
        <el-menu-item index="/equipment">
          <span slot="title">服务创建</span>
        </el-menu-item>
         <el-menu-item index="/dict">
          <span slot="title">服务分配</span>
        </el-menu-item>
        <el-menu-item index="/equipment">
          <span slot="title">服务处理</span>
        </el-menu-item>
        <el-menu-item index="/equipment">
          <span slot="title">服务反馈</span>
        </el-menu-item>
        <el-menu-item index="/equipment">
          <span slot="title">服务归档</span>
        </el-menu-item>
      </el-submenu>
      
      <el-submenu index="4">
        <template slot="title">
          <i class="el-icon-data-analysis"></i>
          <span >统计报表</span>
        </template>
        <el-menu-item index="/equipment">
          <span slot="title">客户贡献分析</span>
        </el-menu-item>
         <el-menu-item index="/dict">
          <span slot="title">客户构成分析</span>
        </el-menu-item>
        <el-menu-item index="/dict">
          <span slot="title">客户服务分析</span>
        </el-menu-item>
        <el-menu-item index="/dict">
          <span slot="title">客户流失分析</span>
        </el-menu-item>
      </el-submenu>

      <el-submenu index="5">
        <template slot="title">
          <i class="el-icon-setting"></i>
          <span >系统设置</span>
        </template>
        <el-menu-item index="/account">
          <span slot="title">资料修改</span>
        </el-menu-item>

         <el-menu-item index="/">
          <span slot="title">权限管理</span>
        </el-menu-item>

      </el-submenu>
      <!-- <el-menu-item >
          <i class="el-icon-setting"></i>
          <span slot="title" >系统</span>
      </el-menu-item> -->
    </el-menu>
</template>
<script>

export default({
     methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      
    }

   
})
</script>

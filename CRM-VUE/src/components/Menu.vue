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
           <span slot="title"><i class="el-icon-s-opportunity"></i>营销机会管理</span>
        </el-menu-item>
        <el-menu-item index="/CusDevPlan">
            <span slot="title" ><i class="el-icon-aim"></i>客户开发计划</span>
        </el-menu-item>
      </el-submenu>
      
      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-s-custom"></i>
          <span >客户管理</span>
        </template>

        <el-menu-item index="/Cus">
          <span slot="title"><i class="el-icon-s-management"></i>客户信息管理</span>
        </el-menu-item>

         <el-menu-item index="/CusLoss">
          <span slot="title"><i class="el-icon-s-release"></i>客户流失管理</span>
        </el-menu-item>
      </el-submenu>

      <el-submenu index="3">
        <template slot="title">
          <i class="el-icon-service"></i>
          <span >服务管理</span>
        </template>
        <el-menu-item index="/equipment">
          <span slot="title"><i class="el-icon-plus"></i>服务创建</span>
        </el-menu-item>
         <el-menu-item index="/dict">
          <span slot="title"><i class="el-icon-s-order"></i>服务分配</span>
        </el-menu-item>
        <el-menu-item index="/equipment">
          <span slot="title"><i class="el-icon-finished"></i>服务处理</span>
        </el-menu-item>
        <el-menu-item index="/equipment">
          <span slot="title"><i class="el-icon-receiving"></i>服务反馈</span>
        </el-menu-item>
        <el-menu-item index="/equipment">
          <span slot="title"><i class="el-icon-collection"></i>服务归档</span>
        </el-menu-item>
      </el-submenu>
      
      <el-submenu index="4">
        <template slot="title">
          <i class="el-icon-data-analysis"></i>
          <span >统计报表</span>
        </template>
        <el-menu-item index="/equipment">
          <span slot="title"><i class="el-icon-data-line"></i>客户贡献分析</span>
        </el-menu-item>
         <el-menu-item index="/dict">
          <span slot="title"><i class="el-icon-pie-chart"></i>客户构成分析</span>
        </el-menu-item>
        <el-menu-item index="/dict">
          <span slot="title"><i class="el-icon-data-line"></i>客户服务分析</span>
        </el-menu-item>
        <el-menu-item index="/dict">
          <span slot="title"><i class="el-icon-data-line"></i>客户流失分析</span>
        </el-menu-item>
      </el-submenu>

      <el-submenu index="5">
        <template slot="title">
          <i class="el-icon-setting"></i>
          <span >系统设置</span>
        </template>
         <el-menu-item index="/Account">
          <span slot="title"><i class="el-icon-s-tools"></i>资料修改</span>
        </el-menu-item>
        
         <el-menu-item index="/Account">
          <span slot="title"><i class="el-icon-notebook-2"></i>字典管理</span>
        </el-menu-item>

         <el-menu-item index="/User">
          <span slot="title"><i class="el-icon-user"></i>用户管理</span>
        </el-menu-item>

         <el-menu-item index="/Role">
          <span slot="title"><i class="el-icon-notebook-2"></i>角色管理</span>
        </el-menu-item>

        <el-menu-item index="/Module">
          <span slot="title"><i class="el-icon-key"></i>资源管理</span>
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

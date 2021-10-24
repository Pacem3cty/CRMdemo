<template>
 <el-row :gutter="24">
    <div class="el-main-font-left"><img alt="icon" width="40" height="40" src="../assets/icon.png">CRM</div>
    <div class="el-main-font-right">
        <el-dropdown>
            <span class="el-dropdown-link">
                个人用户:{{trueName}}<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
        <el-dropdown-menu slot="dropdown">
            <el-dropdown-item ><router-link :to="{ path: '/home'}">首页</router-link></el-dropdown-item>
            <el-dropdown-item > <span @click="Logout">退出</span></el-dropdown-item>
        </el-dropdown-menu>
        </el-dropdown>
    </div>
 </el-row>

</template>

<script>

export default({

    data(){
        
        return{
        trueName:localStorage.getItem("trueName")
        };
    },
    methods:{
        Logout(){
            localStorage.removeItem('trueName');
            localStorage.removeItem('token');
            localStorage.removeItem('userName')
            this.$router.push('/');
        }
    }
})
</script>

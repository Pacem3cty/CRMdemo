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

    <el-dialog  visible.sync="true">
      <el-row :gutter="15">
      <el-form ref="queryForm" :model="queryForm" :rules="rules" size="medium" label-width="100px">
        <el-col :span="24">
          <el-form-item label="用户角色" prop="roleName">
            <el-select v-model="queryForm.roleName" placeholder="请选择用户角色" multiple filterable clearable
              :style="{width: '100%'}"></el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="单行文本" prop="field110">
            <el-input v-model="queryForm.field110" placeholder="请输入单行文本" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="单行文本" prop="field111">
            <el-input v-model="queryForm.field111" placeholder="请输入单行文本" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item size="large">
            <el-button type="primary" @click="submitForm">提交</el-button>
            <el-button @click="resetForm">重置</el-button>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
    </el-dialog>
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
   props: [],
  data() {
    return {
      queryForm: {
        roleName: undefined,
        field110: undefined,
        field111: undefined,
      },
      rules: {
        roleName: [{
          required: true,
          message: '请选择用户角色',
          trigger: 'change'
        }],
        field110: [{
          required: true,
          message: '请输入单行文本',
          trigger: 'blur'
        }],
        field111: [{
          required: true,
          message: '请输入单行文本',
          trigger: 'blur'
        }],
      },
    }
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {},
  methods: {
    submitForm() {
      this.$refs['queryForm'].validate(valid => {
        if (!valid) return
        // TODO 提交表单
      })
    },
    resetForm() {
      this.$refs['queryForm'].resetFields()
    },
  }
}

</script>
<style>
</style>



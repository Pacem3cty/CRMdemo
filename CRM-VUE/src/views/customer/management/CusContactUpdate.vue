<template>
  <div>
    <el-row :gutter="15">
      <el-form ref="updateForm" :model="updateForm" :rules="rules" size="medium" label-width="100px">
        <el-col :span="12">
          <el-form-item label="编号" prop="id">
            <el-input v-model="updateForm.id" placeholder="请选择编号" readonly :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="交往时间" prop="contactTime">
            <el-date-picker v-model="updateForm.contactTime" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
              :style="{width: '100%'}" placeholder="请选择交往时间" clearable></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="交往地址" prop="contactAddress">
            <el-input v-model="updateForm.contactAddress" placeholder="请输入交往地址" clearable
              :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="交往概述" prop="overview">
            <el-input v-model="updateForm.overview" placeholder="请输入交往概述" clearable :style="{width: '100%'}">
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
  </div>
</template>
<script>
export default {
  components: {},
  props: ["multiple","id"],
  data() {
    return {
      updateForm: {
        id: this.$props.multiple[0].id,
        contactTime: this.$props.multiple[0].contactTime,
        contactAddress: this.$props.multiple[0].contactAddress,
        overview: this.$props.multiple[0].overview,
      },
      updateDate:"",
      rules: {
        id: [{
          required: true,
          message: '请选择编号',
          trigger: 'blur'
        }],
        contactTime: [{
          required: true,
          message: '请选择交往时间',
          trigger: 'change'
        }],
        contactAddress: [{
          required: true,
          message: '请输入交往地址',
          trigger: 'blur'
        }],
        overview: [{
          required: true,
          message: '请输入交往概述',
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
    setUpdateDate() {
      let date = new Date();
      this.updateDate =
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
    },
    submitForm() {
      this.setUpdateDate();
      this.$refs["updateForm"].validate((valid) => {
        if (valid) {
          const params = {
            id: this.updateForm.id,
            cusId:this.$props.id,
            contactTime: this.updateForm.contactTime,
            contactAddress: this.updateForm.contactAddress,
            overview: this.updateForm.overview,
            isValid: 0,
            createDate: this.$props.multiple[0].createDate,
            updateDate: this.updateDate,
          };
          this.$store
            .dispatch("CusContact/update", params)
            .then(() => {
              if (this.$store.state.CusContact.updateInfo.data === true) {
                this.$emit("onAdd");
                this.$emit("queryAll");
                this.$message({
                  message: "修改操作成功！",
                  type: "success",
                });
              } else {
                this.$message.error("修改操作失败！");
                this.$emit("onAdd");
                this.$emit("queryAll");
                this.resetForm();
              }
              if (this.$store.state.CusContact.updateInfo.code === 403) {
                this.$message({
                  message: "当前角色无相关权限",
                  type: "warning",
                });
                this.$emit("onAdd");
                this.$emit("queryAll");
                return;
              }
            })
            .catch((e) => {
              this.$message.error("新增操作失败！发生错误：" + e);
              this.resetForm();
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm() {
      this.updateForm.contactTime = this.$props.multiple[0].contactTime;
      this.updateForm.contactAddress = this.$props.multiple[0].contactAddress;
      this.updateForm.overview = this.$props.multiple[0].overview;
    },
  }
}

</script>
<style>
</style>

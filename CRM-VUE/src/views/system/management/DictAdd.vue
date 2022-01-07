<template>
  <div>
    <el-row :gutter="15">
      <el-form ref="addForm" :model="addForm" :rules="rules" size="medium" label-width="100px">
        <el-col :span="12">
          <el-form-item label="编号" prop="id">
            <el-input v-model="addForm.id" placeholder="请选择编号" readonly :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="数据类型" prop="dataType">
            <el-input v-model="addForm.dataType" placeholder="请选择数据类型" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="数据名称" prop="dataName">
            <el-input v-model="addForm.dataName" placeholder="请输入数据名称" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="数据值" prop="dataValue">
            <el-input v-model="addForm.dataValue" placeholder="请输入数据值" clearable :style="{width: '100%'}">
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
  props: [],
  data() {
    return {
      addForm: {
        id: undefined,
        dataType: undefined,
        dataName: undefined,
        dataValue: undefined,
      },
      rules: {
        id: [{
          required: true,
          message: '请选择编号',
          trigger: 'blur'
        }],
        dataType: [{
          required: true,
          message: '请选择数据类型',
          trigger: 'blur'
        }],
        dataName: [{
          required: true,
          message: '请输入数据名称',
          trigger: 'blur'
        }],
        dataValue: [{
          required: true,
          message: '请输入数据值',
          trigger: 'blur'
        }],
      },
    }
  },
  computed: {},
  watch: {},
  created() {
      this.init();
  },
  mounted() {},
  methods: {
      init() {
      this.$store
        .dispatch("Dict/getCurrentId", null) //从API中匹配相应的路径发送请求以及请求参数
        .then(() => {
          if (this.$store.state.Dict.currentIdInfo.code === 200) {
            //状态码为200 即请求成功
            this.addForm.id = this.$store.state.Dict.currentIdInfo.data;
          } else {
            this.$emit("onAdd");
            this.$emit("reInit");
            this.$message.error("获取编号失败导致无法执行添加操作！");
          }
          if (this.$store.state.Dict.currentIdInfo.code === 403) {
                this.$message({
                  message: "当前角色无相关权限",
                  type: "warning",
                });
                this.$emit("onAdd");
                this.$emit("reInit");
                return;
              }
        })
        .catch((e) => {
          this.$emit("onAdd");
          this.$emit("reInit");
          this.$message.error(
            "获取编号失败导致无法执行添加操作！发生错误：" + e
          );
        });
    },
    submitForm() {
     let date = new Date();
      this.createDate =
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
      this.$refs["addForm"].validate((valid) => {
        if (valid) {
          const params = {
            id: this.addForm.id,
            dataType: this.addForm.dataType,
            dataName: this.addForm.dataName,
            dataValue:this.addForm.dataValue,
            isValid: 0,
            createDate: this.createDate,
            updateDate: "",
          };
          this.$store
            .dispatch("Dict/add", params)
            .then(() => {
              if (this.$store.state.Dict.addInfo.data === true) {
                this.$emit("onAdd");
                this.$emit("reInit");
                this.$message({
                  message: "新增操作成功！",
                  type: "success",
                });
              } else {
                this.$message.error("新增操作失败！");
                this.resetForm();
              }
              if (this.$store.state.Dict.addInfo.code === 403) {
                this.$message({
                  message: "当前角色无相关权限",
                  type: "warning",
                });
                this.$emit("onAdd");
                this.$emit("reInit");
                return;
              }
            })
            .catch((e) => {
              this.$message.error("新增操作失败！发生错误：" + e);
              this.resetForm();
            });
        } else {
          console.log("error submit!");
          return false;
        }
      });
    },
    resetForm() {
      this.addForm.dataType = "";
      this.addForm.dataName = "";
      this.addForm.dataValue = "";
    },
  }
}

</script>
<style>
</style>

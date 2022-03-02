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
          <el-form-item label="数据类型" prop="dataType">
            <el-input v-model="updateForm.dataType" placeholder="请选择数据类型" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="数据名称" prop="dataName">
            <el-input v-model="updateForm.dataName" placeholder="请输入数据名称" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="数据值" prop="dataValue">
            <el-input v-model="updateForm.dataValue" placeholder="请输入数据值" clearable :style="{width: '100%'}">
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
  props: ["multiple"],
  data() {
    return {
      updateForm: {
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
  watch: {
      multiple: function () {
      if (this.$props.multiple.length !== 0) {
        this.init();
      }
    },
  },
  created() {
      this.init();
  },
  mounted() {},
  methods: {
      setUpdateDate() {
      let date = new Date();
      this.updateDate =
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
    },
    init() {
      this.updateForm.id = this.$props.multiple[0].id;
      this.updateForm.dataType = this.$props.multiple[0].dataType;
      this.updateForm.dataName = this.$props.multiple[0].dataName;
      this.updateForm.dataValue = this.$props.multiple[0].dataValue;
    },
    submitForm() {
      this.setUpdateDate();
      this.$refs["updateForm"].validate((valid) => {
        if (valid) {
          const params = {
            id: this.updateForm.id,
            dataType: this.updateForm.dataType,
            dataName: this.updateForm.dataName,
            dataValue:this.updateForm.dataValue,
            isValid: 0,
            createDate: this.$props.multiple[0].createDate,
            updateDate: this.updateDate,
          };
          this.$store
            .dispatch("Dict/update", params)
            .then(() => {
              if (this.$store.state.Dict.updateInfo.data === true) {
                this.$emit("onAdd");
                this.$emit("reInit");
                this.$message({
                  message: "修改操作成功！",
                  type: "success",
                });
              } else {
                this.$message.error("修改操作失败！");
                this.resetForm();
              }
              if (this.$store.state.Dict.updateInfo.code === 403) {
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
              this.$message.error("修改操作失败！发生错误：" + e);
              this.resetForm();
            });
        } else {
          console.log("error submit!");
          return false;
        }
      });
    },
    resetForm() {
      this.addForm.dataType = this.$props.multiple[0].id;
      this.addForm.dataName = this.$props.multiple[0].dataName;
      this.addForm.dataValue = this.$props.multiple[0].dataValue;
  }
}
}
</script>
<style>
</style>
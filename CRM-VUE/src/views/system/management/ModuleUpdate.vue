<template>
  <div>
    <el-row :gutter="15">
      <el-form
        ref="updateForm"
        :model="updateForm"
        :rules="rules"
        size="medium"
        label-width="100px"
      >
        <el-col :span="12">
          <el-form-item label="资源编号" prop="id">
            <el-input
              v-model="updateForm.id"
              placeholder="请输入资源编号"
              readonly
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="父级编号" prop="parentId">
            <el-input
              v-model="updateForm.parentId"
              placeholder="请输入父级编号"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="资源名称" prop="moduleName">
            <el-input
              v-model="updateForm.moduleName"
              placeholder="请输入资源名称"
              clearable
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="权限码" prop="optValue">
            <el-input
              v-model="updateForm.optValue"
              placeholder="请输入权限码"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="资源地址" prop="url">
            <el-input
              v-model="updateForm.url"
              placeholder="请输入资源地址"
              clearable
              :style="{ width: '100%' }"
            >
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
        id: "",
        parentId: "",
        moduleName: "",
        optValue: "",
        url: "",
      },
      rules: {
        id: [
          {
            required: true,
            message: "请选择资源编号",
            trigger: "blur",
          },
        ],
        parentId: [
          {
            required: true,
            message: "请输入父级编号",
            trigger: "blur",
          },
        ],
        moduleName: [
          {
            required: true,
            message: "请输入资源名称",
            trigger: "blur",
          },
        ],
        optValue: [
          {
            required: true,
            message: "请输入权限码",
            trigger: "blur",
          },
        ],
        url: [],
      },
    };
  },
  computed: {},
  watch: {},
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
      this.updateForm.parentId = this.$props.multiple[0].parentId;
      this.updateForm.moduleName = this.$props.multiple[0].moduleName;
      this.updateForm.optValue = this.$props.multiple[0].optValue;
      this.updateForm.url = this.$props.multiple[0].url;
    },
    submitForm() {
      this.setUpdateDate();
      this.$refs["updateForm"].validate((valid) => {
        if (valid) {
          const params = {
            id: this.updateForm.id,
            parentId: this.updateForm.parentId,
            moduleName: this.updateForm.moduleName,
            optValue: this.updateForm.optValue,
            url: this.updateForm.url,
            isValid: 0,
            createDate: this.$props.multiple[0].createDate,
            updateDate: this.updateDate,
          };
          this.$store
            .dispatch("Module/update", params)
            .then(() => {
              if (this.$store.state.Module.updateInfo.data === true) {
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
              if (this.$store.state.Module.updateInfo.code === 403) {
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
      this.updateForm.parentId = this.$props.multiple[0].parentId;
      this.updateForm.moduleName = this.$props.multiple[0].moduleName;
      this.updateForm.optValue = this.$props.multiple[0].optValue;
      this.updateForm.url = this.$props.multiple[0].url;
    },
  },
};
</script>
<style>
</style>

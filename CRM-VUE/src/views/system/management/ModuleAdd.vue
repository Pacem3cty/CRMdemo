<template>
  <div>
    <el-row :gutter="15">
      <el-form
        ref="addForm"
        :model="addForm"
        :rules="rules"
        size="medium"
        label-width="100px"
      >
        <el-col :span="12">
          <el-form-item label="资源编号" prop="id">
            <el-input
              v-model="addForm.id"
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
              v-model="addForm.parentId"
              placeholder="请输入父级编号"
              readonly
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="资源名称" prop="moduleName">
            <el-input
              v-model="addForm.moduleName"
              placeholder="请输入资源名称"
              clearable
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="权限码" prop="optValue">
            <el-input
              v-model="addForm.optValue"
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
              v-model="addForm.url"
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
  props: [],
  data() {
    return {
      addForm: {
        id: "",
        parentId: -1,
        moduleName: "",
        optValue: "",
        url: "",
        createDate: "",
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
    init() {
      this.$store
        .dispatch("Module/getCurrentId", null) //从API中匹配相应的路径发送请求以及请求参数
        .then(() => {
          if (this.$store.state.Module.currentIdInfo.code === 200) {
            //状态码为200 即请求成功
            this.addForm.id = this.$store.state.Module.currentIdInfo.data;
          } else {
            this.$emit("onAdd");
            this.$emit("reInit");
            this.$message.error("获取编号失败导致无法执行添加操作！");
          }
          if (this.$store.state.Module.currentIdInfo.code === 403) {
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
            parentId: this.addForm.parentId,
            moduleName: this.addForm.moduleName,
            optValue: this.addForm.optValue,
            orders: this.addForm.id,
            url: this.addForm.url,
            isValid: 0,
            createDate: this.createDate,
            updateDate: "",
          };
          this.$store
            .dispatch("Module/add", params)
            .then(() => {
              if (this.$store.state.Module.addInfo.data === true) {
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
              if (this.$store.state.Module.addInfo.code === 403) {
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
        // TODO 提交表单
      });
    },
    resetForm() {
      this.updateForm.moduleName = "";
      this.updateForm.optValue = "";
      this.updateForm.url = "";
    },
  },
};
</script>
<style>
</style>

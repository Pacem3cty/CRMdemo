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
          <el-form-item label="编号" prop="id">
            <el-input
              v-model="addForm.id"
              placeholder="请选择编号"
              readonly
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="角色名称" prop="roleName">
            <el-input
              v-model="addForm.roleName"
              placeholder="请输入角色名称"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="角色备注" prop="roleRemark">
            <el-input
              v-model="addForm.roleRemark"
              placeholder="请输入角色备注"
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
        roleName: "",
        roleRemark: "",
      },
      createDate: "",
      rules: {
        id: [
          {
            required: true,
            message: "请选择编号",
            trigger: "blur",
          },
        ],
        roleName: [
          {
            required: true,
            message: "请输入角色名称",
            trigger: "blur",
          },
          {
            pattern: /[^\d^%&',;./=?~\x22$\x22]+/,
            message: "请输入正确的角色名称格式",
            trigger: "blur",
          },
        ],
        roleRemark: [],
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
        .dispatch("Role/getCurrentId", null) //从API中匹配相应的路径发送请求以及请求参数
        .then(() => {
          if (this.$store.state.Role.currentIdInfo.code === 200) {
            //状态码为200 即请求成功
            this.addForm.id = this.$store.state.Role.currentIdInfo.data;
          } else {
            this.$emit("onAdd");
            this.$emit("reInit");
            this.$message.error("获取编号失败导致无法执行添加操作！");
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
            roleName: this.addForm.roleName,
            roleRemark: this.addForm.roleRemark,
            isValid: 0,
            createDate: this.createDate,
            updateDate: "",
          };
          this.$store
            .dispatch("Role/add", params)
            .then(() => {
              if (this.$store.state.Role.addInfo.data === true) {
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
      this.addForm.roleName = "";
      this.addForm.roleRemark = "";
    },
  },
};
</script>
<style>
</style>
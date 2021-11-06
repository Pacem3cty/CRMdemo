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
          <el-form-item label="编号" prop="id">
            <el-input
              v-model="updateForm.id"
              placeholder="请选择编号"
              readonly
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="角色名称" prop="roleName">
            <el-input
              v-model="updateForm.roleName"
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
              v-model="updateForm.roleRemark"
              placeholder="请输入角色备注"
              clearable
              :style="{ width: '100%' }"
            ></el-input>
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
        roleName: "",
        roleRemark: "",
      },
      updateDate: "",
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
    init(){
        this.updateForm.id = this.$props.multiple[0].id;
        this.updateForm.roleName = this.$props.multiple[0].roleName;
        this.updateForm.roleRemark = this.$props.multiple[0].roleRemark;
    },
    submitForm() {
      this.setUpdateDate();
      this.$refs["updateForm"].validate((valid) => {
        if (valid) {
          const params = {
            id: this.updateForm.id,
            roleName: this.updateForm.roleName,
            roleRemark: this.updateForm.roleRemark,
            isValid: 0,
            createDate: this.$props.multiple[0].createDate,
            updateDate: this.updateDate,
          };
          this.$store
            .dispatch("Role/update", params)
            .then(() => {
              if (this.$store.state.Role.updateInfo.data === true) {
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
      this.updateForm.roleName = this.$props.multiple[0].roleName;
      this.updateForm.roleRemark = this.$props.multiple[0].roleRemark;
    },
  },
};
</script>
<style>
</style>


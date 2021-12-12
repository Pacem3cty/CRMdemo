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
          <el-form-item label="序号" prop="id">
            <el-input
              v-model="updateForm.id"
              placeholder="请输入序号"
              readonly
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="流失编号" prop="lossId">
            <el-input
              v-model="updateForm.lossId"
              placeholder="请输入流失编号"
              readonly
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="暂缓措施" prop="measure">
            <el-input
              v-model="updateForm.measure"
              placeholder="请输入暂缓措施"
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
  props: ["multiple", "id"],
  data() {
    return {
      updateForm: {
        id: this.$props.multiple[0].id,
        lossId: this.$props.multiple[0].lossId,
        measure: this.$props.multiple[0].measure,
      },
      updateDate: "",
      rules: {
        id: [
          {
            required: true,
            message: "请输入序号",
            trigger: "blur",
          },
        ],
        lossId: [
          {
            required: true,
            message: "请输入流失编号",
            trigger: "blur",
          },
        ],
        measure: [
          {
            required: true,
            message: "请输入暂缓措施",
            trigger: "blur",
          },
        ],
      },
    };
  },
  computed: {},
  watch: {},
  created() {
  },
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
            lossId: this.updateForm.lossId,
            measure: this.updateForm.measure,
            updateDate: this.updateDate,
          };
          this.$store
            .dispatch("CusReprieve/update", params)
            .then(() => {
              if (this.$store.state.CusReprieve.updateInfo.data === true) {
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
      this.updateForm.measure = this.$props.multiple[0].measure;
    },
  },
};
</script>
<style>
</style>

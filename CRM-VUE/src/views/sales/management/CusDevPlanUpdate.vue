
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
              placeholder="请输入编号"
              readonly
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="机会编号" prop="saleChanceId">
            <el-input
              v-model="updateForm.saleChanceId"
              placeholder="请输入机会编号"
              readonly
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="计划项" prop="planItem">
            <el-input
              v-model="updateForm.planItem"
              placeholder="请输入计划项"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="计划日期" prop="planDate">
            <el-date-picker
              v-model="updateForm.planDate"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              :style="{ width: '100%' }"
              placeholder="请选择计划日期"
              clearable
            ></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="执行效果" prop="exeAffect">
            <el-input
              v-model="updateForm.exeAffect"
              placeholder="请输入执行效果"
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
  props: ["multiple"],
  data() {
    return {
      updateForm: {
        id: "",
        saleChanceId: "",
        planItem: "",
        planDate: "",
        exeAffect: "",
      },
      createDate: "",
      rules: {
        id: [
          {
            required: true,
            message: "请输入编号",
            trigger: "blur",
          },
        ],
        saleChanceId: [
          {
            required: true,
            message: "请输入机会编号",
            trigger: "blur",
          },
        ],
        planItem: [
          {
            required: true,
            message: "请输入计划项",
            trigger: "blur",
          },
        ],
        planDate: [
          {
            required: true,
            message: "请选择计划日期",
            trigger: "change",
          },
        ],
        exeAffect: [
          {
            required: true,
            message: "请输入执行效果",
            trigger: "blur",
          },
        ],
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
    init() {
      let date = new Date();
      this.updateDate =
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
      this.updateForm.id = this.$props.multiple[0].id;
      this.updateForm.saleChanceId = this.$props.multiple[0].saleChanceId;
      this.updateForm.planItem = this.$props.multiple[0].planItem;
      this.updateForm.planDate = this.$props.multiple[0].planDate;
      this.updateForm.exeAffect = this.$props.multiple[0].exeAffect;
    },
    submitForm() {
      this.$refs["updateForm"].validate((valid) => {
        if (valid) {
          const params = {
            id: this.updateForm.id,
            saleChanceId: this.updateForm.saleChanceId,
            planItem: this.updateForm.planItem,
            planDate: this.updateForm.planDate,
            exeAffect: this.updateForm.exeAffect,
            createDate: this.$props.multiple[0].createDate,
            updateDate: this.updateDate,
            isValid: 0,
          };
          this.$store
            .dispatch("Sales/updateCusDevPlan", params)
            .then(() => {
              if (this.$store.state.Sales.updateCusDevPlanInfo.data === true) {
                this.$emit("onAdd");
                this.$emit("queryAll");
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
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm() {
      this.updateForm.planItem = this.$props.multiple[0].planItem;
      this.updateForm.planDate = this.$props.multiple[0].planDate;
      this.updateForm.exeAffect = this.$props.multiple[0].exeAffect;
    },
  },
};
</script>
<style>
</style>
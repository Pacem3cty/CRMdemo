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
              v-model="addForm.saleChanceId"
              placeholder="请输入机会编号"
              readonly
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="计划项" prop="planItem">
            <el-input
              v-model="addForm.planItem"
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
              v-model="addForm.planDate"
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
              v-model="addForm.exeAffect"
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
  props: ["salesChanceId"],
  data() {
    return {
      addForm: {
        id: "",
        saleChanceId: this.$props.salesChanceId,
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
  watch: {},
  created() {
    this.init();
  },
  mounted() {},
  methods: {
    init() {
      let date = new Date();
      this.createDate =
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
      this.$store
        .dispatch("Sales/getCusDevPlanCurrentId", null) //从API中匹配相应的路径发送请求以及请求参数
        .then(() => {
          if (this.$store.state.Sales.cusDevPlanCurrentIdInfo.code === 200) {
            //状态码为200 即请求成功
            this.addForm.id =
              this.$store.state.Sales.cusDevPlanCurrentIdInfo.data;
          } else {
            this.$emit("onAdd");
            this.$emit("queryAll");
            this.$message.error("获取编号失败导致无法执行添加操作！");
          }
        })
        .catch((e) => {
          this.$emit("onAdd");
          this.$emit("queryAll");
          this.$message.error(
            "获取编号失败导致无法执行添加操作！发生错误：" + e
          );
        });
    },
    submitForm() {
      this.$refs["addForm"].validate((valid) => {
        if (valid) {
          const params = {
            id: this.addForm.id,
            saleChanceId: this.addForm.saleChanceId,
            planItem: this.addForm.planItem,
            planDate: this.addForm.planDate,
            exeAffect: this.addForm.exeAffect,
            createDate: this.createDate,
            updateDate: "",
            isValid: 0,
          };
          this.$store
            .dispatch("Sales/addCusDevPlan", params)
            .then(() => {
              if (this.$store.state.Sales.addCusDevPlanInfo.data === true) {
                this.$emit("onAdd");
                this.$emit("queryAll");
                this.$message({
                  message: "新增操作成功！",
                  type: "success",
                });
              } else {
                this.$message.error("新增操作失败！");
                this.resetForm();
              }
              if (this.$store.state.Sales.addCusDevPlanInfo.code === 403) {
                this.$message({
                  message: "当前角色无相关权限",
                  type: "warning",
                });
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
      this.addForm.planItem = "";
      this.addForm.planDate = "";
      this.addForm.exeAffect = "";
    },
  },
};
</script>
<style>
</style>
<template>
  <div>
    <h5 style="color: red">*编号项不得修改</h5>
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
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="机会来源" prop="chanceSource">
            <el-input
              v-model="addForm.chanceSource"
              placeholder="请输入机会来源"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="客户名称" prop="customerName">
            <el-input
              v-model="addForm.customerName"
              placeholder="请输入客户名称"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="成功概率(%)" prop="probability">
            <el-input
              v-model="addForm.probability"
              placeholder="请输入成功概率(%)"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="概述" prop="overview">
            <el-input
              v-model="addForm.overview"
              type="textarea"
              placeholder="请输入概述"
              :maxlength="125"
              :autosize="{ minRows: 4, maxRows: 4 }"
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="联系人" prop="contractPerson">
            <el-input
              v-model="addForm.contractPerson"
              placeholder="请输入联系人"
              clearable
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="联系电话" prop="contractPhone">
            <el-input
              v-model="addForm.contractPhone"
              placeholder="请输入联系电话"
              clearable
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="机会描述" prop="description">
            <el-input
              v-model="addForm.description"
              type="textarea"
              placeholder="请输入机会描述"
              :maxlength="125"
              :autosize="{ minRows: 4, maxRows: 4 }"
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="指派人" prop="assignPerson">
            <el-input
              v-model="addForm.assignPerson"
              placeholder="请输入指派人"
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
  data() {
    return {
      outerVisible: false,
      innerVisible: false,
      addForm: {
        id: "",
        chanceSource: "",
        customerName: "",
        probability: "",
        overview: "",
        contractPerson: "",
        contractPhone: "",
        description: "",
        assignPerson: "",
      },
      assignDate: "",
      createDate: "",
      state: 0,
      updateDate: "",
      rules: {
        id: [
          {
            required: true,
            message: "请输入编号",
            trigger: "blur",
          },
        ],
        chanceSource: [
          {
            required: true,
            message: "请输入机会来源",
            trigger: "blur",
          },
        ],
        customerName: [
          {
            required: true,
            message: "请输入客户名称",
            trigger: "blur",
          },
        ],
        probability: [
          {
            required: true,
            message: "请输入成功概率(%)",
            trigger: "blur",
          },
          {
            pattern: /^(?:0|[1-9][0-9]?|100)$/,
            message: "请输入0-100内（包括0-100）的数字！",
            trigger: "blur",
          },
        ],
        overview: [],
        contractPerson: [
          {
            required: true,
            message: "请输入联系人",
            trigger: "blur",
          },
        ],
        contractPhone: [
          {
            required: true,
            message: "请输入联系电话",
            trigger: "blur",
          },
          {
            pattern: /^1[0-9]{10}$/,
            message: "请输入正确的手机号码格式！",
            trigger: "blur",
          },
        ],
        description: [],
        assignPerson: [],
      },
    };
  },
  created() {
    this.outerVisible = this.outerVisible1;
    this.init();
    console.log(this.outerVisible1); //我是字符串
  },
  methods: {
    //方法区规划 新增编号获取（当前记录最大编号+1）、新增请求、指派人信息不存在时为未分配并不设置分配日期
    setAssignDate() {
      let date = new Date();
      this.assignDate =
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
    },
    init() {
      let date = new Date();
      this.createDate =
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
      this.$store
        .dispatch("Sales/getCurrentId", null) //从API中匹配相应的路径发送请求以及请求参数
        .then(() => {
          if (this.$store.state.Sales.currentIdInfo.code === 200) {
            //状态码为200 即请求成功
            this.addForm.id = this.$store.state.Sales.currentIdInfo.data;
          } else {
            this.$emit("onAdd");
            this.$emit("reInit");
            this.$message.error("获取编号失败导致无法执行添加操作！");
          }
          if (this.$store.state.Sales.currentIdInfo.code === 403) {
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
      if (this.addForm.assignPerson !== "") {
        this.state = 1;
        this.setAssignDate();
      }
      this.createDate =
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
      this.$refs["addForm"].validate((valid) => {
        if (valid) {
          const params = {
            id: this.addForm.id,
            chanceSource: this.addForm.chanceSource,
            customerName: this.addForm.customerName,
            probability: this.addForm.probability,
            overview: this.addForm.overview,
            contractPerson: this.addForm.contractPerson,
            contractPhone: this.addForm.contractPhone,
            description: this.addForm.description,
            createPerson: localStorage.getItem("trueName"),
            createDate: this.createDate,
            assignPerson: this.addForm.assignPerson,
            assignDate: this.assignDate,
            state: this.state,
            devResult: 0,
            isValid: 0,
            updateDate: "",
          };
          this.$store
            .dispatch("Sales/add", params)
            .then(() => {
              if (this.$store.state.Sales.addInfo.data === true) {
                this.$emit("onAdd");
                this.$emit("reInit");
                this.$message({
                  message: "新增操作成功！",
                  type: "success",
                });
              } else {
                this.$message.error("新增操作失败！");
                this.$emit("onAdd");
                this.$emit("reInit");
                this.resetForm();
              }
              if (this.$store.state.Sales.addInfo.code === 403) {
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
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm() {
      this.addForm.chanceSource = "";
      this.addForm.customerName = "";
      this.addForm.probability = "";
      this.addForm.overview = "";
      this.addForm.contractPerson = "";
      this.addForm.contractPhone = "";
      this.addForm.description = "";
      this.addForm.assignPerson = "";
    },
  },
};
</script>

<style scoped>
.el-form-item {
  float: left;
}
</style>

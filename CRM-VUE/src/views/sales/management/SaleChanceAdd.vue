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
        changeSource: "",
        customerName: "",
        probability: "",
        overview: "",
        contractPerson: "",
        contractPhone: "",
        description: "",
        assignPerson: "",
      },
      state: "",
      devResult: "",
      isValid: 0,
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
    this.loadComments();
    console.log(this.outerVisible1); //我是字符串
  },
  methods: {//方法区规划 新增编号获取（当前记录最大编号+1）、新增请求、指派人信息不存在时为未分配并不设置分配时间
    // loadComments() {
    //   let chineseName = localStorage.getItem("trueName");
    //   this.addForm.createName = chineseName;
    // },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const params = {
            "id": this.addForm.id,
            "changeSource": this.addForm.changeSource,
            "customerName": this.addForm.customerName,
           " probability": this.addForm.probability,
            "overview": this.addForm.overview,
            "contractPerson": this.addForm.contractPerson,
            "contractPhone": this.addForm.contractPhone,
            "description": this.addForm.description,
            "createPerson": localStorage.getItem("trueName"),
            "createDate": '',
            "assignPerson": this.addForm.assignPerson,
            "assigneDate": '',
          };
          this.$store
            .dispatch("CustomerCenter/add", params)
            .then(() => {
              if (
                this.$store.state.CustomerCenter.addInfo.code === 200 &&
                this.$store.state.CustomerCenter.addInfo.data === true
              ) {
                this.$emit("onAdd");
                this.$message({
                  message: this.$store.state.CustomerCenter.addInfo.message,
                  type: "success",
                });

                this.$emit("getfindAll");
              } else {
                this.$message.error(
                  this.$store.state.CustomerCenter.addInfo.message
                );
              }
            })
            .catch(() => {});
          this.resetForm(formName);
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm() {
      this.$refs["addForm"].resetFields();
    },
  },
};
</script>

<style scoped>
.el-form-item {
  float: left;
}
</style>


<template>
  <div>
    <h5 style="color: red">*编号项不得修改</h5>
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
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="机会来源" prop="chanceSource">
            <el-input
              v-model="updateForm.chanceSource"
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
              v-model="updateForm.customerName"
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
              v-model="updateForm.probability"
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
              v-model="updateForm.overview"
              type="textarea"
              placeholder="请输入概述"
              :maxlength="125"
              :autosize="{ minRows: 4, maxRows: 4 }"
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="联系人" prop="contactPerson">
            <el-input
              v-model="updateForm.contactPerson"
              placeholder="请输入联系人"
              clearable
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="联系电话" prop="contactPhone">
            <el-input
              v-model="updateForm.contactPhone"
              placeholder="请输入联系电话"
              clearable
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="机会描述" prop="description">
            <el-input
              v-model="updateForm.description"
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
              v-model="updateForm.assignPerson"
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
  props: ["multiple"],

  data() {
    return {
      outerVisible: false,
      innerVisible: false,
      updateForm: {
        id: "",
        chanceSource: "",
        customerName: "",
        probability: "",
        overview: "",
        contactPerson: "",
        contactPhone: "",
        description: "",
        assignPerson: "",
      },
      assigneDate: "",
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
        contactPerson: [
          {
            required: true,
            message: "请输入联系人",
            trigger: "blur",
          },
        ],
        contactPhone: [
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
    this.init();
  },
  watch: {
    multiple: function () {
      if (this.$props.multiple.length !== 0) {
        this.init();
      }
    },
  },
  methods: {
    setUpdateDate() {
      let date = new Date();
      this.updateDate =
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
    },
    setAssignDate() {
      let date = new Date();
      this.assignDate =
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
    },
    init() {
      (this.updateForm.id = this.$props.multiple[0].id),
        (this.updateForm.chanceSource = this.$props.multiple[0].chanceSource),
        (this.updateForm.customerName = this.$props.multiple[0].customerName),
        (this.updateForm.probability = this.$props.multiple[0].probability),
        (this.updateForm.overview = this.$props.multiple[0].overview),
        (this.updateForm.contactPerson =
          this.$props.multiple[0].contactPerson),
        (this.updateForm.contactPhone = this.$props.multiple[0].contactPhone),
        (this.updateForm.description = this.$props.multiple[0].description),
        (this.updateForm.assignPerson = this.$props.multiple[0].assignPerson);
    },
    submitForm() {
      this.setUpdateDate();
      if (this.updateForm.assignPerson !== "") {
        this.state = 1;
        this.setAssignDate();
      }
      this.$refs["updateForm"].validate((valid) => {
        if (valid) {
          const params = {
            id: this.updateForm.id,
            chanceSource: this.updateForm.chanceSource,
            customerName: this.updateForm.customerName,
            probability: this.updateForm.probability,
            overview: this.updateForm.overview,
            contactPerson: this.updateForm.contactPerson,
            contactPhone: this.updateForm.contactPhone,
            description: this.updateForm.description,
            createPerson: this.$props.multiple[0].createPerson,
            createDate: this.$props.multiple[0].createDate,
            assignPerson: this.updateForm.assignPerson,
            assignDate: this.assignDate,
            state: this.state,
            devResult: 0,
            isValid: 0,
            updateDate: this.updateDate,
          };
          this.$store
            .dispatch("Sales/update", params)
            .then(() => {
              console.log(this.$store.state.Sales.updateInfo);
              if (this.$store.state.Sales.updateInfo.data === true) {
                this.$emit("onAdd");
                this.$emit("reInit");
                this.$message({
                  message: "修改操作成功！",
                  type: "success",
                });
              } else {
                this.$message.error("修改操作失败！");
                this.$emit("onAdd");
                this.$emit("reInit");
                this.resetForm();
              }
              if (this.$store.state.Sales.updateInfo.code === 403) {
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
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm() {
      this.updateForm.chanceSource = this.$props.multiple[0].chanceSource;
      this.updateForm.customerName = this.$props.multiple[0].customerName;
      this.updateForm.probability = this.$props.multiple[0].probability;
      this.updateForm.overview = this.$props.multiple[0].overview;
      this.updateForm.contactPerson = this.$props.multiple[0].contactPerson;
      this.updateForm.contactPhone = this.$props.multiple[0].contactPhone;
      this.updateForm.description = this.$props.multiple[0].description;
      this.updateForm.assignPerson = this.$props.multiple[0].assignPerson;
    },
  },
};
</script>

<style scoped>
.el-form-item {
  float: left;
}
</style>
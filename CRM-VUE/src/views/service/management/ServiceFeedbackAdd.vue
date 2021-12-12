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
          <el-form-item label="服务序号" prop="id">
            <el-input
              v-model="addForm.id"
              placeholder="请输入服务序号"
              readonly
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="服务类型" prop="serviceType">
            <el-select
              v-model="addForm.serviceType"
              placeholder="请选择服务类型"
              :disabled="true"
              :style="{ width: '100%' }"
            >
              <el-option
                v-for="(item, index) in serviceTypeOptions"
                :key="index"
                :label="item.label"
                :value="item.value"
                :disabled="item.disabled"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="客户" prop="customer">
            <el-input
              v-model="addForm.customer"
              placeholder="请输入客户"
              readonly
              clearable
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="服务内容" prop="serviceRequest">
            <el-input
              v-model="addForm.serviceRequest"
              type="textarea"
              placeholder="请输入服务内容"
              readonly
              :autosize="{ minRows: 4, maxRows: 4 }"
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="服务概要" prop="overview">
            <el-input
              v-model="addForm.overview"
              type="textarea"
              placeholder="请输入服务概要"
              readonly
              :autosize="{ minRows: 4, maxRows: 4 }"
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分配人" prop="assignPerson">
            <el-input
              v-model="addForm.assignPerson"
              placeholder="请输入分配人"
              :disabled="true"
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分配时间" prop="assignDate">
            <el-input
              v-model="addForm.assignDate"
              placeholder="请输入分配时间"
              :disabled="true"
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="服务处理" prop="serviceProcess">
            <el-input
              v-model="addForm.serviceProcess"
              type="textarea"
              placeholder="请输入服务处理"
              readonly
              :autosize="{ minRows: 4, maxRows: 4 }"
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="处理人" prop="processPerson">
            <el-input
              v-model="addForm.processPerson"
              placeholder="请输入处理人"
              :disabled="true"
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="处理时间" prop="processDate">
            <el-input
              v-model="addForm.processDate"
              placeholder="请输入处理时间"
              :disabled="true"
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="处理结果" prop="processResult">
            <el-input
              v-model="addForm.processResult"
              type="textarea"
              placeholder="请输入处理结果"
              :autosize="{ minRows: 4, maxRows: 4 }"
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="客户满意度" prop="csr">
            <el-rate v-model="addForm.csr" :texts="this.texts" show-text></el-rate>
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
        texts:["不满意","较不满意","一般","较满意","非常满意"],
      addForm: {
        id: this.$props.multiple[0].id,
        serviceType: this.$props.multiple[0].serviceType,
        customer: this.$props.multiple[0].customer,
        serviceRequest: this.$props.multiple[0].serviceRequest,
        overview: this.$props.multiple[0].overview,
        assignPerson: this.$props.multiple[0].assignPerson,
        assignDate: this.$props.multiple[0].assignDate,
        serviceProcess: this.$props.multiple[0].serviceProcess,
        processPerson: this.$props.multiple[0].processPerson,
        processDate: this.$props.multiple[0].processDate,
        processResult: undefined,
        csr: 0,
      },
      rules: {
        id: [
          {
            required: true,
            message: "请输入服务序号",
            trigger: "blur",
          },
        ],
        serviceType: [
          {
            required: true,
            message: "请选择服务类型",
            trigger: "change",
          },
        ],
        customer: [
          {
            required: true,
            message: "请输入客户",
            trigger: "blur",
          },
        ],
        serviceRequest: [
          {
            required: true,
            message: "请输入服务内容",
            trigger: "blur",
          },
        ],
        overview: [
          {
            required: true,
            message: "请输入服务概要",
            trigger: "blur",
          },
        ],
        assignPerson: [
          {
            required: true,
            message: "请输入分配人",
            trigger: "blur",
          },
        ],
        assignDate: [
          {
            required: true,
            message: "请输入分配时间",
            trigger: "blur",
          },
        ],
        serviceProcess: [
          {
            required: true,
            message: "请输入服务处理",
            trigger: "blur",
          },
        ],
        processPerson: [
          {
            required: true,
            message: "请输入处理人",
            trigger: "blur",
          },
        ],
        processDate: [
          {
            required: true,
            message: "请输入处理时间",
            trigger: "blur",
          },
        ],
        processResult: [
          {
            required: true,
            message: "请输入处理结果",
            trigger: "blur",
          },
        ],
        csr: [
          {
            required: true,
            message: "客户满意度不能为空",
            trigger: "change",
          },
        ],
      },
      serviceTypeOptions: [
        {
          label: "咨询",
          value: "咨询",
        },
        {
          label: "建议",
          value: "建议",
        },
        {
          label: "投诉",
          value: "投诉",
        },
      ],
    };
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {},
  methods: {
    submitForm() {
      let cusCsr = "";
      if (this.addForm.csr == 1) {
        cusCsr = "不满意";
      } else if (this.addForm.csr == 2) {
        cusCsr = "较不满意";
      } else if (this.addForm.csr == 3) {
        cusCsr = "一般";
      } else if (this.addForm.csr == 4) {
        cusCsr = "较满意";
      } else if (this.addForm.csr == 5) {
        cusCsr = "非常满意";
      } else {
        cusCsr = "未作评价";
      }
      this.$refs["addForm"].validate((valid) => {
        if (valid) {
          const params = {
            id:this.addForm.id,
            // customer: this.addForm.customer,
            // serviceRequest: this.addForm.serviceRequest,
            // overview: this.addForm.overview,
            assignPerson: this.addForm.assignPerson,
            serviceProcess: this.addForm.serviceProcess,
            processPerson: localStorage.getItem("trueName"),
            processResult: this.addForm.processResult,
            csr: cusCsr,
            state:3
          };
          this.$store
            .dispatch("Service/updatePart", params)
            .then(() => {
              if (this.$store.state.Service.updatePartInfo.data === true) {
                this.$emit("onAdd");
                this.$emit("reInit");
                this.$message({
                  message: "服务分配操作成功！",
                  type: "success",
                });
              } else {
                this.$message.error("服务分配操作失败！");
                this.resetForm();
              }
              if (this.$store.state.Service.updatePartInfo.code === 403) {
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
      this.$refs["addForm"].resetFields();
    },
  },
};
</script>
<style>
.el-rate {
  display: inline-block;
  vertical-align: text-top;
}
</style>

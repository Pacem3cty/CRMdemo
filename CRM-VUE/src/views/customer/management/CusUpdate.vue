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
          <el-form-item label="客户编号" prop="cusNum">
            <el-input
              v-model="updateForm.cusNum"
              placeholder="请输入客户编号"
              readonly
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="客户名称" prop="cusName">
            <el-input
              v-model="updateForm.cusName"
              placeholder="请输入客户名称"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="地区" prop="area">
            <el-input
              v-model="updateForm.area"
              placeholder="请输入地区"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="法人代表" prop="artificialPerson">
            <el-input
              v-model="updateForm.artificialPerson"
              placeholder="请输入法人代表"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="客户经理" prop="cusManager">
            <el-input
              v-model="updateForm.cusManager"
              placeholder="请输入客户经理"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="满意度" prop="csr">
            <el-input
              v-model="updateForm.csr"
              placeholder="请输入满意度"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="客户级别" prop="level">
            <el-input
              v-model="updateForm.level"
              placeholder="请输入客户级别"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="信用度" prop="cct">
            <el-input
              v-model="updateForm.cct"
              placeholder="请输入信用度"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="邮政编码" prop="postalCode">
            <el-input
              v-model="updateForm.postalCode"
              placeholder="请输入邮政编码"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="详细地址" prop="cusAddress">
            <el-input
              v-model="updateForm.cusAddress"
              placeholder="请输入详细地址"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="客户电话" prop="cusPhone">
            <el-input
              v-model="updateForm.cusPhone"
              placeholder="请输入客户电话"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="传真" prop="cusFax">
            <el-input
              v-model="updateForm.cusFax"
              placeholder="请输入传真"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="网址" prop="webSite">
            <el-input
              v-model="updateForm.webSite"
              placeholder="请输入网址"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="信用代码" prop="socialCreditCode">
            <el-input
              v-model="updateForm.socialCreditCode"
              placeholder="请输入统一社会信用代码"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="注册资金" prop="registeredCapital">
            <el-input
              v-model="updateForm.registeredCapital"
              placeholder="请输入注册资金"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="开户银行" prop="annualTurnover">
            <el-input
              v-model="updateForm.annualTurnover"
              placeholder="请输入开户银行"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="银行账号" prop="accountBank">
            <el-input
              v-model="updateForm.accountBank"
              placeholder="请输入银行账号"
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
        id: this.$props.multiple[0].id,
        cusNum: this.$props.multiple[0].cusNum,
        cusName: this.$props.multiple[0].cusName,
        artificialPerson: this.$props.multiple[0].artificialPerson,
        area: this.$props.multiple[0].area,
        cusManager: this.$props.multiple[0].cusManager,
        csr: this.$props.multiple[0].csr,
        level: this.$props.multiple[0].level,
        cct: this.$props.multiple[0].cct,
        cusAddress: this.$props.multiple[0].cusAddress,
        postalCode: this.$props.multiple[0].postalCode,
        cusPhone: this.$props.multiple[0].cusPhone,
        cusFax: this.$props.multiple[0].cusFax,
        webSite: this.$props.multiple[0].webSite,
        socialCreditCode: this.$props.multiple[0].socialCreditCode,
        registeredCapital: this.$props.multiple[0].registeredCapital,
        annualTurnover: this.$props.multiple[0].annualTurnover,
        accountBank: this.$props.multiple[0].accountBank,
        accountNum: this.$props.multiple[0].accountNum,
      },
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
        cusNum: [
          {
            required: true,
            message: "请输入客户编号",
            trigger: "blur",
          },
        ],
        cusName: [
          {
            required: true,
            message: "请输入客户名称",
            trigger: "blur",
          },
        ],
        artificialPerson: [
          {
            required: true,
            message: "请输入法人代表",
            trigger: "blur",
          },
          {
            pattern: /[^\d^%&',;./=?~\x22$\x22]+/,
            message: "请输入正确的姓名格式",
            trigger: "blur",
          },
        ],
        area: [],
        cusManager: [
          {
            required: true,
            message: "请输入客户经理",
            trigger: "blur",
          },
          {
            pattern: /[^\d^%&',;./=?~\x22$\x22]+/,
            message: "请输入正确的姓名格式",
            trigger: "blur",
          },
        ],
        csr: [],
        level: [],
        cct: [],
        cusAddress: [],
        postalCode: [
          {
            pattern: /[1-9]\d{5}(?!\d)/,
            message: "请输入正确的邮政编码格式！",
            trigger: "blur",
          },
        ],
        cusPhone: [
          {
            required: true,
            message: "请输入客户电话",
            trigger: "blur",
          },
          {
            pattern: /^1[0-9]{10}$/,
            message: "请输入正确的手机号码格式！",
            trigger: "blur",
          },
        ],
        cusFax: [
          {
            pattern: /^(?:\d{3,4}-)?\d{7,8}(?:-\d{1,6})?$/,
            message: "请输入正确的传真号码格式！",
            trigger: "blur",
          },
        ],
        webSite: [
          {
            pattern:
              /[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\.?/,
            message: "请输入正确的URL格式！",
            trigger: "blur",
          },
        ],
        socialCreditCode: [
          {
            required: true,
            message: "请输入统一社会信用代码",
            trigger: "blur",
          },
          {
            pattern:
              /^([0-9A-HJ-NPQRTUWXY]{2}\d{6}[0-9A-HJ-NPQRTUWXY]{10}|[1-9]\d{14})$/,
            message: "请输入正确的统一社会信用代码格式！",
            trigger: "blur",
          },
        ],
        registeredCapital: [],
        annualTurnover: [],
        accountBank: [],
        accountNum: [],
      },
    };
  },
  created() {
    this.init();
  },
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
            cusNum: this.updateForm.cusNum,
            cusName: this.updateForm.cusName,
            artificialPerson: this.updateForm.artificialPerson,
            area: this.updateForm.area,
            cusManager: this.updateForm.cusManager,
            csr: this.updateForm.csr,
            level: this.updateForm.level,
            cct: this.updateForm.cct,
            cusAddress: this.updateForm.cusAddress,
            postalCode: this.updateForm.postalCode,
            cusPhone: this.updateForm.cusPhone,
            cusFax: this.updateForm.cusFax,
            webSite: this.updateForm.webSite,
            socialCreditCode: this.updateForm.socialCreditCode,
            registeredCapital: this.updateForm.registeredCapital,
            annualTurnover: this.updateForm.annualTurnover,
            accountBank: this.updateForm.accountBank,
            accountNum: this.updateForm.accountNum,
            isValid: 0,
            lossStatus:this.$props.multiple[0].lossStatus,
            createDate: this.$props.multiple[0].createDate,
            updateDate: this.updateDate,
          };
          this.$store
            .dispatch("Cus/update", params)
            .then(() => {
              if (this.$store.state.Cus.updateInfo.data === true) {
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
              if (this.$store.state.Cus.updateInfo.code === 403) {
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
      this.updateForm.cusName = this.$props.multiple[0].id;
      this.updateForm.artificialPerson = this.$props.multiple[0].artificialPerson;
      this.updateForm.area = this.$props.multiple[0].area;
      this.updateForm.cusManager = this.$props.multiple[0].cusManager;
      this.updateForm.csr = this.$props.multiple[0].csr;
      this.updateForm.level = this.$props.multiple[0].level;
      this.updateForm.cct = this.$props.multiple[0].cct;
      this.updateForm.cusAddress = this.$props.multiple[0].cusAddress;
      this.updateForm.postalCode = this.$props.multiple[0].postalCode;
      this.updateForm.cusPhone = this.$props.multiple[0].cusPhone;
      this.updateForm.cusFax = this.$props.multiple[0].cusFax;
      this.updateForm.webSite = this.$props.multiple[0].webSite;
      this.updateForm.socialCreditCode = this.$props.multiple[0].socialCreditCode;
      this.updateForm.registeredCapital = this.$props.multiple[0].registeredCapital;
      this.updateForm.annualTurnover = this.$props.multiple[0].id;
      this.updateForm.accountBank = this.$props.multiple[0].accountBank;
      this.updateForm.accountNum = this.$props.multiple[0].accountNum;
    },
  },
};
</script>

<style scoped>
.el-form-item {
  float: left;
}
</style>

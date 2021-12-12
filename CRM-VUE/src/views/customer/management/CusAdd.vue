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
          <el-form-item label="客户编号" prop="cusNum">
            <el-input
              v-model="addForm.cusNum"
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
              v-model="addForm.cusName"
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
              v-model="addForm.area"
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
              v-model="addForm.artificialPerson"
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
              v-model="addForm.cusManager"
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
              v-model="addForm.csr"
              placeholder="请输入满意度"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="客户级别" prop="level">
            <el-select
              v-model="addForm.level"
              placeholder="请选择客户等级"
              clearable
              :style="{ width: '100%' }"
              @change="$forceUpdate()"
            >
              <el-option
                v-for="(item, index) in levelOptions"
                :key="index"
                :label="item.label"
                :value="item.value"
                :disabled="item.disabled"
              ></el-option>
            </el-select>
            <!-- <el-input
              v-model="addForm.level"
              placeholder="请输入客户级别"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input> -->
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="信用度" prop="cct">
            <el-input
              v-model="addForm.cct"
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
              v-model="addForm.postalCode"
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
              v-model="addForm.cusAddress"
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
              v-model="addForm.cusPhone"
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
              v-model="addForm.cusFax"
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
              v-model="addForm.webSite"
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
              v-model="addForm.socialCreditCode"
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
              v-model="addForm.registeredCapital"
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
              v-model="addForm.annualTurnover"
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
              v-model="addForm.accountBank"
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
  data() {
    return {
      levelOptions: [
        {
          label: "A级",
          value: "A级",
        },
        {
          label: "B级",
          value: "B级",
        },
        {
          label: "C级",
          value: "C级",
        },
        {
          label: "D级",
          value: "D级",
        },
      ],
      createDate: "",
      addForm: {
        id: "",
        cusNum: "",
        cusName: "",
        artificialPerson: "",
        area: "",
        cusManager: "",
        csr: "",
        level: "",
        cct: "",
        cusAddress: "",
        postalCode: "",
        cusPhone: "",
        cusFax: "",
        webSite: "",
        socialCreditCode: "",
        registeredCapital: "",
        annualTurnover: "",
        accountBank: "",
        accountNum: "",
      },
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
    init() {
      let date = new Date();
      this.createDate =
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
      this.$store
        .dispatch("Cus/getCurrentId", null) //从API中匹配相应的路径发送请求以及请求参数
        .then(() => {
          if (this.$store.state.Cus.currentIdInfo.code === 200) {
            //状态码为200 即请求成功
            this.addForm.id = this.$store.state.Cus.currentIdInfo.data;
          } else {
            this.$emit("onAdd");
            this.$emit("reInit");
            this.$message.error("获取编号失败导致无法执行添加操作！");
          }
          if (this.$store.state.Cus.currentIdInfo.code === 403) {
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
      this.$store
        .dispatch("Cus/getCusNum", null) //从API中匹配相应的路径发送请求以及请求参数
        .then(() => {
          if (this.$store.state.Cus.cusNumInfo.code === 200) {
            //状态码为200 即请求成功
            this.addForm.cusNum = this.$store.state.Cus.cusNumInfo.data;
          } else {
            this.$emit("onAdd");
            this.$emit("reInit");
            this.$message.error("获取角色编号失败导致无法执行添加操作！");
          }
          if (this.$store.state.Cus.cusNumInfo.code === 403) {
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
      this.createDate =
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
      this.$refs["addForm"].validate((valid) => {
        if (valid) {
          const params = {
            id: this.addForm.id,
            cusNum: this.addForm.cusNum,
            cusName: this.addForm.cusName,
            artificialPerson: this.addForm.artificialPerson,
            area: this.addForm.area,
            cusManager: this.addForm.cusManager,
            csr: this.addForm.csr,
            level: this.addForm.level,
            cct: this.addForm.cct,
            cusAddress: this.addForm.cusAddress,
            postalCode: this.addForm.postalCode,
            cusPhone: this.addForm.cusPhone,
            cusFax: this.addForm.cusFax,
            webSite: this.addForm.webSite,
            socialCreditCode: this.addForm.socialCreditCode,
            registeredCapital: this.addForm.registeredCapital,
            annualTurnover: this.addForm.annualTurnover,
            accountBank: this.addForm.accountBank,
            accountNum: this.addForm.accountNum,
            isValid: 0,
            lossStatus:0,
            createDate: this.createDate,
            updateDate: "",
          };
          this.$store
            .dispatch("Cus/add", params)
            .then(() => {
              if (this.$store.state.Cus.addInfo.data === true) {
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
              if (this.$store.state.Cus.addInfo.code === 403) {
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
      this.addForm.cusName = "";
      this.addForm.artificialPerson = "";
      this.addForm.area = "";
      this.addForm.cusManager = "";
      this.addForm.csr = "";
      this.addForm.level = "";
      this.addForm.cct = "";
      this.addForm.cusAddress = "";
      this.addForm.postalCode = "";
      this.addForm.cusPhone = "";
      this.addForm.cusFax = "";
      this.addForm.webSite = "";
      this.addForm.socialCreditCode = "";
      this.addForm.registeredCapital = "";
      this.addForm.annualTurnover = "";
      this.addForm.accountBank = "";
      this.addForm.accountNum = "";
    },
  },
};
</script>

<style scoped>
.el-form-item {
  float: left;
}
</style>

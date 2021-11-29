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
        <!-- <el-col :span="12">
          <el-form-item label="客户编号" prop="cusId">
            <el-input
              v-model="addForm.cusId"
              placeholder="请输入客户编号"
              readonly
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col> -->
        <el-col :span="12">
          <el-form-item label="姓名" prop="linkmanName">
            <el-input
              v-model="addForm.linkmanName"
              placeholder="请输入姓名"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="性别" prop="sex">
            <el-select
              v-model="addForm.sex"
              placeholder="请选择性别"
              clearable
              :style="{ width: '100%' }"
            >
              <el-option
                v-for="(item, index) in sexOptions"
                :key="index"
                :label="item.label"
                :value="item.value"
                :disabled="item.disabled"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="职位" prop="position">
            <el-input
              v-model="addForm.position"
              placeholder="请输入职位"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="办公号码" prop="officePhone">
            <el-input
              v-model="addForm.officePhone"
              placeholder="请输入办公号码"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="手机号码" prop="phone">
            <el-input
              v-model="addForm.phone"
              placeholder="请输入手机号码"
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
  props: ["id"],
  data() {
    return {
      addForm: {
        id: undefined,
        // cusId:this.$props.id,
        linkmanName: undefined,
        sex: undefined,
        position: undefined,
        officePhone: undefined,
        phone: undefined,
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
        linkmanName: [
          {
            required: true,
            message: "请输入姓名",
            trigger: "blur",
          },
           {
            pattern: /[^\d^%&',;./=?~\x22$\x22]+/,
            message: "请输入正确的姓名格式",
            trigger: "blur",
          },
        ],
        sex: [
          {
            required: true,
            message: "请选择性别",
            trigger: "change",
          },
        ],
        position: [
          {
            required: true,
            message: "请输入职位",
            trigger: "blur",
          },
        ],
        officePhone: [
          {
            required: true,
            message: "请输入办公号码",
            trigger: "blur",
          },
           {
            pattern: /^1[0-9]{10}$/,
            message: "请输入正确的手机号码格式！",
            trigger: "blur",
          },
        ],
        phone: [
           {
            pattern: /^1[0-9]{10}$/,
            message: "请输入正确的手机号码格式！",
            trigger: "blur",
          },
        ],
      },
      sexOptions: [
        {
          label: "男",
          value: 0,
        },
        {
          label: "女",
          value: 1,
        },
      ],
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
      this.$store
        .dispatch("CusLinkman/getCurrentId", null) //从API中匹配相应的路径发送请求以及请求参数
        .then(() => {
          if (this.$store.state.CusLinkman.currentIdInfo.code === 200) {
            //状态码为200 即请求成功
            this.addForm.id = this.$store.state.CusLinkman.currentIdInfo.data;
          } else {
            this.$emit("onAdd");
            this.$emit("reInit");
            this.$message.error("获取编号失败导致无法执行添加操作！");
          }
          if (this.$store.state.CusLinkman.currentIdInfo.code === 403) {
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
          this.$emit("onAdd");
          this.$emit("queryAll");
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
            // cusId:this.addForm.cusId,
            cusId:this.$props.id,
            linkmanName: this.addForm.linkmanName,
            sex: this.addForm.sex,
            position: this.addForm.position,
            officePhone: this.addForm.officePhone,
            phone: this.addForm.phone,
            isValid: 0,
            createDate: this.createDate,
            updateDate: "",
          };
          this.$store
            .dispatch("CusLinkman/add", params)
            .then(() => {
              if (this.$store.state.CusLinkman.addInfo.data === true) {
                this.$emit("onAdd");
                this.$emit("queryAll");
                this.$message({
                  message: "新增操作成功！",
                  type: "success",
                });
              } else {
                this.$message.error("新增操作失败！");
                this.$emit("onAdd");
                this.$emit("queryAll");
                this.resetForm();
              }
              if (this.$store.state.CusLinkman.addInfo.code === 403) {
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
      this.addForm.linkmanName = "";
      this.addForm.sex = "";
      this.addForm.position = "";
      this.addForm.officePhone = "";
      this.addForm.phone = "";
    },
  },
};
</script>
<style>
</style>

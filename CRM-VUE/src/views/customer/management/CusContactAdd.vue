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
              placeholder="请选择编号"
              readonly
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <!-- <el-col :span="12">
          <el-form-item label="客户编号" prop="cusId">
            <el-input
              v-model="addForm.cusId"
              placeholder="请选择客户编号"
              readonly
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col> -->
        <el-col :span="12">
          <el-form-item label="交往时间" prop="contactTime">
            <el-date-picker
              v-model="addForm.contactTime"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              :style="{ width: '100%' }"
              placeholder="请选择交往时间"
              :picker-options="pickerOptions"
              clearable
            ></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="交往地址" prop="contactAddress">
            <el-input
              v-model="addForm.contactAddress"
              placeholder="请输入交往地址"
              clearable
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="交往概述" prop="overview">
            <el-input
              v-model="addForm.overview"
              placeholder="请输入交往概述"
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
        // cusId: this.$props.id,
        contactTime: null,
        contactAddress: undefined,
        overview: undefined,
      },
      createDate: "",
      rules: {
        id: [
          {
            required: true,
            message: "请选择编号",
            trigger: "blur",
          },
        ],
        cusId: [
          {
            required: true,
            message: "请选择客户编号",
            trigger: "blur",
          },
        ],
        contactTime: [
          {
            required: true,
            message: "请选择交往时间",
            trigger: "change",
          },
        ],
        contactAddress: [
          {
            required: true,
            message: "请输入交往地址",
            trigger: "blur",
          },
        ],
        overview: [
          {
            required: true,
            message: "请输入交往概述",
            trigger: "blur",
          },
        ],
      },
      dateRange: [],
      pickerMinDate: "",
      pickerOptions: {
        onPick: ({ maxDate, minDate }) => {
          this.pickerMinDate = minDate.getTime();
          if (maxDate) {
            this.pickerMinDate = "";
          }
        },
        // 限制不能选择今天之后的日期
        disabledDate: (time) => {
          if (this.pickerMinDate !== "") {
            let one = 31 * 24 * 3600 * 1000;
            let minTime = this.pickerMinDate - one;
            let maxTime = this.pickerMinDate + one;
            if (maxTime > new Date()) {
              maxTime = new Date();
            }
            return time.getTime() < minTime || time.getTime() > maxTime;
          }
          return time.getTime() > Date.now();
        },
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
      this.$store
        .dispatch("CusContact/getCurrentId", null) //从API中匹配相应的路径发送请求以及请求参数
        .then(() => {
          if (this.$store.state.CusContact.currentIdInfo.code === 200) {
            //状态码为200 即请求成功
            this.addForm.id = this.$store.state.CusContact.currentIdInfo.data;
          } else {
            this.$emit("onAdd");
            this.$emit("queryAll");
            this.$message.error("获取编号失败导致无法执行添加操作！");
          }
          if (this.$store.state.CusContact.currentIdInfo.code === 403) {
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
            // cusId: this.addForm.cusId,
            cusId: this.$props.id,
            contactTime: this.addForm.contactTime,
            contactAddress: this.addForm.contactAddress,
            overview: this.addForm.overview,
            isValid: 0,
            createDate: this.createDate,
            updateDate: "",
          };
          this.$store
            .dispatch("CusContact/add", params)
            .then(() => {
              if (this.$store.state.CusContact.addInfo.data === true) {
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
              if (this.$store.state.CusContact.addInfo.code === 403) {
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
      this.addForm.contactTime = "";
      this.addForm.contactAddress = "";
      this.addForm.overview = "";
    },
  },
};
</script>
<style>
</style>

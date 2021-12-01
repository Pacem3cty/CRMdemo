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
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="订单编号" prop="orderNum">
            <el-input
              v-model="addForm.orderNum"
              placeholder="请选择订单编号"
              readonly
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="下单时间" prop="orderDate">
            <el-date-picker
              v-model="addForm.orderDate"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              :style="{ width: '100%' }"
              placeholder="请输入下单时间"
              :picker-options="pickerOptions"
              clearable
            ></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="地址" prop="address">
            <el-input
              v-model="addForm.address"
              placeholder="请输入地址"
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
        orderNum: undefined,
        orderDate: null,
        address: undefined,
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
        orderNum: [
          {
            required: true,
            message: "请选择订单编号",
            trigger: "blur",
          },
        ],
        orderDate: [
          {
            required: true,
            message: "请输入下单时间",
            trigger: "change",
          },
        ],
        address: [
          {
            required: true,
            message: "请输入地址",
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
      let date = new Date();
      this.createDate =
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
      this.$store
        .dispatch("CusOrder/getCurrentId", null) //从API中匹配相应的路径发送请求以及请求参数
        .then(() => {
          if (this.$store.state.CusOrder.currentIdInfo.code === 200) {
            //状态码为200 即请求成功
            this.addForm.id = this.$store.state.CusOrder.currentIdInfo.data;
          } else {
            this.$emit("onAdd");
            this.$emit("queryAll");
            this.$message.error("获取编号失败导致无法执行添加操作！");
          }
          if (this.$store.state.CusOrder.currentIdInfo.code === 403) {
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
      this.$store
        .dispatch("CusOrder/getOrderNum", null) //从API中匹配相应的路径发送请求以及请求参数
        .then(() => {
          if (this.$store.state.CusOrder.orderNumInfo.code === 200) {
            //状态码为200 即请求成功
            this.addForm.orderNum =
              this.$store.state.CusOrder.orderNumInfo.data;
          } else {
            this.$emit("onAdd");
            this.$emit("queryAll");
            this.$message.error("获取角色编号失败导致无法执行添加操作！");
          }
          if (this.$store.state.CusOrder.orderNumInfo.code === 403) {
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
            cusId: this.$props.id,
            orderNum: this.addForm.orderNum,
            orderDate: this.addForm.orderDate,
            address: this.addForm.address,
            orderTotal: "",
            state: 0,
            isValid: 0,
            createDate: this.createDate,
            updateDate: "",
          };
          this.$store
            .dispatch("CusOrder/add", params)
            .then(() => {
              if (this.$store.state.CusOrder.addInfo.data === true) {
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
              if (this.$store.state.CusOrder.addInfo.code === 403) {
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
      this.addForm.orderDate = "";
      this.addForm.address = "";
    },
  },
};
</script>
<style>
</style>

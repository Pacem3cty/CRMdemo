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
        <el-col :span="12">
          <el-form-item label="商品名称" prop="goodsName">
            <el-input
              v-model="addForm.goodsName"
              placeholder="请选择商品名称"
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="单位" prop="unit">
            <el-input
              v-model="addForm.unit"
              placeholder="请输入单位"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="单价" prop="price">
            <el-input
              v-model="addForm.price"
              placeholder="请输入单价"
              clearable
              :style="{ width: '100%' }"
            >
              <template slot="prepend">¥</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="数量" prop="goodsNum">
            <el-input
              v-model="addForm.goodsNum"
              placeholder="请输入数量"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="总价" prop="total">
            <el-input
              v-model="addForm.total"
              placeholder="请输入总价"
              readonly
              :style="{ width: '100%' }"
            >
              <template slot="prepend">¥</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="备注" prop="remark">
            <el-input
              v-model="addForm.remark"
              placeholder="请输入备注"
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
        goodsName: undefined,
        unit: undefined,
        price: undefined,
        goodsNum: undefined,
        total: undefined,
        remark: undefined,
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
        goodsName: [
          {
            required: true,
            message: "请选择商品名称",
            trigger: "blur",
          },
        ],
        unit: [
          {
            required: true,
            message: "请输入单位",
            trigger: "blur",
          },
          {
            pattern: /^[\u4e00-\u9fa5]{0,}$/,
            message: "请输入中文",
            trigger: "blur",
          },
        ],
        price: [
          {
            required: true,
            message: "请输入单价",
            trigger: "blur",
          },
          {
            pattern: /^[0-9].*$/,
            message: "请输入数字",
            trigger: "blur",
          },
        ],
        goodsNum: [
          {
            required: true,
            message: "请输入数量",
            trigger: "blur",
          },
          {
            pattern: /^[0-9].*$/,
            message: "请输入数字",
            trigger: "blur",
          },
        ],
        total: [
          {
            required: true,
            message: "请输入总价",
            trigger: "blur",
          },
          {
            pattern: /^[0-9].*$/,
            message: "请输入数字",
            trigger: "blur",
          },
        ],
        remark: [],
      },
    };
  },
  computed: {},
  watch: {
    "addForm.goodsNum": function () {
      this.calTotal();
    },
    "addForm.price": function () {
      this.calTotal();
    },
  },
  created() {
    this.init();
  },
  mounted() {},
  methods: {
    calTotal() {
      this.addForm.total = this.addForm.price * this.addForm.goodsNum;
    },
    init() {
      let date = new Date();
      this.createDate =
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
      this.$store
        .dispatch("OrderDetails/getCurrentId", null) //从API中匹配相应的路径发送请求以及请求参数
        .then(() => {
          if (this.$store.state.OrderDetails.currentIdInfo.code === 200) {
            //状态码为200 即请求成功
            this.addForm.id = this.$store.state.OrderDetails.currentIdInfo.data;
          } else {
            this.$emit("onAdd");
            this.$emit("reInit");
            this.$message.error("获取编号失败导致无法执行添加操作！");
          }
          if (this.$store.state.OrderDetails.currentIdInfo.code === 403) {
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
            orderId: this.$props.id,
            goodsName: this.addForm.goodsName,
            goodsNum: this.addForm.goodsNum,
            unit: this.addForm.unit,
            price: this.addForm.price,
            total: this.addForm.total,
            remark: this.addForm.remark,
            isValid: 0,
            createDate: this.createDate,
            updateDate: "",
          };
          this.$store
            .dispatch("OrderDetails/add", params)
            .then(() => {
              if (this.$store.state.OrderDetails.addInfo.data === true) {
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
              if (this.$store.state.OrderDetails.addInfo.code === 403) {
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
      this.$refs["addForm"].resetFields();
    },
  },
};
</script>
<style>
</style>

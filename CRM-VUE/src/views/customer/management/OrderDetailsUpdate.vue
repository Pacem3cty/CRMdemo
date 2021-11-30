<template>
  <div>
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
              placeholder="请选择编号"
              readonly
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="商品名称" prop="goodsName">
            <el-input
              v-model="updateForm.goodsName"
              placeholder="请选择商品名称"
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="单位" prop="unit">
            <el-input
              v-model="updateForm.unit"
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
              v-model="updateForm.price"
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
              v-model="updateForm.goodsNum"
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
              v-model="updateForm.total"
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
              v-model="updateForm.remark"
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
  props: ["multiple", "id"],
  data() {
    return {
      updateForm: {
        id: this.$props.multiple[0].id,
        goodsName: this.$props.multiple[0].goodsName,
        goodsNum: this.$props.multiple[0].goodsNum,
        unit: this.$props.multiple[0].unit,
        price: this.$props.multiple[0].price,
        total: this.$props.multiple[0].total,
        remark: this.$props.multiple[0].remark,
      },
      updateDate: "",
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
    "updateForm.goodsNum": function () {
      this.calTotal();
    },
    "update.price": function () {
      this.calTotal();
    },
  },
  created() {},
  mounted() {},
  methods: {
    calTotal() {
      this.updateForm.total = this.updateForm.price * this.updateForm.goodsNum;
    },
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
            orderId: this.$props.id,
            goodsName: this.updateForm.goodsName,
            goodsNum: this.updateForm.goodsNum,
            unit: this.updateForm.unit,
            price: this.updateForm.price,
            total: this.updateForm.total,
            remark: this.updateForm.remark,
            isValid: 0,
            createDate: this.$props.multiple[0].createDate,
            updateDate: this.updateDate,
          };
          this.$store
            .dispatch("OrderDetails/update", params)
            .then(() => {
              if (this.$store.state.OrderDetails.updateInfo.data === true) {
                this.$emit("onAdd");
                this.$emit("queryAll");
                this.$message({
                  message: "修改操作成功！",
                  type: "success",
                });
              } else {
                this.$message.error("修改操作失败！");
                this.$emit("onAdd");
                this.$emit("queryAll");
                this.resetForm();
              }
              if (this.$store.state.OrderDetails.updateInfo.code === 403) {
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
      this.updateForm.goodsName = this.$props.multiple[0].goodsName = "";
      this.updateForm.goodsNum = this.$props.multiple[0].goodsNum = "";
      this.updateForm.unit = this.$props.multiple[0].unit = "";
      this.updateForm.price = this.$props.multiple[0].price = "";
      this.updateForm.total = this.$props.multiple[0].total = "";
      this.updateForm.remark = this.$props.multiple[0].remark = "";
    },
  },
};
</script>
<style>
</style>

<template>
  <div>
    <el-row :gutter="15">
      <el-form ref="updateForm" :model="updateForm" :rules="rules" size="medium" label-width="100px">
        <el-col :span="12">
          <el-form-item label="编号" prop="id">
            <el-input v-model="updateForm.id" placeholder="请选择编号" readonly :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="订单编号" prop="orderNum">
            <el-input v-model="updateForm.orderNum" placeholder="请选择订单编号" readonly :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="下单时间" prop="orderDate">
            <el-date-picker v-model="updateForm.orderDate" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
              :style="{width: '100%'}" placeholder="请输入下单时间" clearable></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="地址" prop="address">
            <el-input v-model="updateForm.address" placeholder="请输入地址" clearable :style="{width: '100%'}">
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
  props: ["multiple","id"],
  data() {
    return {
      updateForm: {
        id: this.$props.multiple[0].id,
        cusId:this.$props.id,
        orderNum: this.$props.multiple[0].orderNum,
        orderDate: this.$props.multiple[0].orderDate,
        address: this.$props.multiple[0].address,
      },
      updateDate:"",
      rules: {
        id: [{
          required: true,
          message: '请选择编号',
          trigger: 'blur'
        }],
        orderNum: [{
          required: true,
          message: '请选择订单编号',
          trigger: 'blur'
        }],
        orderDate: [{
          required: true,
          message: '请输入下单时间',
          trigger: 'change'
        }],
        address: [{
          required: true,
          message: '请输入地址',
          trigger: 'blur'
        }],
      },
    }
  },
  computed: {},
  watch: {},
  created() {
  },
  mounted() {},
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
            // cusId:this.addForm.cusId,
            cusId: this.$props.id,
            orderNum: this.updateForm.orderNum,
            orderDate: this.updateForm.orderDate,
            address: this.updateForm.address,
            state: this.$props.multiple[0].state,
            isValid: 0,
            createDate: this.$props.multiple[0].createDate,
            updateDate: this.updateDate,
          };
          this.$store
            .dispatch("CusOrder/update", params)
            .then(() => {
              if (this.$store.state.CusOrder.updateInfo.data === true) {
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
              if (this.$store.state.CusOrder.updateInfo.code === 403) {
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
      this.updateForm.orderDate = this.$props.multiple[0].orderDate;
      this.updateForm.address = this.$props.multiple[0].address;
    },
  }
}

</script>
<style>
</style>

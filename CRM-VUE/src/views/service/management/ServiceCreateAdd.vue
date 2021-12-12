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
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="服务类型" prop="serviceType">
            <el-select
              v-model="addForm.serviceType"
              placeholder="请选择服务类型"
              clearable
              :style="{ width: '100%' }"
              @change="$forceUpdate()"
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
          <el-form-item label="客户名称" prop="customerName">
            <el-autocomplete
              popper-class="my-autocomplete"
              v-model="addForm.customerName"
              value-key="customerName"
              :fetch-suggestions="querySearchCustomerName"
              :trigger-on-focus="false"
              placeholder="请输入客户名称"
              :style="{ width: '100%' }"
              @select="handleSelect"
            >
              <template slot-scope="{ item }">
                <div class="name">{{ item.customerName }}</div>
                <span class="num">{{ item.cusNum }}</span>
              </template>
            </el-autocomplete>
            <!-- <el-input
              v-model="addForm.customer"
              placeholder="请输入客户"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input> -->
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="服务内容" prop="serviceRequest">
            <el-input
              v-model="addForm.serviceRequest"
              type="textarea"
              placeholder="请输入服务内容"
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
              :autosize="{ minRows: 4, maxRows: 4 }"
              :style="{ width: '100%' }"
            ></el-input>
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
  props: [],
  data() {
    return {
      restaurantsCustomerName: [],
      addForm: {
        id: undefined,
        serviceType: undefined,
        customerName: undefined,
        serviceRequest: undefined,
        overview: undefined,
      },
      createDate: "",
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
        customerName: [
          {
            required: true,
            message: "请输入客户名称",
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
  created() {
    this.init();
    this.listCustomerName();
  },
  mounted() {},
  methods: {
    querySearchCustomerName(queryString, cb) {
      var restaurantsCustomerName = this.restaurantsCustomerName;
      var results = queryString
        ? restaurantsCustomerName.filter(
            this.createFilterCustomerName(queryString)
          )
        : restaurantsCustomerName;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilterCustomerName(queryString) {
      return (restaurantsCustomerName) => {
        return (
          restaurantsCustomerName.customerName
            .toLowerCase()
            .indexOf(queryString.toLowerCase()) === 0
        );
      };
    },
    handleSelect(item) {
      this.addForm.customerName = item.customerName;
    },
    listCustomerName() {
      this.restaurantsCustomerName = []; //清空数组 否则将导致后续记录添加导致重复
      this.$store
        .dispatch("Cus/listCusName", null)
        .then(() => {
          if (this.$store.state.Cus.cusNameInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.$store.state.Cus.cusNameInfo.data.forEach((e) => {
              const records = {
                //可根据需求更改
                customerName: e.cusName,
                cusNum: e.cusNum,
              };
              this.restaurantsCustomerName.push(records);
            });
          }
        })
        .catch((e) => {
          this.$message.error("发生错误：" + e);
        });
    },
    init() {
      this.$store
        .dispatch("Service/getCurrentId", null) //从API中匹配相应的路径发送请求以及请求参数
        .then(() => {
          if (this.$store.state.Service.currentIdInfo.code === 200) {
            //状态码为200 即请求成功
            this.addForm.id = this.$store.state.Service.currentIdInfo.data;
          } else {
            this.$emit("onAdd");
            this.$emit("reInit");
            this.$message.error("获取编号失败导致无法执行添加操作！");
          }
          if (this.$store.state.Service.currentIdInfo.code === 403) {
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
            serviceType: this.addForm.serviceType,
            customer: this.addForm.customerName,
            serviceRequest: this.addForm.serviceRequest,
            overview: this.addForm.overview,
            createPerson: localStorage.getItem("trueName"),
            state:0,
            isValid: 0,
            createDate: this.createDate,
            updateDate: "",
          };
          this.$store
            .dispatch("Service/add", params)
            .then(() => {
              if (this.$store.state.Service.addInfo.data === true) {
                this.$emit("onAdd");
                this.$emit("reInit");
                this.$message({
                  message: "服务创建操作成功！",
                  type: "success",
                });
              } else {
                this.$message.error("服务创建操作失败！");
                this.resetForm();
              }
              if (this.$store.state.Service.addInfo.code === 403) {
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
              this.$message.error("服务创建操作失败！发生错误：" + e);
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
      this.addForm.serviceType = "";
      this.addForm.customerName = "";
      this.addForm.serviceRequest = "";
      this.addForm.overview = "";
    },
  },
};
</script>
<style>
.name {
  text-overflow: ellipsis;
  overflow: hidden;
}
.num {
  font-size: 12px;
  color: #b4b4b4;
}
</style>

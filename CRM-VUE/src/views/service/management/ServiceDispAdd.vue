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
              disabled
              clearable
              :style="{ width: '100%' }"
            >
              <el-option
                v-for="(item, index) in serviceTypeOptions"
                :key="index"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="客户" prop="customer">
            <el-input
              v-model="addForm.customer"
              placeholder="请输入客户"
              clearable
              readonly
              :style="{ width: '100%' }"
            >
            </el-input>
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
        <el-col :span="24">
          <el-form-item label="分配人" prop="assignPerson">
            <el-autocomplete
              popper-class="my-autocomplete"
              v-model="addForm.assignPerson"
              value-key="trueName"
              :fetch-suggestions="querySearchTrueName"
              :trigger-on-focus="false"
              :clearable="false"
              placeholder="请输入分配人"
              @select="handleSelect"
            >
              <template slot-scope="{ item }">
                <div class="name">{{ item.trueName }}</div>
                <span class="num">{{ item.roleName }}</span>
              </template>
            </el-autocomplete>
            <!-- <el-input v-model="addForm.assignPerson" placeholder="请输入分配人" clearable :style="{width: '100%'}">
            </el-input> -->
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
      restaurantsTrueName: [],
      addForm: {
        id: this.$props.multiple[0].id,
        serviceType: this.$props.multiple[0].serviceType,
        customer: this.$props.multiple[0].customer,
        serviceRequest: this.$props.multiple[0].serviceRequest,
        overview: this.$props.multiple[0].overview,
        assignPerson: undefined,
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
    this.listUserTrueName();
  },
  mounted() {},
  methods: {
    listUserTrueName() {
      this.restaurantsTrueName = []; //清空数组 否则将导致后续记录添加导致重复
      this.$store
        .dispatch("User/queryRoleName", null)
        .then(() => {
          if (this.$store.state.User.userRoleNameInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.$store.state.User.userRoleNameInfo.data.forEach((e) => {
              const records = {
                //可根据需求更改
                trueName: e.trueName,
                roleName: e.roleName,
              };
              this.restaurantsTrueName.push(records);
            });
          }
        })
        .catch((e) => {
          this.$message.error("发生错误：" + e);
        });
    },
    querySearchTrueName(queryString, cb) {
      var restaurantsTrueName = this.restaurantsTrueName;
      var results = queryString
        ? restaurantsTrueName.filter(this.createFilterTrueName(queryString))
        : restaurantsTrueName;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilterTrueName(queryString) {
      return (restaurantsTrueName) => {
        return (
          restaurantsTrueName.trueName
            .toLowerCase()
            .indexOf(queryString.toLowerCase()) === 0
        );
      };
    },
    handleSelect(item) {
      this.addForm.assignPerson = item.trueName;
    },
    submitForm() {
      this.$refs["addForm"].validate((valid) => {
        if (valid) {
          const params = {
            id:this.addForm.id,
            // customer: this.addForm.customer,
            // serviceRequest: this.addForm.serviceRequest,
            // overview: this.addForm.overview,
            assignPerson: this.addForm.assignPerson,
            state:1
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
      // this.addForm.customer = this.$props.multiple[0].customer;
      // this.addForm.serviceRequest = this.$props.multiple[0].serviceRequest;
      // this.addForm.overview = this.$props.multiple[0].overview;
      this.addForm.assignPerson = " ";
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


<template>
  <div>
    <el-breadcrumb separator="/" class="main-nav">
      <el-breadcrumb-item>首页</el-breadcrumb-item>
      <el-breadcrumb-item>营销管理</el-breadcrumb-item>
      <el-breadcrumb-item>客户开发计划</el-breadcrumb-item>
      <div style="font-size: 25px; float: right; margin-right: 25px">
        <i
          class="el-icon-document-checked"
          style="margin-right: 6px; cursor: pointer"
          @click="onInfo"
          title="计划项详情"
        ></i>
        <i
          class="el-icon-edit-outline"
          style="margin-right: 6px; cursor: pointer"
          @click="ondetails"
          title="计划项数据维护"
        ></i>
        <i
          class="el-icon-delete"
          style="margin-right: 6px; cursor: pointer"
          @click="checkDialog"
          title="删除"
        ></i>
      </div>
    </el-breadcrumb>

    <div class="demo-input-size">
      <div class="i-div">
        <label class="i-label">客户名</label>
        <el-autocomplete
          popper-class="my-autocomplete"
          v-model="customerName"
          value-key="customerName"
          :fetch-suggestions="querySearchCustomerName"
          :trigger-on-focus="false"
          :clearable="false"
          placeholder="请输入客户名"
          @select="handleSelect"
        >
          <!-- <template slot-scope="{ item }">
            <div class="name">{{ item.customerName }}</div>
          </template> -->
        </el-autocomplete>
      </div>

      <div class="i-div">
        <label class="i-label">创建人</label>
        <!-- <el-input v-model="createPerson" placeholder="请输入创建人"></el-input> -->
        <el-autocomplete
          popper-class="my-autocomplete"
          v-model="createPerson"
          value-key="createPerson"
          :fetch-suggestions="querySearchCreatePerson"
          :trigger-on-focus="false"
          :clearable="false"
          placeholder="请输入创建人"
          @select="handleSelect"
        >
          <template slot-scope="{ item }">
            <div class="name">{{ item.createPerson }}</div>
          </template>
        </el-autocomplete>
      </div>

      <div class="i-div">
        <label class="i-label">开发结果</label>
        <el-select v-model="devResult" placeholder="请选择开发结果">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </div>
      <div class="i-div" style="margin-top: 10px; margin-right: 10px">
        <label class="i-label" style="margin-right: 10px">创建日期</label>
        <el-date-picker
          v-model="createDate"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          value-format="yyyy-MM-dd"
        >
        </el-date-picker>
      </div>
      <div class="i-div">
        <el-button type="primary" style="margin-top: 9px" @click="this.queryAll"
          >查询</el-button
        >
      </div>
    </div>
    <div class="table-div">
      <el-table
        v-loading="loading"
        element-loading-text="加载中"
        element-loading-spinner="el-icon-loading"
        element-loading-background="rgba(0, 0, 0, 0.8)"
        :data="tableData"
        height="700"
        border
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="30"> </el-table-column>
        <el-table-column
          v-for="item in tableColumns"
          :key="item.key"
          :label="item.name"
          :prop="item.key"
          :width="item.width"
        >
        </el-table-column>
        <el-table-column prop="devResult" label="开发状态" width="85">
          <template slot-scope="scope">
            <span v-if="scope.row.devResult === 0"
              ><el-tag>正在开发</el-tag></span
            >
            <span v-else-if="scope.row.devResult === 1"
              ><el-tag type="success">开发成功</el-tag></span
            >
            <span v-else-if="scope.row.devResult === 2"
              ><el-tag type="danger">开发失败</el-tag></span
            >
          </template>
        </el-table-column>
      </el-table>
      <div class="block">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage"
          :page-sizes="[5, 10, 20, 30]"
          :page-size="size"
          layout="sizes, prev, pager, next"
          :total="total"
        >
        </el-pagination>
      </div>
    </div>
    <!-- 删除提示 -->
    <el-dialog
      :close-on-click-modal="false"
      :visible.sync="dialogVisible"
      width="30%"
      ><i slot="title" class="el-icon-warning">警告</i>
      <span>确认删除该信息？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="onDel">确定</el-button>
      </span>
    </el-dialog>
    <!-- 新增 -->
    <el-dialog
      title="计划项数据详情"
      :visible.sync="outerVisible"
      :close-on-click-modal="false"
    >
      <CusDevPlanInfo
        @onAdd="onAdd"
        @reInit="reInit"
        :multiple="this.multipleSelection"
      ></CusDevPlanInfo>
    </el-dialog>
    <!-- 修改 -->
    <el-dialog
      title="计划项数据维护"
      :visible.sync="outerUpdateVisible"
      :close-on-click-modal="false"
    >
      <CusDevPlanMainten
        @onAdd="onAdd"
        @reInit="reInit"
        :multiple="this.multipleSelection"
      ></CusDevPlanMainten>
    </el-dialog>
  </div>
</template>

<script>
import CusDevPlanInfo from "../basicData/CusDevPlanInfo.vue";
import CusDevPlanMainten from "../management/CusDevPlanMainten.vue";

export default {
  components: {
    CusDevPlanInfo,
    CusDevPlanMainten,
  },
  data() {
    return {
      restaurantsCustomerName: [],
      restaurantsCreatePerson: [],
      options: [
        {
          value: null,
          label: "所有结果",
        },
        {
          value: 0,
          label: "正在开发",
        },
        {
          value: 1,
          label: "开发成功",
        },
        {
          value: 2,
          label: "开发失败",
        },
      ],
      value: "",
      tableData: [],
      tableColumns: [
        { key: "id", name: "编号", width: 50 },
        { key: "chanceSource", name: "机会来源", width: 80 },
        { key: "customerName", name: "客户名称", width: 100 },
        { key: "probability", name: "成功概率(%)", width: 55 },
        { key: "overview", name: "概述", width: 225 },
        { key: "contactPerson", name: "联系人", width: 80 },
        { key: "contactPhone", name: "联系电话", width: 150 },
        { key: "description", name: "机会描述", width: 225 },
        { key: "createPerson", name: "创建人", width: 80 },
        { key: "createDate", name: "创建日期", width: 100 },
        { key: "assignPerson", name: "指派人", width: 80 },
        { key: "assignDate", name: "分配日期", width: 100 },
        // { key: 'state', name: '分配状态', width: 250 },
        // { key: 'devResult', name: '开发状态', width: 250 }
      ],
      infoShow: false,
      devShow: false,
      loading: true,
      input: "",
      dialogVisible: false,
      outerVisible: false,
      innerVisible: false,
      outerUpdateVisible: false,
      id: "",
      changeSource: "",
      customerName: "",
      probability: "",
      overview: "",
      contactPerson: "",
      contactPhone: "",
      description: "",
      createPerson: "",
      createDate: [],
      assignPerson: "",
      assigneDate: [],
      state: "",
      devResult: "",
      currentPage: 1,
      size: 5,
      total: 0,
      multipleSelection: [],
    };
  },
  created() {
    this.reInit();
  },
  mounted() {},
  destroyed() {
    // 销毁用第三方登录
    // window.removeEventListener('storage', this.afterQRScan)
  },
  methods: {
    reInit() {
      this.queryAll();
      this.listCustomerName();
      this.listCreatePerson();
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.size = val;
      this.currentPage = 1;
      this.queryAll();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val;
      this.queryAll();
    },
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
    querySearchCreatePerson(queryString, cb) {
      var restaurantsCreatePerson = this.restaurantsCreatePerson;
      var results = queryString
        ? restaurantsCreatePerson.filter(
            this.createFilterCreatePerson(queryString)
          )
        : restaurantsCreatePerson;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilterCreatePerson(queryString) {
      return (restaurantsCreatePerson) => {
        return (
          restaurantsCreatePerson.createPerson
            .toLowerCase()
            .indexOf(queryString.toLowerCase()) === 0
        );
      };
    },
    handleSelect(item) {
      this.customerName = item.customerName;
      this.createPerson = item.createPerson;
    },
    toggleSelection(rows) {
      if (rows) {
        rows.forEach((row) => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    listCustomerName() {
      this.restaurantsCustomerName = []; //清空数组 否则将导致后续记录添加导致重复
      this.$store
        .dispatch("Sales/listCustomerName", null)
        .then(() => {
          if (this.$store.state.Sales.customerNameInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.$store.state.Sales.customerNameInfo.data.forEach((e) => {
              const records = {
                //可根据需求更改
                customerName: e.customerName,
              };
              this.restaurantsCustomerName.push(records);
            });
          }
        })
        .catch((e) => {
          this.$message.error("发生错误：" + e);
        });
    },
    listCreatePerson() {
      this.restaurantsCreatePerson = []; //清空数组 否则将导致后续记录添加导致重复
      this.$store
        .dispatch("Sales/listCreatePerson", null)
        .then(() => {
          if (this.$store.state.Sales.createPersonInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.$store.state.Sales.createPersonInfo.data.forEach((e) => {
              const records = {
                //可根据需求更改
                createPerson: e.createPerson,
              };
              this.restaurantsCreatePerson.push(records);
            });
          }
        })
        .catch((e) => {
          this.$message.error("发生错误：" + e);
        });
    },
    queryAll() {
      this.loading = true;
      const params = {
        current: this.currentPage,
        pageSize: this.size,
        customerName: this.customerName,
        createPerson: this.createPerson,
        devResult: this.devResult,
        startTime:
          this.createDate === null || this.createDate.length === 0
            ? null
            : this.createDate[0],
        endTime:
          this.createDate === null || this.createDate.length === 0
            ? null
            : this.createDate[1],
      };
      this.$store
        .dispatch("Sales/queryIsAssigned", params)
        .then(() => {
          if (this.$store.state.Sales.assignedInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.tableData = this.$store.state.Sales.assignedInfo.data.records;

            this.total = this.$store.state.Sales.assignedInfo.data.total;
            this.currentPage =
              this.$store.state.Sales.assignedInfo.data.current;
            this.size = this.$store.state.Sales.assignedInfo.data.size;
          }
          if (this.$store.state.Sales.assignedInfo.code === 403) {
            this.$message({
              message: "当前角色无相关权限",
              type: "warning",
            });
            return;
          }
        })
        .catch((e) => {
          this.loading = false;
          this.$message.error("发生错误：" + e);
        });
    },
    checkDialog() {
      if (
        this.multipleSelection === undefined ||
        this.multipleSelection.length === 0
      ) {
        this.$message({
          message: "请选择至少一条信息执行删除操作",
          type: "warning",
        });
        return;
      }
      this.dialogVisible = true;
    },
    onDel: function () {
      this.dialogVisible = false;
      let arrayId = "";
      this.multipleSelection.forEach((element) => {
        console.log(element);
        arrayId += element.id + ",";
      });
      const params = {
        ids: arrayId,
      };
      this.$store
        .dispatch("Sales/del", params)
        .then(() => {
          console.log(this.$store.state.Sales.delInfo);
          if (
            this.$store.state.Sales.delInfo.code === 200 &&
            this.$store.state.Sales.delInfo.data === true
          ) {
            this.$message({
              message: "删除操作成功！",
              type: "success",
            });
            this.reInit();
          } else {
            this.$message.error("执行删除操作失败！");
          }
          if (this.$store.state.Sales.delInfo.code === 403) {
            this.$message({
              message: "当前角色无相关权限",
              type: "warning",
            });
            this.reInit();
            return;
          }
        })
        .catch((e) => {
          this.$message.error("执行删除操作失败！发生错误：" + e);
        });
    },
    onInfo: function () {
      if (
        this.multipleSelection === undefined ||
        this.multipleSelection.length === 0
      ) {
        this.$message({
          message: "请选择一条信息查看详情",
          type: "warning",
        });
        return;
      }
      if (this.multipleSelection[0].devResult === 0) {
        this.$message({
          message: "正在开发的信息无法查看详情！",
          type: "warning",
        });
        return;
      }
      if (this.multipleSelection.length > 1) {
        this.$message({
          message: "最多只能选择一条信息查看详情",
          type: "warning",
        });
        return;
      }
      this.outerVisible = true;
    },
    onAdd: function () {
      this.outerVisible = false;
      this.outerUpdateVisible = false;
    },
    ondetails: function () {
      if (
        this.multipleSelection === undefined ||
        this.multipleSelection.length === 0
      ) {
        this.$message({
          message: "请选择一条信息执行开发操作",
          type: "warning",
        });
        return;
      }
      if (this.multipleSelection.length > 1) {
        this.$message({
          message: "最多只能选择一条信息执行开发操作",
          type: "warning",
        });
        return;
      }
      if (
        this.multipleSelection[0].devResult === 1 ||
        this.multipleSelection[0].devResult === 2
      ) {
        this.$message({
          message: "该信息已有开发结果！无法进行开发",
          type: "warning",
        });
        return;
      }
      this.outerUpdateVisible = true;
    },
  },
};
</script>

<style lang="scss">
$hc: #409eff;

.el-input {
  width: 150px;
  margin: 10px;
}
.demo-input-size {
  width: 100%;
  text-align: left;
  background-color: #ffffff;
  margin-top: 6px;
  flex: auto;
}
.table-div {
  background: #ffffff;
  min-height: 90vh;
}
.el-table {
  width: 99%;
  margin: 0px 0px 0px 10px;
}
.el-pagination {
  padding: 10px 10px;
}
.i-label {
  margin-left: 10px;
}
.i-div {
  float: left;
}
.el-icon-document-checked:hover {
  color: $hc;
}
.el-icon-delete:hover {
  color: $hc;
}
.el-dialog__body {
  overflow: hidden;
}
.el-dialog__header {
  background: #e2e0e0;
}
.el-form-item__label {
  line-height: 4em;
}
</style>



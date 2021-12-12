<template>
  <div>
    <el-breadcrumb separator="/" class="main-nav">
      <el-breadcrumb-item>首页</el-breadcrumb-item>
      <el-breadcrumb-item>服务管理</el-breadcrumb-item>
      <el-breadcrumb-item>服务创建</el-breadcrumb-item>
      <div style="font-size: 25px; float: right; margin-right: 25px">
        <!-- <i
          class="el-icon-document-add"
          style="margin-right: 6px; cursor: pointer"
          @click="outerVisible = true"
          title="新增"
        ></i>
        <i
          class="el-icon-edit"
          style="margin-right: 6px; cursor: pointer"
          @click="ondetails"
          title="修改"
        ></i>
        <i
          class="el-icon-delete"
          style="margin-right: 6px; cursor: pointer"
          @click="checkDialog"
          title="删除"
        ></i> -->
      </div>
    </el-breadcrumb>

    <div class="demo-input-size">
      <div class="i-div">
        <label class="i-label">客户名称</label>
        <el-autocomplete
          popper-class="my-autocomplete"
          v-model="customerName"
          value-key="customerName"
          :fetch-suggestions="querySearchCustomerName"
          :trigger-on-focus="false"
          :clearable="false"
          placeholder="请输入客户名称"
          @select="handleSelect"
        >
          <!-- <template slot-scope="{ item }">
            <div class="name">{{ item.customerName }}</div>
          </template> -->
        </el-autocomplete>
      </div>

      <div class="i-div">
        <label class="i-label">服务类型</label>
        <el-select v-model="serviceType" placeholder="请选择服务类型" @change="$forceUpdate()">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </div>
      <div class="i-div">
        <el-button type="primary" style="margin-top: 9px" @click="this.queryAll"
          >查询</el-button
        >
      </div>
       <div class="i-div-r">
        <!-- <el-tooltip
          class="item"
          effect="dark"
          content="请选择需新增子资源信息的一项"
          placement="left"
        > -->
          <el-button
            type="primary"
            style="margin-top: 9px"
             @click="outerVisible = true"
            >创建服务</el-button
          >
        <!-- </el-tooltip> -->
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
         <el-table-column prop="state" label="服务状态" width="85">
          <template slot-scope="scope">
            <span v-if="scope.row.state === 0"
              ><el-tag>分配</el-tag></span
            >
            <span v-else-if="scope.row.state === 1"
              ><el-tag>处理</el-tag></span
            >
            <span v-else-if="scope.row.state === 2"
              ><el-tag>反馈</el-tag></span
            >
            <span v-else-if="scope.row.state === 3"
              ><el-tag>归档</el-tag></span
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
    <!-- <el-dialog
      :close-on-click-modal="false"
      :visible.sync="dialogVisible"
      width="30%"
      ><i slot="title" class="el-icon-warning">警告</i>
      <span>确认删除该信息？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="onDel">确定</el-button>
      </span>
    </el-dialog> -->
    <!-- 新增 -->
    <el-dialog
      title="服务管理-服务创建"
      :visible.sync="outerVisible"
      v-if="outerVisible"
      :close-on-click-modal="false"
    >
      <ServiceCreateAdd @onAdd="onAdd" @reInit="reInit"></ServiceCreateAdd>
    </el-dialog>
    <!-- 修改 -->
    <!-- <el-dialog
      title="服务管理-服务创建"
      :visible.sync="outerUpdateVisible"
      :close-on-click-modal="false"
    >
      <SaleChanceUpdate
        @onAdd="onAdd"
        @reInit="reInit"
        :multiple="this.multipleSelection"
      ></SaleChanceUpdate>
    </el-dialog> -->
  </div>
</template>

<script>
import ServiceCreateAdd from "../management/ServiceCreateAdd.vue";

export default {
  components: {
    ServiceCreateAdd,
    // SaleChanceUpdate,
  },
  data() {
    return {
      restaurantsCustomerName: [],
      options: [
         {
          value: null,
          label: "所有服务类型",
        },
        {
          value: "咨询",
          label: "咨询",
        },
        {
          value: "建议",
          label: "建议",
        },
         {
          value: "投诉",
          label: "投诉",
        },
      ],
      value: "",
      tableData: [],
      tableColumns: [
        { key: "id", name: "编号", width: 50 },
        { key: "customer", name: "客户名称", width: 200 },
        { key: "serviceType", name: "服务类型", width: 80 },
        { key: "createPerson", name: "创建人", width: 80 },
        { key: "overview", name: "概要信息", width: 590 },
        { key: "createDate", name: "创建日期", width: 100 },
        { key: "updateDate", name: "修改日期", width: 100 },
      ],
      loading: true,
      input: "",
      serviceType:"",
      customerName:"",
      dialogVisible: false,
      outerVisible: false,
      innerVisible: false,
      outerUpdateVisible: false,
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
  destroyed() {},
  methods: {
    reInit() {
      this.queryAll();
      this.listCustomerName();
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
    handleSelect(item) {
      this.customerName = item.customerName;
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
        .dispatch("Service/listCustomerName", null)
        .then(() => {
          if (this.$store.state.Service.customerNameInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.$store.state.Service.customerNameInfo.data.forEach((e) => {
              const records = {
                //可根据需求更改
                customerName: e.customer,
              };
              this.restaurantsCustomerName.push(records);
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
        customer: this.customerName,
        serviceType: this.serviceType,
      };
      this.$store
        .dispatch("Service/queryAll", params)
        .then(() => {
          if (this.$store.state.Service.serviceInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.tableData = this.$store.state.Service.serviceInfo.data.records;

            this.total = this.$store.state.Service.serviceInfo.data.total;
            this.currentPage = this.$store.state.Service.serviceInfo.data.current;
            this.size = this.$store.state.Service.serviceInfo.data.size;
          }
          if (this.$store.state.Service.serviceInfo.code === 403) {
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
    // onDel: function () {
    //   this.dialogVisible = false;

    //   let arrayId = "";
    //   this.multipleSelection.forEach((element) => {
    //     console.log(element);
    //     arrayId += element.id + ",";
    //   });
    //   const params = {
    //     ids: arrayId,
    //   };
    //   this.$store
    //     .dispatch("Sales/del", params)
    //     .then(() => {
    //       // console.log(this.$store.state.Sales.delInfo);
    //       if (
    //         this.$store.state.Sales.delInfo.code === 200 &&
    //         this.$store.state.Sales.delInfo.data === true
    //       ) {
    //         this.$message({
    //           message: "删除操作成功！",
    //           type: "success",
    //         });
    //         this.reInit();
    //       } else {
    //         this.$message.error("执行删除操作失败！");
    //       }
    //       if (this.$store.state.Sales.delInfo.code === 403) {
    //         this.$message({
    //           message: "当前角色无相关权限",
    //           type: "warning",
    //         });
    //         return;
    //       }
    //     })
    //     .catch((e) => {
    //       this.$message.error("执行删除操作失败！发生错误：" + e);
    //     });
    // },
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
          message: "请选择一条信息执行修改操作",
          type: "warning",
        });
        return;
      }
      if (this.multipleSelection.length > 1) {
        this.$message({
          message: "最多只能选择一条信息执行修改操作",
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
.i-div-r {
  float: right;
  margin-right: 45px;
  margin-bottom: 10px;
}
</style>

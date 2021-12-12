<template>
  <div>
    <el-breadcrumb separator="/" class="main-nav">
      <el-breadcrumb-item>首页</el-breadcrumb-item>
      <el-breadcrumb-item>客户管理</el-breadcrumb-item>
      <el-breadcrumb-item>客户流失管理</el-breadcrumb-item>
      <div style="font-size: 25px; float: right; margin-right: 25px">
        <i
          class="el-icon-document-checked"
          style="margin-right: 6px; cursor: pointer"
          @click="onInfo"
          title="客户流失详情"
        ></i>
        <i
          class="el-icon-edit-outline"
          style="margin-right: 6px; cursor: pointer"
          @click="ondetails"
          title="暂缓措施维护"
        ></i>
        <!-- <i
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
          v-model="cusName"
          value-key="cusName"
          :fetch-suggestions="querySearchCusName"
          :trigger-on-focus="false"
          :clearable="false"
          placeholder="请输入客户名称"
          @select="handleSelect"
        >
        </el-autocomplete>
      </div>

      <div class="i-div">
        <label class="i-label">客户经理</label>
        <el-autocomplete
          popper-class="my-autocomplete"
          v-model="cusManager"
          value-key="cusManager"
          :fetch-suggestions="querySearchCusManager"
          :trigger-on-focus="false"
          :clearable="false"
          placeholder="请输入客户经理"
          @select="handleSelect"
        >
        </el-autocomplete>
      </div>

      <div class="i-div">
        <label class="i-label">流失状态</label>
        <el-select v-model="state" placeholder="请选择流失状态" @change="$forceUpdate()">
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
        <el-table-column prop="state" label="流失状态" width="85">
          <template slot-scope="scope">
            <span v-if="scope.row.state === 0"
              ><el-tag type="warning">暂缓流失</el-tag></span
            >
            <span v-else-if="scope.row.state === 1"
              ><el-tag type="danger">确认流失</el-tag></span
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
    <!-- 客户流失详情 -->
    <el-dialog
      title="客户管理-客户流失详情"
      :visible.sync="outerVisible"
      v-if="outerVisible"
      :close-on-click-modal="false"
    >
      <CusReprieveInfo @onAdd="onAdd" @reInit="reInit" :multiple="this.multipleSelection"></CusReprieveInfo>
    </el-dialog>
    <!-- 客户管理-暂缓措施维护 -->
    <el-dialog
      title="客户管理-暂缓措施维护"
      :visible.sync="outerUpdateVisible"
      v-if="outerUpdateVisible"
      :close-on-click-modal="false"
    >
      <CusReprieveMainten
        @onAdd="onAdd"
        @reInit="reInit"
        :multiple="this.multipleSelection"
      ></CusReprieveMainten>
    </el-dialog>
  </div>
</template>

<script>
import CusReprieveInfo from "../basicData/CusReprieveInfo.vue";
import CusReprieveMainten from "../management/CusReprieveMainten.vue";

export default {
  components: {
    CusReprieveInfo,
    CusReprieveMainten,
  },
  data() {
    return {
       options: [
         {
          value: null,
          label: "所有流失状态",
        },
        {
          value: 0,
          label: "暂缓流失",
        },
        {
          value: 1,
          label: "确认流失",
        }
      ],
      restaurantsCusName: [],
      restaurantsCusManager: [],
      value: "",
      tableData: [],
      tableColumns: [
        { key: "id", name: "序号", width: 50 },
        { key: "cusNum", name: "客户编号", width: 160 },
        { key: "cusName", name: "客户名称", width: 200 },
        { key: "cusManager", name: "客户经理", width: 80 },
        { key: "lastOrderTime", name: "最后下单时间", width: 120 },
        { key: "lossReason", name: "流失原因", width: 380 },
        { key: "createDate", name: "创建日期", width: 100 },
        { key: "updateDate", name: "修改日期", width: 100 },
      ],
      loading: false,
      input: "",
      dialogVisible: false,
      outerVisible: false,
      innerVisible: false,
      outerUpdateVisible: false,
      cusName:"",
      cusManager: "",
      createDate: "",
      updateDate: "",
      state:"",
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
    querySearchCusName(queryString, cb) {
      var restaurantsCusName = this.restaurantsCusName;
      var results = queryString
        ? restaurantsCusName.filter(this.createFilterCusName(queryString))
        : restaurantsCusName;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilterCusName(queryString) {
      return (restaurantsCusName) => {
        return (
          restaurantsCusName.cusName
            .toLowerCase()
            .indexOf(queryString.toLowerCase()) === 0
        );
      };
    },
    querySearchCusManager(queryString, cb) {
      var restaurantsCusManager = this.restaurantsCusManager;
      var results = queryString
        ? restaurantsCusManager.filter(this.createFilterCusManager(queryString))
        : restaurantsCusManager;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilterCusManager(queryString) {
      return (restaurantsCusManager) => {
        return (
          restaurantsCusManager.cusManager
            .toLowerCase()
            .indexOf(queryString.toLowerCase()) === 0
        );
      };
    },
    handleSelect(item) {
      this.cusName = item.cusName;
      this.cusManager = item.cusManager;
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
    listCusName() {
      this.restaurantsCusName = []; //清空数组 否则将导致后续记录添加导致重复
      this.$store
        .dispatch("CusLoss/listCusName", null)
        .then(() => {
          if (this.$store.state.CusLoss.cusNameInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.$store.state.CusLoss.cusNameInfo.data.forEach((e) => {
              const records = {
                //可根据需求更改
                cusName: e.cusName,
              };
              this.restaurantsCusName.push(records);
            });
          }
        })
        .catch((e) => {
          this.$message.error("发生错误：" + e);
        });
    },
    listCusManager() {
      this.restaurantsCusManager = []; //清空数组 否则将导致后续记录添加导致重复
      this.$store
        .dispatch("CusLoss/listCusManager", null)
        .then(() => {
          if (this.$store.state.CusLoss.cusManagerInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.$store.state.CusLoss.cusManagerInfo.data.forEach((e) => {
              const records = {
                //可根据需求更改
                cusManager: e.cusManager,
              };
              this.restaurantsCusManager.push(records);
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
        current: 1,
        pageSize: 5,
        cusName: this.cusName,
        cusManager: this.cusManager,
      };
      this.$store
        .dispatch("CusLoss/queryAllCusLossInfo", params)
        .then(() => {
          if (this.$store.state.CusLoss.cusLossInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.tableData = this.$store.state.CusLoss.cusLossInfo.data.records;

            this.total = this.$store.state.CusLoss.cusLossInfo.data.total;
            this.currentPage = this.$store.state.CusLoss.cusLossInfo.data.current;
            this.size = this.$store.state.CusLoss.cusLossInfo.data.size;
          }
          if (this.$store.state.CusLoss.cusLossInfo.code === 403) {
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
        .dispatch("User/del", params)
        .then(() => {
          if (
            this.$store.state.User.delInfo.code === 200 &&
            this.$store.state.User.delInfo.data === true
          ) {
            this.$message({
              message: "删除操作成功！",
              type: "success",
            });
            this.reInit();
          } else {
            this.$message.error("执行删除操作失败！");
          }
          if (this.$store.state.User.delInfo.code === 403) {
            this.$message({
              message: "当前角色无相关权限",
              type: "warning",
            });
            return;
          }
        })
        .catch((e) => {
          this.$message.error("执行删除操作失败！发生错误：" + e);
        });
    },
    onAdd: function () {
      this.outerVisible = false;
      this.outerUpdateVisible = false;
    },
    reInit() {
      this.queryAll();
      this.listCusName();
      this.listCusManager();
    },
    ondetails: function () {
      if (
        this.multipleSelection === undefined ||
        this.multipleSelection.length === 0
      ) {
        this.$message({
          message: "请选择一条信息执行暂缓流失措施维护操作",
          type: "warning",
        });
        return;
      }
      if (this.multipleSelection.length > 1) {
        this.$message({
          message: "最多只能选择一条信息执行暂缓流失措施维护操作",
          type: "warning",
        });
        return;
      }
      if (this.multipleSelection[0].state === 1) {
        this.$message({
          message: "确认流失客户无法进行维护",
          type: "warning",
        });
        return;
      }
      this.outerUpdateVisible = true;
    },
    onInfo: function () {
      if (
        this.multipleSelection === undefined ||
        this.multipleSelection.length === 0
      ) {
        this.$message({
          message: "请选择一条信息查看客户流失详情",
          type: "warning",
        });
        return;
      }
      if (this.multipleSelection.length > 1) {
        this.$message({
          message: "最多只能选择一条信息查看客户流失详情",
          type: "warning",
        });
        return;
      }
      if (this.multipleSelection[0].state === 0) {
        this.$message({
          message: "暂缓流失客户无法查看详情",
          type: "warning",
        });
        return;
      }
      this.outerVisible = true;
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

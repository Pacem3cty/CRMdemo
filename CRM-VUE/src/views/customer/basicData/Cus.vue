<template>
  <div>
    <el-breadcrumb separator="/" class="main-nav">
      <el-breadcrumb-item>首页</el-breadcrumb-item>
      <el-breadcrumb-item>客户管理</el-breadcrumb-item>
      <el-breadcrumb-item>客户信息管理</el-breadcrumb-item>
      <div style="font-size: 25px; float: right; margin-right: 25px">
        <i
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
        ></i>
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
        <label class="i-label">客户编号</label>
        <el-input v-model="cusNum" placeholder="请输入客户编号"></el-input>
      </div>

      <div class="i-div-r">
        <el-button type="primary" style="margin-top: 9px" @click="this.queryAll"
          >查询</el-button
        >
          <el-button
            type="primary"
            style="margin-top: 9px"
            @click="this.onlinkman"
            >联系人管理</el-button
          >
           <el-button
            type="primary"
            style="margin-top: 9px"
            @click="this.oncontact"
            >交往记录管理</el-button
          >
           <el-button
            type="primary"
            style="margin-top: 9px"
            @click="this.onorder"
            >订单管理</el-button
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
         <el-table-column prop="lossStatus" label="流失状态" width="85">
          <template slot-scope="scope">
            <span v-if="scope.row.lossStatus === 0"
              ><el-tag type="success">未流失</el-tag></span
            >
            <span v-else-if="scope.row.lossStatus === 1"
              ><el-tag type="warning">暂缓流失</el-tag></span
            >
            <span v-else-if="scope.row.lossStatus === 2"
              ><el-tag type="danger">已流失</el-tag></span
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
      title="新增客户信息"
      :visible.sync="outerVisible"
      v-if="outerVisible"
      :close-on-click-modal="false"
    >
      <CusAdd @onAdd="onAdd" @reInit="reInit"></CusAdd>
    </el-dialog>
    <!-- 修改 -->
    <el-dialog
      title="修改客户信息"
      v-if="outerUpdateVisible"
      :visible.sync="outerUpdateVisible"
      :close-on-click-modal="false"
    >
      <CusUpdate
        @onAdd="onAdd"
        @reInit="reInit"
        :multiple="this.multipleSelection"
      ></CusUpdate>
    </el-dialog>
    <!-- 客户交往记录 -->
    <el-dialog
      title="管理客户交往记录信息"
      v-if="outerContactVisible"
      :visible.sync="outerContactVisible"
      :close-on-click-modal="false"
    >
      <CusContactMainten
        @onAdd="onAdd"
        @reInit="reInit"
        :multiple="this.multipleSelection"
      ></CusContactMainten>
    </el-dialog>
    <!-- 客户联系人 -->
    <el-dialog
      title="管理客户联系人信息"
      v-if="outerLinkmanVisible"
      :visible.sync="outerLinkmanVisible"
      :close-on-click-modal="false"
    >
      <CusLinkmanMainten
        @onAdd="onAdd"
        @reInit="reInit"
        :multiple="this.multipleSelection"
      ></CusLinkmanMainten>
    </el-dialog>
    <!-- 客户订单 -->
    <el-dialog
      title="管理客户订单信息"
      v-if="outerOrderVisible"
      :visible.sync="outerOrderVisible"
      :close-on-click-modal="false"
    >
      <CusOrderMainten
        @onAdd="onAdd"
        @reInit="reInit"
        :multiple="this.multipleSelection"
      ></CusOrderMainten>
    </el-dialog>
  </div>
</template>

<script>
import CusAdd from "../management/CusAdd.vue";
import CusUpdate from "../management/CusUpdate.vue";
import CusContactMainten from "../management/CusContactMainten.vue";
import CusLinkmanMainten from "../management/CusLinkmanMainten.vue";
import CusOrderMainten from "../management/CusOrderMainten.vue";

export default {
  components: {
    CusAdd,
    CusUpdate,
    CusContactMainten,
    CusLinkmanMainten,
    CusOrderMainten
  },
  data() {
    return {
      restaurantsCusName: [],
      value: "",
      tableData: [],
      tableColumns: [
        { key: "id", name: "序号", width: 50 },
        { key: "cusNum", name: "客户编号", width: 150 },
        { key: "cusName", name: "客户名称", width: 100 },
        { key: "artificialPerson", name: "法人代表", width: 80 },
        { key: "area", name: "地区", width: 100 },
        { key: "cusManager", name: "客户经理", width: 80 },
        { key: "csr", name: "满意度", width: 80 },
        { key: "level", name: "客户级别", width: 120 },
        { key: "cct", name: "信用度", width: 80 },
        { key: "cusAddress", name: "详细地址", width: 200 },
        { key: "postalCode", name: "邮编", width: 80 },
        { key: "cusPhone", name: "客户电话", width: 150 },
        { key: "cusFax", name: "传真", width: 150 },
        { key: "webSite", name: "网站", width: 180 },
        { key: "socialCreditCode", name: "统一社会信用代码", width: 180 },
        { key: "registeredCapital", name: "注册资金", width: 100 },
        { key: "annualTurnover", name: "年营业额", width: 100 },
        { key: "accountBank", name: "开户银行", width: 100 },
        { key: "accountNum", name: "银行账号", width: 180 },
        { key: "createDate", name: "创建日期", width: 100 },
        { key: "updateDate", name: "修改日期", width: 100 },
      ],
      loading: false,
      input: "",
      dialogVisible: false,
      outerVisible: false,
      outerLinkmanVisible: false,
      outerContactVisible: false,
      outerOrderVisible:false,
      outerUpdateVisible: false,
      cusName: "",
      cusNum: "",
      level: "",
      createDate: "",
      updateDate: "",
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
    handleSelect(item) {
      this.cusName = item.cusName;
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
        .dispatch("Cus/listCusName", null)
        .then(() => {
          if (this.$store.state.Cus.cusNameInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.$store.state.Cus.cusNameInfo.data.forEach((e) => {
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
    queryAll() {
      this.loading = true;
      const params = {
        current: 1,
        pageSize: 5,
        cusName: this.cusName,
        cusNum: this.cusNum,
      };
      this.$store
        .dispatch("Cus/queryAllCusInfo", params)
        .then(() => {
          if (this.$store.state.Cus.cusInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.tableData = this.$store.state.Cus.cusInfo.data.records;

            this.total = this.$store.state.Cus.cusInfo.data.total;
            this.currentPage = this.$store.state.Cus.cusInfo.data.current;
            this.size = this.$store.state.Cus.cusInfo.data.size;
          }
          if (this.$store.state.Cus.cusInfo.code === 403) {
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
        .dispatch("Cus/del", params)
        .then(() => {
          if (
            this.$store.state.Cus.deleteInfo.code === 200 &&
            this.$store.state.Cus.deleteInfo.data === true
          ) {
            this.$message({
              message: "删除操作成功！",
              type: "success",
            });
            this.reInit();
          } else {
            this.$message.error("执行删除操作失败！");
          }
          if (this.$store.state.Cus.deleteInfo.code === 403) {
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
      this.outerLinkmanVisible = false;
      this.outerContactVisible = false;
    },
    reInit() {
      this.queryAll();
      this.listCusName();
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
    onlinkman: function () {
      if (
        this.multipleSelection === undefined ||
        this.multipleSelection.length === 0
      ) {
        this.$message({
          message: "请选择一条信息执行管理联系人信息操作",
          type: "warning",
        });
        return;
      }
      if (this.multipleSelection.length > 1) {
        this.$message({
          message: "最多只能选择一条信息执行管理联系人信息操作",
          type: "warning",
        });
        return;
      }
      this.outerLinkmanVisible = true;
    },
    oncontact: function () {
      if (
        this.multipleSelection === undefined ||
        this.multipleSelection.length === 0
      ) {
        this.$message({
          message: "请选择一条信息执行管理交往记录信息操作",
          type: "warning",
        });
        return;
      }
      if (this.multipleSelection.length > 1) {
        this.$message({
          message: "最多只能选择一条信息执行管理交往记录信息操作",
          type: "warning",
        });
        return;
      }
      this.outerContactVisible = true;
    },
    onorder: function () {
      if (
        this.multipleSelection === undefined ||
        this.multipleSelection.length === 0
      ) {
        this.$message({
          message: "请选择一条信息执行管理订单信息操作",
          type: "warning",
        });
        return;
      }
      if (this.multipleSelection.length > 1) {
        this.$message({
          message: "最多只能选择一条信息执行管理订单信息操作",
          type: "warning",
        });
        return;
      }
      this.outerOrderVisible = true;
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
}，
.i-div-r {
  float: right;
  margin-right: 45px;
  margin-bottom: 10px;
}
</style>

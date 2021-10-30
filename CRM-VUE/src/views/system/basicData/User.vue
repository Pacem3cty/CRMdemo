<template>
  <div>
    <el-breadcrumb separator="/" class="main-nav">
      <el-breadcrumb-item>首页</el-breadcrumb-item>
      <el-breadcrumb-item>营销管理</el-breadcrumb-item>
      <el-breadcrumb-item>营销机会管理</el-breadcrumb-item>
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
        <label class="i-label">用户名称</label>
        <el-autocomplete
          popper-class="my-autocomplete"
          v-model="userName"
          value-key="userName"
          :fetch-suggestions="querySearchCustomerName"
          :trigger-on-focus="false"
          :clearable="false"
          placeholder="请输入用户名称"
          @select="handleSelect"
        >
        </el-autocomplete>
      </div>

      <div class="i-div">
        <label class="i-label">邮箱</label>
        <el-autocomplete
          popper-class="my-autocomplete"
          v-model="email"
          :fetch-suggestions="querySearchCreatePerson"
          :trigger-on-focus="false"
          :clearable="false"
          placeholder="请输入邮箱"
          @select="handleSelect"
        >
        </el-autocomplete>
      </div>

       <div class="i-div">
        <label class="i-label">电话</label>
        <!-- <el-input v-model="createPerson" placeholder="请输入创建人"></el-input> -->
        <el-autocomplete
          popper-class="my-autocomplete"
          v-model="phone"
          :fetch-suggestions="querySearchCreatePerson"
          :trigger-on-focus="false"
          :clearable="false"
          placeholder="请输入电话"
          @select="handleSelect"
        >
        </el-autocomplete>
      </div>

       <div class="i-div">
        <label class="i-label">真实姓名</label>
        <el-autocomplete
          popper-class="my-autocomplete"
          v-model="trueName"
          :fetch-suggestions="querySearchCreatePerson"
          :trigger-on-focus="false"
          :clearable="false"
          placeholder="请输入真实姓名"
          @select="handleSelect"
        >
        </el-autocomplete>
      </div>

      <div class="i-div">
        <label class="i-label">备注</label>
        <el-autocomplete
          popper-class="my-autocomplete"
          v-model="remark"
          :fetch-suggestions="querySearchCreatePerson"
          :trigger-on-focus="false"
          :clearable="false"
          placeholder="请输入备注"
          @select="handleSelect"
        >
        </el-autocomplete>
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
    <!-- <el-dialog
      title="新增营销机会信息"
      :visible.sync="outerVisible"
      v-if="outerVisible"
      :close-on-click-modal="false"
    >
      <SaleChanceAdd @onAdd="onAdd" @getfindAll="queryAll"></SaleChanceAdd>
    </el-dialog> -->
    <!-- 修改 -->
    <!-- <el-dialog title="修改营销机会信息" :visible.sync="outerUpdateVisible" :close-on-click-modal="false"  v-if="outerUpdateVisible">
      <SaleChanceUpdate
        @onAdd="onAdd"
        @getfindAll="queryAll"
        :multiple="this.multipleSelection"
      ></SaleChanceUpdate>
    </el-dialog> -->
  </div>
</template>

<script>
// import SaleChanceAdd from "../management/SaleChanceAdd.vue";
// import SaleChanceUpdate from "../management/SaleChanceUpdate.vue";

export default {
  components: {
    // SaleChanceAdd,
    // SaleChanceUpdate,
  },
  data() {
    return {
      restaurantsCustomerName: [],
      restaurantsCreatePerson: [],
      value: "",
      tableData: [],
      tableColumns: [
        { key: "id", name: "编号", width: 50 },
        { key: "userName", name: "用户名称", width: 150 },
        { key: "userPwd", name: "用户密码", width: 260 },
        { key: "trueName", name: "真实姓名", width: 80 },
        { key: "email", name: "邮箱", width: 240 },
        { key: "phone", name: "电话", width: 150 },
        { key: "remark", name: "备注", width: 300 },
        { key: "createDate", name: "创建日期", width: 100 },
        { key: "updateDate", name: "修改日期", width: 100 },
      ],
      loading: false,
      input: "",
      dialogVisible: false,
      outerVisible: false,
      innerVisible: false,
      outerUpdateVisible: false,
      id: "",
      userName: "",
      userPwd: "",
      trueName: "",
      email: "",
      phone: "",
      remark: "",
      createDate: "",
      updateDate: "",
      currentPage: 1,
      size: 5,
      total: 0,
      multipleSelection: [],
    };
  },
  created() {
    this.queryAll();
    // this.listCustomerName();
    // this.listCreatePerson();
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
    hell() {
      this.outerVisible1 = true;
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
      this.$store
        .dispatch("Sales/queryAllUserInfo", null)
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
         "current":this.currentPage,
        "pageSize": this.size,
        "userName": this.userName,
        "email": this.email,
        "phone": this.phone,
        "trueName": this.trueName
      };
       this.$store
        .dispatch("User/queryAllUserInfo", params)
        .then(() => {
          if (this.$store.state.User.userInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.tableData = this.$store.state.User.userInfo.data.records;

            this.total = this.$store.state.User.userInfo.data.total;
            this.currentPage = this.$store.state.User.userInfo.data.current;
            this.size = this.$store.state.User.userInfo.data.size;
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
            this.queryAll();
            this.listCustomerName();
            this.listCreatePerson();
          } else {
            this.$message.error("执行删除操作失败！");
          }
        })
        .catch((e) => {
          this.$message.error("执行删除操作失败！发生错误：" + e);
        });
    },
    onAdd: function () {
      this.outerVisible = false;
      this.outerUpdateVisible = false;
      this.queryAll();
      this.listCreatePerson();
      this.listCustomerName();
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
</style>

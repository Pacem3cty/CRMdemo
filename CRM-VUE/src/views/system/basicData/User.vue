<template>
  <div>
    <el-breadcrumb separator="/" class="main-nav">
      <el-breadcrumb-item>首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统设置</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
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
          :fetch-suggestions="querySearchUserName"
          :trigger-on-focus="false"
          :clearable="false"
          placeholder="请输入用户名称"
          @select="handleSelect"
        >
        </el-autocomplete>
      </div>

      <!-- <div class="i-div">
        <label class="i-label">邮箱</label>
        <el-input v-model="email" placeholder="请输入邮箱"></el-input>
      </div> -->

      <div class="i-div">
        <label class="i-label">电话</label>
        <el-input v-model="phone" placeholder="请输入电话"></el-input>
      </div>

      <div class="i-div">
        <label class="i-label">真实姓名</label>
        <el-autocomplete
          popper-class="my-autocomplete"
          v-model="trueName"
          value-key="trueName"
          :fetch-suggestions="querySearchTrueName"
          :trigger-on-focus="false"
          :clearable="false"
          placeholder="请输入真实姓名"
          @select="handleSelect"
        >
        </el-autocomplete>
      </div>

      <div class="i-div">
        <label class="i-label">备注</label>
        <el-input v-model="remark" placeholder="请输入备注"></el-input>
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
    <el-dialog
      title="新增用户信息"
      :visible.sync="outerVisible"
      v-if="outerVisible"
      :close-on-click-modal="false"
    >
      <UserAdd @onAdd="onAdd" @reInit="reInit"></UserAdd>
    </el-dialog>
    <!-- 修改 -->
    <el-dialog
      title="修改用户信息"
      v-if="outerUpdateVisible"
      :visible.sync="outerUpdateVisible"
      :close-on-click-modal="false"
    >
      <UserUpdate
        @onAdd="onAdd"
        @reInit="reInit"
        :multiple="this.multipleSelection"
      ></UserUpdate>
    </el-dialog>
  </div>
</template>

<script>
import UserAdd from "../management/UserAdd.vue";
import UserUpdate from "../management/UserUpdate.vue";

export default {
  components: {
    UserAdd,
    UserUpdate,
  },
  data() {
    return {
      restaurantsUserName: [],
      restaurantsTrueName: [],
      value: "",
      tableData: [],
      tableColumns: [
        { key: "id", name: "编号", width: 50 },
        { key: "userName", name: "用户名称", width: 150 },
        { key: "userPwd", name: "用户密码", width: 265 },
        { key: "trueName", name: "真实姓名", width: 80 },
        { key: "roleName", name: "角色名称", width: 100 },
        { key: "email", name: "邮箱", width: 240 },
        { key: "phone", name: "电话", width: 150 },
        { key: "remark", name: "备注", width: 290 },
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
    this.notice();
    this.reInit();
  },
  mounted() {},
  destroyed() {
    // 销毁用第三方登录
    // window.removeEventListener('storage', this.afterQRScan)
  },
  methods: {
    notice() {
      const h = this.$createElement;
      this.$notify({
        title: "提示消息：",
        message: h(
          "i",
          { style: "color: black" },
          "角色信息可在用户新增/修改弹窗中设置"
        ),
      });
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
    querySearchUserName(queryString, cb) {
      var restaurantsUserName = this.restaurantsUserName;
      var results = queryString
        ? restaurantsUserName.filter(this.createFilterUserName(queryString))
        : restaurantsUserName;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilterUserName(queryString) {
      return (restaurantsUserName) => {
        return (
          restaurantsUserName.userName
            .toLowerCase()
            .indexOf(queryString.toLowerCase()) === 0
        );
      };
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
      this.userName = item.userName;
      this.trueName = item.trueName;
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
    listUserName() {
      this.restaurantsUserName = []; //清空数组 否则将导致后续记录添加导致重复
      this.$store
        .dispatch("User/listUserNameInfo", null)
        .then(() => {
          if (this.$store.state.User.userNameInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.$store.state.User.userNameInfo.data.forEach((e) => {
              const records = {
                //可根据需求更改
                userName: e.userName,
              };
              this.restaurantsUserName.push(records);
            });
          }
        })
        .catch((e) => {
          this.$message.error("发生错误：" + e);
        });
    },
    listTrueName() {
      this.restaurantsTrueName = []; //清空数组 否则将导致后续记录添加导致重复
      this.$store
        .dispatch("User/listTrueNameInfo", null)
        .then(() => {
          if (this.$store.state.User.trueNameInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.$store.state.User.trueNameInfo.data.forEach((e) => {
              const records = {
                //可根据需求更改
                trueName: e.trueName,
              };
              this.restaurantsTrueName.push(records);
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
        userName: this.userName,
        email: this.email,
        phone: this.phone,
        trueName: this.trueName,
        remark: this.remark,
      };
      this.$store
        .dispatch("User/queryAllUserInfo", params)
        .then(() => {
          if (this.$store.state.User.userInfo.code === 200) {
            this.loading = false; //取消加载状态
            // this.tableData = this.$store.state.User.userInfo.data.records;
            this.tableData = this.$store.state.User.userInfo.data;
            this.total = this.$store.state.User.userInfo.data.total;
            this.currentPage = this.$store.state.User.userInfo.data.current;
            this.size = this.$store.state.User.userInfo.data.size;
          }
          if (this.$store.state.User.userInfo.code === 403) {
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
      this.listUserName();
      this.listTrueName();
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

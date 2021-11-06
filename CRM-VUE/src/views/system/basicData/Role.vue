<template>
  <div>
    <el-breadcrumb separator="/" class="main-nav">
      <el-breadcrumb-item>首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统设置</el-breadcrumb-item>
      <el-breadcrumb-item>角色管理</el-breadcrumb-item>
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
        <label class="i-label">角色名称</label>
        <el-autocomplete
          popper-class="my-autocomplete"
          v-model="roleName"
          value-key="roleName"
          :fetch-suggestions="querySearchRoleName"
          :trigger-on-focus="false"
          :clearable="false"
          placeholder="请输入用户名称"
          @select="handleSelect"
        >
        </el-autocomplete>
      </div>

      <div class="i-div">
        <el-button type="primary" style="margin-top: 9px" @click="this.queryAll"
          >查询</el-button
        >
      </div>

      <div class="i-div-r">
        <el-button type="primary" style="margin-top: 9px" @click="onAuth">授权管理</el-button>
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
      title="新增角色信息"
      :visible.sync="outerVisible"
      v-if="outerVisible"
      :close-on-click-modal="false"
    >
      <RoleAdd @onAdd="onAdd" @reInit="reInit"></RoleAdd>
    </el-dialog>
    <!-- 修改 -->
    <el-dialog
      title="修改角色信息"
      :visible.sync="outerUpdateVisible"
      :close-on-click-modal="false"
    >
      <RoleUpdate
        @onAdd="onAdd"
        @reInit="reInit"
        :multiple="this.multipleSelection"
      ></RoleUpdate>
    </el-dialog>
  </div>
</template>

<script>
import RoleAdd from "../management/RoleAdd.vue";
import RoleUpdate from "../management/RoleUpdate.vue";

export default {
  components: {
    RoleAdd,
    RoleUpdate,
  },
  data() {
    return {
      restaurantsRoleName: [],
      value: "",
      tableData: [],
      tableColumns: [
        { key: "id", name: "编号", width: 286 },
        { key: "roleName", name: "角色名称", width: 286 },
        { key: "roleRemark", name: "角色备注", width: 286 },
        { key: "createDate", name: "创建日期", width: 286 },
        { key: "updateDate", name: "修改日期", width: 286 },
      ],
      loading: false,
      input: "",
      dialogVisible: false,
      outerVisible: false,
      innerVisible: false,
      outerUpdateVisible: false,
      id: "",
      roleName: "",
      roleRemark: "",
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
    querySearchRoleName(queryString, cb) {
      var restaurantsRoleName = this.restaurantsRoleName;
      var results = queryString
        ? restaurantsRoleName.filter(this.createFilterRoleName(queryString))
        : restaurantsRoleName;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilterRoleName(queryString) {
      return (restaurantsRoleName) => {
        return (
          restaurantsRoleName.roleName
            .toLowerCase()
            .indexOf(queryString.toLowerCase()) === 0
        );
      };
    },
    handleSelect(item) {
      this.roleName = item.roleName;
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
    listRoleName() {
      this.restaurantsRoleName = []; //清空数组 否则将导致后续记录添加导致重复
      this.$store
        .dispatch("Role/listRoleNameInfo", null)
        .then(() => {
          if (this.$store.state.Role.roleNameInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.$store.state.Role.roleNameInfo.data.forEach((e) => {
              const records = {
                //可根据需求更改
                roleName: e.roleName,
              };
              this.restaurantsRoleName.push(records);
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
        roleName: this.roleName,
      };
      this.$store
        .dispatch("Role/queryAllRoleInfo", params)
        .then(() => {
          if (this.$store.state.Role.roleInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.tableData = this.$store.state.Role.roleInfo.data.records;

            this.total = this.$store.state.Role.roleInfo.data.total;
            this.currentPage = this.$store.state.Role.roleInfo.data.current;
            this.size = this.$store.state.Role.roleInfo.data.size;
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
        .dispatch("Role/del", params)
        .then(() => {
          if (
            this.$store.state.Role.deleteInfo.code === 200 &&
            this.$store.state.Role.deleteInfo.data === true
          ) {
            this.$message({
              message: "删除操作成功！",
              type: "success",
            });
            this.reInit();
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
    },
    reInit() {
      this.queryAll();
      this.listRoleName();
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
    onAuth: function () {
      if (
        this.multipleSelection === undefined ||
        this.multipleSelection.length === 0
      ) {
        this.$message({
          message: "请选择一条角色信息执行授权管理操作",
          type: "warning",
        });
        return;
      }
      if (this.multipleSelection.length > 1) {
        this.$message({
          message: "最多只能选择一条角色信息执行授权管理操作",
          type: "warning",
        });
        return;
      }

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
}
</style>

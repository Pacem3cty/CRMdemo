<template>
  <div>
    <el-breadcrumb separator="/" class="main-nav">
      <el-breadcrumb-item>首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统设置</el-breadcrumb-item>
      <el-breadcrumb-item>资源管理</el-breadcrumb-item>
      <div style="font-size: 25px; float: right; margin-right: 25px">
        <i
          class="el-icon-refresh-left"
          style="margin-right: 6px; cursor: pointer"
          @click="queryAll"
          title="刷新"
        ></i>
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
        <el-button-group>
          <el-button
            size="small"
            type="primary"
            icon="el-icon-arrow-down"
            :disabled="unfoldDisabled"
            @click="unfold"
            >全部展开</el-button
          >
          <el-button
            size="small"
            type="primary"
            @click="fold"
            :disabled="foldDisabled"
            >全部折叠<i class="el-icon-arrow-up"></i
          ></el-button>
        </el-button-group>
      </div>
      <div class="i-div-r">
        <el-tooltip
          class="item"
          effect="dark"
          content="请选择需新增子资源信息的一项"
          placement="left"
        >
          <el-button
            type="primary"
            style="margin-top: 9px"
            @click="onsubdetails"
            >新增子资源信息</el-button
          >
        </el-tooltip>
      </div>
    </div>
    <div class="table-div">
      <el-table
        v-if="refreshTable"
        row-key="id"
        v-loading="loading"
        element-loading-text="加载中"
        element-loading-spinner="el-icon-loading"
        element-loading-background="rgba(0, 0, 0, 0.8)"
        :data="tableData"
        height="700"
        border
        @selection-change="handleSelectionChange"
        :default-expand-all="isExpand"
        :tree-props="{ children: 'childList' }"
      >
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
      title="新增资源信息"
      :visible.sync="outerVisible"
      v-if="outerVisible"
      :close-on-click-modal="false"
    >
      <ModuleAdd @onAdd="onAdd" @reInit="queryAll"></ModuleAdd>
    </el-dialog>
    <el-dialog
      title="新增子资源信息"
      :visible.sync="outerSubVisible"
      v-if="outerSubVisible"
      :close-on-click-modal="false"
    >
      <SubModuleAdd
        @onAdd="onAdd"
        @reInit="queryAll"
        :multiple="this.multipleSelection"
      ></SubModuleAdd>
    </el-dialog>
    <!-- 修改 -->
    <el-dialog
      title="修改资源信息"
      :visible.sync="outerUpdateVisible"
      :close-on-click-modal="false"
    >
      <ModuleUpdate
        v-if="outerUpdateVisible"
        @onAdd="onAdd"
        @reInit="queryAll"
        :multiple="this.multipleSelection"
      ></ModuleUpdate>
    </el-dialog>
  </div>
</template>
<script>
import ModuleAdd from "../management/ModuleAdd.vue";
import SubModuleAdd from "../management/SubModuleAdd.vue";
import ModuleUpdate from "../management/ModuleUpdate.vue";

export default {
  components: {
    ModuleAdd,
    SubModuleAdd,
    ModuleUpdate,
  },
  data() {
    return {
      refreshTable: true,
      isExpand: false,
      value: "",
      tableData: [],
      tableColumns: [
        { key: "id", name: "资源编号", width: 286 },
        { key: "parentId", name: "上级资源编号", width: 286 },
        { key: "moduleName", name: "资源名称", width: 286 },
        { key: "url", name: "资源地址", width: 286 },
        { key: "optValue", name: "权限码", width: 286 },
      ],
      unfoldDisabled: false,
      foldDisabled: true,
      loading: false,
      dialogVisible: false,
      outerVisible: false,
      outerSubVisible: false,
      innerVisible: false,
      outerUpdateVisible: false,
      outerAuthVisible: false,
      id: "",
      parentId: "",
      moduleName: "",
      url: "",
      optValue: "",
      multipleSelection: [],
    };
  },
  created() {
    this.queryAll();
  },
  mounted() {},
  destroyed() {
    // 销毁用第三方登录
    // window.removeEventListener('storage', this.afterQRScan)
  },
  methods: {
    unfold() {
      this.refreshTable = false;
      this.isExpand = true;
      this.$nextTick(() => {
        this.refreshTable = true;
        this.unfoldDisabled = true;
        this.foldDisabled = false;
      });
    },
    fold() {
      this.refreshTable = false;
      this.isExpand = false;
      this.$nextTick(() => {
        this.refreshTable = true;
        this.foldDisabled = true;
        this.unfoldDisabled = false;
      });
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
    queryAll() {
      this.loading = true;
      this.$store
        .dispatch("Module/queryAllModuleInfo", null)
        .then(() => {
          if (this.$store.state.Module.moduleInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.tableData = this.$store.state.Module.moduleInfo.data;
          }
          if (this.$store.state.Module.moduleInfo.code === 403) {
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
        .dispatch("Module/del", params)
        .then(() => {
          if (
            this.$store.state.Module.deleteInfo.code === 200 &&
            this.$store.state.Module.deleteInfo.data === true
          ) {
            this.$message({
              message: "删除操作成功！",
              type: "success",
            });
            this.queryAll();
          } else {
            this.$message.error("执行删除操作失败！");
          }
          if (this.$store.state.Module.deleteInfo.code === 403) {
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
      this.outerSubVisible = false;
      this.outerUpdateVisible = false;
    },
    onsubdetails: function () {
      if (
        this.multipleSelection === undefined ||
        this.multipleSelection.length === 0
      ) {
        this.$message({
          message: "请选择一条信息执行新增子资源信息操作",
          type: "warning",
        });
        return;
      }
      if (this.multipleSelection.length > 1) {
        this.$message({
          message: "最多只能选择一条信息执行新增子资源信息操作",
          type: "warning",
        });
        return;
      }
      this.outerSubVisible = true;
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
  margin-left: 45px;
  margin-top: 10px;
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

<template>
  <div>
    <div>
      <el-descriptions
        class="margin-top"
        title="营销机会信息"
        :column="3"
        :size="size"
        border
      >
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-office-building"></i>客户名称</template
          >{{ this.$props.multiple[0].customerName }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-opportunity"></i>机会来源</template
          >{{ this.$props.multiple[0].chanceSource }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-custom"></i>联系人</template
          >{{ this.$props.multiple[0].contactPerson }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-mobile-phone"></i>联系电话</template
          >{{ this.$props.multiple[0].contactPhone }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-tickets"></i>机会描述</template
          >{{ this.$props.multiple[0].description }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-data"></i>成功概率(%)</template
          >{{ this.$props.multiple[0].probability }}
        </el-descriptions-item>
      </el-descriptions>
    </div>
    <div style="font-size: 25px; float: left; margin-left: 25px">
      <i
        class="el-icon-success"
        style="margin-right: 6px; :6px ; cursor: pointer"
        @click="signSuccessDialogVisible = true"
        title="标记开发成功"
      ></i>
      <i
        class="el-icon-error"
        style="margin-right: 6px; :6px ; cursor: pointer"
        @click="signFailureDialogVisible = true"
        title="标记开发失败"
      ></i>
    </div>
    <div style="font-size: 25px; float: right; margin-right: 25px">
      <i
        class="el-icon-document-add"
        style="margin-right: 6px; cursor: pointer"
        @click="outerAddVisible = true"
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
      <i
        class="el-icon-refresh-left"
        style="margin-right: 6px; cursor: pointer"
        @click="queryAll"
        title="刷新"
      ></i>
      <i
        class="el-icon-document"
        style="margin-right: 6px; cursor: pointer"
        title="导出为PDF"
      ></i>
    </div>
    <div class="table-div">
      <el-table
        v-loading="loading"
        element-loading-text="加载中"
        element-loading-spinner="el-icon-loading"
        element-loading-background="rgba(0, 0, 0, 0.8)"
        :data="tableData"
        height="750"
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
    <div>
      <!-- 标记开发成功提示 -->
      <el-dialog
        :close-on-click-modal="false"
        :visible.sync="signSuccessDialogVisible"
        :append-to-body="true"
        width="30%"
        ><i slot="title" class="el-icon-warning">警告</i>
        <span>确认开发成功？标记为开发成功后将无法进行开发项维护</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="signSuccessDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="signSuccess">确定</el-button>
        </span>
      </el-dialog>
      <!-- 标记开发失败提示 -->
      <el-dialog
        :close-on-click-modal="false"
        :visible.sync="signFailureDialogVisible"
        :append-to-body="true"
        width="30%"
        ><i slot="title" class="el-icon-warning">警告</i>
        <span>确认开发失败？标记为开发失败后将无法进行开发项维护</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="signFailureDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="signFailure">确定</el-button>
        </span>
      </el-dialog>
      <!-- 删除提示 -->
      <el-dialog
        :close-on-click-modal="false"
        :visible.sync="dialogVisible"
        :append-to-body="true"
        width="30%"
        ><i slot="title" class="el-icon-warning">警告</i>
        <span>确认删除该信息？</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="onDel">确定</el-button>
        </span>
      </el-dialog>
      <el-dialog
        title="新增计划项数据"
        :visible.sync="outerAddVisible"
        :append-to-body="true"
        :close-on-click-modal="false"
      >
        <CusDevPlanAdd
          v-if="outerAddVisible"
          @onAdd="onAdd"
          @queryAll="queryAll"
          :salesChanceId="this.$props.multiple[0].id"
          :modal-append-to-body="true"
        ></CusDevPlanAdd>
      </el-dialog>
      <el-dialog
        title="修改计划项数据"
        :visible.sync="outerUpdateVisible"
        :append-to-body="true"
        :close-on-click-modal="false"
      >
        <CusDevPlanUpdate
          @onAdd="onAdd"
          @queryAll="queryAll"
          :multiple="this.multipleSelection"
        ></CusDevPlanUpdate>
      </el-dialog>
    </div>
  </div>
</template>
<script>
import CusDevPlanAdd from "../management/CusDevPlanAdd.vue";
import CusDevPlanUpdate from "../management/CusDevPlanUpdate.vue";
export default {
  props: ["multiple"],
  components: {
    CusDevPlanAdd,
    CusDevPlanUpdate,
  },
  data() {
    return {
      signSuccessDialogVisible:false,
      signFailureDialogVisible:false,
      outerUpdateVisible: false,
      outerAddVisible: false,
      outerVisible: false,
      innerVisible: false,
      loading: false,
      tableData: [],
      currentPage: 1,
      size: 5,
      total: 0,
      tableColumns: [
        { key: "id", name: "编号", width: 50 },
        { key: "saleChanceId", name: "营销机会编号", width: 50 },
        { key: "planItem", name: "计划项", width: 226 },
        { key: "planDate", name: "计划日期", width: 100 },
        { key: "exeAffect", name: "执行效果", width: 226 },
        { key: "createDate", name: "创建日期", width: 100 },
      ],
      multipleSelection: [],
      dialogVisible: false,
      salesChanceId: this.$props.multiple[0].id,
      updateDate: "",
    };
  },
  created() {
    this.queryAll();
  },
  watch: {
    multiple: function () {
      if (this.$props.multiple.length !== 0) {
        this.queryAll();
      }
    },
  },
  methods: {
    queryAll() {
      this.loading = true;
      const params = {
        current: this.currentPage,
        pageSize: this.size,
        saleChanceId: this.$props.multiple[0].id,
      };
      this.$store
        .dispatch("Sales/queryCusDevPlan", params)
        .then(() => {
          if (this.$store.state.Sales.cusDevPlanInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.tableData =
              this.$store.state.Sales.cusDevPlanInfo.data.records;

            this.total = this.$store.state.Sales.cusDevPlanInfo.data.total;
            this.currentPage =
              this.$store.state.Sales.cusDevPlanInfo.data.current;
            this.size = this.$store.state.Sales.cusDevPlanInfo.data.size;
          }
          if (this.$store.state.Sales.cusDevPlanInfo.code === 403) {
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
    handleSelectionChange(val) {
      this.multipleSelection = val;
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
        .dispatch("Sales/cusDevPlanDel", params)
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
          } else {
            this.$message.error("执行删除操作失败！");
          }
          if (this.$store.state.Sales.delInfo.code === 403) {
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
    setUpdateDate() {
      let date = new Date();
      this.updateDate =
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
    },
    signSuccess() {
      this.setUpdateDate();
      const params = {
        id: this.$props.multiple[0].id,
        chanceSource: this.$props.multiple[0].chanceSource,
        customerName: this.$props.multiple[0].customerName,
        probability: this.$props.multiple[0].probability,
        overview: this.$props.multiple[0].overview,
        contactPerson: this.$props.multiple[0].contactPerson,
        contactPhone: this.$props.multiple[0].contactPhone,
        description: this.$props.multiple[0].description,
        createPerson: this.$props.multiple[0].createPerson,
        createDate: this.$props.multiple[0].createDate,
        assignPerson: this.$props.multiple[0].assignPerson,
        assignDate: this.$props.multiple[0].assignDate,
        state: 1,
        devResult: 1,
        isValid: 0,
        updateDate: this.updateDate,
      };
      this.$store
        .dispatch("Sales/update", params)
        .then(() => {
          console.log(this.$store.state.Sales.updateInfo);
          if (this.$store.state.Sales.updateInfo.data === true) {
            this.signSuccessDialogVisible = false;
            this.$emit("onAdd");
            this.$emit("reInit");
            this.$message({
              message: "标记操作成功！",
              type: "success",
            }); 
          } else {
            this.signSuccessDialogVisible = false;
            this.$message.error("标记操作失败！");
            this.$emit("onAdd");
            this.$emit("reInit");
          }
          if (this.$store.state.Sales.updateInfo.code === 403) {
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
          this.$message.error("标记操作失败！发生错误：" + e);
          this.signSuccessDialogVisible = false;
        });
    },
    signFailure() {
      this.setUpdateDate();
      const params = {
        id: this.$props.multiple[0].id,
        chanceSource: this.$props.multiple[0].chanceSource,
        customerName: this.$props.multiple[0].customerName,
        probability: this.$props.multiple[0].probability,
        overview: this.$props.multiple[0].overview,
        contactPerson: this.$props.multiple[0].contactPerson,
        contactPhone: this.$props.multiple[0].contactPhone,
        description: this.$props.multiple[0].description,
        createPerson: this.$props.multiple[0].createPerson,
        createDate: this.$props.multiple[0].createDate,
        assignPerson: this.$props.multiple[0].assignPerson,
        assignDate: this.$props.multiple[0].assignDate,
        state: 1,
        devResult: 2,
        isValid: 0,
        updateDate: this.updateDate,
      };
      this.$store
        .dispatch("Sales/update", params)
        .then(() => {
          console.log(this.$store.state.Sales.updateInfo);
          if (this.$store.state.Sales.updateInfo.data === true) {
            this.signFailureDialogVisible = false;
            this.$emit("onAdd");  
            this.$emit("reInit");
            this.$message({
              message: "标记操作成功！",
              type: "success",
            });
          } else {
            this.signFailureDialogVisible = false;
            this.$message.error("标记操作失败！");
            this.$emit("onAdd");
            this.$emit("reInit");
          }
          if (this.$store.state.Sales.updateInfo.code === 403) {
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
          this.$message.error("标记操作失败！发生错误：" + e);
          this.signFailureDialogVisible = false;
        });
    },
    onAdd: function () {
      this.outerAddVisible = false;
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

<style scoped>
.el-form-item {
  float: left;
}
</style>
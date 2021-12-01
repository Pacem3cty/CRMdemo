<template>
  <div>
    <div style="display: inline; font-size: 15px">
      <label style="margin-right: 6px; :6px ; cursor: pointer">
        <i class="el-icon-money"></i>订单总金额：</label
      >
      <label style="color: blue">¥ {{ orderTotal }}</label>
    </div>
    <div>
      <el-descriptions
        class="margin-top"
        title="客户-订单详情信息"
        :column="3"
        border
      >
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-custom"></i>客户编号</template
          >{{ this.$props.cusNum }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-office-building"></i>客户名称</template
          >{{ this.$props.cusName }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-mobile-phone"></i>客户电话</template
          >{{ this.$props.cusPhone }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-order"></i>订单编号</template
          >{{ this.$props.multiple[0].orderNum }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-custom"></i>下单时间</template
          >{{ this.$props.multiple[0].orderDate }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">下单地址</template
          >{{ this.$props.multiple[0].address }}
        </el-descriptions-item>
      </el-descriptions>
    </div>
    <div style="font-size: 25px; float: left; margin-left: 25px">
      <i
        class="el-icon-success"
        style="margin-right: 6px; :6px ; cursor: pointer"
        @click="signPaidDialogVisible = true"
        title="标记已支付"
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
      <el-dialog
        :close-on-click-modal="false"
        :visible.sync="signPaidDialogVisible"
        :append-to-body="true"
        width="30%"
        ><i slot="title" class="el-icon-warning">警告</i>
        <span>确认订单已支付？标记已支付后将无法修改此订单详情信息</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="signPaidDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="signPaid">确定</el-button>
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
        title="新增订单详情信息"
        v-if="outerAddVisible"
        :visible.sync="outerAddVisible"
        :append-to-body="true"
        :close-on-click-modal="false"
      >
        <OrderDetailsAdd
          @onAdd="onAdd"
          @reInit="reInit"
          :id="this.$props.multiple[0].id"
          :modal-append-to-body="true"
        ></OrderDetailsAdd>
      </el-dialog>
      <el-dialog
        title="修改订单详情信息"
        v-if="outerUpdateVisible"
        :visible.sync="outerUpdateVisible"
        :append-to-body="true"
        :close-on-click-modal="false"
      >
        <OrderDetailsUpdate
          @onAdd="onAdd"
          @reInit="reInit"
          :multiple="this.multipleSelection"
          :id="this.$props.multiple[0].id"
        ></OrderDetailsUpdate>
      </el-dialog>
    </div>
  </div>
</template>
<script>
import OrderDetailsAdd from "../management/OrderDetailsAdd.vue";
import OrderDetailsUpdate from "../management/OrderDetailsUpdate.vue";
export default {
  props: ["multiple", "cusNum", "cusName", "cusPhone"],
  components: {
    OrderDetailsAdd,
    OrderDetailsUpdate,
  },
  data() {
    return {
      id: this.$props.multiple[0].id,
      signPaidDialogVisible: false,
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
        { key: "id", name: "序号", width: 50 },
        { key: "goodsName", name: "商品名称", width: 200 },
        { key: "unit", name: "单位", width: 50 },
        { key: "price", name: "单价/¥", width: 100 },
        { key: "goodsNum", name: "数量", width: 50 },
        { key: "total", name: "总价/¥", width: 100 },
        { key: "remark", name: "备注", width: 200 },
      ],
      multipleSelection: [],
      dialogVisible: false,
      // cusId: this.$props.multiple[0].id,
      updateDate: "",
      orderTotal: "",
    };
  },
  created() {
    this.reInit();
  },
  watch: {
    multiple: function () {
      if (this.$props.multiple.length !== 0) {
        this.queryAll();
      }
    },
  },
  methods: {
    reInit() {
      this.queryAll();
      this.getOrderTotal();
    },
    signPaid() {
      this.setUpdateDate();
      const params = {
        id: this.$props.multiple[0].id,
        orderTotal:this.orderTotal,
        // updateDate: this.updateDate,
      };
      this.$store
        .dispatch("CusOrder/signPaid", params)
        .then(() => {
          console.log(this.$store.state.CusOrder.signPaidInfo);
          if (this.$store.state.CusOrder.signPaidInfo.data === true) {
            this.signPaidDialogVisible = false;
            this.$emit("onAdd");
            this.$emit("queryAll");
            this.$message({
              message: "标记操作成功！",
              type: "success",
            });
          } else {
            this.signPaidDialogVisible = false;
            this.$message.error("标记操作失败！");
            this.$emit("onAdd");
            this.$emit("queryAll");
          }
          if (this.$store.state.CusOrder.signPaidInfo.code === 403) {
            this.$message({
              message: "当前角色无相关权限",
              type: "warning",
            });
            this.signPaidDialogVisible = false;
            this.queryAll();
            return;
          }
        })
        .catch((e) => {
          this.$message.error("标记操作失败！发生错误：" + e);
          this.signPaidDialogVisible = false;
        });
    },
    queryAll() {
      this.loading = true;
      const params = {
        current: this.currentPage,
        pageSize: this.size,
        orderId: this.$props.multiple[0].id,
      };
      this.$store
        .dispatch("OrderDetails/queryAllOrderDetailsInfo", params)
        .then(() => {
          if (this.$store.state.OrderDetails.orderDetailsInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.tableData =
              this.$store.state.OrderDetails.orderDetailsInfo.data.records;

            this.total =
              this.$store.state.OrderDetails.orderDetailsInfo.data.total;
            this.currentPage =
              this.$store.state.OrderDetails.orderDetailsInfo.data.current;
            this.size =
              this.$store.state.OrderDetails.orderDetailsInfo.data.size;
          }
          if (this.$store.state.OrderDetails.orderDetailsInfo.code === 403) {
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
    getOrderTotal() {
      this.$store
        .dispatch("OrderDetails/getOrderTotal", this.$props.multiple[0].id)
        .then(() => {
          if (this.$store.state.OrderDetails.orderTotalInfo.code === 200) {
            this.orderTotal =
              this.$store.state.OrderDetails.orderTotalInfo.data;
          }
          if (this.$store.state.OrderDetails.orderTotalInfo.code === 403) {
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
        .dispatch("OrderDetails/del", params)
        .then(() => {
          // console.log(this.$store.state.CusContact.deleteInfo);
          if (
            this.$store.state.OrderDetails.deleteInfo.code === 200 &&
            this.$store.state.OrderDetails.deleteInfo.data === true
          ) {
            this.$message({
              message: "删除操作成功！",
              type: "success",
            });
            this.reInit();
          } else {
            this.$message.error("执行删除操作失败！");
          }
          if (this.$store.state.OrderDetails.deleteInfo.code === 403) {
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
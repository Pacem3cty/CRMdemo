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
    <div style="font-size: 25px; float: right; margin-right: 25px">
         <i
        class="el-icon-refresh-left"
        style="margin-right: 6px; cursor: pointer"
        @click="reInit"
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
  </div>
</template>
<script>

export default {
  props: ["multiple", "cusNum", "cusName", "cusPhone"],
  components: {},
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
        { key: "price", name: "单价", width: 100 },
        { key: "goodsNum", name: "数量", width: 50 },
        { key: "total", name: "总价", width: 100 },
        { key: "remark", name: "备注", width: 200 },
      ],
      multipleSelection: [],
      dialogVisible: false,
      // cusId: this.$props.multiple[0].id,
      updateDate: "",
      orderTotal:""
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
  },
};
</script>

<style scoped>
.el-form-item {
  float: left;
}
</style>
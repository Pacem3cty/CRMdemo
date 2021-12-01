<template>
  <div>
    <div>
      <el-descriptions
        class="margin-top"
        title="客户基本信息"
        :column="3"
        :size="size"
        border
      >
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-opportunity"></i>客户编号</template
          >{{ this.$props.multiple[0].cusNum }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-office-building"></i>客户名称</template
          >{{ this.$props.multiple[0].cusName }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-custom"></i>法人代表</template
          >{{ this.$props.multiple[0].artificialPerson }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-custom"></i>客户经理</template
          >{{ this.$props.multiple[0].cusManager }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-custom"></i>客户级别</template
          >{{ this.$props.multiple[0].level }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-mobile-phone"></i>客户电话</template
          >{{ this.$props.multiple[0].cusPhone }}
        </el-descriptions-item>
      </el-descriptions>
    </div>
    <div style="font-size: 25px; float: left; margin-left: 25px">
      <!-- <i
        class="el-icon-success"
        style="margin-right: 6px; :6px ; cursor: pointer"
        @click="this.onsignpaid"
        title="标记已支付"
      ></i> -->
      <i
        class="el-icon-s-order"
        style="margin-right: 6px; cursor: pointer"
        title="修改订单项详情"
        @click="onorderdetails"
      ></i>
      <i
        class="el-icon-document-checked"
        style="margin-right: 6px; cursor: pointer"
        @click="onInfo"
        title="订单项详情"
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
        <el-table-column prop="state" label="支付状态" width="85">
          <template slot-scope="scope">
            <span v-if="scope.row.state === 0"
              ><el-tag type="info">未支付</el-tag></span
            >
            <span v-else-if="scope.row.state === 1"
              ><el-tag type="success">已支付</el-tag></span
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
    <div>
      <!-- <el-dialog
        :close-on-click-modal="false"
        :visible.sync="signPaidDialogVisible"
        :append-to-body="true"
        width="30%"
        ><i slot="title" class="el-icon-warning">警告</i>
        <span>确认订单已支付？标记已支付后将无法进行订单详情信息维护</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="signPaidDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="signPaid">确定</el-button>
        </span>
      </el-dialog> -->
      <el-dialog
        title="订单项详情查看"
        v-if="outerVisible"
        :visible.sync="outerVisible"
        :append-to-body="true"
        :close-on-click-modal="false"
      >
        <OrderDetailsInfo
          @onAdd="onAdd"
          @queryAll="queryAll"
          :multiple="this.multipleSelection"
          :cusNum="this.cusNum"
          :cusName="this.cusName"
          :cusPhone="this.cusPhone"
        ></OrderDetailsInfo>
      </el-dialog>
      <el-dialog
        title="订单项详情管理"
        v-if="outerOrderDetailsVisible"
        :visible.sync="outerOrderDetailsVisible"
        :append-to-body="true"
        :close-on-click-modal="false"
      >
        <OrderDetailsMainten
          @onAdd="onAdd"
          @queryAll="queryAll"
          :multiple="this.multipleSelection"
          :cusNum="this.cusNum"
          :cusName="this.cusName"
          :cusPhone="this.cusPhone"
        ></OrderDetailsMainten>
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
        title="新增订单信息"
        :visible.sync="outerAddVisible"
        :append-to-body="true"
        :close-on-click-modal="false"
      >
        <CusOrderAdd
          v-if="outerAddVisible"
          @onAdd="onAdd"
          @queryAll="queryAll"
          :id="this.id"
        ></CusOrderAdd>
      </el-dialog>
      <el-dialog
        title="修改订单信息"
        v-if="outerUpdateVisible"
        :visible.sync="outerUpdateVisible"
        :append-to-body="true"
        :close-on-click-modal="false"
      >
        <CusOrderUpdate
          @onAdd="onAdd"
          @queryAll="queryAll"
          :multiple="this.multipleSelection"
          :id="this.id"
        ></CusOrderUpdate>
      </el-dialog>
    </div>
  </div>
</template>
<script>
import CusOrderAdd from "../management/CusOrderAdd.vue";
import CusOrderUpdate from "../management/CusOrderUpdate.vue";
import OrderDetailsMainten from "../management/OrderDetailsMainten.vue";
import OrderDetailsInfo from "../basicData/OrderDetailsInfo.vue";

export default {
  props: ["multiple"],
  components: {
    CusOrderAdd,
    CusOrderUpdate,
    OrderDetailsMainten,
    OrderDetailsInfo,
  },
  data() {
    return {
      outerOrderDetailsVisible: false,
      outerUpdateVisible: false,
      outerAddVisible: false,
      // signPaidDialogVisible:false,
      outerVisible: false,
      innerVisible: false,
      loading: false,
      tableData: [],
      currentPage: 1,
      size: 5,
      total: 0,
      tableColumns: [
        { key: "id", name: "编号", width: 50 },
        { key: "orderNum", name: "订单编号", width: 150 },
        { key: "orderDate", name: "下单时间", width: 100 },
        { key: "address", name: "订单地址", width: 260 },
        { key: "orderTotal", name: "总金额/¥", width: 100 },
      ],
      multipleSelection: [],
      dialogVisible: false,
      id: this.$props.multiple[0].id,
      cusNum: this.$props.multiple[0].cusNum,
      cusName: this.$props.multiple[0].cusName,
      cusPhone: this.$props.multiple[0].cusPhone,
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
        cusId: this.$props.multiple[0].id,
      };
      this.$store
        .dispatch("CusOrder/queryAllCusOrderInfo", params)
        .then(() => {
          if (this.$store.state.CusOrder.cusOrderInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.tableData =
              this.$store.state.CusOrder.cusOrderInfo.data.records;

            this.total = this.$store.state.CusOrder.cusOrderInfo.data.total;
            this.currentPage =
              this.$store.state.CusOrder.cusOrderInfo.data.current;
            this.size = this.$store.state.CusOrder.cusOrderInfo.data.size;
          }
          if (this.$store.state.CusOrder.cusOrderInfo.code === 403) {
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
        .dispatch("CusOrder/del", params)
        .then(() => {
          console.log(this.$store.state.CusOrder.deleteInfo);
          if (
            this.$store.state.CusOrder.deleteInfo.code === 200 &&
            this.$store.state.CusOrder.deleteInfo.data === true
          ) {
            this.$message({
              message: "删除操作成功！",
              type: "success",
            });
            this.queryAll();
          } else {
            this.$message.error("执行删除操作失败！");
          }
          if (this.$store.state.CusOrder.deleteInfo.code === 403) {
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
    // signPaid() {
    //   this.setUpdateDate();
    //   const params = {
    //     id: this.multipleSelection[0].id,
    //     updateDate:this.updateDate
    //   };
    //   this.$store
    //     .dispatch("CusOrder/signPaid", params)
    //     .then(() => {
    //       console.log(this.$store.state.CusOrder.signPaidInfo);
    //       if (this.$store.state.CusOrder.signPaidInfo.data === true) {
    //         this.signPaidDialogVisible = false;
    //         this.queryAll();
    //         this.$message({
    //           message: "标记操作成功！",
    //           type: "success",
    //         });
    //       } else {
    //         this.signPaidDialogVisible = false;
    //         this.$message.error("标记操作失败！");
    //         this.queryAll();
    //       }
    //       if (this.$store.state.CusOrder.signPaidInfo.code === 403) {
    //         this.$message({
    //           message: "当前角色无相关权限",
    //           type: "warning",
    //         });
    //         this.signPaidDialogVisible = false;
    //         this.queryAll();
    //         return;
    //       }
    //     })
    //     .catch((e) => {
    //       this.$message.error("标记操作失败！发生错误：" + e);
    //       this.signPaidDialogVisible = false;
    //     });
    // },
    onAdd: function () {
      this.outerAddVisible = false;
      this.outerUpdateVisible = false;
      this.outerOrderDetailsVisible = false;
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
    onInfo: function () {
      if (
        this.multipleSelection === undefined ||
        this.multipleSelection.length === 0
      ) {
        this.$message({
          message: "请选择一条信息查看订单详情",
          type: "warning",
        });
        return;
      }
      if (this.multipleSelection.length > 1) {
        this.$message({
          message: "最多只能选择一条信息查看订单详情",
          type: "warning",
        });
        return;
      }
      if (this.multipleSelection[0].state === 0) {
        this.$message({
          message: "未支付订单无法查看详情",
          type: "warning",
        });
        return;
      }
      this.outerVisible = true;
    },
    onorderdetails: function () {
      if (
        this.multipleSelection === undefined ||
        this.multipleSelection.length === 0
      ) {
        this.$message({
          message: "请选择一条信息执行管理订单详情操作",
          type: "warning",
        });
        return;
      }
      if (this.multipleSelection.length > 1) {
        this.$message({
          message: "最多只能选择一条信息执行管理订单详情操作",
          type: "warning",
        });
        return;
      }
      if (this.multipleSelection[0].state === 1) {
        this.$message({
          message: "已支付订单无法修改详情",
          type: "warning",
        });
        return;
      }
      this.outerOrderDetailsVisible = true;
    },
  },
};
</script>

<style scoped>
.el-form-item {
  float: left;
}
</style>
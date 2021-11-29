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
        <span>确认订单已支付？标记已支付后将无法进行订单详情信息维护</span>
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
        title="新增联系人信息"
        :visible.sync="outerAddVisible"
        :append-to-body="true"
        :close-on-click-modal="false"
      >
        <CusLinkmanAdd
          v-if="outerAddVisible"
          @onAdd="onAdd"
          @queryAll="queryAll"
          :id="this.id"
        ></CusLinkmanAdd>
      </el-dialog>
      <el-dialog
        title="修改联系人信息"
        v-if="outerUpdateVisible"
        :visible.sync="outerUpdateVisible"
        :append-to-body="true"
        :close-on-click-modal="false"
      >
        <CusLinkmanUpdate
          @onAdd="onAdd"
          @queryAll="queryAll"
          :multiple="this.multipleSelection"
          :id="this.id"
        ></CusLinkmanUpdate>
      </el-dialog>
    </div>
  </div>
</template>
<script>
import CusLinkmanAdd from "../management/CusLinkmanAdd.vue";
import CusLinkmanUpdate from "../management/CusLinkmanUpdate.vue";
export default {
  props: ["multiple"],
  components: {
    CusLinkmanAdd,
    CusLinkmanUpdate,
  },
  data() {
    return {
      id:this.$props.multiple[0].id,
      outerUpdateVisible: false,
      outerAddVisible: false,
      signPaidDialogVisible:false,
      outerVisible: false,
      innerVisible: false,
      loading: false,
      tableData: [],
      currentPage: 1,
      size: 5,
      total: 0,
      tableColumns: [
        { key: "id", name: "编号", width: 50 },
        { key: "linkmanName", name: "姓名", width: 80 },
        { key: "position", name: "职位", width: 195 },
        { key: "officePhone", name: "办公电话", width: 150 },
        { key: "phone", name: "手机号码", width: 150 },
      ],
      multipleSelection: [],
      dialogVisible: false,
      cusId: this.$props.multiple[0].id,
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
        .dispatch("CusLinkman/queryAllCusLinkmanInfo", params)
        .then(() => {
          if (this.$store.state.CusLinkman.cusLinkmanInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.tableData =
              this.$store.state.CusLinkman.cusLinkmanInfo.data.records;

            this.total = this.$store.state.CusLinkman.cusLinkmanInfo.data.total;
            this.currentPage =
              this.$store.state.CusLinkman.cusLinkmanInfo.data.current;
            this.size = this.$store.state.CusLinkman.cusLinkmanInfo.data.size;
          }
          if (this.$store.state.CusLinkman.cusLinkmanInfo.code === 403) {
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
        .dispatch("CusLinkman/del", params)
        .then(() => {
          console.log(this.$store.state.CusLinkman.deleteInfo);
          if (
            this.$store.state.CusLinkman.deleteInfo.code === 200 &&
            this.$store.state.CusLinkman.deleteInfo.data === true
          ) {
            this.$message({
              message: "删除操作成功！",
              type: "success",
            });
            this.queryAll();
          } else {
            this.$message.error("执行删除操作失败！");
          }
          if (this.$store.state.CusLinkman.deleteInfo.code === 403) {
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
        contractPerson: this.$props.multiple[0].contractPerson,
        contractPhone: this.$props.multiple[0].contractPhone,
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
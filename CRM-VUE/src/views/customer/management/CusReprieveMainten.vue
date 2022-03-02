<template>
  <div>
    <div>
      <el-descriptions
        class="margin-top"
        title="暂缓流失管理"
        :column="3"
        :size="size"
        border
      >
        <el-descriptions-item>
          <template slot="label"> <i class="el-icon-info"></i>序号</template
          >{{ this.$props.multiple[0].id }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-custom"></i>客户名称</template
          >{{ this.$props.multiple[0].cusName }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"> <i class="el-icon-info"></i>客户编号</template
          >{{ this.$props.multiple[0].cusNum }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"
            ><i class="el-icon-user-solid"></i>客户经理</template
          >{{ this.$props.multiple[0].cusManager }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-date"></i>最后下单时间</template
          >{{ this.$props.multiple[0].lastOrderTime }}
        </el-descriptions-item>
      </el-descriptions>
    </div>
    <div style="font-size: 25px; float: left; margin-left: 25px">
      <!-- <i
        class="el-icon-success"
        style="margin-right: 6px; :6px ; cursor: pointer"
        @click="signSuccessDialogVisible = true"
        title="标记开发成功"
      ></i> -->
      <i
        class="el-icon-error"
        style="margin-right: 6px; :6px ; cursor: pointer"
        @click="signLossDialogVisible = true"
        title="标记确认流失"
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
      <!-- <el-dialog
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
      </el-dialog> -->
      <!-- 标记为确认流失提示 -->
      <el-dialog
        :close-on-click-modal="false"
        :visible.sync="signLossDialogVisible"
        :append-to-body="true"
        width="30%"
        ><i slot="title" class="el-icon-warning">警告</i>
        <span>确认客户已流失？标记为确认流失后将无法进行暂缓流失信息维护</span>
        <el-form
          ref="signForm"
          :model="signForm"
          :rules="signrules"
          size="medium"
          label-width="100px"
        >
          <el-col :span="12">
            <el-form-item label="流失原因" prop="lossReason">
              <el-input
                v-model="signForm.lossReason"
                type="textarea"
                placeholder="请输入流失原因"
                :autosize="{ minRows: 4, maxRows: 4 }"
                :style="{ width: '100%' }"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="signLossDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="signLoss">确定</el-button>
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
        title="新增暂缓流失数据"
        :visible.sync="outerAddVisible"
        :append-to-body="true"
        :close-on-click-modal="false"
      >
        <CusReprieveAdd
          v-if="outerAddVisible"
          @onAdd="onAdd"
          @queryAll="queryAll"
          :id="this.$props.multiple[0].id"
          :modal-append-to-body="true"
        ></CusReprieveAdd>
      </el-dialog>
      <el-dialog
        title="修改计划项数据"
        v-if="outerUpdateVisible"
        :visible.sync="outerUpdateVisible"
        :append-to-body="true"
        :close-on-click-modal="false"
      >
        <CusReprieveUpdate
          @onAdd="onAdd"
          @queryAll="queryAll"
          :multiple="this.multipleSelection"
          :id="this.$props.multiple[0].id"
        ></CusReprieveUpdate>
      </el-dialog>
    </div>
  </div>
</template>
<script>
import CusReprieveAdd from "../management/CusReprieveAdd.vue";
import CusReprieveUpdate from "../management/CusReprieveUpdate.vue";
export default {
  props: ["multiple"],
  components: {
    CusReprieveAdd,
    CusReprieveUpdate,
  },
  data() {
    return {
      signForm: {
        lossReason: "",
      },
      signrules: {
        lossReason: [
          {
            required: true,
            message: "请输入流失原因",
            trigger: "blur",
          },
        ],
      },
      // signSuccessDialogVisible: false,
      signLossDialogVisible: false,
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
        { key: "lossId", name: "流失编号", width: 80 },
        { key: "measure", name: "暂缓措施", width: 345 },
        { key: "createDate", name: "创建日期", width: 100 },
        { key: "updateDate", name: "修改日期", width: 100 },
      ],
      multipleSelection: [],
      dialogVisible: false,
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
        lossId: this.$props.multiple[0].id,
      };
      this.$store
        .dispatch("CusReprieve/queryAllCusReprieveInfo", params)
        .then(() => {
          if (this.$store.state.CusReprieve.cusReprieveInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.tableData =
              this.$store.state.CusReprieve.cusReprieveInfo.data.records;

            this.total =
              this.$store.state.CusReprieve.cusReprieveInfo.data.total;
            this.currentPage =
              this.$store.state.CusReprieve.cusReprieveInfo.data.current;
            this.size = this.$store.state.CusReprieve.cusReprieveInfo.data.size;
          }
          if (this.$store.state.CusReprieve.cusReprieveInfo.code === 403) {
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
        .dispatch("CusReprieve/del", params)
        .then(() => {
          if (
            this.$store.state.CusReprieve.deleteInfo.code === 200 &&
            this.$store.state.CusReprieve.deleteInfo.data === true
          ) {
            this.$message({
              message: "删除操作成功！",
              type: "success",
            });
            this.queryAll();
          } else {
            this.$message.error("执行删除操作失败！");
          }
          if (this.$store.state.CusReprieve.deleteInfo.code === 403) {
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
    signLoss: function() {
      this.setUpdateDate();
      const params = {
        id: this.$props.multiple[0].id,
        lossReason: this.signForm.lossReason,
        state: 1,
        updateDate: this.updateDate,
      };
      this.$refs["signForm"].validate((valid) => {
        if (valid) {
          this.$store
            .dispatch("CusLoss/update", params)
            .then(() => {
              console.log(this.$store.state.CusLoss.updateInfo);
              if (this.$store.state.CusLoss.updateInfo.data === true) {
                this.signLossDialogVisible = false;
                this.$emit("onAdd");
                this.$emit("reInit");
                this.$message({
                  message: "标记操作成功！",
                  type: "success",
                });
              } else {
                this.signLossDialogVisible = false;
                this.$message.error("标记操作失败！");
                this.$emit("onAdd");
                this.$emit("reInit");
              }
              if (this.$store.state.CusLoss.updateInfo.code === 403) {
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
              this.signLossDialogVisible = false;
            });
        } else {
          console.log("error submit!!");
          return false;
        }
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
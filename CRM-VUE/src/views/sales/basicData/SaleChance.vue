<template>
  <div>
    <el-breadcrumb separator="/" class="main-nav">
      <el-breadcrumb-item>首页</el-breadcrumb-item>
      <el-breadcrumb-item>业务管理</el-breadcrumb-item>
      <el-breadcrumb-item>客户中心</el-breadcrumb-item>
      <div style="font-size: 25px; float: right; margin-right: 25px">
        <i
          class="el-icon-document-checked"
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
          @click="onDel"
          title="删除"
        ></i>
      </div>
    </el-breadcrumb>

    <div class="demo-input-size">
      <div class="i-div">
        <label class="i-label">客户名</label>
        <!-- <el-input v-model="customerName" placeholder="请输入客户名"></el-input> -->
        <el-autocomplete
          popper-class="my-autocomplete"
          v-model="customerName"
          value-key="customerName"
          :fetch-suggestions="querySearchCustomerName"
          :trigger-on-focus="false"
          :clearable="false"
          placeholder="请输入客户名"
          @select="handleSelect"
        >
          <!-- <template slot-scope="{ item }">
            <div class="name">{{ item.customerName }}</div>
          </template> -->
        </el-autocomplete>
      </div>

      <div class="i-div">
        <label class="i-label">创建人</label>
        <!-- <el-input v-model="createPerson" placeholder="请输入创建人"></el-input> -->
        <el-autocomplete
          popper-class="my-autocomplete"
          v-model="createPerson"
          :fetch-suggestions="querySearchCreatePerson"
          :trigger-on-focus="false"
          :clearable="false"
          placeholder="请输入创建人"
          @select="handleSelect"
        >
          <template slot-scope="{ item }">
            <div class="name">{{ item.createPerson }}</div>
          </template>
        </el-autocomplete>
      </div>

      <div class="i-div">
        <label class="i-label">分配状态</label>
        <el-select v-model="state" placeholder="请选择分配状态">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </div>
      <div class="i-div" style="margin-top: 10px; margin-right: 10px">
        <label class="i-label" style="margin-right: 10px">创建日期</label>
        <el-date-picker
          v-model="createDate"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          value-format="yyyy-MM-dd"
        >
        </el-date-picker>
      </div>
      <div class="i-div">
        <el-button type="primary" style="margin-top: 9px" @click="this.queryAll"
          >搜索</el-button
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
        height="70vh"
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
        <el-table-column prop="state" label="分配状态" width="80">
          <template slot-scope="scope">
            <span v-if="scope.row.state === 1"
              ><el-tag type="success">已分配</el-tag></span
            >
            <span v-else><el-tag type="warning">未分配</el-tag></span>
          </template>
        </el-table-column>
      </el-table>
      <div class="block">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage"
          :page-sizes="[10, 20, 30]"
          :page-size="size"
          layout="sizes, prev, pager, next"
          :total="total"
        >
        </el-pagination>
      </div>
    </div>
   <!-- 新增 -->
    <el-dialog title="新增营销机会信息" :visible.sync="outerVisible" v-if="outerVisible">
       <SaleChanceAdd @onAdd="onAdd" @getfindAll='queryAll'></SaleChanceAdd>
    </el-dialog>
    <!-- 修改 -->
    <el-dialog title="修改营销机会信息" :visible.sync="outerUpdateVisible">
        <SaleChanceUpdate @onAdd="onAdd" @getfindAll='queryAll' :multiple="this.multipleSelection"></SaleChanceUpdate>
    </el-dialog>

  </div>
</template>

<script>
import SaleChanceAdd from '../management/SaleChanceAdd.vue'
import SaleChanceUpdate from '../management/SaleChanceUpdate.vue'

export default {
  components:{
      SaleChanceAdd,
      SaleChanceUpdate
  },
  data() {
    return {
      restaurants: [],
      options: [
        {
          value: null,
          label: "所有状态",
        },
        {
          value: 1,
          label: "已分配",
        },
        {
          value: 0,
          label: "未分配",
        },
      ],
      value: "",
      tableData: [],
      tableColumns: [
        { key: "id", name: "编号", width: 50 },
        { key: "changeSource", name: "机会来源", width: 80 },
        { key: "customerName", name: "客户名称", width: 100 },
        { key: "probability", name: "成功概率(%)", width: 55 },
        { key: "overview", name: "概述", width: 237.5 },
        { key: "contractPerson", name: "联系人", width: 80 },
        { key: "contractPhone", name: "联系电话", width: 150 },
        { key: "description", name: "机会描述", width: 237.5 },
        { key: "createPerson", name: "创建人", width: 80 },
        { key: "createDate", name: "创建时间", width: 100 },
        { key: "assignPerson", name: "指派人", width: 80 },
        { key: "assigneDate", name: "分配时间", width: 100 },
        // { key: 'state', name: '分配状态', width: 250 },
        // { key: 'devResult', name: '开发状态', width: 250 }
      ],
      loading: true,
      input: "",
      outerVisible: false,
      innerVisible: false,
      outerUpdateVisible: false,
      id: "",
      changeSource: "",
      customerName: "",
      probability: "",
      overview: "",
      contractPerson: "",
      contractPhone: "",
      description: "",
      createPerson: "",
      createDate: [],
      assignPerson: "",
      assigneDate: [],
      state: "",
      devResult: "",
      currentPage: 1,
      size: 5,
      total: 0,
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
      var restaurants = this.restaurants;
      var results = queryString
        ? restaurants.filter(this.createFilterCustomerName(queryString))
        : restaurants;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilterCustomerName(queryString) {
      return (restaurants) => {
        return (
          restaurants.customerName
            .toLowerCase()
            .indexOf(queryString.toLowerCase()) === 0
        );
      };
    },
    querySearchCreatePerson(queryString, cb) {
      var restaurants = this.restaurants;
      var results = queryString
        ? restaurants.filter(this.createFilterCreatePerson(queryString))
        : restaurants;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilterCreatePerson(queryString) {
      return (restaurants) => {
        return (
          restaurants.createPerson
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
    queryAll() {
      this.loading = true;
      const params = {
        current: this.currentPage,
        pageSize: this.size,
        customerName: this.customerName,
        createPerson: this.createPerson,
        state: this.state,
        startTime:
          this.createDate === null || this.createDate.length === 0
            ? null
            : this.createDate[0],
        endTime:
          this.createDate === null || this.createDate.length === 0
            ? null
            : this.createDate[1],
      };
      this.$store
        .dispatch("Sales/queryAll", params)
        .then(() => {
          if (this.$store.state.Sales.salesInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.tableData = this.$store.state.Sales.salesInfo.data.records;

            this.tableData.forEach((e) => {
              const records = {
                //可根据需求更改
                customerName: e.customerName,
                createPerson: e.createPerson,
              };
              this.restaurants.push(records);
            });

            this.total = this.$store.state.Sales.salesInfo.data.total;
            this.currentPage = this.$store.state.Sales.salesInfo.data.current;
            this.size = this.$store.state.Sales.salesInfo.data.size;
          }
        })
        .catch((e) => {
          this.loading = false;
          this.$message.error("发生错误：" + e);
        });
    },
    onDel: function () {
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
              message: this.$store.state.Sales.delInfo.message,
              type: "success",
            });
            this.queryAll();
          } else {
            this.$message.error(this.$store.state.Sales.delInfo.message);
          }
        })
        .catch(() => {});
    },
    onAdd: function () {
      this.outerVisible = false;
      this.outerUpdateVisible = false;
    },
    ondetails: function () {
      if (
        this.multipleSelection === undefined ||
        this.multipleSelection.length === 0
      ) {
        this.$message("请选择一条信息执行修改操作");
        return;
      }
      if (this.multipleSelection.length > 1) {
        this.$message("最多只能选择一条信息执行修改操作");
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

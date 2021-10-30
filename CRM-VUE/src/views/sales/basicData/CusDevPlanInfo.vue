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
              >{{this.$props.multiple[0].customerName}}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-s-opportunity"></i>机会来源</template
              >{{this.$props.multiple[0].chanceSource}}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-s-custom"></i>联系人</template
              >{{this.$props.multiple[0].contractPerson}}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-mobile-phone"></i>联系电话</template
              >{{this.$props.multiple[0].contractPhone}}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-tickets"></i>机会描述</template
              >{{this.$props.multiple[0].description}}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-s-data"></i>成功概率(%)</template
              >{{this.$props.multiple[0].probability}}
            </el-descriptions-item>
          </el-descriptions>
  </div>
   <div style="font-size: 25px; float: right; margin-right: 25px">
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
      >
        <!-- <el-table-column type="selection" width="30"> </el-table-column> -->
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
  props: ["multiple"],
  
  data() {
    return {
      outerVisible: false,
      innerVisible: false,
      loading: false,
      tableData:[],
      currentPage: 1,
      size: 5,
      total: 0,
       tableColumns: [
        { key: "id", name: "编号", width: 50 },
        { key: "saleChanceId", name: "营销机会编号", width: 50 },
        { key: "planItem", name: "计划项", width: 242 },
        { key: "planDate", name: "计划日期", width: 100 },
        { key: "exeAffect", name: "执行效果", width: 242 },
         { key: "createDate", name: "创建日期", width: 100 },
      ],
    };
  },
  created() {
    this.queryAll();
  },
  watch: {
    // multiple: function () {
    //   if (this.$props.multiple.length !== 0) {
    //     this.init();
    //   }
    // },
  },
  methods: {
    queryAll(){
       this.loading = true;
      const params = {
        "current": this.currentPage,
        "pageSize": this.size,
        "saleChanceId": this.$props.multiple[0].id,
      };
      this.$store
        .dispatch("Sales/queryCusDevPlan", params)
        .then(() => {
          if (this.$store.state.Sales.cusDevPlanInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.tableData = this.$store.state.Sales.cusDevPlanInfo.data.records;

            this.total = this.$store.state.Sales.cusDevPlanInfo.data.total;
            this.currentPage = this.$store.state.Sales.cusDevPlanInfo.data.current;
            this.size = this.$store.state.Sales.cusDevPlanInfo.data.size;
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
  }
};
</script>

<style scoped>
.el-form-item {
  float: left;
}
</style>



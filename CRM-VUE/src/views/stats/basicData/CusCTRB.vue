<template>
  <div>
    <el-breadcrumb separator="/" class="main-nav">
      <el-breadcrumb-item>首页</el-breadcrumb-item>
      <el-breadcrumb-item>统计报表</el-breadcrumb-item>
      <el-breadcrumb-item>客户贡献分析</el-breadcrumb-item>
      <div style="font-size: 25px; float: right; margin-right: 25px">
        <i
          class="el-icon-refresh-left"
          style="margin-right: 6px; cursor: pointer"
          title="刷新"
          @click="refresh"
        ></i>
        <i
          class="el-icon-document"
          style="margin-right: 6px; cursor: pointer"
          title="导出为PDF"
        ></i>
      </div>
    </el-breadcrumb>

    <div class="demo-input-size">
      <!-- <div class="i-div">
        <label class="i-label">客户名称</label>
        <el-autocomplete
          popper-class="my-autocomplete"
          v-model="cusName"
          value-key="cusName"
          :fetch-suggestions="querySearchCusName"
          :trigger-on-focus="false"
          :clearable="false"
          placeholder="请输入客户名称"
          @select="handleSelect"
        >
          <template slot-scope="{ item }">
            <div class="name">{{ item.cusName }}</div>
            <span class="num">{{ item.cusNum }}</span>
          </template>
        </el-autocomplete>
      </div> -->

      <div class="i-div">
        <label class="i-label">客户编号</label>
        <el-input v-model="cusNum" placeholder="请输入客户编号"></el-input>
      </div>

      <div class="i-div">
        <label class="i-label">客户名称</label>
        <el-input v-model="cusName" placeholder="请输入客户名称"></el-input>
      </div>

      <div class="i-div">
        <label class="i-label">客户电话</label>
        <el-input v-model="cusPhone" placeholder="请输入客户电话"></el-input>
      </div>

      <div class="i-div">
        <label class="i-label">客户经理</label>
        <el-input v-model="cusManager" placeholder="请输入客户经理"></el-input>
      </div>

      <div class="i-div-r">
        <el-button type="primary" style="margin-top: 9px" @click="this.queryAll"
          >查询</el-button
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
      <br /><br /><br /><br />
      <h1 :style="{ margin: '0 auto' }">客户贡献分析图表</h1>
      <br /><br /><br /><br />
      <div
        id="orderChart"
        :style="{ width: '500px', height: '500px', margin: '0 auto' }"
      ></div>
      <br /><br /><br /><br />
      <div
        id="totalChart"
        :style="{ width: '500px', height: '500px', margin: '0 auto' }"
      ></div>
      <br /><br /><br /><br />
      <div
        id="aovChart"
        :style="{ width: '500px', height: '500px', margin: '0 auto' }"
      ></div>
    </div>
  </div>
</template>
<script>
// 引入基本模板
let echarts = require("echarts/lib/echarts");
// 引入柱状图组件
require("echarts/lib/chart/pie");
// 引入提示框和title组件
require("echarts/lib/component/legend");
require("echarts/lib/component/tooltip");
require("echarts/lib/component/title");

export default {
  data() {
    return {
      orderChart: undefined,
      totalChart: undefined,
      aovChart: undefined,
      tableData: [],
      tableColumns: [
        { key: "id", name: "序号", width: 50 },
        { key: "cusNum", name: "客户编号", width: 200 },
        { key: "cusName", name: "客户名称", width: 465 },
        { key: "cusPhone", name: "客户电话", width: 150 },
        { key: "cusManager", name: "客户经理", width: 100 },
        { key: "ctrb", name: "订单总金额", width: 100 },
        { key: "orderCount", name: "有效订单数", width: 100 },
        { key: "aov", name: "平均订单价值", width: 120 },
      ],
      cusNum: "",
      cusName: "",
      cusPhone: "",
      cusManager: "",
      loading: false,
      currentPage: 1,
      size: 5,
      total: 0,
      multipleSelection: [],
    };
  },
  created() {
    this.queryAll();
  },
  mounted() {
    this.getOrderStatsData();
    this.getTotalStatsData();
    this.getAovStatsData();
    this.drawLine();
  },
  methods: {
    refresh() {
      this.queryAll();
      this.getOrderStatsData();
      this.getTotalStatsData();
      this.getAovStatsData();
      this.drawLine();
    },
    getOrderStatsData() {
      this.$store
        .dispatch("CusCTRB/queryOrderStatsInfo", null)
        .then(() => {
          if (this.$store.state.CusCTRB.orderStatsInfo.code === 200) {
            // this.orderStatsData = this.$store.state.CusCTRB.orderStatsInfo.data;
            this.orderChart.setOption({
              series: [
                {
                  data: this.$store.state.CusCTRB.orderStatsInfo.data,
                },
              ],
            });
          }
          if (this.$store.state.CusCTRB.orderStatsInfo.code === 403) {
            this.$message({
              message: "当前角色无相关权限",
              type: "warning",
            });
            return;
          }
        })
        .catch((e) => {
          this.$message.error("发生错误：" + e);
        });
    },
    getTotalStatsData() {
      this.$store
        .dispatch("CusCTRB/queryTotalStatsInfo", null)
        .then(() => {
          if (this.$store.state.CusCTRB.totalStatsInfo.code === 200) {
            // this.orderStatsData = this.$store.state.CusCTRB.orderStatsInfo.data;
            this.totalChart.setOption({
              series: [
                {
                  data: this.$store.state.CusCTRB.totalStatsInfo.data,
                },
              ],
            });
          }
          if (this.$store.state.CusCTRB.totalStatsInfo.code === 403) {
            this.$message({
              message: "当前角色无相关权限",
              type: "warning",
            });
            return;
          }
        })
        .catch((e) => {
          this.$message.error("发生错误：" + e);
        });
    },
    getAovStatsData() {
      this.$store
        .dispatch("CusCTRB/queryAovStatsInfo", null)
        .then(() => {
          if (this.$store.state.CusCTRB.aovStatsInfo.code === 200) {
            // this.orderStatsData = this.$store.state.CusCTRB.orderStatsInfo.data;
            this.aovChart.setOption({
              series: [
                {
                  data: this.$store.state.CusCTRB.aovStatsInfo.data,
                },
              ],
            });
          }
          if (this.$store.state.CusCTRB.aovStatsInfo.code === 403) {
            this.$message({
              message: "当前角色无相关权限",
              type: "warning",
            });
            return;
          }
        })
        .catch((e) => {
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
    queryAll() {
      this.loading = true;
      const params = {
        cusNum: this.cusNum,
        cusName: this.cusName,
        cusPhone: this.cusPhone,
        cusManager: this.cusManager,
        current: 1,
        pageSize: 5,
      };
      this.$store
        .dispatch("CusCTRB/queryAllCusCTRBInfo", params)
        .then(() => {
          if (this.$store.state.CusCTRB.cusCTRBInfo.code === 200) {
            this.loading = false; //取消加载状态
            this.tableData = this.$store.state.CusCTRB.cusCTRBInfo.data.records;

            this.total = this.$store.state.CusCTRB.cusCTRBInfo.data.total;
            this.currentPage =
              this.$store.state.CusCTRB.cusCTRBInfo.data.current;
            this.size = this.$store.state.CusCTRB.cusCTRBInfo.data.size;
          }
          if (this.$store.state.CusCTRB.cusCTRBInfo.code === 403) {
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
    drawLine() {
      // 基于准备好的dom，初始化echarts实例
      this.orderChart = echarts.init(document.getElementById("orderChart"));
      this.totalChart = echarts.init(document.getElementById("totalChart"));
      this.aovChart = echarts.init(document.getElementById("aovChart"));

      // 绘制图表
      this.orderChart.setOption({
        title: {
          text: "客户有效订单数统计",
          subtext: "单位 单",
          left: "center",
        },
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)",
        },
        legend: {
          orient: "vertical",
          left: "left",
        },
        series: [
          {
            name: "有效订单数",
            type: "pie",
            radius: "50%",
            data: [],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      }),
        // 绘制图表
        this.totalChart.setOption({
          title: {
            text: "客户订单总额统计",
            subtext: "单位 ¥",
            left: "center",
          },
          tooltip: {
            trigger: "item",
            formatter: "{a} <br/>{b} : {c} ({d}%)",
          },
          legend: {
            orient: "vertical",
            left: "left",
          },
          series: [
            {
              name: "订单总额",
              type: "pie",
              radius: "50%",
              data: [],
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: "rgba(0, 0, 0, 0.5)",
                },
              },
            },
          ],
        }),
        this.aovChart.setOption({
          title: {
            text: "客户平均订单价值统计",
            subtext: "单位 ¥/单",
            left: "center",
          },
          tooltip: {
            trigger: "item",
            formatter: "{a} <br/>{b} : {c} ({d}%)",
          },
          legend: {
            orient: "vertical",
            left: "left",
          },
          series: [
            {
              name: "平均订单价值",
              type: "pie",
              radius: "50%",
              data: [],
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: "rgba(0, 0, 0, 0.5)",
                },
              },
            },
          ],
        });
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
， .i-div-r {
  float: right;
  margin-right: 45px;
  margin-bottom: 10px;
}
</style>

<template>
  <div>
    <el-breadcrumb separator="/" class="main-nav">
      <el-breadcrumb-item>首页</el-breadcrumb-item>
      <el-breadcrumb-item>统计报表</el-breadcrumb-item>
      <el-breadcrumb-item>客户构成分析</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="table-div">
      <br /><br /><br /><br />
      <h1 :style="{ margin: '0 auto' }">客户构成分析图表</h1>
      <br /><br /><br /><br />
       <div
        id="consistChart"
        :style="{ width: '500px', height: '500px', margin: '0 auto' }"
      ></div>
    </div>
  </div>
</template>
<script>
let echarts = require("echarts/lib/echarts");
// 引入柱状图组件
require("echarts/lib/chart/pie");
// require("echarts/lib/chart/bar");
// 引入提示框和title组件
require("echarts/lib/component/legend");
require("echarts/lib/component/tooltip");
require("echarts/lib/component/title");

export default {
  data() {
    return {
      consistChart: undefined,
      // tableData: [],
      // tableColumns: [
      //   { key: "id", name: "序号", width: 50 },
      //   { key: "cusNum", name: "客户编号", width: 200 },
      //   { key: "cusName", name: "客户名称", width: 465 },
      //   { key: "cusPhone", name: "客户电话", width: 150 },
      //   { key: "cusManager", name: "客户经理", width: 100 },
      //   { key: "ctrb", name: "订单总金额", width: 100 },
      //   { key: "orderCount", name: "有效订单数", width: 100 },
      //   { key: "aov", name: "平均订单价值", width: 120 },
      // ],
      // cusNum: "",
      // cusName: "",
      // cusPhone: "",
      // cusManager: "",
      // loading: false,
      // currentPage: 1,
      // size: 5,
      // total: 0,
      // multipleSelection: [],
    };
  },
  created() {
    
  },
  mounted() {
    this.getConsistStatsData();
    this.drawLine();
  },
  methods: {
    refresh() {
      this.getConsistStatsData();
      this.drawLine();
    },
    getConsistStatsData() {
      this.$store
        .dispatch("CusConsist/queryCusConsistInfo", null)
        .then(() => {
          if (this.$store.state.CusConsist.cusConsistInfo.code === 200) {
            // this.orderStatsData = this.$store.state.CusCTRB.orderStatsInfo.data;
            this.consistChart.setOption({
              series: [
                {
                  data: this.$store.state.CusConsist.cusConsistInfo.data,
                },
              ],
            });
          }
          if (this.$store.state.CusConsist.cusConsistInfo.code === 403) {
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
    drawLine() {
      // 基于准备好的dom，初始化echarts实例
      this.consistChart = echarts.init(document.getElementById("consistChart"));

      // 绘制图表
      this.consistChart.setOption({
        title: {
            text: "客户构成分析",
            subtext: "单位 位",
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
              name: "客户构成",
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

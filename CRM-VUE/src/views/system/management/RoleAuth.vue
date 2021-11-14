<template>
  <div style="font-size: 25px; float: left; margin-left: 25px">
    <div style="display:inline;font-size:10px">
      <label>当前更改角色 【</label>
        <label style=";color:blue">{{roleName}}</label>
      <label>】 资源授权</label>
    </div>
    <span>
      <el-input
        style="width: 220px"
        placeholder="输入关键字对列表进行过滤"
        v-model="filterText"
        clearable
      >
      </el-input>
      <i
        class="el-icon-refresh-left"
        style="margin-right: 10px; cursor: pointer"
        @click="loadData"
        title="刷新资源列表"
      ></i>
      <el-button @click="resetChecked">清空</el-button>
    </span>
    <el-tree
      :props="defaultProps"
      node-key="id"
      :data="data"
      ref="tree"
      show-checkbox
      @node-click="handleNodeClick"
      default-expand-all
      empty-text="获取资源列表失败"
      :filter-node-method="filterNode"
      highlight-current
    >
    </el-tree>
  </div>
</template>
<script>
export default {
  props: ["multiple"],
  data() {
    return {
      data: {},
      filterText: "",
      defaultProps: {
        label: "label",
        children: "childList",
      },
      roleName:this.$props.multiple[0].roleName
    };
  },
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val);
    },
  },
  created() {
    this.loadData();
  },
  filters: {
    filterText: function (value) {
      if (!value) return true;
      return this.data.label.indexOf(value) !== -1;
    },
  },
  methods: {
    loadData() {
      this.$store
        .dispatch("Module/loadModuleData", null)
        .then(() => {
          if (this.$store.state.Module.treeListInfo.code === 200) {
            this.data = this.$store.state.Module.treeListInfo.data;
            this.$message({
              message: "资源列表加载成功！",
              type: "success",
            });
          }
        })
        .catch((e) => {
          this.$message.error("加载资源列表发生错误：" + e);
        });
    },
    handleNodeClick(data) {
      console.log(data);
    },
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    resetChecked() {
      this.$refs.tree.setCheckedKeys([]);
    },
  },
};
</script>
<style lang="scss">
.el-tree-node__content {
  line-height: 50px;
  .custom-tree-node {
    width: 100%;
    display: block;
    .fl {
      float: left;
      line-height: 31px;
    }
    .fr {
      float: right;
      margin-right: 50px;
    }
  }
}
</style>
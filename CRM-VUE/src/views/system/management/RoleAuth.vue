<template>
  <div style="font-size: 25px; float: left; margin-left: 25px">
    <div style="display: inline; font-size: 10px">
      <label>当前更改角色 【</label>
      <label style="color: blue">{{ roleName }}</label>
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
        @click="loadTreeData"
        title="刷新资源列表"
      ></i>
    </span>
    <el-tree
      :props="defaultProps"
      node-key="id"
      :data="data"
      ref="tree"
      show-checkbox
      default-expand-all
      empty-text="获取资源列表失败"
      :filter-node-method="filterNode"
      highlight-current
    >
    </el-tree>
    <el-button @click="authModules">授权</el-button>
    <el-button @click="resetChecked">清空</el-button>
  </div>
</template>
<script>
export default {
  props: ["multiple"],
  data() {
    return {
      data: [],
      checkedData: [],
      filterText: "",
      defaultProps: {
        label: "label",
        children: "childList",
      },
      id: this.$props.multiple[0].id,
      roleName: this.$props.multiple[0].roleName,
    };
  },
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val);
    },
  },
  created() {
    this.loadCheckedData();
    this.loadTreeData();
  },
  mounted() {
    //
      this.checkedData.forEach((i) => {
        //实现回显节点半选
        let node = this.$refs.tree.getNode(i);
        if (node.isLeaf) {
          //不为叶子节点时不选中
          this.$refs.tree.setChecked(node, true); //通过 keys 设置目前勾选的节点 仅设置叶子节点的选中状态
        }
      });
  },
  filters: {
    filterText: function (value) {
      if (!value) return true;
      return this.data.label.indexOf(value) !== -1;
    },
  },
  methods: {
    loadTreeData() {
      this.$store
        .dispatch("Module/loadModuleData", null)
        .then(() => {
          if (this.$store.state.Module.treeListInfo.code === 200) {
            this.data = this.$store.state.Module.treeListInfo.data;
            this.$message({
              message: "资源列表加载成功！",
              type: "success",
            });
            this.$nextTick(() => {
              //下次 DOM 更新循环结束之后执行延迟回调
              this.checkedData.forEach((i) => {
                //实现回显节点半选
                let node = this.$refs.tree.getNode(i);
                if (node.isLeaf) {
                  //不为叶子节点时不选中
                  this.$refs.tree.setChecked(node, true); //通过 keys 设置目前勾选的节点 仅设置叶子节点的选中状态
                }
              });
            });
          }
        })
        .catch((e) => {
          this.$message.error("加载资源列表发生错误：" + e);
        });
    },
    loadCheckedData() {
      this.$store
        .dispatch("Permission/loadCheckedData", this.id)
        .then(() => {
          if (this.$store.state.Permission.checkedRoleIdInfo.code === 200) {
            this.checkedData =
              this.$store.state.Permission.checkedRoleIdInfo.data;
            this.$message({
              message: "角色授权资源加载成功！",
              type: "success",
            });
          }
        })
        .catch((e) => {
          this.$message.error("加载角色授权资源发生错误：" + e);
        });
    },
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    authModules() {
      if (
        this.$refs.tree.getCheckedKeys() === this.checkedData
      ) {
        this.$message({
          message: "所要提交的角色授权资源信息不可与原信息一致",
          type: "warning",
        });
        return;
      }
      const params = {
        roleId: this.id,
        moduleIds: this.$refs.tree.getCheckedKeys(),
      };
      this.$store
        .dispatch("Permission/authModules", params)
        .then(() => {
          if (this.$store.state.Permission.authModulesInfo.data === true) {
            this.$emit("onAdd");
            this.$message({
              message: "角色授权资源成功！",
              type: "success",
            });
          }
          else{
            this.$message.error("角色授权资源失败！无法查询到相关资源权限码");
          }
          if(this.$store.state.Permission.authModulesInfo.code === 500){
             this.$message.error("角色授权资源失败！无法查询到相关资源权限码");
          }
        })
        .catch((e) => {
          this.$emit("onAdd");
          this.$message.error("角色授权资源发生错误：" + e);
        });
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
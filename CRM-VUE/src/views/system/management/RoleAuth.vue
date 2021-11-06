<template>
	<el-tree
      empty-text="暂无数据"
      :expand-on-click-node="false"
      :props="defaultProps"
      :load="loadNode"
      node-key="id"
      lazy>
      <div class="custom-tree-node" slot-scope="{ node, data }">
        <div class="fl"><i class="el-icon-menu"></i>{{ node.label }}</div>
        <div class="fr">
          <el-button
            type="primary"
            size="mini"
            @click="() => addDialogShow(node, data, 0)">
            新增
          </el-button>
          <el-button
            type="primary"
            plain 
            size="mini"
            @click="() => addDialogShow(node, data, 1)">
            更新
          </el-button>
          <!-- <el-button
            disabled
            type="danger"
            size="mini"
            @click="() => remove(node, data)">
            删除
          </el-button> -->
        </div>
      </div>
    </el-tree>
</template>
<script>
  import { typeList, addtype, updatetype, deltype } from '@/api/baseType'

  export default {
    data() {
      return {
        defaultProps: {
            id: "Id",
            label: 'Name',
            children: 'children'
        },
        //新增相关
        addDialog:false,
        addForm: {},
        addRules:{
          name:[{required: true,message: '请输入基础类型名',trigger: 'blur'}]
        },
        pid: null, // 基础类型得父级id
        flag: null, //操作标志位
        node: {}, // tree 节点对象
        nodedata: {} 
      }
    },
    created(){
    },
    methods: {
      // 加载树结点
      loadNode(node, resolve) {
        // 如果是顶级的父节点
        if (node.level === 0) {
          // 查找顶级对象
          typeList(node.level).then(res => {
            if (res.Data) {
              return resolve(res.Data)
            } else {
              this.$message.error(res.Msg)
            }
          }).catch(() => {
            let data = []
            return resolve(data)
          })
        } else {
          // 根据父节点id找寻下一级的所有节点 
          typeList(node.data.Id).then(res => {
            if (res.Data) {
              return resolve(res.Data)
            } else {
              this.$message.error(res.Msg)
            }
          }).catch(() => {
            let data = []
            return resolve(data)
          })
        }
      },
      remove(node, data) {
        console.log(node, data)
        // const parent = node.parent;
        // const children = parent.data.children || parent.data;
        // const index = children.findIndex(d => d.id === data.id);
        // children.splice(index, 1);
      },
      
      //新增
      addDialogShow(node,data,flag) {
        this.node = node //
        this.nodedata = data
        if(flag === 0) {
          this.addDialog = true
          this.pid = data.Id
          this.flag = 0
        }
        if(flag === 1) {
          this.addDialog = true
          this.pid = data.Id
          this.addForm.name = data.Name
          this.flag = 1
        }
      },
      addSubmit(flag) {
        this.$refs.addForm.validate((valid) => {
          if(valid){
            this.listLoading = true
            let arr = Object.assign({}, this.addForm)
            console.log(flag)
            if(flag === 0) {
              let data = {
                pid: this.pid,
                name: arr.name
              }
              // 新增 api
              addtype(data).then(() => {
                typeList(data.pid).then(res => {
                  let id = res.Data[0].Id 
                  const newChild = { id: id, label: arr.name, children: [] };
                  if (!this.nodedata.children) {
                    this.$set(nodedata, 'children', []);
                  }
                  this.nodedata.children.push(newChild)
                  this.addDialog = false
                  this.$notify.success({
                    message:'新增成功',
                    duration: 2000
                  })
                })
              }).catch(() => {
                this.addDialog = false
              })
            }
            if(flag === 1) {
              let data = {
                name: arr.name
              }
              updatetype(this.pid,data).then(res => {
                this.$set(this.node.data, 'Name',arr.name)
                this.addDialog = false
                this.$notify.success({
                  message:'更新成功',
                  duration: 2000
                })
              }).catch(() => {
                this.addDialog = false
              })
            }
          }
        })
      }
    }
  }
</script>
<style lang="less" scoped>
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
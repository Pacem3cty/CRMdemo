<template>
  <div>
    <el-row :gutter="15">
      <el-form ref="updateForm" :model="updateForm" :rules="rules" size="medium" label-width="100px">
        <el-col :span="12">
          <el-form-item label="编号" prop="id">
            <el-input v-model="updateForm.id" placeholder="请输入编号" readonly :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="姓名" prop="linkmanName">
            <el-input v-model="updateForm.linkmanName" placeholder="请输入姓名" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="性别" prop="sex">
            <el-select v-model="updateForm.sex" placeholder="请选择性别" clearable :style="{width: '100%'}">
              <el-option v-for="(item, index) in sexOptions" :key="index" :label="item.label"
                :value="item.value" :disabled="item.disabled"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="职位" prop="position">
            <el-input v-model="updateForm.position" placeholder="请输入职位" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="办公号码" prop="officePhone">
            <el-input v-model="updateForm.officePhone" placeholder="请输入办公号码" clearable
              :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="手机号码" prop="phone">
            <el-input v-model="updateForm.phone" placeholder="请输入手机号码" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item size="large">
            <el-button type="primary" @click="submitForm">提交</el-button>
            <el-button @click="resetForm">重置</el-button>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
  </div>
</template>
<script>
export default {
  components: {},
  props: ["multiple"],
  data() {
    return {
      updateForm: {
        id: this.$props.multiple[0].id,
        linkmanName: this.$props.multiple[0].linkmanName,
        sex: this.$props.multiple[0].sex,
        position: this.$props.multiple[0].position,
        officePhone: this.$props.multiple[0].officePhone,
        phone: this.$props.multiple[0].phone,
      },
      updateDate:"",
      rules: {
        id: [{
          required: true,
          message: '请输入编号',
          trigger: 'blur'
        }],
        linkmanName: [{
          required: true,
          message: '请输入姓名',
          trigger: 'blur'
        }],
        sex: [{
          required: true,
          message: '请选择性别',
          trigger: 'change'
        }],
        position: [{
          required: true,
          message: '请输入职位',
          trigger: 'blur'
        }],
        officePhone: [{
          required: true,
          message: '请输入办公号码',
          trigger: 'blur'
        }],
        phone: [],
      },
      sexOptions: [{
        "label": "男",
        "value": 0
      }, {
        "label": "女",
        "value": 1
      }],
    }
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {},
  methods: {
      setUpdateDate() {
      let date = new Date();
      this.updateDate =
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
    },
    submitForm() {
      this.setUpdateDate();
      this.$refs["updateForm"].validate((valid) => {
        if (valid) {
          const params = {
            id: this.addForm.id,
            linkmanName: this.addForm.linkmanName,
            sex: this.addForm.sex,
            position: this.addForm.position,
            officePhone: this.addForm.officePhone,
            phone: this.addForm.phone,
            isValid: 0,
            createDate: this.$props.multiple[0].createDate,
            updateDate: this.updateDate,
          };
          this.$store
            .dispatch("Cus/update", params)
            .then(() => {
              if (this.$store.state.Cus.updateInfo.data === true) {
                this.$emit("onAdd");
                this.$emit("reInit");
                this.$message({
                  message: "修改操作成功！",
                  type: "success",
                });
              } else {
                this.$message.error("修改操作失败！");
                this.$emit("onAdd");
                this.$emit("reInit");
                this.resetForm();
              }
              if (this.$store.state.Cus.updateInfo.code === 403) {
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
              this.$message.error("新增操作失败！发生错误：" + e);
              this.resetForm();
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm() {
      this.addForm.linkmanName = this.$props.multiple[0].id;
      this.addForm.sex = this.$props.multiple[0].sex;
      this.addForm.position = this.$props.multiple[0].position;
      this.addForm.officePhone = this.$props.multiple[0].officePhone;
      this.addForm.phone = this.$props.multiple[0].phone;
    },
  }
}

</script>
<style>
</style>

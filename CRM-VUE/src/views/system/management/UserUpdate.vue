<template>
  <div>
    <label style="color: red">用户密码MD5转换</label>
    <el-input
      v-model="md5trans"
      placeholder="如需更改用户密码 请在此输入以进行自动MD5转换"
      show-password
      :style="{ width: '45%' }"
    ></el-input>
    <el-row :gutter="15">
      <el-form
        ref="updateForm"
        :model="updateForm"
        :rules="rules"
        size="medium"
        label-width="100px"
      >
        <el-col :span="12">
          <el-form-item label="用户编号" prop="id">
            <el-input
              v-model="updateForm.id"
              placeholder="请输入用户编号"
              readonly
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="用户名称" prop="userName">
            <el-input
              v-model="updateForm.userName"
              placeholder="请输入用户名称"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="用户密码" prop="userPwd">
            <el-input
              v-model="updateForm.userPwd"
              placeholder="请输入用户密码"
              readonly
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="真实姓名" prop="trueName">
            <el-input
              v-model="updateForm.trueName"
              placeholder="请输入真实姓名"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="邮箱" prop="email">
            <el-input
              v-model="updateForm.email"
              placeholder="请输入邮箱"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="电话" prop="phone">
            <el-input
              v-model="updateForm.phone"
              placeholder="请输入电话"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="用户角色" prop="roleName">
            <el-select
              v-model="updateForm.roleName"
              placeholder="请选择用户角色"
              filterable
              :style="{ width: '100%' }"
            >
              <el-option
                v-for="item in roleNames"
                :key="item.id"
                :label="item.roleName"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="备注" prop="remark">
            <el-input
              v-model="updateForm.remark"
              placeholder="请输入备注"
              clearable
              :style="{ width: '100%' }"
            >
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
      md5trans: "",
      updateForm: {
        id: "",
        userName: "",
        userPwd: "",
        trueName: "",
        email: "",
        phone: "",
        roleName: "",
        remark: "",
      },
      roleNames: [],
      updateDate: "",
      rules: {
        id: [
          {
            required: true,
            message: "请输入编号",
            trigger: "blur",
          },
        ],
        userName: [
          {
            required: true,
            message: "请输入用户名称",
            trigger: "blur",
          },
        ],
        userPwd: [
          {
            required: true,
            message: "请输入用户密码",
            trigger: "blur",
          },
          {
            pattern: /[\w%&',;=?$\x22]+/,
            message: "请输入正确的密码格式！",
            trigger: "blur",
          },
        ],
        trueName: [
          {
            required: true,
            message: "请输入真实姓名",
            trigger: "blur",
          },
          {
            pattern: /[^\d^%&',;./=?~\x22$\x22]+/,
            message: "请输入正确的真实姓名格式！",
            trigger: "blur",
          },
        ],
        email: [
          {
            pattern: /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
            message: "请输入正确的邮箱格式！",
            trigger: "blur",
          },
        ],
        phone: [
          {
            required: true,
            message: "请输入电话",
            trigger: "blur",
          },
          {
            pattern: /^1[0-9]{10}$/,
            message: "请输入正确的手机号码格式！",
            trigger: "blur",
          },
        ],
        roleName: [
          {
            required: true,
            message: "请选择用户角色",
            trigger: "change",
          },
        ],
      },
    };
  },
  computed: {},
  watch: {
    multiple: function () {
      if (this.$props.multiple.length !== 0) {
        this.init();
      }
    },
    md5trans: function () {
      if (this.md5trans === "") {
        this.updateForm.userPwd = this.$props.multiple[0].userPwd;
      } else {
        this.updateForm.userPwd = this.$md5(this.md5trans);
      }
    },
  },
  created() {
    this.listRoleName();
    this.init();
  },
  mounted() {},
  methods: {
    setUpdateDate() {
      let date = new Date();
      this.updateDate =
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
    },
    listRoleName() {
      this.$store
        .dispatch("Role/listRoleNameInfo", null)
        .then(() => {
          if (this.$store.state.Role.roleNameInfo.code === 200) {
            this.roleNames = this.$store.state.Role.roleNameInfo.data;
          } else {
            this.$emit("onAdd");
            this.$emit("reInit");
            this.$message.error("获取用户角色列表失败导致无法执行修改操作！");
          }
          if (this.$store.state.Role.roleNameInfo.code === 403) {
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
          this.$emit("onAdd");
          this.$emit("reInit");
          this.$message.error("获取用户角色列表失败导致无法执行修改操作！" + e);
        });
    },
    init() {
      this.$store
        .dispatch("UserRole/queryRoleId", this.$props.multiple[0].id)
        .then(() => {
          if (this.$store.state.UserRole.roleIdInfo.code === 200) {
            // console.log(this.$store.state.UserRole.roleIdInfo.data);
            this.updateForm.roleName =
              this.$store.state.UserRole.roleIdInfo.data;
          } else {
            this.$message.error("获取用户角色失败导致无法执行修改操作！");
            this.$emit("onAdd");
            this.$emit("reInit");
          }
          if (this.$store.state.UserRole.roleIdInfo.code === 403) {
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
          this.$message.error(
            "获取用户角色失败导致无法执行修改操作！发生错误：" + e
          );
          this.$emit("onAdd");
          this.$emit("reInit");
        });
      (this.updateForm.id = this.$props.multiple[0].id),
        (this.updateForm.userName = this.$props.multiple[0].userName),
        (this.updateForm.userPwd = this.$props.multiple[0].userPwd),
        (this.updateForm.trueName = this.$props.multiple[0].trueName),
        (this.updateForm.email = this.$props.multiple[0].email),
        (this.updateForm.phone = this.$props.multiple[0].phone),
        (this.updateForm.remark = this.$props.multiple[0].remark);
    },
    submitForm() {
      this.setUpdateDate();
      this.$refs["updateForm"].validate((valid) => {
        if (valid) {
          const paramsUser = {
            id: this.updateForm.id,
            userName: this.updateForm.userName,
            userPwd: this.updateForm.userPwd,
            trueName: this.updateForm.trueName,
            email: this.updateForm.email,
            phone: this.updateForm.phone,
            remark: this.updateForm.remark,
            isValid: 0,
            createDate: this.$props.multiple[0].createDate,
            updateDate: this.updateDate,
          };
          const paramsUserRole = {
            id: this.updateForm.id,
            userId: this.updateForm.id,
            roleId: this.updateForm.roleName,
            createDate: this.$props.multiple[0].createDate,
            updateDate: this.updateDate,
          };
          this.$store
            .dispatch("User/update", paramsUser)
            .then(() => {
              if (this.$store.state.User.updateInfo.data === true) {
                this.$store
                  .dispatch("UserRole/update", paramsUserRole)
                  .then(() => {
                    if (this.$store.state.UserRole.updateInfo.data === true) {
                      this.$emit("onAdd");
                      this.$emit("reInit");
                      this.$message({
                        message: "修改操作成功！",
                        type: "success",
                      });
                    } else {
                      this.$message.error("分配用户角色失败！");
                      this.resetForm();
                    }
                    if (this.$store.state.UserRole.updateInfo.code === 403) {
                      this.$message({
                        message: "当前角色无相关权限",
                        type: "warning",
                      });
                      this.$emit("onAdd");
                      this.$emit("reInit");
                      return;
                    }
                  });
              } else {
                this.$message.error("修改操作失败！");
                this.resetForm();
              }
            })
            .catch((e) => {
              this.$message.error("修改操作失败！发生错误：" + e);
              this.resetForm();
            });
        } else {
          console.log("error submit!");
          return false;
        }
      });
    },
    resetForm() {
      this.updateForm.userName = this.$props.multiple[0].userName;
      this.updateForm.userPwd = this.$props.multiple[0].userPwd;
      this.updateForm.trueName = this.$props.multiple[0].trueName;
      this.updateForm.email = this.$props.multiple[0].email;
      this.updateForm.phone = this.$props.multiple[0].phone;
      this.updateForm.roleName = this.$store.state.UserRole.roleIdInfo.data;
      this.updateForm.remark = this.$props.multiple[0].remark;
    },
  },
};
</script>
<style>
</style>


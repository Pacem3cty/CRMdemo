<template>
  <div>
    <el-row :gutter="15">
      <el-form
        ref="addForm"
        :model="addForm"
        :rules="rules"
        size="medium"
        label-width="100px"
      >
        <el-col :span="12">
          <el-form-item label="用户编号" prop="id">
            <el-input
              v-model="addForm.id"
              placeholder="请输入用户编号"
              readonly
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="用户名称" prop="userName">
            <el-input
              v-model="addForm.userName"
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
              v-model="addForm.userPwd"
              placeholder="请输入用户密码"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="真实姓名" prop="trueName">
            <el-input
              v-model="addForm.trueName"
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
              v-model="addForm.email"
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
              v-model="addForm.phone"
              placeholder="请输入电话"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="用户角色" prop="roleName">
            <el-select
              v-model="addForm.roleName"
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
              v-model="addForm.remark"
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
  props: [],
  data() {
    return {
      addForm: {
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
      createDate: "",
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
  watch: {},
  created() {
    this.init();
    this.listRoleName();
  },
  mounted() {},
  methods: {
    init() {
      this.$store
        .dispatch("User/getCurrentId", null) //从API中匹配相应的路径发送请求以及请求参数
        .then(() => {
          if (this.$store.state.User.currentIdInfo.code === 200) {
            //状态码为200 即请求成功
            this.addForm.id = this.$store.state.User.currentIdInfo.data;
          } else {
            this.$emit("onAdd");
            this.$emit("reInit");
            this.$message.error("获取编号失败导致无法执行添加操作！");
          }
          if (this.$store.state.User.currentIdInfo.code === 403) {
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
          this.$message.error(
            "获取编号失败导致无法执行添加操作！发生错误：" + e
          );
        });
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
            this.$message.error("获取用户角色列表失败导致无法执行添加操作！");
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
          this.$message.error(
            "获取用户角色列表失败导致无法执行添加操作！发生错误：" + e
          );
        });
    },
    submitForm() {
      let date = new Date();
      this.createDate =
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
      this.$refs["addForm"].validate((valid) => {
        if (valid) {
          const paramsUser = {
            id: this.addForm.id,
            userName: this.addForm.userName,
            userPwd: this.$md5(this.addForm.userPwd),
            trueName: this.addForm.trueName,
            email: this.addForm.email,
            phone: this.addForm.phone,
            remark: this.addForm.remark,
            isValid: 0,
            createDate: this.createDate,
            updateDate: "",
          };
          const paramsUserRole = {
            id: this.addForm.id,
            userId: this.addForm.id,
            roleId: this.addForm.roleName,
            createDate: this.createDate,
            updateDate: "",
          };
          this.$store
            .dispatch("User/add", paramsUser)
            .then(() => {
              if (this.$store.state.User.addInfo.data === true) {
                this.$store
                  .dispatch("UserRole/add", paramsUserRole)
                  .then(() => {
                    if (this.$store.state.UserRole.addInfo.data === true) {
                      this.$emit("onAdd");
                      this.$emit("reInit");
                      this.$message({
                        message: "新增操作成功！",
                        type: "success",
                      });
                    } else {
                      this.$message.error("分配用户角色失败！");
                      this.$refs["addForm"].resetFields();
                    }
                  });
              } else {
                this.$message.error("新增操作失败！");
                this.resetForm();
              }
              if (this.$store.state.UserRole.addInfo.code === 403) {
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
          console.log("error submit!");
          return false;
        }
      });
    },
    resetForm() {
      this.addForm.userName = "";
      this.addForm.userPwd = "";
      this.addForm.trueName = "";
      this.addForm.email = "";
      this.addForm.phone = "";
      this.addForm.roleName = "";
      this.addForm.remark = "";
    },
  },
};
</script>
<style>
</style>

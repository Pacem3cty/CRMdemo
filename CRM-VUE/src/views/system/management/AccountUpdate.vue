<template>
  <div>
    <el-breadcrumb separator="/" class="main-nav">
      <el-breadcrumb-item>首页</el-breadcrumb-item>
      <el-breadcrumb-item>账号设置</el-breadcrumb-item>
      <el-breadcrumb-item>{{ activeName }}</el-breadcrumb-item>
      <div style="font-size: 25px; float: right; margin-right: 25px"></div>
    </el-breadcrumb>
    <el-tabs type="border-card" v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="修改资料" name="修改资料">
        <span slot="label"><i class="el-icon-user-solid"></i>修改资料</span>
        <div>
          <el-descriptions
            class="margin-top"
            title="个人资料"
            :column="3"
            :size="size"
            border
          >
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-user"></i>用户编号</template
              >{{ userInfoList.id }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-user"></i>用户名称</template
              >{{ userInfoList.userName }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-user"></i>真实姓名</template
              >{{ userInfoList.trueName }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-mobile-phone"></i>联系方式 </template
              >{{ userInfoList.phone }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-paperclip"></i>邮箱地址 </template
              >{{ userInfoList.email }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-tickets"></i>备注 </template
              >{{ userInfoList.remark }}
            </el-descriptions-item>
          </el-descriptions>
          <h5 style="color: red">
            *真实姓名为重要信息，若有更改需要请联系系统管理员！
          </h5>

          <el-row :gutter="15">
            <el-form
              ref="infoformData"
              :model="infoformData"
              :rules="formrules"
              size="medium"
              label-width="100px"
            >
              <el-col :span="12">
                <el-form-item label="用户编号" prop="id">
                  <el-input
                    v-model="infoformData.id"
                    placeholder="请输入用户编号"
                    readonly
                    :style="{ width: '100%' }"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="用户名称" prop="userName">
                  <el-input
                    v-model="infoformData.userName"
                    placeholder="请输入用户名称"
                    clearable
                    :style="{ width: '100%' }"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="真实姓名" prop="trueName">
                  <el-input
                    v-model="infoformData.trueName"
                    placeholder="请输入真实姓名"
                    readonly
                    :style="{ width: '100%' }"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="联系方式" prop="phone">
                  <el-input
                    v-model="infoformData.phone"
                    placeholder="请输入联系方式"
                    clearable
                    :style="{ width: '100%' }"
                  >
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="邮箱地址" prop="email">
                  <el-input
                    v-model="infoformData.email"
                    placeholder="请输入邮箱地址"
                    clearable
                    :style="{ width: '100%' }"
                  >
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="备注" prop="remark">
                  <el-input
                    v-model="infoformData.remark"
                    placeholder="请输入备注"
                    :maxlength="125"
                    clearable
                    :style="{ width: '100%' }"
                  >
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item size="large">
                  <el-button type="primary" @click="submitUpdateInfoForm"
                    >修改</el-button
                  >
                </el-form-item>
              </el-col>
            </el-form>
          </el-row>
        </div>
      </el-tab-pane>

      <el-tab-pane label="修改密码" name="修改密码">
        <span slot="label"><i class="el-icon-edit"></i>修改密码</span>
        <div>
          <el-row :gutter="15">
            <el-form
              ref="formData"
              :model="formData"
              :rules="pwdrules"
              size="medium"
              label-width="100px"
            >
              <el-col :span="12">
                <el-form-item label="原密码" prop="ogpwd">
                  <el-input
                    v-model="formData.ogpwd"
                    placeholder="请输入原密码"
                    clearable
                    show-password
                    :style="{ width: '100%' }"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="新密码" prop="newpwd">
                  <el-input
                    v-model="formData.newpwd"
                    placeholder="请输入新密码"
                    clearable
                    show-password
                    :style="{ width: '100%' }"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="确认密码" prop="repwd">
                  <el-input
                    v-model="formData.repwd"
                    placeholder="请输入确认密码"
                    clearable
                    show-password
                    :style="{ width: '100%' }"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item size="large">
                  <el-button type="primary" @click="submitUpdatePwdForm"
                    >修改</el-button
                  >
                  <el-button @click="resetForm">重置</el-button>
                </el-form-item>
              </el-col>
            </el-form>
          </el-row>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
export default {
  data() {
    return {
      userInfoList: [],
      infoformData: {
        id: "",
        userName: "",
        trueName: "",
        phone: "",
        email: "",
        remark: "",
      },
      formrules: {
        id: [
          {
            required: true,
            message: "请输入用户编号",
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
        trueName: [
          {
            required: true,
            message: "请输入真实姓名",
            trigger: "blur",
          },
        ],
        phone: [
          { required: true, message: "请输入联系方式", trigger: "blur" },
          {
            pattern: /^1[0-9]{10}$/,
            message: "请输入正确的手机号码格式！",
            trigger: "blur",
          },
        ],
        email: [
          {
            pattern:
              /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,})$/,
            message: "请输入正确的邮箱格式！",
            trigger: "blur",
          },
        ],
      },
      formData: {
        ogpwd: "",
        newpwd: "",
        repwd: "",
      },
      pwdrules: {
        ogpwd: [
          {
            required: true,
            message: "请输入原密码",
            trigger: "blur",
          },
        ],
        newpwd: [
          {
            required: true,
            message: "请输入新密码",
            trigger: "blur",
          },
          {
            validator: (rule, value, callback) => {
              if (value === this.formData.ogpwd) {
                callback(new Error("新密码不可与原密码一致"));
              } else {
                callback();
              }
            },
            trigger: "blur",
          },
        ],
        repwd: [
          {
            required: true,
            message: "请输入确认密码",
            trigger: "blur",
          },
          {
            validator: (rule, value, callback) => {
              if (value !== this.formData.newpwd) {
                callback(new Error("两次输入密码不一致"));
              } else {
                callback();
              }
            },
            trigger: "blur",
          },
        ],
      },
      activeName: "修改资料",
    };
  },
  computed: {},
  watch: {},
  created() {
    this.getUserList();
  },
  mounted() {},
  methods: {
    getUserList() {
      this.$store
        .dispatch("User/listUserInfo", localStorage.getItem("id"))
        .then(() => {
          if (this.$store.state.User.userInfo.code === 200) {
            this.userInfoList = this.$store.state.User.userInfo.data;
            this.infoformData.id = this.userInfoList.id;
            this.infoformData.userName = this.userInfoList.userName;
            this.infoformData.trueName = this.userInfoList.trueName;
            this.infoformData.phone = this.userInfoList.phone;
            this.infoformData.email = this.userInfoList.email;
            this.infoformData.remark = this.userInfoList.remark;
          }
        })
        .catch(() => {});
    },
    submitUpdateInfoForm() {
      const params = {
        id: this.infoformData.id,
        userName: this.infoformData.userName,
        trueName: this.infoformData.trueName,
        phone: this.infoformData.phone,
        email: this.infoformData.email,
        remark: this.infoformData.remark,
      };
      this.$refs["infoformData"].validate((valid) => {
        if (valid) {
          this.$store
            .dispatch("User/updateUserInfo", params)
            .then(() => {
              if (this.$store.state.User.updateInfo.data === 0) {
                this.$message({
                  message: "修改资料成功！",
                  type: "success",
                });
                this.getUserList();
              } else if (this.$store.state.User.updateInfo.data === 1) {
                this.$message.error("无效账号！将自动退出到登录页！");
                this.$router.push("/");
                // this.$route.push({ path: this.redirect || '/' , query: this.otherQuery})
                // this.loading = false
              } else {
                this.$message.error("修改资料失败！");
              }
            })
            .catch((e) => {
              this.$message.error("发生错误：" + e);
            });
        } else {
          return false;
        }
      });
    },
    submitUpdatePwdForm() {
      const params = {
        userName: localStorage.getItem("userName"),
        userPwd: this.formData.ogpwd,
        newPwd: this.formData.newpwd,
      };
      this.$refs["formData"].validate((valid) => {
        if (valid) {
          
          this.$store
            .dispatch("User/updatePwd", params)
            .then(() => {
              if (this.$store.state.User.updatePwdInfo.data === 0) {
                this.$message({
                  message: "修改密码成功！",
                  type: "success",
                });
                this.resetForm();
              } else if (this.$store.state.User.updatePwdInfo.data === 1) {
                this.$message.error("无效账号！将自动退出到登录页！");
                this.$router.push("/");
              } else if (this.$store.state.User.updatePwdInfo.data === 2) {
                this.$message.error("原密码错误！");
                this.$router.push("/");
              } else {
                this.$message.error("修改密码失败！");
              }
            })
            .catch((e) => {
              this.$message.error("发生错误：" + e);
              // this.loading = false
            });
        } else {
          return false;
        }
      });
    },
    resetForm() {
      this.$refs["formData"].resetFields();
    },
    handleClick(tab, event) {
      console.log(tab, event);
    },
  },
};
</script>
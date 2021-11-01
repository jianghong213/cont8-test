<template>
  <div>
    <!--Layout布局-->
    <el-row>
      <el-col :span="24">
        <el-row :gutter="20">
          <el-col :span="6">
            <!--搜索区域-->
            <el-input
              placeholder="Input"
              v-model="queryInfo.keyword"
              clearable
              @clear="getUserList"
            >
              <el-button
                slot="append"
                icon="el-icon-search"
                @click="getUserList"
              ></el-button>
            </el-input>
          </el-col>
          <el-col :span="2.5">
            <el-button type="primary" @click="addDialogVisible = true"
              >Add user</el-button
            >
          </el-col>
          <el-col :span="2.5">
            <el-button type="danger" @click="batchDeleteUser"
              >Delete user</el-button
            >
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="24">
        <!--表格-->
        <el-table
          :data="userList"
          border
          stripe
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" width="55"> </el-table-column>
          <el-table-column prop="id" label="ID"></el-table-column>
          <el-table-column prop="userName" label="User Name"></el-table-column>
          <el-table-column prop="name" label="Name"></el-table-column>
          <!-- <el-table-column prop="sex" label="Gender"></el-table-column> -->
          <el-table-column prop="email" label="E-Mail"></el-table-column>
          <el-table-column prop="password" label="Password"></el-table-column>
          <!-- <el-table-column prop="address" label="Address"></el-table-column> -->
          <el-table-column label="Operation">
            <!-- 作用域插槽 -->
            <template slot-scope="scope">
              <!--修改按钮-->
              <el-button
                type="primary"
                size="mini"
                icon="el-icon-edit"
                @click="showEditDialog(scope.row)"
              ></el-button>
              <!--删除按钮-->
              <el-button
                type="danger"
                size="mini"
                icon="el-icon-delete"
                @click="removeUserById(scope.row.id)"
              ></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <el-row>
      <!--分页区域-->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pageNo"
        :page-sizes="[1, 2, 5, 10]"
        :page-size="queryInfo.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </el-row>
    <!--添加用户的对话框-->
    <el-dialog
      title="Add User"
      :visible.sync="addDialogVisible"
      width="30%"
      @close="addDialogClosed"
    >
      <!--内容主体区域-->
      <el-form :model="userForm" label-width="90px">
        <el-form-item label="Login Name" prop="loginName">
          <el-input v-model="userForm.loginName"></el-input>
        </el-form-item>
        <el-form-item label="User Name" prop="userName">
          <el-input v-model="userForm.userName"></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="password">
          <el-input v-model="userForm.password" show-password></el-input>
        </el-form-item>       
        <el-form-item label="Email" prop="email">
          <el-input v-model="userForm.email"></el-input>
        </el-form-item>
      </el-form>
      <!--底部按钮区域-->
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="addUser">Confirm</el-button>
      </span>
    </el-dialog>
    <!--修改用户的对话框-->
    <el-dialog title="Edit User Info" :visible.sync="editDialogVisible" width="30%">
      <!--内容主体区域-->
      <el-form :model="editForm" label-width="100px">
        <el-form-item label="User Name" prop="userName">
          <el-input v-model="editForm.userName"></el-input>
        </el-form-item>
        <el-form-item label="Email" prop="email">
          <el-input v-model="editForm.email"></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="password">
          <el-input v-model="editForm.password"></el-input>
        </el-form-item>
      </el-form>
      <!--底部按钮区域-->
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="editUser">Confirm</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { userList, userAdd, userUpdate, userDelete, userBatchDelete} from "@/api/user";
export default {
  data() {
    return {
      userList: [], // 用户列表
      total: 0, // 用户总数
      // 获取用户列表的参数对象
      queryInfo: {
        keyword: "", // 查询参数
        pageNo: 1, // 当前页码
        pageSize: 5, // 每页显示条数
      },
      addDialogVisible: false, // 控制添加用户对话框是否显示
      userForm: {
        //用户
        loginName: "",
        userName: "",
        password: "",
        sex: "",
        email: "",
        address: "",
      },
      editDialogVisible: false, // 控制修改用户信息对话框是否显示
      editForm: {
        //id: "",
        loginName: "",
        userName: "",
        password: "",
        //sex: "",
        email: "",
        //address: "",
      },
      multipleSelection: [],
      ids: [],
    };
  },
  created() {
    // 生命周期函数
    this.getUserList();
  },
  methods: {
    getUserList() {
      userList(this.queryInfo)
        .then((res) => {
          if (res.data.code === "200") {
            //用户列表
            this.userList = res.data.data;
            this.total = res.data.data.total;
            console.log(res.data.data);
            //console.log(res.data.total);
          } else {
            this.$message.error(res.data.message);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 监听 pageSize 改变的事件
    handleSizeChange(newSize) {
      // console.log(newSize)
      this.queryInfo.pageSize = newSize;
      // 重新发起请求用户列表
      this.getUserList();
    },
    // 监听 当前页码值 改变的事件
    handleCurrentChange(newPage) {
      // console.log(newPage)
      this.queryInfo.pageNo = newPage;
      // 重新发起请求用户列表
      this.getUserList();
    },
    //添加用户
    addUser() {
      userAdd(this.userForm)
        .then((res) => {
          if (res.data.code === "200") {
            this.addDialogVisible = false;
            this.getUserList();
            this.$message({
              message: "Success",
              type: "success",
            });
          } else {
            this.$message.error("User Existed");
          }
        })
        .catch((err) => {
          this.$message.error("Error");
          console.log(err);
        });
    },

    // 监听 添加用户对话框的关闭事件
    addDialogClosed() {
      // 表单内容重置为空
      this.$refs.addFormRef.resetFields();
    },

    // 监听 修改用户状态
    showEditDialog(userinfo) {
      this.editDialogVisible = true;
      console.log(userinfo);
      this.editForm = userinfo;
    },
    //修改用户
    editUser() {
      userUpdate(this.editForm)
        .then((res) => {
          if (res.data.code === "200") {
            this.editDialogVisible = false;
            this.getUserList();
            this.$message({
              message: "Success",
              type: "success",
            });
          } else {
            this.$message.error("Failed");
          }
        })
        .catch((err) => {
          this.$message.error("Error");
          console.loge(err);
        });
    },
    // 根据ID删除对应的用户信息
    async removeUserById(id) {
      // 弹框 询问用户是否删除
      const confirmResult = await this.$confirm(
        "Confirm?",
        "Alert",
        {
          confirmButtonText: "Confirm",
          cancelButtonText: "Cancel",
          type: "warning",
        }
      ).catch((err) => err);
      // 如果用户确认删除，则返回值为字符串 confirm
      // 如果用户取消删除，则返回值为字符串 cancel
      // console.log(confirmResult)
      if (confirmResult == "confirm") {
        //删除用户
        userDelete(id)
          .then((res) => {
            if (res.data.code === "200") {
              this.getUserList();
              this.$message({
                message: "Success",
                type: "success",
              });
            } else {
              this.$message.error("Failed");
            }
          })
          .catch((err) => {
            this.$message.error("Error");
            console.log(err);
          });
      }
    },
    //批量选中事件处理
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(this.multipleSelection);
      //向被删除的ids赋值
      this.multipleSelection.forEach((item) => {
        this.ids.push(item.id);
        console.log(this.ids);
      });
    },
    //批量删除用户
    async batchDeleteUser(){
     // 弹框 询问用户是否删除
      const confirmResult = await this.$confirm(
        "Confirm?",
        "Alert",
        {
          confirmButtonText: "Confirm",
          cancelButtonText: "Cancel",
          type: "warning",
        }
      ).catch((err) => err);
      // 如果用户确认删除，则返回值为字符串 confirm
      // 如果用户取消删除，则返回值为字符串 cancel
      if (confirmResult == "confirm") {
        //批量删除用户
        userBatchDelete(this.ids)
          .then((res) => {
            if (res.data.code === 200) {
              this.$message({
                message: "Success",
                type: "success",
              });
              this.getUserList();
            } else {
              this.$message.error("Failed");
            }
          })
          .catch((err) => {
            this.$message.error("Error");
            console.log(err);
          });
      }
    }
  },
};
</script>

<style>
.el-row {
  margin-bottom: 20px;
}
.el-col {
  border-radius: 4px;
}
.el-card {
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1) !important;
  height: 60pt;
}
</style>

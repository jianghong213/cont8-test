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
              @clear="getbookList"
            >
              <el-button
                slot="append"
                icon="el-icon-search"
                @click="getbookList"
              ></el-button>
            </el-input>
          </el-col>
          <el-col :span="2.5">
            <el-button type="primary" @click="addDialogVisible = true"
              >Add Book</el-button
            >
          </el-col>
          <el-col :span="2.5">
            <el-button type="danger" @click="batchDeleteUser"
              >Delete Book</el-button
            >
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="24">
        <!--表格-->
        <el-table
          :data="bookList"
          border
          stripe
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" width="55"> </el-table-column>
          <el-table-column prop="id" label="ID"></el-table-column>
          <!-- <el-table-column prop="Name" label="User Name"></el-table-column> -->
          <el-table-column prop="bookName" label="Name"></el-table-column>
          <!-- <el-table-column prop="sex" label="Gender"></el-table-column> -->
          <el-table-column prop="date" label="Date"></el-table-column>
          <el-table-column prop="type" label="Type"></el-table-column>
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
                @click="removebookById(scope.row.id)"
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
      title="Add Book"
      :visible.sync="addDialogVisible"
      width="30%"
      @close="addDialogClosed"
    >
      <!--内容主体区域-->
      <el-form :model="userForm" label-width="100px">
        <el-form-item label="Book Name" prop="bookName">
          <el-input v-model="userForm.bookName"></el-input>
        </el-form-item>
        <el-form-item label="Date" prop="date">
          <el-input v-model="userForm.date"></el-input>
        </el-form-item>
        <el-form-item label="Type" prop="type">
          <el-input v-model="userForm.type" show-password></el-input>
        </el-form-item>       
      </el-form>
      <!--底部按钮区域-->
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="addBook">Confirm</el-button>
      </span>
    </el-dialog>
    <!--修改用户的对话框-->
    <el-dialog title="Edit Book Info" :visible.sync="editDialogVisible" width="30%">
      <!--内容主体区域-->
      <el-form :model="editForm" label-width="70px">
        <el-form-item label="Book Name" prop="bookName">
          <el-input v-model="editForm.bookName"></el-input>
        </el-form-item>
        <el-form-item label="Date" prop="date">
          <el-input v-model="editForm.date"></el-input>
        </el-form-item>
        <el-form-item label="Type" prop="type">
          <el-input v-model="editForm.type"></el-input>
        </el-form-item>
      </el-form>
      <!--底部按钮区域-->
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="editBook">Confirm</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { bookList, bookAdd, bookUpdate, bookDelete, userBatchDelete} from "@/api/book";
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
        bookName: "",
        date: "",
        type: "",
        // sex: "",
        // email: "",
        //address: "",
      },
      editDialogVisible: false, // 控制修改用户信息对话框是否显示
      
      editForm: {
        bookName: "",
        date: "",
        type: "",
      },
      multipleSelection: [],
      ids: [],
    };
  },
  created() {
    // 生命周期函数
    this.getbookList();
  },
  methods: {
    getbookList() {
      bookList(this.queryInfo)
        .then((res) => {
          if (res.data.code === "200") {
            //用户列表
            this.bookList = res.data.data;
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
      this.getbookList();
    },
    // 监听 当前页码值 改变的事件
    handleCurrentChange(newPage) {
      // console.log(newPage)
      this.queryInfo.pageNo = newPage;
      // 重新发起请求用户列表
      this.getbookList();
    },
    //添加用户
    addBook() {
      bookAdd(this.userForm)
        .then((res) => {
          if (res.data.code === "200") {
            this.addDialogVisible = false;
            this.getbookList();
            this.$message({
              message: "Success",
              type: "success",
            });
          } else {
            this.$message.error("Book Existed");
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
    editBook() {
      bookUpdate(this.editForm)
        .then((res) => {
          if (res.data.code === "200") {
            this.editDialogVisible = false;
            this.getbookList();
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
    async removebookById(id) {
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

      if (confirmResult == "confirm") {
        //删除用户
        bookDelete(id)
          .then((res) => {
            if (res.data.code === "200") {
              this.getbookList();
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
              this.getbookList();
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

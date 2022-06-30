<template>
  <div class="body2">
    <el-card class="box-card">
      <div class="clearfix">
        <el-button
          style="float: right; padding: 3px 0;font-size:20px"
          type="text"
          @click="dialogVisible = true"
          >添加用户</el-button
        >
        <h2 class="search">
          用户列表<el-input
            v-model="search"
            placeholder="请输入您要查找的用户信息"
          >
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="find()"
            ></el-button
          ></el-input>
        </h2>
      </div>
      <el-table :data="showData" stripe style="width: 100%">
        <el-table-column
          prop="userId"
          label="用户编号"
          width="180"
        ></el-table-column>
        <el-table-column prop="userName" label="用户名"> </el-table-column>
        <el-table-column prop="userPassword" label="用户密码" >
        </el-table-column>
        <el-table-column prop="userAccount" label="用户账号"> </el-table-column>
        <el-table-column prop="userLoc" label="用户所在地"> </el-table-column>
        <el-table-column prop="userEmail" label="用户邮箱"> </el-table-column>
        <el-table-column prop="userPhone" label="用户手机"> </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
              >编辑</el-button
            >
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <div class="block">
        <el-pagination
          @current-change="changeCurrent"  
          :current-page="currentPage"
          :page-sizes="[5]"
          :page-size="5"
          layout="total, sizes, prev, pager, next, jumper"
          :total="this.length"
          @next-click="next()"
          @prev-click="prev()"
        >
        </el-pagination>
      </div>
    </el-card>
    <el-dialog
      title="添加用户"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose"
    >
        <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="用户id">
          <el-input v-model="form.userid"></el-input>
        </el-form-item>
        </el-form-item>
        <el-form-item label="用户账号">
          <el-input v-model="form.userAccount"></el-input>
        </el-form-item>
        <el-form-item label="用户密码">
          <el-input v-model="form.userPassword"></el-input>
        </el-form-item>
        <el-form-item label="用户地址">
          <el-input v-model="form.userLoc"></el-input>
        </el-form-item>
        <el-form-item label="用户邮箱">
          <el-input v-model="form.userEmail"></el-input>
        </el-form-item>
          <el-form-item label="用户名">
          <el-input v-model="form.userName"></el-input>
        </el-form-item>
        </el-form-item>
          <el-form-item label="用户手机">
          <el-input v-model="form.userPhone"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="close(1)">取 消</el-button>
        <el-button type="primary" @click="postData()">确 定</el-button>
      </span>
    </el-dialog>
     <el-dialog
      title="修改用户"
      :visible.sync="dialogVisible2"
      width="30%"
      :before-close="handleClose"
    >
       <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="用户id">
          <el-input v-model="form.userId"></el-input>
        </el-form-item>
        </el-form-item>
        <el-form-item label="用户账号">
          <el-input v-model="form.userAccount"></el-input>
        </el-form-item>
        <el-form-item label="用户密码">
          <el-input v-model="form.userPassword"></el-input>
        </el-form-item>
        <el-form-item label="用户地址">
          <el-input v-model="form.userLoc"></el-input>
        </el-form-item>
        <el-form-item label="用户邮箱">
          <el-input v-model="form.userEmail"></el-input>
        </el-form-item>
          <el-form-item label="用户名">
          <el-input v-model="form.userName"></el-input>
        </el-form-item>
        </el-form-item>
          <el-form-item label="用户手机">
          <el-input v-model="form.userPhone"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="close(2)">取 消</el-button>
        <el-button type="primary" @click="putData()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "User",
  data() {
    return {
      search: "",
      dialogVisible: false,
      dialogVisible2: false,
      length: 0,
      showData: [],
      currentPage: 1,
      carData: [],
      price: "",
      user: [],
      carid: "",
      form: {
        userId:"",
        userAccount:"",
        userPassword:"",
        userLoc:"",
        userEmail:"",
        userName:"",
        userPhone:"",
        vipId:""
      },
    };
  },
  mounted() {
    this.getUser();
  },
  methods: {
    close(type) {
      if (type == 1) {
        this.dialogVisible = false;
      } else {
        this.dialogVisible2 = false;
      }
      for (let i in this.form) {
        if (i != "isziti") {
          this.form[i] = "";
        } else {
          this.form[i] = false;
        }
      }
    },
    //更新用户
    putData() {
      if(this.form.vipId==""){
        this.form.vipId="0";
      }
      axios
        .put(`http://localhost:8000/admin/updateUser`,this.form)
        .then((res) => {
          this.getUser();
          this.dialogVisible2 = false;
           this.$message({
            message: "修改成功",
            type: "success",
          });
          for (let i in this.form) {
            this.form[i] = "";
          }
        })
        .catch((err) => {
          this.$message({
            message: "修改失败",
            type: "error",
          });
        });
    },
    changeCurrent(val) {
      this.currentPage = val;
      this.showData = this.user.slice(
        (this.currentPage - 1) * 5,
        this.currentPage * 5
      );
    },
    //新增用户
    postData() {
      axios
        .post(`http://localhost:8000/admin/addUser`,this.form)
        .then((res) => {
          //成功,置空所有form中的key值
          for (let i in this.form) {
            this.form[i] = "";
          }
          this.getUser();
          this.dialogVisible = false;
          this.$message({
            message: "新增成功",
            type: "success",
          });
        })
        .catch((err) => {
          this.$message({
            message: "新增失败",
            type: "error",
          });
        });
    },
    //获得所有用户信息
    getUser() {
      axios
        .get(`http://localhost:8000/admin/selectAllUser`)
        .then((res) => {
          console.log(res.data.data);
          this.user = res.data.data;
          this.showData = this.user.slice(0, 5);
          this.length = this.user.length;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //编辑
    handleEdit(index, row) {
      this.dialogVisible2 = true;
      for (let i in this.form) {
        this.form[i] = row[i];
      }
      this.carid = row.id;
    },
    //删除用户
    handleDelete(index, row) {
      console.log(row.userId)
      axios
        .delete(`http://localhost:8000/admin/deleteUser?userId=${row.userId}`)
        .then((res) => {
          this.getUser();
          this.currentPage = 1;
          this.$message({
            message: "删除成功",
            type: "success",
          });
        })
        .catch((ERR) => {
          
          this.$message({
            message: "删除失败",
            type: "error",
          });
        });
    },
    handleSizeChange() {},

    //分页触发
    next() {
      this.currentPage++;
      this.showData = this.user.slice(
        (this.currentPage - 1) * 5,
        this.currentPage * 5
      );
    },
    //前一页
    prev() {
      this.currentPage--;
      this.showData = this.user.slice(
        (this.currentPage - 1) * 5,
        this.currentPage * 5
      );
    },
    //关闭之前
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          done();
        })
        .catch((_) => {});
    },
    //根据名字搜索
    find() {
      this.showData = this.user.filter((item) => {
        return item.userName.indexOf(this.search) != -1;
      });
      this.length = this.showData.length;
      if (this.showData.length > 5) {
        this.showData = this.showData.slice(0, 5);
      }
    },
  },
};
</script>

<style scoped>
.owner {
  width: 70%;
}
.body2 {
  width: 100%;
  height: 100%;
}
.box-card {
  width: 100%;
  height: 100%;
}
.block {
  text-align: center;
}
</style>
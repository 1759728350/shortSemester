<template>
  <div class="body2">
    <el-card class="box-card">
      <div class="clearfix">
        <el-button
          style="float: right; padding: 3px 0;font-size:20px"
          type="text"
          @click="()=>{
            this.dialogVisible = true;
            this.form.carCid = this.randomString(15)
          }"
          >添加汽车公司</el-button
        >
        <h2 class="search">
          汽车公司列表<el-input
            v-model="search"
            placeholder="请输入您要查找的汽车公司信息"
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
          prop="carCid"
          label="汽车公司编号"
          width="180"
        ></el-table-column>
        <el-table-column prop="carCname" label="汽车公司名"> </el-table-column>
        <el-table-column prop="carCLoc" label="公司所在地">
        </el-table-column>
        <el-table-column prop="carCEmail" label="公司邮箱">
        </el-table-column>
        <el-table-column prop="carPhone" label="公司手机">
        </el-table-column>
        <el-table-column prop="carCInfo" label="备注信息">
        </el-table-column>
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
      title="添加汽车公司"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose"
    >
        <el-form ref="form" :model="form" label-width="100px" label-position="left">
        <el-form-item label="公司id">
          <el-input v-model="form.carCid" disabled></el-input>
        </el-form-item>
        </el-form-item>
        <el-form-item label="公司名称">
          <el-input v-model="form.carCname"></el-input>
        </el-form-item>
        <el-form-item label="公司所在地">
          <el-input v-model="form.carCLoc"></el-input>
        </el-form-item>
        <el-form-item label="公司邮箱">
          <el-input v-model="form.carCEmail"></el-input>
        </el-form-item>
        <el-form-item label="公司手机号">
          <el-input v-model="form.carPhone"></el-input>
        </el-form-item>
        <el-form-item label="公司备注信息">
          <el-input v-model="form.carCInfo" type="textarea"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="close(1)">取 消</el-button>
        <el-button type="primary" @click="postData()">确 定</el-button>
      </span>
    </el-dialog>
     <el-dialog
      title="修改汽车公司"
      :visible.sync="dialogVisible2"
      width="30%"
      :before-close="handleClose"
    >
      <el-form ref="form" :model="form" label-width="100px" label-position="left">
        <el-form-item label="公司id">
          <el-input v-model="form.carCid" :disabled="true"></el-input>
        </el-form-item>
        </el-form-item>
        <el-form-item label="公司名称">
          <el-input v-model="form.carCname"></el-input>
        </el-form-item>
        <el-form-item label="公司所在地">
          <el-input v-model="form.carCLoc"></el-input>
        </el-form-item>
        <el-form-item label="公司邮箱">
          <el-input v-model="form.carCEmail"></el-input>
        </el-form-item>
        <el-form-item label="公司手机号">
          <el-input v-model="form.carPhone"></el-input>
        </el-form-item>

        <el-form-item label="公司备注信息">
          <el-input v-model="form.carCInfo" type="textarea"></el-input>
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
  name: "CarCompany",
  data() {
    return {
      search: "",
      dialogVisible: false,
      dialogVisible2: false,
      length: 0,
      showData: [],
      currentPage: 1,
      user: [],
      price: "",
      carCompany: [],
      carid: "",
      form: {
        carCid: "",
        carCname: "",
        carCLoc: "",
        carCEmail: "",
        carPhone: "",
        carCInfo: "",
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
    putData() {
      axios
        .put(`http://localhost:8000/admin/updateCompany`, this.form)
        .then((res) => {
          this.getUser();
          this.dialogVisible2 = false;
          for (let i in this.form) {
            this.form[i] = "";
          }
          this.$message({
            message: "修改成功",
            type: "success",
          });
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
    //新增
    postData() {
      axios
        .post(`http://localhost:8000/admin/addCompany`, this.form)
        .then((res) => {
          //成功,置空所有form中的key值
          for (let i in this.form) {
            this.form[i] = "";
          }
          this.getUser();
          this.dialogVisible = false;
          this.currentPage = 1;
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
    //获取所有汽车公司信息
    getUser() {
      axios
        .get(`http://localhost:8000/admin/selectAllCompany`)
        .then((res) => {
          console.log(res.data);
          this.user = res.data.data;
          this.showData = this.user.slice(0, 5);
          this.length = this.user.length;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    handleEdit(index, row) {
      this.dialogVisible2 = true;
      for (let i in this.form) {
        this.form[i] = row[i];
      }
    },
    //删除汽车公司
    handleDelete(index, row) {
      axios
        .delete(
          `http://localhost:8000/admin/deleteCompanyById?carCompanyId=${row.carCid}`
        )
        .then((res) => {
          if (res.data.code === 3000) {
            this.currentPage = 1;
            this.$message({
              message: res.data.msg,
              type: "error",
            });
          } else {
            this.getUser();
            this.currentPage = 1;
            this.$message({
              message: "删除成功",
              type: "success",
            });
          }
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
        return item.carCname.indexOf(this.search) != -1;
      });
      this.length = this.showData.length;
      if (this.showData.length > 5) {
        this.showData = this.showData.slice(0, 5);
      }
    },
    //随机字符串
    randomString(len) {
      len = len || 32;
      var $chars = "ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678";
      var maxPos = $chars.length;
      var pwd = "";
      for (let i = 0; i < len; i++) {
        pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
      }
      return pwd;
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
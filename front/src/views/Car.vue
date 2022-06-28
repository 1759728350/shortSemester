<template>
  <div class="list">
    <el-card class="box-card">
      <div class="clearfix">
        <h2 class="search">
          车辆列表<el-input v-model="search" placeholder="请输入您要查找的车辆">
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="find()"
            ></el-button
          ></el-input>
        </h2>
        <el-button
          style="float: right; padding: 3px 0;font-size:20px"
          type="text"
          @click="dialogVisible = true"
          >添加车辆</el-button
        >
      </div>
      <el-table
        :data="
          showData.slice((currentPage - 1) * pagesize, currentPage * pagesize)
        "
        stripe
        style="width: 100%"
      >
        <el-table-column prop="id" label="车辆编号" width="180">
        </el-table-column>
        <el-table-column
          prop="name"
          label="车辆名称"
          width="180"
        ></el-table-column>
        <el-table-column label="车辆预览" width="180">
          <template slot-scope="scope">
            <el-image :src="scope.row.img" style="width: 90px"></el-image>
          </template>
        </el-table-column>
        <el-table-column label="车辆描述">
          <template slot-scope="scope">
            <el-popover
              placement="bottom"
              title="描述"
              width="300"
              trigger="click"
              :content="scope.row.info"
            >
              <el-button slot="reference">点击查看</el-button>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="车辆评分" width="180">
          <template slot-scope="scope">
            <el-rate v-model="scope.row.eva * 1"></el-rate>
          </template>
        </el-table-column>
        <el-table-column prop="price" label="车辆单价" width="180">
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
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10]"
          :page-size="100"
          layout="total, sizes, prev, pager, next, jumper"
          :total="this.showData.length"
        >
        </el-pagination>
      </div>
    </el-card>
    <el-dialog
      title="添加车辆"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose"
    >
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="车辆名称">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="车辆单价">
          <el-input v-model="form.price"></el-input>
        </el-form-item>
        <el-form-item label="车辆评分">
          <el-rate v-model="form.eva"></el-rate>
        </el-form-item>
        <el-form-item label="车辆描述">
             <template slot-scope="scope">
               <el-input
            type="textarea"
            :autosize="{ minRows: 2, maxRows: 4 }"
            placeholder="请输入内容"
            v-model="form.info"
          >
          </el-input>
            </el-popover>
          </template>
        </el-form-item>
        <el-form-item label="缩略图">
          <el-input v-model="form.img"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="close()">取 消</el-button>
        <el-button type="primary" @click="postData()">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="编辑车辆"
      :visible.sync="dialogVisible2"
      width="30%"
      :before-close="handleClose"
    >
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="车辆名称">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="车辆价格">
          <el-input v-model="form.price"></el-input>
        </el-form-item>
        <el-form-item label="车辆评分">
          <el-rate v-model="form.eva*1"></el-rate>
        </el-form-item>
        <el-form-item label="车辆描述">
          <el-input
            type="textarea"
            :autosize="{ minRows: 2, maxRows: 4 }"
            placeholder="请输入内容"
            v-model="form.info"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="图片地址">
          <el-input v-model="form.img"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="close()">取 消</el-button>
        <el-button type="primary" @click="editdata()">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Car",
  mounted() {
    this.getAlldata();
  },
  data() {
    return {
      tableData: [],
      showData: [],
      pagesize: 5,
      search: "",
      dialogVisible: false,
      dialogVisible2: false,
      currentPage: 1,
      form: {
        name: "",
        price: "",
        info: "",
        img: "",
        eva: null,
        id: "",
      },
    };
  },
  methods: {
    find() {
      this.showData = this.tableData.filter((item) => {
        return item.name.indexOf(this.search) != -1;
      });
    },
    editdata() {
      let obj = {
        name: this.form.name,
        info: this.form.fino,
        price: this.form.price,
        img: this.form.img,
        id: this.form.id,
        eva: this.form.eva + "",
      };
      axios
        .patch(`http://localhost:3000/allgoods/${this.form.id}`, obj)
        .then((res) => {
          this.getAlldata();
          this.dialogVisible2 = false;
          for (let i in this.form) {
            this.form[i] = "";
          }
          this.$message({
            showClose: true,
            message: "修改成功!",
            type: "success",
          });
        })
        .catch((err) => {
          console.log(err);
          this.dialogVisible2 = false;
          for (let i in this.form) {
            this.form[i] = "";
          }
          this.$message({
            showClose: true,
            message: "修改失败!",
            type: "error",
          });
        });
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          done();
        })
        .catch((_) => {});
    },
    close() {
      this.dialogVisible = false;
      this.dialogVisible2 = false;
      for (let i in this.form) {
        this.form[i] = "";
      }
    },
    handleEdit(index, row) {
      this.dialogVisible2 = true;
      this.form.name = row.name;
      this.form.price = row.price;
      this.form.info = row.info;
      this.form.img = row.img;
      this.form.id = row.id;
      this.form.eva = row.eva;
    },
    handleDelete(index, row) {
      axios
        .delete(`http://localhost:3000/allgoods/${row.id}`)
        .then((res) => {
          this.$message({
            showClose: true,
            message: "删除成功!",
            type: "success",
          });
          this.getAlldata();
        })
        .catch((err) => {
          console.log(err);
          this.$message({
            showClose: true,
            message: "删除失败",
            type: "error",
          });
        });
    },
    handleSizeChange(val) {
      this.pagesize = val;
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      console.log(`当前页: ${val}`);
    },
    getAlldata() {
      axios
        .get(`http://localhost:3000/allgoods`)
        .then((res) => {
          this.tableData = res.data;
          this.showData = this.tableData;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    postData() {
      let obj = {
        name: this.form.name,
        info: this.form.fino,
        price: this.form.price,
        img: this.form.img,
        id: this.form.id,
        eva: this.form.eva + "",
        type: "new",
        volume: "50",
      };
      axios
        .post(`http://localhost:3000/allgoods`, obj)
        .then((res) => {
          this.getAlldata();
          this.dialogVisible = false;
          for (let i in this.form) {
            this.form[i] = "";
          }
          this.search = "";
          this.$message({
            showClose: true,
            message: "添加成功!",
            type: "success",
          });
        })
        .catch((err) => {
          console.log(err);
          for (let i in this.form) {
            this.form[i] = "";
          }
          this.$message({
            showClose: true,
            message: "添加失败!",
            type: "error",
          });
        });
    },
  },
};
</script>

<style scoped>
.text {
  font-size: 14px;
}
.list {
  width: 100%;
  height: 100%;
}
.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  width: 100%;
  height: 100%;
  overflow: scroll;
}
.block {
  width: 100%;
  text-align: center;
}
.search {
  width: 80%;
  float: left;
}
</style>
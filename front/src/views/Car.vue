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
          style="float: right; padding: 3px 0; font-size: 20px"
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
        <el-table-column prop="carId" label="车辆编号"> </el-table-column>
        <el-table-column prop="carModel" label="车辆型号"></el-table-column>
        <el-table-column label="车辆预览">
          <template slot-scope="scope">
            <el-image :src="scope.row.carImg" style="width: 90px"></el-image>
          </template>
        </el-table-column>
        <el-table-column prop="mileage" label="行驶里程"> </el-table-column>
        <el-table-column prop="mileage" label="租借状态">
          <template slot-scope="scope">
            {{ scope.row.state == 0 ? "租借中" : "闲置或可租借" }}
          </template>
        </el-table-column>
        <el-table-column prop="carBrand" label="汽车品牌"> </el-table-column>
        <el-table-column prop="leaseAmount" label="租借金额(元/月)">
        </el-table-column>
        <el-table-column label="备注信息">
          <template slot-scope="scope">
            <el-popover
              placement="bottom"
              title="汽车备注"
              width="300"
              trigger="click"
              :content="scope.row.carInfo"
            >
              <el-button slot="reference">点击查看</el-button>
            </el-popover>
          </template>
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
      <el-form
        ref="form"
        :model="form"
        label-width="100px"
        label-position="left"
      >
        <el-form-item label="车辆编号">
          <el-input v-model="form.carId"></el-input>
        </el-form-item>
        <el-form-item label="车辆型号">
          <el-input v-model="form.carModel"></el-input>
        </el-form-item>
        <el-form-item label="车牌号">
          <el-input v-model="form.carNumber"></el-input>
        </el-form-item>
        <el-form-item label="车辆所在地">
          <el-input v-model="form.carLoc"></el-input>
        </el-form-item>
        <el-form-item label="车辆颜色">
          <el-input v-model="form.carColor"></el-input>
        </el-form-item>
        <el-form-item label="车辆缩略图">
          <el-input v-model="form.carImg"></el-input>
        </el-form-item>
        <el-form-item label="所属公司id">
          <el-select v-model="form.carCid" placeholder="请选择">
            <el-option :value="cid" v-for="cid in CIds"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="使用时间">
          <el-input-number
            v-model="form.useTime"
            :min="1"
            :max="99999"
          ></el-input-number>
        </el-form-item>
        <el-form-item label="行驶里程">
          <el-input-number
            v-model="form.mileage"
            :min="1"
            :max="99999"
          ></el-input-number>
        </el-form-item>
        <el-form-item label="租借金额">
          <el-input-number
            v-model="form.leaseAmount"
            :min="1"
            :max="99999"
          ></el-input-number>
        </el-form-item>
        <el-form-item label="逾期金额">
          <el-input-number
            v-model="form.overdueAmount"
            :min="1"
            :max="99999"
          ></el-input-number>
        </el-form-item>
        <el-form-item label="汽车品牌">
          <el-input v-model="form.carBrand"></el-input>
        </el-form-item>
        <el-form-item label="备注信息">
          <el-input v-model="form.carInfo" type="textarea"></el-input>
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
      <el-form
        ref="form"
        :model="form"
        label-width="100px"
        label-position="left"
      >
        <el-form-item label="车辆编号">
          <el-input v-model="form.carId" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="车辆型号">
          <el-input v-model="form.carModel"></el-input>
        </el-form-item>
        <el-form-item label="车辆所在地">
          <el-input v-model="form.carLoc"></el-input>
        </el-form-item>
        <el-form-item label="车牌号">
          <el-input v-model="form.carNumber"></el-input>
        </el-form-item>
        <el-form-item label="车辆颜色">
          <el-input v-model="form.carColor"></el-input>
        </el-form-item>
        <el-form-item label="车辆缩略图">
          <el-input v-model="form.carImg"></el-input>
        </el-form-item>
        <el-form-item label="使用时间">
          <el-input-number
            v-model="form.useTime"
            :min="1"
            :max="99999"
          ></el-input-number>
        </el-form-item>
        <el-form-item label="行驶里程">
          <el-input-number
            v-model="form.mileage"
            :min="1"
            :max="99999"
          ></el-input-number>
        </el-form-item>
        <el-form-item label="租借金额">
          <el-input-number
            v-model="form.leaseAmount"
            :min="1"
            :max="99999"
          ></el-input-number>
        </el-form-item>
        <el-form-item label="逾期金额">
          <el-input-number
            v-model="form.overdueAmount"
            :min="1"
            :max="99999"
          ></el-input-number>
        </el-form-item>
        <el-form-item label="汽车品牌">
          <el-input v-model="form.carBrand"></el-input>
        </el-form-item>
        <el-form-item label="备注信息">
          <el-input v-model="form.carInfo" type="textarea"></el-input>
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
  carModel: "Car",
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
      CIds: [],
      currentPage: 1,
      form: {
        carId: "",
        carModel: "",
        carNumber: "",
        carColor: "",
        carImg: "",
        carType: 1,
        useTime: 1,
        mileage: 1,
        state: 1,
        leaseAmount: 1,
        overdueAmount: 1,
        startTime: "",
        endTime: "",
        carBrand: "",
        carLoc: "",
        carInfo: "",
        startTime: "",
        endTime: "",
        userId: "",
        leaseholderId: "",
      },
    };
  },
  methods: {
    find() {
      this.currentPage = 1;
      this.showData = this.tableData.filter((item) => {
        return item.carModel.indexOf(this.search) != -1;
      });
    },
    editdata() {
      axios
        .put(`http://localhost:8000/admin/updateCar`, this.form)
        .then((res) => {
          this.getAlldata();
          this.dialogVisible2 = false;
          this.$message({
            showClose: true,
            message: "修改成功!",
            type: "success",
          });
          for (let i in this.form) {
            if (i == "carType" || i == "state") {
              this.form[i] = 1;
            } else {
              this.form[i] = "";
            }
          }
          this.currentPage = 1;
          this.search = "";
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
    //关闭x号
    close() {
      this.dialogVisible = false;
      this.dialogVisible2 = false;
      for (let i in this.form) {
        this.form[i] = "";
      }
    },
    //编辑车辆
    handleEdit(index, row) {
      this.dialogVisible2 = true;
      for (let i in this.form) {
        this.form[i] = row[i];
      }
    },
    //删除操作
    handleDelete(index, row) {
      axios
        .delete(`http://localhost:8000/admin/deleteCarById?carId=${row.carId}`)
        .then((res) => {
          if (res.data.code == 200) {
            this.$message({
              showClose: true,
              message: "删除成功!",
              type: "success",
            });
            this.currentPage = 1;
            this.search = "";
            this.getAlldata();
          } else {
            this.$message({
              showClose: true,
              message: "删除失败,车辆正在被租用!",
              type: "error",
            });
          }
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
      this.getCIds();
      axios
        .get(`http://localhost:8000/admin/selectAllCar`)
        .then((res) => {
          this.tableData = res.data.data;
          this.showData = this.tableData;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //获取所有公司id
    getCIds() {
      axios
        .get("http://localhost:8000/admin/selectAllCompany")
        .then((res) => {
          res.data.data.forEach((item) => {
            this.CIds.push(item.carCid);
          });
          console.log(this.CIds);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //新增车辆
    postData() {
      axios
        .post(`http://localhost:8000/admin/addCar`, this.form)
        .then((res) => {
          this.getAlldata();
          this.dialogVisible = false;
          for (let i in this.form) {
            if (i == "carType" || i == "state") {
              this.form[i] = 1;
            } else {
              this.form[i] = "";
            }
          }
          this.search = "";
          this.currentPage = 1;
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
          this.search = "";
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
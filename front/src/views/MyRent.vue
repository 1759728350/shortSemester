<template>
  <div class="body">
    <div class="tabbar">
      <el-page-header
        @back="goBack"
        content="我的租借"
        style="width: 100%; height: 100%"
      >
        <template #title><h3>返回</h3></template>
         <template #content
          ><h3>我的租借</h3>
        </template>
      </el-page-header>
    </div>
    <div class="carCard" v-if="carAndUser.length!=0">
      <el-row>
        <el-col
          :span="4"
          v-for="(o, index) in carAndUser"
          :key="o.carId"
          :offset="index % 5 > 0 ? 1 : 0"
          style="margin-top: 10px"
        >
          <el-card :body-style="{ padding: '2px' }" shadow="hover">
            
            <img :src="o.carImg" class="image" v-if="o.carImg.length>10"/>
            <img src="https://img0.baidu.com/it/u=2570981049,4015989895&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=400" class="image" v-if="o.carImg==''||o.carImg.length<10"/>
            <div style="padding: 14px">
              <div>汽车型号:{{ o.carModel }}</div>
              <div>汽车颜色:{{ o.carColor }}</div>
              <template v-if="o.state == 0">
                <div>车牌号:{{ o.carNumber }}</div>
                <div>租借金额:{{ o.LeaseAmount }}元/月</div>
                <div>车辆使用时间:{{ o.useTime }}年</div>
                <div>已行驶里程:{{ o.mileage }}里</div>
                <div>车辆备注:{{ o.carInfo }}</div>

                <div class="bottom clearfix">
                  <el-button type="primary" class="leftbutton" @click="rentCar(o)">租借中</el-button>
                  <el-button type="danger" class="button" @click="deleteCar(o.carId)">提前还车</el-button>
                </div>
              </template>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
    <div v-if="carAndUser.length==0">
      <br>
      <el-empty description="未找到任何相关信息"></el-empty>
    </div>
    
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "myCar",
  mounted() {
    this.getMyCar();
  },
  data() {
    return {
      carAndUser: [],
      car: {},
      dialogVisible2: false,
      form: {
        carId: "",
        carModel: "",
        carNumber: "",
        carColor: "",
        carImg: "",
        carType: 0,
        useTime: 1,
        mileage: 1,
        state: 2,
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
        carLoc:""
      },
    };
  },
  methods: {
    deleteCar(cid){
       axios
        .delete(`http://localhost:8000/admin/deleteCarById?carId=${cid}`)
        .then((res) => {
          this.$message({
            showClose: true,
            message: "删除成功!",
            type: "success",
          });
          this.getMyCar()
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
    handleClose() {
      this.dialogVisible2 = false;
      for (let i in this.form) {
        this.form[i] = "";
      }
    },
    connect(phone) {
      this.$notify({
        title: "联系方式",
        message: phone,
      });
    },
    goBack() {
      this.$router.push({
        path: "/",
      });
    },
    //关闭
    close() {
      this.dialogVisible2 = false;
      for (let i in this.form) {
        this.form[i] = "";
      }
    },

    //获取我的租车信息
    getMyCar() {
      let userId = JSON.parse(localStorage.user).userId;
     
      axios
        .get(`http://localhost:8000/user/userAndCarInfo?userId=${userId}`)
        .then((res) => {
          this.carAndUser = res.data.data;
          console.log(this.carAndUser);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //新增车辆
    postData() {
       this.form.carLoc =  localStorage.getItem("myloc");
      axios
        .post(`http://localhost:8000/admin/addCar`, this.form)
        .then((res) => {
          this.getMyCar()
          this.dialogVisible2 = false;
          for (let i in this.form) {
            this.form[i] = "";
          }
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
    //随机字符串
    randomString(len) {
      len = len || 32;
      var $chars =
        "ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678"; /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
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
.body {
  width: 100%;
  height: 800px;
}
.tabbar {
  width: 100%;
  height: 10%;
  border-bottom: 1px solid grey;
}
h3 {
  position: relative;
  top: 35%;
}
.carCard {
  margin-top: 5px;
  width: 100%;
  height: 85%;
}
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  width: 65px;
  height: 30px;
  float: right;
}
.leftbutton {
  padding: 0;
  width: 65px;
  height: 30px;
  float: left;
}
.image {
  width: 100%;
  height: 200px;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}
.right {
  position: absolute;
  top: 30px;
  right: 10px;
  float: right;
}
.right:hover {
  color: #409eff;
}
</style>
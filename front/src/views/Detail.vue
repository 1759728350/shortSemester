<template>
  <div class="body">
    <div class="tabbar">
      <el-page-header
        @back="goBack"
        content="详情页面"
        style="width: 100%; height: 100%"
      >
        <template #title><h3>返回</h3></template>
        <template #content><h3>详情页面</h3></template>
      </el-page-header>
    </div>
    <div class="card">
      <div>
        <div class="cardBox">
          <div class="bodyBox-2">
            <img :src="car.carImg" alt="图片" class="img" />
          </div>
          <div class="bodyBox">
            <p>车牌号:{{ car.carNumber }}</p>
            <p>车辆类型:{{ car.carModel }}</p>
            <p>颜色:{{ car.carColor }}</p>
            <p>使用时间:{{ car.useTime }}年</p>
            <p>行驶里程:{{ car.mileage }}里</p>
            <p>租借金额:{{ car.leaseAmount }}元/月</p>
            <p>品牌:{{ car.carBrand }}</p>
            <p>所在地:{{ car.carLoc }}</p>
            <p>逾期金额:{{ car.deposit }}元/月</p>
            <br />
            <el-button type="danger" @click="rent()">租借车辆</el-button>
            <br />
            <el-button type="primary" @click="connect()">联系方式</el-button>
          </div>
        </div>
      </div>
    </div>
    <el-dialog
      title="汽车租借"
      :visible.sync="dialogVisible"
      width="60%"
      :before-close="handleClose"
    >
      <el-steps :active="active" align-center>
        <el-step
          title="确认信息"
        >
        </el-step>
        <el-step
          title="生成订单"
        >
        </el-step>
        <el-step
          title="付款"
        ></el-step>
      </el-steps>
      <div class="yi" v-if="active==0">
        <h2>请确认车辆相关信息</h2>
      </div>
      <div class="er" v-if="active==1">
        <h2  v-loading="loading"
    element-loading-text="生成订单中">生成订单</h2>
      </div>
      <div class="san" v-if="active==2||active==3">
        <h4>请扫描二维码付款</h4>
        <br>
         <el-image
      style="width: 260px; height: 260px"
      src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.xjishu.com%2Fimg%2Fzl%2F2017%2F10%2F19454498268039.gif&refer=http%3A%2F%2Fimg.xjishu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1659162149&t=cbc934a0afdb633a5602a4eff4f1eecc"
      fit="fit"></el-image>
      
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false,active=0">取 消</el-button>
        <el-button type="primary" @click="next()"
          >{{active!=3?'下一步':'确认租借'}}</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: "Detail",
  mounted() {
    this.getCar();
  },
  data() {
    return {
      car: {},
      active:0,
      loading: true,
      dialogVisible: false,
    };
  },
  methods: {
    //下一步
    next(){
      this.active++
      if(this.active>3){
        console.log("确认提交")
        this.active = 0
        this.dialogVisible=false
      }
    },
    //租借车辆
    rentCar(){

    },
    //关闭之前
    handleClose() {},
    goBack() {
      this.$router.push({
        path: "/",
      });
    },
    //获取当前车辆
    getCar() {
      this.car = JSON.parse(this.$route.query.thisCar);
    },
    connect() {
      this.$notify({
        title: "联系方式",
        message: "电话:15042070108",
      });
    },
    rent() {
      this.dialogVisible = true;
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
#image {
  width: 200px;
  height: 100px;
  float: right;
}
#nav {
  float: left;
}
.cardBox {
  width: 50%;
  box-shadow: 10px 10px 10px 10px rgba(0, 0, 0, 0.2),
    0 6px 20px 0 rgba(0, 0, 0, 0.19);
  float: left;
  margin-right: 25%;
  padding: 5px;
  padding-top: 15px;
  margin-left: 25%;
  height: 500px;
  margin-top: 5%;
}
.headerBox {
  color: #fff;
  padding: 10px;
  font-size: 15px;
  height: 60px;
}
.bodyBox {
  float: right;
  padding-bottom: 10px;
  border-width: 2px;
  border-color: black;
  border-style: solid;
  border-color: black;
  height: 97%;
  width: 19%;
  background-repeat: no-repeat;
  background-size: 100%;
}
.button-1 {
  float: right;
  margin-top: 100px;
}
.p {
  float: left;
  color: red;
}
.bodyBox-2 {
  float: left;
  width: 80%;
  height: 100%;
}
.bodyBox > p {
  margin-bottom: 10%;
  font-weight: bold;
}
img {
  position: relative;
  left: 20%;
  top: 15%;
  width: 60%;
  height: 60%;
}
.bodyBox > .el-button {
  position: relative;
  left: 10%;
  margin-top: 5%;
  width: 80%;
}
.yi{
  margin-top: 20px;
  text-align: center;
}
.er{
  margin-top: 20px;
  text-align: center;
}
.san{
  position: relative;
  margin-top: 20px;
  left: 30%;
  width: 40%;
  height: 350px;
  text-align: center;
}
</style>
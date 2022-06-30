<template>
  <div class="body">
    <div class="tabbar">
      <el-page-header
        @back="goBack"
        content="详情页面"
        style="width: 100%; height: 100%"
      >
        <template #title><h3>返回</h3></template>
        <template #content><h3>我的爱车</h3>
        <h3 class="right" @click="addCar()"><i class="el-icon-plus"></i>添加爱车</h3>
        </template>
         
      </el-page-header>
    </div>
    <div class="carCard">
        <el-row>
          <el-col
            :span="4"
            v-for="(o, index) in 6"
            :key="o"
            :offset="index%5 > 0? 1 : 0"
            style="margin-top:10px"
          >
            <el-card :body-style="{ padding: '2px'}" shadow="hover">
              <img
                :src="'https://dss0.bdstatic.com/-0U0bnSm1A5BphGlnYG/tam-ogel/-912671055_-1201020382_360_212.png'"
                class="image"
              />
              <div style="padding: 14px">
                <span>我的爱车</span>
                <div>15156</div>
                <div>15156</div>
                <div>15156</div>
                <div class="bottom clearfix">
                  <el-button type="primary">出 租</el-button>
                  <el-button type="danger" class="button">移 除</el-button>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "myCar",
  mounted() {
    this.getMyCar();
  },
  data() {
    return {
      car: {},
    };
  },
  methods: {
    goBack() {
      this.$router.push({
        path: "/",
      });
    },
    //获取我的爱车
    geMmyCar() {},
    //添加车辆()
    addCar(){
        alert("添加车辆")
    }
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
.leftbutton{
    padding: 0;
    width: 65px;
   height: 30px;
    float: left;
}
.image {
  width: 100%;
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
.right{
    position: absolute;
    top: 30px;
    right: 10px;
    float: right;
}
.right:hover{
    color: #409EFF;
}
</style>
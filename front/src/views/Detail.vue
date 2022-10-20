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
            <div class="boxImg">
              <img :src="car.carImg" alt="图片" class="img" />
            </div>
            <div class="commit">
              <div class="commitHeader">
                <h2>用户点评</h2>
              </div>
              <div class="userCommit">
                <hr />
                <div v-if="commit.length==0">
                    <h3>暂无相关评论</h3>
                </div>
                <div v-for="i in commit" style="background-color: #f2f6fc" v-else>
                  <div class="commitTop">
                    <div class="userName">
                      用户&nbsp;{{i.coUid.slice(0,10)}}
                      <div class="commitTime">{{new Date(i.createTime).toLocaleDateString()}}</div>
                    </div>
                    <div class="commitGrade">
                      <el-rate
                        v-model="i.level"
                        show-text
                        :colors="colors"
                        :texts="texts"
                        disabled
                      ></el-rate>
                    </div>
                  </div>
                  <div class="commitText">
                    {{i.content}}
                    <hr />
                  </div>
                </div>
                
              </div>
            </div>
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
            <br />
            <el-button type="info" @click="displayCommit()">发表评论</el-button>
          </div>
          <transition>
            <div class="Drawer" v-show="drawer">
              <div class="title">
                <span>发表一条友善的评论吧</span>
                <i
                  class="el-icon-close"
                  @click="drawer = false"
                  style="float: right; font-size: 22px"
                ></i>
              </div>
              <div class="commitBody">
                <div style="width:70%;height:100%;">
                  <el-input
                    type="textarea"
                    rows="7"
                    placeholder="请输入内容"
                    v-model="newCommit.content"
                    :resize="'none'"
                  ></el-input>
                </div>
                <div>
                    <div>
                       <el-rate
                        v-model="newCommit.level"
                        show-text
                        :colors="colors"
                        :texts="texts"
                      ></el-rate>
                    </div>
                    <div><el-button type="primary" @click="addNewCommit()">提交评论</el-button></div>
                </div>
              </div>
            </div>
          </transition>
        </div>
      </div>
    </div>
    <el-dialog
      title="汽车租借"
      :visible.sync="disalog1"
      width="60%"
      :before-close="handleClose"
    >
      <el-steps :active="active" align-center>
        <el-step title="选择租借时长"> </el-step>
        <el-step title="生成订单"> </el-step>
        <el-step title="付款"></el-step>
      </el-steps>
      <div class="yi" v-if="active == 0">
        <div class="block">
          <el-date-picker
            v-model="date"
            type="datetimerange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
          >
          </el-date-picker>
        </div>
      </div>
      <div class="er" v-if="active == 1">
        <h2 v-loading="loading" element-loading-text="生成订单中">生成订单</h2>
      </div>
      <div class="san" v-if="active == 2 || active == 3">
        <h4>请扫描二维码付款</h4>
        <br />
        <el-image
          style="width: 260px; height: 260px"
          src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.xjishu.com%2Fimg%2Fzl%2F2017%2F10%2F19454498268039.gif&refer=http%3A%2F%2Fimg.xjishu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1659162149&t=cbc934a0afdb633a5602a4eff4f1eecc"
          fit="fit"
        ></el-image>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="(disalog1 = false), (active = 0)">取 消</el-button>
        <el-button type="primary" @click="next()">{{
          active != 3 ? "下一步" : "确认租借"
        }}</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Detail",
  mounted() {
    this.getCar();
    
  },
  data() {
    return {
      car: {},
      drawer: false,
      textarea:"",
      commit:{},
      direction: "btt",
      active: 0,
      loading: true,
      texts: ["差评", "较差", "一般", "较好", "好评"],
      disalog1: false,
      colors: ["#409EFF", "#409EFF", "#409EFF"],
      date: [],
      grade: 5,
      newCommit:{
        content:"",
        level:5
      }
    };
  },
  methods: {
    //下一步
    next() {
      if (this.date.length != 0) {
        this.active++;
        if (this.active > 3) {
          let startTime = this.date[0].format("yyyy-MM-dd hh:mm:ss");
          let endTime = this.date[1].format("yyyy-MM-dd hh:mm:ss");
          this.rentCar(startTime, endTime);
          this.active = 0;
          this.disalog1 = false;
        }
      } else {
        this.$message("日期不能为空!");
      }
    },
    //租借车辆
    rentCar(stime, etime) {
      let uID = JSON.parse(localStorage.user).userId;
      axios
        .put("http://localhost:8000/user/userLeaseCar", {
          leaseholderId: uID,
          startTime: stime,
          endTime: etime,
          carId: this.car.carId,
        })
        .then((res) => {
          if (res.data.success) {
            this.$message.success("租借成功!请及时归还车辆");
            this.$router.push({
              path: "/",
            });
            this.date = [];
          }
        })
        .catch((err) => {
          console.log(err);
          this.date = [];
        });
    },
    // 显示评论区
    displayCommit() {
      this.drawer = true;
    },
    //关闭之前
    handleClose() {
      this.$confirm("确认关闭？")
        .then((res) => {
          if (res === "confirm") {
            this.drawer = false;
          }
        })
        .catch((err) => {});
    },
    goBack() {
      this.$router.push({
        path: "/",
      });
    },
    //获取当前车辆
    getCar() {
      this.car = JSON.parse(this.$route.query.thisCar);
      this.getCommit();
    },
    connect() {
      this.$notify({
        title: "联系方式",
        message: "电话:15042070108",
      });
    },
    rent() {
      this.disalog1 = true;
    },
    getCommit(){
      axios.get(`http://localhost:8000/commit/selectCommit?carId=${this.car.carId}`).then(res=>{
        if(res.status===200){
            this.commit = res.data.data
            console.log(this.commit)
        }
      }).catch(err=>{
        console.log(err)
      })
    },
    addNewCommit(){
      let commit = {
        coUid:JSON.parse(localStorage.getItem('user')).userId,
        level:this.newCommit.level+"",
        content:this.newCommit.content,
        carId:this.car.carId
      }
      axios.post("http://localhost:8000/commit/addCommit",commit).then(res=>{
        if(res.status===200){
          this.newCommit.content="";
          this.newCommit.level=5;
          this.$message('发布成功!');
          this.getCommit()
          this.drawer = false
        }
      }).catch(err=>{
        console.log(err)
      })
    }
  },
};
//日期转换
Date.prototype.format = function (fmt) {
  var o = {
    "M+": this.getMonth() + 1, //月份
    "d+": this.getDate(), //日
    "h+": this.getHours(), //小时
    "m+": this.getMinutes(), //分
    "s+": this.getSeconds(), //秒
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
    S: this.getMilliseconds(), //毫秒
  };
  if (/(y+)/.test(fmt)) {
    fmt = fmt.replace(
      RegExp.$1,
      (this.getFullYear() + "").substr(4 - RegExp.$1.length)
    );
  }
  for (var k in o) {
    if (new RegExp("(" + k + ")").test(fmt)) {
      fmt = fmt.replace(
        RegExp.$1,
        RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length)
      );
    }
  }
  return fmt;
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
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  position: relative;
  overflow: hidden;
}
.Drawer {
  position: absolute;
  width: 100%;
  height: 40%;
  background-color: white;
  border-top: 1px solid black;
  bottom: 0;
  transition: 2s;
}
.title {
  padding: 8px 8px;
  font-weight: bold;
  font-size: 18px;
}
.commitBody {
  width: 100%;
  height: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  align-content: center;
}
.commitBody>div:nth-child(2){
  width:25%;
  height:70%;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-content: space-around;
  font-size: 20px;
}
/* vue动画 */
.v-enter,
.v-leave-to {
  transform: translateY(200px);
}
.v-enter-active,
.v-leave-active {
  transition: all 1s ease;
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
  display: flex;
  flex-wrap: wrap;
  align-content: center;
  justify-content: center;
}
.boxImg {
  width: 80%;
  height: 60%;
}
.commit {
  width: 100%;
  height: 40%;
  display: flex;
  flex-wrap: wrap;
  align-content: space-between;
  overflow: auto;
}
.userCommit {
  width: 100%;
  height: 80%;
}
.commitTop {
  width: 100%;
  height: 35%;
  font-weight: bolder;
  font-size: 18px;
  padding-bottom: 16px;
}
.commitText {
  width: 100%;
  height: 63%;
  font-size: 18px;
  font-weight: bold;
}
.commitTime {
  float: right;
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
.yi {
  margin-top: 20px;
  text-align: center;
}
.er {
  margin-top: 20px;
  text-align: center;
}
.san {
  position: relative;
  margin-top: 20px;
  left: 30%;
  width: 40%;
  height: 350px;
  text-align: center;
}
</style>
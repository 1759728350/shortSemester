<template>
  <div class="body">
    <div class="left">
      <div class="tabber">
        <div class="center white big">"乐享"后台管理系统</div>
        <br />
        <el-menu
          default-active="user"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          active-text-color="#409EFF"
          router
        >
          <el-submenu index="1">
            <template #title>
              <i class="el-icon-user-solid"></i>
              <span class="white font">用户管理</span>
            </template>
            <el-menu-item-group class="bgcolor">
              <el-menu-item index="user" class="white">用户列表</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-submenu index="2">
            <template #title>
              <i class="el-icon-location"></i>
              <span class="white font">车辆管理</span>
            </template>
            <el-menu-item-group class="bgcolor">
              <el-menu-item index="car" class="white">车辆列表</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-submenu index="3">
            <template #title>
              <i class="el-icon-office-building"></i>
              <span class="white font">汽车公司管理</span>
            </template>
            <el-menu-item-group class="bgcolor">
              <el-menu-item index="carCompany" class="white">汽车公司列表</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
           <el-submenu index="4">
            <template #title>
              <i class="el-icon-s-order"></i>
              <span class="white font">用户评论管理</span>
            </template>
            <el-menu-item-group class="bgcolor">
              <el-menu-item index="Commit" class="white">用户评论列表</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </div>
    </div>
    <div class="right">
      <div class="top">
        <el-dropdown trigger="click" class="pos" @command="adminQuit">
          <span class="el-dropdown-link font"> 
            admin
            <i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu>
            <el-dropdown-item>退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
      <div class="rightbody">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "home",
  data() {
    return {

    };
  },
  methods: {
    handleOpen(){

    },
    handleClose(){

    },
    adminQuit(){
      this.$router.push({
        path:"/"
      })
    }
  },
};
</script>

<style scoped>
.body {
  width: 100%;
  height: 800px;
  background-color: rgba(255, 255, 255, 0.4);
  display: flex;
  justify-content: center;
  align-content: center;
  overflow: hidden;
}
.left {
  width: 15%;
  height: 100%;
  background-color: rgb(45, 58, 75);
}
.right {
  width: 85%;
  height: 100%;
}
.top {
  width: 100%;
  height: 10%;
  border-bottom: 1px solid grey;
  position: relative;
}
.tabber {
  width: 100%;
  height: 60%;
  margin-top: 5%;
}
.el-menu-vertical-demo {
  width: 100%;
  height: 100%;
  background-color: rgb(45, 58, 75);
}
.white {
  color: white;
}
.font {
  font-size: 16px;
}
.center {
  text-align: center;
}
.big {
  font-size: 22px;
  font-weight: bolder;
}
.bgcolor {
  background-color: rgb(45, 58, 75);
}
.rightbody {
  width: 100%;
  height: 90%;
}
.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
  }
.el-icon-arrow-down {
    font-size: 16px;
}
.pos{
  position: absolute;
  bottom: 30%;
  right: 2%;
}
</style>
<template>
  <div class="body">
    <div class="login">
      <h1>"乐享"后台管理系统</h1>
      <div class="form">
        <el-form label-width="80px" label-position="left">
          <el-form-item>
            <template #label>
              <h2>用户名</h2>
            </template>
            <el-input v-model="adminName" placeholder="请输入账号"></el-input>
          </el-form-item>

          <el-form-item label="密码">
            <template #label>
              <h2>密码</h2>
            </template>
            <el-input
              v-model="adminPassword"
              type="password"
              placeholder="请输入密码"
            ></el-input>
          </el-form-item>
          <el-button
            type="primary"
            class="center"
            style="width: 100%; margin-top: 25px"
            @click="submit()"
            >登录</el-button
          >
        </el-form>
        <div class="back"><router-link to="/" class="a">点此返回主页</router-link></div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "adminLogin",
  data() {
    return {
      adminName: "",
      adminPassword: "",
    };
  },
  methods: {
    submit() {
      axios.post("http://localhost:8000/admin/adminLogin",{
        adminAccount:this.adminName,
        adminPassword:this.adminPassword
      }).then(res=>{
        if(res.data.success){
          this.$message.success("登录成功!")
          this.$router.push({
            path:"/adminHome"
          })
        }else{
          this.$message.error("账号密码错误!")
        }
        
      }).catch(err=>{
        this.$message.error("请求错误!")
        console.log(err)
      })
    },
  },
};
</script>

<style scoped>
.body {
  width: 100%;
  height: 800px;
  background-image: url("https://img.58cdn.com.cn/ui6/my/images/reg-bg.png");
  background-size: 100% 100%;
  display: flex;
  justify-content: center;
  align-content: center;
  flex-wrap: nowrap;
}
.login {
  position: relative;
  top: 25%;
  width: 30%;
  height: 50%;
  background-color: rgba(17, 17, 17, 0.8);
  border-radius: 3%;
  display: flex;
  justify-content: center;
  align-content: space-around;
  flex-wrap: wrap;
}
h1 {
  margin-top: 3%;
  font-family: "楷体";
  color: aliceblue;
}
.form {
  width: 92%;
  height: 70%;
}
.center {
  align-content: center;
}
h2 {
  color: aliceblue;
}
.back{
    position: absolute;
    bottom: 80px;
    right: 20px;
}
.a{
    font-weight: bold;
    color: white;
    text-decoration: none;
}
.a:hover{
    color:rgb(64, 158, 255);
    text-decoration: underline;
}
</style>
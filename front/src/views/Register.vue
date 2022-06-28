<template>
  <div class="login">
    <div class="body">
      <div class="logo">
        <img
          src="https://img0.baidu.com/it/u=927934986,287206218&fm=253&fmt=auto&app=138&f=JPEG?w=890&h=500"
          alt="logo"
        />
      </div>
      <div class="form">
        <div class="Tabs">
          <el-tabs
            v-model="activeName"
            :stretch="true"
            @tab-click="handleClick()"
          >
            <el-tab-pane name="first" class="Tab">
              <template #label>
                <div class="tabText">欢迎加入乐享科技</div>
              </template>
              <el-form
                :model="user"
              >
                <el-form-item>
                  <div class="input">
                  <el-input v-model="user.Account" placeholder="请注册账号(6-12位英文字母组合)" type="text" :clearable="true"></el-input>
                  </div>
                </el-form-item>
                <el-form-item>
                  <div class="input">
                  <el-input v-model="user.PassWord" placeholder="请设置密码(6-18位数字,英文字母组合)" type="password" :clearable="true"></el-input>
                  </div>
                </el-form-item>
                <el-form-item>
                  <div class="input">
                  <el-input v-model="user.Phone" placeholder="请绑定手机号" type="text" :clearable="true">
                     <el-button slot="append" @click="sell()" class="hover" :disabled="disabled">{{this.disabled?"请等待"+this.time+"秒":"发送短信"}}</el-button>
                  </el-input>
                  </div>
                </el-form-item>
                 <el-form-item>
                  <div class="input">
                  <el-input v-model="user.code" placeholder="请输入手机动态验证码" type="text" :clearable="true"></el-input>
                  </div>
                </el-form-item>
                <div><el-button type="primary" class="button" @click="register()">注 &emsp;册</el-button></div>
              </el-form>
            </el-tab-pane>
          </el-tabs>
        </div>
      </div>
      <div class="end">
          <div>
            <div class="returnIndex"><router-link to="/" class="a">点此返回主页</router-link></div>
          </div>
           <div>
            <div class="gotoRegister"><router-link to="/login" class="a">点此前往登录</router-link></div>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Riegster",
  data() {
    return {
      activeName: "first",
        user: {
          Account: '',
          PassWord: '',
          Phone:"",
          code:""
        },
        disabled:false,
        time:0
    };
  },
  methods: {
    handleClick() {
      this.activeName = "first";
    },
    register(){

    },
    //发送短信
    sell(){
      this.buttonTimeout()
    },
    //按钮计时器
    buttonTimeout(){
      this.disabled=true;
      this.time = 60;
      let timeOut = () => { setTimeout(()=>{
        if(this.time!=0){
          this.time--;
          console.log(this.time)
          timeOut()
        }else{
          this.disabled = false;
        }
      },1000)}
      timeOut()
    }
  },
};
</script>

<style scoped>
.login {
  width: 100%;
  height: 800px;
  display: flex;
  justify-content: center;
  align-content: center;
  background: url("https://img.58cdn.com.cn/ui6/my/images/reg-bg.png");
  background-size: cover;
}
.body {
  margin-top: 30px;
  width: 35%;
  height: 75%;
  background-color: rgb(255, 255, 253);
  border-radius: 1%;
  display: flex;
  justify-content: center;
  align-content: center;
  flex-wrap: wrap;
}
.logo {
  width: 100%;
  height: 35%;
  display: flex;
  justify-content: center;
  align-content: center;
}
.logo > img {
  width: 65%;
  height: 100%;
}
.form {
  width: 80%;
  height: 50%;
  background-color: whitesmoke;
}
.Tabs {
  width: 100%;
  height: 12%;
}
.tabText {
  font-weight: bold;
  font-size: 18px;
}
.input{
  display: flex;
  justify-content: center;
  align-content: center;
}
.input .el-input{
  width: 86%;
}
/* 深度选择器 */
::v-deep .el-input__inner{
  height: 30px;
}
.button{
  width: 80%;
  height: 40px;
  position: relative;
  left: 10%;
  font-size: 18px;
  font-weight: bold;
}
.lostPWD{
  margin-top: 25px;
  font-weight: bold;
  position: relative;
  right: 5px;
  font-size: 13px;
  float: right;
  color: grey;
}
.lostPWD:hover{
  color: #409EFF;
  text-decoration: underline;
}
.icon{
  width: 100%;
  height: 35px;
  font-size: 16px;
}
.end{
  width: 100%;
  height: 10%;
}
.returnIndex{
  float: left;
  margin-top: 10px;
  margin-left: 10px;
}
.gotoRegister{
  float: right;
  margin-top: 10px;
  margin-right: 10px;
}
a{
   color: #409EFF;
   font-weight: bold;
   text-decoration: none;
}
a:hover{
  opacity: .6;
  text-decoration: underline;
}
.hover:hover{
  background-color: #409EFF;
  color: white;
}
</style>
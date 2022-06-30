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
                <div class="tabText">账号密码登录</div>
              </template>
              <el-form
                :model="user"
              >
                <el-form-item>
                  <div class="input">
                  <el-input v-model="user.Account" placeholder="请输入账号(6-12位英文字母组合)" size="small" type="text" :clearable="true"></el-input>
                  </div>
                </el-form-item>
                <el-form-item>
                  <div class="input">
                  <el-input v-model="user.PassWord" placeholder="请输入密码(6-18位数字,英文字母组合)" type="password" :clearable="true"></el-input>
                  </div>
                </el-form-item>
                <div><el-button type="primary" class="button" @click="login()">登 &emsp;录</el-button></div>
                <div class="lostPWD">忘记密码?点击此处</div>
              </el-form>
            </el-tab-pane>
            <el-tab-pane name="second" class="Tab">
              <template #label>
                <div class="tabText">手机动态码登录</div>
              </template>
                 <el-form
                :model="user"
              >
                <el-form-item>
                  <div class="input">
                  <el-input v-model="user.Phone" placeholder="请输入手机号" type="text" :clearable="true">
                    <el-button slot="append" class="hover" :disabled="disabled" @click="sell()">{{this.disabled?"请等待"+this.time+"秒":"发送短信"}}</el-button>
                  </el-input>
                  </div>
                </el-form-item>
                <el-form-item>
                  <div class="input">
                  <el-input v-model="user.code" placeholder="请输入六位验证码" type="password" :clearable="true" ></el-input>
                  </div>
                </el-form-item>
                <div><el-button type="primary" class="button" @click="loginByphone()">登 &emsp;录</el-button></div>
              </el-form>
            </el-tab-pane>
          </el-tabs>
        </div>
      </div>
      <div class="end">
          <div>
            <div class="returnIndex"><router-link to="/" class="a">点此前往主页</router-link></div>
          </div>
           <div>
            <div class="gotoRegister"><router-link to="/register" class="a">点此前往注册</router-link></div>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: "login",
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
      this.activeName = "second";
    },
    //账号密码登录
    login(){
        if((this.user.Account.length>=6&&this.user.Account.length<=12)&&(this.user.PassWord.length>=6&&this.user.PassWord.length<=12)){
            axios.post("http://localhost:8000/user/login",{
              userAccount:this.user.Account,
              userPassword:this.user.PassWord
            }).then(res=>{
                if(res.data.code==200){
                  this.$message.success('登录成功!');
                  localStorage.user = JSON.stringify(res.data.data);
                  this.$router.push({
                    path:"/"
                  })
                }else{
                   this.$message.error('账号或密码错误');
                }
            }).catch(err=>{

            })
        }else{
            this.$message.error('请输入正确格式的账号密码');
        }
    },
    //通过手机登录
    loginByphone(){
       if(this.user.Phone.length==11&&this.user.code.length==6){
          axios.post("http://localhost:8000/user/loginByPhone",{
            userPhone:this.user.Phone,
            code:this.user.code
          }).then(res=>{
            if(res.data.code==200){
                  this.$message.success('登录成功!');
                  localStorage.user = JSON.stringify(res.data.data);
                  this.$router.push({
                    path:"/"
                  })
                }else{
                   this.$message.error('未查找到该用户!,请先注册');
                }
          }).catch(err=>{
            this.$message.error('验证码错误!');
          })
       }else{
        this.$message.error('验证码应为6位数字');
       }
    },
    //发送短信
    sell(){
      if(this.user.Phone.length==11){
           this.buttonTimeout();
           axios.post("http://localhost:8000/sms/sendCode",{
            phoneNum:this.user.Phone
           }).then(res=>{
              console.log(res.data)
           }).catch(err=>{
              this.$message.error("验证码数量达到上限!")
           })
      }else{
         this.$message.error('请输入11位正确格式手机号');
      }
    },
    //按钮计时器
    buttonTimeout(){
      this.disabled=true;
      this.time = 60;
      let timeOut = () => { setTimeout(()=>{
        if(this.time!=0){
          this.time--;
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
  margin-top: 2%;
  width: 90%;
}
/* 深度选择器 */
::v-deep .el-input__inner{
  height: 40px;
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
import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path:"/",
    name:"index",
    component: () => import("../views/Index.vue")
  },
  {
    path:"/login",
    name:"login",
    component: () => import("../views/Login.vue")
  },
  {
    path:"/register",
    name:"register",
    component: ()=> import("../views/Register.vue")
  },
  {
    path:"/adminLogin",
    name:"adminLogin",
    component: ()=> import("../components/adminLogin.vue")
  },
  {
    path:"/adminHome",
    name:"adminHome",
    redirect:"/user",
    component: ()=> import("../components/Home.vue"),
    children:[
      {
        path:"/user",
        name:"user",
        component: ()=>import("../views/User.vue")
      },
      {
        path:"/car",
        name:"car",
        component:()=> import("../views/Car.vue")
      },
      {
        path:"/carCompany",
        name:"carCompany",
        component:()=> import("../views/CarCompany.vue")
      }
    ]
  },
  {
    path:"/detail",
    name:"detail",
    component:() => import("../views/Detail.vue")
  },
  {
    path:"/myCar",
    name:"myCar",
    component:() => import("../views/MyCar.vue")
  }
]

const router = new VueRouter({
  routes
})

export default router

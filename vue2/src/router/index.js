import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from "@/views/Layout";
import loginByEmail from "@/views/Login/LoginByEmail";
import LoginByAccount from "@/views/Login/LoginByAccount";
import RegisterByAccount from "@/views/Login/RegisterByAccount";
import LoginEmailCode from "@/views/Login/LoginEmailCode";
import UserIndex from "@/views/UserIndex";
import axios from "axios";
import Error from "@/components/Error";
import LevelRanking from "@/views/LevelRanking";
import MyPK from "@/views/MyPK";
import MyUpload from "@/views/MyUpload";
import MyProfile from "@/views/MyProfile";
import RandomPK from "@/views/RandomPK";



Vue.use(VueRouter)
//禁止全局路由错误处理打印，这个也是vue-router开发者给出的解决方案：
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location, onResolve, onReject) {
  if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
  return originalPush.call(this, location).catch(err => err)
}

const routes = [
  {
    path: '/',
    component: Layout,
    children:[
      {
        path: '',
        name: 'loginByEmail',
        component: loginByEmail,
        meta:{isAuth:false}
      },
      {
        path: 'loginByAccount',
        name: 'LoginByAccount',
        component: LoginByAccount,
        meta:{isAuth:false}
      },
      {
        path: 'registerByAccount',
        name: 'RegisterByAccount',
        component: RegisterByAccount,
        meta:{isAuth:false}
      },
      {
        path: 'loginEmailCode',
        name: 'LoginEmailCode',
        component: LoginEmailCode,
        meta:{isAuth:false}
      },
    ]
  },
  {
    path: '/index',
    name: 'UserIndex',
    component: UserIndex,
    redirect:'/index/levelRanking',
    meta:{isAuth:true},
    children:[
      {
        path: 'levelRanking',
        name: 'LevelRanking',
        component: LevelRanking,
        meta:{isAuth:true}
      },
      {
        path: 'myPk',
        name: 'MyPK',
        component: MyPK,
        meta:{isAuth:true}
      },
      {
        path: 'myUpload',
        name: 'MyUpload',
        component: MyUpload,
        meta:{isAuth:true}
      },
      {
        path: 'myProfile',
        name: 'MyProfile',
        component: MyProfile,
        meta:{isAuth:true}
      },
      {
        path: 'randomPK',
        name: 'RandomPK',
        component: RandomPK,
        meta:{isAuth:true}
      },
    ]
  },
  {
    path: '/error',
    name: 'Error',
    component: Error,
  },


  /*{
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/!* webpackChunkName: "about" *!/ '../views/AboutView.vue')
  }*/
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


router.beforeEach((to, from, next) => {
  if (!to.meta.isAuth) { //判断是否需要鉴权
    // window.localStorage.removeItem('access-admin')
    next()
  } else {
    let admin = JSON.parse(window.localStorage.getItem('access-admin'))
    if (!admin) {
      next({path: '/loginByAccount'})
    } else {
      if(admin){
        //校验token合法性
        axios({
          url:'http://localhost:9000/ssm/checkToken',
          method:'post',
          headers:{
            token:admin.token
          }
        }).then((response) => {
          console.log(response.data)
          if(!response.data){
            console.log('校验失败')
            next({path: '/error'})
          }
        })
        next()
      }
    }
  }
})




export default router

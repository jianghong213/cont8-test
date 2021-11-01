import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
//导入登录页面组件
import Login from '@/views/login.vue'
import Home from '@/views/Home.vue'
import Register from "@/views/register";
//import Book from "@/views/book.vue";


Vue.use(Router)

const router = new Router({
  mode: "history",
  routes: [
     {
       path: '/',
       name: 'Default',
       redirect: '/login',
       component: Home,
     },
    {
       // home页面并不需要被访问，只是作为其它组件的父组件
      path: '/home',
      name: 'Home',
      component: Home,
      meta: {
        requireAuth: true
      },
      redirect: '/index',
      children:[
        {
          path:'/index',
          name:'Index',
          component:() => import('@/views/home/index'),
          meta:{
            requireAuth:true
          }
        },
        {
          path:'/user',
          name:'User',
          component:()=>import('@/views/user/index'),
          meta:{
            requireAuth:true
          }
        },
        {
          path:'/book',
          name:'Book',
          component:()=>import('@/views/book/index'),
          meta:{
            requireAuth:true
          }
        }
      ]
    },
    //添加登录页面路由
    {
      path:'/login',
      name: 'Login',
      component: Login
    },
    {
      path:'/register',
      name: 'Register',
      component: Register
    },
    // {
    //   path:'/index',
    //   name:'Index',
    //   component:() => import('@/views/home/index'),
    //   meta:{
    //     requireAuth:true
    //   }
    // },

    {
      path:'/user',
      name:'User',
      component:()=>import('@/views/user/index'),
      meta:{
        requireAuth:true
      }
    },


  ]
})

router.beforeEach((to, from, next) => {
  next()
})


export default router

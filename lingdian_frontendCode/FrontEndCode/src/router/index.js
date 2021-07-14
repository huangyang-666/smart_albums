import Vue from 'vue'
import Router from 'vue-router'

import Login from "../views/user/Login";
import Register from "../views/user/Register";
import Home from "../views/user/Home";
import NotFound from "../views/error/NotFound"
import Password from "../views/user/Password";


import Page0 from '../views/Operate/Page0'
import Home1 from '../views/user/Home1'
import Page1 from '../views/Operate/Page1'
import Page1_2 from '../views/Operate/Page1_2'
import Show from '../views/Operate/Show'

import Page2 from '../views/Operate/Page2'
import Page3 from '../views/Operate/Page3'
import Page4 from '../views/Operate/Page4'
import test from '../views/Operate/test'
import Human from '../views/type/Humans'
import Animals from '../views/type/Animals'
import Sceneries from '../views/type/Sceneries'
import Vehicles from '../views/type/Vehicles'
import Constructs from '../views/type/Constructs'
import More from '../views/type/More'

import Character1 from '../views/video/Character1'
import Sceneries1 from '../views/video/Sceneries1'
import Vehicles1 from '../views/video/Vehicles1'
import Animals1 from '../views/video/Animals1'
import Download from '../views/Operate/Download'
import Humans from '../views/type/Humans'
import face from '../views/face/face'
import Add from '../views/face/Add'
import AddFace from '../views/face/AddFace'
import Constructs1 from "../views/video/Constructs1";
import More1 from "../views/video/More1";
Vue.use(Router);


export default new Router({
  //去掉/#哈希模式->路由模式
  mode:'history',
  routes: [
    {
      //登录页
      path:'/login',
      name:'Login',
      component:Login,
      meta: {
        roles: ['admin', 'user']
      },
    },
    {
      //注册页
      path:'/register',
      name:'Register',
      component:Register,
      meta: {
        roles: ['admin', 'user']
      },
    },
    {
      //首页
      path: '/Home',
      name: 'Home',
      component: Home,
      children:[
        {
          //图片首页
          path:'/home1',
          name: 'Home1',
          component: Home1
        },
        {
          //全部图片
          path:'/all',
          name: 'Page0',
          component:Page0
        },
        {
          //全部图片
          path:'/password',
          name: 'Password',
          component:Password
        },
        {
          //空
          path:'/Download',
          name: 'Download',
          component:Download
        },

        {
          //图片上传
          path:'/upload',
          name: 'Page1',
          component: Page1
        },
        {
          //智能图片分类上传
          path:'/loads',
          name: 'Page1_2',
          component: Page1_2,
        },
        {
          //展示
          path: '/show',
          name: 'Show',
          component: Show
        },
        {
          //相册类别
          path: '/type',
          name: 'Page2',
          component: Page2
        },
        {
          //人物
          path:'/humans',
          name:'Humans',
          component:Humans
        },
        {
          //动物
          path:'/animals',
          name:'Animals',
          component:Animals
        },
        {
          //风景
          path:'/sceneries',
          name:'Sceneries',
          component:Sceneries
        },
        {
          //交通
          path:'/vehicles',
          name:'Vehicles',
          component:Vehicles
        },
        {
          //建筑
          path:'/constructs',
          name:'Constructs',
          component:Constructs
        },
        {
          //更多
          path:'/more',
          name:'More',
          component:More
        },
        {
          //人体识别
          path: '/recognition',
          name: 'Page3',
          component: Page3
        },

        {
          //face
          path: '/face',
          name: 'face',
          component: face
        },
        {
          //face
          path: '/AddFace',
          name: 'AddFace',
          component: AddFace
        },
        {
          //add
          path: '/Add',
          name: 'add',
          component: Add
        },
        {
          //精彩时刻
          path: '/moment',
          name: 'Page4',
          component: Page4
        },

        {
          //精彩时刻
          path: '/Character1',
          name: 'Character1',
          component: Character1
        },
        {
          //精彩时刻
          path: '/Sceneries1',
          name: 'Sceneries1',
          component: Sceneries1
        },
        {
          //精彩时刻
          path: '/Vehicles1',
          name: 'Vehicles1',
          component: Vehicles1
        },
        {
          //精彩时刻
          path: '/Animals1',
          name: 'Animals1',
          component: Animals1
        },
        {
          //精彩时刻
          path: '/Constructs1',
          name: 'Constructs1',
          component: Constructs1
        },{
          //精彩时刻
          path: '/More1',
          name: 'More1',
          component: More1
        },



        {
          //精彩时刻
          path: '/test',
          name: 'test',
          component: test
        },
      ]
    },

    {
      //  回到的首页
      path: '/goMain/:username',
      //重定向
      redirect:'/main/:username'
    },
    {
      path: '*',
      component: NotFound
    },
  ]
})



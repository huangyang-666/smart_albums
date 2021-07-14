import Vue from 'vue'

import App from './App'
import VueRouter from 'vue-router'
// import VueResource from 'vue-resource';
// Vue.use(VueResource)
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import router from './router'


import Video from 'video.js'
import 'video.js/dist/video-js.css'

Vue.prototype.$video = Video;


// 引入瀑布流 vue-waterfall2
import waterfall from 'vue-waterfall2';
Vue.use(waterfall);

//挂在axios
import {Axios} from './utils/axios'

Vue.config.productionTip = false

Vue.prototype.$http = Axios;
//设置访问跟路径
// axios.defaults.baseURL = "http://localhost:8086"

import Vuex from 'vuex'

import store from "./store";

import scroll from 'vue-seamless-scroll'
Vue.use(scroll);

const whiteList = ['/login', '/register']; // 不重定向白名单
//路由跳转前
router.beforeEach((to, from, next) => {
  let isLogin = sessionStorage.getItem('isLogin');

  //注销
  if (to.path === '/logout') {
    sessionStorage.clear();
    next({path: '/login'});
  } else if (to.path === '/login') {
    if (isLogin != null) {
      next({path: '/main'})
    }
  } else if (isLogin == null) {
    if (whiteList.indexOf(to.path) !== -1) {
      next()
    }else
      next({path: '/login'})
  }

  next();
});

Vue.use(Vuex);
Vue.use(VueRouter);
Vue.use(ElementUI);


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
});

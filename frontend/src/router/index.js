import { createRouter, createWebHashHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Home from "@/views/Home.vue";

import HomePage from "@/views/front-views/HomePage.vue";

import Labs from "@/views/front-views/Labs.vue";

import News from "@/views/front-views/News.vue";
import Information from "@/views/front-views/news/Information.vue";
import Notices from "@/views/front-views/news/Notices.vue";

import Res from "@/views/front-views/Res.vue";
import Audio from "@/views/front-views/res/Audio.vue";
import Doc from "@/views/front-views/res/Doc.vue";
import Video from "@/views/front-views/res/Video.vue";
import Virtual from "@/views/front-views/res/Virtual.vue";

import Shared from "@/views/front-views/Shared.vue";
import SharedLab from "@/views/front-views/shared/SharedLab.vue";
import SharedRes from "@/views/front-views/shared/SharedRes.vue";

import Training from "@/views/front-views/Training.vue";

import UserCenter from "@/views/front-views/UserCenter.vue";
import MyCollection from "@/views/front-views/user-center/MyCollection.vue";
import MyCourse from "@/views/front-views/user-center/MyCourse.vue";
import MyExperiment from "@/views/front-views/user-center/MyExperiment.vue";
import MyInfo from "@/views/front-views/user-center/MyInfo.vue";
import MyMsg from "@/views/front-views/user-center/MyMsg.vue";

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    children:[
      {path: 'homePage', name: 'HomePage', component: HomePage},
      {path: 'labs', name: 'Labs', component: Labs},
      {path: 'news', name: 'News', component: News, children:[
          {path: 'information', name: 'Information', component: Information},
          {path: 'notices', name: 'Notices', component: Notices},
        ]},
      {path: 'res', name: 'Res', component: Res, children:[
          {path: 'audio', name: 'Audio', component: Audio},
          {path: 'doc', name: 'Doc', component: Doc},
          {path: 'video', name: 'Video', component: Video},
          {path: 'virtual', name: 'Virtual', component: Virtual},
        ]},
      {path: 'shared', name: 'Shared', component: Shared, children:[
          {path: 'sharedLab', name: 'SharedLab', component: SharedLab},
          {path: 'sharedRes', name: 'SharedRes', component: SharedRes},
        ]},
      {path: 'training', name: 'Training', component: Training},
      {path: 'userCenter', name: 'UserCenter', component: UserCenter, children:[
          {path: 'myCollection', name: 'MyCollection', component: MyCollection},
          {path: 'myCourse', name: 'MyCourse', component: MyCourse},
          {path: 'myExperiment', name: 'MyExperiment', component: MyExperiment},
          {path: 'myInfo', name: 'MyInfo', component: MyInfo},
          {path: 'myMsg', name: 'MyMsg', component: MyMsg},
        ]},
    ]
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router

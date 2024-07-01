import { createRouter, createWebHashHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Home from "@/views/Home.vue";

import HomePage from "@/views/main-views/HomePage.vue";

import Labs from "@/views/main-views/Labs.vue";

import News from "@/views/main-views/News.vue";
import Information from "@/views/main-views/news/Information.vue";
import Notices from "@/views/main-views/news/Notices.vue";

import Res from "@/views/main-views/Res.vue";
import Audio from "@/views/main-views/res/Audio.vue";
import Doc from "@/views/main-views/res/Doc.vue";
import Video from "@/views/main-views/res/Video.vue";
import Virtual from "@/views/main-views/res/Virtual.vue";

import Shared from "@/views/main-views/Shared.vue";
import SharedLab from "@/views/main-views/shared/SharedLab.vue";
import SharedRes from "@/views/main-views/shared/SharedRes.vue";

import Training from "@/views/main-views/Training.vue";

import UserCenter from "@/views/main-views/UserCenter.vue";
import MyCollection from "@/views/main-views/user-center/MyCollection.vue";
import MyCourse from "@/views/main-views/user-center/MyCourse.vue";
import MyExperiment from "@/views/main-views/user-center/MyExperiment.vue";
import MyInfo from "@/views/main-views/user-center/MyInfo.vue";
import MyMsg from "@/views/main-views/user-center/MyMsg.vue";

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

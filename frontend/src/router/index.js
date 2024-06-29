import { createRouter, createWebHashHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Home from "@/views/Home.vue";

import HomePage from "@/views/main-views/HomePage.vue";

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
    ]
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router

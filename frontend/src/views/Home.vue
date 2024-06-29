<template>
  <div class="main-body">
    <el-backtop :right="10" :bottom="50" />
    <!--总容器-->
    <el-container>
      <!--头部-->
      <el-header class="head-container">
        <el-affix
            :offset="state.headMenuOffset"
        >
          <el-menu
              :router="true"
              :ellipsis="false"
              :default-active="state.defaultPage"
              class="head-menu"
              mode="horizontal"
              @select="handleSelect"
              background-color="#ffffe0"
              text-color="#333333"
          >
            <!--左侧头部-->
            <a href="https://ys.mihoyo.com/" title="进入官网" target="_blank">
              <img
                  class="main-logo"
                  src="@/assets/logo.png"
                  alt="Logo"
              />
            </a>
            <el-text size="large" style="color: #333333; margin-right: 10px; white-space: nowrap">
              圆舟在线教育平台
            </el-text>

            <el-menu-item
                v-for="item in userHeadMenuItemList"
                :index="item.path"
            >
              <el-icon v-if="item.isHomePage">
                <home-filled />
              </el-icon>
              {{item.label}}
            </el-menu-item>

            <!--右侧头部-->
            <div class="head-container-right"/>

            <el-sub-menu
                index="/"
            >
              <template #title>
                <el-icon>
                  <user />
                </el-icon>
                {{state.user?.nickname || '用户'}}
              </template>
              <el-menu-item @click="help">帮助中心</el-menu-item>
              <el-menu-item @click="about">关于</el-menu-item>
              <el-menu-item @click="logout">退出登录</el-menu-item>
            </el-sub-menu>
          </el-menu>
        </el-affix>

      </el-header>
      <!--主体-->
      <el-main
          class="main-container"
      >
        <router-view v-slot="{ Component }">
          <component :is="Component" />
        </router-view>
      </el-main>

    </el-container>

  </div>

</template>



<script setup>
import {h, onMounted, reactive} from 'vue'
import router from "@/router/index.js";
import {HomeFilled, User} from "@element-plus/icons-vue";
import {ElMessageBox} from "element-plus";
//import {axiosGet} from "@/utils/axiosUtil.js";

onMounted(initialize)

const userHeadMenuItemList = [
  {label: '首页', path: '/home/homePage', isHomePage: true},
  {label: '新闻公告', path: '/home/news'},
  {label: '资源中心', path: '/home/res'},
  {label: '实训中心', path: '/home/training'},
  {label: '实验室', path: '/home/labs'},
  {label: '共享开发', path: '/home/shared'},
  {label: '用户中心', path: '/home/userCenter'},
]

const state = reactive({
  nowMenuActive: '',  //当前首部栏界面
  defaultPage: '',  //当前路由界面
  headMenuOffset: 0,  //首部菜单锚点偏移量
  user: null,  //用户
})

function help(){
  window.open('https://sr.mihoyo.com/', '_blank');
}

async function about(){
  let yuan_zhou_version = 'v0.1.0_Alpha'

  await ElMessageBox({
    title: '关于',
    message: h('p', null, [
      h('div', null,[
        h('span', null, 'yuan-zhou '),
        h('b', {style: 'color: teal'}, yuan_zhou_version)
      ])
    ]),
    confirmButtonText: '确定',
  })
}

function logout(){
  localStorage.setItem("token", "");
  router.push("/")
}

//点击首部菜单
const handleSelect = (key) => {
  state.nowMenuActive = key;
  pageChange(key)
}

const pageChange = (key = '') => {
  if(key === ''){
    const CURRENT_PATH = window.location.hash  // 获取当前路径，例如 "#/page/subpage"
    const pageList = CURRENT_PATH.split('#')
    state.defaultPage = pageList[pageList.length - 1]
    state.nowMenuActive = state.defaultPage
  }
  else{
    state.nowMenuActive = key
  }
  //homePage中的首部菜单固定在首部, 其他界面不固定
  state.headMenuOffset = state.nowMenuActive === '/home/homePage' ? 0 : -65535
  console.log('pageChange', state.nowMenuActive)
}

//初始化
async function initialize(){
  pageChange()
  // const userJson = localStorage.getItem("user") || '';
  // if(!userJson){
  //   ElMessage.error("用户信息获取失败，请重新登录！")
  //   await router.push('/')
  // }
  // else{
  //   state.user = JSON.parse(userJson)
  //   console.log("user", state.user)
  // }
  // const result = await axiosGet({url: '/auth', name: 'auth'})
  // if(!result){
  //   await router.push('/')
  // }
}
</script>


<style scoped>
.main-body {
  display: flex;
  justify-content: center;
  min-width: 100vh;
  min-height: 100vh;
  background: rgb(255, 255, 255);
  background-size: contain;
}
.head-container{
  padding: 0;
  width: 100%;
}
.main-container{
  display: flex;
  padding: 0;
  max-width: 100%;
}
.head-menu {
  width: 100%;
  border: 0 !important;
}
.main-logo {
  width: 40px;
  height: 40px;
  margin-top: 8px;
  margin-left: 10px;
  margin-right: 10px;
}
.head-container-right {
  flex-grow: 1;
}
</style>
<template>
  <el-container>
    <el-aside
        class="side-bar"
        width="collapse"
    >
      <SideBar
          class="side-menu"
          :menu-list="sideMenu"
          @selectMenu="handleSelect"
      >
      </SideBar>
    </el-aside>

    <el-main
        class="main-container"
    >
      <my-tab
          ref="myTab"
          :default-tab="defaultTab"
      >
      </my-tab>
    </el-main>

  </el-container>
</template>



<script setup>
import {onMounted, ref} from "vue";
import SideBar from "@/components/SideBar.vue";
import MyTab from "@/components/MyTab.vue";

onMounted(init)

//侧边菜单内容
const sideMenu = [
  {name: 'MyInfo', label: "我的信息", path: "/home/userCenter/myInfo", icon: "User"},
  {name: 'MyMsg', label: "我的消息", path: "/home/userCenter/myMsg", icon: "Message"},
  {name: 'MyCourse', label: "我的课程", path: "/home/userCenter/myCourse", icon: "DataBoard"},
  {name: 'MyExperiment', label: "我的实验", path: "/home/userCenter/myExperiment", icon: "DataAnalysis"},
  {name: 'MyCollection', label: "我的收藏", path: "/home/userCenter/myCollection", icon: "Collection"},
];

//初始tab内容
const defaultTab = {name: 'MyInfo', label: "我的信息", path: "/home/userCenter/myInfo"}

let myTab = ref(null);

//初始化函数
function init() {}

//点击侧边栏菜单
function handleSelect(menu){
  myTab.value.addTab(menu.name, menu.label, menu.path)
}
</script>

<style scoped>
.side-menu {
  height: 100%;
  border: 0 !important;
}
.main-container{
  padding: 5px;
}
</style>
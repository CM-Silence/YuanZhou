<template>
  <el-container>
    <el-aside
        class="side-bar"
        width="collapse"
    >
      <SideBar
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
  {name: 'NewsManagement', label: "新闻公告", path: "/home/integratedManagement/newsManagement", icon: "Bell"},
  {name: 'PlatformPersonnel', label: "平台人员", path: "/home/integratedManagement/platformPersonnel", icon: "User"},
  {name: 'PlatformSetting', label: "平台设置", path: "/home/integratedManagement/platformSetting", icon: "Setting"},
];

//初始tab内容
const defaultTab = {name: 'NewsManagement', label: "新闻公告", path: "/home/integratedManagement/newsManagement"}

let myTab = ref(null);

//初始化函数
function init() {}

//点击侧边栏菜单
function handleSelect(menu){
  myTab.value.addTab(menu.name, menu.label, menu.path)
}
</script>

<style scoped>
.main-container{
  padding: 5px;
  overflow-y: hidden;
}
</style>
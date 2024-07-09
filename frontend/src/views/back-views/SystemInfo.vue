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
  {name: 'UserManagement', label: "个人中心", path: "/home/systemInfo/userManagement", icon: "User"},
  {name: 'SystemUser', label: "系统用户", path: "/home/systemInfo/systemUser", icon: "Avatar"},
  {name: 'DataDictionary', label: "数据字典", path: "/home/systemInfo/dataDictionary", icon: "Grid"},
  {name: 'SystemLog', label: "系统日志", path: "/home/systemInfo/systemLog", icon: "Memo"},
];

//初始tab内容
const defaultTab = {name: 'UserManagement', label: "个人中心", path: "/home/systemInfo/userManagement"}

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
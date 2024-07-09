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
  {name: 'LabsApply', label: "实验室", path: "/home/resManagement/labsApply", icon: "OfficeBuilding"},
  {name: 'DevicesManagement', label: "仿真设备", path: "/home/resManagement/devicesManagement", icon: "Monitor"},
  {name: 'TrainingResManagement', label: "实训资源", path: "/home/resManagement/trainingResManagement", icon: "DataBoard"},
  {name: 'TeacherManagement', label: "师资管理", path: "/home/resManagement/teacherManagement", icon: "User"},
  {name: 'LabsApply', label: "实验室申请", path: "/home/resManagement/labsApply", icon: "Tickets"},
];

//初始tab内容
const defaultTab = {name: 'LabsApply', label: "实验室", path: "/home/resManagement/labsApply"}

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
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
  {name: 'CourseManagement', label: "课程管理", path: "/home/trainingManagement/courseManagement", icon: "Reading"},
  {name: 'TrainingExperiment', label: "实训实验", path: "/home/trainingManagement/trainingExperiment", icon: "DataBoard"},
  {name: 'TeachingPlan', label: "教学计划", path: "/home/trainingManagement/teachingPlan", icon: "Document"},
  {name: 'ProcessResults', label: "过程结果", path: "/home/trainingManagement/processResults", icon: "Aim"},
];

//初始tab内容
const defaultTab = {name: 'CourseManagement', label: "课程管理", path: "/home/trainingManagement/courseManagement"}

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
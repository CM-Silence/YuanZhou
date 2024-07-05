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
  {name: 'Audio', label: "音频资源", path: "/home/res/audio", icon: "Headset"},
  {name: 'Video', label: "视频资源", path: "/home/res/video", icon: "VideoCamera"},
  {name: 'Doc', label: "文档资源", path: "/home/res/doc", icon: "Document"},
  {name: 'Virtual', label: "虚拟资源", path: "/home/res/virtual", icon: "Monitor"},
];

//初始tab内容
const defaultTab = {name: 'Audio', label: "音频资源", path: "/home/res/audio"}

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
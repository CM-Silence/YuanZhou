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
import {useRoute} from "vue-router";
const route = useRoute();
onMounted(init)

//侧边菜单内容
const sideMenu = [
  {name: 'Information', label: "新闻资讯", path: "/home/news/information", icon: "Tickets"},
  {name: 'Notices', label: "公告通知", path: "/home/news/notices", icon: "Bell"},
];

//初始tab内容
const defaultTab = {name: 'Information', label: "新闻资讯", path: "/home/news/information"}

let myTab = ref(null);

//初始化函数
function init() {
  if(route.query.newsId){
    const result = localStorage.getItem(route.query.newsId) || ''
    if(result){
      const res = JSON.parse(result)
      myTab.value.addTab('NewsShowView', res.title, `${route.path}?newsId=${route.query.newsId}`)
    }
  }
}

//点击侧边栏菜单
function handleSelect(menu){
  myTab.value.addTab(menu.name, menu.label, menu.path)
}
</script>

<style scoped>
.main-container{
  padding: 5px;
}
</style>
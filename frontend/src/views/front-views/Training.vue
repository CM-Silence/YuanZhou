<template>
  <el-container>
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
import MyTab from "@/components/MyTab.vue";

onMounted(init)

//初始tab内容
const defaultTab = {name: 'TrainingCenter', label: "实训中心", path: "/home/training/trainingCenter"}

let myTab = ref(null);

//初始化函数
function init() {}

</script>

<style scoped>
.main-container{
  padding: 5px;
}
</style>
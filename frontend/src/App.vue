<template>
  <el-config-provider :locale="zhCn">
    <!--  根据路由展示页面-->
    <router-view/>
  </el-config-provider>
</template>

<script setup>
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
</script>

<style>

</style>

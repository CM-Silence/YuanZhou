<template>
  <data-show-view
      :urls="urls"
      title-data="name"
      data-show-view-url="/home/labs/labsShowView"
      data-show-view-params="labsId"
      key-data="lid"
      @click-card="addTab"
  />
</template>

<script setup>
import DataShowView from "@/components/DataShowView.vue";

//对外事件列表
const emit = defineEmits(["addTab"]);
const addTab = (title, url) => {
  emit("addTab", title, title, url)
}

const urls = {
  getData: '/labs/list'
}

</script>

<style scoped>

</style>
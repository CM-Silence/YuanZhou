<template>
  <data-show-view
      :urls="urls"
      data-show-view-url="/home/training/trainingShowView"
      data-show-view-params="trainingId"
      key-data="tid"
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
  getData: '/training/list'
}

</script>

<style scoped>

</style>
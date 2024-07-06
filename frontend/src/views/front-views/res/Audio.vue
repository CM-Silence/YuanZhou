<template>
<data-show-view
  :extra-params="extraParams"
  :urls="urls"
  key-data="rid"
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

const extraParams = {
  type: 3,
}

const urls = {
  getData: '/res/list'
}

</script>

<style scoped>

</style>
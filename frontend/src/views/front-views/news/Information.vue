<template>
  <data-show-list
      :extra-params="extraParams"
      :urls="urls"
      key-data="mid"
      @click-item="addTab"/>
</template>

<script setup>
import DataShowList from "@/components/DataShowList.vue";

//对外事件列表
const emit = defineEmits(["addTab"]);
const addTab = (title, url) => {
  emit("addTab", title, title, url)
}

const extraParams = {
  type: 0,
}

const urls = {
  getData: '/news/list'
}
</script>

<style scoped>

</style>
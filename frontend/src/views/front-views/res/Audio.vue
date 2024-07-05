<template>
<data-show-view
  :extra-params="extraParams"
  :urls="urls"
/>
</template>

<script setup>
import DataShowView from "@/components/DataShowView.vue";

const extraParams = {
  type: 1,
}

const urls = {
  getData: '/res/list'
}

</script>

<style scoped>

</style>
<template>
  <table-view
      key-data="aid"
      search-data="name"
      :table-col-list="tableColList"
      :extra-params="extraParams"
      :urls="urls"
      :ban-search="true"
      large
      download
  />
</template>

<script setup>
import TableView from "@/components/TableView.vue";
import {CURRENT_USER} from "@/utils/appManager";

const extraParams = {
  key_word: CURRENT_USER.value.uid
}

/**
 * 表头属性列表
 * */
const tableColList = [
  {property: "audited_type", label: "审批状态", sortable: false, width: 100,
    isMapping: true, mappingList:[
      {label: '通过', value: 1},
      {label: '未通过', value: 0},
    ]},
  {property: "lab", label: "实验室", sortable: false, width: 120, isFK: true,
    FKData:{
      url: "/labs/list",
      property: "lid",
      label: "name"
    }},
  {property: "created_at", label: "申请时间", isDateFormat: true, sortable: false, width: 240},
  {property: "audited_time", label: "审批时间", isDateFormat: true, sortable: false, width: 240},
  {property: "comment", label: "审批人备注", sortable: false, width: 300, isTextArea: true},
]

/**
 * 网络请求url
 * */
const urls = {
  getData: "/application/list",
}

</script>

<style scoped>

</style>
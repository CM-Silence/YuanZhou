<template>
  <table-view
      key-data="mid"
      :table-col-list="tableColList"
      :add-form="addForm"
      :edit-form="editForm"
      :urls="urls"
      large
      delete
      download
  />
</template>

<script setup>
import TableView from "@/components/TableView.vue";

/**
 * 表头属性列表
 * */
const tableColList = [
  {property: "img1", label: "缩略图", sortable: false, isImage: true, width: 80},
  {property: "title", label: "标题", sortable: false, width: 120},
  {property: "src", label: "来源", sortable: false, width: 100},
  {property: "editor", label: "编辑", sortable: false, width: 100},
  {property: "type", label: "类型", sortable: false, width: 100},
  {property: "view_amount", label: "浏览量", sortable: false, width: 120},
  {property: "content", label: "内容", sortable: false, width: 240},
  {property: "files1", label: "附件", sortable: false, isFile: true, width: 80},
  {property: "created_at", label: "添加时间", isDateFormat: true, sortable: true, width: 240},
]

/**
 * 编辑部门时所用到的表单对象
 * */
const editForm = {
  data :{
    did:'',
    name:'',
  },
  dataType:{
    did:'String',
    name:'String',
  },
  dataNum: 2,
  rules: {
    name: [
      { required: 'true', message: '部门名不能为空', trigger: 'blur' },
    ],
  },
  item:[
    {label: '部门名称', prop: 'name', dataName: 'name', isInput: true},
  ],
}

/**
 * 添加部门时所用到的对象
 * */
const addForm = {
  data :{
    name:'',
  },
  dataType:{
    name:'String',
  },
  dataNum: 1,
  rules: {
    name: [
      { required: 'true', message: '部门名不能为空', trigger: 'blur' },
    ],
  },
  item:[
    {label: '部门名称', prop: 'name', dataName: 'name', isInput: true},
  ],
}

/**
 * 网络请求url
 * */
const urls = {
  getData: "/news/list",
  deleteData: "/news/delete",
  addData: "/news/add",
  updateData: "/news/update",
}

</script>

<style scoped>

</style>
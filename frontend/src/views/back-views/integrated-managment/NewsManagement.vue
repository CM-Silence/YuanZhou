<template>
  <table-view
      key-data="mid"
      search-data="title"
      :table-col-list="tableColList"
      :add-form="addForm"
      :edit-form="editForm"
      :urls="urls"
      large
      delete
      download
      upload-file
      upload-img
  />
</template>

<script setup>
import TableView from "@/components/TableView.vue";

/**
 * 表头属性列表
 * */
const tableColList = [
  {property: "img1", label: "缩略图", sortable: false, isImage: true, width: 120},
  {property: "files1", label: "附件", sortable: false, isFile: true, width: 80},
  {property: "title", label: "标题", sortable: false, width: 120},
  {property: "src", label: "来源", sortable: false, width: 100},
  {property: "editor", label: "编辑", sortable: false, width: 100},
  {property: "type", label: "类型", sortable: false, width: 100, isMapping: true, mappingList:[
      {label: "新闻资讯", value: 1},
      {label: "公告通知", value: 2},
    ]},
  {property: "view_amount", label: "浏览量", sortable: false, width: 120},
  {property: "content", label: "内容", sortable: false, width: 240, isTextArea: true},
  {property: "created_at", label: "添加时间", isDateFormat: true, sortable: true, width: 240},
]

/**
 * 编辑部门时所用到的表单对象
 * */
const editForm = {
  data :{
    mid: '',
    title: '',
    src: '',
    editor: '',
    content: '',
    type: '',
  },
  dataType:{
    mid: 'String',
    title: 'String',
    src: 'String',
    editor: 'String',
    content: 'String',
    type: 'Int',
  },
  dataNum: 6,
  rules: {
    title: [
      { required: 'true', message: '标题不能为空', trigger: 'blur' },
    ],
    src: [
      { required: 'true', message: '来源不能为空', trigger: 'blur' },
    ],
    editor: [
      { required: 'true', message: '编辑不能为空', trigger: 'blur' },
    ],
    content: [
      { required: 'true', message: '内容不能为空', trigger: 'blur' },
    ],
    type: [
      { required: 'true', message: '请选择消息类型', trigger: 'blur' },
    ],
  },
  item:[
    {label: '标题', prop: 'title', dataName: 'title', isInput: true},
    {label: '来源', prop: 'src', dataName: 'src', isInput: true},
    {label: '编辑', prop: 'editor', dataName: 'editor', isInput: true},
    {label: '类型', prop: 'type', dataName: 'type', isSelect: true,
      selectOptions: [
        {label: '新闻资讯', value: 1},
        {label: '公告通知', value: 2},
      ]},
    {label: '内容', prop: 'content', dataName: 'content', isTextArea: true, maxLength: 2000},
  ],
}

/**
 * 添加部门时所用到的对象
 * */
const addForm = {
  data :{
    title: '',
    src: '',
    editor: '',
    content: '',
    type: '',
  },
  dataType:{
    title: 'String',
    src: 'String',
    editor: 'String',
    content: 'String',
    type: 'Int',
  },
  dataNum: 5,
  rules: {
    title: [
      { required: 'true', message: '标题不能为空', trigger: 'blur' },
    ],
    src: [
      { required: 'true', message: '来源不能为空', trigger: 'blur' },
    ],
    editor: [
      { required: 'true', message: '编辑不能为空', trigger: 'blur' },
    ],
    content: [
      { required: 'true', message: '内容不能为空', trigger: 'blur' },
    ],
    type: [
      { required: 'true', message: '请选择消息类型', trigger: 'blur' },
    ],
  },
  item:[
    {label: '标题', prop: 'title', dataName: 'title', isInput: true},
    {label: '来源', prop: 'src', dataName: 'src', isInput: true},
    {label: '编辑', prop: 'editor', dataName: 'editor', isInput: true},
    {label: '类型', prop: 'type', dataName: 'type', isSelect: true,
      selectOptions: [
        {label: '新闻资讯', value: 1},
        {label: '公告通知', value: 2},
      ]},
    {label: '内容', prop: 'content', dataName: 'content', isTextArea: true, maxLength: 2000},
  ],
}

/**
 * 网络请求url
 * */
const urls = {
  getData: "/news/list",
  deleteData: "/news/delete",
  addData: "/news/add",
  updateData: "/news/edit",
}

</script>

<style scoped>

</style>
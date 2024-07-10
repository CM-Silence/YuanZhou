<template>
  <table-view
      key-data="rid"
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
  {property: "major", label: "专业", sortable: false, width: 100},
  {property: "administrator", label: "管理员", sortable: false, width: 100},
  {property: "type", label: "类型", sortable: true, width: 100, isMapping: true, mappingList:[
      {label: "虚拟资源", value: 1},
      {label: "视频资源", value: 2},
      {label: "音频资源", value: 3},
      {label: "文档资源", value: 4},
    ]},
  {property: "view_amount", label: "浏览量", sortable: false, width: 120},
  {property: "like_amount", label: "点赞量", sortable: false, width: 120},
  {property: "collect_amount", label: "收藏量", sortable: false, width: 120},
  {property: "content", label: "简介", sortable: false, width: 240, isTextArea: true},
  {property: "created_at", label: "添加时间", isDateFormat: true, sortable: true, width: 240},
]

/**
 * 编辑部门时所用到的表单对象
 * */
const editForm = {
  data :{
    rid: '',
    title: '',
    major: '',
    administrator: '',
    type: '',
    content: '',
  },
  dataType:{
    rid: 'String',
    title: 'String',
    major: 'String',
    administrator: 'String',
    type: 'Int',
    content: 'String',
  },
  dataNum: 6,
  rules: {
    title: [
      { required: 'true', message: '标题不能为空', trigger: 'blur' },
    ],
    administrator: [
      { required: 'true', message: '管理员不能为空', trigger: 'blur' },
    ],
    type: [
      { required: 'true', message: '请选择资源类型', trigger: 'blur' },
    ],
  },
  item:[
    {label: '标题', prop: 'title', dataName: 'title', isInput: true},
    {label: '管理员', prop: 'administrator', dataName: 'administrator', isInput: true},
    {label: '类型', prop: 'type', dataName: 'type', isSelect: true,
      selectOptions: [
        {label: "虚拟资源", value: 1},
        {label: "视频资源", value: 2},
        {label: "音频资源", value: 3},
        {label: "文档资源", value: 4},
      ]},
    {label: '专业', prop: 'major', dataName: 'major', isInput: true},
    {label: '简介', prop: 'content', dataName: 'content', isTextArea: true, maxLength: 500},
  ],
}

/**
 * 添加部门时所用到的对象
 * */
const addForm = {
  data :{
    title: '',
    major: '',
    administrator: '',
    type: '',
    content: '',
  },
  dataType:{
    title: 'String',
    major: 'String',
    administrator: 'String',
    type: 'Int',
    content: 'String',
  },
  dataNum: 6,
  rules: {
    title: [
      { required: 'true', message: '标题不能为空', trigger: 'blur' },
    ],
    administrator: [
      { required: 'true', message: '管理员不能为空', trigger: 'blur' },
    ],
    type: [
      { required: 'true', message: '请选择资源类型', trigger: 'blur' },
    ],
  },
  item:[
    {label: '标题', prop: 'title', dataName: 'title', isInput: true},
    {label: '管理员', prop: 'administrator', dataName: 'administrator', isInput: true},
    {label: '类型', prop: 'type', dataName: 'type', isSelect: true,
      selectOptions: [
        {label: "虚拟资源", value: 1},
        {label: "视频资源", value: 2},
        {label: "音频资源", value: 3},
        {label: "文档资源", value: 4},
      ]},
    {label: '专业', prop: 'major', dataName: 'major', isInput: true},
    {label: '简介', prop: 'content', dataName: 'content', isTextArea: true, maxLength: 500},
  ],
}

/**
 * 网络请求url
 * */
const urls = {
  getData: "/res/list",
  deleteData: "/res/delete",
  addData: "/res/add",
  updateData: "/res/edit",
}

</script>

<style scoped>

</style>
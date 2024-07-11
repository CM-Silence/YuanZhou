<template>
  <table-view
      key-data="did"
      search-data="name"
      :table-col-list="tableColList"
      :add-form="addForm"
      :edit-form="editForm"
      :urls="urls"
      large
      delete
      download
      upload-img
  />
</template>

<script setup>
import TableView from "@/components/TableView.vue";
import {pIntValidatorNRequire, pNumValidatorNRequire} from "@/utils/validator";

/**
 * 表头属性列表
 * */
const tableColList = [
  {property: "img1", label: "缩略图", sortable: false, isImage: true, width: 120},
  {property: "name", label: "名称", sortable: false, width: 120},
  {property: "type", label: "类别", sortable: false, width: 100},
  {property: "software_system", label: "软件系统", sortable: false, width: 100},
  {property: "version", label: "版本号", sortable: false, width: 120},
  {property: "supplier", label: "供应商", sortable: false, width: 120},
  {property: "device_usage", label: "用途", sortable: false, width: 100},
  {property: "lab", label: "所在实验室", sortable: false, width: 100, isFK: true,
    FKData:{
      url: "/labs/list",
      property: "lid",
      label: "name"
    }},
  {property: "status", label: "状态", sortable: false, width: 100, isMapping: true, mappingList:[
      {label: '禁用', value: 0},
      {label: '启用', value: 1},
    ]},
  {property: "created_at", label: "创建时间", isDateFormat: true, sortable: true, width: 240},
  {property: "update_at", label: "更新时间", isDateFormat: true, sortable: true, width: 240},
]

/**
 * 编辑部门时所用到的表单对象
 * */
const editForm = {
  data :{
    did: '',
    name: '',
    type: '',
    software_system: '',
    version: '',
    supplier: '',
    device_usage: '',
    lab: '',
    status: 0,
  },
  dataType:{
    lid: 'String',
    name: 'String',
    type: 'String',
    software_system: 'String',
    version: 'String',
    supplier: 'String',
    device_usage: 'String',
    lab: 'String',
    status: 'Int',
  },
  dataNum: 9,
  rules: {
    name: [
      { required: 'true', message: '设备名称不能为空', trigger: 'blur' },
    ],
    lab: [
      { required: 'true', message: '设备所处实验室不能为空', trigger: 'blur' },
    ],
    status: [
      { required: 'true', message: '请选择设备状态', trigger: 'blur' },
    ]
  },
  item:[
    {label: '名称', prop: 'name', dataName: 'name', isInput: true},
    {label: '所在实验室', prop: 'lab', dataName: 'lab', isFK: true,
      FKData:{
        url: "/labs/list",
        property: "lid",
        label: "name"
      }},
    {label: '状态', prop: 'status', dataName: 'status', isSelect: true,
      selectOptions: [
        {label: '禁用', value: 0},
        {label: '启用', value: 1},
      ]},
    {label: '类别', prop: 'type', dataName: 'type', isInput: true},
    {label: '软件系统', prop: 'software_system', dataName: 'software_system', isInput: true},
    {label: '版本号', prop: 'version', dataName: 'version', isInput: true},
    {label: '供应商', prop: 'supplier', dataName: 'supplier', isInput: true},
    {label: '用途', prop: 'device_usage', dataName: 'device_usage', isInput: true},
  ],
}

/**
 * 添加部门时所用到的对象
 * */
const addForm = {
  data :{
    name: '',
    type: '',
    software_system: '',
    version: '',
    supplier: '',
    device_usage: '',
    lab: '',
    status: 0,
  },
  dataType:{
    name: 'String',
    type: 'String',
    software_system: 'String',
    version: 'String',
    supplier: 'String',
    device_usage: 'String',
    lab: 'String',
    status: 'Int',
  },
  dataNum: 8,
  rules: {
    name: [
      { required: 'true', message: '设备名称不能为空', trigger: 'blur' },
    ],
    lab: [
      { required: 'true', message: '设备所处实验室不能为空', trigger: 'blur' },
    ],
    status: [
      { required: 'true', message: '请选择设备状态', trigger: 'blur' },
    ]
  },
  item:[
    {label: '名称', prop: 'name', dataName: 'name', isInput: true},
    {label: '所在实验室', prop: 'lab', dataName: 'lab', isFK: true,
      FKData:{
        url: "/labs/list",
        property: "lid",
        label: "name"
      }},
    {label: '状态', prop: 'status', dataName: 'status', isSelect: true,
      selectOptions: [
        {label: '禁用', value: 0},
        {label: '启用', value: 1},
      ]},
    {label: '类别', prop: 'type', dataName: 'type', isInput: true},
    {label: '软件系统', prop: 'software_system', dataName: 'software_system', isInput: true},
    {label: '版本号', prop: 'version', dataName: 'version', isInput: true},
    {label: '供应商', prop: 'supplier', dataName: 'supplier', isInput: true},
    {label: '用途', prop: 'device_usage', dataName: 'device_usage', isInput: true},
  ],
}

/**
 * 网络请求url
 * */
const urls = {
  getData: "/devices/list",
  deleteData: "/devices/delete",
  addData: "/devices/add",
  updateData: "/devices/edit",
}

</script>

<style scoped>

</style>
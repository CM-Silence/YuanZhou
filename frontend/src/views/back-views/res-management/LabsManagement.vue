<template>
  <table-view
      key-data="lid"
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
  {property: "workstation_amount", label: "工位数", sortable: false, width: 100},
  {property: "area", label: "面积(m²)", sortable: false, width: 100},
  {property: "facility_value", label: "设施总值(万)", sortable: false, width: 120},
  {property: "address", label: "地址", sortable: false, width: 120},
  {property: "devices_amount", label: "关联设备数", sortable: false, width: 100},
  {property: "administrator", label: "管理员", sortable: false, width: 100, isFK: true,
    FKData:{
      url: "/staff/list",
      property: "sid",
      label: "name"
    }},
  {property: "phone", label: "联系电话", sortable: false, width: 120, isFK: true,
    FKData:{
      url: "/staff/list",
      property: "sid",
      label: "phone"
    }},
  {property: "created_at", label: "创建时间", isDateFormat: true, sortable: true, width: 240},
  {property: "update_at", label: "更新时间", isDateFormat: true, sortable: true, width: 240},
]

/**
 * 编辑部门时所用到的表单对象
 * */
const editForm = {
  data :{
    lid: '',
    name: '',
    workstation_amount: '',
    area: '',
    facility_value: '',
    address: '',
    devices_amount: '',
    administrator: '',
  },
  dataType:{
    lid: 'String',
    name: 'String',
    workstation_amount: 'Int',
    area: 'Float',
    facility_value: 'Float',
    address: 'String',
    devices_amount: 'Int',
    administrator: 'String',
  },
  dataNum: 8,
  rules: {
    name: [
      { required: 'true', message: '实验室名称不能为空', trigger: 'blur' },
    ],
    administrator: [
      { required: 'true', message: '管理员不能为空', trigger: 'blur' },
    ],
    workstation_amount:[
      { validator: pIntValidatorNRequire, trigger: 'blur' }
    ],
    area:[
      { validator: pNumValidatorNRequire, trigger: 'blur' }
    ],
    facility_value:[
      { validator: pNumValidatorNRequire, trigger: 'blur' }
    ],
    devices_amount:[
      { validator: pIntValidatorNRequire, trigger: 'blur' }
    ]
  },
  item:[
    {label: '名称', prop: 'name', dataName: 'name', isInput: true},
    {label: '管理员', prop: 'administrator', dataName: 'administrator', isFK: true,
      FKData:{
        url: "/staff/list",
        property: "sid",
        label: "name"
      }},
    {label: '工位数', prop: 'workstation_amount', dataName: 'workstation_amount', isInput: true},
    {label: '面积(m²)', prop: 'area', dataName: 'area', isInput: true},
    {label: '设施总值(万)', prop: 'facility_value', dataName: 'facility_value', isInput: true},
    {label: '地址', prop: 'address', dataName: 'address', isInput: true},
    {label: '关联设备数', prop: 'devices_amount', dataName: 'devices_amount', isInput: true},
  ],
}

/**
 * 添加部门时所用到的对象
 * */
const addForm = {
  data :{
    name: '',
    workstation_amount: '',
    area: '',
    facility_value: '',
    address: '',
    devices_amount: '',
    administrator: '',
  },
  dataType:{
    name: 'String',
    workstation_amount: 'Int',
    area: 'Float',
    facility_value: 'Float',
    address: 'String',
    devices_amount: 'Int',
    administrator: 'String',
  },
  dataNum: 7,
  rules: {
    name: [
      { required: 'true', message: '实验室名称不能为空', trigger: 'blur' },
    ],
    administrator: [
      { required: 'true', message: '管理员不能为空', trigger: 'blur' },
    ],
    workstation_amount:[
      { validator: pIntValidatorNRequire, trigger: 'blur' }
    ],
    area:[
      { validator: pNumValidatorNRequire, trigger: 'blur' }
    ],
    facility_value:[
      { validator: pNumValidatorNRequire, trigger: 'blur' }
    ],
    devices_amount:[
      { validator: pIntValidatorNRequire, trigger: 'blur' }
    ]
  },
  item:[
    {label: '名称', prop: 'name', dataName: 'name', isInput: true},
    {label: '管理员', prop: 'administrator', dataName: 'administrator', isFK: true,
      FKData:{
        url: "/staff/list",
        property: "sid",
        label: "name"
      }},
    {label: '工位数', prop: 'workstation_amount', dataName: 'workstation_amount', isInput: true},
    {label: '面积(m²)', prop: 'area', dataName: 'area', isInput: true},
    {label: '设施总值(万)', prop: 'facility_value', dataName: 'facility_value', isInput: true},
    {label: '地址', prop: 'address', dataName: 'address', isInput: true},
    {label: '关联设备数', prop: 'devices_amount', dataName: 'devices_amount', isInput: true},
  ],
}

/**
 * 网络请求url
 * */
const urls = {
  getData: "/labs/list",
  deleteData: "/labs/delete",
  addData: "/labs/add",
  updateData: "/labs/edit",
}

</script>

<style scoped>

</style>
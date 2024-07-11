<template>
  <table-view
      key-data="aid"
      search-data="name"
      :table-col-list="tableColList"
      :add-form="addForm"
      :edit-form="editForm"
      :urls="urls"
      has-refresh-event-bus
      large
      download
  />
</template>

<script setup>
import TableView from "@/components/TableView.vue";
import {pIntValidatorNRequire, pNumValidatorNRequire} from "@/utils/validator";
import EventBus from "@/utils/eventBus";
import {ElInput, ElMessage, ElMessageBox, ElSwitch} from "element-plus";
import {axiosPut} from "@/utils/axiosUtil";
import {h, ref} from "vue";

//自定义点击函数
const audit = (aid, isAudit) =>{
  if(!isAudit){
    const passed = ref(false)
    const comment = ref('')
    ElMessageBox({
      title: '实验室申请审核',
      message: () =>
          h('p', null, [
            h('div', null, [
              `申请单id：${aid}`,
            ]),
            h('div', null, [
              `是否通过审核(${passed.value ? '是' : '否'})`,
              h(ElSwitch, {
                modelValue: passed.value,
                style: 'margin-left: 10px',
                'onUpdate:modelValue': (val) => {passed.value = val},
              }),
            ]),
            h('div', null, [
              h(ElInput, {
                modelValue: comment.value,
                placeholder: "审核备注",
                'onUpdate:modelValue': (val) => {comment.value = val},
              }),
            ]),
          ])
    })
    .then(async () => {
      const result = await axiosPut({
        url: '/application/audit',
        data: {
          aid: aid,
          audited_type: passed.value ? 1 : 0,
          comment: comment.value
        },
        name: 'audit'
      })
      if(result){
        ElMessage.success("审核成功")
        EventBus.emit("refresh", 1)
      }
    })
    .catch(() => {})
  }
  else{
    ElMessageBox.confirm(
        `你确定要撤销${aid}的审核吗？`,
        '注意',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
        }
    )
    .then(async () => {
      const result = await axiosPut({
        url: '/application/audit',
        data: {
          aid: aid,
          audited_type: 0,
          audited_time: '',
          comment: ''
        },
        name: 'audit-revoke'
      })
      if(result){
        ElMessage.success("审核已撤销")
        EventBus.emit("refresh", 1)
      }
    })
    .catch(() => {})
  }
}

/**
 * 表头属性列表
 * */
const tableColList = [
  {property: "audited_type", label: "审批状态", sortable: false, width: 100, isFixed: true, operable: true, operationEvent: audit,
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
  {property: "applicant", label: "申请人", sortable: false, width: 100, isFK: true,
    FKData:{
      url: "/user/list",
      property: "uid",
      label: "name"
    }},
  {property: "applicant", label: "联系电话", sortable: false, width: 120, isFK: true,
    FKData:{
      url: "/user/list",
      property: "uid",
      label: "phone"
    }},
  {property: "created_at", label: "申请时间", isDateFormat: true, sortable: false, width: 240},
  {property: "start_time", label: "开始时间", isDateFormat: true, sortable: false, width: 240},
  {property: "end_time", label: "结束时间", isDateFormat: true, sortable: false, width: 240},
  {property: "audited_time", label: "审批时间", isDateFormat: true, sortable: false, width: 240},
  {property: "comment", label: "审批人备注", sortable: false, width: 300, isTextArea: true},
]

/**
 * 编辑部门时所用到的表单对象
 * */
const editForm = {
  data :{
    aid: '',
    lab: '',
    applicant: '',
  },
  dataType:{
    aid: 'String',
    lab: 'String',
    applicant: 'String',
  },
  dataNum: 3,
  rules: {
    lab: [
      { required: 'true', message: '请选择实验室', trigger: 'blur' },
    ],
    applicant: [
      { required: 'true', message: '请选择申请人', trigger: 'blur' },
    ]
  },
  item:[
    {label: '实验室', prop: 'lab', dataName: 'lab', isFK: true,
      FKData:{
        url: "/labs/list",
        property: "lid",
        label: "name"
      }},
    {label: '申请人', prop: 'applicant', dataName: 'applicant', isFK: true,
      FKData:{
        url: "/user/list",
        property: "uid",
        label: "name"
      }},
  ],
}

/**
 * 网络请求url
 * */
const urls = {
  getData: "/application/list",
  deleteData: "/application/delete",
  addData: "/application/add",
  updateData: "/application/edit",
}

</script>

<style scoped>

</style>
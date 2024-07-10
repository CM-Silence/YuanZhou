<template>
  <table-view
      key-data="aid"
      :table-col-list="tableColList"
      :add-form="addForm"
      :edit-form="editForm"
      :urls="urls"
      has-refresh-event-bus
      large
      delete
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
      title: '调拨审核',
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
        url: '/apply/audit',
        data: {
          tid: aid,
          passed: passed.value,
          audit_comment: comment.value
        },
        name: 'audit'
      })
      if(result){
        ElMessage.success("审核成功")
        EventBus.emit("refresh", 1)
      }
    })
  }
  else{
    ElMessageBox.confirm(
        `你确定要撤销${aid}的审核吗？`,
        '注意',
        {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel',
          type: 'warning',
        }
    )
    .then(async () => {
      const result = await axiosPut({
        url: '/apply/audit/revoke',
        data: {
          aid: aid
        },
        name: 'audit-revoke'
      })
      if(result){
        ElMessage.success("审核已撤销")
        EventBus.emit("refresh", 1)
      }
    })
  }
}

/**
 * 表头属性列表
 * */
const tableColList = [
  {property: "audited_type", label: "审批状态", sortable: false, width: 100, isFixed: true, operable: true, operationEvent: audit,
    isMapping: true, mappingList:[
      {label: '通过', value: true},
      {label: '未通过', value: false},
    ]},
  {property: "lab", label: "实验室", sortable: false, width: 120},
  {property: "applicant", label: "申请人", sortable: false, width: 100},
  {property: "phone", label: "联系电话", sortable: false, width: 120},
  {property: "apply_time", label: "申请时间", isDateFormat: true, sortable: false, width: 240},
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
    lid: 'String',
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
    {label: '实验室', prop: 'lab', dataName: 'lab', isInput: true},
    {label: '申请人', prop: 'applicant', dataName: 'applicant', isInput: true},
  ],
}

/**
 * 添加部门时所用到的对象
 * */
const addForm = {
  data :{
    lab: '',
    applicant: '',
  },
  dataType:{
    lab: 'String',
    applicant: 'String',
  },
  dataNum: 2,
  rules: {
    lab: [
      { required: 'true', message: '请选择实验室', trigger: 'blur' },
    ],
    applicant: [
      { required: 'true', message: '请选择申请人', trigger: 'blur' },
    ]
  },
  item:[
    {label: '实验室', prop: 'lab', dataName: 'lab', isInput: true},
    {label: '申请人', prop: 'applicant', dataName: 'applicant', isInput: true},
  ],
}

/**
 * 网络请求url
 * */
const urls = {
  getData: "/apply/list",
  deleteData: "/apply/delete",
  addData: "/apply/add",
  updateData: "/apply/update",
}

</script>

<style scoped>

</style>
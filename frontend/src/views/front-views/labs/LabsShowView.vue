<template>
  <el-container style="padding: 0">
    <el-header class="header">
      <el-image
          class="title-img"
          fit="cover"
          :src="`${axios.defaults.baseURL}${res?.img1}`">
      </el-image>
      <div class="courseImgCover"></div>
      <div class="title">
        <el-text class="title-name">
          {{res?.name}}
        </el-text>
        <el-text class="title-category">
          创建时间：{{res?.created_at}}
        </el-text>
      </div>
    </el-header>
    <el-main class="main">
      <el-tabs v-model="activeName" class="main-tabs">
        <el-tab-pane label="实验室信息" name="one">
          <el-text class="pane-info">
            工位数：{{res?.workstation_amount}}
          </el-text>
          <el-text class="pane-info">
            面积：{{res?.area}}m²
          </el-text>
          <el-text class="pane-info">
            设施总值：{{res?.facility_value}}万
          </el-text>
          <el-text class="pane-info">
            地址：{{res?.address}}
          </el-text>
          <el-text class="pane-info">
            管理员：{{administrator?.name}}
          </el-text>
          <el-text class="pane-info">
            联系电话：{{administrator?.phone? administrator.phone : '无'}}
          </el-text>
        </el-tab-pane>
        <el-tab-pane label="实验室申请" name="two">
          <el-form :model="dateList" :rules="rules" ref="myDateForm" label-position="left">
            <el-form-item
              style="width: 40%"
              label="申请时间"
              prop="date"
            >
              <el-date-picker
                  v-model="dateList"
                  type="datetimerange"
                  range-separator="到"
                  value-format="YYYY-MM-DD HH:mm"
                  format="YYYY-MM-DD HH:mm"
                  start-placeholder="开始时间"
                  end-placeholder="结束时间"
              />
            </el-form-item>
          </el-form>
          <el-button
            type="primary"
            @click="apply(myDateForm)"
            style="margin-top: 20px"
          >
            提交申请
          </el-button>
        </el-tab-pane>
      </el-tabs>
    </el-main>
  </el-container>
</template>

<script setup>
import {markRaw, onMounted, reactive, ref} from "vue";
import {onBeforeRouteUpdate, useRoute} from "vue-router";
import {Collection, Delete, Star, View} from "@element-plus/icons-vue";
import MyPlayer from "@/components/myPlayer.vue";
import axios from "axios";
import {axiosGet, axiosPost} from "@/utils/axiosUtil";
import {CURRENT_USER, refreshUser} from "@/utils/appManager";
import {ElMessage, ElMessageBox} from "element-plus";
import {isPasswordValid, pIntValidatorNRequire} from "@/utils/validator";

const activeName = ref('one')
const route = useRoute();
const res = ref('')
const administrator = ref(null)

const dateList = ref([])

const myDateForm = ref(null)

const notEmpty = (rule, value, callback) => {
  if(dateList.value.length > 0){
    callback()
  }
  else{
    callback(new Error("请选择申请时间"))
  }
}


const rules = {
  date:[
    { validator: notEmpty, trigger: 'blur' }
  ]
}

const state = reactive({
  getDataFail: false
})

const update = async (labsId) => {
  const result = localStorage.getItem(`${labsId}`) || ''
  if(result){
    res.value = JSON.parse(result)
    const staffList = await axiosGet({
      url: '/staff/list',
      name: 'labs-getStaff'
    })
    if(staffList){
      for(const item of staffList.rows){
        if(item.sid === res.value.administrator){
          administrator.value = item
        }
      }
    }
  }
  else{
    state.getDataFail = true
  }
}

onBeforeRouteUpdate((to, from, next) => {
  // 当路由参数变化且当前组件被复用时执行
  if (to.query.labsId !== from.query.labsId) {
    update(to.query.labsId)
  }
  next();
});

onMounted( async () =>{
  await update(route.query.labsId)
})

const apply = async (form) => {
  if (!form) return
  await form.validate((valid) => {
    if (valid) {
      ElMessageBox.confirm(
          '你确定要申请使用' + res.value.name + '吗？',
          '注意',
          {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
          }
      )
      .then(async () => {
        await confirmApply()
      })
      .catch(() => {})
    }
  })
}

const confirmApply = async () => {
  refreshUser()
  const result = await axiosPost({
    url: '/application/add',
    data: {
      lab: res.value.lid,
      applicant: CURRENT_USER.value.uid,
      start_time: dateList.value[0],
      end_time: dateList.value[1],
    },
    name: 'applyLabs'
  })
  if(result){
    ElMessage.success("申请发送成功！请等待管理员审核")
  }
}
</script>

<style scoped>
.header{
  display: flex;
  position: absolute;
  width: 99%;
  height: 240px;
  padding: 0;
}
.main{
  display: flex;
  position: absolute;
  margin-top: 240px;
  width: 99%;
  padding: 50px;
}
.title-img{
  width: 99%;
  height: 100%;
  filter: blur(8px); /* 应用模糊效果 */
}
.courseImgCover {
  display: flex;
  z-index: 1;
  background: -webkit-linear-gradient(left, rgba(2, 2, 2, .9), rgba(2, 2, 2, .4), rgba(2, 2, 2, .3));
  background: -moz-linear-gradient(left, rgba(2, 2, 2, .9), rgba(2, 2, 2, .4), rgba(2, 2, 2, .3));
  background: -o-linear-gradient(left, rgba(2, 2, 2, .9), rgba(2, 2, 2, .4), rgba(2, 2, 2, .3));
  left: 0;
  position: absolute;
  top: 0;
  width: 100%;
  height: 100%;
}
.title {
  display: flex;
  flex-direction: column;
  position: absolute; /* 绝对定位以覆盖在遮罩层上 */
  top: 10%; /* 根据需要调整位置 */
  left: 10%; /* 居中开始 */
  z-index: 2; /* 确保在遮罩层之上 */
  text-align: left;
}
.title-category{
  display: inline-block;
  padding-top: 5px;
  color: white;
  width: 100%;
}
.title-name-div{
  display: flex;
  align-items: center;
}
.title-name{
  display: inline-block;
  width: 100%;
  color: white;
  font-size: 28px;
}
.main-tabs{
  width: 100%;
  --el-font-size-base: 16px;
}
.pane-info{
  display: block;
  margin-top: 5px;
}
.main-tabs :deep(.el-tabs__item){
  padding: 0 50px;
}
.chapters-item :deep(.el-collapse-item__header){
  padding: 10px;
}
.chapters-item :deep(.el-collapse-item__content){
  padding-left: 10px;
}
.res-aside-collapse :deep(.el-collapse-item__header){
  padding: 10px;
}
</style>
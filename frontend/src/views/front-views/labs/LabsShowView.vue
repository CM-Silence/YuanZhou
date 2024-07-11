<template>
  <el-container
      class="res-body"
  >
    <el-main
        class="res-main"
    >
      <el-empty
          v-if="state.getDataFail"
          description="数据获取失败，请重试！"
      />
      <div
          v-if="!state.getDataFail"
      >
        <div>
          <el-text
              class="res-title"
              truncated
          >
            {{`${res.name}`}}
          </el-text>
          <el-text
              class="res-data"
          >
            创建时间：{{res.created_at}}
          </el-text>
          <el-text
              class="res-data"
          >
            工位数：{{res.workstation_amount}}
          </el-text>
          <el-text
              class="res-data"
          >
            占地面积：{{res.area}}m²
          </el-text>
          <el-text
              class="res-data"
          >
            关联设备数：{{res.devices_amount}}
          </el-text>
          <el-text
              class="res-data"
          >
            设备总值：{{res.facility_value}}万
          </el-text>
          <el-text
              class="res-data"
          >
            地址：{{res.address}}
          </el-text>
        </div>

        <el-card class="first-item-img">
          <el-image
              fit="fill"
              class="first-item-img"
              :src="`${axios.defaults.baseURL}${res.img1}`"
          >
            <template #error>
              <div
                  class="error-image-slot"
              >
                <el-icon><Picture /></el-icon>
              </div>
            </template>
          </el-image>
        </el-card>

        <div>
          <el-button
            type="primary"
            @click="apply"
          >
            申请使用
          </el-button>
        </div>

      </div>
    </el-main>
  </el-container>
</template>

<script setup>
import {markRaw, onMounted, reactive, ref} from "vue";
import {onBeforeRouteUpdate, useRoute} from "vue-router";
import {Collection, Delete, Star, View} from "@element-plus/icons-vue";
import MyPlayer from "@/components/myPlayer.vue";
import axios from "axios";
import {axiosPost} from "@/utils/axiosUtil";
import {CURRENT_USER, refreshUser} from "@/utils/appManager";
import {ElMessage, ElMessageBox} from "element-plus";

const activeNames = ref(['1'])
const route = useRoute();
const res = ref('')

const state = reactive({
  getDataFail: false
})

const update = (labsId) => {
  const result = localStorage.getItem(`${labsId}`) || ''
  if(result){
    res.value = JSON.parse(result)
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

onMounted(() =>{
  update(route.query.labsId)
})

const apply = async () => {
  ElMessageBox.confirm(
      '你确定要申请' + res.value.name + '吗？',
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

const confirmApply = async () => {
  refreshUser()
  const result = await axiosPost({
    url: '/application/add',
    data: {
      lab: res.value.lid,
      applicant: CURRENT_USER.value.uid
    },
    name: 'applyLabs'
  })
  if(result){
    ElMessage.success("申请发送成功！请等待管理员审核")
  }
}
</script>

<style scoped>
.res-body{
  display: flex;
}

.res-main{
  padding: 5px;
}

.res-main div{
  margin-bottom: 10px;
}

.res-title{
  display: block;
  color: black;
  font-size: 18px;
  font-weight: bold;
  line-height: 30px;
}

.res-data{
  font-size: 15px;
  margin-right: 10px;
}

.first-item-img{
  align-content: center;
  width: 640px;
  height: 360px;
  --el-card-padding: 0;
  margin-right: 20px;
}
.error-image-slot{
  display: flex;
  justify-content: center;
  align-items: center;
  width: 200px;
  height: 100px;
  background: var(--el-fill-color-light);
  color: var(--el-text-color-secondary);
  font-size: 20px;
}
</style>
<template>
  <el-container
      v-loading="state.isLoading"
  >
    <el-header>
      <data-show-view-header
          @refresh="refresh"
          @search="search"
      />
    </el-header>

    <el-main
        class="res-card-list"
    >
      <div v-for="item in state.currentDataArray" class="item-body" @click="itemClick(item)">
        <div class="item-img">
          <el-image
              :src="`${axios.defaults.baseURL}/${item.img1}`"
              fit="cover"
          >
            <template #error>
              <div
                  class="error-image-slot"
              >
                <el-icon><Picture /></el-icon>
              </div>
            </template>
          </el-image>
        </div>
        <div class="item-content">
          <el-text class="title">
            {{item.title}}
          </el-text>
          <br>
          <br>
          <el-text class="content">
            {{item.update_at}}
          </el-text>
          <br>
          <el-text class="content">
            {{`来源：${item.src}`}}
          </el-text>
        </div>

      </div>
    </el-main>

    <el-footer
        style="margin-top: 15px"
    >
      <el-pagination
          v-model:current-page="state.currentPage"
          :page-count="state.pageCount"
          background
          layout="prev, pager, next, jumper"
          @current-change="update(state.currentPage)"
      />
    </el-footer>

  </el-container>
</template>

<script setup>
import DataShowViewHeader from "@/components/DataShowViewHeader.vue";
import ResCard from "@/components/resCard.vue";
import {onMounted, reactive, ref} from "vue";
import {axiosGet} from "@/utils/axiosUtil";
import router from "@/router";
import axios from "axios";

//页面元素数量
const PAGE_SIZE = 12

const state = reactive({
  isLoading: false,
  current_page: 1,
  pageCount: 1,
  currentDataArray: [],
  searchCondition: {
    key_word: '',
    start_time: '',
    end_time: ''
  },
})
const infoList = ref([
  {
    img1: "http://whcm.hwadee.cn/group1/M00/00/00/wKgIB2QLRh6AaADeAAlq3wfJc18298.jpg",
    title: "国家广播电视总局批复在我校设立“虚拟现实视听技术创新与应用国家广播电视总局实验室”",
    audited_time: "2023-02-12 16:03:15",
    src: "四川传媒学院",
    editor: "四川传媒学院",
    view_amount: 1284,
    content: "12月25日，国家广电总局官网公布：国家广播电视总局同意在四川传媒学院设立“虚拟现实视听技术创新与应用国家广播电视总局实验室”。\n" +
        "\n" +
        "同一时间被批复设立的还有华为技术有限公司“超高清技术创新与应用国家广播电视总局重点实验室”，武汉大学“移动广播与信息服务国家广播电视总局实验室”。",
    files: [
      {
        "name": "附件一",
        "url": ""
      }
    ],
    type: 0,
    created_at: "2023-02-12 16:03:15",
    updated_at: "2024-02-16 10:06:17",
    mid: "m0000001"
  },
  {
    img1: "http://whcm.hwadee.cn/group1/M00/00/00/wKgIB2Wg-VmAcSIkAAVKBTrC4hY42.jpeg",
    title: "四川传媒学院召开党委会（扩大）会议暨2023年度二级党组织书记抓基层党建工作述职考核评议会",
    audited_time: "2023-02-12 16:03:15",
    src: "xxx编辑社",
    editor: "张三",
    view_amount: 1284,
    content: "消息内容1",
    files: [
      {
        "name": "附件一",
        "url": ""
      },
      {
        "name": "附件二",
        "url": ""
      }
    ],
    type: 0,
    created_at: "2023-02-12 16:03:15",
    updated_at: "2024-02-16 10:06:17",
    mid: "m0000002"
  },
  {
    img1: "http://whcm.hwadee.cn/group1/M00/00/00/wKgIB2Wg-SGAAsEpAAM5ySjlLgQ762.jpg",
    title: "消息标题1",
    audited_time: "2023-02-12 16:03:15",
    src: "xxx编辑社",
    editor: "张三",
    view_amount: 1284,
    content: "消息内容1",
    files: [],
    type: 0,
    created_at: "2023-02-12 16:03:15",
    updated_at: "2024-02-16 10:06:17",
    mid: "m0000003"
  },
  {
    img1: "http://whcm.hwadee.cn/group1/M00/00/00/wKgIB2Wg-MmAJkg2AAEtah3QYGQ720.jpg",
    title: "消息标题1",
    audited_time: "2023-02-12 16:03:15",
    src: "xxx编辑社",
    editor: "张三",
    view_amount: 1284,
    content: "消息内容1",
    files: [
      {
        "name": "附件一",
        "url": ""
      }
    ],
    type: 0,
    created_at: "2023-02-12 16:03:15",
    updated_at: "2024-02-16 10:06:17",
    mid: "m0000004"
  },
])

const prop = defineProps({
  urls: {
    type: Object,
    default: () => {},
    description: '请求url'
  },
  extraParams: {
    type: Object,
    default: () => {},
    description: '请求时附带的参数'
  },
  keyData: {
    type: String,
    default: () => '',
    description: '数据主键'
  },
})

//对外事件列表
const emit = defineEmits(["clickItem"]);

const refresh = () => {
  console.log("refresh")
}

const search = async (searchWord, timeRange) => {
  state.isLoading = true
  state.searchCondition.key_word = searchWord
  let defaultParams = {
    page: 1,
    page_size: PAGE_SIZE,
  }
  if(timeRange.length > 0){
    state.searchCondition.start_time = timeRange[0]
    state.searchCondition.end_time = timeRange[1]
  }
  console.log("search", {...defaultParams,...state.searchCondition, ...prop.extraParams})
  state.currentDataArray = await getData(prop.urls['getData'], {...defaultParams,...state.searchCondition, ...prop.extraParams}, 'search-getData')
  state.isLoading = false
}

//页数更新时更新数据
async function update(currentPage) {
  //后端分页
  state.isLoading = true
  const defaultParams = {
    ...{
      page: currentPage,
      page_size: PAGE_SIZE,
    },
    ...state.searchCondition
  }
  state.currentDataArray = await getData(prop.urls['getData'], {...defaultParams, ...prop.extraParams}, `getData-page${currentPage}`)
  //判断数据更新后当前页数是否大于总页数
  if (currentPage > state.pageCount) {
    currentPage = state.pageCount
  }
  state.currentPage = currentPage
  state.isLoading = false
}

/**
 * getData()
 * 获取数据的请求
 * */

const getData = async (url, params = {}, name = 'getData') => {
  const result = await axiosGet({url: url, params: params, name: name})
  if (result && result.rows) {
    state.pageCount = Math.max(result['total_pages'], 1)
    for(const i in result.rows){
      if ('created_at' in result.rows[i]){
        result.rows[i].created_at = result.rows[i].created_at.replace('T', ' ')
      }
      if ('update_at' in result.rows[i]){
        result.rows[i].update_at = result.rows[i].update_at.replace('T', ' ')
      }
      if ('files1' in result.rows[i]){
        result.rows[i].files1 = JSON.parse(result.rows[i].files1)
      }
    }
    return result.rows
  }
  else{
    return undefined
  }
}

const itemClick = (item) => {
  console.log("itemClick", item)
  localStorage.setItem(item[prop.keyData], JSON.stringify(item))
  emit("clickItem", item.title, `/home/news/newsShowView?newsId=${item[prop.keyData]}`)
}

onMounted(async () => {
  state.current_page = 1
  await update(1)
})

</script>

<style scoped>
.res-card-list{
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  width: 100%;
  min-height: 60vh;
  padding: 5px;
}
.item-body{
  display: flex;
  align-items: center;
  cursor: pointer;
  margin: 15px 0 15px 0;
}
.item-img{
  flex: 0 0 auto;
  width: 200px;
  height: 100px;
  margin-right: 20px;
}
.item-content{}
.title{
  width: calc(100% - 260px);
  color: black;
  transition: color 0.3s ease;
}
.item-body:hover .title{
  color: #409eff;
}

.error-image-slot{
  display: flex;
  justify-content: center;
  align-items: center;
  width: 200px;
  height: 100px;
  background: var(--el-fill-color-light);
  color: var(--el-text-color-secondary);
  font-size: 30px;
}
</style>
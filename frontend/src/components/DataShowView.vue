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
    <res-card
      v-for="item in resList"
      :res="item"
      @card-click="cardClick"
    />

  </el-main>

</el-container>
</template>

<script setup>
import DataShowViewHeader from "@/components/DataShowViewHeader.vue";
import ResCard from "@/components/resCard.vue";
import {onMounted, reactive, ref} from "vue";
import {axiosGet} from "@/utils/axiosUtil";

//页面元素数量
const PAGE_SIZE = 12

const state = reactive({
  isLoading: false,
  current_page: 1,
  searchCondition: {
    keyWord: '',
    start_time: '',
    end_time: ''
  },
})
const resList = ref([
  {
    img: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
    title: "动物世界，群魔乱舞",
    major: "软件工程",
    administrator: "张三",
    view_amount: 1284,
    like_amount: 327,
    collect_amount: 243,
    content: "资源简介1",
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
    rid: "r0000001"
  },
  {
    img: "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg",
    title: "【童年回忆】《动物世界》CCTV节目 17集全 12月份节目一览",
    major: "软件工程",
    administrator: "李四",
    view_amount: 1567,
    like_amount: 529,
    collect_amount: 382,
    content: "资源简介1",
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
    updated_at: "2024-05-18 18:30:05",
    rid: "r0000002"
  },
  {
    img: "https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg",
    title: "动物世界奇妙旅行记",
    major: "软件工程",
    administrator: "王五",
    view_amount: 485,
    like_amount: 24,
    collect_amount: 2,
    content: "资源简介1",
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
    updated_at: "2024-03-19 12:26:10",
    rid: "r0000003"
  },
  {
    img: "https://fuss10.elemecdn.com/0/6f/e35ff375812e6b0020b6b4e8f9583jpeg.jpeg",
    title: "世界上咬合力最炸裂的10种动物，你见过能把汽车给咬成两半的吗？",
    major: "软件工程",
    administrator: "张三",
    view_amount: 1284,
    like_amount: 327,
    collect_amount: 243,
    content: "资源简介1",
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
    rid: "r0000004"
  },
  {
    img: "https://fuss10.elemecdn.com/9/bb/e27858e973f5d7d3904835f46abbdjpeg.jpeg",
    title: "【中配4K】豆瓣9.6分纪录片《Tiny World》小小世界1-2季 中文字幕【合集】",
    major: "软件工程",
    administrator: "李四",
    view_amount: 1567,
    like_amount: 529,
    collect_amount: 382,
    content: "资源简介1",
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
    updated_at: "2024-05-18 18:30:05",
    rid: "r0000005"
  }
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
})

//对外事件列表
const emit = defineEmits(["addTab", "removeTab"]);

const refresh = () => {
  console.log("refresh")
}

const search = async (searchWord, timeRange) => {
  console.log("search", searchWord, timeRange)
  state.isLoading = true
  state.searchCondition.keyWord = searchWord
  let defaultParams = {
    page: 1,
    page_size: PAGE_SIZE,
  }
  if(timeRange.length > 0){
    state.searchCondition.start_time = timeRange[0]
    state.searchCondition.end_time = timeRange[1]
  }
  state.currentDataArray = await getData(prop.urls['getData'], {...defaultParams,...state.searchCondition, ...prop.extraParams}, 'search-getData')
  state.isLoading = false
}

//页数更新时更新数据
async function update(currentPage) {
  //后端分页
  state.isLoading = true
  const defaultParams = {
    page: currentPage,
    page_size: PAGE_SIZE,
    keyword: state.searchWord
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
  if (result && result.data && result.data.rows) {
    return result.data.rows
  }
  else{
    return undefined
  }
}

const cardClick = (res) => {
  console.log("cardClick", res)
  emit("addTab", res)
}

onMounted(() => {

})

</script>

<style scoped>
.res-card-list{
  display: flex;
  flex-wrap: wrap; /* 允许容器内子元素换行 */
  justify-content: flex-start; /* 水平分布，两端对齐 */
  width: 100%;
  padding: 5px;
}
</style>
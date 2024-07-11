<template>
  <el-container>
    <el-header style="height: auto">
      <div style="margin-top: 10px">
        <el-carousel trigger="click" height="40vh" type="card" :interval="6000" style="width: 100%">
          <el-carousel-item v-for="item in imgList" :key="item">
            <el-image
              style="width: 100%; height: auto"
              fit="contain"
              :src="item.url"
            />
          </el-carousel-item>
        </el-carousel>
      </div>
    </el-header>

    <el-main>
      <div class="home-main">
        <infos-card
          url="/home/news/information"
          :item-list="infoList"
        />
        <notices-card
            url="/home/news/notices"
            :item-list="noticeList"
        />
      </div>

    </el-main>
  </el-container>
</template>

<script setup>
import {onMounted, ref} from "vue";
import InfosCard from "@/components/cards/InfosCard.vue";
import NoticesCard from "@/components/cards/NoticesCard.vue";
import {axiosGet} from "@/utils/axiosUtil";

const imgList = ref([
  {url: 'http://whcm.hwadee.cn/group1/M00/00/00/wKgIB2QLRh6AaADeAAlq3wfJc18298.jpg', target: ''},
  {url: 'http://whcm.hwadee.cn/group1/M00/00/00/wKgIB2QLRiKAHOqtAAfQcoH2GBA147.jpg', target: ''},
  {url: 'https://www.scmc.edu.cn/attachment/core/spider/2023_12/26_09/69fdf73e18c7f0de.jpg.cthumb.jpg', target: ''},
])

const infoList = ref([
    {
      img: "http://whcm.hwadee.cn/group1/M00/00/00/wKgIB2QLRh6AaADeAAlq3wfJc18298.jpg",
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
        },
        {
          "name": "附件二",
          "url": ""
        }
      ],
      type: 0,
      created_at: "2023-02-12 16:03:15",
      updated_at: "2024-02-16 10:06:17",
      mid: "m0000001"
    },
  {
    img: "缩略图url",
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
    mid: "m0000001"
  },
  {
    img: "缩略图url",
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
      },
      {
        "name": "附件二",
        "url": ""
      }
    ],
    type: 0,
    created_at: "2023-02-12 16:03:15",
    updated_at: "2024-02-16 10:06:17",
    mid: "m0000001"
  },
  {
    img: "缩略图url",
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
      },
      {
        "name": "附件二",
        "url": ""
      }
    ],
    type: 0,
    created_at: "2023-02-12 16:03:15",
    updated_at: "2024-02-16 10:06:17",
    mid: "m0000001"
  },
])

const noticeList = ref([])

const resList = ref([

])

const labList = ref([

])

onMounted( async () => {
  const infoObject = await getData('/news/list',{
    type: 1,
    page: 1,
    page_size: 3
  }, 'getInfo')
  const noticeObject = await getData('/news/list',{
    type: 2,
    page: 1,
    page_size: 4
  }, 'getNotice')
  const resObject = await getData('/res/list',{
    page: 1,
    page_size: 3
  }, 'getRes')
  const labObject = await getData('/labs/list',{
    page: 1,
    page_size: 3
  }, 'getLab')

  infoList.value = infoObject
  noticeList.value = noticeObject
})

const getData = async (url, params = {}, name = 'getData') => {
  const result = await axiosGet({url: url, params: params, name: name})
  if (result && result.rows) {
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
</script>

<style scoped>
.home-main{
  display: flex;
  justify-content: space-between;
  width: 100%;
}
</style>
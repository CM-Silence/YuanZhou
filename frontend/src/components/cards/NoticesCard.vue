<template>
  <div class="card-body">
    <div class="card-title">
      <span>
        <el-text
            class="card-title-text"
            type="primary"
        >
          通知公告
        </el-text>
        <el-divider direction="vertical"/>
        <el-text
            class="card-title-text-en"
        >
          Notice
        </el-text>
      </span>

      <el-text
          class="card-more"
          @click="showMore"
      >
        查看更多 →
      </el-text>
    </div>
    <div
        v-if="itemList.length > 0"
        v-for="item in itemList"
        @click="clickCard(item)"
    >
      <div class="item-body">
        <div class="item-date">
          <div class="date-day">
            {{getDate(item.update_at)}}
          </div>
          <div class="date-year-mouth">
            {{getYearMonth(item.update_at)}}
          </div>
        </div>
        <el-text class="title" truncated>
          {{item.title}}
        </el-text>
      </div>
    </div>
    <div v-else>
      <el-empty/>
    </div>
  </div>
</template>

<script setup>
import router from "@/router";
import {setCurrentPage} from "@/utils/appManager";

const prop = defineProps({
  url: {
    type: String,
    default: () => '',
    description: '显示更多跳转路由'
  },
  itemList: {
    type: Array,
    default: () => [],
    description: '数据'
  }
})

const getYearMonth = (date) => {
  if(!date){
    return ''
  }
  const dateParts = date.split(/-| /)
  return `${dateParts[0]}-${dateParts[1]}`
}

const getDate = (date) => {
  if(!date){
    return ''
  }
  const dateParts = date.split(/-| /)
  return dateParts[2]
}
const showMore = async () => {
  await router.push(prop.url)
  setCurrentPage('/home/news/information')
}

const clickCard = async (item) => {
  localStorage.setItem(item.mid, JSON.stringify(item))
  await router.push(`/home/news/newsShowView?newsId=${item.mid}`)
  setCurrentPage('/home/news/information')
}
</script>

<style scoped>
.card-body{
  display: inline;
  width: 40%;
  height: auto;
  margin: 0 50px 0 0;
}
.card-title{
  display: flex;
  justify-content: space-between;
}
.card-title-text{
  font-size: 20px;
}
.card-title-text-en{
  font-size: 20px;
  color: #cccccc;
  font-weight: bold;
}
.card-more{
  font-size: 15px;
  color: #cccccc;
  transition: color 0.3s ease;
  cursor: pointer;
}
.card-more:hover{
  color: #409eff;
}
.title{
  width: calc(100% - 100px);
  color: black;
  transition: color 0.3s ease;
}
.item-body{
  display: flex;
  justify-content: space-between;
  align-items: center;
  cursor: pointer;
  margin: 10px 0 10px 0;
}
.item-body:hover .title{
  color: #409eff;
}
.item-date{
  display: flex;
  background: #dddddd;
  justify-content: center;
  flex-direction: column;
  width: 70px;
  height: 50px;
  margin-right: 10px;
}
.date-day{
  font-size: 18px;
  text-align: center;
}
.date-year-mouth{
  font-size: 12px;
  text-align: center;
}
</style>
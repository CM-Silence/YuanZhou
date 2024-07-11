<template>
  <div class="card-body">
    <div class="card-title">
      <span>
        <el-text
            class="card-title-text"
            type="primary"
        >
          新闻资讯
        </el-text>
        <el-divider direction="vertical"/>
        <el-text
            class="card-title-text-en"
        >
          News
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
        v-for="(item, index) in itemList"
        @click="clickCard(item)"
    >
      <div v-if="index === 0" class="first-item-body">
        <el-card class="first-item-img">
          <el-image
              fit="fill"
              class="first-item-img"
              :src="`${axios.defaults.baseURL}${item.img1}`"
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
        <div class="first-item-content">
          <el-text class="title">
            {{item.title}}
          </el-text>
          <br>
          <el-text class="content">
            {{`来源：${item.src}`}}
          </el-text>
          <br>
          <el-text class="content">
            {{item.audited_time}}
          </el-text>
        </div>
      </div>

      <div v-else class="item-body">
        <el-text class="title" truncated>
          {{item.title}}
        </el-text>
        <el-text class="content">
          {{item.audited_time}}
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
import axios from "axios";

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
  margin: 0 0 0 50px;
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
.first-item-body{
  display: flex;
  align-items: center;
  cursor: pointer;
  margin: 10px 0 10px 0;
}
.first-item-img{
  align-content: center;
  width: 200px;
  height: 100px;
  --el-card-padding: 0;
  margin-right: 20px;
}
.first-item-body:hover .title{
  color: #409eff;
}
.title{
  width: calc(100% - 260px);
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
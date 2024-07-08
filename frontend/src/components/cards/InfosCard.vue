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
        v-for="(item, index) in itemList"
        @click="clickCard(item)"
    >
      <div v-if="index === 0" class="first-item-body">
        <div class="first-item-img">
          <el-image
              :src="item.img"
          />
        </div>
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
  </div>
</template>

<script setup>
import router from "@/router";

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
const showMore = () =>{
  router.push(prop.url)
}

const clickCard = (item) =>{
  localStorage.setItem(item.mid, JSON.stringify(item))
  router.push(`/home/news/newsShowView?newsId=${item.mid}`)
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
  flex: 0 0 auto;
  width: 200px;
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
</style>
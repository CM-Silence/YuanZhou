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
        <div class="res-title-body">
          <el-text
              class="res-title"
              truncated
          >
            {{res.title}}
          </el-text>
          <el-text
              class="res-data"
          >
            {{`发布时间：${res.updated_at}`}}
          </el-text>
          <el-text
              class="res-data"
          >
            {{`来源：${res.src}`}}
          </el-text>
          <el-text
              class="res-data"
          >
            {{`编辑：${res.editor}`}}
          </el-text>
          <el-text
              class="res-data"
          >
            {{`浏览量：${res.view_amount}`}}
          </el-text>
        </div>

        <div>
          <el-text
              style="white-space: pre-wrap; color: black"
          >
            {{res.content}}

          </el-text>
        </div>
      </div>
    </el-main>
    <el-aside>
      <el-collapse v-model="activeNames" class="res-aside-collapse">
        <el-collapse-item
            title="附件列表"
            name="1"
        >
          <div
              v-for="item in res.files"
          >
            <el-text
                class="res-aside-item-text"
                truncated
                @click="fileClick(item)"
            >
              {{item.name}}
            </el-text>

            <el-text
                v-if="res.type === 2 || res.type === 3"
                class="res-aside-item-time"
                truncated
            >
              15:12
            </el-text>
          </div>
        </el-collapse-item>
      </el-collapse>
    </el-aside>
  </el-container>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {onBeforeRouteUpdate, useRoute} from "vue-router";

const activeNames = ref(['1'])
const route = useRoute();
const res = ref('')

const state = reactive({
  getDataFail: false
})

const update = (newsId) => {
  const result = localStorage.getItem(`${newsId}`) || ''
  if(result){
    res.value = JSON.parse(result)
  }
  else{
    state.getDataFail = true
  }
}

const fileClick = (file) => {
  window.open(file.url, '_blank');
}

onBeforeRouteUpdate((to, from, next) => {
  // 当路由参数变化且当前组件被复用时执行
  if (to.query.newsId !== from.query.newsId) {
    update(to.query.newsId)
  }
  next();
});

onMounted(() =>{
  update(route.query.newsId)
})
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

.res-title-body{
  text-align: center;
}

.res-title{
  display: block;
  color: black;
  font-size: 18px;
  font-weight: bold;
  line-height: 30px;
}

.res-data{
  font-size: 12px;
  padding-left: 5px;
  margin-right: 10px;
}

.res-aside-collapse{
  overflow-y: hidden;
}

.res-aside-item-text{
  width: 70%;
  padding: 0 0 3px 5px;
  transition: color 0.3s ease;
  cursor: pointer;
}

.res-aside-item-time{
  padding: 0 0 3px 25px;
  cursor: default;
}

.res-aside-item-text:hover{
  color: #409eff;
}
</style>
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
          {{`${res.title}${activeFile? ` [${activeFile.name}]` : ''}`}}
        </el-text>
        <el-text
            class="res-data"
        >
          <el-icon>
            <View/>
          </el-icon>
          {{res.view_amount}}
        </el-text>
        <el-text
            class="res-data"
        >
          <el-icon>
            <Star/>
          </el-icon>
          {{res.like_amount}}
        </el-text>
        <el-text
            class="res-data"
        >
          <el-icon>
            <Collection/>
          </el-icon>
          {{res.collect_amount}}
        </el-text>
        <el-text
            class="res-data"
        >
          {{res.update_at}}
        </el-text>
      </div>
      <div v-if="res.type === 1">
        <el-text
          style="color: black"
        >
          资源地址：
        </el-text>
        <a :href="`${res.content}`" target="_blank">
          {{`${res.content}`}}
        </a>

      </div>

      <div v-if="res.type === 2">
        <my-player
          :url="`${axios.defaults.baseURL}${activeFile?.url}`"
          res-type="video"
        />
      </div>


      <div v-if="res.type === 3">
        <my-player
            :url="`${axios.defaults.baseURL}${activeFile?.url}`"
            res-type="audio"
        />
      </div>

      <div v-if="res.type === 4">
        <el-text>
          点击附件以查看文档
        </el-text>
      </div>

      <div>
        <el-text
          :line-clamp="showLimit"
          style="white-space: pre-wrap;"
        >
          {{res.content}}

        </el-text>
        <el-button
            type="primary"
            @click="showMoreContent"
            text
        >
          {{showMoreBtnText}}
        </el-button>
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
          v-for="item in res.files1"
        >
          <el-text
              class="res-aside-item-text"
              truncated
              @click="fileClick(item)"
          >
            {{item.name}}
          </el-text>

<!--          <el-text-->
<!--              v-if="res.type === 2 || res.type === 3"-->
<!--              class="res-aside-item-time"-->
<!--              truncated-->
<!--          >-->
<!--            15:12-->
<!--          </el-text>-->
        </div>
      </el-collapse-item>
    </el-collapse>
  </el-aside>
</el-container>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {onBeforeRouteUpdate, useRoute} from "vue-router";
import {Collection, Star, View} from "@element-plus/icons-vue";
import MyPlayer from "@/components/myPlayer.vue";
import axios from "axios";

const activeNames = ref(['1'])
const route = useRoute();
const res = ref('')
const activeFile = ref(null)
const showLimit = ref(1)
const showMoreBtnText = ref("显示更多")

const state = reactive({
  getDataFail: false
})

const update = (resId) => {
  const result = localStorage.getItem(`${resId}`) || ''
  if(result){
    res.value = JSON.parse(result)
    if(res.value.type !== 1){
      activeFile.value = res.value.files1.length > 0 ? res.value.files1[0] : null
    }
  }
  else{
    state.getDataFail = true
  }
}

const fileClick = (file) => {
  activeFile.value = file
  if(res.value.type === 3 || res.value.type === 4){
    window.open(`${axios.defaults.baseURL}${file.url}`, "_blank")
  }
}

const showMoreContent = () =>{
  if(showLimit.value === 1){
    showLimit.value = 65535
    showMoreBtnText.value = "隐藏"
  }
  else{
    showLimit.value = 1
    showMoreBtnText.value = "显示更多"
  }
}

onBeforeRouteUpdate((to, from, next) => {
  // 当路由参数变化且当前组件被复用时执行
  if (to.query.resId !== from.query.resId) {
    update(to.query.resId)
  }
  next();
});

onMounted(() =>{
  update(route.query.resId)
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
<template>
  <div
    class="res-body"
    @click="emit('cardClick', res)"
  >
    <el-card
        class="res-card-body"
    >
      <el-image
          class="res-card-body"
          fit="fill"
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
      <div class="res-card-img-cover">
        <el-text
          size="small"
        >
          <el-icon>
            <View/>
          </el-icon>
          {{res.view_amount}}
        </el-text>

        <el-text
            size="small"
        >
          <el-icon>
            <Star/>
          </el-icon>
          {{res.like_amount}}
        </el-text>

        <el-text
            size="small"
        >
          <el-icon>
            <Collection/>
          </el-icon>
          {{res.collect_amount}}
        </el-text>
      </div>
    </el-card>
    <el-text
        line-clamp="2"
        class="res-title"
    >
      {{res[titleData]}}
    </el-text>

    <el-text
        truncated
        size="small"
        class="res-date"
    >
      {{`${res.administrator} · ${res.update_at}`}}
    </el-text>
  </div>
</template>

<script setup>
import axios from "axios";
import {Collection, Star, View} from "@element-plus/icons-vue";

//对外事件列表
const emit = defineEmits(["itemClick"]);

const prop = defineProps({
  res: {
    type: Object,
    default: () => null,
    description: '资源内容'
  },
  titleData: {
    type: String,
    default: () => 'title',
    description: '标题参数名'
  },
})
</script>

<style scoped>
.res-body{
  display: flex;
  flex-direction: column;
  justify-content: flex-start; /* 子元素在父容器中垂直分布 */
  width: 240px;
  height: 220px;
  margin: 0 12px 5px 12px;
  cursor: pointer;
}
.res-card-body{
  align-content: center;
  width: 240px;
  height: 150px;
  --el-card-padding: 0;
}
.res-card-img-cover{
  position: relative;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -120%);
  background-image: linear-gradient(rgba(0, 0, 0, 0), rgba(99, 99, 99, 127));
}
.res-card-img-cover .el-text{
  padding: 5px;
  color: white;
}
.res-title{
  padding: 5px 0 0 5px;
  color: black;
  align-self: flex-start;
  transition: color 0.2s ease;
}
.res-title:hover{
  color: cornflowerblue;
}
.res-date{
  padding: 5px 0 0 5px;
  align-self: flex-start;
  color: #999999;
}
.error-image-slot{
  display: flex;
  justify-content: center;
  align-items: center;
  width: 240px;
  height: 150px;
  background: var(--el-fill-color-light);
  color: var(--el-text-color-secondary);
  font-size: 30px;
}
</style>
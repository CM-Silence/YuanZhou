<template>
<div>
  <audio v-if="resType === 'audio'" ref="myAudio" preload="metadata" :key="resKey" controls>
    <source :src="resUrl">
  </audio>

  <video v-if="resType === 'video'" ref="myVideo" preload="metadata" :key="resKey" controls>
    <source :src="resUrl">
  </video>
</div>
</template>

<script setup>
import {onMounted, ref, watch} from "vue";
import Video from "@/views/front-views/res/Video.vue";

const prop = defineProps({
  url: {
    type: String,
    default: () => '',
    description: 'url'
  },
  resType: {
    type: String,
    default: 'audio',
    description: '资源类型',
    validator: (value) => {
      return ['audio', 'video'].includes(value);
    }
  }
})

const myAudio = ref(null)
const myVideo = ref(null)

const resUrl = ref(prop.url)
const resKey = ref('res')

//监听数据变化并实时更新播放器
watch(() => prop.url, (newValue) => {
  resUrl.value = newValue;
  resKey.value = `res-${newValue}`
});

let durationStr = ''

onMounted(() => {
  let T
  if(prop.resType === 'audio'){
    T = myAudio.value
  }
  else{
    T = myVideo.value
  }
  T.addEventListener('loadedmetadata', () => {
    // 获取音频时长，并转换为分钟和秒
    const duration = T.duration;
    const minutes = Math.floor(duration / 60);
    const seconds = Math.floor(duration % 60);
    durationStr = `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
  });
})


</script>

<style scoped>
video{
  width: 100%;
  height: auto;
}
</style>
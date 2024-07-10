<template>
<div>
  <audio v-if="resType === 'audio'" ref="myAudio" preload="metadata" controls>
    <source :src="url">
  </audio>

  <video v-if="resType === 'video'" ref="myVideo" preload="metadata" controls>
    <source :src="url">
  </video>
</div>
</template>

<script setup>
import {onMounted, ref} from "vue";
import Video from "@/views/front-views/res/Video.vue";

const myAudio = ref(null)
const myVideo = ref(null)

let durationStr = ''

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
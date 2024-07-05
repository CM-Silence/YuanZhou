<template>
<div>
  <el-form
      :model="data"
      class="header"
  >
    <el-form-item
        label="搜索"
        class="form-item"
    >
      <el-input
          v-model.trim="data.searchWord"
          autocomplete="off"
      />
    </el-form-item>

    <el-form-item
        label="日期"
        class="form-item"
    >
      <el-date-picker
          v-model="data.timeRange"
          type="daterange"
          range-separator="到"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          value-format="YYYY-MM-DD"
          unlink-panels
      />
    </el-form-item>

    <el-form-item

    >
      <el-button
        type="success"
        icon="search"
        @click="search"
        plain
      >
        搜索
      </el-button>
    </el-form-item>

    <el-form-item
      style="margin-left: 20px"
    >
      <el-button
          type="primary"
          icon="refresh"
          @click="refresh"
          plain
      >
        刷新
      </el-button>
    </el-form-item>
  </el-form>
</div>
</template>

<script setup>
import {reactive} from "vue";

const data = reactive({
  searchWord: '',
  timeRange: [],
})

//对外事件列表
const emit = defineEmits(["search", "refresh"]);
const search = () =>{
  emit("search", data.searchWord, data.timeRange)
}

const refresh = () =>{
  emit("refresh")
}
</script>

<style scoped>
.header{
  display: flex;
  flex-wrap: nowrap; /* 仅当使用flex布局时 */
  justify-content: flex-start; /* 水平分布，两端对齐 */
}

.form-item {
  text-align: justify;
  width: 300px;
  padding-right: 30px;
}
</style>
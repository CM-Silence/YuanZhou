import { createApp } from 'vue'
import App from './App.vue'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import axios from 'axios'

import router from './router'

axios.defaults.baseURL = "http://127.0.0.1:8217";

const app = createApp(App)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.provide("axios", axios)

app.use(router)
    .use(ElementPlus)

app.mount('#app')
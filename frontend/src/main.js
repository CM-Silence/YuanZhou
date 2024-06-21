import { createApp } from 'vue'
import App from './App.vue'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from 'axios'

import router from './router'
import store from './store'

axios.defaults.baseURL = "http://127.0.0.1:8217";

const app = createApp(App)

app.provide("axios", axios)

app.use(store)
    .use(router)
    .use(ElementPlus)

app.mount('#app')
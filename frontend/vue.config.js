const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  lintOnSave: false,
  transpileDependencies: true,
  devServer: {
    proxy: {
      '/user': {
        target: 'http://localhost:8217', // 这里只是示例，通常你应该指向你的后端API服务器
        changeOrigin: true, // 允许跨域
      }
    }
  }
})

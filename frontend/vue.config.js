const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  lintOnSave: false,
  outputDir:'yz-f',
  devServer:{
    host: '0.0.0.0',
    port:7775,
    client: {
      webSocketURL: 'ws://0.0.0.0:7775/ws',
    },
    headers: {
      'Access-Control-Allow-Origin': '*',
    }

  },
  chainWebpack: (config) => {
    config.plugin('define').tap((definitions) => {
      Object.assign(definitions[0], {
        __VUE_OPTIONS_API__: 'true',
        __VUE_PROD_DEVTOOLS__: 'false',
        __VUE_PROD_HYDRATION_MISMATCH_DETAILS__: 'false'
      })
      return definitions
    })
  },
})

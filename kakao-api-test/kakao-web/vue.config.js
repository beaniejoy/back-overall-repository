const { defineConfig } = require('@vue/cli-service');
const NodePolyfillPlugin = require('node-polyfill-webpack-plugin');
module.exports = defineConfig({
  devServer: {
    hot: true,
    port: process.env.VUE_APP_PORT || 5001,
    proxy: {
      '/api': {
        target: process.env.VUE_APP_API_URL,
        changeOrigin: true, // '/api' 호출시 target으로 변경
      }
    },
  },
  transpileDependencies: [
    'vuetify'
  ],
  configureWebpack: {
    plugins: [
      new NodePolyfillPlugin()
    ]
  }
});

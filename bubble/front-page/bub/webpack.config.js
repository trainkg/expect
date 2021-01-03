// 不能使用node_modules/@vue/cli-service/webpack.config.js,否则会导致代码提示失效
// 需要把vue.config.js中的webpack配置信息单独写一个webpack.config.js
// 在vue.config.js中引用webpack.config.js:
// Preferences | Languages & Frameworks | JavaScript | Webpack 配置文件指向webpack.config.js

const path = require('path');

function resolve(dir) {
  return path.join(__dirname, dir);
}

module.exports = {
  devServer: {
    host: 'localhost',
    disableHostCheck: true,
    contentBase: path.join(__dirname, 'dist'),
    compress: true,
    port: 9000,
    // proxy: {
    //   [process.env.VUE_APP_BASE_API]: {
    //     target: process.env.VUE_APP_TARGET,
    //     changeOrigin: true,
    //     pathRewrite: {
    //       ['^' + process.env.VUE_APP_BASE_API]: ''
    //     }
    //   }
    // }
  },
  resolve: {
    extensions: ['.js', '.vue', '.json'],
    alias: {
      'vue$': 'vue/dist/vue.esm.js',
      '@': resolve('src')
    }
  }
};


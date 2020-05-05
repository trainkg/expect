'use strict'
const path 	= require('path')
const pkg 	= require('./package')

function resolve(dir) {
  return path.join(__dirname, dir)
}

const name = pkg.name || 'vue-project' // page title
const port = 2333 // dev port

// All configuration item explanations can be find in https://cli.vuejs.org/config/
module.exports = {
  /**
   * You will need to set publicPath if you plan to deploy your site under a sub path,
   * for example GitHub Pages. If you plan to deploy your site to https://foo.github.io/bar/,
   * then publicPath should be set to "/bar/".
   * In most cases please use '/' !!!
   * Detail: https://cli.vuejs.org/config/#publicpath
   */

  publicPath: process.env.NODE_ENV === 'production'
    ? '/prodRoot'
    : '/',

  // 如果你不需要生产环境的 source map，可以将其设置为 false 以加速生产环境构建。
  outputDir: 'dist',
  assetsDir: 'dist/static',
  lintOnSave: process.env.NODE_ENV === 'development',
  productionSourceMap: false,
  configureWebpack: require('./webpack.config.js')
}

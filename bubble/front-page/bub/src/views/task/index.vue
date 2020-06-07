<template>
  <a-layout>
    <a-layout-sider width="200" style="background: #fff">
      <pageMenu />
    </a-layout-sider>
    <a-layout style="padding: 0 12px 12px;">
      <router-view>
        <pageContent />
      </router-view>
    </a-layout>
  </a-layout>
</template>
<script>
import pageMenu from './menu'
import pageContent from './task-content'
export default {
  name: 'bub-auth',
  components: {
    pageMenu,
    pageContent
  }
}
</script>

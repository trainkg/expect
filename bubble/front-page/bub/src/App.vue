<template>
  <a-layout id="components-layout-top-side-2">
    <a-layout-header class="header" style="position: relative">
      <div class="logo" />
      <bubNav />
      <div style="position: absolute; top:0px; right: 20px">
        <a-avatar size="large" style="backgroundColor:#87d068" icon="user" />
      </div>
    </a-layout-header>
    <router-view />
    <bubBottom />
  </a-layout>
</template>
<script>
import Navigation from '@/components/Navigation'
import PageBottom from '@/components/page-bottom'
export default {
  name: 'bub-application',
  components: {
    'bubNav': Navigation,
    'bubBottom': PageBottom
  }
}
</script>
<style>
  #components-layout-top-side-2 .logo {
    width: 120px;
    height: 31px;
    background: rgba(255, 255, 255, 0.2);
    margin: 16px 28px 16px 0;
    float: left;
  }
</style>

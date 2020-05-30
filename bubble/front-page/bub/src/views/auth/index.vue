<template>
  <a-layout>
    <a-layout-sider width="200" style="background: #fff">
      <AuthMenu />
    </a-layout-sider>
    <a-layout style="padding: 0 12px 12px;">
        <router-view>
            <AuthContent></AuthContent>
        </router-view>
    </a-layout>
  </a-layout>
</template>
<script>
import AuthMenu from './Menu'
import AuthContent from './auth-content'
export default {
  name: 'bub-auth',
  components: {
    AuthMenu,
    AuthContent
  }
}
</script>

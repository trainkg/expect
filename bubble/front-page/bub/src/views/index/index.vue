<template>
  <a-layout>
    <a-layout style="padding: 0 12px 12px;">
      <router-view>
        <AuthContent />
      </router-view>
    </a-layout>
  </a-layout>
</template>
<script>
import AuthContent from '@/views/auth/auth-content'
export default {
  name: 'bub-auth',
  components: {
    AuthContent
  }
}
</script>

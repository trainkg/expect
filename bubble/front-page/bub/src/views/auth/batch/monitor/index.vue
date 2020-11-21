<template>
    <div>
        <a-page-header
                style="border: 1px solid rgb(235, 237, 240)"
                title="系统Batch管理"
                sub-title="BATCH 监控"
                @back="() => null"
        />
        <a-layout style="padding: 0 12px 12px;">
            <pageContent />
        </a-layout>
    </div>
</template>
<script>
import pageContent from './monitor-content'
export default {
  name: 'bub-auth',
  components: {
    pageContent
  }
}
</script>

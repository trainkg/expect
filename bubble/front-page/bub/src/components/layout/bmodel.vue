<template>
  <div class="b-model">
    <div v-if="title" class="b-model-head">
      {{ title }}
    </div>
    <div class="b-model-content">
      <slot>this is default content, if you see this , because of you use error.</slot>
    </div>
  </div>
</template>
<script>
export default {
  props: ['title']
}
</script>

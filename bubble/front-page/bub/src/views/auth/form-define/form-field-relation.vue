<template>
  <div class="b-form-item">
    <div v-if="label" class="b-item-label">
      {{ label }}
    </div>
    <div class="b-item-content">
      <slot>this is default content, if you see this , because of you use error.</slot>
    </div>
  </div>
</template>
<script>
export default {
  props: ['label']
}
</script>

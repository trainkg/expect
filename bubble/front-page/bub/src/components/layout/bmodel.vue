<template>
  <div class="b-model">
    <div v-if="title" class="b-model-head">
      <slot name="header" v-bind:title="title">
        {{ title }}
      </slot>
    </div>
    <div class="b-model-content">
      <slot name="default">
        this is default content, if you see this , because of you use error.
      </slot>
    </div>
  </div>
</template>
<script>
export default {
  props: { 'title': { type: String, default: 'section name' }}
}
</script>

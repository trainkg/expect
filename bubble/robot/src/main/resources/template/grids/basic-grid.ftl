<!--form 表单配置参考 https://www.antdv.com/components/form-model-cn/ -->
<template>
  <a-form :form="form" />
</template>
<script>
  export default {
    beforeCreate() {
      this.form = this.$form.createForm(this, options);
    },
  };
</script>
<template>
  <b-model title="基本信息">
    <a-row class="b-form-row">
      <a-col :span="4" class="b-form-item">
        <a-form-item label="名称">
          <a-input
            v-decorator="['note']"
          />
        </a-form-item>
      </a-col>
      <a-col :span="4" class="b-form-item">
        <a-form-item label="描述">
          <a-input
            v-decorator="['description']"
          />
        </a-form-item>
      </a-col>
    </a-row>
  </b-model>
</template>

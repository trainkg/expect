<template>
  <a-menu
    mode="inline"
    :style="{ height: '100%', borderRight: 10 }"
  >
  <a-sub-menu>
    <span slot="title"><a-icon type="apartment" />办公任务</span>
    <a-menu-item key="1-1">
      <router-link :to="{name:'task-list'}">
        待办任务
      </router-link>
    </a-menu-item>
  </a-sub-menu>
  </a-menu>
</template>

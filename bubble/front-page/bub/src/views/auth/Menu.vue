<template>
  <a-menu
    mode="inline"
    :style="{ height: '100%', borderRight: 10 }"
  >
    <a-sub-menu key="sub1">
      <span slot="title"><a-icon type="user" />配置中心</span>
      <a-menu-item key="1-1">
        <router-link :to="{name:'auth-module'}">
          菜单管理
        </router-link>
      </a-menu-item>
      <a-menu-item key="1-2">
        <router-link :to="{name:'auth-role'}">
          权限管理
        </router-link>
      </a-menu-item>
      <a-menu-item key="1-3">
        <router-link :to="{name:'form-module'}">
          表单管理
        </router-link>
      </a-menu-item>
    </a-sub-menu>
  </a-menu>
</template>

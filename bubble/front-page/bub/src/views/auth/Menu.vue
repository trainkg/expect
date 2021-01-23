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

    <a-sub-menu key="sub2">
      <span slot="title"><a-icon type="file" />影像系统</span>
      <a-menu-item key="2-1">
        <router-link :to="{name:'file-module'}">
          文件管理
        </router-link>
      </a-menu-item>
    </a-sub-menu>

    <a-sub-menu key="sub3">
      <span slot="title"><a-icon type="eye" />batch管理</span>
      <a-menu-item key="3-1">
        <router-link :to="{name:'batch-manage'}">
          定义管理
        </router-link>
      </a-menu-item>
      <a-menu-item key="3-2">
        <router-link :to="{name:'batch-monitor'}">
          流程监控
        </router-link>
      </a-menu-item>
      <a-menu-item key="3-3">
        <router-link :to="{name:'manual-scheduler'}">
          手动提交
        </router-link>
      </a-menu-item>
      <a-menu-item key="3-4">
        <router-link :to="{name:'quartz-scheduler'}">
          定时任务
        </router-link>
      </a-menu-item>
    </a-sub-menu>
  </a-menu>
</template>

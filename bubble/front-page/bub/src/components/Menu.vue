<template>
  <a-menu
          mode="inline"
          forceSubMenuRender="true"
    :style="{ height: '100%', borderRight: 10 }"
  >
    <a-sub-menu key="sub1">
      <span slot="title"><a-icon type="user" />配置中心</span>
      <a-menu-item key="1-1">
        菜单管理
      </a-menu-item>
      <a-menu-item key="1-2">
        权限管理
      </a-menu-item>
    </a-sub-menu>
    <a-sub-menu key="sub2">
      <span slot="title"><a-icon type="laptop" />人员管理</span>
      <a-menu-item key="2-1">
        人员信息
      </a-menu-item>
    </a-sub-menu>
    <a-sub-menu key="sub3">
      <span slot="title"><a-icon type="laptop" />任务中心</span>
      <a-menu-item key="3-1">
        待办任务
      </a-menu-item>
    </a-sub-menu>
  </a-menu>
</template>

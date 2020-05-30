<template>
  <a-menu
    mode="inline"
    :style="{ height: '100%', borderRight: 10 }"
  >
    <a-sub-menu>
      <span slot="title"><a-icon type="apartment" />部门管理</span>
      <a-menu-item key="1-2">
        <router-link :to="{name:'create-depart'}">
          部门信息
        </router-link>
      </a-menu-item>
    </a-sub-menu>
    <a-sub-menu key="sub1">
      <span slot="title"><a-icon type="user" />人员管理</span>
      <a-menu-item key="2-1">
        <router-link :to="{name:'user-type-list'}">
            人员类别
        </router-link>
      </a-menu-item>
      <a-menu-item key="2-2">
        <router-link :to="{name:'party-maintain'}">
          人员信息
        </router-link>
      </a-menu-item>
    </a-sub-menu>
  </a-menu>
</template>

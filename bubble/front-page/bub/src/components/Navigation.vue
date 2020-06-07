<template>

    <a-menu
      theme="dark"
      mode="horizontal"
      :default-selected-keys="['1']"
      :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="home">
        <router-link :to="{name:'home-index'}">
          首页
        </router-link>
      </a-menu-item>
      <a-menu-item key="auth">
        <router-link :to="{name:'auth-index'}">
          配置中心
        </router-link>
      </a-menu-item>
      <a-menu-item key="party">
        <router-link :to="{name:'party-index'}">
          对象管理
        </router-link>
      </a-menu-item>
      <a-menu-item key="task">
        <router-link :to="{name:'task-index'}">
          任务中心
        </router-link>
      </a-menu-item>
      <a-menu-item key="system">
        <router-link :to="{name:'system-index'}">
          系统管理
        </router-link>
      </a-menu-item>
    </a-menu>

</template>

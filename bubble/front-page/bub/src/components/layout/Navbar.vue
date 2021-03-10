<template>
  <a-menu
    mode="inline"
    :style="{ height: '100%', borderRight: 10 }"
  >
    <template v-for="item in modules">
      <a-menu-item v-if="item.modules.length === 0" :key="item.listId">
        <router-link :to="item.uri">
          {{ item.name }} {{ item.listId }}
        </router-link>
      </a-menu-item>
      <a-sub-menu v-if="item.modules.length > 0">
        <span slot="title"><a-icon type="apartment" />{{ item.name }}</span>
        <a-menu-item v-for="subItem in item.modules" :key="subItem.listId">
          <router-link :to="subItem.uri">
            {{ subItem.name }} {{ subItem.listId }}
          </router-link>
        </a-menu-item>
      </a-sub-menu>
    </template>
  </a-menu>
</template>
<script>
import Vue from 'vue'
import {
  searchData
} from '@/api/base'
export default {
  props: ['parentId', 'loadSub'],
  data() {
    return {
      component: '@/views/party/user-type',
      last: null,
      // 功能页面菜单列表
      modules: []
    }
  },
  created() {
    this.loadingModule()
  },
  methods: {
    loadingModule() {
      // 7 robot
      const search = { parentId: this.parentId, loadSub: this.loadSub }
      searchData('module', search).then(rs => {
        this.modules = rs.data.data
      })
    }
  }
}
</script>

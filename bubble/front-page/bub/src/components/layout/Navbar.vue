<template>
  <a-menu
    mode="inline"
    :style="{ height: '100%', borderRight: 10 }"
  >
    <template v-for="item in modules">
      <a-menu-item v-if="item.modules.length === 0" :key="item.listId">
        <router-link :to="item.uri">
          <span>
            <a-icon v-if="icons[item.listId]" :type="icons[item.listId]" />{{ item.name }}
          </span>
        </router-link>
      </a-menu-item>
      <a-sub-menu v-if="item.modules.length > 0">
        <span slot="title"><a-icon v-show="icons[item.listId]" :type="icons[item.listId]" />{{ item.name }}</span>
        <a-menu-item v-for="subItem in item.modules" :key="subItem.listId">
          <router-link :to="subItem.uri">
            <span><a-icon v-if="icons[subItem.listId]" :type="icons[subItem.listId]" />{{ subItem.name }}</span>
          </router-link>
        </a-menu-item>
      </a-sub-menu>
    </template>
  </a-menu>
</template>
<script>
import {
  searchData
} from '@/api/base'
import icons from '@/store/settings/icons'

console.log('icons', icons)
export default {
  props: ['parentId', 'loadSub'],
  data() {
    return {
      component: '@/views/party/user-type',
      last: null,
      // 功能页面菜单列表
      modules: [],
      icons: icons
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

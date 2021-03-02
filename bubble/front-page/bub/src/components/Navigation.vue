<template>
  <a-menu
    theme="dark"
    mode="horizontal"
    :default-selected-keys="['1']"
    :style="{ lineHeight: '64px' }"
  >
    <template v-if="modules.length !== 0">
      <!--<a-menu-item key="home">
        <router-link :to="{ name: 'home-index' }">
          首页
        </router-link>
      </a-menu-item>
      <a-menu-item key="auth">
        <router-link :to="{ name: 'auth-index' }">
          配置中心
        </router-link>
      </a-menu-item>
      <a-menu-item key="party">
        <router-link :to="{ name: 'party-index' }">
          对象管理
        </router-link>
      </a-menu-item>
      <a-menu-item key="task">
        <router-link :to="{ name: 'task-index' }">
          任务中心
        </router-link>
      </a-menu-item>
      <a-menu-item key="system">
        <router-link :to="{ name: 'system-index' }">
          系统管理
        </router-link>
      </a-menu-item>-->
      <a-menu-item v-for="item in modules" :key="item.listId">
        <router-link :to="item.uri">
          {{ item.name }}
        </router-link>
      </a-menu-item>
    </template>
  </a-menu>
</template>
<script>
import {
  searchData
} from '@/api/base'
export default {
  data() {
    return {
      modules: []
    }
  },
  created() {
    this.loadingModule()
  },
 /* beforeRouteEnter(to, from, next) {
    console.log('beforeRouteEnterbeforeRouteEnterbeforeRouteEnterbeforeRouteEnter')
    const search = { parentId: 0 }
    searchData('module', search).then((rs) => {
      next(vm => { vm.setData(rs.data.data) })
    })
  },*/
  methods: {
    loadingModule() {
      const search = { parentId: 0 }
      searchData('module', search).then((rs) => {
        this.modules = rs.data.data
      })
    },
    setData(data) {
      this.modules = data
    }
  }
}
</script>

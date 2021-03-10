<template>
  <a-menu
    theme="dark"
    mode="horizontal"
    :default-selected-keys="['1']"
    :style="{ lineHeight: '64px' }"
  >
    <template v-if="modules.length !== 0">
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

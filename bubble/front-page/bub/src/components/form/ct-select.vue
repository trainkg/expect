<template>
  <a-select
    show-search
    :value="value"
    placeholder="Select users"
    style="width: 100%"
    :show-arrow="true"
    :filter-option="filterOption"
    :not-found-content="fetching ? undefined : null"
    @change="handleChange"
  >
    <a-spin v-if="fetching" slot="notFoundContent" size="small" />
    <a-select-option v-for="d in data" :key="d.value">
      {{ d.text }}
    </a-select-option>
  </a-select>
</template>
<script>
import debounce from 'lodash/debounce'
import reqwest from 'reqwest'
export default {
  props: ['codeName', 'initValue'],
  data() {
    this.lastFetchId = 0
    this.fetchUser()
    console.log('fetching us1111er', this.initValue)
    return {
      data: [],
      fetching: false,
      value: this.initValue
    }
  },
  created() {
    // loading form define
  },
  beforeMount() {
    console.log('before mount,this form key is ')
  },
  methods: {
    fetchUser(value) {
      console.log('fetching user', this.value)
      const uri = process.env.VUE_APP_URL + '/codetable/' + this.codeName
      this.lastFetchId += 1
      const fetchId = this.lastFetchId
      this.data = []
      this.fetching = true
      reqwest({
        url: uri,
        method: 'get',
        type: 'json'
      }).then(rs => {
        if (fetchId !== this.lastFetchId) {
          // for fetch callback order
          return
        }
        const data = rs.map(target => ({
          text: `${target.cid} ${target.description}`,
          value: target.cid
        }))
        this.data = data
        this.fetching = false
      })
    },
    filterOption(input, option) {
      return (
        option.componentOptions.children[0].text.toLowerCase().indexOf(input.toLowerCase()) >= 0
      )
    },
    handleChange(value) {
      this.value = value
    }
  }
}
</script>

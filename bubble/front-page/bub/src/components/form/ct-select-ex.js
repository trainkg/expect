import { Select } from 'ant-design-vue'
import reqwest from 'reqwest'
export default {
  extends: Select,
  props: {
    codeName: String,
    initValue: String,
    placeholder: {
      type: String,
      default: 'Select Bubble User'
    }
  },
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
    console.log('execute1 ... ', this.$props)
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
    }
  }
}

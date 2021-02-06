<template>
  <a-form-model :model="form"  layout="vertical">
    <a-collapse v-model="activeKey" :expand-icon-position="left">
      <a-collapse-panel key="1" header="This is panel header 1">
        <a-icon slot="extra" type="setting" />
        <a-row :gutter="[16,8]">
          <a-col :span="6">
            <a-form-model-item label="Activity name">
              <a-input v-model="form.name" />
            </a-form-model-item>
          </a-col>
          <a-col :span="6">
            <a-form-model-item label="Activity zone">
              <a-select v-model="form.region" placeholder="please select your zone">
                <a-select-option value="shanghai">
                  Zone one
                </a-select-option>
                <a-select-option value="beijing">
                  Zone two
                </a-select-option>
              </a-select>
            </a-form-model-item>
          </a-col>
          <a-col :span="6">
            <a-form-model-item label="Activity type">
              <a-checkbox-group v-model="form.type">
                <a-checkbox value="1" name="type">
                  Online
                </a-checkbox>
                <a-checkbox value="2" name="type">
                  Promotion
                </a-checkbox>
                <a-checkbox value="3" name="type">
                  Offline
                </a-checkbox>
              </a-checkbox-group>
            </a-form-model-item>
          </a-col>
          <a-col :span="6">
            <a-form-model-item label="Resources">
              <a-radio-group v-model="form.resource">
                <a-radio value="1">
                  Sponsor
                </a-radio>
                <a-radio value="2">
                  Venue
                </a-radio>
              </a-radio-group>
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-collapse-panel>
      <a-collapse-panel key="2" header="This is panel header 2">
        <a-icon slot="extra" type="setting" />
        <a-row :gutter="[16,8]">
          <a-col :span="6">
            <a-form-model-item label="Activity name">
              <a-input v-model="form.name" />
            </a-form-model-item>
          </a-col>
          <a-col :span="6">
            <a-form-model-item label="Activity zone">
              <a-select v-model="form.region" placeholder="please select your zone">
                <a-select-option value="shanghai">
                  Zone one
                </a-select-option>
                <a-select-option value="beijing">
                  Zone two
                </a-select-option>
              </a-select>
            </a-form-model-item>
          </a-col>
          <a-col :span="6">
            <a-form-model-item label="Activity type">
              <a-checkbox-group v-model="form.type">
                <a-checkbox value="1" name="type">
                  Online
                </a-checkbox>
                <a-checkbox value="2" name="type">
                  Promotion
                </a-checkbox>
                <a-checkbox value="3" name="type">
                  Offline
                </a-checkbox>
              </a-checkbox-group>
            </a-form-model-item>
          </a-col>
          <a-col :span="6">
            <a-form-model-item label="Resources">
              <a-radio-group v-model="form.resource">
                <a-radio value="1">
                  Sponsor
                </a-radio>
                <a-radio value="2">
                  Venue
                </a-radio>
              </a-radio-group>
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-collapse-panel>
    </a-collapse>

    <a-row :gutter="[16,8]">
      <a-col :span="12">
        <a-form-model-item label="Activity t11me">
          <a-date-picker
            v-model="form.date1"
            show-time
            type="date"
            placeholder="Pick a date"
            style="width: 100%;"
          />
        </a-form-model-item>
      </a-col>
      <a-col :span="12">
        <a-form-model-item label="Instant delivery">
          <a-switch v-model="form.delivery" />
        </a-form-model-item>
      </a-col>
    </a-row>

    <a-form-model-item label="Activity form">
      <a-input v-model="form.desc" type="textarea" />
    </a-form-model-item>
    <a-form-model-item :wrapper-col="{ span: 14, offset: 4 }">
      <a-button type="primary" @click="onSubmit">
        Create
      </a-button>
      <a-button style="margin-left: 10px;">
        Cancel
      </a-button>
    </a-form-model-item>
  </a-form-model>
</template>
<script>
export default {
  data() {
    return {
      activeKey: ['1','2'],
      labelCol: { span: 2 },
      wrapperCol: { span: 24 },
      form: {
        name: '',
        region: undefined,
        date1: undefined,
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      }
    }
  },
  methods: {
    onSubmit() {
      console.log('submit!', this.form)
    }
  }
}
</script>

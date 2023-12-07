<template>
  <div class="app-container" style="height: 100%">
    <el-container id="starter">
      <el-button id="starter-button"
                 @click="$emit('click')"
                 type="success"
                 round
                 v-hasPermi="['interviewee:register:add']"
      >
        开始录入
      </el-button>
    </el-container>
  </div>
</template>

<script>


export default {
  name: 'RegisterStart',
  data() {
    return {
      // 表单校验
      rules: {}
    }
  },
  created() {

  },
  methods: {}
}
</script>
<style scoped lang="scss">
#starter {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}

#starter-button {
  width: 130px;
  height: 50px;
  border-radius: 30px;
  font-size: 17px;
}
</style>

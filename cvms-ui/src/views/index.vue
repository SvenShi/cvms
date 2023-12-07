<template>
  <div class="app-container">
    <el-container>
      <el-header>
        欢迎使用简历管理系统，{{ username }}
      </el-header>
      <el-main>

      </el-main>
    </el-container>

  </div>
</template>

<script>

export default {
  name: 'Index',
  data() {
    return {
      username: this.$store.getters.name
    }
  },
  created() {
  },
  methods: {}
}
</script>

<style lang="scss" scoped>
</style>

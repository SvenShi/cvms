<template>
  <div class="app-container">
    <el-container style="align-items: center">
      <el-card style="width: 100%; align-items: center">
        <div slot="header" class="clearfix">上传简历</div>
        <el-upload
          ref="upload"
          action=""
          :on-remove="onChange"
          :before-remove="beforeRemove"
          drag
          multiple
          :limit="limit"
          :on-change="onChange"
          :on-exceed="handleExceed"
          :auto-upload="false"
          accept=".pdf,.doc,.docx,.md,.txt"
        >
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
      </el-card>
    </el-container>
  </div>
</template>

<script>


export default {
  name: 'UploadCv',
  props: {
    fileList: {
      type: Array,
      default: []
    },
    limit: {
      type: Number,
      default: 3
    }
  },
  data() {
    return {}
  },
  methods: {
    onChange(file, fileList) {
      this.fileList.length = 0
      fileList.forEach(item => {
        this.fileList.push(item)
      })
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 ${this.limit} 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`)
    }
  }
}
</script>
<style scoped lang="scss">

.input {
  width: 40% !important;
  min-width: 200px;
}

</style>

<template>
  <div class="app-container">
    <el-container style="align-items: center">
      <el-card style="width: 100%; align-items: center">
        <div slot="header" class="clearfix">上传简历</div>
        <el-upload
          action="https://jsonplaceholder.typicode.com/posts/"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
          drag
          multiple
          :limit="3"
          :on-exceed="handleExceed"
          :file-list="fileList"
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
      default: () => {
        return [{
          name: 'food.jpeg',
          url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
        }, {
          name: 'food2.jpeg',
          url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
        }]
      }
    }
  },
  data() {
    // TODO: 2023/12/2 23:11 sven file upload feature need to add
    return {}
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList)
    },
    handlePreview(file) {
      console.log(file)
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
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

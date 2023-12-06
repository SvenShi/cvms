<template>
  <div>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleUpdate"
          v-hasPermi="['interviewee:interviewee:upload']"
        >上传
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['interviewee:interviewee:remove']"
        >删除
        </el-button>
      </el-col>
      <right-toolbar :search="false" @queryTable="getList"></right-toolbar>
    </el-row>
    <el-row>
      <el-table height="300px" v-loading="loading" :data="cvList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"/>
        <el-table-column label="ID" width="100px" align="center" prop="id"/>
        <el-table-column label="简历名称" align="center" prop="name"/>
        <el-table-column label="操作" width="150px" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handlePreview(scope.row)"
              v-hasPermi="['cv:cv:preview']"
            >预览
            </el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDownload(scope.row)"
              v-hasPermi="['cv:cv:download']"
            >下载
            </el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['cv:cv:remove']"
            >删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>


    <el-dialog title="简历上传" :visible.sync="showUpload" width="500px" append-to-body>
      <upload-cv :file-list="fileList" :limit="3 - existFileCount"/>
    </el-dialog>
  </div>
</template>

<script>
import { listCv } from '@/api/cv/cv'
import UploadCv from '@/views/interviewee/register/uploadCv.vue'

export default {
  name: 'CvTable',
  components: { UploadCv },
  props: {
    intervieweeId: {
      type: String,
      default: null
    }
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 人才库表格数据
      cvList: [],
      // 显示简历上传弹窗
      showUpload: false,
      // 上传的文件列表
      fileList: [],
      // 现存文件数量
      existFileCount: 0
    }
  },
  created() {
  },
  methods: {
    /** 查询简历列表 */
    getList() {
      this.loading = true
      listCv(this.intervieweeId).then(response => {
        this.intervieweeList = response.rows
        this.total = response.total
        this.loading = false
      })
      this.loading = false
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },

    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除人才库编号为"' + ids + '"的数据项？').then(function() {
        return delInterviewee(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    /** 预览 */
    handlePreview(row) {

    },
    /** 下载 */
    handleDownload(row) {

    },
    /**
     * 上传简历
     */
    uploadCv() {

    }
  }
}
</script>

<style scoped lang="scss">

</style>

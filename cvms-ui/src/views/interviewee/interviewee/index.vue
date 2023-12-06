<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm"  size="small" :inline="true" v-show="showSearch"
             label-width="68px"
    >
      <el-row>
        <el-form-item label-width="100px" label="姓名" prop="name">
          <el-input
            v-model="queryParams.name"
            placeholder="请输入姓名"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label-width="100px" label="性别" prop="gender">
          <el-select v-model="queryParams.gender" placeholder="请选择性别" clearable>
            <el-option
              v-for="dict in dict.type.sys_user_sex"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label-width="100px" label="生日" prop="birthday">
          <el-date-picker clearable
                          v-model="queryParams.birthday"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择生日"
          >
          </el-date-picker>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item label-width="100px" label="联系方式" prop="contact">
          <el-input
            v-model="queryParams.contact"
            placeholder="请输入联系方式"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label-width="100px" label="学历" prop="degree">
          <el-select v-model="queryParams.degree" multiple placeholder="请选择学历" clearable>
            <el-option
              v-for="dict in dict.type.sys_user_sex"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label-width="100px" label="期望薪资范围" prop="salaryStart">
          <el-input
            v-model.number="queryParams.salaryStart"
            clearable
            placeholder="期望薪资"
            @keyup.enter.native="handleQuery"
            style="width: 165px; margin-right: 5px"
          />
          -
          <el-input
            v-model.number="queryParams.salaryEnd"
            clearable
            placeholder="期望薪资"
            @keyup.enter.native="handleQuery"
            style="width: 165px; margin-left: 5px"
          />
        </el-form-item>
      </el-row>
      <el-row>

        <el-form-item label-width="100px" label="创建人" prop="createBy">
          <el-input
            v-model="queryParams.createBy"
            placeholder="请输入创建人"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label-width="100px" label="创建时间" prop="createTime">
          <el-date-picker clearable
                          v-model="queryParams.createTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择创建时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label-width="100px" label="年龄范围" prop="ageStart">
          <el-input
            v-model.number="queryParams.ageStart"
            clearable
            placeholder="年龄"
            @keyup.enter.native="handleQuery"
            style="width: 165px; margin-right: 5px"
          />
          -
          <el-input
            v-model.number="queryParams.ageEnd"
            clearable
            placeholder="年龄"
            @keyup.enter.native="handleQuery"
            style="width: 165px; margin-left: 5px"
          />
        </el-form-item>
      </el-row>
      <el-row>
        <div style="float: right;margin-bottom: 18px">
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </div>
      </el-row>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['interviewee:interviewee:edit']"
        >修改
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
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['interviewee:interviewee:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="intervieweeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="ID" align="center" prop="id"/>
      <el-table-column label="姓名" align="center" prop="name"/>
      <el-table-column label="性别" align="center" prop="gender">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.gender"/>
        </template>
      </el-table-column>
      <el-table-column label="生日" align="center" prop="birthday" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.birthday, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="年龄" align="center" prop="age"/>
      <el-table-column label="联系方式" align="center" prop="contact"/>
      <el-table-column label="邮箱" align="center" prop="email"/>
      <el-table-column label="学历" align="center" prop="degree">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.degree"/>
        </template>
      </el-table-column>
      <el-table-column label="政治面貌" align="center" prop="political"/>
      <el-table-column label="应聘岗位" align="center" prop="job"/>
      <el-table-column label="期望薪资" align="center" prop="salary"/>
      <el-table-column label="操作" width="150px" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-document"
            @click="handleCv(scope.row)"
            v-hasPermi="['interviewee:interviewee:cv']"
          >简历
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['interviewee:interviewee:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['interviewee:interviewee:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改人才库对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label-width="100px" label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名"/>
        </el-form-item>
        <el-form-item label-width="100px" label="性别" prop="gender">
          <el-select v-model="form.gender" placeholder="请选择性别">
            <el-option
              v-for="dict in dict.type.sys_user_sex"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label-width="100px" label="生日" prop="birthday">
          <el-date-picker clearable
                          v-model="form.birthday"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择生日"
                          default-value="2000-01-01"
                          @change="birthdayChange"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label-width="100px" label="年龄" prop="age">
          <el-input class="input" v-model.number="form.age" placeholder="请输入年龄"/>
        </el-form-item>
        <el-form-item label-width="100px" label="联系方式" prop="contact">
          <el-input v-model="form.contact" placeholder="请输入联系方式"/>
        </el-form-item>
        <el-form-item label-width="100px" label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱"/>
        </el-form-item>
        <el-form-item label-width="100px" label="学历" prop="degree">
          <el-select v-model="form.degree" placeholder="请选择学历">
            <el-option
              v-for="dict in dict.type.sys_user_sex"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label-width="100px" label="政治面貌" prop="political">
          <el-input v-model="form.political" placeholder="请输入政治面貌"/>
        </el-form-item>
        <el-form-item label-width="100px" label="应聘岗位" prop="job">
          <el-input v-model="form.job" placeholder="请输入应聘岗位"/>
        </el-form-item>
        <el-form-item label-width="100px" label="期望薪资" prop="salary">
          <el-input v-model.number="form.salary" placeholder="请输入期望薪资"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog :title="cvTittle" :visible.sync="cvShow" width="50vw" append-to-body>
      <cv-table ref="cvDialog" :interviewee-id="intervieweeId"/>
    </el-dialog>

  </div>
</template>

<script>
import {
  listInterviewee,
  getInterviewee,
  delInterviewee,
  addInterviewee,
  updateInterviewee
} from '@/api/interviewee/interviewee'
import CvTable from '@/views/interviewee/interviewee/cvTable.vue'

export default {
  name: 'Interviewee',
  components: { CvTable },
  dicts: ['sys_user_sex'],
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
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 人才库表格数据
      intervieweeList: [],
      // 弹出层标题
      title: '',
      cvTittle: '',
      // 是否显示弹出层
      open: false,
      cvShow: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        gender: null,
        birthday: null,
        contact: null,
        email: null,
        degree: null,
        political: null,
        job: null,
        salaryStart: null,
        salaryEnd: null,
        ageStart: null,
        ageEnd: null,
        createBy: null,
        createTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: '姓名不能为空' }
        ],
        gender: [
          { required: true, message: '性别不能为空' }
        ],
        age: [
          { required: true, message: '年龄不能为空' },
          { type: 'number', message: '年龄必须为数字值' }
        ],
        email: [
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ]
      },
      // 人才id
      intervieweeId: null
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询人才库列表 */
    getList() {
      this.loading = true
      listInterviewee(this.queryParams).then(response => {
        this.intervieweeList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        gender: null,
        birthday: null,
        age: null,
        contact: null,
        email: null,
        degree: null,
        political: null,
        job: null,
        salary: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.queryParams.salaryStart = null
      this.queryParams.salaryEnd = null
      this.queryParams.ageStart = null
      this.queryParams.ageEnd = null
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getInterviewee(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改人才信息'
      })
    },
    /** 简历管理 */
    handleCv(row) {
      this.cvTittle = row.name + '简历管理'
      this.intervieweeId = row.id
      this.cvShow = true
      let that = this;
      this.$nextTick(()=>{
        console.log(that.$refs.cvDialog)
        that.$refs.cvDialog.getList()
      })

    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateInterviewee(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addInterviewee(this.form).then(response => {
              this.$modal.msgSuccess('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
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
    /** 导出按钮操作 */
    handleExport() {
      this.download('interviewee/export', {
        ...this.queryParams
      }, `interviewee_${new Date().getTime()}.xlsx`)
    },
    /**
     * 自动更新年龄
     * @param date
     */
    birthdayChange(date) {
      let birthday = new Date(date)
      let today = new Date()
      let age = today.getFullYear() - birthday.getFullYear()
      let m = today.getMonth() - birthday.getMonth()
      if (m < 0 || (m === 0 && today.getDate() < birthday.getDate())) {
        age--
      }
      this.form.age = age < 0 ? undefined : age
    }
  }
}
</script>
<style scoped lang="scss">
.el-form-item {
  margin-right: calc((100vw - 1200px) / 4) !important;
}

.el-date-editor {
  width: 205px !important;
}

</style>

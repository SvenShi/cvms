<template>
  <div class="app-container">
    <el-container style="align-items: center">
      <el-card style="width: 100%;align-items: center">
        <div slot="header" class="clearfix">人才信息录入</div>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="姓名" prop="name">
            <el-input class="input" v-model="form.name" placeholder="请输入姓名"/>
          </el-form-item>
          <el-form-item label="性别" prop="gender">
            <el-select v-model="form.gender" placeholder="性别" style="width: 80px">
              <el-option
                v-for="dict in dict.type.sys_user_sex"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="生日" prop="birthday">
            <el-date-picker clearable
                            class="input"
                            v-model="form.birthday"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="请选择生日"
                            default-value="2000-01-01"
                            @change="birthdayChange"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item label="年龄" prop="age">
            <el-input class="input" v-model.number="form.age" placeholder="请输入年龄"/>
          </el-form-item>
          <el-form-item label="联系方式" prop="contact">
            <el-input class="input" v-model="form.contact" placeholder="请输入联系方式"/>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input class="input" v-model="form.email" placeholder="请输入邮箱"/>
          </el-form-item>
          <el-form-item label="学历" prop="degree">
            <el-select v-model="form.degree" placeholder="学历" style="width: 90px">
              <el-option
                v-for="dict in dict.type.sys_user_sex"
                :key="dict.value"
                :label="dict.label"
                :value="parseInt(dict.value)"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="政治面貌" prop="political">
            <el-input class="input" v-model="form.political" placeholder="请输入政治面貌"/>
          </el-form-item>
          <el-form-item label="工作年限" prop="workYear">
            <el-input class="input" v-model.number="form.workYear" placeholder="请输入工作年限"/>
          </el-form-item>
          <el-form-item label="应聘岗位" prop="job">
            <el-input class="input" v-model="form.job" placeholder="请输入应聘岗位"/>
          </el-form-item>
          <el-form-item label="期望薪资" prop="salary">
            <el-input class="input" v-model.number="form.salary" type="" placeholder="请输入期望薪资"/>
          </el-form-item>
        </el-form>
      </el-card>
    </el-container>
  </div>
</template>

<script>


export default {
  name: 'IntervieweeForm',
  dicts: ['sys_user_sex'],
  props: {
    // 表单参数
    form: {
      name: '',
      gender: '',
      age: null,
      birthday: '',
      contact: '',
      email: '',
      degree: '',
      political: '',
      workYear: null,
      job: '',
      salary: ''
    }
  },
  data() {
    return {
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
        workYear:
          [
            { type: 'number', message: '工作年限必须为数字值' }
          ],
        email: [
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ]
      }
    }
  },
  created() {

  },
  methods: {
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
    },
    validate() {
      return this.$refs.form.validate()
    },
    resetForm() {
      return this.$refs.form.resetFields()
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

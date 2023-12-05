<template>
  <div class="app-container">
    <el-container style="align-items: center">

      <el-header class="header">
        <el-steps :active="step">
          <el-step title="开始" icon="el-icon-warning-outline"></el-step>
          <el-step title="信息录入" icon="el-icon-edit"></el-step>
          <el-step title="上传简历" icon="el-icon-upload"></el-step>
          <el-step title="完成" icon="el-icon-circle-check"></el-step>
        </el-steps>
      </el-header>
      <el-main class="main">
        <register-start @click="move(1)" :step="step" v-show="step == 1"/>
        <interviewee-form ref="interForm" v-show="step == 2" :form="form"/>
        <upload-cv :file-list="fileList" v-show="step == 3"/>
        <register-complete :type="submitMessage.type" :detail="submitMessage.detail" @click="reset" v-show="step == 4"/>
      </el-main>
      <el-footer class="footer">
        <el-button @click="move(-1)" type="primary" v-show="step == 3">上一步</el-button>
        <el-button @click="move(1)" type="primary" v-show="step == 2">下一步</el-button>
        <el-button @click="submit" type="success" v-show="step == 3">完成</el-button>
      </el-footer>
    </el-container>
  </div>
</template>

<script>


import IntervieweeForm from '@/views/interviewee/register/intervieweeForm.vue'
import RegisterStart from '@/views/interviewee/register/registerStart.vue'
import UploadCv from '@/views/interviewee/register/uploadCv.vue'
import RegisterComplete from '@/views/interviewee/register/registerComplete.vue'
import { registerInterviewee } from '@/api/interviewee/interviewee'
import item from '@/layout/components/Sidebar/Item.vue'

export default {
  name: 'Register',
  components: { RegisterComplete, UploadCv, RegisterStart, IntervieweeForm },
  data() {
    return {
      // 当前步数
      step: 1,
      fileList: [],
      // 表单参数
      form: {
        name: '',
        gender: '',
        age: undefined,
        birthday: '',
        contact: '',
        email: '',
        degree: '',
        political: '',
        job: '',
        salary: ''
      },
      // 表单校验
      rules: {},
      // 提交完成信息
      submitMessage: {
        type: 'success',
        detail: ''
      }
    }
  },
  created() {

  },
  methods: {
    /**
     * 移动
     * @param step
     */
    move(step) {
      if (this.step == 2) {
        let that = this
        //   检查表单要求
        this.$refs.interForm.validate().then((valid, obj) => {
            if (valid) {
              that.step += step
            }
          }
        )
      } else {
        this.step += step
      }
    },
    /**
     * 提交
     */
    submit() {
      this.$modal.confirm(`确认提交录入请求并上传[${this.fileList.length}]个简历`).then(() => {
        let formData = new FormData()
        if (this.fileList.length > 0) {
          this.fileList.forEach(item => {
            formData.append('fileList', item.raw)
          })
        }
        for (let key in this.form) {
          formData.append(key, this.form[key])
        }
        registerInterviewee(formData).then(res => {
          if (res.code == 200) {
            this.submitMessage.type = 'success'
          } else {
            this.submitMessage.type = 'error'
            if (res.msg == null) {
              this.submitMessage.detail = '出现异常，请重新尝试录入'
            } else {
              this.submitMessage.detail = res.msg
            }
          }
          this.move(1)
        })
      })
    },
    /**
     * 提交
     */
    reset(type) {
      if (type == 'success') {
        this.form = {
          name: '',
          gender: '',
          age: 0,
          birthday: '',
          contact: '',
          email: '',
          degree: '',
          political: '',
          job: '',
          salary: ''
        }
        this.$refs.interForm.resetForm()
        this.step = 2
        this.fileList = []
        this.submitMessage.type = 'success'
        this.submitMessage.detail = ''
      }
    }
  }
}
</script>
<style scoped lang="scss">
.header {
  width: 40%;
  min-width: 500px;
  height: 30px !important;
}

.main {
  height: calc(100vh - 155px);
  width: 40%;
  min-width: 500px;
}

.input {
  width: 40% !important;
  min-width: 200px;
}

.footer {
  position: absolute;
  bottom: 0;
  align-items: center;
}
</style>

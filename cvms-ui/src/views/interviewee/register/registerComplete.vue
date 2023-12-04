<template>
  <div class="app-container" style="height: 100%">

    <el-container class="complete-container" v-if="type == 'success'">
      <div class="animate success complete-text">
        <span>录</span><span>入</span><span>成</span><span>功</span>
      </div>
      <el-button @click="$emit('click',type)" type="success" round>继续录入</el-button>
    </el-container>


    <el-container class="complete-container" v-if="type == 'error'">
      <div class="animate error complete-text">
        <span>录</span><span>入</span><span>失</span><span>败</span>
      </div>
      <span>{{ detail }}</span>
      <el-button @click="$emit('click',type)" type="primary" round>重新录入</el-button>
    </el-container>
  </div>
</template>

<script>


export default {
  name: 'RegisterComplete',
  props: {
    type: {
      type: String,
      required: true,
      default: 'success',
      validator: function(value) {
        // 这个值必须匹配下列字符串中的一个
        return ['success', 'error'].includes(value)
      }
    },
    detail: {
      type: String,
      default: ''
    }
  },
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

.complete-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}

.complete-text {
  position: absolute;
  top: 30vh;
  font-size: 50px;
}

a, a:link, a:visited {
  text-decoration: none;
  padding: 9px 15px;
  border: 1px solid #ececec;
  border-radius: 5px;
  color: gray;
}

.animate {
  font-size: 50px;
}

.animate span {
  display: inline-block;
}

.animate span:nth-of-type(2) {
  animation-delay: .05s;
}

.animate span:nth-of-type(3) {
  animation-delay: .1s;
}

.animate span:nth-of-type(4) {
  animation-delay: .15s;
}

.animate span:nth-of-type(5) {
  animation-delay: .2s;
}

.animate span:nth-of-type(6) {
  animation-delay: .25s;
}

.animate span:nth-of-type(7) {
  animation-delay: .3s;
}

.animate span:nth-of-type(8) {
  animation-delay: .35s;
}

.animate span:nth-of-type(9) {
  animation-delay: .4s;
}

.animate span:nth-of-type(10) {
  animation-delay: .45s;
}

.animate span:nth-of-type(11) {
  animation-delay: .5s;
}

.animate span:nth-of-type(12) {
  animation-delay: .55s;
}

.animate span:nth-of-type(13) {
  animation-delay: .6s;
}

.animate span:nth-of-type(14) {
  animation-delay: .65s;
}

.animate span:nth-of-type(15) {
  animation-delay: .7s;
}

.animate span:nth-of-type(16) {
  animation-delay: .75s;
}

.animate span:nth-of-type(17) {
  animation-delay: .8s;
}

.animate span:nth-of-type(18) {
  animation-delay: .85s;
}

.animate span:nth-of-type(19) {
  animation-delay: .9s;
}

.animate span:nth-of-type(20) {
  animation-delay: .95s;
}

.success span {
  color: #67C23A;
  opacity: 0;
  transform: translate(-150px, 0) scale(.3);
  animation: leftRight-success .5s forwards;
}

.error span {
  color: #F56C6C;
  opacity: 0;
  transform: translate(-150px, 0) scale(.3);
  animation: leftRight-error .5s forwards;
}

@keyframes leftRight-success {
  40% {
    transform: translate(50px, 0) scale(.7);
    opacity: 1;
    color: #67C23A;
  }

  60% {
    color: #409EFF;
  }

  80% {
    transform: translate(0) scale(2);
    opacity: 0;
  }

  100% {
    transform: translate(0) scale(1);
    opacity: 1;
  }
}

@keyframes leftRight-error {
  40% {
    transform: translate(50px, 0) scale(.7);
    opacity: 1;
    color: #F56C6C;
  }

  60% {
    color: #E6A23C;
  }

  80% {
    transform: translate(0) scale(2);
    opacity: 0;
  }

  100% {
    transform: translate(0) scale(1);
    opacity: 1;
  }
}


</style>

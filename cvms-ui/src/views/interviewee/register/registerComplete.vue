<template>
  <div class="app-container" style="height: 100%">
    <el-container id="complete">
      <div id="complete-text" class="animate seven">
        <span>录</span><span>入</span><span>成</span><span>功</span><span>!</span>
      </div>
      <el-button @click="$emit('click')" type="success" round>继续录入</el-button>
    </el-container>
  </div>
</template>

<script>


export default {
  name: 'RegisterComplete',
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

#complete {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}

#complete-text {
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

.seven span {
  color: #348c04;
  opacity: 0;
  transform: translate(-150px, 0) scale(.3);
  animation: leftRight .5s forwards;
}

@keyframes leftRight {
  40% {
    transform: translate(50px, 0) scale(.7);
    opacity: 1;
    color: #348c04;
  }

  60% {
    color: #235ae7;
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

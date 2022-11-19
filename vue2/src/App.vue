<template>
  <div id="app">
    <transition :name="transitionName">
      <router-view></router-view>
    </transition>
  </div>
</template>

<style>
  *{
    padding: 0;
    margin: 0;
  }

  a {
    text-decoration: none;
    color: #000;
  }
  li{
    list-style: none;
  }

  @font-face {
    font-family: "ALMM";font-weight: 700;src: url('./assets/font/阿里妈妈数黑体/Alimama_ShuHeiTi_Bold.woff2') format("woff2"),
    url('./assets/font/阿里妈妈数黑体/Alimama_ShuHeiTi_Bold.woff') format("woff");
    font-display: swap;
  }

  ::-webkit-scrollbar{
    display:none;
  }

 /* .slide-right-enter-active,
  .slide-right-leave-active,
  .slide-left-enter-active,
  .slide-left-leave-active {
    will-change: transform;
    transition: all 500ms;
    position: absolute;
  }

  .slide-right-enter {
    opacity: 0;
    transform: translate3d(-100%, 0, 0);
  }

  .slide-right-leave-active {
    opacity: 0;
    transform: translate3d(100%, 0, 0);
  }

  .slide-left-enter {
    opacity: 0;
    transform: translate3d(100%, 0, 0);
  }

  .slide-left-leave-active {
    opacity: 0;
    transform: translate3d(-100%, 0, 0);
  }
*/
</style>


<script>
import Layout from "@/views/Layout";
export default {
  components: {Layout},
  data() {
    return {
      transitionName: ''
    }
  },
  /*watch: { //使用watch 监听$router的变化
    $route(to, from) {
      //如果to索引大于from索引,判断为前进状态,反之则为后退状态
      if (to.meta.index > from.meta.index) {
        //设置动画名称
        this.transitionName = 'slide-left';
      } else {
        this.transitionName = 'slide-right';
      }
    }
  }*/
}

</script>
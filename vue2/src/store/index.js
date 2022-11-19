import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    flag:false,
    userId:''
  },
  getters: {
  },
  //用于操作数据
  mutations: {
    CHANGE(state){
      state.flag = !state.flag
    },
    USERID(state,userid){
      state.userId = userid
    }
  },
  // 用于响应组件中的动作
  actions: {
    change(context){
      context.commit('CHANGE')
    },
    userid(context,value){
      context.commit('USERID',value)
    }
  },
  modules: {
  }
})

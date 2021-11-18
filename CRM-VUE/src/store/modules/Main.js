import { main } from '../../api/Main'

const state = {
    mainInfo:{}
}


const mutations = {
  SET_USERID:(state,userId) => {
    state.userId = userId;
},
  SET_MAININFO:(state,mainInfo) => {
    state.mainInfo = mainInfo;
},
}


const actions = {
  main({ commit }, param) {
    return new Promise((resolve, reject) => {
      main(param).then(response => {
        //   const { data } = response
        commit('SET_MAININFO', response)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
    setUserId({commit},param){
      commit('SET_USERID',param)
    }
}


export default {
    namespaced: true,
    state,
    mutations,
    actions
  }
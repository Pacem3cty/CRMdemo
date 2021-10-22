import { login } from '../../api/Login'


const state = {
    loginInfo:{}
}


const mutations = {
    SET_LOGININFO:(state,loginInfo) => {
        state.loginInfo = loginInfo
    }
}


const actions = {
  login({commit},param){
        return new Promise((resolve, reject) => {
          login(param).then(response => {
            //   const { data } = response
              commit('SET_LOGININFO', response)
              resolve()
            }).catch(error => {
              reject(error)
            })
          })
    }
}


export default {
    namespaced: true,
    state,
    mutations,
    actions
  }
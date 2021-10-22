import { updatePwd,listUserInfo,updateUserInfo } from '../../api/User'


const state = {
   userInfo:{},
   addInfo:{},
   updatePwdInfo:{},
   updateInfo:{},
   delInfo:{}
}


const mutations = {
    SET_USERINFO:(state,userInfo) => {
        state.userInfo = userInfo
    },
    SET_ADDINFO: (state, addInfo) => {
      state.addInfo = addInfo
    },
    SET_UPDATEPWDINFO: (state, updatePwdInfo) => {
      state.updatePwdInfo = updatePwdInfo
    },
    SET_UPDATEINFO: (state, updateInfo) => {
      state.updateInfo = updateInfo
    },
    SET_DELINFO: (state, delInfo) => {
      state.delInfo = delInfo
    },
}


const actions = {
    updatePwd({commit},param){
        return new Promise((resolve, reject) => {
            updatePwd(param).then(response => {
            //   const { data } = response
              commit('SET_UPDATEPWDINFO', response)
              resolve()
            }).catch(error => {
              reject(error)
            })
          })
    },
  listUserInfo({commit},param){
    return new Promise((resolve, reject) => {
      listUserInfo(param).then(response => {
        //   const { data } = response
          commit('SET_USERINFO', response)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
},
updateUserInfo({commit},param){
  return new Promise((resolve, reject) => {
    updateUserInfo(param).then(response => {
      //   const { data } = response
        commit('SET_UPDATEINFO', response)
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
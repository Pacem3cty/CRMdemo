import { updatePwd, listUserInfo, updateUserInfo, queryAllUserInfo, listUserNameInfo, listTrueNameInfo, add, update, getCurrentId, del } from '../../api/User'



const state = {
  userInfo: {},
  addInfo: {},
  updatePwdInfo: {},
  updateInfo: {},
  delInfo: {},
  userNameInfo: {},
  trueNameInfo: {},
  currentIdInfo: {}

}


const mutations = {
  SET_USERINFO: (state, userInfo) => {
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


  SET_USERNAMEINFO: (state, userNameInfo) => {
    state.userNameInfo = userNameInfo
  },
  SET_TRUENAMEINFO: (state, trueNameInfo) => {
    state.trueNameInfo = trueNameInfo
  },
  SET_CURRENTIDINFO: (state, currentIdInfo) => {
    state.currentIdInfo = currentIdInfo
  },

}


const actions = {
  updatePwd({ commit }, param) {
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
  listUserInfo({ commit }, param) {
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
  updateUserInfo({ commit }, param) {
    return new Promise((resolve, reject) => {
      updateUserInfo(param).then(response => {
        //   const { data } = response
        commit('SET_UPDATEINFO', response)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  queryAllUserInfo({ commit }, param) {
    return new Promise((resolve, reject) => {
      queryAllUserInfo(param).then(response => {
        //   const { data } = response
        commit('SET_USERINFO', response)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })

  },
  listUserNameInfo({ commit }, param) {
    return new Promise((resolve, reject) => {
      listUserNameInfo(param).then(response => {
        //   const { data } = response
        commit('SET_USERNAMEINFO', response)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  listTrueNameInfo({ commit }, param) {
    return new Promise((resolve, reject) => {
      listTrueNameInfo(param).then(response => {
        //   const { data } = response
        commit('SET_TRUENAMEINFO', response)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  add({ commit }, param) {
    return new Promise((resolve, reject) => {
      add(param).then(response => {
        //   const { data } = response
        commit('SET_ADDINFO', response)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  update({ commit }, param) {
    return new Promise((resolve, reject) => {
      update(param).then(response => {
        //   const { data } = response
        commit('SET_UPDATEINFO', response)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  getCurrentId({ commit }, param) {
    return new Promise((resolve, reject) => {
      getCurrentId(param).then(response => {
        //   const { data } = response
        commit('SET_CURRENTIDINFO', response)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  del({ commit }, param) {
    return new Promise((resolve, reject) => {
      del(param).then(response => {
        //   const { data } = response
        commit('SET_DELINFO', response)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

}


export default {
  namespaced: true,
  state,
  mutations,
  actions
}
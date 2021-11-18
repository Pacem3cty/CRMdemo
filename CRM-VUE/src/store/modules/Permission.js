import { loadCheckedData,authModules } from '../../api/Permission'

const state = {
  checkedRoleIdInfo: {},
  authModulesInfo: {}
}


const mutations = {
  SET_CHECKEDROLEIDINFO: (state, checkedRoleIdInfo) => {
    state.checkedRoleIdInfo = checkedRoleIdInfo
  },
  SET_AUTHMODULESINFO: (state, authModulesInfo) => {
    state.authModulesInfo = authModulesInfo
  },
}


const actions = {
  loadCheckedData({ commit }, param) {
    return new Promise((resolve, reject) => {
      loadCheckedData(param).then(response => {
        //   const { data } = response
        commit('SET_CHECKEDROLEIDINFO', response)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  authModules({ commit }, param) {
    return new Promise((resolve, reject) => {
      authModules(param).then(response => {
        //   const { data } = response
        commit('SET_AUTHMODULESINFO', response)
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
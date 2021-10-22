import { queryAll, del } from '../../api/Sales'


const state = {
  salesInfo: {},
  addInfo: {},
  updateInfo: {},
  delInfo: {}
}


const mutations = {
  SET_SALESINFO: (state, salesInfo) => {
    state.salesInfo = salesInfo
  },
  SET_ADDINFO: (state, addInfo) => {
    state.addInfo = addInfo
  },
  SET_UPDATEINFO: (state, updateInfo) => {
    state.updateInfo = updateInfo
  },
  SET_DELINFO: (state, delInfo) => {
    state.delInfo = delInfo
  },
}


const actions = {
  queryAll({ commit }, param) {
    return new Promise((resolve, reject) => {
      queryAll(param).then(response => {
        //   const { data } = response
        commit('SET_SALESINFO', response)
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
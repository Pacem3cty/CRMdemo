import { loadModuleData } from '../../api/Module'

const state = {
    treeListInfo: {},
  }
  
  
  const mutations = {
    SET_TREELISTINFO: (state, treeListInfo) => {
      state.treeListInfo = treeListInfo
    },
  }
  
  
  const actions = {
    loadModuleData({ commit }, param) {
      return new Promise((resolve, reject) => {
        loadModuleData(param).then(response => {
          //   const { data } = response
          commit('SET_TREELISTINFO', response)
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
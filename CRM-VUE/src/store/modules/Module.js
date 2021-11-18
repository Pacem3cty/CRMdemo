import { loadModuleData,queryAllModuleInfo } from '../../api/Module'

const state = {
    treeListInfo: {},
    moduleInfo:{}
  }
  
  
  const mutations = {
    SET_TREELISTINFO: (state, treeListInfo) => {
      state.treeListInfo = treeListInfo
    },
    SET_MODULEINFO: (state, moduleInfo) => {
      state.moduleInfo = moduleInfo
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
    queryAllModuleInfo({ commit }, param) {
      return new Promise((resolve, reject) => {
        queryAllModuleInfo(param).then(response => {
          //   const { data } = response
          commit('SET_MODULEINFO', response)
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
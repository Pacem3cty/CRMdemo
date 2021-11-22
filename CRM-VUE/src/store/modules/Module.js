import { loadModuleData,queryAllModuleInfo,add,update,del,getCurrentId } from '../../api/Module'

const state = {
    treeListInfo: {},
    moduleInfo:{},
    deleteInfo:{},
    updateInfo:{},
    addInfo:{},
    currentIdInfo: {}
  }
  
  
  const mutations = {
    SET_TREELISTINFO: (state, treeListInfo) => {
      state.treeListInfo = treeListInfo
    },
    SET_MODULEINFO: (state, moduleInfo) => {
      state.moduleInfo = moduleInfo
    },
    SET_DELETEINFO: (state, deleteInfo) => {
      state.deleteInfo = deleteInfo
    },
    SET_UPDATEINFO: (state, updateInfo) => {
      state.updateInfo = updateInfo
    },
    SET_ADDINFO: (state, addInfo) => {
      state.addInfo = addInfo
    },
    SET_CURRENTIDINFO: (state, currentIdInfo) => {
    state.currentIdInfo = currentIdInfo
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
    del({ commit }, param) {
      return new Promise((resolve, reject) => {
        del(param).then(response => {
          //   const { data } = response
          commit('SET_DELETEINFO', response)
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
    
  }
  
  
  export default {
    namespaced: true,
    state,
    mutations,
    actions
  }
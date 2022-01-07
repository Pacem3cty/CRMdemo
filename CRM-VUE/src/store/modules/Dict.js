import { listDataNameInfo,queryAllDictInfo,del,add,update,getCurrentId } from '../../api/Dict'

const state = {
  dataNameInfo: {},
  dictInfo: {},
  deleteInfo: {},
  updateInfo: {},
  addInfo: {},
  currentIdInfo: {},
}

const mutations = {
    SET_DATANAMEINFO: (state, dataNameInfo) => {
      state.dataNameInfo = dataNameInfo
    },
    SET_DICTINFO: (state, dictInfo) => {
      state.dictInfo = dictInfo
    },
    SET_DELETEINFO:(state, deleteInfo) => {
      state.deleteInfo = deleteInfo
    },
    SET_UPDATEINFO:(state, updateInfo) => {
      state.updateInfo = updateInfo
    },
    SET_ADDINFO:(state, addInfo) => {
      state.addInfo = addInfo
    },
    SET_CURRENTIDINFO: (state, currentIdInfo) => {
      state.currentIdInfo = currentIdInfo
    },
  }
  
  
  const actions = {
    listDataNameInfo({ commit }, param) {
      return new Promise((resolve, reject) => {
        listDataNameInfo(param).then(response => {
          //   const { data } = response
          commit('SET_DATANAMEINFO', response)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    queryAllDictInfo({ commit }, param) {
      return new Promise((resolve, reject) => {
        queryAllDictInfo(param).then(response => {
          //   const { data } = response
          commit('SET_DICTINFO', response)
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
  }
  
  
  export default {
    namespaced: true,
    state,
    mutations,
    actions
  }
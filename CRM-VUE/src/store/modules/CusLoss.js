import { queryAllCusLossInfo,listCusManager,listCusName,update } from '../../api/CusLoss'

const state = {
    cusLossInfo: {},
    cusNameInfo:{},
    cusManagerInfo:{},
    updateInfo:{}
  }
  
  
  const mutations = {
    SET_CUSLOSSINFO: (state, cusLossInfo) => {
      state.cusLossInfo = cusLossInfo
    },
    SET_CUSNAMEINFO: (state, cusNameInfo) => {
      state.cusNameInfo = cusNameInfo
    },
    SET_CUSMANAGERINFO: (state, cusManagerInfo) => {
      state.cusManagerInfo = cusManagerInfo
    },
    SET_UPDATEINFO: (state, updateInfo) => {
      state.updateInfo = updateInfo
    },
  }
  
  
  const actions = {
    queryAllCusLossInfo({ commit }, param) {
      return new Promise((resolve, reject) => {
        queryAllCusLossInfo(param).then(response => {
          //   const { data } = response
          commit('SET_CUSLOSSINFO', response)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    listCusManager({ commit }, param) {
      return new Promise((resolve, reject) => {
        listCusManager(param).then(response => {
          //   const { data } = response
          commit('SET_CUSMANAGERINFO', response)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    listCusName({ commit }, param) {
      return new Promise((resolve, reject) => {
        listCusName(param).then(response => {
          //   const { data } = response
          commit('SET_CUSNAMEINFO', response)
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
  }
  
  
  export default {
    namespaced: true,
    state,
    mutations,
    actions
  }
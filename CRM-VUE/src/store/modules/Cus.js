import { queryAllCusInfo, getCurrentId, getCusNum,update,add,del,listCusName } from '../../api/Cus'

const state = {
  cusInfo: {},
  currentIdInfo: {},
  cusNumInfo: {},
  deleteInfo: {},
  updateInfo: {},
  addInfo: {},
  cusNameInfo:{},
}


const mutations = {
  SET_CUSINFO: (state, cusInfo) => {
    state.cusInfo = cusInfo
  },
  SET_CURRENTIDINFO: (state, currentIdInfo) => {
    state.currentIdInfo = currentIdInfo
  },
  SET_CUSNUMINFO: (state, cusNumInfo) => {
    state.cusNumInfo = cusNumInfo
  },
  SET_ADDINFO: (state, addInfo) => {
    state.addInfo = addInfo
  },
  SET_DELETEINFO: (state, deleteInfo) => {
    state.deleteInfo = deleteInfo
  },
  SET_UPDATEINFO: (state, updateInfo) => {
    state.updateInfo = updateInfo
  },
  SET_CUSNAMEINFO: (state, cusNameInfo) => {
    state.cusNameInfo = cusNameInfo
  },
}


const actions = {
  queryAllCusInfo({ commit }, param) {
    return new Promise((resolve, reject) => {
      queryAllCusInfo(param).then(response => {
        //   const { data } = response
        commit('SET_CUSINFO', response)
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
  getCusNum({ commit }, param) {
    return new Promise((resolve, reject) => {
      getCusNum(param).then(response => {
        //   const { data } = response
        commit('SET_CUSNUMINFO', response)
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
}


export default {
  namespaced: true,
  state,
  mutations,
  actions
}
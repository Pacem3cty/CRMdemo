import { listRoleNameInfo, queryAllRoleInfo,del,add,update,getCurrentId } from '../../api/Role'

const state = {
  roleNameInfo: {},
  roleInfo: {},
  deleteInfo: {},
  updateInfo: {},
  addInfo: {},
  currentIdInfo: {}
}


const mutations = {
  SET_ROLENAMEINFO: (state, roleNameInfo) => {
    state.roleNameInfo = roleNameInfo
  },
  SET_ROLEINFO: (state, roleInfo) => {
    state.roleInfo = roleInfo
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
  listRoleNameInfo({ commit }, param) {
    return new Promise((resolve, reject) => {
      listRoleNameInfo(param).then(response => {
        //   const { data } = response
        commit('SET_ROLENAMEINFO', response)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  queryAllRoleInfo({ commit }, param) {
    return new Promise((resolve, reject) => {
      queryAllRoleInfo(param).then(response => {
        //   const { data } = response
        commit('SET_ROLEINFO', response)
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
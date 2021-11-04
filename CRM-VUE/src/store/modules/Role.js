import { listRoleNameInfo,queryAllRoleInfo } from '../../api/Role'

const state = {
    roleNameInfo:{},
    roleInfo:{}
}


const mutations = {
    SET_ROLENAMEINFO:(state,roleNameInfo) => {
        state.roleNameInfo = roleNameInfo
    },
    SET_ROLEINFO:(state,roleInfo) => {
      state.roleInfo = roleInfo
  },
}


const actions = {
    listRoleNameInfo({commit},param){
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
    queryAllRoleInfo({commit},param){
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
}


export default {
    namespaced: true,
    state,
    mutations,
    actions
  }
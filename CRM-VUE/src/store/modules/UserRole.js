import { add, update,queryRoleId } from '../../api/UserRole'

const state = {
    addInfo: {},
    updateInfo: {},
    roleIdInfo:{}
}


const mutations = {
    SET_ADDINFO: (state, addInfo) => {
        state.addInfo = addInfo
    },
    SET_UPDATEINFO: (state, updateInfo) => {
        state.updateInfo = updateInfo
    },
    SET_ROLEIDINFO:(state,roleIdInfo) => {
        state.roleIdInfo = roleIdInfo
    },
}


const actions = {
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
    queryRoleId({commit},param){
        return new Promise((resolve, reject) => {
          queryRoleId(param).then(response => {
            //   const { data } = response
              commit('SET_ROLEIDINFO', response)
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
import { queryAllCusLinkmanInfo, getCurrentId, update, add, del } from '../../api/CusLinkman'

const state = {
    cusLinkmanInfo: {},
    currentIdInfo: {},
    deleteInfo: {},
    updateInfo: {},
    addInfo: {},
}


const mutations = {
    SET_CUSLINKMANINFO: (state, cusLinkmanInfo) => {
        state.cusLinkmanInfo = cusLinkmanInfo
    },
    SET_CURRENTIDINFO: (state, currentIdInfo) => {
        state.currentIdInfo = currentIdInfo
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
}


const actions = {
    queryAllCusLinkmanInfo({ commit }, param) {
        return new Promise((resolve, reject) => {
            queryAllCusLinkmanInfo(param).then(response => {
                //   const { data } = response
                commit('SET_CUSLINKMANINFO', response)
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
}


export default {
    namespaced: true,
    state,
    mutations,
    actions
}
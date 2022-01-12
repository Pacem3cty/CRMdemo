import { queryCusConsistInfo,queryCusCsrInfo,queryCusCcrInfo } from '../../api/CusConsist'

const state = {
    cusConsistInfo: {},
    cusCsrInfo:{},
    cusCcrInfo:{}
}

const mutations = {
    SET_CUSCONSISTINFO: (state, cusConsistInfo) => {
        state.cusConsistInfo = cusConsistInfo
    },
    SET_CUSCSRINFO: (state, cusCsrInfo) => {
        state.cusCsrInfo = cusCsrInfo
    },
    SET_CUSCCRINFO: (state, cusCcrInfo) => {
        state.cusCcrInfo = cusCcrInfo
    }
}


const actions = {
    queryCusConsistInfo({ commit }, param) {
        return new Promise((resolve, reject) => {
            queryCusConsistInfo(param).then(response => {
                //   const { data } = response
                commit('SET_CUSCONSISTINFO', response)
                resolve()
            }).catch(error => {
                reject(error)
            })
        })
    },
    queryCusCsrInfo({ commit }, param) {
        return new Promise((resolve, reject) => {
            queryCusCsrInfo(param).then(response => {
                //   const { data } = response
                commit('SET_CUSCSRINFO', response)
                resolve()
            }).catch(error => {
                reject(error)
            })
        })
    },
    queryCusCcrInfo({ commit }, param) {
        return new Promise((resolve, reject) => {
            queryCusCcrInfo(param).then(response => {
                //   const { data } = response
                commit('SET_CUSCCRINFO', response)
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
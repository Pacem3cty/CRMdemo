import { queryAllCusCTRBInfo,queryOrderStatsInfo,queryTotalStatsInfo,queryAovStatsInfo } from '../../api/CusCTRB'

const state = {
    cusCTRBInfo: {},
    orderStatsInfo:{},
    totalStatsInfo:{},
    aovStatsInfo:{}
    // currentIdInfo: {},
    // deleteInfo: {},
    // updateInfo: {},
    // addInfo: {},
}


const mutations = {
    SET_CUSCTRBINFO: (state, cusCTRBInfo) => {
        state.cusCTRBInfo = cusCTRBInfo
    },
    SET_ORDERSTATSINFO:(state, orderStatsInfo) => {
        state.orderStatsInfo = orderStatsInfo
    },
    SET_TOTALSTATSINFO:(state, totalStatsInfo) => {
        state.totalStatsInfo = totalStatsInfo
    },
    SET_AOVSTATSINFO:(state, aovStatsInfo) => {
        state.aovStatsInfo = aovStatsInfo
    },
    // SET_CURRENTIDINFO: (state, currentIdInfo) => {
    //     state.currentIdInfo = currentIdInfo
    // },
    // SET_ADDINFO: (state, addInfo) => {
    //     state.addInfo = addInfo
    // },
    // SET_DELETEINFO: (state, deleteInfo) => {
    //     state.deleteInfo = deleteInfo
    // },
    // SET_UPDATEINFO: (state, updateInfo) => {
    //     state.updateInfo = updateInfo
    // },
}


const actions = {
    queryAllCusCTRBInfo({ commit }, param) {
        return new Promise((resolve, reject) => {
            queryAllCusCTRBInfo(param).then(response => {
                //   const { data } = response
                commit('SET_CUSCTRBINFO', response)
                resolve()
            }).catch(error => {
                reject(error)
            })
        })
    },
    queryOrderStatsInfo({ commit }, param) {
        return new Promise((resolve, reject) => {
            queryOrderStatsInfo(param).then(response => {
                //   const { data } = response
                commit('SET_ORDERSTATSINFO', response)
                resolve()
            }).catch(error => {
                reject(error)
            })
        })
    },
    queryTotalStatsInfo({ commit }, param) {
        return new Promise((resolve, reject) => {
            queryTotalStatsInfo(param).then(response => {
                //   const { data } = response
                commit('SET_TOTALSTATSINFO', response)
                resolve()
            }).catch(error => {
                reject(error)
            })
        })
    },
    queryAovStatsInfo({ commit }, param) {
        return new Promise((resolve, reject) => {
            queryAovStatsInfo(param).then(response => {
                //   const { data } = response
                commit('SET_AOVSTATSINFO', response)
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
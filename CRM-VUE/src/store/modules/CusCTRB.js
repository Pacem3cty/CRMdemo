import { queryAllCusCTRBInfo } from '../../api/CusCTRB'

const state = {
    cusCTRBInfo: {},
    // currentIdInfo: {},
    // deleteInfo: {},
    // updateInfo: {},
    // addInfo: {},
}


const mutations = {
    SET_CUSCTRBINFO: (state, cusCTRBInfo) => {
        state.cusCTRBInfo = cusCTRBInfo
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
}


export default {
    namespaced: true,
    state,
    mutations,
    actions
}
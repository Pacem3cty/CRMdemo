import { queryCusConsistInfo } from '../../api/CusConsist'

const state = {
    cusConsistInfo: {},
}

const mutations = {
    SET_CUSCONSISTINFO: (state, cusConsistInfo) => {
        state.cusConsistInfo = cusConsistInfo
    },
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
}


export default {
    namespaced: true,
    state,
    mutations,
    actions
}
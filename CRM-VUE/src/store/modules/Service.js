import { queryAll, del,getCurrentId,add,listCustomerName,update,updatePart,queryCsrInfo,queryServiceTypeInfo} from '../../api/Service'


const state = {
  serviceInfo: {},
  addInfo: {},
  updateInfo: {},
  delInfo: {},
  currentIdInfo:{},
  customerNameInfo:{},
  updatePartInfo:{},
  serviceTypeInfo:{},
  csrInfo:{}
}


const mutations = {
  SET_SERVICEINFO: (state, serviceInfo) => {
    state.serviceInfo = serviceInfo
  },
  SET_ADDINFO: (state, addInfo) => {
    state.addInfo = addInfo
  },
  SET_UPDATEINFO: (state, updateInfo) => {
    state.updateInfo = updateInfo
  },
  SET_DELINFO: (state, delInfo) => {
    state.delInfo = delInfo
  },
  SET_CURRENTIDINFO:(state, currentIdInfo) => {
    state.currentIdInfo = currentIdInfo
  },
  SET_CUSTOMERNAMEINFO:(state, customerNameInfo) => {
    state.customerNameInfo = customerNameInfo
  },
  SET_UPDATEPARTINFO: (state, updatePartInfo) => {
    state.updatePartInfo = updatePartInfo
  },
  SET_SERVICETYPEINFO: (state, serviceTypeInfo) => {
    state.serviceTypeInfo = serviceTypeInfo
  },
  SET_CSRINFO: (state, csrInfo) => {
    state.csrInfo = csrInfo
  },
}


const actions = {
  queryAll({ commit }, param) {
    return new Promise((resolve, reject) => {
      queryAll(param).then(response => {
        //   const { data } = response
        commit('SET_SERVICEINFO', response)
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
        commit('SET_DELINFO', response)
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
  updatePart({ commit }, param) {
    return new Promise((resolve, reject) => {
      updatePart(param).then(response => {
        //   const { data } = response
        commit('SET_UPDATEPARTINFO', response)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  listCustomerName({ commit }, param) {
    return new Promise((resolve, reject) => {
      listCustomerName(param).then(response => {
        //   const { data } = response
        commit('SET_CUSTOMERNAMEINFO', response)
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
  queryCsrInfo({ commit }, param) {
    return new Promise((resolve, reject) => {
      queryCsrInfo(param).then(response => {
        //   const { data } = response
        commit('SET_CSRINFO', response)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  queryServiceTypeInfo({ commit }, param) {
    return new Promise((resolve, reject) => {
      queryServiceTypeInfo(param).then(response => {
        //   const { data } = response
        commit('SET_SERVICETYPEINFO', response)
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
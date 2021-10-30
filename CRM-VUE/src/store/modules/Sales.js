import { queryAll, del,getCurrentId,add,listCreatePerson,listCustomerName,update,queryIsAssigned,queryCusDevPlan,cusDevPlanDel,getCusDevPlanCurrentId,addCusDevPlan,updateCusDevPlan} from '../../api/Sales'


const state = {
  salesInfo: {},
  addInfo: {},
  updateInfo: {},
  delInfo: {},
  currentIdInfo:{},
  customerNameInfo:{},
  createPersonInfo:{},
  assignedInfo:{},
  cusDevPlanInfo:{},
  cusDevPlanCurrentIdInfo:{},
  addCusDevPlanInfo: {},
  updateCusDevPlanInfo: {},
}


const mutations = {
  SET_SALESINFO: (state, salesInfo) => {
    state.salesInfo = salesInfo
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
  SET_CREATEPERSONINFO:(state, createPersonInfo) => {
    state.createPersonInfo = createPersonInfo
  },
  SET_ASSIGNEDINFO:(state, assignedInfo) => {
    state.assignedInfo = assignedInfo
  },
  SET_CUSDEVPLANINFO:(state, cusDevPlanInfo) => {
    state.cusDevPlanInfo = cusDevPlanInfo
  },
  SET_CUSDEVPLANCURRENTIDINFO:(state, cusDevPlanCurrentIdInfo) => {
    state.cusDevPlanCurrentIdInfo = cusDevPlanCurrentIdInfo
  },
  SET_ADDCUSDEVPLANINFO: (state, addCusDevPlanInfo) => {
    state.addCusDevPlanInfo = addCusDevPlanInfo
  },
  SET_UPDATECUSDEVPLANINFO: (state, updateCusDevPlanInfo) => {
    state.updateCusDevPlanInfo = updateCusDevPlanInfo
  },
}


const actions = {
  queryAll({ commit }, param) {
    return new Promise((resolve, reject) => {
      queryAll(param).then(response => {
        //   const { data } = response
        commit('SET_SALESINFO', response)
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
  listCreatePerson({ commit }, param) {
    return new Promise((resolve, reject) => {
      listCreatePerson(param).then(response => {
        //   const { data } = response
        commit('SET_CREATEPERSONINFO', response)
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
  queryIsAssigned({ commit }, param) {
    return new Promise((resolve, reject) => {
      queryIsAssigned(param).then(response => {
        //   const { data } = response
        commit('SET_ASSIGNEDINFO', response)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  queryCusDevPlan({ commit }, param) {
    return new Promise((resolve, reject) => {
      queryCusDevPlan(param).then(response => {
        //   const { data } = response
        commit('SET_CUSDEVPLANINFO', response)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  cusDevPlanDel({ commit }, param) {
    return new Promise((resolve, reject) => {
      cusDevPlanDel(param).then(response => {
        //   const { data } = response
        commit('SET_DELINFO', response)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  getCusDevPlanCurrentId({ commit }, param) {
    return new Promise((resolve, reject) => {
      getCusDevPlanCurrentId(param).then(response => {
        //   const { data } = response
        commit('SET_CUSDEVPLANCURRENTIDINFO', response)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  addCusDevPlan({ commit }, param) {
    return new Promise((resolve, reject) => {
      addCusDevPlan(param).then(response => {
        //   const { data } = response
        commit('SET_ADDCUSDEVPLANINFO', response)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  updateCusDevPlan({ commit }, param) {
    return new Promise((resolve, reject) => {
      updateCusDevPlan(param).then(response => {
        //   const { data } = response
        commit('SET_UPDATECUSDEVPLANINFO', response)
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
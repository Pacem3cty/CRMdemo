import md5 from 'js-md5'
 
export default {
  install: function (Vue) {
    Object.defineProperty(Vue.prototype, '$md5', { value: md5 })
  }
}
// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import router from './router'

// element-ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

// 编辑器
import MavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

import {
  deleteRequest,
  postRequest,
  putRequest,
  getRequest,
  uploadRequest,
} from "./HttpRequest";


import App from './App'

Vue.config.productionTip = false

Vue.prototype.$put=putRequest
Vue.prototype.$del=deleteRequest
Vue.prototype.$post=postRequest
Vue.prototype.$get=getRequest
Vue.prototype.$filePost = uploadRequest


Vue.use(ElementUI);
Vue.use(MavonEditor)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})

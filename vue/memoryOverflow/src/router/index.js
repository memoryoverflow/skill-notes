import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/pages/Index.vue'
import Home from '@/pages/Home.vue'
import Introduction from '@/pages/Introduction.vue'
import Article from '@/pages/Article.vue'
import Write from '@/pages/Write.vue'
import Login from '@/pages/Login.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index,
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
    },
    {
      path: '/home',
      name: 'home',
      component: Home,
    },
    {
      path: '/:mao',
      name: 'home',
      component: Home,
    },
     {
      path: '/write/publish',
      name: 'Write',
      component: Write
    },
    {
      path: '/write/edit/:id',
      name: 'Write',
      component: Write
    }
  ]
})

import Vue from 'vue'
import VueRouter from 'vue-router'
import sensorPage from '../views/sensorPage.vue'
import envWatch from '../views/envWatch.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',component: envWatch
  },
  {
    path: '/sensorPage',component: sensorPage
  },
]

const router = new VueRouter({
  routes
})

export default router

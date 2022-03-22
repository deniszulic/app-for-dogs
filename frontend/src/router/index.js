import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import {Auth} from '@/services'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    children:[
      {
        path: 'preglednestalih/:id',
        props: true,
            name: 'dog-details',
        component: () => import('../views/detaljipsa.vue')
      }
    ]
  },
  {
    path: '/about',
    name: 'About',
    component: () => import('../views/About.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/login.vue')
  },
  {
    path: '/register',
    name: 'register',
    component: () => import('../views/register.vue')
  },
  {
    path: '/pocetnakorisnik',
    name: 'pocetnakorisnik',
    component: () => import('../views/pocetnakorisnik.vue')
  },
  {
    path: '/pocetnaazil',
    name: 'pocetnaazil',
    component: () => import('../views/pocetnaazil.vue')
  },
  {
    path: '/admin',
    name: 'admin',
    component: () => import('../views/admin.vue')
  },
  {
    path: '/nestanakpsa',
    name: 'nestanakpsa',
    component: () => import('../views/nestanakpsa.vue')
  },
  {
    path: '/preglednestalih',
    name: 'preglednestalih',
    component: () => import('../views/preglednestalih.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to,from,next)=>{
  const publicpages=["/login","/register","/"]
  const login_needed=publicpages.includes(to.path)
  const typeofusr=Auth.state.typeofuser;
  if(login_needed && typeofusr=="korisnik"){
    return next('/pocetnakorisnik')
  }
  if(login_needed && typeofusr=="azil"){
    return next('/pocetnaazil')
  }
  if(login_needed && typeofusr=="admin"){
    return next('/admin')
  }
  else next()
})

export default router

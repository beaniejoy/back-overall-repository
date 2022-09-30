import Vue from 'vue';
import VueRouter from 'vue-router';
const HomeView = () => import(/* webpackChunkName: "HomeView" */ '@/views/HomeView.vue');
const LoginView = () => import(/* webpackChunkName: "LoginView" */ '@/views/LoginView.vue');

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeView
  },
  {
    path: '/login',
    alias: ['/oauth'],
    name: 'Login',
    component: LoginView
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});

export default router;

import Vue from 'vue';
import App from './App.vue';
import store from './store';
import router from './router';
import vuetify from './plugins/vuetify';
import 'tailwindcss/tailwind.css';
import VueCookies from 'vue-cookies';

// vue-cookies default setting
Vue.use(VueCookies);
Vue.$cookies.config('3d');  // expire (3 days)

Vue.config.productionTip = false;

new Vue({
  store,
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app');

import Vue from 'vue';

function getCookie(key) {
  return Vue.$cookies.get(key);
}

function setCookie(key, value, expireTimes, path = '/') {
  Vue.$cookies.set(key, value, expireTimes, path);
}

export {
  getCookie, 
  setCookie
};
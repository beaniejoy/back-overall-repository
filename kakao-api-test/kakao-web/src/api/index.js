import axios from 'axios';

function setInterceptors(instance) {
  instance.interceptors.request.use(config => {
    console.log('axios request', config);
    return config;
  });

  instance.interceptors.response.use(response => {
    console.log('axios response', response);
    return response;
  }, error => {
    console.log('axios api error:', error);
    return Promise.reject(error);
  });

  return instance;
}

export function createAxiosInstance() {
  const instance = axios.create({
    timeout: 3000
  });

  return setInterceptors(instance);
}
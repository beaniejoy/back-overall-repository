import { createAxiosInstance } from '@/api';

export function requestKakaoTokenApi(requestData) {
  return createAxiosInstance().post('/api/kakao/oauth/token', requestData);
}
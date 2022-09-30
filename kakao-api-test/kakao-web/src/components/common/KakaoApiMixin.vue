<script>
import CommonFuncMixin from '@/components/common/CommonFuncMixin.vue';
import { requestKakaoTokenApi } from '@/api/kakao';

const Kakao = require('@/utils/kakao.min');

export default {
  mixins: [CommonFuncMixin],
  data() {
    return {
      redirectUri: `${window.location.protocol}//${window.location.host}/oauth`
    };
  },
  methods: {
    initKakaoSdk() {
      if (!Kakao.isInitialized()) {
        Kakao.init(process.env.VUE_APP_KAKAO_SDK_APP_KEY);
      }
    },
    authenticateKakaoUser() {
      Kakao.Auth.authorize({
        redirectUri: this.redirectUri
      });
    },
    getKakaoAccessToken() {
      return Kakao.Auth.getAccessToken();
    },
    setKakaoAccessToken(accessToken) {
      Kakao.Auth.setAccessToken(accessToken);
    },
    async requestKakaoToken(authCode) {
      const response = await requestKakaoTokenApi({
        grantType: 'authorization_code',
        clientId: process.env.VUE_APP_KAKAO_API_APP_KEY,
        redirectUri: this.redirectUri,
        code: authCode
      }).catch(error => {
        console.log('error:', error);
        throw new Error(this.defaultErrorMsg);
      });

      return response.data.access_token;
    }
  }
};
</script>
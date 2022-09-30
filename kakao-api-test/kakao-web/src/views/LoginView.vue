<template>
  <v-container class="mt-9 pa-0">
    <v-card
      class="mx-auto"
      :max-width="size"
      elevation="6"
    >
      <v-img
        contain
        class="white--text align-end"
        max-height="400"
        src="@/assets/img/health.jpg"
        eager
      >
        <v-card-title class="ml-3 mb-1">
          <span class="courgette card-title">Let's Health</span>
        </v-card-title>
      </v-img>
      <v-card-actions class="mt-5 px-10">
        <v-img
          class="mx-auto mb-5 cursor-pointer"
          :max-width="size * 0.5"
          src="@/assets/img/kakao-login.png"
          @click="authenticateKakaoUser"
        />
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
import CommonFuncMixin from '@/components/common/CommonFuncMixin.vue';
import KakaoApiMixin from '@/components/common/KakaoApiMixin';

export default {
  name: 'HomeView',
  mixins: [CommonFuncMixin, KakaoApiMixin],
  computed: {
    size() {
      switch (this.$vuetify.breakpoint.name) {
      case 'xs':
        return 330;
      case 'sm':
        return 400;
      case 'md':
        return 450;
      case 'lg':
      case 'xl':
        return 500;
      default:
        return 0;
      }
    }
  },
  async created() {
    const params = this.getQueryParams();
    console.log('params', params);

    const code = params.code;
    console.log('code', code);

    if (code) {
      console.log('authenticate Kakao!');
      const accessToken = await this.requestKakaoToken(code);
      console.log('accessToken', accessToken);
      this.setKakaoAccessToken(accessToken);

      this.$router.replace({ path: '/', query: {} });
      return;
    }
  },
};
</script>

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
          @click="login"
        />
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
export default {
  name: 'HomeView',
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
  created() {

    this.initKakaoSdk();
  },
  methods: {
    initKakaoSdk() {
      const Kakao = require('@/utils/kakao.min');

      if (!Kakao.isInitialized()) {
        Kakao.init('41c041072b9bd4a13ff26cde9a7394d0');
      }
    },
    login() {
      const Kakao = require('@/utils/kakao.min');
      Kakao.Auth.authorize({
        redirectUri: `${window.location.protocol}//${window.location.host}`
      });
    }
  }
};
</script>

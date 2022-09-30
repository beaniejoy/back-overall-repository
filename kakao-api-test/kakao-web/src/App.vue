<template>
  <v-app>
    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
import KakaoApiMixin from '@/components/common/KakaoApiMixin';

export default {
  name: 'App',
  mixins: [KakaoApiMixin],
  data() {
    return {};
  },
  created() {
    console.log('App.vue');
    this.initKakaoSdk();
  }
};
</script>

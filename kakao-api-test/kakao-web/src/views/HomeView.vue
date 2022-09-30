<template>
  <div>Home!</div>  
</template>

<script>
import KakaoApiMixin from '@/components/common/KakaoApiMixin';

export default {
  name: 'HomeView',
  mixins: [KakaoApiMixin],
  created() {
    this.checkAuthorizedKakaoUser();
  },
  methods: {
    checkAuthorizedKakaoUser() {
      if (!this.getKakaoAccessToken()) {
        this.$router.replace({ path: '/login', query: {} });
      }
    }
  }
};
</script>

<style>

</style>

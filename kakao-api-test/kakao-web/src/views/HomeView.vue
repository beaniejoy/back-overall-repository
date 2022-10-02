<template>
  <v-container>
    <KakaoSendMessageComp />
  </v-container>
</template>

<script>
import KakaoApiMixin from '@/components/common/KakaoApiMixin';
import KakaoSendMessageComp from '@/components/home/KakaoSendMessageComp';
  
export default {
  name: 'HomeView',
  components: {
    KakaoSendMessageComp
  },
  mixins: [KakaoApiMixin],
  created() {
    this.checkAuthorizedKakaoUser();
  },
  methods: {
    checkAuthorizedKakaoUser() {
      if (!this.isValidAccessToken()) {
        this.$router.replace({ path: '/login', query: {} });
      }
    }
  }
};
</script>

<style>

</style>

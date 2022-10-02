<template>
  <v-card 
    class="mx-auto" 
    max-width="344" 
    @click="sendKakaoMessageToMe"
  >
    <v-img src="@/assets/img/kakao-talk.png" height="200px" />

    <v-card-title>카카오톡 메시지 보내기</v-card-title>

    <v-card-subtitle>나에게 보내기</v-card-subtitle>
  </v-card>
</template>

<script>
export default {
  methods: {
    sendKakaoMessageToMe() {
      console.log('카카오톡 나에게 보내기');
    },
  },
};
</script>

<style></style>

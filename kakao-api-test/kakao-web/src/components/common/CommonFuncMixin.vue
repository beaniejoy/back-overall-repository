<script>
export default {
  data() {
    return {
      defaultErrorMsg: '일시적인 오류입니다.'
    };
  },
  methods: {
    getQueryParams() {
      const searchParams = new URLSearchParams(window.location.search);
      return Object.fromEntries(searchParams.entries()); // undefined return의 위험성은 없다.
    },
  }
};
</script>
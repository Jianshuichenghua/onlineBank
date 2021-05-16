<template>
  <div>
    <div class="userDrop">
      <span class="userDrop_text">{{ userName }} |</span>
      <a class="login-out" @click="_loginOut()"> Logout</a>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  computed: {
    ...mapGetters(["userName"]),
  },
  methods: {
    _loginOut() {
      this.$store.dispatch("user/loginOut");
    },
  },
};
</script>
<style scoped>
.userDrop_text,
.login-out {
  color: #fff;
}
.login-out:hover {
  background: transparent;
  color: #fff;
}
</style>

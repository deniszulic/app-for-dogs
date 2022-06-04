<template>
  <div v-if="store.tipkorisnika != null">
    <router-view />
  </div>
  <div v-else>
    <login />
  </div>
</template>

<script>
import store from "@/store.js";
import login from "@/views/login.vue";
export default {
  name: "Home",
  data() {
    return {
      store,
    };
  },
  components: {
    login,
  },
};
</script>

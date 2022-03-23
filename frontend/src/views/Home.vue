<template>
  <div v-if="store.tipkorisnika!=null">
    <!-- <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/> -->
    <router-view/>
  </div>
  <div v-else>
    <login />
  </div>
</template>

<script>
// @ is an alias to /src
//import HelloWorld from '@/components/HelloWorld.vue'
import store from "@/store.js";
import login from "@/views/login.vue";
export default {
  name: 'Home',
  // components: {
  //   HelloWorld
  // }
  data(){
    return{
      store
    }
  },
  components:{
    login
  }
}
</script>

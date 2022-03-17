<template>
  <div>
    <div class="bg-dark">
      <div class="container">
        <div class="row">
          <div class="col">
            <nav class="navbar navbar-expand-lg navbar-dark">
              <a class="navbar-brand" href="#"
                ><i class="fa-solid fa-paw fa-spin-hover"></i
              ></a>
              <button
                class="navbar-toggler"
                type="button"
                data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent"
                aria-expanded="false"
                aria-label="Toggle navigation"
              >
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
                  <li class="nav-item">
                    <!-- <a class="nav-link" href="#">Link</a> -->
                    <router-link to="login" class="nav-link" v-if="store.tipkorisnika==null">Prijava</router-link>
                  </li>
                  <li class="nav-item">
                    <router-link to="register" class="nav-link" v-if="store.tipkorisnika==null">Registracija</router-link>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" v-if="store.tipkorisnika!=null" @click.prevent="logout" href="#">Odjava</a>
                  </li>
                  <li class="nav-item">
                    <router-link to="nestanakpsa" class="nav-link" v-if="store.tipkorisnika!=null">Prijavi nestanak psa</router-link>
                  </li>
                </ul>
              </div>
            </nav>
          </div>
        </div>
      </div>
    </div>
    <div class="container">
      <router-view />
    </div>
  </div>
</template>

<style lang="scss">
// #app {
//   font-family: Avenir, Helvetica, Arial, sans-serif;
//   -webkit-font-smoothing: antialiased;
//   -moz-osx-font-smoothing: grayscale;
//   text-align: center;
//   color: #2c3e50;
// }

// #nav {
//   padding: 30px;

//   a {
//     font-weight: bold;
//     color: #2c3e50;

//     &.router-link-exact-active {
//       color: #42b983;
//     }
//   }
// }
 
  body {
background: url("../src/assets/background.png") no-repeat center center fixed;
-webkit-background-size: cover;
-moz-background-size: cover;
-o-background-size: cover;
background-size: cover;
}
</style>
<script>
import store from "@/store.js";
import { Auth } from "@/services";
export default {
  data(){
    return{
      store
    }
  },
  mounted() {
    $(".fa-spin-hover").hover(
      function () {
        $(this).addClass("fa-flip");
      },
      function () {
        $(this).removeClass("fa-flip");
      }
    );
  },
  methods:{
    logout() {
      Auth.logout();
      this.store.tipkorisnika = null;
      this.$router.push({ path: "login" });
    }
  }
};
</script>

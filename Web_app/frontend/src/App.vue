<template>
  <div>
    <div class="bg-dark">
      <div class="container">
        <div class="row">
          <div class="col">
            <nav class="navbar navbar-expand-lg navbar-dark">
              <router-link
                to="/preglednestalih"
                class="navbar-brand"
                v-if="store.tipkorisnika == 'korisnik'"
              >
                <i class="fa-solid fa-paw fa-spin-hover"></i
              ></router-link>
              <router-link
                to="/preglednestalih"
                class="navbar-brand"
                v-if="store.tipkorisnika == 'azil'"
              >
                <i class="fa-solid fa-paw fa-spin-hover"></i
              ></router-link>
              <router-link
                to="/preglednestalih"
                class="navbar-brand"
                v-if="store.tipkorisnika == 'admin'"
              >
                <i class="fa-solid fa-paw fa-spin-hover"></i
              ></router-link>
              <router-link
                to="/pocetna"
                class="navbar-brand"
                v-if="store.tipkorisnika == null"
              >
                <i class="fa-solid fa-paw fa-spin-hover"></i
              ></router-link>
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
                    <router-link
                      to="/login"
                      class="nav-link"
                      v-if="store.tipkorisnika == null"
                      ><i class="fa-solid fa-right-to-bracket"></i>
                      Prijava</router-link
                    >
                  </li>
                  <li class="nav-item">
                    <router-link
                      to="/register"
                      class="nav-link"
                      v-if="store.tipkorisnika == null"
                      ><i class="fa-solid fa-user-plus"></i>
                      Registracija</router-link
                    >
                  </li>
                  <li
                    class="nav-item dropdown"
                    v-if="store.tipkorisnika != null"
                  >
                    <a
                      class="nav-link dropdown-toggle"
                      href="#"
                      id="navbarDropdownMenuLink1"
                      data-toggle="dropdown"
                      aria-haspopup="true"
                      aria-expanded="false"
                    >
                      Oglasi
                    </a>
                    <div
                      class="dropdown-menu"
                      aria-labelledby="navbarDropdownMenuLink1"
                    >
                      <router-link to="/preglednestalih" class="dropdown-item"
                        >Nestali psi</router-link
                      >
                      <router-link to="/udomipsaoglasi" class="dropdown-item"
                        >Udomi psa</router-link
                      >
                    </div>
                  </li>
                  <li
                    class="nav-item dropdown"
                    v-if="store.tipkorisnika == 'korisnik'"
                  >
                    <a
                      class="nav-link dropdown-toggle"
                      href="#"
                      id="navbarDropdownMenuLink"
                      data-toggle="dropdown"
                      aria-haspopup="true"
                      aria-expanded="false"
                    >
                      Obrasci
                    </a>
                    <div
                      class="dropdown-menu"
                      aria-labelledby="navbarDropdownMenuLink"
                    >
                      <router-link to="/nestanakpsa" class="dropdown-item"
                        >Prijavi nestanak psa</router-link
                      >
                      <router-link to="/udomljavanjepsa" class="dropdown-item"
                        >Udomi psa</router-link
                      >
                    </div>
                  </li>
                  <li
                    class="nav-item dropdown"
                    v-if="store.tipkorisnika == 'azil'"
                  >
                    <a
                      class="nav-link dropdown-toggle"
                      href="#"
                      id="navbarDropdownMenuLink1"
                      data-toggle="dropdown"
                      aria-haspopup="true"
                      aria-expanded="false"
                    >
                      Obrasci
                    </a>
                    <div
                      class="dropdown-menu"
                      aria-labelledby="navbarDropdownMenuLink1"
                    >
                      <router-link to="/nestanakpsaazil" class="dropdown-item"
                        >Prijavi nestanak psa</router-link
                      >
                      <router-link
                        to="/udomljavanjepsaazil"
                        class="dropdown-item"
                        >Udomi psa</router-link
                      >
                    </div>
                  </li>
                  <li
                    class="nav-item dropdown"
                    v-if="store.tipkorisnika == 'azil'"
                  >
                    <a
                      class="nav-link dropdown-toggle"
                      href="#"
                      id="navbarDropdownMenuLink1"
                      data-toggle="dropdown"
                      aria-haspopup="true"
                      aria-expanded="false"
                    >
                      Zahtjevi korisnika
                    </a>
                    <div
                      class="dropdown-menu"
                      aria-labelledby="navbarDropdownMenuLink1"
                    >
                      <router-link to="/potvrdioglasazil" class="dropdown-item"
                        >Nestanak psa</router-link
                      >
                      <router-link
                        to="/potvrdioglaszaudomit"
                        class="dropdown-item"
                        >Udomi psa</router-link
                      >
                    </div>
                  </li>
                  <li
                    class="nav-item dropdown"
                    v-if="store.tipkorisnika == 'korisnik'"
                  >
                    <a
                      class="nav-link dropdown-toggle"
                      href="#"
                      id="navbarDropdownMenuLink2"
                      data-toggle="dropdown"
                      aria-haspopup="true"
                      aria-expanded="false"
                    >
                      Moji oglasi
                    </a>
                    <div
                      class="dropdown-menu"
                      aria-labelledby="navbarDropdownMenuLink2"
                    >
                      <router-link to="/mojioglasi" class="dropdown-item"
                        >Moji oglasi</router-link
                      >
                      <router-link to="/mojeprijave" class="dropdown-item"
                        >Moje prijave</router-link
                      >
                      <router-link to="/prijavenamojoglas" class="dropdown-item"
                        >Prijave na moj oglas</router-link
                      >
                    </div>
                  </li>
                  <li
                    class="nav-item dropdown"
                    v-if="store.tipkorisnika == 'azil'"
                  >
                    <a
                      class="nav-link dropdown-toggle"
                      href="#"
                      id="navbarDropdownMenuLink3"
                      data-toggle="dropdown"
                      aria-haspopup="true"
                      aria-expanded="false"
                    >
                      Moji oglasi
                    </a>
                    <div
                      class="dropdown-menu"
                      aria-labelledby="navbarDropdownMenuLink3"
                    >
                      <router-link to="/mojioglasiazil" class="dropdown-item"
                        >Moji oglasi</router-link
                      >
                      <router-link to="/mojeprijave" class="dropdown-item"
                        >Moje prijave</router-link
                      >
                      <router-link
                        to="/prijavenamojoglasazil"
                        class="dropdown-item"
                        >Prijave na moj oglas</router-link
                      >
                    </div>
                  </li>
                  <li
                    class="nav-item dropdown"
                    v-if="store.tipkorisnika == 'admin'"
                  >
                    <a
                      class="nav-link dropdown-toggle"
                      href="#"
                      id="navbarDropdownMenuLink4"
                      data-toggle="dropdown"
                      aria-haspopup="true"
                      aria-expanded="false"
                    >
                      Promjena podataka
                    </a>
                    <div
                      class="dropdown-menu"
                      aria-labelledby="navbarDropdownMenuLink4"
                    >
                      <router-link to="/nestalipsiadmin" class="dropdown-item"
                        >Nestali psi</router-link
                      >
                      <router-link to="/udomljenipsiadmin" class="dropdown-item"
                        >Udomljeni psi</router-link
                      >
                    </div>
                  </li>
                  <li
                    class="nav-item"
                    v-if="
                      store.tipkorisnika != 'admin' &&
                      store.tipkorisnika != null
                    "
                  >
                    <router-link to="/profil" class="nav-item nav-link"
                      >Profil</router-link
                    >
                  </li>
                  <li class="nav-item" v-if="store.tipkorisnika == 'admin'">
                    <router-link to="/adminprofil" class="nav-item nav-link"
                      >Profil</router-link
                    >
                  </li>
                  <li class="nav-item">
                    <a
                      class="nav-link"
                      v-if="store.tipkorisnika != null"
                      @click.prevent="logout"
                      href="#"
                      ><i class="fa-solid fa-right-to-bracket"></i> Odjava</a
                    >
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
body {
  background: url("../src/assets/background.png") no-repeat center center fixed;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}
@import url("https://fonts.googleapis.com/css2?family=Baloo+2&display=swap");
.light {
  background: #f3f5f7;
}
a,
a:hover {
  text-decoration: none;
  transition: color 0.3s ease-in-out;
}
.postcard {
  flex-wrap: wrap;
  display: flex;
  box-shadow: 0 4px 21px -12px rgba(0, 0, 0, 0.66);
  border-radius: 10px;
  margin: 0 0 2rem 0;
  overflow: hidden;
  position: relative;
  color: #fff;
  width: 1200px;
}
.postcard.dark {
  background-color: #18151f;
}
.postcard.light {
  background-color: #e1e5ea;
}
.postcard .t-dark {
  color: #18151f;
}
.postcard a {
  color: inherit;
}
.postcard h1,
.postcard .h1 {
  margin-bottom: 0.5rem;
  font-weight: 500;
  line-height: 1.2;
}
.postcard .small {
  font-size: 80%;
}
.postcard .postcard__title {
  font-size: 1.75rem;
}
.postcard .postcard__img {
  max-height: 180px;
  width: 100%;
  object-fit: cover;
  position: relative;
}
.postcard .postcard__img_link {
  display: contents;
}
.postcard .postcard__bar {
  width: 50px;
  height: 10px;
  margin: 10px 0;
  border-radius: 5px;
  background-color: #424242;
  transition: width 0.2s ease;
}
.postcard .postcard__text {
  padding: 1.5rem;
  position: relative;
  display: flex;
  flex-direction: column;
}
.postcard .postcard__preview-txt {
  overflow: hidden;
  text-overflow: ellipsis;
  text-align: justify;
  height: 100%;
}
.postcard .postcard__tagbox {
  display: flex;
  flex-flow: row wrap;
  font-size: 14px;
  margin: 20px 0 0 0;
  padding: 0;
  justify-content: center;
}
.postcard .postcard__tagbox .tag__item {
  display: inline-block;
  background: rgba(83, 83, 83, 0.4);
  border-radius: 3px;
  padding: 2.5px 10px;
  margin: 0 5px 5px 0;
  cursor: default;
  user-select: none;
  transition: background-color 0.3s;
}
.postcard .postcard__tagbox .tag__item:hover {
  background: rgba(83, 83, 83, 0.8);
}
.postcard:before {
  content: "";
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background-image: linear-gradient(-70deg, #424242, transparent 50%);
  opacity: 1;
  border-radius: 10px;
}
.postcard:hover .postcard__bar {
  width: 100px;
}
@media screen and (min-width: 769px) {
  .postcard {
    flex-wrap: inherit;
  }
  .postcard .postcard__title {
    font-size: 2rem;
  }
  .postcard .postcard__tagbox {
    justify-content: start;
  }
  .postcard .postcard__img {
    max-width: 300px;
    max-height: 580px;
    transition: transform 0.3s ease;
  }
  .postcard .postcard__text {
    padding: 3rem;
    width: 100%;
  }
  .postcard .media.postcard__text:before {
    content: "";
    position: absolute;
    display: block;
    background: #18151f;
    top: -20%;
    height: 130%;
    width: 55px;
  }
  .postcard:hover .postcard__img {
    transform: scale(1.1);
  }
  .postcard:nth-child(2n + 1) {
    flex-direction: row;
  }
  .postcard:nth-child(2n + 0) {
    flex-direction: row-reverse;
  }
  .postcard:nth-child(2n + 1) .postcard__text::before {
    left: -12px !important;
    transform: rotate(4deg);
  }
  .postcard:nth-child(2n + 0) .postcard__text::before {
    right: -12px !important;
    transform: rotate(-4deg);
  }
}
@media screen and (min-width: 1024px) {
  .postcard__text {
    padding: 2rem 3.5rem;
  }
  .postcard__text:before {
    content: "";
    position: absolute;
    display: block;
    top: -20%;
    height: 130%;
    width: 48px;
  }
  .postcard.dark .postcard__text:before {
    background: #18151f;
  }
  .postcard.light .postcard__text:before {
    background: #e1e5ea;
  }
}
.postcard .postcard__tagbox .blue.play:hover {
  background: #0076bd;
}
.blue .postcard__title:hover {
  color: #0076bd;
}
.blue .postcard__bar {
  background-color: #0076bd;
}
.blue::before {
  background-image: linear-gradient(
    -30deg,
    rgba(0, 118, 189, 0.1),
    transparent 50%
  );
}
.blue:nth-child(2n)::before {
  background-image: linear-gradient(
    30deg,
    rgba(0, 118, 189, 0.1),
    transparent 50%
  );
}
@media screen and (min-width: 769px) {
  .blue::before {
    background-image: linear-gradient(
      -80deg,
      rgba(0, 118, 189, 0.1),
      transparent 50%
    );
  }
  .blue:nth-child(2n)::before {
    background-image: linear-gradient(
      80deg,
      rgba(0, 118, 189, 0.1),
      transparent 50%
    );
  }
}
</style>
<script>
import store from "@/store.js";
import { Auth } from "@/services";
export default {
  data() {
    return {
      store,
    };
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
  methods: {
    logout() {
      Auth.logout();
      this.store.tipkorisnika = null;
      this.$router.push({ name: "login" });
    },
  },
};
</script>

<template>
  <div>
    <div
      class="d-flex justify-content-center"
      style="margin-top: 10px; margin-bottom: -32px"
    >
      <article class="postcard light blue">
        <a
          class="postcard__img_link"
          v-if="data.url_slike != null"
          href="javascript:void(0)"
        >
          <img
            class="postcard__img"
            :src="data.url_slike"
            alt="Image Title"
            @click="$emit('change', data.url_slike)"
          />
        </a>
        <div class="postcard__text t-dark">
          <h1 class="postcard__title blue">Naziv azila: {{ data.naziv }}</h1>
          <div class="postcard__subtitle small">
            <time>
              <i class="fas fa-calendar-alt mr-2"></i>Postavljeno:
              {{ moment(parseInt(data.postavljeno)).format("DD.MM.YYYY.") }}
            </time>
          </div>
          <div class="postcard__bar"></div>
          <div class="postcard__preview-txt">
            <div class="container">
              <div class="row d-flex justify-content-center">
                <h5>Podaci o azilu</h5>
              </div>
              <div class="row">
                <div class="col-sm">
                  <p>Grad: {{ data.grad }}</p>
                </div>
                <div class="col-sm">
                  <p>Ulica: {{ data.ulica }}</p>
                </div>
                <div class="col-sm">
                  <p>Kućni br.: {{ data.kucnibr }}</p>
                </div>
              </div>
              <div class="row">
                <div class="col-sm">
                  <p>Poštanski br.: {{ data.postanski_broj }}</p>
                </div>
              </div>
              <hr />
              <div class="row d-flex justify-content-center">
                <h5>Podaci o oglasu</h5>
              </div>
              <div class="row">
                <div class="col-sm">
                  <p>
                    <b>Ime psa: {{ data.ime_psa }}</b>
                  </p>
                </div>
              </div>
              <div class="row">
                <div class="col-sm">
                  <p>Telefonski broj: {{ data.telefonskibr }}</p>
                </div>
                <div class="col-sm">
                  <p>Boja psa: {{ data.boja }}</p>
                </div>
                <div class="col-sm">
                  <p>Starost: {{ data.starost }}</p>
                </div>
              </div>
              <div class="row">
                <div class="col-sm">
                  <p>Dlaka: {{ data.dlaka }}</p>
                </div>
                <div class="col-sm">
                  <p>Spol: {{ data.spol }}</p>
                </div>
                <div class="col-sm">
                  <p>Pasmina: {{ data.pasmina }}</p>
                </div>
              </div>
              <div class="row">
                <div class="col-sm">
                  <p>Kastrat: {{ data.kastrat }}</p>
                </div>
                <div class="col-sm">
                  <p>Kilaža: {{ data.kilaza }}</p>
                </div>
                <div class="col-sm">
                  <p>Opasnost: {{ data.opasnost }}</p>
                </div>
              </div>
              <div class="row">
                <div class="col-sm">
                  <p>Napomena: {{ data.napomena }}</p>
                </div>
              </div>
            </div>
          </div>
          <div
            class="d-flex justify-content-center"
            v-if="email_prijavljen != data.email"
          >
            <button
              class="btn btn-primary"
              @click="$emit('alldata', data)"
              v-if="store.tipkorisnika != 'admin'"
            >
              Udomi psa
            </button>
          </div>
        </div>
      </article>
    </div>
  </div>
</template>
<script>
import moment from "moment";
import { dog_data } from "@/services";
import { Auth } from "@/services";
import store from "../store.js";
export default {
  props: ["data"],
  data() {
    return {
      moment,
      url: "",
      komentar: "",
      id: "",
      email_prijavljen: Auth.state.email,
      store,
    };
  },
  mounted() {
    moment.locale("hr");
  },
  created() {},
  watch: {},
  methods: {},
};
</script>
<style scoped>
a:hover {
  color: blue;
}
.back {
  background: rgba(83, 83, 83, 0.4);
}
</style>
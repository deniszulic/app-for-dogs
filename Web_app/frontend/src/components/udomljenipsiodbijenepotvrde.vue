<template>
  <div>
    <div
      class="d-flex justify-content-center"
      style="margin-top: 10px; margin-bottom: -32px"
    >
      <article class="postcard light blue" v-if="showcomments == null">
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
          <h1 class="postcard__title blue">Ime psa: {{ data.ime_psa }}</h1>
          <div class="postcard__subtitle small">
            <time>
              <i class="fas fa-calendar-alt mr-2"></i>Dostavljen zahtjev:
              {{
                moment(parseInt(data.azil_udomljavanje_postavljeno)).format(
                  "DD.MM.YYYY."
                )
              }}
            </time>
          </div>
          <div class="postcard__bar"></div>
          <div class="postcard__preview-txt">
            <div class="container">
              <div class="row">
                <div class="col-sm">
                  <p>Ime vlasnika: {{ data.ime }}</p>
                </div>
                <div class="col-sm">
                  <p>Prezime vlasnika: {{ data.prezime }}</p>
                </div>
                <div class="col-sm">
                  <p>Adresa vlasnika: {{ data.adresa }}</p>
                </div>
              </div>
              <div class="row">
                <div class="col-sm">
                  <p>Telefonski broj: {{ data.telefonskibr }}</p>
                </div>
                <div class="col-sm">
                  <p>Grad: {{ data.grad }}</p>
                </div>
                <div class="col-sm">
                  <p>Poštanski broj: {{ data.postanski_broj }}</p>
                </div>
              </div>
              <div class="row">
                <div class="col-sm">
                  <p>Boja psa: {{ data.boja }}</p>
                </div>
                <div class="col-sm">
                  <p>Starost: {{ data.starost }}</p>
                </div>
                <div class="col-sm">
                  <p>Dlaka: {{ data.dlaka }}</p>
                </div>
              </div>
              <div class="row">
                <div class="col-sm">
                  <p>Vet. lokacija: {{ data.vet_lokacija }}</p>
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
                  <p>Kilaža: {{ data.kilaza }}</p>
                </div>
                <div class="col-sm">
                  <p>Kastrat: {{ data.kastrat }}</p>
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
          <div class="d-flex justify-content-center">
            <button class="btn btn-warning" @click="$emit('getid', data)">
              Potvrda oglasa
            </button>
          </div>
        </div>
      </article>
      <div
        class="modal fade"
        id="pictureModal1"
        tabindex="-1"
        role="dialog"
        aria-labelledby="pictureModalLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content" style="display: inline-block">
            <div class="modal-header">
              <button
                type="button"
                class="close"
                data-dismiss="modal"
                aria-label="Close"
              >
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <img class="modal-content" :src="url" />
            <div class="modal-footer">
              <button
                type="button"
                class="btn btn-secondary"
                data-dismiss="modal"
              >
                Zatvori
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import moment from "moment";
import { dog_data } from "@/services";
import { Auth } from "@/services";
export default {
  props: ["data", "showcomments", "com"],
  data() {
    return {
      moment,
      url: "",
      komentar: "",
      email: Auth.state.email,
    };
  },
  mounted() {
    moment.locale("hr");
  },
  methods: {
    openimg(data) {
      this.url = data;
      $("#pictureModal1").modal("show");
    },
  },
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
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
          <h1 class="postcard__title blue">Ime psa: {{ data.ime_psa }}</h1>
          <div class="postcard__subtitle small">
            <time>
              <i class="fas fa-calendar-alt mr-2"></i>Postavljeno:
              {{ moment(parseInt(data.postavljeno)).format("DD.MM.YYYY.") }}
            </time>
          </div>
          <div class="postcard__bar"></div>
          <div class="postcard__preview-txt">
            <div class="container">
              <div class="row">
                <div class="col-sm">
                  <p>Telefonski broj: {{ data.telefonskibr }}</p>
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
                  <p>Kilaža: {{ data.kilaza }}</p>
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
                  <p>Napomena: {{ data.napomena }}</p>
                </div>
              </div>
            </div>
          </div>
          <div class="d-flex justify-content-center">
            <button
              class="btn btn-warning"
              @click="$emit('changedata_adoptdog', data)"
              style="margin-right: 10px"
            >
              Promjena podataka
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
export default {
  props: ["data"],
  data() {
    return {
      moment,
      url: "",
      komentar: "",
      id: "",
      email_prijavljen: Auth.state.email,
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
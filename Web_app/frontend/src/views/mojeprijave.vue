<template>
  <div>
    <div class="d-flex justify-content-center" style="margin-top: 10px">
      <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
        <li class="nav-item">
          <a
            class="nav-link active"
            id="pills-home-tab"
            data-toggle="pill"
            href="#pills-home"
            role="tab"
            aria-controls="pills-home"
            aria-selected="true"
            @click="refreshmissingdog"
            >Nestali psi</a
          >
        </li>
        <li class="nav-item">
          <a
            class="nav-link"
            id="pills-profile-tab"
            data-toggle="pill"
            href="#pills-profile"
            role="tab"
            aria-controls="pills-profile"
            aria-selected="false"
            @click="refreshadopteddog"
            >Udomljavanje psa</a
          >
        </li>
      </ul>
    </div>
    <div class="tab-content" id="pills-tabContent">
      <div
        class="tab-pane fade show active"
        id="pills-home"
        role="tabpanel"
        aria-labelledby="pills-home-tab"
      >
        <mojeprijavenestalipsi
          :data="getmyreportdisapp"
          @myreportmissingdog="myreportmissingdog"
        />
      </div>
      <div
        class="tab-pane fade"
        id="pills-profile"
        role="tabpanel"
        aria-labelledby="pills-profile-tab"
      >
        <mojeprijaveudomljenipsi
          :data="getmyreportadoptdog"
          @myreports_adopteddogs="adopted_dogs_report"
        />
      </div>
    </div>

    <div class="modal fade" id="reportadopteddog" tabindex="-1" role="dialog">
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Id: {{ id }}</h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div class="form-group">
              <h5 class="modal-title">Podaci oglasa</h5>
            </div>
            <div class="form-group" v-if="oglas_azila != true">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Ime</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="ime_udomljavanje"
                  disabled
                />
              </div>
            </div>
            <div class="form-group" v-if="oglas_azila != true">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Prezime</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="prezime_udomljavanje"
                  disabled
                />
              </div>
            </div>
            <div class="form-group" v-if="oglas_azila != true">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Adresa</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="adresa_udomljavanje"
                  disabled
                />
              </div>
            </div>
            <div class="form-group" v-if="oglas_azila == true">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Naziv azila</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="naziv_azila"
                  disabled
                />
              </div>
            </div>
            <div class="form-group" v-if="oglas_azila == true">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Grad azila</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="grad_azila"
                  disabled
                />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Telefonski broj</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="telefonskibr"
                  disabled
                />
              </div>
            </div>
            <div class="form-group" v-if="oglas_azila != true">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Grad</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="grad_udomljavanje"
                  disabled
                />
              </div>
            </div>
            <div class="form-group" v-if="oglas_azila != true">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Poštanski broj</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="postanski_broj_udomljavanje"
                  disabled
                />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Boja</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="boja"
                  disabled
                />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Starost</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="starost"
                  disabled
                />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Dlaka</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="dlaka"
                  disabled
                />
              </div>
            </div>
            <div class="form-group" v-if="oglas_azila != true">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Vet. lokacija</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="vet_lokacija"
                  disabled
                />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Ime psa</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="ime_psa"
                  disabled
                />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Spol</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="spol"
                  disabled
                />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Pasmina</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="pasmina"
                  disabled
                />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Kilaža</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="kilaza"
                  disabled
                />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Kastrat</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="kastrat"
                  disabled
                />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Opasnost</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="opasnost"
                  disabled
                />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Napomena</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="napomena_udomljavanje"
                  disabled
                />
              </div>
            </div>
            <template v-if="url_slike != null">
              <img class="modal-content" :src="url_slike" />
            </template>
            <hr />

            <div class="form-group">
              <h5 class="modal-title">Podaci prijave</h5>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Ime</span>
                </div>
                <input type="text" class="form-control" v-model="ime" />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Prezime</span>
                </div>
                <input type="text" class="form-control" v-model="prezime" />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Adresa</span>
                </div>
                <input type="text" class="form-control" v-model="adresa" />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Grad</span>
                </div>
                <input type="text" class="form-control" v-model="grad" />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Poštanski broj</span>
                </div>
                <input
                  type="number"
                  class="form-control"
                  v-model="postanski_broj"
                />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Kontakt</span>
                </div>
                <input type="text" class="form-control" v-model="kontakt" />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text">Razlog prijave</span>
                </div>
                <textarea
                  class="form-control"
                  aria-label="With textarea"
                  v-model="razlog_prijave"
                ></textarea>
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Postavljeno</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="postavljeno"
                  disabled
                />
              </div>
            </div>
            <hr />
            <div class="form-group">
              <h5 class="modal-title">Prihvaćena prijava?</h5>
            </div>
            <div class="form-check">
              <input
                class="form-check-input"
                type="radio"
                name="flexRadioDefault"
                id="flexRadioDefault1"
                value="True"
                v-model="prihvaceno"
                disabled
              />
              <label class="form-check-label" for="flexRadioDefault1">
                <span class="badge badge-pill badge-success">Prihvaćeno</span>
              </label>
            </div>
            <div class="form-check">
              <input
                class="form-check-input"
                type="radio"
                name="flexRadioDefault"
                id="flexRadioDefault2"
                value="False"
                v-model="prihvaceno"
                disabled
              />
              <label class="form-check-label" for="flexRadioDefault2">
                <span class="badge badge-pill badge-danger">Odbijeno</span>
              </label>
            </div>
            <div class="form-check">
              <input
                class="form-check-input"
                type="radio"
                name="flexRadioDefault"
                id="flexRadioDefault2"
                value="Obrada"
                v-model="prihvaceno"
                disabled
              />
              <label class="form-check-label" for="flexRadioDefault2">
                <span class="badge badge-pill badge-warning">U razradi</span>
              </label>
            </div>
            <div class="form-group" style="margin-top: 10px">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text">Razlog</span>
                </div>
                <textarea
                  class="form-control"
                  aria-label="With textarea"
                  v-model="razlog"
                  disabled
                ></textarea>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-dismiss="modal"
              @click="close"
            >
              Zatvori
            </button>
            <button
              type="button"
              class="btn btn-primary"
              @click="update_adopteddogs"
            >
              Spremi
            </button>
          </div>
        </div>
      </div>
    </div>
    <div
      class="modal fade"
      id="updatemissingdogreport"
      tabindex="-1"
      role="dialog"
    >
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Id: {{ id }}</h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div class="form-group">
              <h5 class="modal-title">Podaci oglasa</h5>
            </div>

            <div class="form-group" v-if="oglas_azila != true">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Ime</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="ime_nestanak"
                  disabled
                />
              </div>
            </div>
            <div class="form-group" v-if="oglas_azila != true">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Prezime</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="prezime_nestanak"
                  disabled
                />
              </div>
            </div>
            <div class="form-group" v-if="oglas_azila != true">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Adresa</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="adresa"
                  disabled
                />
              </div>
            </div>
            <div class="form-group" v-if="oglas_azila == true">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Naziv azila</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="naziv_azila"
                  disabled
                />
              </div>
            </div>
            <div class="form-group" v-if="oglas_azila == true">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Grad azila</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="grad_azila"
                  disabled
                />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Telefonski broj</span>
                </div>
                <input
                  type="number"
                  class="form-control"
                  v-model="telefonskibr"
                  disabled
                />
              </div>
            </div>
            <div class="form-group" v-if="oglas_azila != true">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Grad</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="grad"
                  disabled
                />
              </div>
            </div>
            <div class="form-group" v-if="oglas_azila != true">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Poštanski broj</span>
                </div>
                <input
                  type="number"
                  class="form-control"
                  v-model="postanski_broj"
                  disabled
                />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Boja</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="boja"
                  disabled
                />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Starost</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="starost"
                  disabled
                />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Dlaka</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="dlaka"
                  disabled
                />
              </div>
            </div>
            <div class="form-group" v-if="oglas_azila != true">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Vet. lokacija</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="vet_lokacija"
                  disabled
                />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Ime psa</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="ime_psa"
                  disabled
                />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Spol</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="spol"
                  disabled
                />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Pasmina</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="pasmina"
                  disabled
                />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id=""
                    >Datum izgubljenog psa</span
                  >
                </div>
                <input
                  type="date"
                  class="form-control"
                  v-model="datum_izgubljen"
                  disabled
                />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text">Napomena</span>
                </div>
                <textarea
                  class="form-control"
                  aria-label="With textarea"
                  v-model="napomena_nestanak"
                  disabled
                ></textarea>
              </div>
            </div>
            <template v-if="url_slike != null">
              <img class="modal-content" :src="url_slike" />
            </template>

            <hr />
            <div class="form-group">
              <h5 class="modal-title">Podaci prijave</h5>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Ime</span>
                </div>
                <input type="text" class="form-control" v-model="ime" />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Prezime</span>
                </div>
                <input type="text" class="form-control" v-model="prezime" />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Kontakt</span>
                </div>
                <input type="text" class="form-control" v-model="kontakt" />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id=""
                    >Adresa za preuzimanje psa</span
                  >
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="adresa_za_preuzimanje_psa"
                />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Adresa pronalska</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="adresa_pronalaska"
                />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text">Napomena</span>
                </div>
                <textarea
                  class="form-control"
                  aria-label="With textarea"
                  v-model="napomena"
                ></textarea>
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Postavljeno</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="postavljeno"
                  disabled
                />
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-dismiss="modal"
            >
              Zatvori
            </button>
            <button
              type="button"
              class="btn btn-primary"
              @click="updatemissingdog"
            >
              Spremi
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import mojeprijavenestalipsi from "@/components/mojeprijavenestalipsi.vue";
import mojeprijaveudomljenipsi from "@/components/mojeprijaveudomljenipsi.vue";
import { Auth } from "@/services";
import { dog_data } from "@/services";
import moment from "moment";
export default {
  name: "mojeprijave",
  components: {
    mojeprijavenestalipsi,
    mojeprijaveudomljenipsi,
  },
  data() {
    return {
      getmyreportdisapp: [],
      getmyreportadoptdog: [],
      Auth,
      razlog_prijave: "",
      ime: "",
      prezime: "",
      adresa: "",
      grad: "",
      kontakt: "",
      postanski_broj: "",
      postavljeno: "",
      prihvaceno: "",
      id: "",
      razlog: "",
      moment,
      ime_udomljavanje: "",
      prezime_udomljavanje: "",
      adresa_udomljavanje: "",
      telefonskibr: "",
      grad_udomljavanje: "",
      postanski_broj_udomljavanje: "",
      boja: "",
      starost: "",
      dlaka: "",
      vet_lokacija: "",
      ime_psa: "",
      spol: "",
      pasmina: "",
      kilaza: "",
      kastrat: "",
      opasnost: "",
      napomena_udomljavanje: "",
      url_slike: "",
      adresa_pronalaska: "",
      adresa_za_preuzimanje_psa: "",
      napomena: "",
      ime_nestanak: "",
      prezime_nestanak: "",
      datum_izgubljen: "",
      napomena_nestanak: "",
      oglas_azila: null,
      naziv_azila: "",
      grad_azila: "",
    };
  },
  created() {
    this.getdata();
    this.getmyadopteddog();
  },
  watch: {
    $route: "getdata",
    $route: "getmyadopteddog",
  },
  methods: {
    async getdata() {
      try {
        this.getmyreportdisapp = await dog_data.getmyreportdisapp(
          Auth.state.email
        );
      } catch (e) {
        console.log(e);
      }
    },
    async getmyadopteddog() {
      try {
        this.getmyreportadoptdog = await dog_data.getmyreportadoptdog(
          Auth.state.email
        );
      } catch (e) {
        console.log(e);
      }
    },
    adopted_dogs_report(event) {
      this.id = event.id;
      this.ime = event.ime;
      this.prezime = event.prezime;
      this.adresa = event.adresa;
      this.grad = event.grad;
      this.kontakt = event.kontakt;
      this.postanski_broj = event.postanski_broj;
      this.prihvaceno = event.prihvaceno;
      this.razlog_prijave = event.razlog_prijave;
      this.razlog = event.napomena;
      this.postavljeno = this.moment(parseInt(event.postavljeno)).format(
        "DD.MM.YYYY."
      );
      this.ime_udomljavanje = event.ime_udomljavanje;
      this.prezime_udomljavanje = event.prezime_udomljavanje;
      this.adresa_udomljavanje = event.adresa_udomljavanje;
      this.telefonskibr = event.telefonskibr;
      this.grad_udomljavanje = event.grad_udomljavanje;
      this.postanski_broj_udomljavanje = event.postanski_broj_udomljavanje;
      this.boja = event.boja;
      this.starost = event.starost;
      this.dlaka = event.dlaka;
      this.vet_lokacija = event.vet_lokacija;
      this.ime_psa = event.ime_psa;
      this.spol = event.spol;
      this.pasmina = event.pasmina;
      this.kilaza = event.kilaza;
      this.kastrat = event.kastrat;
      this.opasnost = event.opasnost;
      this.napomena_udomljavanje = event.napomena_udomljavanje;
      this.url_slike = event.url_slike;
      this.oglas_azila = event.oglas_azila;
      this.naziv_azila = event.naziv_azila;
      this.grad_azila = event.grad_azila;
      $("#reportadopteddog").modal("show");
    },
    async update_adopteddogs() {
      let update = {
        ime: this.ime,
        prezime: this.prezime,
        adresa: this.adresa,
        grad: this.grad,
        kontakt: this.kontakt,
        postanski_broj: this.postanski_broj,
        razlog_prijave: this.razlog_prijave,
      };
      try {
        await dog_data
          .update_adoptdog_report(this.id, update)
          .then(() => {
            for (let [i, x] of this.getmyreportadoptdog.entries()) {
              if (x.id == this.id) {
                x.adresa = update.adresa;
                x.grad = update.grad;
                x.ime = update.ime;
                x.kontakt = update.kontakt;
                x.postanski_broj = update.postanski_broj;
                x.prezime = update.prezime;
                x.razlog_prijave = update.razlog_prijave;
                break;
              }
            }
          })
          .then(() => {
            $("#reportadopteddog").modal("hide");
          });
      } catch (e) {
        console.log(e);
      }
    },
    close() {
      this.ime = "";
      this.preime = "";
      this.adresa = "";
      this.grad = "";
      this.kontakt = "";
      this.postanski_broj = "";
      this.id = "";
      this.postavljeno = "";
    },
    myreportmissingdog(event) {
      this.telefonskibr = event.telefonskibr;
      this.ime_nestanak = event.ime_nestanak;
      this.prezime_nestanak = event.prezime_nestanak;
      this.adresa = event.adresa;
      this.grad = event.grad;
      this.postanski_broj = event.postanski_broj;
      this.boja = event.boja;
      this.starost = event.starost;
      this.dlaka = event.dlaka;
      this.vet_lokacija = event.vet_lokacija;
      this.ime_psa = event.ime_psa;
      this.spol = event.spol;
      this.pasmina = event.pasmina;
      this.napomena_nestanak = event.napomena_nestanak;
      this.url_slike = event.url_slike;
      this.datum_izgubljen = this.moment(event.datum_izgubljen).format(
        "YYYY-MM-DD"
      );

      this.adresa_pronalaska = event.adresa_pronalaska;
      this.adresa_za_preuzimanje_psa = event.adresa_za_preuzimanje_psa;
      this.ime = event.ime;
      this.kontakt = event.kontakt;
      this.napomena = event.napomena;
      this.postavljeno = this.moment(parseInt(event.postavljeno)).format(
        "DD.MM.YYYY."
      );
      this.prezime = event.prezime;
      this.id = event.id;
      this.oglas_azila = event.oglas_azila;
      this.naziv_azila = event.naziv_azila;
      this.grad_azila = event.grad_azila;
      $("#updatemissingdogreport").modal("show");
    },
    async updatemissingdog() {
      let update = {
        ime: this.ime,
        prezime: this.prezime,
        adresa_pronalaska: this.adresa_pronalaska,
        adresa_za_preuzimanje_psa: this.adresa_za_preuzimanje_psa,
        kontakt: this.kontakt,
        napomena: this.napomena,
      };
      try {
        await dog_data
          .update_my_missingdog_report(this.id, update)
          .then(() => {
            for (let [i, x] of this.getmyreportdisapp.entries()) {
              if (x.id == this.id) {
                x.ime = update.ime;
                x.prezime = update.prezime;
                x.adresa_pronalaska = update.adresa_pronalaska;
                x.adresa_za_preuzimanje_psa = update.adresa_za_preuzimanje_psa;
                x.kontakt = update.kontakt;
                x.napomena = update.napomena;
                break;
              }
            }
          })
          .then(() => {
            $("#updatemissingdogreport").modal("hide");
          });
      } catch (e) {
        console.log(e);
      }
    },
    refreshmissingdog() {
      this.getdata();
    },
    refreshadopteddog() {
      this.getmyadopteddog();
    },
  },
};
</script>
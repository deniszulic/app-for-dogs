<template>
  <div v-if="store.tipkorisnika == 'azil'">
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
            @click="refreshobrada"
            >U obradi</a
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
            @click="refreshprihvaceni"
            >Prihvaćeni oglasi</a
          >
        </li>
        <li class="nav-item">
          <a
            class="nav-link"
            id="pills-contact-tab"
            data-toggle="pill"
            href="#pills-contact"
            role="tab"
            aria-controls="pills-contact"
            aria-selected="false"
            @click="refreshodbijeni"
            >Odbijeni oglasi</a
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
        <udomljenipsipotvrdaazil
          v-for="data in adopteddogsrequests"
          :key="data.id"
          :data="data"
          @change="listen"
          @getid="updaterequest"
        />
      </div>
      <div
        class="tab-pane fade"
        id="pills-profile"
        role="tabpanel"
        aria-labelledby="pills-profile-tab"
      >
        <udomljenipsiprihvacenepotvrde
          v-for="data in acceptedreq"
          :key="data.id"
          :data="data"
          @change="listen"
          @getid="updaterequest"
        />
      </div>
      <div
        class="tab-pane fade"
        id="pills-contact"
        role="tabpanel"
        aria-labelledby="pills-contact-tab"
      >
        <udomljenipsiodbijenepotvrde
          v-for="data in rejectedreq"
          :key="data.id"
          :data="data"
          @change="listen"
          @getid="updaterequest"
        />
      </div>
    </div>

    <div
      class="modal fade"
      id="pictureModal"
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

    <div
      class="modal fade"
      id="acceptorrejectmissingdog"
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
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Ime vlasnika</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="ime"
                  disabled
                />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Prezime vlasnika</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="prezime"
                  disabled
                />
              </div>
            </div>
            <div class="form-group">
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
            <div class="form-group">
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
            <div class="form-group">
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
                  <span class="input-group-text" id="">Tel. broj</span>
                </div>
                <input
                  type="number"
                  class="form-control"
                  v-model="tel_br"
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
                  <span class="input-group-text" id="">Starost</span>
                </div>
                <input
                  type="number"
                  class="form-control"
                  v-model="starost"
                  disabled
                />
              </div>
            </div>
            <div class="form-group">
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
            <div class="input-group mb-3">
              <div class="input-group-prepend">
                <label class="input-group-text" for="inputGroupSelect01"
                  >Dlaka</label
                >
              </div>
              <select
                class="custom-select"
                id="inputGroupSelect01"
                v-model="dlaka"
                disabled
              >
                <option value="Kratka">Kratka</option>
                <option value="Duga">Duga</option>
              </select>
            </div>
            <div class="input-group mb-3">
              <div class="input-group-prepend">
                <label class="input-group-text" for="inputGroupSelect01"
                  >Spol</label
                >
              </div>
              <select
                class="custom-select"
                id="inputGroupSelect01"
                v-model="spol"
                disabled
              >
                <option value="M">M</option>
                <option value="Ž">Ž</option>
              </select>
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
                  disabled
                ></textarea>
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Dostavljen zahtjev</span>
                </div>
                <input
                  type="text"
                  class="form-control"
                  v-model="postavljeno"
                  disabled
                />
              </div>
            </div>
            <template v-if="url != null">
              <img class="modal-content" :src="url" />
            </template>
            <hr />
            <div class="form-check">
              <input
                class="form-check-input"
                type="radio"
                name="flexRadioDefault"
                id="flexRadioDefault1"
                value="true"
                v-model="prihvaceno"
              />
              <label class="form-check-label" for="flexRadioDefault1">
                <span class="badge badge-pill badge-success">Prihvati</span>
              </label>
            </div>
            <div class="form-check">
              <input
                class="form-check-input"
                type="radio"
                name="flexRadioDefault"
                id="flexRadioDefault2"
                value="false"
                v-model="prihvaceno"
              />
              <label class="form-check-label" for="flexRadioDefault2">
                <span class="badge badge-pill badge-danger">Odbij</span>
              </label>
            </div>
            <div class="form-group" style="margin-top: 10px">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text">Napomena</span>
                </div>
                <textarea
                  class="form-control"
                  aria-label="With textarea"
                  v-model="azil_udomljavanje_napomena"
                ></textarea>
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
              @click="updatereportadopteddog"
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
import moment from "moment";
import { Auth } from "@/services";
import { dog_data } from "@/services";
import udomljenipsipotvrdaazil from "../components/udomljenipsipotvrdaazil.vue";
import udomljenipsiprihvacenepotvrde from "../components/udomljenipsiprihvacenepotvrde.vue";
import udomljenipsiodbijenepotvrde from "../components/udomljenipsiodbijenepotvrde.vue";
import store from "../store.js";
export default {
  name: "potvrdioglaszaudomit",
  components: {
    udomljenipsipotvrdaazil,
    udomljenipsiprihvacenepotvrde,
    udomljenipsiodbijenepotvrde,
  },
  data() {
    return {
      adopteddogsrequests: [],
      acceptedreq: [],
      rejectedreq: [],
      Auth,
      url: "",
      id: "",
      ime: "",
      prezime: "",
      adresa: "",
      grad: "",
      postanski_broj: "",
      tel_br: "",
      ime_psa: "",
      pasmina: "",
      starost: "",
      vet_lokacija: "",
      boja: "",
      // datum_izgubljen:"",
      dlaka: "",
      spol: "",
      napomena: "",
      postavljeno: "",
      aktivan: "",
      azil_udomljavanje_napomena: "",
      prihvaceno: null,
      moment,
      udomljavanjeid: null,
      store,
    };
  },
  created() {
    this.getdata();
    this.getaccepteddata();
    this.getrejecteddata();
  },
  watch: {
    $route: "getdata",
    $route: "getaccepteddata",
    $route: "getrejecteddata",
  },
  methods: {
    async getdata() {
      try {
        this.adopteddogsrequests = await dog_data.getadopteddogsshelterreports(
          Auth.state.email
        );
      } catch (e) {
        console.log(e);
      }
    },
    async getaccepteddata() {
      try {
        this.acceptedreq = await dog_data.getacceptedadopteddogsshelterreports(
          Auth.state.email
        );
      } catch (e) {
        console.log(e);
      }
    },
    async getrejecteddata() {
      try {
        this.rejectedreq = await dog_data.getrejectedadopteddogsshelterreports(
          Auth.state.email
        );
      } catch (e) {
        console.log(e);
      }
    },
    listen(event) {
      this.url = event;
      $("#pictureModal").modal("show");
    },
    updaterequest(data) {
      this.url = data.url;
      this.id = data.id;
      this.ime = data.ime;
      this.prezime = data.prezime;
      this.adresa = data.adresa;
      this.grad = data.grad;
      this.postanski_broj = data.postanski_broj;
      this.tel_br = data.telefonskibr;
      this.ime_psa = data.ime_psa;
      this.pasmina = data.pasmina;
      this.starost = data.starost;
      this.vet_lokacija = data.vet_lokacija;
      this.boja = data.boja;
      this.dlaka = data.dlaka;
      this.spol = data.spol;
      this.napomena = data.napomena;
      this.azil_udomljavanje_napomena = data.azil_udomljavanje_napomena;
      this.postavljeno = this.moment(
        parseInt(data.azil_udomljavanje_postavljeno)
      ).format("DD.MM.YYYY.");
      this.aktivan = data.aktivan;
      this.prihvaceno = data.prihvaceno;
      this.udomljavanjeid = data.udomljavanje_id;
      $("#acceptorrejectmissingdog").modal("show");
    },
    async updatereportadopteddog() {
      let data = {
        napomena: this.azil_udomljavanje_napomena,
        prihvaceno: this.prihvaceno,
      };
      try {
        await dog_data.updatereportadopteddog(this.id, data).then(() => {
          this.getrejecteddata();
          this.getaccepteddata();
          this.getdata();
          $("#acceptorrejectmissingdog").modal("hide");
        });
      } catch (e) {
        console.log(e);
      }
    },
    refreshodbijeni() {
      this.getrejecteddata();
    },
    refreshprihvaceni() {
      this.getaccepteddata();
    },
    refreshobrada() {
      this.getdata();
    },
  },
};
</script>
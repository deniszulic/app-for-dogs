<template>
    <div v-if="store.tipkorisnika=='azil'">
        <div class="d-flex justify-content-center" style="margin-top: 10px">
        <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
  <li class="nav-item">
    <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab" aria-controls="pills-home" aria-selected="true" @click="refreshmissingdog">Nestali psi</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-profile" role="tab" aria-controls="pills-profile" aria-selected="false" @click="refreshadopteddog">Udomljavanje psa</a>
  </li>
</ul>
    </div>
<div class="tab-content" id="pills-tabContent">
  <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
    <prijavenamojnestalipas :data="reportsonmymissingdog" @reportmissingdogs="missingdogs" />
  </div>
  <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">
      <prijavenamojudomljenpas :data="reportsonmyad" @reportsonmyadadopteddogs="openmodal"/>      
  </div>
</div>

<div
          class="modal fade"
          id="reportmyadopteddog"
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
                <!-- <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Ime</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="ime_udomljavanje" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Prezime</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="prezime_udomljavanje" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Adresa</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="adresa_udomljavanje" disabled
                    />
                  </div>
                </div> -->
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Tel. br.</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="telefonskibr" disabled
                    />
                  </div>
                </div>
                <!-- <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Grad</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="grad_udomljavanje" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Poštanski br.</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="postanski_broj_udomljavanje" disabled
                    />
                  </div>
                </div> -->
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Boja</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="boja" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Starost</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="starost" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Dlaka</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="dlaka" disabled
                    />
                  </div>
                </div>
                <!-- <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Vet. lokacija</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="vet_lokacija" disabled
                    />
                  </div>
                </div> -->
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Ime psa</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="ime_psa" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Spol</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="spol" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Pasmina</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="pasmina" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Kilaža</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="kilaza" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Kastrat</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="kastrat" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Opasnost</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="opasnost" disabled
                    />
                  </div>
                </div>
                <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text">Napomena</span>
                    </div>
                    <textarea
                      class="form-control"
                      aria-label="With textarea"
                      v-model="napomena_udomljavanje" disabled
                    ></textarea>
                  </div>
                  <template v-if="url_slike!=null">
                  <img class="modal-content" style="margin-top:10px;" :src="url_slike" />
                </template>

                <hr/>
                <div class="form-group">
                  <h5 class="modal-title">Podaci prijave</h5>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Ime</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="ime" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Prezime</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="prezime" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Adresa</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="adresa" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Grad</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="grad" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Poštanski broj</span
                      >
                    </div>
                    <input
                      type="number"
                      class="form-control"
                      v-model="postanski_broj" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Kontakt</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="kontakt" disabled
                    />
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
                      v-model="razlog_prijave" disabled
                    ></textarea>
                  </div>
                  </div>
                  <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Postavljeno</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="postavljeno" disabled
                    />
                  </div>
                </div><hr/>
                <div class="form-group">
                  <h5 class="modal-title">Odgovor korisniku</h5>
                </div>
                <div class="form-check">
                    <h5>Prihvati?</h5>
  <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1" value='True' v-model="prihvaceno">
  <label class="form-check-label" for="flexRadioDefault1">
    <span class="badge badge-pill badge-success">Prihvaćeno</span>
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" value='False' v-model="prihvaceno">
  <label class="form-check-label" for="flexRadioDefault2">
    <span class="badge badge-pill badge-danger">Odbijeno</span>
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" value='Obrada' v-model="prihvaceno">
  <label class="form-check-label" for="flexRadioDefault2">
    <span class="badge badge-pill badge-warning">U razradi</span>
  </label>
</div>
<div class="form-group" style="margin-top:10px;">
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
              </div>
              <div class="modal-footer">
                <button
                  type="button"
                  class="btn btn-secondary"
                  data-dismiss="modal"
                >
                  Zatvori
                </button>
                <button type="button" class="btn btn-primary" @click="updateadoptdog">Spremi</button>
              </div>
            </div>
          </div>
        </div>


        <div
          class="modal fade"
          id="reportmymissingddog"
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
                <!-- <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Ime</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="nestanak_ime" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Prezime</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="nestanak_prezime" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Adresa</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="adresa" disabled
                    />
                  </div>
                </div> -->
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Telefonski br.</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="telefonskibr" disabled
                    />
                  </div>
                </div>
                <!-- <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Grad</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="grad" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Poštanski br.</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="postanski_broj" disabled
                    />
                  </div>
                </div> -->
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Boja</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="boja" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Starost</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="starost" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Dlaka</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="dlaka" disabled
                    />
                  </div>
                </div>
                <!-- <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Vet. lokacija</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="vet_lokacija" disabled
                    />
                  </div>
                </div> -->
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Ime psa</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="ime_psa" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Spol</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="spol" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Pasmina</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="pasmina" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Datum izgubljen</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="datum_izgubljen" disabled
                    />
                  </div>
                </div>
                <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text">Napomena</span>
                    </div>
                    <textarea
                      class="form-control"
                      aria-label="With textarea"
                      v-model="nestanak_napomena" disabled
                    ></textarea>
                  </div>
                  <template v-if="url_slike!=null">
                  <img class="modal-content" style="margin-top:10px;" :src="url_slike" />
                </template>

                <hr />

                <div class="form-group">
                  <h5 class="modal-title">Podaci prijave</h5>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Ime</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="ime" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Prezime</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="prezime" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Kontakt</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="kontakt" disabled
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
                      v-model="napomena" disabled
                    ></textarea>
                  </div></div>
                  <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Adresa pronalaska</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="adresa_pronalaska" disabled
                    />
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
                      v-model="adresa_za_preuzimanje_psa" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Postavljeno</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="postavljeno" disabled
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
                <!-- <button type="button" class="btn btn-primary" @click="updateadoptdog">Spremi</button> -->
              </div>
            </div>
          </div>
        </div>

    </div>
</template>
<script>
import { dog_data } from "@/services";
import {Auth} from "@/services";
import prijavenamojudomljenpas from "@/components/prijavenamojudomljenpas.vue";
import prijavenamojnestalipas from "@/components/prijavenamojnestalipas.vue";
import store from '../store.js'
import moment from 'moment';
export default {
    name:"prijavenamojoglas",
    //select prijava_na_udomljavanje.ime, prijava_na_udomljavanje.prezime, prijava_na_udomljavanje.adresa, prijava_na_udomljavanje.grad, prijava_na_udomljavanje.postanski_broj, prijava_na_udomljavanje.kontakt, prijava_na_udomljavanje.razlog_prijave, prijava_na_udomljavanje.prihvaceno, prijava_na_udomljavanje.postavljeno, prijava_na_udomljavanje.id from prijava_na_udomljavanje left join udomljavanje on prijava_na_udomljavanje.udomljavanje_id=udomljavanje.id left join korisnik on udomljavanje.korisnik_id=korisnik.id where korisnik.email='b@b.com'
    components: {
    prijavenamojudomljenpas,
    prijavenamojnestalipas
  },
    data(){
        return{
            reportsonmyad:[],
            reportsonmymissingdog:[],
            Auth,
            razlog_prijave:"",
          ime:"",
          prezime:"",
          adresa:"",
          grad:"",
          kontakt:"",
          postanski_broj:"",
          postavljeno:"",
          prihvaceno:"",
          id:"",
          napomena:"",
          moment,
          // ime_udomljavanje:"",
          // prezime_udomljavanje:"",
          // adresa_udomljavanje:"",
          // grad_udomljavanje:"",
          // postanski_broj_udomljavanje:"",
          telefonskibr:"",
          boja:"",
          starost:"",
          dlaka:"",
          // vet_lokacija:"",
          ime_psa:"",
          spol:"",
          pasmina:"",
          kilaza:"",
          kastrat:"",
          opasnost:"",
          napomena_udomljavanje:"",
          url_slike:"",
          adresa_pronalaska:"",
          adresa_za_preuzimanje_psa:"",
          // nestanak_ime:"",
          // nestanak_prezime:"",
          nestanak_napomena:"",
          datum_izgubljen:"",
          store
        }
    },
    created() {
    this.getdata();
    this.getmissingdogs();
  },
  watch: {
    $route: "getdata",
    $route: "getmissingdogs"
  },
  methods:{
      async getdata() {
      try {
        this.reportsonmyad = await dog_data.reportsonmyadopteddogs(Auth.state.email);
      } catch (e) {
        this.errormsg = e.message;
      }
    },
    async getmissingdogs() {
      try {
        this.reportsonmymissingdog = await dog_data.reportsonmymissingdogs(Auth.state.email);
      } catch (e) {
        this.errormsg = e.message;
      }
    },
    openmodal(event){
        this.id=event.id
        this.ime=event.ime
        this.prezime=event.prezime
        this.adresa=event.adresa
        this.grad=event.grad
        this.kontakt=event.kontakt
        this.postanski_broj=event.postanski_broj
        this.prihvaceno=event.prihvaceno
        this.razlog_prijave=event.razlog_prijave
        this.napomena=event.napomena
        // this.ime_udomljavanje=event.udomljavanje_ime
        // this.prezime_udomljavanje=event.udomljavanje_prezime
        // this.adresa_udomljavanje=event.udomljavanje_adresa
        this.telefonskibr=event.telefonskibr
        // this.grad_udomljavanje=event.udomljavanje_grad
        // this.postanski_broj_udomljavanje=event.udomljavanje_postanski_broj
        this.boja=event.boja
        this.starost=event.starost
        this.dlaka=event.dlaka
        // this.vet_lokacija=event.vet_lokacija
        this.ime_psa=event.ime_psa
        this.spol=event.spol
        this.pasmina=event.pasmina
        this.kilaza=event.kilaza
        this.kastrat=event.kastrat
        this.opasnost=event.opasnost
        this.napomena_udomljavanje=event.udomljavanje_napomena
        this.url_slike=event.url_slike
        this.postavljeno=this.moment(parseInt(event.postavljeno)).format('DD.MM.YYYY.')
        $("#reportmyadopteddog").modal("show")
    },
    missingdogs(event){
      this.ime=event.ime
      this.adresa_pronalaska=event.adresa_pronalaska
      this.adresa_za_preuzimanje_psa=event.adresa_za_preuzimanje_psa
      this.id=event.id
      this.kontakt=event.kontakt
      this.napomena=event.napomena
      this.postavljeno=this.moment(parseInt(event.postavljeno)).format('DD.MM.YYYY.')
      this.prezime=event.prezime


      this.boja=event.boja
        this.spol=event.spol
        this.starost=event.starost
        this.postanski_broj=event.postanski_broj
        this.grad=event.grad
        this.pasmina=event.pasmina
        this.dlaka=event.dlaka
        // this.nestanak_ime=event.nestanak_ime
        // this.nestanak_prezime=event.nestanak_prezime
        this.nestanak_napomena=event.nestanak_napomena
        this.adresa=event.adresa
        this.datum_izgubljen=this.moment(event.datum_izgubljen).format("DD.MM.YYYY.")
        this.ime_psa=event.ime_psa
        this.telefonskibr=event.telefonskibr
        this.url_slike=event.url_slike
        // this.vet_lokacija=event.vet_lokacija
        $("#reportmymissingddog").modal("show")

    },
    async updateadoptdog(){
        let update={
            napomena:this.napomena,
            prihvaceno:this.prihvaceno
        }
        try{
            await dog_data.update_my_adoptdog_report(this.id, update).then(()=>{
              for (let [i, x] of this.reportsonmyad.entries()) {
              if (x.id == this.id) {
                x.napomena=update.napomena
                x.prihvaceno=update.prihvaceno
                break;
              }
              }
            }).then(()=>{$("#reportmyadopteddog").modal("hide")})
        }catch(e){
            console.log(e)
        }
    },
     refreshmissingdog(){
      this.getmissingdogs();
    },
    refreshadopteddog(){
      this.getdata();
    }
  }
}
</script>
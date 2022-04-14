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
            >Nestanak psa</a
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
            >Udomljeni psi</a
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
        <mojinestalipsi
          v-for="data in missingdogs"
          :key="data.id"
          :data="data"
          @change="listen"
          @changedata_missingdog="form_missingdog"
          @getid="sendtoshelter"
        />

      <div
          class="modal fade"
          id="promjenanestanakpsa"
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
                  <h5 class="modal-title">Osnovni podaci oglasa</h5>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Ime vlasnika</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="ime"
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Prezime vlasnika</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="prezime"
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
                      v-model="adresa"
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
                      v-model="grad"
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
                      v-model="postanski_broj"
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Tel. broj</span
                      >
                    </div>
                    <input
                      type="number"
                      class="form-control"
                      v-model="tel_br"
                    />
                  </div>
                </div>
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
                      v-model="ime_psa"
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
                      v-model="pasmina"
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
                      type="number"
                      class="form-control"
                      v-model="starost"
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Vet. lokacija</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="vet_lokacija"
                    />
                  </div>
                </div>
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
                      v-model="boja"
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
                      type="date"
                      class="form-control"
                      v-model="datum_izgubljen"
                    />
                  </div>
                </div>
                <div class="input-group mb-3">
  <div class="input-group-prepend">
    <label class="input-group-text" for="inputGroupSelect01">Dlaka</label>
  </div>
  <select class="custom-select" id="inputGroupSelect01" v-model="dlaka">
    <option value="Kratka">Kratka</option>
    <option value="Duga">Duga</option>
  </select>
</div>
<div class="input-group mb-3">
  <div class="input-group-prepend">
    <label class="input-group-text" for="inputGroupSelect01">Spol</label>
  </div>
  <select class="custom-select" id="inputGroupSelect01" v-model="spol">
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
                      v-model="nestanak_napomena"
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
                </div>
                <template v-if="url != null">
                  <img class="modal-content" :src="url" />
                </template>
                <hr/>
               <div class="form-check">
  <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1" value=true v-model="aktivan">
  <label class="form-check-label" for="flexRadioDefault1">
    <span class="badge badge-pill badge-success">Aktivan oglas</span>
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" value=false v-model="aktivan">
  <label class="form-check-label" for="flexRadioDefault2">
    <span class="badge badge-pill badge-danger">Neaktivan oglas</span>
  </label>
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
                <button type="button" class="btn btn-primary" @click="updatemissingdog">Spremi</button>
              </div>
            </div>
          </div>
        </div>


      </div>
      <div
        class="tab-pane fade"
        id="pills-profile"
        role="tabpanel"
        aria-labelledby="pills-profile-tab"
      >
        <mojiudomipsa
          v-for="data in adoptingdogs"
          :key="data.id"
          :data="data"
          @change="listen"
          @changedata_adoptdog="form"
          @getadoptedid="sendtoshelteradopt"
        />
        <div
          class="modal fade"
          id="promjenaudomipsa"
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
                  <h5 class="modal-title">Osnovni podaci oglasa</h5>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Ime vlasnika</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="ime"
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Prezime vlasnika</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="prezime"
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
                      v-model="adresa"
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
                      v-model="grad"
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
                      v-model="postanski_broj"
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Tel. broj</span
                      >
                    </div>
                    <input
                      type="number"
                      class="form-control"
                      v-model="tel_br"
                    />
                  </div>
                </div>
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
                      v-model="ime_psa"
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
                      type="number"
                      class="form-control"
                      v-model="kilaza"
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
                      v-model="pasmina"
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
                      type="number"
                      class="form-control"
                      v-model="starost"
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Vet. lokacija</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="vet_lokacija"
                    />
                  </div>
                </div>
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
                      v-model="boja"
                    />
                  </div>
                </div>
                <div class="input-group mb-3">
  <div class="input-group-prepend">
    <label class="input-group-text" for="inputGroupSelect01">Dlaka</label>
  </div>
  <select class="custom-select" id="inputGroupSelect01" v-model="dlaka">
    <option value="Kratka">Kratka</option>
    <option value="Duga">Duga</option>
  </select>
</div>
<div class="input-group mb-3">
  <div class="input-group-prepend">
    <label class="input-group-text" for="inputGroupSelect01">Kastrat</label>
  </div>
  <select class="custom-select" id="inputGroupSelect01" v-model="kastrat">
    <option value="Da">Da</option>
    <option value="Ne">Ne</option>
  </select>
</div>
<div class="input-group mb-3">
  <div class="input-group-prepend">
    <label class="input-group-text" for="inputGroupSelect01">Moguća opasnost psa?</label>
  </div>
  <select class="custom-select" id="inputGroupSelect01" v-model="opasnost">
    <option value="Da">Da</option>
    <option value="Ne">Ne</option>
  </select>
</div>
<div class="input-group mb-3">
  <div class="input-group-prepend">
    <label class="input-group-text" for="inputGroupSelect01">Spol</label>
  </div>
  <select class="custom-select" id="inputGroupSelect01" v-model="spol">
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
                </div>
                <template v-if="url != null">
                  <img class="modal-content" :src="url" />
                </template>
                <hr/>
                <div class="form-check">
  <input class="form-check-input" type="radio" name="flexRadioDefault1" id="flexRadioDefault3" value=true v-model="aktivan">
  <label class="form-check-label" for="flexRadioDefault3">
    <span class="badge badge-pill badge-success">Aktivan oglas</span>
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" type="radio" name="flexRadioDefault2" id="flexRadioDefault4" value=false v-model="aktivan">
  <label class="form-check-label" for="flexRadioDefault4">
    <span class="badge badge-pill badge-danger">Neaktivan oglas</span>
  </label>
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
          id="shelterreport"
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
                <div id="requestforshelter">
                <select class="form-select form-select-lg mb-3" aria-label="Default select example" v-model="odabranavet">
  <option v-for="(a, index) in shelters" :key="index">{{a.naziv}}, {{a.grad}}</option>
</select>
  <div class="d-flex justify-content-center">
                <button v-if="prihvaceno==null" class="btn btn-primary" type="submit" @click="sendrequest">Pošalji molbu azilu</button></div>
              <hr/>
              </div>

               <div class="form-check">
  <input class="form-check-input" type="radio" name="flexRadioDefault1" id="flexRadioDefault1" value="true" v-model="prihvaceno_azil_missing" disabled>
  <label class="form-check-label" for="flexRadioDefault1">
    <span class="badge badge-pill badge-success">Prihvaćeno</span>
  </label>
</div>
<div class="form-check">    
  <input class="form-check-input" type="radio" name="flexRadioDefault2" id="flexRadioDefault2" value="false" v-model="prihvaceno_azil_missing" disabled>
  <label class="form-check-label" for="flexRadioDefault2">
    <span class="badge badge-pill badge-danger">Odbijeno</span>
  </label>
</div>
<div class="form-group" style="margin-top:10px;">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text">Razlog</span>
                    </div>
                    <textarea
                      class="form-control"
                      aria-label="With textarea"
                      v-model="azil_nestanak_napomena" disabled
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
              </div>
            </div>
          </div>
        </div>

        <div
          class="modal fade"
          id="shelterreportadopt"
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
                <div id="requestforshelteradopt">
                <select class="form-select form-select-lg mb-3" aria-label="Default select example" v-model="odabranavet">
  <option v-for="(a, index) in shelters" :key="index">{{a.naziv}}, {{a.grad}}</option>
</select>
  <div class="d-flex justify-content-center">
                <button v-if="prihvaceno==null" class="btn btn-primary" type="submit" @click="sendrequestadopt">Pošalji molbu azilu</button></div>
              <hr/>
              </div>

               <div class="form-check">
  <input class="form-check-input" type="radio" name="flexRadioDefault3" id="flexRadioDefault3" value="true" v-model="prihvaceno_azil" disabled>
  <label class="form-check-label" for="flexRadioDefault3">
    <span class="badge badge-pill badge-success">Prihvaćeno</span>
  </label>
</div>
<div class="form-check">    
  <input class="form-check-input" type="radio" name="flexRadioDefault4" id="flexRadioDefault4" value=false v-model="prihvaceno_azil" disabled>
  <label class="form-check-label" for="flexRadioDefault4">
    <span class="badge badge-pill badge-danger">Odbijeno</span>
  </label>
</div>
<div class="form-group" style="margin-top:10px;">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text">Razlog</span>
                    </div>
                    <textarea
                      class="form-control"
                      aria-label="With textarea"
                      v-model="azil_nestanak_napomena" disabled
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
              </div>
            </div>
          </div>
        </div>
  </div>
</template>
<script>
import moment from 'moment';
import { Auth } from "@/services";
import { dog_data } from "@/services";
import mojinestalipsi from "@/components/mojinestalipsi.vue";
import mojiudomipsa from "@/components/mojiudomipsa.vue";
export default {
  name: "mojioglasi",
  components: {
    mojinestalipsi,
    mojiudomipsa,
  },
  async mounted(){
    moment.locale('hr')
    },
  data() {
    return {
      moment,
      missingdogs: [],
      adoptingdogs: [],
      url: "",
      id:"",
          ime:"",
          prezime:"",
          grad:"",
          adresa:"",
          postanski_broj:"",
          ime_psa:"",
          kilaza:"",
          pasmina:"",
          boja:"",
          dlaka:"",
          kastrat:"",
          napomena:"",
          opasnost:"",
          postavljeno:"",
          spol:"",
          starost:"",
          tel_br:"",
          vet_lokacija:"",
          datum_izgubljen:"",
          aktivan: null,
          prihvaceno: null,
          shelters:[],
          odabranavet:"",
          shelterid:"",
          prihvaceno_azil:"",
          azil_nestanak_napomena:"",
          prihvaceno_azil_missing:"",
          nestanak_napomena:""
    };
  },
  created() {
    this.getdatamissingdogs();
    this.getdataadopteddogs();
    this.getshelters();
  },
  watch: {
    $route: "getdatamissingdogs",
    $route: "getdataadopteddogs",
    $route: "getshelters"
  },
  methods: {
    async getdatamissingdogs() {
      try {
        this.missingdogs = await dog_data.getmymissingdogs(Auth.state.email);
      } catch (e) {
        this.errormsg = e.message;
        console.log(e);
      }
    },
    async getdataadopteddogs() {
      try {
        this.adoptingdogs = await dog_data.getmyadopteddogs(Auth.state.email);
      } catch (e) {
        this.errormsg = e.message;
        console.log(e);
      }
    },
    listen(event) {
      this.url = event;
      $("#pictureModal").modal("show");
    },
    form(event){
      this.id=event.id
      this.ime=event.ime
      this.prezime=event.prezime
      this.adresa=event.adresa
      this.postanski_broj=event.postanski_broj
      this.grad=event.grad
      this.ime_psa=event.ime_psa
      this.kilaza=event.kilaza
      this.pasmina=event.pasmina
      this.url=event.url_slike
      this.boja=event.boja
      this.dlaka=event.dlaka
      this.kastrat=event.kastrat
      this.napomena=event.napomena
      this.opasnost=event.opasnost
      this.postavljeno=this.moment(parseInt(event.postavljeno)).format('DD.MM.YYYY.')
      this.spol=event.spol
      this.starost=event.starost
      this.tel_br=event.telefonskibr
      this.vet_lokacija=event.vet_lokacija
      this.aktivan=event.aktivan
      $("#promjenaudomipsa").modal("show");
    },
    form_missingdog(event){
      this.id=event.id
      this.ime=event.ime
      this.prezime=event.prezime
      this.adresa=event.adresa
      this.postanski_broj=event.postanski_broj
      this.grad=event.grad
      this.ime_psa=event.ime_psa
      this.pasmina=event.pasmina
      this.url=event.url_slike
      this.boja=event.boja
      this.dlaka=event.dlaka
      this.napomena=event.napomena
      this.postavljeno=this.moment(parseInt(event.postavljeno)).format('DD.MM.YYYY.')
      this.spol=event.spol
      this.starost=event.starost
      this.tel_br=event.telefonskibr
      this.vet_lokacija=event.vet_lokacija
      this.datum_izgubljen=this.moment(event.datum_izgubljen).format('YYYY-MM-DD')
      this.aktivan=event.aktivan
      this.nestanak_napomena=event.nestanak_napomena
      $("#promjenanestanakpsa").modal("show");
    },
    async updateadoptdog(){
      let update={
        ime:this.ime,
        prezime:this.prezime,
      adresa:this.adresa,
      postanski_broj:this.postanski_broj,
      grad:this.grad,
      ime_psa:this.ime_psa,
      kilaza:this.kilaza,
      pasmina:this.pasmina,
      boja:this.boja,
      dlaka:this.dlaka,
      kastrat:this.kastrat,
      napomena:this.napomena,
      opasnost:this.opasnost,
      spol:this.spol,
      starost:this.starost,
      telefonskibr:this.tel_br,
      vet_lokacija:this.vet_lokacija,
      aktivan:this.aktivan
      }
      try{
        await dog_data.updatemydataadopteddog(this.id, update).then(()=>{
          for (let [i, x] of this.adoptingdogs.entries()) {
              if (x.id == this.id) {
                x.ime=update.ime
                x.prezime=update.prezime
                x.adresa=update.adresa
                x.postanski_broj=update.postanski_broj
                x.grad=update.grad
                x.ime_psa=update.ime_psa
                x.kilaza=update.kilaza
                x.pasmina=update.pasmina
                x.boja=update.boja
                x.dlaka=update.dlaka
                x.kastrat=update.kastrat
                x.napomena=update.napomena
                x.opasnost=update.opasnost
                x.spol=update.spol
                x.starost=update.starost
                x.telefonskibr=update.telefonskibr
                x.vet_lokacija=update.vet_lokacija
                x.napomena=update.napomena
                x.aktivan=update.aktivan
                break;
              }
          }
        }).then(() => $("#promjenaudomipsa").modal("hide"));
      }catch(e){
        console.log(e)
      }
    },
    async updatemissingdog(){
      let update={
        ime:this.ime,
        prezime:this.prezime,
      adresa:this.adresa,
      postanski_broj:this.postanski_broj,
      grad:this.grad,
      ime_psa:this.ime_psa,
      pasmina:this.pasmina,
      boja:this.boja,
      dlaka:this.dlaka,
      napomena:this.nestanak_napomena,
      spol:this.spol,
      starost:this.starost,
      telefonskibr:this.tel_br,
      vet_lokacija:this.vet_lokacija,
      datum_izgubljen:this.datum_izgubljen,
      aktivan: this.aktivan
      }
      try{
        await dog_data.updatemydatamissingddog(this.id, update).then(()=>{
          for (let [i, x] of this.missingdogs.entries()) {
              if (x.id == this.id) {
                x.ime=update.ime
                x.prezime=update.prezime
                x.adresa=update.adresa
                x.postanski_broj=update.postanski_broj
                x.grad=update.grad
                x.ime_psa=update.ime_psa
                x.pasmina=update.pasmina
                x.boja=update.boja
                x.dlaka=update.dlaka
                x.nestanak_napomena=update.napomena
                x.spol=update.spol
                x.starost=update.starost
                x.telefonskibr=update.telefonskibr
                x.vet_lokacija=update.vet_lokacija
                x.datum_izgubljen=update.datum_izgubljen
                x.aktivan=update.aktivan
                break;
              }
          }
        }).then(() => $("#promjenanestanakpsa").modal("hide"))
      }catch(e){
        console.log(e)
      }
    },
    async getshelters(){
    try{
      this.shelters = await dog_data.getshelters();
    }catch(e){
      console.log(e)
    }
  },
    sendtoshelter(event){
      if(event.prihvaceno!=null) $("#requestforshelter").hide();
      this.id=event.id
      this.azil_nestanak_napomena=event.napomena
      this.prihvaceno_azil_missing=event.prihvaceno
      $("#shelterreport").modal("show");
    },
    sendtoshelteradopt(event){
      if(event.prihvaceno!=null) $("#requestforshelteradopt").hide();
      //udomljavanje id ne od adopt
      this.id=event.id
      this.azil_nestanak_napomena=event.azil_udomljavanje_napomena
      this.prihvaceno_azil=event.prihvaceno
      $("#shelterreportadopt").modal("show");
    },
    async sendrequestadopt(){
      let a = this.odabranavet.substr(0, this.odabranavet.indexOf(','))
      for (let [i, x] of this.shelters.entries()) {
              if (x.naziv == a) {
                this.shelterid=x.id
                console.log(x.id)
                break;
              }
      }
      let data = {
        udomljavanje_id: this.id,
        korisnik_id: this.shelterid,
        postavljeno: Date.now(),
        prihvaceno: "obrada"
      }
      try{
        await dog_data.sendreqadopt(data).then(()=>{
           $("#requestforshelteradopt").hide();
        })
      }catch(e){
        console.log(e)
      }
    },
    async sendrequest(){
      let a = this.odabranavet.substr(0, this.odabranavet.indexOf(','))
      for (let [i, x] of this.shelters.entries()) {
              if (x.naziv == a) {
                this.shelterid=x.id
                console.log(x.id)
                break;
              }
      }
      let data = {
        nestanak_id: this.id,
        korisnik_id: this.shelterid,
        postavljeno: Date.now(),
        prihvaceno: "obrada"
      }
      try{
        await dog_data.sendreqshelter(data).then(()=>{
           $("#requestforshelter").hide();
        })
      }catch(e){
        console.log(e)
      }
    }
  },
};
</script>
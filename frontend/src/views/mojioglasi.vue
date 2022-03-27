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
                <!-- <div class="form-group">
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
                </div> -->
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
<!-- <div class="input-group mb-3">
  <div class="input-group-prepend">
    <label class="input-group-text" for="inputGroupSelect01">Kastrat</label>
  </div>
  <select class="custom-select" id="inputGroupSelect01" v-model="kastrat">
    <option value="Da">Da</option>
    <option value="Ne">Ne</option>
  </select>
</div> -->
<!-- <div class="input-group mb-3">
  <div class="input-group-prepend">
    <label class="input-group-text" for="inputGroupSelect01">Moguća opasnost psa?</label>
  </div>
  <select class="custom-select" id="inputGroupSelect01" v-model="opasnost">
    <option value="Da">Da</option>
    <option value="Ne">Ne</option>
  </select>
</div> -->
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
  mounted(){
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
          datum_izgubljen:""
    };
  },
  created() {
    this.getdatamissingdogs();
    this.getdataadopteddogs();
  },
  watch: {
    $route: "getdatamissingdogs",
    $route: "getdataadopteddogs",
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
      vet_lokacija:this.vet_lokacija
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
      napomena:this.napomena,
      spol:this.spol,
      starost:this.starost,
      telefonskibr:this.tel_br,
      vet_lokacija:this.vet_lokacija,
      datum_izgubljen:this.datum_izgubljen
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
                x.napomena=update.napomena
                x.spol=update.spol
                x.starost=update.starost
                x.telefonskibr=update.telefonskibr
                x.vet_lokacija=update.vet_lokacija
                x.datum_izgubljen=update.datum_izgubljen
                break;
              }
          }
        }).then(() => $("#promjenanestanakpsa").modal("hide"))
      }catch(e){
        console.log(e)
      }
    }
  },
};
</script>
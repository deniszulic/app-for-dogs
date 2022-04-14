<template>
<div>
  <div class="d-flex justify-content-center" style="margin-top: 10px">
      <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
  <li class="nav-item">
    <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab" aria-controls="pills-home" aria-selected="true">Oglasi korisnika</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-profile" role="tab" aria-controls="pills-profile" aria-selected="false">Oglasi azila</a>
  </li>
</ul>
</div>
<div class="tab-content" id="pills-tabContent">
  <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
    <nestalipsiadmintablica :data="missingdogs" @myreportmissingdog="openmodal"/></div>
  <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">
      <nestalipsiadmintablicaazil :data="missingdogsshelter" @myreportmissingdog="openmodalshelter"/>
  </div>
</div>

        <div
          class="modal fade"
          id="missingdogadmin"
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
                      v-model="telefonskibr"
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
                <template v-if="url_slike != null">
                  <img class="modal-content" :src="url_slike" />
                </template>
                <hr/>
                <div class="form-group">
                  <h5 class="modal-title">Podaci korisnika</h5>
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
                      v-model="korisnik_ime" disabled
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
                      v-model="korisnik_prezime" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Email</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="email" disabled
                    />
                  </div>
                </div>
                <div class="form-check">
  <input class="form-check-input" type="radio" name="flexRadioDefault1" id="flexRadioDefault1" value=true v-model="aktivan_user">
  <label class="form-check-label" for="flexRadioDefault1">
    <span class="badge badge-pill badge-success">Aktivan oglas</span>
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" type="radio" name="flexRadioDefault2" id="flexRadioDefault2" value=false v-model="aktivan_user">
  <label class="form-check-label" for="flexRadioDefault2">
    <span class="badge badge-pill badge-danger">Neaktivan oglas</span>
  </label>
</div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-warning mr-auto" @click="deletedata">Obriši</button>
                <button
                  type="button"
                  class="btn btn-secondary"
                  data-dismiss="modal"
                >
                  Zatvori
                </button>
                <button type="button" class="btn btn-primary" @click="updatedatamissingdoguser">Spremi</button>
              </div>
            </div>
          </div>
        </div>

        <div
          class="modal fade"
          id="missingdogadminshelter"
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
                      <span class="input-group-text" id=""
                        >Tel. broj</span
                      >
                    </div>
                    <input
                      type="number"
                      class="form-control"
                      v-model="telefonskibr"
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
                <template v-if="url_slike != null">
                  <img class="modal-content" :src="url_slike" />
                </template>
                <div class="form-check">
  <input class="form-check-input" type="radio" name="flexRadioDefault3" id="flexRadioDefault3" value=true v-model="aktivan">
  <label class="form-check-label" for="flexRadioDefault3">
    <span class="badge badge-pill badge-success">Aktivan oglas</span>
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" type="radio" name="flexRadioDefault4" id="flexRadioDefault4" value=false v-model="aktivan">
  <label class="form-check-label" for="flexRadioDefault4">
    <span class="badge badge-pill badge-danger">Neaktivan oglas</span>
  </label>
</div>
                <hr/>
                <div class="form-group">
                  <h5 class="modal-title">Podaci korisnika</h5>
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
                      v-model="korisnik_ime" disabled
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
                      v-model="korisnik_prezime" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Email</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="email" disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Naziv ambulante</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="naziv" disabled
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
                      v-model="azil_grad" disabled
                    />
                  </div>
                </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-warning mr-auto" @click="deletedata">Obriši</button>
                <button
                  type="button"
                  class="btn btn-secondary"
                  data-dismiss="modal"
                >
                  Zatvori
                </button>
                <button type="button" class="btn btn-primary" @click="updatedatamissingdog">Spremi</button>
              </div>
            </div>
          </div>
        </div>
  </div>
</template>
<script>
import { dog_data } from "@/services";
import nestalipsiadmintablica from "@/components/nestalipsiadmintablica.vue";
import nestalipsiadmintablicaazil from "@/components/nestalipsiadmintablicaazil.vue";
import moment from 'moment';
export default {
  name: "nestalipsiadmin",
  components: {
    nestalipsiadmintablica,
    nestalipsiadmintablicaazil
  },
  data() {
    return {
      moment,
      missingdogs: [],
      missingdogsshelter:[],
      adresa:"",
      boja:"",
      datum_izgubljen:"",
      dlaka:"",
      email:"",
      grad:"",
      ime:"",
      ime_psa:"",
        korisnik_ime:"",
        korisnik_prezime:"",
        napomena:"",
        pasmina:"",
        postanski_broj:"",
        postavljeno:"",
        prezime:"",
        spol:"",
        starost:"",
        telefonskibr:"",
        url_slike:"",
        vet_lokacija:"",
        id:null,
        aktivan:null,
        aktivan_user:null,
        azil_grad:"",
        naziv:""
    };
  },
  created() {
    this.getdata();
    this.getdatashelter();
  },
  watch: {
    $route: "getdata",
    $route: "getdatashelter"
  },
  methods: {
    async getdata() {
      try {
        this.missingdogs = await dog_data.getmissingdogsadmin();
      } catch (e) {
        this.errormsg = e.message;
      }
    },
    async getdatashelter() {
      try {
        this.missingdogsshelter = await dog_data.getmissingdogsadminshelter();
      } catch (e) {
        this.errormsg = e.message;
      }
    },
    openmodal(event){
        this.id=event.id
        this.adresa=event.adresa
        this.boja=event.boja
        this.datum_izgubljen=this.moment(event.datum_izgubljen).format('YYYY-MM-DD')
        this.dlaka=event.dlaka
        this.email=event.email
        this.grad=event.grad
        this.ime=event.ime
        this.ime_psa=event.ime_psa
        this.korisnik_ime=event.korisnik_ime
        this.korisnik_prezime=event.korisnik_prezime
        this.napomena=event.napomena
        this.pasmina=event.pasmina
        this.postanski_broj=event.postanski_broj
        this.postavljeno=this.moment(parseInt(event.postavljeno)).format('DD.MM.YYYY.')
        this.prezime=event.prezime
        this.spol=event.spol
        this.starost=event.starost
        this.telefonskibr=event.telefonskibr
        this.url_slike=event.url_slike
        this.vet_lokacija=event.vet_lokacija
        this.aktivan_user=event.aktivan
        $("#missingdogadmin").modal("show")
    },
    openmodalshelter(event){
        this.id=event.id
        this.boja=event.boja
        this.datum_izgubljen=this.moment(event.datum_izgubljen).format('YYYY-MM-DD')
        this.dlaka=event.dlaka
        this.email=event.email
        this.ime_psa=event.ime_psa
        this.korisnik_ime=event.korisnik_ime
        this.korisnik_prezime=event.korisnik_prezime
        this.napomena=event.napomena
        this.pasmina=event.pasmina
        this.postavljeno=this.moment(parseInt(event.postavljeno)).format('DD.MM.YYYY.')
        this.spol=event.spol
        this.starost=event.starost
        this.telefonskibr=event.telefonskibr
        this.url_slike=event.url_slike
        this.aktivan=event.aktivan
        this.naziv=event.naziv
        this.azil_grad=event.azil_grad
        $("#missingdogadminshelter").modal("show")
    },
    async updatedatamissingdog(){
        let data={
            boja:this.boja,
            datum_izgubljen:this.datum_izgubljen,
            dlaka:this.dlaka,
            grad:this.grad,
            ime:this.ime,
            prezime:this.prezime,
            ime_psa:this.ime_psa,
            napomena:this.napomena,
            pasmina:this.pasmina,
            postanski_broj:this.postanski_broj,
            spol:this.spol,
            starost:this.starost,
            telefonskibr:this.telefonskibr,
            vet_lokacija:this.vet_lokacija,
            adresa:this.adresa,
            aktivan: this.aktivan
        }
        try{
            await dog_data.updatemydatamissingddog(this.id, data).then(()=>{
                $("#missingdogadminshelter").modal("hide")
                this.getdatashelter();
            })
        }catch(e){
            console.log(e)
        }
    },
    async updatedatamissingdoguser(){
      let data={
            boja:this.boja,
            datum_izgubljen:this.datum_izgubljen,
            dlaka:this.dlaka,
            grad:this.grad,
            ime:this.ime,
            prezime:this.prezime,
            ime_psa:this.ime_psa,
            napomena:this.napomena,
            pasmina:this.pasmina,
            postanski_broj:this.postanski_broj,
            spol:this.spol,
            starost:this.starost,
            telefonskibr:this.telefonskibr,
            vet_lokacija:this.vet_lokacija,
            adresa:this.adresa,
            aktivan: this.aktivan_user
        }
        try{
            await dog_data.updatemydatamissingddog(this.id, data).then(()=>{
                $("#missingdogadmin").modal("hide")
                this.getdata();
            })
        }catch(e){
            console.log(e)
        }
    },
    async deletedata(){
      try{
        await dog_data.deletespecificdata(this.id)
        .then(()=>{
          $("#missingdogadmin").modal("hide")
          $("#missingdogadminshelter").modal("hide")
                this.getdata();
                this.getdatashelter();
        })
      }catch(e){
        console.log(e)
      }
    }
  },
};
</script>
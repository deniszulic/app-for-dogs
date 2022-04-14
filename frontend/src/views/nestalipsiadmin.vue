<template>
  <div style="margin-top:10px;">
    <nestalipsiadmintablica :data="missingdogs" @myreportmissingdog="openmodal"/>
    <!-- <div
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
                  <h5 class="modal-title">Prijava pronalska</h5>
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
                      v-model="ime" 
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
                <button type="button" class="btn btn-primary">Spremi</button>
              </div>
            </div>
          </div>
        </div> -->
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
              </div>
              <div class="modal-footer">
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
import moment from 'moment';
export default {
  name: "nestalipsiadmin",
  components: {
    nestalipsiadmintablica,
  },
  data() {
    return {
      moment,
      missingdogs: [],
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
        id:null
    };
  },
  created() {
    this.getdata();
  },
  watch: {
    $route: "getdata",
  },
  methods: {
    async getdata() {
      try {
        this.missingdogs = await dog_data.getmissingdogsadmin();
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
        $("#missingdogadmin").modal("show")
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
            adresa:this.adresa
        }
        try{
            await dog_data.updatedatamissingdogs(this.id, data).then(()=>{
                $("#missingdogadmin").modal("hide")
                this.getdata();
            })
        }catch(e){
            console.log(e)
        }
    }
  },
};
</script>
<template>
    <div>
        <div class="d-flex justify-content-center" style="margin-top: 10px">
        <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
  <li class="nav-item">
    <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab" aria-controls="pills-home" aria-selected="true">Nestali psi</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-profile" role="tab" aria-controls="pills-profile" aria-selected="false">Udomljavanje psa</a>
  </li>
</ul>
    </div>
<div class="tab-content" id="pills-tabContent">
  <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
        <mojeprijavenestalipsi :data="getmyreportdisapp"/>
  </div>
  <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">
    <mojeprijaveudomljenipsi :data="getmyreportadoptdog" @myreports_adopteddogs="adopted_dogs_report"/>
  </div>
</div>

<div
          class="modal fade"
          id="reportadopteddog"
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
                        >Kontakt</span
                      >
                    </div>
                    <input
                      type="number"
                      class="form-control"
                      v-model="kontakt"
                    />
                  </div>
                </div>
                <!-- <div class="form-group">
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
                </div> -->
                <!-- <div class="form-group">
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
</div> -->
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
                <!-- <template v-if="url != null">
                  <img class="modal-content" :src="url" />
                </template> -->
              </div>
              <div class="modal-footer">
                <button
                  type="button"
                  class="btn btn-secondary"
                  data-dismiss="modal" @click="close"
                >
                  Zatvori
                </button>
                <button type="button" class="btn btn-primary" @click="update_adopteddogs">Spremi</button>
              </div>
            </div>
          </div>
        </div>

    </div>
</template>
<script>
import mojeprijavenestalipsi from "@/components/mojeprijavenestalipsi.vue";
import mojeprijaveudomljenipsi from "@/components/mojeprijaveudomljenipsi.vue";
import {Auth} from "@/services";
import { dog_data } from "@/services";
import moment from 'moment';
export default {
    name:"mojeprijave",
    components: {
    mojeprijavenestalipsi,
    mojeprijaveudomljenipsi
  },
  data(){
      return{
          getmyreportdisapp:[],
          getmyreportadoptdog:[],
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
          moment
      }
  },
  created() {
    this.getdata();
    this.getmyadopteddog();
  },
  watch: {
    $route: "getdata",
    $route: "getmyadopteddog",
  },
  methods:{
      async getdata(){
        try{
            this.getmyreportdisapp=await dog_data.getmyreportdisapp(Auth.state.email)
        }catch(e){
            console.log(e)
        }
      },
      async getmyadopteddog(){
          try{
            this.getmyreportadoptdog=await dog_data.getmyreportadoptdog(Auth.state.email)
        }catch(e){
            console.log(e)
        }
      },
      adopted_dogs_report(event){
        this.id=event.id
        this.ime=event.ime
        this.prezime=event.prezime
        this.adresa=event.adresa
        this.grad=event.grad
        this.kontakt=event.kontakt
        this.postanski_broj=event.postanski_broj
        this.prihvaceno=event.prihvaceno
        this.razlog_prijave=event.razlog_prijave
        this.postavljeno=this.moment(parseInt(event.postavljeno)).format('DD.MM.YYYY.')
        $("#reportadopteddog").modal("show")
      },
      async update_adopteddogs(){
        let update={
          ime:this.ime,
          prezime:this.prezime,
          adresa:this.adresa,
          grad:this.grad,
          kontakt:this.kontakt,
          postanski_broj:this.postanski_broj,
          razlog_prijave:this.razlog_prijave
        }
        try{
          await dog_data.update_adoptdog_report(this.id, update)
        }catch(e){
          console.log(e)
        }
      },
      close(){
        this.ime=""
            this.preime=""
            this.adresa=""
            this.grad=""
            this.kontakt=""
            this.postanski_broj=""
            this.id=""
            this.postavljeno=""
      }
  }
}
</script>
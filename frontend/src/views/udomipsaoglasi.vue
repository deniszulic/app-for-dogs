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
    <udomipsa v-for="data in adoptingdogs" :key="data.id" :data="data" @change="listen" @alldata="form" />
  </div>
  <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">
     
    <udomipsa v-for="data in getadopteddogsshelter" :key="data.id" :data="data" @change="listen" @alldata="form" />

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
              <div class="modal-content" style="display:inline-block;">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <img class="modal-content" :src="url" />
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Zatvori</button>
                </div>
              </div>
            </div>
          </div>
          
    <div class="modal fade" id="prijavaudomipsa" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Id: {{id}}</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <!-- <input type="text" class="form-control" v-model="id" disabled> -->
      <div class="form-group">
      <h5 class="modal-title">Osnovni podaci oglasa</h5></div>
       <div class="form-group">
      <div class="input-group">
  <div class="input-group-prepend">
    <span class="input-group-text" id="">Ime i prezime vlasnika</span>
  </div>
  <input type="text" class="form-control" v-model="ime" disabled>
  <input type="text" class="form-control" v-model="prezime" disabled>
</div>
       </div>
       <div class="form-group">
      <div class="input-group">
  <div class="input-group-prepend">
    <span class="input-group-text" id="">Adresa, grad i poštanski broj</span>
  </div>
  <input type="text" class="form-control" v-model="adresa" disabled>
  <input type="text" class="form-control" v-model="grad" disabled>
  <input type="text" class="form-control" v-model="postanski_broj" disabled>
</div>
       </div>
       <div class="form-group">
      <div class="input-group">
  <div class="input-group-prepend">
    <span class="input-group-text" id="">Ime psa, kilaža i pasmina</span>
  </div>
  <input type="text" class="form-control" v-model="ime_psa" disabled>
  <input type="text" class="form-control" v-model="kilaza" disabled>
  <input type="text" class="form-control" v-model="pasmina" disabled>
</div>
       </div>
       <template v-if="url!=null">
        <img class="modal-content" :src="url" />
        </template>
        <!-- <div class="form-group">
      <div class="input-group mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1">aaaaaaaaaa</span>
  </div>
  <input type="text" class="form-control" placeholder="Username" v-model="id" disabled>
</div>
</div> -->
<hr>
<div class="form-group">
<h5 class="modal-title">Podaci za prijavu</h5>
</div>
<div class="form-group">
      <div class="input-group mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1">Ime</span>
  </div>
  <input type="text" class="form-control" v-model="ime_obrazac">
</div>
</div>
<div class="form-group">
      <div class="input-group mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1">Prezime</span>
  </div>
  <input type="text" class="form-control" v-model="prezime_obrazac">
</div>
</div>
<div class="form-group">
      <div class="input-group mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1">Adresa</span>
  </div>
  <input type="text" class="form-control" v-model="adresa_obrazac">
</div>
</div>
<div class="form-group">
      <div class="input-group mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1">Grad</span>
  </div>
  <input type="text" class="form-control" v-model="grad_obrazac">
</div>
</div>
<div class="form-group">
      <div class="input-group mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1">Pošt. broj</span>
  </div>
  <input type="number" class="form-control" v-model="postbr_obrazac">
</div>
</div>
<div class="form-group">
      <div class="input-group mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1">Kontakt</span>
  </div>
  <input type="text" class="form-control" v-model="kontakt_obrazac">
</div>
</div>
<div class="form-group">
  <div class="input-group">
  <div class="input-group-prepend">
    <span class="input-group-text">Razlog prijave</span>
  </div>
  <textarea class="form-control" aria-label="With textarea" v-model="prijava"></textarea>
</div>
                  </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Zatvori</button>
        <button type="button" class="btn btn-primary" @click="form_adoptdog">Spremi</button>
      </div>
    </div>
  </div>
</div>
    </div>
</template>
<script>
import { dog_data } from "@/services";
import udomipsa from "@/components/udomipsa.vue";
import {Auth} from "@/services";
export default {
    name:"udomipsaoglasi",
    components: {
    udomipsa
  },
  data(){
      return{
          adoptingdogs:[],
          getadopteddogsshelter:[],
          url:"",
          id:"",
          ime:"",
          prezime:"",
          grad:"",
          adresa:"",
          postanski_broj:"",
          ime_psa:"",
          kilaza:"",
          pasmina:"",
          url:"",
          prijava:"",
          ime_obrazac:"",
          prezime_obrazac:"",
          adresa_obrazac:"",
          grad_obrazac:"",
          postbr_obrazac:"",
          kontakt_obrazac:"",
          Auth
      }
  },
  created() {
    this.getdata();
    this.getadopteddogs();
  },
  watch: {
    $route: "getdata",
    $route: "getadopteddogs"
  },
  methods:{
      async getdata() {
      try {
        this.adoptingdogs = await dog_data.getadopteddogs();
      } catch (e) {
        this.errormsg = e.message;
      }
    },
    async getadopteddogs() {
      try {
        this.getadopteddogsshelter = await dog_data.getadopteddogsshelter();
      } catch (e) {
        this.errormsg = e.message;
      }
    },
    listen(event){
      this.url=event
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
      $("#prijavaudomipsa").modal("show");
    },
    async form_adoptdog(){
      let data={
        ime:this.ime_obrazac,
        prezime:this.prezime_obrazac,
        adresa:this.adresa_obrazac,
        grad:this.grad_obrazac,
        postanski_broj:this.postbr_obrazac,
        kontakt:this.kontakt_obrazac,
        razlog_prijave:this.prijava,
        prihvaceno:"Obrada",
        postavljeno:Date.now(),
        udomljavanje_id:this.id,
        korisnik_id:this.Auth.state.id
      }
      try{
        await dog_data.adopt_dog_application(data).then(()=>{
          $("#prijavaudomipsa").modal("hide");
          this.ime_obrazac=""
          this.prezime_obrazac=""
        this.adresa_obrazac=""
        this.grad_obrazac=""
        this.postbr_obrazac=""
        this.kontakt_obrazac=""
        this.prijava=""
        })
      }catch(e){
        console.log(e)
      }
    }
  }
}
</script>
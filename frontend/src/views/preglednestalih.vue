<template>
    <div>

      <div class="d-flex justify-content-center" style="margin-top: 10px">
      <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
  <li class="nav-item">
    <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab" aria-controls="pills-home" aria-selected="true" @click="refreshuserdata">Oglasi korisnika</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-profile" role="tab" aria-controls="pills-profile" aria-selected="false" @click="refreshshelterdata">Oglasi azila</a>
  </li>
</ul>
</div>
<div class="tab-content" id="pills-tabContent">
  <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
    <nestalipsi v-for="data in missingdogs" :key="data.id" :data="data" @change="listen" @form_missing_dog="form_missing_dog" />
  </div>
  <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">
     
    <nestalipsiazil v-for="data in missingdogsshelter" :key="data.id" :data="data" @change="listen" @form_missing_dog="form_missing_dog" />

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
          <div
          class="modal fade"
          id="form_missingdog"
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
                      v-model="ime" required
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
                      v-model="prezime" required
                    />
                  </div>
                </div>
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
                      v-model="adresa_pronalaska"
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Adresa za pokupiti psa</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="adresa_pas"
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
                      v-model="kontakt" required
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
                <button type="button" class="btn btn-primary" @click="findmissingdog">Spremi</button>
              </div>
            </div>
          </div>
        </div>
    </div>
</template>
<script>
import { dog_data } from "@/services";
import nestalipsi from "@/components/nestalipsi.vue";
import nestalipsiazil from "@/components/nestalipsiazil.vue";
import {Auth} from "@/services";
export default {
    name:"preglednestalih",
    components: {
    nestalipsi,
    nestalipsiazil
  },
  data(){
      return{
          missingdogs:[],
          missingdogsshelter:[],
          url:"",
          adresa_pronalaska:"",
          id:"",
          ime:"",
          prezime:"",
          adresa_pas:"",
          napomena:"",
          kontakt:""
      }
  },
  created() {
    this.getdata();
    this.getdatashelter();
  },
  watch: {
    $route: "getdata",
    $route: "getdatashelter"
  },
  methods:{
      async getdata() {
      try {
        this.missingdogs = await dog_data.getmissingdogs();
      } catch (e) {
        this.errormsg = e.message;
      }
    },
    async getdatashelter() {
      try {
        this.missingdogsshelter = await dog_data.getmissingdogsshelter();
      } catch (e) {
        this.errormsg = e.message;
      }
    },
    listen(event){
      this.url=event
      $("#pictureModal").modal("show");
    },
    form_missing_dog(event){
       this.id=event
      $("#form_missingdog").modal("show");
    },
    async findmissingdog(){
      let data={
        ime:this.ime,
        prezime:this.prezime,
        napomena:this.napomena,
        adresa_pronalaska:this.adresa_pronalaska,
        adresa_za_pokupiti_psa:this.adresa_pas,
        postavljeno:Date.now(),
        korisnik_id:Auth.state.id,
        nestanak_id:this.id,
        kontakt:this.kontakt
      }
      try{
        await dog_data.report_missing_dog(data).then(()=>{
          $("#form_missingdog").modal("hide");
        })
      }catch(e){
        console.log(e)
      }
    },
    refreshuserdata(){
      this.getdata();
    },
    refreshshelterdata(){
      this.getdatashelter();
    }
  }
}
</script>
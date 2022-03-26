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
        />

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
          @alldata="form"
        />
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
          id="prijavaudomipsa"
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
                        >Ime i prezime vlasnika</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="ime"
                      disabled
                    />
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
                      <span class="input-group-text" id=""
                        >Adresa, grad i poštanski broj</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="adresa"
                      disabled
                    />
                    <input
                      type="text"
                      class="form-control"
                      v-model="grad"
                      disabled
                    />
                    <input
                      type="text"
                      class="form-control"
                      v-model="postanski_broj"
                      disabled
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id=""
                        >Ime psa, kilaža i pasmina</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="ime_psa"
                      disabled
                    />
                    <input
                      type="text"
                      class="form-control"
                      v-model="kilaza"
                      disabled
                    />
                    <input
                      type="text"
                      class="form-control"
                      v-model="pasmina"
                      disabled
                    />
                  </div>
                </div>
                <template v-if="url != null">
                  <img class="modal-content" :src="url" />
                </template>
                <hr />
                <div class="form-group">
                  <h5 class="modal-title">Podaci za prijavu</h5>
                </div>
                <div class="form-group">
                  <div class="input-group mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id="basic-addon1"
                        >Ime</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="ime_obrazac"
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id="basic-addon1"
                        >Prezime</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="prezime_obrazac"
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id="basic-addon1"
                        >Adresa</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="adresa_obrazac"
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id="basic-addon1"
                        >Grad</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="grad_obrazac"
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id="basic-addon1"
                        >Pošt. broj</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="postbr_obrazac"
                    />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id="basic-addon1"
                        >Kontakt</span
                      >
                    </div>
                    <input
                      type="text"
                      class="form-control"
                      v-model="kontakt_obrazac"
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
                      v-model="prijava"
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
        </div>
      </div>
    </div>
  </div>
</template>
<script>
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
  data() {
    return {
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
          url:"",
          prijava:"",
          ime_obrazac:"",
          prezime_obrazac:"",
          adresa_obrazac:"",
          grad_obrazac:"",
          postbr_obrazac:"",
          kontakt_obrazac:""
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
        this.missingdogs = await dog_data.getmymissingdogs(Auth.state.id);
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
      $("#prijavaudomipsa").modal("show");
    },
    onFileChange(e) {
      this.slika = e.target.files[0];
    },
    send_data(){
    if(this.slika!=null){
    let uploadTask=firebase.storage().ref("adopt_dog/"+Auth.state.email+"/"+Date.now()+".png").put(this.slika)
uploadTask.on('state_changed', 
  (snapshot) => {
    this.postotak = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
  }, 
  (error) => {
    console.log(error.message)
  }, 
  () => {
    uploadTask.snapshot.ref.getDownloadURL().then((downloadURL) => {
      if(this.napomena==""){
        this.napomena=null
      }
      let podaci={
        ime:this.ime,
        prezime:this.prezime,
        adresa:this.adresa,
        grad:this.grad,
        postanski_broj:this.postbr,
        boja:this.boja,
        starost:this.starost,
        dlaka:this.dlaka,
        vet_lokacija:this.vet_lokacija,
        ime_psa:this.ime_psa,
        spol:this.spol,
        datum_izgubljen:this.datum,
        napomena:this.napomena,
        pasmina:this.pasmina,
        kg:this.kg,
        kastrat:this.kastrat,
        opasnost:this.opasnost,
        postavljeno:Date.now(),
        telefonskibr:this.telefonskibr,
        Korisnik_id:Auth.state.id,
        url_slike:downloadURL
      }
      try{
        dog_data.adopt_dog(podaci)
        $("#modalnotice").modal("show");
        this.$refs.fileupload.value=null
        this.postotak=null
        this.ime=""
        this.prezime=""
        this.adresa=""
        this.grad=""
        this.postbr=""
        this.boja=""
        this.starost=""
        this.dlaka=""
        this.vet_lokacija=""
        this.ime_psa=""
        this.spol=""
        this.datum=""
        this.napomena=null
        this.telefonskibr=""
        this.pasmina=""
        this.kg=""
        this.kastrat=""
        this.opasnost=""
        this.slika=null
      }catch(e){
        console.log(e)
      }
    });
  }
);
    }
    else{
      if(this.napomena==""){
        this.napomena=null
      }
let podaci={
        ime:this.ime,
        prezime:this.prezime,
        adresa:this.adresa,
        grad:this.grad,
        postanski_broj:this.postbr,
        boja:this.boja,
        starost:this.starost,
        dlaka:this.dlaka,
        vet_lokacija:this.vet_lokacija,
        ime_psa:this.ime_psa,
        spol:this.spol,
        datum_izgubljen:this.datum,
        kg:this.kg,
        kastrat:this.kastrat,
        opasnost:this.opasnost,
        napomena:this.napomena,
        telefonskibr:this.telefonskibr,
        pasmina:this.pasmina,
        postavljeno:Date.now(),
        Korisnik_id:Auth.state.id
      }
      try{
        dog_data.adopt_dog(podaci)
        $("#modalnotice").modal("show");
        this.$refs.fileupload.value=null
        this.ime=""
        this.prezime=""
        this.adresa=""
        this.grad=""
        this.postbr=""
        this.boja=""
        this.starost=""
        this.dlaka=""
        this.vet_lokacija=""
        this.ime_psa=""
        this.spol=""
        this.datum=""
        this.napomena=null
        this.telefonskibr=""
        this.pasmina=""
        this.kg=""
        this.kastrat=""
        this.opasnost=""
      }catch(e){
        console.log(e)
      }
    }
    }
  },
};
</script>
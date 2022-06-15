<template>
  <div
    class="background rounded"
    style="overflow: hidden"
    v-if="store.tipkorisnika == 'korisnik'"
  >
    <div class="row">
      <div class="col-sm-6 dogimg">
      </div>
      <div class="col-sm-6">
        <form @submit.prevent="send_data">
          <div class="form-row" id="name_surname">
            <div class="form-group col-md-6">
              <label for="inputEmail4">Ime</label>
              <input
                type="text"
                class="form-control"
                id="inputEmail4"
                v-model="ime"
                required
              />
            </div>
            <div class="form-group col-md-6">
              <label for="inputPassword4">Prezime</label>
              <input
                type="text"
                class="form-control"
                id="inputPassword4"
                v-model="prezime"
                required
              />
            </div>
          </div>
          <div class="form-row" id="boja_starost">
            <div class="form-group col-md-6">
              <label for="inputAddress">Adresa</label>
              <input
                type="text"
                class="form-control"
                id="inputAddress"
                v-model="adresa"
                required
              />
            </div>
            <div class="form-group col-md-6">
              <label for="inputEmail4">Telefonski br.</label>
              <input
                type="text"
                class="form-control"
                id="inputEmail4"
                v-model="telefonskibr"
                required
              />
            </div>
          </div>
          <div class="form-row" id="grad_postnum">
            <div class="form-group col-md-8">
              <label for="inputCity">Grad</label>
              <input
                type="text"
                class="form-control"
                id="inputCity"
                v-model="grad"
                required
              />
            </div>
            <div class="form-group col-md-4">
              <label for="inputZip">Poštanski br.</label>
              <input
                type="number"
                class="form-control"
                id="inputZip"
                v-model="postbr"
                required
              />
            </div>
          </div>
          <div class="form-row" id="boja_starost">
            <div class="form-group col-md-6">
              <label for="inputPassword4">Boja</label>
              <input
                type="text"
                class="form-control"
                id="inputPassword4"
                v-model="boja"
                required
              />
            </div>
            <div class="form-group col-md-6">
              <label for="inputEmail4">Starost</label>
              <input
                type="number"
                class="form-control"
                id="inputEmail4"
                v-model="starost"
                required
              />
            </div>
          </div>
          <div class="form-row" id="dlaka_lokacija">
            <div class="form-group col-md-6">
              <label for="inputState">Dlaka</label>
              <select
                id="inputState"
                class="form-control"
                v-model="dlaka"
                required
              >
                <option>Kratka</option>
                <option>Duga</option>
              </select>
            </div>
            <div class="form-group col-md-6">
              <label for="inputEmail4">Iz koje vet. stanice</label>
              <input
                type="text"
                class="form-control"
                id="inputEmail4"
                v-model="vet_lokacija"
                required
              />
            </div>
          </div>
          <div class="form-row">
            <div class="form-group col-md-6" id="dogname">
              <label for="inputPassword4">Ime psa</label>
              <input
                type="text"
                class="form-control"
                id="inputPassword4"
                v-model="ime_psa"
                required
              />
            </div>
            <div class="form-group col-md-6" id="steril">
              <label for="inputState1">Spol</label>
              <select
                id="inputState1"
                class="form-control"
                v-model="spol"
                required
              >
                <option>M</option>
                <option>Ž</option>
              </select>
            </div>
          </div>
          <div class="form-row">
            <div class="form-group col-md-6" id="dateid">
              <label for="inputPassword4">Datum kada je pas izgubljen</label>
              <input
                type="date"
                class="form-control"
                v-model="datum"
                required
              />
            </div>
            <div class="form-group col-md-6" id="noticeid">
              <label for="napomena">Pasmina</label>
              <input
                type="text"
                class="form-control"
                id="inputPassword4"
                v-model="pasmina"
                required
              />
            </div>
          </div>
          <div class="form-row">
            <div class="form-group col-md-6" id="noticeid">
              <label for="napomena">Napomena</label>
              <textarea style="min-width: 100%" v-model="napomena"></textarea>
            </div>
          </div>
          <div class="form-row">
            <div class="form-group col-md-12" id="pictureofdog">
              <label for="dog_img">Slika psa</label><br />
              <input
                type="file"
                accept="image/png, image/jpeg"
                ref="fileupload"
                @change="onFileChange"
              />
            </div>
          </div>
          <div class="d-flex justify-content-center">
            <button type="submit" class="btn btn-primary" id="submitbutton">
              Pošalji
            </button>
          </div>
        </form>
        <div class="progress">
          <div
            class="progress-bar"
            :style="`width:` + postotak + `%`"
            role="progressbar"
            aria-valuenow="0"
            aria-valuemin="0"
            aria-valuemax="100"
          ></div>
        </div>
      </div>
    </div>
    <div
      class="modal fade"
      id="modalnotice"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Potvrda</h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">Podatci su uspješno poslani!</div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-dismiss="modal">
              Zatvori
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { Auth } from "@/services";
import { dog_data } from "@/services";
import store from "../store.js";
export default {
  name: "nestanakpsa",
  data() {
    return {
      ime: "",
      prezime: "",
      adresa: "",
      grad: "",
      boja: "",
      starost: "",
      dlaka: "",
      postbr: "",
      vet_lokacija: "",
      ime_psa: "",
      spol: "",
      napomena: null,
      datum: "",
      telefonskibr: "",
      pasmina: "",
      slika: null,
      postotak: null,
      store,
    };
  },
  mounted() {},
  methods: {
    next() {
      $("#name_surname").hide();
      $("#adresa").hide();
      $("#grad_postnum").hide();
      $("#boja_starost").hide();
      $("#dlaka_lokacija").hide();
      $("#dateid").fadeIn();
      $("#noticeid").fadeIn();
      $("#pictureofdog").fadeIn();
      $("#steril").fadeIn();
      $("#dogname").fadeIn();
      $("#submitbutton").css("display", "block");
      $("#rightarrow").css("display", "none");
      $("#leftarrow").css("display", "block");
      //$("#submitbutton").fadeIn();
    },
    back() {
      $("#dateid").hide();
      $("#noticeid").hide();
      $("#pictureofdog").hide();
      $("#steril").hide();
      $("#dogname").hide();
      $("#submitbutton").css("display", "none");
      $("#leftarrow").css("display", "none");
      //$("#submitbutton").hide();

      $("#name_surname").fadeIn();
      $("#adresa").fadeIn();
      $("#grad_postnum").fadeIn();
      $("#boja_starost").fadeIn();
      $("#dlaka_lokacija").fadeIn();
      $("#rightarrow").css("display", "block");
    },
    onFileChange(e) {
      this.slika = e.target.files[0];
    },
    send_data() {
      if (this.slika != null) {
        let uploadTask = firebase
          .storage()
          .ref("missing_dogs/" + Auth.state.email + "/" + Date.now() + ".png")
          .put(this.slika);
        uploadTask.on(
          "state_changed",
          (snapshot) => {
            this.postotak =
              (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
          },
          (error) => {
            console.log(error.message);
          },
          () => {
            uploadTask.snapshot.ref.getDownloadURL().then((downloadURL) => {
              if (this.napomena == "") {
                this.napomena = null;
              }
              let podaci = {
                ime: this.ime,
                prezime: this.prezime,
                adresa: this.adresa,
                grad: this.grad,
                postanski_broj: this.postbr,
                boja: this.boja,
                starost: this.starost,
                dlaka: this.dlaka,
                vet_lokacija: this.vet_lokacija,
                ime_psa: this.ime_psa,
                spol: this.spol,
                datum_izgubljen: this.datum,
                napomena: this.napomena,
                pasmina: this.pasmina,
                postavljeno: Date.now(),
                telefonskibr: this.telefonskibr,
                Korisnik_id: Auth.state.id,
                url_slike: downloadURL,
                aktivan: true,
                oglas_azila: false,
              };
              try {
                dog_data.missing_dog(podaci);
                $("#modalnotice").modal("show");
                this.$refs.fileupload.value = null;
                this.postotak = null;
                this.ime = "";
                this.prezime = "";
                this.adresa = "";
                this.grad = "";
                this.postbr = "";
                this.boja = "";
                this.starost = "";
                this.dlaka = "";
                this.vet_lokacija = "";
                this.ime_psa = "";
                this.spol = "";
                this.datum = "";
                this.napomena = null;
                this.telefonskibr = "";
                this.slika = null;
                this.pasmina = "";
              } catch (e) {
                console.log(e);
              }
            });
          }
        );
      } else {
        if (this.napomena == "") {
          this.napomena = null;
        }
        let podaci = {
          ime: this.ime,
          prezime: this.prezime,
          adresa: this.adresa,
          grad: this.grad,
          postanski_broj: this.postbr,
          boja: this.boja,
          starost: this.starost,
          dlaka: this.dlaka,
          vet_lokacija: this.vet_lokacija,
          ime_psa: this.ime_psa,
          spol: this.spol,
          datum_izgubljen: this.datum,
          napomena: this.napomena,
          telefonskibr: this.telefonskibr,
          pasmina: this.pasmina,
          postavljeno: Date.now(),
          Korisnik_id: Auth.state.id,
          aktivan: true,
          oglas_azila: false,
        };
        try {
          dog_data.missing_dog(podaci);
          $("#modalnotice").modal("show");
          this.$refs.fileupload.value = null;
          this.ime = "";
          this.prezime = "";
          this.adresa = "";
          this.grad = "";
          this.postbr = "";
          this.boja = "";
          this.starost = "";
          this.dlaka = "";
          this.vet_lokacija = "";
          this.ime_psa = "";
          this.spol = "";
          this.datum = "";
          this.napomena = null;
          this.telefonskibr = "";
          this.pasmina = "";
        } catch (e) {
          console.log(e);
        }
      }
    },
  },
};
</script>
<style scoped>
.background {
  background-color: #fff;
  margin-top: 50px;
  margin-bottom: 50px;
}
form {
  padding: 20px;
}
.img {
}
.dogimg {
  background: url("../assets/missingdog.jpg") top left/cover no-repeat;
  display: block;
  width: 100%;
  height: auto;
  background-position: center center;
  background-size: cover;
  transform: scale(1);
  transition: transform 0.3s ease;
}
.dogimg:hover {
  transform: scale(0.9);
  transition: transform 0.3s ease;
}
.dogimg:after {
}
@media (max-width: 767px) {
  .dogimg {
    width: 100%;
    padding-top: 400px;
    background-position: center center;
  }
}
</style>
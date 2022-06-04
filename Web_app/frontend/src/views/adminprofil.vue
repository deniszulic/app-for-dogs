<template>
  <div class="container" v-if="store.tipkorisnika == 'admin'">
    <div
      class="row justify-content-sm-center"
      style="margin-top: 20px; margin-bottom: 20px"
    >
      <div class="col-sm-auto">
        <i class="fa-solid fa-bone fa-10x"></i>
      </div>
    </div>
    <div class="row">
      <div class="col-md">
        <form
          class="background rounded"
          @submit.prevent="updatemydata"
          style="margin-bottom: 10px; background-color: #404040"
        >
          <h1 style="color: white">Ažuriraj podatke</h1>
          <div class="form-group">
            <label for="name" style="color: white">Ime</label>
            <input
              type="text"
              class="form-control"
              v-model="ime"
              style="color: white"
            />
          </div>
          <div class="form-group">
            <label for="surname" style="color: white">Prezime</label>
            <input
              type="text"
              class="form-control"
              v-model="prezime"
              style="color: white"
            />
          </div>
          <div class="form-group">
            <label for="surname" style="color: white">Datum registracije</label>
            <input
              type="text"
              class="form-control"
              v-model="registracija"
              style="color: white"
              disabled
            />
          </div>
          <button type="submit" class="btn btn-primary">Ažuriraj</button>
        </form>
      </div>
      <div class="col-md">
        <form
          class="background rounded"
          @submit.prevent="changepass"
          style="background-color: #404040"
        >
          <h1 style="color: white">Lozinka</h1>
          <div class="form-group">
            <input
              type="password"
              class="form-control"
              id="exampleInputPassword1"
              placeholder="Lozinka"
              v-model="lozinka"
              style="color: white"
            />
          </div>
          <button type="submit" class="btn btn-primary">
            Promijeni lozinku
          </button>
        </form>
      </div>
    </div>
    <div class="row">
      <div class="col-md">
        <form
          class="background rounded"
          @submit.prevent="addadmin"
          style="margin-bottom: 10px; background-color: #404040"
        >
          <h1 style="color: white">Dodaj admina</h1>
          <div class="form-group">
            <label for="name" style="color: white">Ime</label>
            <input
              type="text"
              class="form-control"
              v-model="ime_register"
              style="color: white"
            />
          </div>
          <div class="form-group">
            <label for="surname" style="color: white">Prezime</label>
            <input
              type="text"
              class="form-control"
              v-model="prezime_register"
              style="color: white"
            />
          </div>
          <div class="form-group">
            <label for="surname" style="color: white">Email</label>
            <input
              type="text"
              class="form-control"
              v-model="email_register"
              style="color: white"
            />
          </div>
          <div class="form-group">
            <label for="password" style="color: white">Lozinka</label>
            <input
              type="password"
              class="form-control"
              v-model="lozinka_register"
              style="color: white"
            />
          </div>
          <button type="submit" class="btn btn-primary">Dodaj</button>
        </form>
      </div>
    </div>

    <div class="modal fade" id="modal" tabindex="-1" role="dialog">
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <i class="fa-solid fa-check"></i>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">Podaci su uspješno ažurirani!</div>
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
import { Auth } from "@/services";
import { profile } from "@/services";
import moment from "moment";
import { login_signup } from "@/services";
import store from "../store.js";
export default {
  name: "adminprofil",
  data() {
    return {
      profile: [],
      ime: "",
      prezime: "",
      lozinka: "",
      registracija: null,
      moment,
      ime_register: "",
      prezime_register: "",
      email_register: "",
      lozinka_register: "",
      store,
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
        this.profile = await profile.changeuserdata(Auth.state.email);
        this.ime = this.profile[0].ime;
        this.prezime = this.profile[0].prezime;
        this.registracija = this.moment(
          parseInt(this.profile[0].datumreg)
        ).format("DD.MM.YYYY.");
      } catch (e) {
        this.errormsg = e.message;
      }
    },
    async updatemydata() {
      let data = {
        ime: this.ime,
        prezime: this.prezime,
      };
      try {
        await profile.updatemydata(this.profile[0].id, data).then(() => {
          $("#modal").modal("show");
        });
      } catch (e) {
        console.log(e);
      }
    },
    async changepass() {
      let data = {
        lozinka: this.lozinka,
      };
      try {
        await profile.updatepass(this.profile[0].id, data).then(() => {
          this.lozinka = "";
          $("#modal").modal("show");
        });
      } catch (e) {
        console.log(e);
      }
    },
    async addadmin() {
      let data = {
        ime: this.ime_register,
        prezime: this.prezime_register,
        email: this.email_register,
        lozinka: this.lozinka_register,
        tipkorisnika: "admin",
        datumreg: Date.now(),
      };
      try {
        await login_signup.register_user(data).then(() => {
          this.ime_register = "";
          this.prezime_register = "";
          this.email_register = "";
          this.lozinka_register = "";
        });
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>
<style scoped>
.center {
  margin: 0;
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
}
.background {
  background-color: white;
  padding: 10px;
}
input[type="text"],
input[type="password"],
textarea {
  background-color: #404040;
}
</style>
<template>
  <div class="container h-100 above">
    <div class="d-flex justify-content-center h-100">
      <div class="user_card">
        <div class="d-flex justify-content-center">
          <div class="brand_logo_container">
            <img src="../assets/paw.svg" class="brand_logo" alt="Logo" />
          </div>
        </div>
        <div class="d-flex justify-content-center form_container">
          <form @submit.prevent="login">
            <div class="input-group mb-3">
              <div class="input-group-append">
                <span class="input-group-text"
                  ><i class="fas fa-user"></i
                ></span>
              </div>
              <input
                type="text"
                name=""
                class="form-control input_user"
                value=""
                placeholder="Email"
                v-model="email"
              />
            </div>
            <div class="input-group mb-2">
              <div class="input-group-append">
                <span class="input-group-text"><i class="fas fa-key"></i></span>
              </div>
              <input
                type="password"
                name=""
                class="form-control input_pass"
                value=""
                placeholder="Lozinka"
                v-model="lozinka"
              />
            </div>
            <div class="d-flex justify-content-center mt-3 login_container">
              <button type="submit" name="button" class="btn login_btn">
                PRIJAVA
              </button>
            </div>
          </form>
        </div>

        <div class="mt-4">
          <div class="d-flex justify-content-center links">
            <router-link to="register" class="ml-2">Registracija</router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { login_signup } from "@/services";
import { Auth } from "@/services";
export default {
  name: "login",
  data() {
    return {
      email: "",
      lozinka: "",
    };
  },
  methods: {
    async login() {
      let log = {
        email: this.email,
        lozinka: this.lozinka,
      };
      try {
        await login_signup.login(log);
        if (Auth.state.typeofuser == "korisnik") {
          this.$router.push({ name: "preglednestalih" });
        } else if (Auth.state.typeofuser == "azil") {
          this.$router.push({ name: "preglednestalih" });
        } else if (Auth.state.typeofuser == "admin") {
          this.$router.push({ name: "preglednestalih" });
        }
      } catch (e) {
        console.log(e);
      }
    },
  },
};
</script>
<style>
.logo {
  height: 150px;
  width: 150px;
}
.above {
  margin-top: 100px;
}
.user_card {
  height: 400px;
  width: 350px;
  margin-top: auto;
  margin-bottom: auto;
  background-color: rgba(0, 0, 0, 0.5) !important;
  position: relative;
  display: flex;
  justify-content: center;
  flex-direction: column;
  padding: 10px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  -webkit-box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2),
    0 6px 20px 0 rgba(0, 0, 0, 0.19);
  -moz-box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2),
    0 6px 20px 0 rgba(0, 0, 0, 0.19);
  border-radius: 5px;
}
.brand_logo_container {
  position: absolute;
  height: 170px;
  width: 170px;
  top: -75px;
  border-radius: 50%;
  background-color: rgba(0, 0, 0, 0.2) !important;
  padding: 10px;
  text-align: center;
}
.brand_logo {
  height: 150px;
  width: 150px;
  border-radius: 50%;
}
.form_container {
  margin-top: 100px;
}
.login_btn {
  width: 100%;
  background: #3385ff !important;
  color: white !important;
}
.login_btn:focus {
  box-shadow: none !important;
  outline: 0px !important;
}
.login_container {
  padding: 0 2rem;
}
.input-group-text {
  background: #3385ff !important;
  color: white !important;
  border: 0 !important;
  border-radius: 0.25rem 0 0 0.25rem !important;
}
.input_user,
.input_pass:focus {
  box-shadow: none !important;
  outline: 0px !important;
}
.custom-checkbox .custom-control-input:checked ~ .custom-control-label::before {
  background-color: #3385ff !important;
}
</style>
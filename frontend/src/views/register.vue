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
          <form @submit.prevent="register">
            <div class="input-group mb-3">
              <!-- <div class="input-group-append">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
							</div> -->
              <input
                type="text"
                name=""
                class="form-control input_user"
                value=""
                placeholder="Ime" v-model="ime"
              />
            </div>
            <div class="input-group mb-3">
              <input
                type="text"
                name=""
                class="form-control input_user"
                value=""
                placeholder="Prezime" v-model="prezime"
              />
            </div>
            <div class="input-group mb-3">
              <select
                name="users"
                id="users"
                class="form-control input_user"
                v-model="tipkorisnika"
                @change="onChange($event)"
              >
                <option value="korisnik">Korisnik</option>
                <option value="azil">Azil</option>
              </select>
            </div>
            <div class="input-group mb-3" id="oib">
              <input
                type="number"
                name=""
                class="form-control input_user"
                value=""
                placeholder="OIB" v-model="oib"
              />
            </div>
            <div class="row">
              <div class="input-group mb-3 col-7" id="street">
                <input
                  type="text"
                  name=""
                  class="form-control input_user"
                  value=""
                  placeholder="Ulica" v-model="ulica"
                />
              </div>
              <div class="input-group mb-3 col-5" id="homenum">
                <input
                  type="text"
                  name=""
                  class="form-control input_user"
                  value=""
                  placeholder="Kućni broj" v-model="kucnibr"
                />
              </div>
            </div>
            <div class="row">
              <div class="input-group mb-3 col-6" id="city">
                <input
                  type="text"
                  name=""
                  class="form-control input_user"
                  value=""
                  placeholder="Grad" v-model="grad"
                />
              </div>
              <div class="input-group mb-3 col-6" id="postnum">
                <input
                  type="number"
                  name=""
                  class="form-control input_user"
                  value=""
                  placeholder="Poštanski broj" v-model="postnum"
                />
              </div>
            </div>
            <div class="input-group mb-3" id="emailreg">
              <input
                type="email"
                name=""
                class="form-control input_user"
                value=""
                placeholder="Email" v-model="email"
              />
            </div>
            <div class="input-group mb-3" id="passwordreg">
              <input
                type="password"
                name=""
                class="form-control input_user"
                value=""
                placeholder="Lozinka" v-model="password"
              />
            </div>
            <div class="d-flex justify-content-center mt-3 login_container">
              <button type="submit" name="button" class="btn login_btn">
                REGISTRACIJA
              </button>
            </div>
          </form>
        </div>

        <div class="mt-4">
          <div class="d-flex justify-content-center links">
            <!-- <a href="/register" class="ml-2">Registracija</a> -->
            <router-link to="login">Prijava</router-link>
          </div>
          <!-- <div class="d-flex justify-content-center links">
						<a href="#">Forgot your password?</a>
					</div> -->
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import {login_signup} from "@/services";
export default {
  name: "register",
  data() {
    return {
      tipkorisnika: "",
      postnum:"",
      email:"",
      password:"",
      ime:"",
      prezime:"",
      oib:"",
      ulica:"",
      kucnibr:"",
      grad:""
    };
  },
  methods: {
    onChange(event) {
      console.log(event.target.value);
      if (event.target.value == "korisnik") {
        $("#oib").hide();
        $("#street").hide();
        $("#homenum").hide();
        $("#city").hide();
        $("#postnum").hide();
        $("#emailreg").show();
        $("#passwordreg").show();
      } else {
        $("#oib").show();
        $("#street").show();
        $("#homenum").show();
        $("#city").show();
        $("#postnum").show();
        $("#emailreg").show();
        $("#passwordreg").show();
      }
    },
    async register(){
      if(this.tipkorisnika=="korisnik"){
      let data_user={
        ime:this.ime,
        prezime:this.prezime,
        email:this.email,
        lozinka:this.password,
        tipkorisnika:this.tipkorisnika,
        datumreg:Date.now()
      }
      try{
        await login_signup.register_user(data_user)
        this.$router.push({ name: "login" });
      }catch(error){
        console.log(error)
      }
      }
      else{
        let id;
        let data_user={
        ime:this.ime,
        prezime:this.prezime,
        email:this.email,
        lozinka:this.password,
        tipkorisnika:this.tipkorisnika,
        datumreg:Date.now()
      }
        
      try{
        id=await login_signup.register_user(data_user)
        console.log("id frontend:"+id.data)
      if(id.data!=null){
        let data_asylum={
        oib:this.oib,
        ulica:this.ulica,
        kucnibr:this.kucnibr,
        grad:this.grad,
        postanskibr:this.postnum,
        id:id.data
      }
        try{
        await login_signup.register_asylum(data_asylum)
        this.$router.push({ name: "login" });
      }catch(error){
        console.log(error)
        await login_signup.deleteuser(id.data)
      }
      }
      }catch(error){
        console.log(error)
      }
      }
    }
  },
  mounted() {
    $("#oib").hide();
    $("#street").hide();
    $("#homenum").hide();
    $("#city").hide();
    $("#postnum").hide();
    $("#emailreg").hide();
    $("#passwordreg").hide();
  },
};
</script>
<style scoped>
/* body {
  background: url("../assets/background.png") no-repeat center center fixed;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
} */
.above {
  margin-top: 100px;
}
.user_card {
  height: auto;
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
  /*background: #fff; */
  background-color: rgba(0, 0, 0, 0.2) !important;
  padding: 10px;
  text-align: center;
}
.brand_logo {
  height: 150px;
  width: 150px;
  border-radius: 50%;
  /*border: 2px solid white;*/
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
<style>
/* body{
    background: url('../assets/background.png') no-repeat center center fixed; 
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
} */
</style>
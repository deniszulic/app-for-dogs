<template>
<div class="background" style="overflow: hidden;">
    <div class="row">
        <div class="col-sm-6 dogimg" >
            <!-- <img src="../assets/dog.jpg" class="img-fluid"/> -->
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
        />
      </div>
      <div class="form-group col-md-6">
        <label for="inputPassword4">Prezime</label>
        <input
          type="text"
          class="form-control"
          id="inputPassword4"
        />
      </div>
    </div>
    <div class="form-group" id="adresa">
      <label for="inputAddress">Adresa</label>
      <input
        type="text"
        class="form-control"
        id="inputAddress"
      />
    </div>
    <div class="form-row" id="grad_postnum">
      <div class="form-group col-md-8">
        <label for="inputCity">Grad</label>
        <input type="text" class="form-control" id="inputCity" />
      </div>
      <div class="form-group col-md-4">
        <label for="inputZip">Poštanski br.</label>
        <input type="number" class="form-control" id="inputZip" />
      </div>
    </div>
    <div class="form-row" id="boja_starost">
      <!-- <div class="form-group col-md-6">
        <label for="inputEmail4">Vrsta životinje</label>
        <input
          type="text"
          class="form-control"
          id="inputEmail4"
        />
      </div> -->
      <div class="form-group col-md-6">
        <label for="inputPassword4">Boja</label>
        <input
          type="text"
          class="form-control"
          id="inputPassword4"
        />
      </div>
      <div class="form-group col-md-6">
        <label for="inputEmail4">Starost</label>
        <input
          type="number"
          class="form-control"
          id="inputEmail4"
        />
      </div>
    </div>
    <div class="form-row" id="dlaka_lokacija">
      <div class="form-group col-md-6">
        <label for="inputState">Dlaka</label>
        <select id="inputState" class="form-control">
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
        />
      </div>
      <div class="form-group col-md-6" id="steril">
        <label for="inputState1">Steriliziran / kastriran</label>
        <select id="inputState1" class="form-control">
          <option>Da</option>
          <option>Ne</option>
          <option>Ne znam</option>
        </select>
      </div>
    </div>
    <div class="form-row">
      <div class="form-group col-md-6" id="dateid">
        <label for="inputPassword4">Datum kada je pas izgubljen</label>
        <input
          type="date"
          class="form-control" v-model="datum"
        />
      </div>
      <div class="form-group col-md-6" id="noticeid">
        <label for="napomena">Napomena</label>
        <textarea style="min-width: 100%" v-model="napomena"></textarea>
      </div>
    </div>
    <div class="form-row">
      <div class="form-group col-md-12" id="pictureofdog">
        <label for="dog_img">Slika psa</label><br>
        <input type="file" accept="image/png, image/gif, image/jpeg" ref="fileupload"/>
      </div>
    </div>
    <div class="d-flex justify-content-center" >
    <button type="submit" class="btn btn-primary" id="submitbutton">Pošalji</button></div>
  </form>
  <div class="d-flex justify-content-end" style="margin-right:10px;margin-bottom:5px;" >
    <button type="button" class="rounded-circle" style="background-color:#0275d8;" @click="next" id="rightarrow"><i class="fa-solid fa-arrow-right-long" style="color:white;"></i></button>
    </div>
    <div class="d-flex justify-content-start" style="margin-left:10px;margin-bottom:5px;" >
    <button type="button" class="btn1 rounded-circle" style="background-color:#0275d8;" @click="back" id="leftarrow"><i class="fa-solid fa-arrow-left-long" style="color:white;"></i></button>
    </div>
  </div>
  </div>
  </div>
</template>
<script>
export default {
  name: "nestanakpsa",
  data() {
    return {
      napomena:"",
      datum:""
    }
  },
  mounted(){
  //   $(".btn1").click(function(){
  //   $("#dateid").fadeOut();
  // });
  $("#dateid").hide();
      $("#noticeid").hide();
      $("#pictureofdog").hide();
      $("#steril").hide();
      $("#dogname").hide();
      $("#submitbutton").css('display', 'none');
      $("#leftarrow").css('display', 'none');
  },
  methods:{
    next(){
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
      $("#submitbutton").css('display', 'block');
      $("#rightarrow").css('display', 'none');
      $("#leftarrow").css('display', 'block');
      //$("#submitbutton").fadeIn();
    },
    back(){
      $("#dateid").hide();
      $("#noticeid").hide();
      $("#pictureofdog").hide();
      $("#steril").hide();
      $("#dogname").hide();
      $("#submitbutton").css('display', 'none');
      $("#leftarrow").css('display', 'none');
      //$("#submitbutton").hide();

      $("#name_surname").fadeIn();
      $("#adresa").fadeIn();
      $("#grad_postnum").fadeIn();
      $("#boja_starost").fadeIn();
      $("#dlaka_lokacija").fadeIn();
      $("#rightarrow").css('display', 'block');
    },
    send_data(){
      this.napomena=""
      this.datum=""
      $("#dateid").fadeIn();
      $("#noticeid").fadeIn();
      this.$refs.fileupload.value=null
    }
  }
};
</script>
<style scoped>
    .background{
        background-color: #fff;
        margin-top:50px;
        margin-bottom: 50px;

         /* margin: 0;
  position: absolute;
  top: 50%;
  left: 50%;
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%); */

  /*margin: 0;
  position: absolute;
  top: 50%;
  -ms-transform: translateY(-50%);
  transform: translateY(-50%);*/
    }
    form{
        padding: 20px;
    }
    .img{
        /* width:100%;
    height: 100%; */
    }
    .dogimg{
      background: url("../assets/dog.jpg") top left/cover no-repeat;
      display: block;
    width: 100%;
    height: auto;
    background-position: center center;
    background-size: cover;

    /* display: block;
    width: 100%;
    padding-top: 400px;
    background-position: center center;
    overflow: hidden;display:block;   */
    }
    @media (max-width: 767px) {
      .dogimg {
    /* display: block; */
    width: 100%;
    padding-top: 400px;
    background-position: center center;
  }
    }
</style>
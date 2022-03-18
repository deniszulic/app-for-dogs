<template>
<div class="background" style="overflow: hidden;">
    <div class="row">
        <div class="col-sm-6 dogimg" >
            <!-- <img src="../assets/dog.jpg" class="img-fluid"/> -->
        </div>
        <div class="col-sm-6">
  <form>
    <div class="form-row">
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
    <div class="form-group">
      <label for="inputAddress">Adresa</label>
      <input
        type="text"
        class="form-control"
        id="inputAddress"
      />
    </div>
    <div class="form-row">
      <div class="form-group col-md-8">
        <label for="inputCity">Grad</label>
        <input type="text" class="form-control" id="inputCity" />
      </div>
      <div class="form-group col-md-4">
        <label for="inputZip">Poštanski br.</label>
        <input type="number" class="form-control" id="inputZip" />
      </div>
    </div>
    <div class="form-row">
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
    <div class="form-row">
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
      <div class="form-group col-md-6">
        <label for="inputPassword4">Ime psa</label>
        <input
          type="text"
          class="form-control"
          id="inputPassword4"
        />
      </div>
      <div class="form-group col-md-6">
        <label for="inputState1">Steriliziran / kastriran</label>
        <select id="inputState1" class="form-control">
          <option>Da</option>
          <option>Ne</option>
          <option>Ne znam</option>
        </select>
      </div>
    </div>
    <div class="form-row">
      <div class="form-group col-md-6">
        <label for="inputPassword4">Datum kada je pas izgubljen</label>
        <input
          type="date"
          class="form-control"
          id="inputPassword4"
        />
      </div>
      <div class="form-group col-md-6">
        <label for="napomena">Napomena</label>
        <textarea style="min-width: 100%"></textarea>
      </div>
    </div>
    <div class="form-row">
      <div class="form-group col-md-12">
        <label for="dog_img">Slika psa</label><br>
        <input type="file" accept="image/png, image/gif, image/jpeg" />
      </div>
    </div>
    <div class="d-flex justify-content-center" >
    <button type="submit" class="btn btn-primary" >Pošalji</button></div>
  </form>
  </div>
  </div>
  </div>
</template>
<script>
export default {
  name: "nestanakpsa",
};
</script>
<style scoped>
    .background{
        background-color: #fff;        
        margin-top:50px;

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
    background-position: center center;  

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
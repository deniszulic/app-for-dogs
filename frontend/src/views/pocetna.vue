<template>
  <div class="center">
    <div
      id="carouselExampleIndicators"
      class="carousel slide"
      data-ride="carousel"
    >
      <ol class="carousel-indicators">
        <li
          data-target="#carouselExampleIndicators"
          data-slide-to="0"
          class="active"
        ></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img
            class="d-block w-50"
            style="margin: auto"
            src="../assets/firstpic.jpeg"
            alt="First slide"
          />
          <div class="carousel-caption d-none d-md-block">
            <h5 style="color: black">
              Dobrodošao na aplikaciju za udomljavanje i prijavu nestanka psa
            </h5>
          </div>
        </div>
        <div class="carousel-item">
          <img
            class="d-block w-50"
            style="margin: auto"
            src="../assets/secondpic.jpeg"
            alt="Second slide"
          />
          <div class="carousel-caption d-none d-md-block">
            <h5 style="color: black">Registracija</h5>
            <p style="color: black">
              U ovoj aplikaciji se mogu registrirati korisnici i azili
            </p>
          </div>
        </div>
        <div class="carousel-item">
          <img
            class="d-block w-50"
            style="margin: auto"
            src="../assets/thirdpic.jpeg"
            alt="Third slide"
          />
          <div class="carousel-caption d-none d-md-block">
            <h5 style="color: black">Oglasi</h5>
            <p style="color: black">
              Pregledajte ili postavite oglase za udomljavanje ili prijavu
              nestanka psa
            </p>
          </div>
        </div>
      </div>
      <a
        class="carousel-control-prev"
        href="#carouselExampleIndicators"
        role="button"
        data-slide="prev"
      >
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a
        class="carousel-control-next"
        href="#carouselExampleIndicators"
        role="button"
        data-slide="next"
      >
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div>
  </div>
</template>
<style scoped>
.center {
  margin: 0;
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
}
</style>
<template>
<div>
<div class="d-flex justify-content-center" style="margin-top:10px;margin-bottom:-32px;">
<article class="postcard light blue" v-if="showcomments==null">
			<a class="postcard__img_link"  v-if="data.url_slike!=null" href="javascript:void(0)">
				<img class="postcard__img" :src="data.url_slike" alt="Image Title" @click="$emit('change', data.url_slike) "/>
			</a>
			<div class="postcard__text t-dark">
				<h1 class="postcard__title blue">Ime psa: {{data.ime_psa}}</h1>
				<div class="postcard__subtitle small">
					<time>
						<i class="fas fa-calendar-alt mr-2"></i>Postavljeno: {{ moment(parseInt(data.postavljeno)).format('DD.MM.YYYY')}}
					</time>
				</div>
				<div class="postcard__bar"></div>
				<div class="postcard__preview-txt">
                    <div class="container">
  <div class="row">
    <div class="col-sm">
      <p>Ime vlasnika:{{data.ime}}</p>
    </div>
    <div class="col-sm">
      <p>Prezime vlasnika:{{data.prezime}}</p>
    </div>
    <div class="col-sm">
      <p>Adresa vlasnika:{{data.adresa}}</p>
    </div>
  </div>
  <div class="row">
    <div class="col-sm">
      <p>Telefonski broj:{{data.telefonskibr}}</p>
    </div>
    <div class="col-sm">
      <p>Grad:{{data.grad}}</p>
    </div>
    <div class="col-sm">
      <p>Poštanski broj:{{data.postanski_broj}}</p>
    </div>
  </div>
  <div class="row">
    <div class="col-sm">
      <p>Boja psa:{{data.boja}}</p>
    </div>
    <div class="col-sm">
      <p>Starost:{{data.starost}}</p>
    </div>
    <div class="col-sm">
      <p>Dlaka:{{data.dlaka}}</p>
    </div>
  </div>
  <div class="row">
    <div class="col-sm">
      <p>Vet. lokacija:{{data.vet_lokacija}}</p>
    </div>
    <div class="col-sm">
      <p>Spol:{{data.spol}}</p>
    </div>
	<div class="col-sm">
      <p>Pasmina:{{data.pasmina}}</p>
    </div>
  </div>
</div>             
                </div>
				<ul class="postcard__tagbox">
					<li class="tag__item"><i class="fas fa-clock mr-2"></i>Izgubljen: {{moment(data.datum_izgubljen).format("DD.MM.YYYY")}}</li>
				</ul>
				<div class="d-flex justify-content-center">
			<button class="btn btn-primary" @click="opencard(data.id)">Komentiraj</button></div>
			</div>
		</article>
		<article class="postcard light blue" v-if="showcomments">
			<a class="postcard__img_link"  v-if="data[0].url_slike!=null" href="javascript:void(0)">
				<img class="postcard__img" :src="data[0].url_slike" alt="Image Title" @click="openimg(data[0].url_slike)"/>
			</a>
			<div class="postcard__text t-dark">
				<h1 class="postcard__title blue">Ime psa: {{data[0].ime_psa}}</h1>
				<div class="postcard__subtitle small">
					<time>
						<i class="fas fa-calendar-alt mr-2"></i>Postavljeno: {{ moment(parseInt(data[0].postavljeno)).format('lll') }}
					</time>
				</div>
				<div class="postcard__bar"></div>
				<div class="postcard__preview-txt">
                    <div class="container">
  <div class="row">
    <div class="col-sm">
      <p>Ime vlasnika:{{data[0].ime}}</p>
    </div>
    <div class="col-sm">
      <p>Prezime vlasnika:{{data[0].prezime}}</p>
    </div>
    <div class="col-sm">
      <p>Adresa vlasnika:{{data[0].adresa}}</p>
    </div>
  </div>
  <div class="row">
    <div class="col-sm">
      <p>Telefonski broj:{{data[0].telefonskibr}}</p>
    </div>
    <div class="col-sm">
      <p>Grad:{{data[0].grad}}</p>
    </div>
    <div class="col-sm">
      <p>Poštanski broj:{{data[0].postanski_broj}}</p>
    </div>
  </div>
  <div class="row">
    <div class="col-sm">
      <p>Boja psa:{{data[0].boja}}</p>
    </div>
    <div class="col-sm">
      <p>Starost:{{data[0].starost}}</p>
    </div>
    <div class="col-sm">
      <p>Dlaka:{{data[0].dlaka}}</p>
    </div>
  </div>
  <div class="row">
    <div class="col-sm">
      <p>Vet. lokacija:{{data[0].vet_lokacija}}</p>
    </div>
    <div class="col-sm">
      <p>Spol:{{data[0].spol}}</p>
    </div>
	<div class="col-sm">
      <p>Pasmina:{{data[0].pasmina}}</p>
    </div>
  </div>
  <div class="row">
    <div class="col-sm">
      <p>Napomena:{{data[0].napomena}}</p>
    </div>
  </div>
</div>             
                </div>
				<ul class="postcard__tagbox">
					<li class="tag__item"><i class="fas fa-clock mr-2"></i>Izgubljen: {{moment(data[0].datum_izgubljen).format("DD.MM.YYYY")}}</li>
				</ul>
			<div class="comments list-group">
          </div>
		  
			</div>
		</article>
		<div
            class="modal fade"
            id="pictureModal1"
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
</div>
 <a :key="a.postavljeno" v-for="a in podaci"
              href="javascript:void(0)"
              class="animate list-group-item list-group-item-action flex-column align-items-start light blue"
            >
              <div class="d-flex w-100 justify-content-between">
				<small>{{a.email}}</small>
				<small>{{moment(parseInt(a.postavljeno)).format('lll')}}</small>
				<a @click="deletecom(a.id)"
                  href="javascript:void(0)"
                >Obriši</a>
              </div>
              <!-- {{ c.comment }} -->
			  {{a.komentar}}
            </a>
			<form @submit.prevent="posaljikomentar" class="form-inline" style="margin-top:10px;" v-if="showcomments">
              <input
                v-model="komentar"
                type="text"
                class="form-control mb-2 mr-sm-2"
                id="imageUrl"
                placeholder="Komentar"
              />
            <button type="submit" class="btn btn-primary form-control mb-2 mr-sm-2">Pošalji</button>
          </form></div>
</template>
<script>
//import store from "@/store.js";
import moment from 'moment';
import { dog_data } from "@/services";
import {Auth} from "@/services";
export default {
    props: ["data", "showcomments","com"],
    data(){
        return{
            moment,
			url:"",
			komentar:"",
			podaci:this.com
        }
    },
    mounted(){
    moment.locale('hr')
    },
	created() {
		if(this.showcomments==true){
    this.getcomm();
		}
  },
  watch: {
    $route: "getcomm",
  },
    methods:{
	opencard(data){
		this.$router.push({ path: `mojioglasi/${data}` });
	},
	openimg(data){
		this.url=data
		$("#pictureModal1").modal("show");
	},
	async getcomm(){
		this.podaci=await dog_data.getcomments(this.data[0].id)
	},
	async deletecom(data){
		try{
			await dog_data.deletecomment(data)
			this.getcomm()
		}catch(e){
			console.log(e)
		}
	},
	async posaljikomentar(){
		if(this.komentar!=""){
		let data={
			komentar:this.komentar,
			postavljeno:Date.now(),
			Nestanak_id:this.data[0].id,
			Korisnik_id:Auth.state.id
		}
		try{
			await dog_data.comments(data)
			this.getcomm()
			this.komentar=""
		}catch(e){
			console.log(e)
		}
		}
	}
    }
}
</script>
<style scoped>
a:hover{
	color:blue
}
.back{
	background: rgba(83, 83, 83, 0.4);
}
</style>
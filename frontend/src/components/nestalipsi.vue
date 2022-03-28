<template>
<div>
<div class="d-flex justify-content-center" style="margin-top:10px;margin-bottom:-32px;">
    <!-- <div class="card">
        <div v-if="data.url_slike!=null">
  <img class="card-img-top" :src="data.url_slike" alt="Card image cap">
  </div>
  <div class="card-body">
    <h5 class="card-title">Pas je nestao {{moment(data.datum_izgubljen).format("DD.MM.YYYY")}}</h5>
    <p class="card-text">
        Ime vlasnika:{{data.ime}} <br> Prezime vlasnika{{data.prezime}} <br> Adresa vlasnika:{{data.adresa}}<br>Telefonski broj:{{data.telefonskibr}}
        <br>Grad:{{data.grad}}<br>Poštanski broj:{{data.postanski_broj}}<br>Boja psa:{{data.boja}}<br>Starost:{{data.starost}}<br>Dlaka:{{data.dlaka}}
        <br>Veterinarska lokacija:{{data.vet_lokacija}}<br>Ime psa:{{data.ime_psa}}<br>Spol:{{data.spol}}<br>Postavljeno:{{moment.unix(data.postavljeno).toDate()}}
    </p>
    <a href="#" class="btn btn-primary">Go somewhere</a>
  </div>
</div> -->

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
      <!-- <p>Ime psa:{{data.ime_psa}}</p> -->
      <p>Spol:{{data.spol}}</p>
    </div>
	<div class="col-sm">
      <p>Pasmina:{{data.pasmina}}</p>
    </div>
    <!-- <div class="col-sm">
      <p>Spol:{{data.spol}}</p>
    </div> -->
  </div>
</div>             
                </div>
				<ul class="postcard__tagbox">
					<!-- <li class="tag__item"><i class="fas fa-tag mr-2"></i>Podcast</li> -->
					<li class="tag__item"><i class="fas fa-clock mr-2"></i>Izgubljen: {{moment(data.datum_izgubljen).format("DD.MM.YYYY")}}</li>
					<!-- <li class="tag__item play blue">
						<a href="#"><i class="fas fa-play mr-2"></i>Play Episode</a>
					</li> -->
				</ul>
				<div class="d-flex justify-content-center" v-if="Auth.state.email!=data.email">
			<button class="btn btn-primary" style="margin-right:10px;" @click="opencard(data.id)">Komentiraj</button>
			<button class="btn btn-success" @click="$emit('form_missing_dog', data.id)">Pronašao sam psa</button>
			</div>
			<div class="d-flex justify-content-center" v-else>
			<button class="btn btn-primary" @click="opencard(data.id)">Komentiraj</button>
			</div>
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
            <!-- <a
              :key="c.posted_at"
              v-for="c in g.comments"
              href="#"
              class="animate list-group-item list-group-item-action flex-column align-items-start"
            > -->

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
                <!-- <small>{{ c.email }}</small> -->
				<small>{{a.email}}</small>
				<small>{{moment(parseInt(a.postavljeno)).format('lll')}}</small>
                <!-- <a
                  v-if="(store.koremail == g.email) || (c.email == store.koremail) || (store.tipKorisnika=='admin')"
                  @click="removeComment(c.id)"
                  href="#"
                >Obriši</a> -->
				<a @click="deletecom(a.id)" v-if="(email==data[0].email)"
                  href="javascript:void(0)"
                >Obriši</a>
				<a @click="deletecom(a.id)" v-if="(email!=data[0].email) && email==a.email"
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
			podaci:this.com,
			email:Auth.state.email,
			Auth
        }
    },
//     computed: {
//     timestamp : function(data){
//         let date = new Date(parseInt(data))
//         //return date.toUTCString()
//         return date.getUTCDate()+"."+date.getUTCMonth()+"."+date.getUTCFullYear()
//     }
//   },
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
//         timestamp(data){
//         let date = new Date(parseInt(data))
//         let month = date.getUTCMonth()+1
// 		let hours=date.getUTCHours()+1
// 		if(hours==24) hours=0;
//         return date.getUTCDate()+"."+month+"."+date.getUTCFullYear()+" "+this.addzero(hours)+":"+this.addzero(date.getUTCMinutes());
//     },
// 	addzero(i) {
//   if (i < 10) {i = "0" + i}
//   return i;
// },
    // showimg(data){
    //     //this.url=data
    //     // this.store.url=data
    //     // console.log(this.url)
    //     $("#picusrModal").modal("show");
    // }
	opencard(data){
		this.$router.push({ path: `preglednestalih/${data}` });
	},
	openimg(data){
		this.url=data
		$("#pictureModal1").modal("show");
	},
	async getcomm(){
		//this.comments=await dog_data.getcomments(this.data[0].id)
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
/* p{
    white-space: nowrap ;
} */
/* @import url("https://fonts.googleapis.com/css2?family=Baloo+2&display=swap");
 .light {
	 background: #f3f5f7;
}
 a, a:hover {
	 text-decoration: none;
	 transition: color 0.3s ease-in-out;
}
 .postcard {
	 flex-wrap: wrap;
	 display: flex;
	 box-shadow: 0 4px 21px -12px rgba(0, 0, 0, 0.66);
	 border-radius: 10px;
	 margin: 0 0 2rem 0;
	 overflow: hidden;
	 position: relative;
	 color: #fff;
     width:1200px;
}
 .postcard.dark {
	 background-color: #18151f;
}
 .postcard.light {
	 background-color: #e1e5ea;
}
 .postcard .t-dark {
	 color: #18151f;
}
 .postcard a {
	 color: inherit;
}
 .postcard h1, .postcard .h1 {
	 margin-bottom: 0.5rem;
	 font-weight: 500;
	 line-height: 1.2;
}
 .postcard .small {
	 font-size: 80%;
}
 .postcard .postcard__title {
	 font-size: 1.75rem;
}
 .postcard .postcard__img {
	 max-height: 180px;
	 width: 100%;
	 object-fit: cover;
	 position: relative;
}
 .postcard .postcard__img_link {
	 display: contents;
}
 .postcard .postcard__bar {
	 width: 50px;
	 height: 10px;
	 margin: 10px 0;
	 border-radius: 5px;
	 background-color: #424242;
	 transition: width 0.2s ease;
}
 .postcard .postcard__text {
	 padding: 1.5rem;
	 position: relative;
	 display: flex;
	 flex-direction: column;
}
 .postcard .postcard__preview-txt {
	 overflow: hidden;
	 text-overflow: ellipsis;
	 text-align: justify;
	 height: 100%;
}
 .postcard .postcard__tagbox {
	 display: flex;
	 flex-flow: row wrap;
	 font-size: 14px;
	 margin: 20px 0 0 0;
	 padding: 0;
	 justify-content: center;
}
 .postcard .postcard__tagbox .tag__item {
	 display: inline-block;
	 background: rgba(83, 83, 83, 0.4);
	 border-radius: 3px;
	 padding: 2.5px 10px;
	 margin: 0 5px 5px 0;
	 cursor: default;
	 user-select: none;
	 transition: background-color 0.3s;
}
 .postcard .postcard__tagbox .tag__item:hover {
	 background: rgba(83, 83, 83, 0.8);
}
 .postcard:before {
	 content: "";
	 position: absolute;
	 top: 0;
	 right: 0;
	 bottom: 0;
	 left: 0;
	 background-image: linear-gradient(-70deg, #424242, transparent 50%);
	 opacity: 1;
	 border-radius: 10px;
}
 .postcard:hover .postcard__bar {
	 width: 100px;
}
 @media screen and (min-width: 769px) {
	 .postcard {
		 flex-wrap: inherit;
	}
	 .postcard .postcard__title {
		 font-size: 2rem;
	}
	 .postcard .postcard__tagbox {
		 justify-content: start;
	}
	 .postcard .postcard__img {
		 max-width: 300px;
		 max-height: 100%;
		 transition: transform 0.3s ease;
	}
	 .postcard .postcard__text {
		 padding: 3rem;
		 width: 100%;
	} 
	  .postcard .media.postcard__text:before {
		 content: "";
		 position: absolute;
		 display: block;
		 background: #18151f;
		 top: -20%;
		 height: 130%;
		 width: 55px;
	}
	 .postcard:hover .postcard__img {
		 transform: scale(1.1);
	}
	 .postcard:nth-child(2n+1) {
		 flex-direction: row;
	}
	 .postcard:nth-child(2n+0) {
		 flex-direction: row-reverse;
	}
	 .postcard:nth-child(2n+1) .postcard__text::before {
		 left: -12px !important;
		 transform: rotate(4deg);
	}
	 .postcard:nth-child(2n+0) .postcard__text::before {
		 right: -12px !important;
		 transform: rotate(-4deg);
	}
}
 @media screen and (min-width: 1024px) {
	 .postcard__text {
		 padding: 2rem 3.5rem;
	}
	 .postcard__text:before {
		 content: "";
		 position: absolute;
		 display: block;
		 top: -20%;
		 height: 130%;
		 width: 48px;
	}
	 .postcard.dark .postcard__text:before {
		 background: #18151f;
	}
	 .postcard.light .postcard__text:before {
		 background: #e1e5ea;
	}
}
 .postcard .postcard__tagbox .blue.play:hover {
	 background: #0076bd;
}
 .blue .postcard__title:hover {
	 color: #0076bd;
}
 .blue .postcard__bar {
	 background-color: #0076bd;
}
 .blue::before {
	 background-image: linear-gradient(-30deg, rgba(0, 118, 189, 0.1), transparent 50%);
}
 .blue:nth-child(2n)::before {
	 background-image: linear-gradient(30deg, rgba(0, 118, 189, 0.1), transparent 50%);
}
 @media screen and (min-width: 769px) {
	 .blue::before {
		 background-image: linear-gradient(-80deg, rgba(0, 118, 189, 0.1), transparent 50%);
	}
	 .blue:nth-child(2n)::before {
		 background-image: linear-gradient(80deg, rgba(0, 118, 189, 0.1), transparent 50%);
	}
} */
</style>
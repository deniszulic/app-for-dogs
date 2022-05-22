<template>
<div class="table-responsive">
        <table class="table table-hover table-dark">
  <thead>
    <tr>
      <th scope="col">Ime</th>
      <th scope="col">Prezime</th>
      <th scope="col">Adresa</th>
      <th scope="col">Grad</th>
      <th scope="col">Poštanski broj</th>
      <th scope="col">Email</th>
      <th scope="col">Postavljeno</th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="data in filtrirano" :key="data.postavljeno">
      <th scope="row">{{data.ime}}</th>
      <td>{{data.prezime}}</td>
      <td>{{data.adresa}}</td>
      <td>{{data.grad}}</td>
      <td>{{data.postanski_broj}}</td>
      <td>{{data.email}}</td>
      <td>{{moment(parseInt(data.postavljeno)).format('lll')}}</td>
      <td><button class="btn btn-primary rounded-circle" @click="$emit('myreportmissingdog', data)"><i class="fa-solid fa-circle-info"></i></button></td>
    </tr>
  </tbody>
</table>
</div>
</template>
<script>
import moment from 'moment';
export default {
  props:["data", "search"],
  data(){
    return{
      moment
    }
  },
  computed: {
    filtrirano() {
      return this.data.filter((post) => {
        return post.email.toLowerCase()
          .includes(this.search.toLowerCase());
      });
    },
  },
  mounted(){
    moment.locale('hr')
  },
}
</script>
<template>
<div class="table-responsive">
        <table class="table table-hover table-dark">
  <thead>
    <tr>
      <th scope="col">Ime</th>
      <th scope="col">Prezime</th>
      <th scope="col">Adresa pronalaksa</th>
      <th scope="col">Adresa za pokupiti psa</th>
      <th scope="col">Napomena</th>
      <th scope="col">Postavljeno</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="data in data" :key="data.postavljeno">
      <th scope="row">{{data.ime}}</th>
      <td>{{data.prezime}}</td>
      <td>{{data.adresa_pronalaska}}</td>
      <td>{{data.adresa_za_pokupiti_psa}}</td>
      <td>{{data.napomena}}</td>
      <td>{{moment(parseInt(data.postavljeno)).format('lll')}}</td>
    </tr>
  </tbody>
</table>
</div>
</template>
<script>
import moment from 'moment';
export default {
  props:["data"],
  data(){
    return{
      moment
    }
  },
  mounted(){
    moment.locale('hr')
  },
}
</script>
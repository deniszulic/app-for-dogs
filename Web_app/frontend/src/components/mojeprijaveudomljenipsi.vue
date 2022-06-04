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
          <th scope="col">Kontakt</th>
          <th scope="col">Razlog prijave</th>
          <th scope="col">Postavljeno</th>
          <th scope="col">Prihvaćeno</th>
          <th scope="col"></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="data in data" :key="data.postavljeno">
          <th scope="row">{{ data.ime }}</th>
          <td>{{ data.prezime }}</td>
          <td>{{ data.adresa }}</td>
          <td>{{ data.grad }}</td>
          <td>{{ data.postanski_broj }}</td>
          <td>{{ data.kontakt }}</td>
          <td>{{ data.razlog_prijave }}</td>
          <td>{{ moment(parseInt(data.postavljeno)).format("lll") }}</td>
          <td v-if="data.prihvaceno == 'Obrada'">
            <span class="badge badge-pill badge-warning">{{
              data.prihvaceno
            }}</span>
          </td>
          <td v-if="data.prihvaceno == 'False'">
            <span class="badge badge-pill badge-danger">{{
              data.prihvaceno
            }}</span>
          </td>
          <td v-if="data.prihvaceno == 'True'">
            <span class="badge badge-pill badge-success">{{
              data.prihvaceno
            }}</span>
          </td>
          <td>
            <button
              class="btn btn-primary rounded-circle"
              @click="$emit('myreports_adopteddogs', data)"
            >
              <i class="fa-solid fa-circle-info"></i>
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
import moment from "moment";
export default {
  props: ["data"],
  data() {
    return {
      moment,
    };
  },
  mounted() {
    moment.locale("hr");
  },
};
</script>
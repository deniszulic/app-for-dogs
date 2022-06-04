<template>
  <div class="table-responsive">
    <table class="table table-hover table-dark">
      <thead>
        <tr>
          <th scope="col">Ime psa</th>
          <th scope="col">Pasmina</th>
          <th scope="col">Starost</th>
          <th scope="col">Tel. br.</th>
          <th scope="col">Postavljeno</th>
          <th scope="col"></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="data in filtrirano" :key="data.postavljeno">
          <th scope="row">{{ data.ime_psa }}</th>
          <td>{{ data.pasmina }}</td>
          <td>{{ data.starost }}</td>
          <td>{{ data.telefonskibr }}</td>
          <td>{{ moment(parseInt(data.postavljeno)).format("lll") }}</td>
          <td>
            <button
              class="btn btn-primary rounded-circle"
              @click="$emit('myreportmissingdog', data)"
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
  props: ["data", "search"],
  data() {
    return {
      moment,
    };
  },
  computed: {
    filtrirano() {
      return this.data.filter((post) => {
        return post.email.toLowerCase().includes(this.search.toLowerCase());
      });
    },
  },
  mounted() {
    moment.locale("hr");
  },
};
</script>
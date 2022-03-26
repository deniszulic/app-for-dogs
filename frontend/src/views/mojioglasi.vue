<template>
    <div>
        <div class="d-flex justify-content-center" style="margin-top:10px;">
    <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
  <li class="nav-item">
    <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab" aria-controls="pills-home" aria-selected="true">Nestanak psa</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-profile" role="tab" aria-controls="pills-profile" aria-selected="false">Udomljeni psi</a>
  </li>
</ul>
</div>
<div class="tab-content" id="pills-tabContent">
  <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
      <mojinestalipsi v-for="data in missingdogs" :key="data.id" :data="data" @change="listen" />

        <div
            class="modal fade"
            id="pictureModal"
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
  <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">.s..</div>
</div>
    </div>
</template>
<script>
import {Auth} from "@/services";
import { dog_data } from "@/services";
import mojinestalipsi from "@/components/mojinestalipsi.vue";
export default {
    name:"mojioglasi",
    components: {
    mojinestalipsi
  },
    data(){
      return{
          missingdogs:[],
          url:""
      }
  },
  created() {
    this.getdataa();
  },
  watch: {
    $route: "getdataa",
  },
  methods:{
      async getdataa() {
      try {
        this.missingdogs=await dog_data.getmymissingdogs(Auth.state.id);
      } catch (e) {
        this.errormsg = e.message;
        console.log(e)
      }
    },
    listen(event){
      this.url=event
      $("#pictureModal").modal("show");
    }
  }
}
</script>
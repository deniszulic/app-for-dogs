<template>
    <div>
        <nestalipsi v-for="data in missingdogs" :key="data.id" :data="data" @change="listen" />

        <div
            class="modal fade"
            id="pictureModal"
            tabindex="-1"
            role="dialog"
            aria-labelledby="pictureModalLabel"
            aria-hidden="true"
          >
            <div class="modal-dialog" role="document">
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
</template>
<script>
import { dog_data } from "@/services";
import nestalipsi from "@/components/nestalipsi.vue";
export default {
    name:"preglednestalih",
    components: {
    nestalipsi
  },
  data(){
      return{
          missingdogs:[],
          url:""
      }
  },
  created() {
    this.getdata();
  },
  watch: {
    $route: "getdata",
  },
  methods:{
      async getdata() {
      try {
        this.missingdogs = await dog_data.getmissingdogs();
      } catch (e) {
        this.errormsg = e.message;
      }
    },
    listen(event){
      this.url=event
      $("#pictureModal").modal("show");
    }
  }
}
</script>
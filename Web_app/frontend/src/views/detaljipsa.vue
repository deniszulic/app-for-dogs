<template>
  <div>
    <div v-if="data">
      <button @click="back" class="btn btn-primary" style="margin-top: 5px">
        <i class="fa-solid fa-arrow-left-long"></i> Nazad
      </button>
      <nestalipsi :data="data" :showcomments="true" :com="com" />
    </div>
    <div v-else>Something went wrong</div>
  </div>
</template>
<script>
import nestalipsi from "@/components/nestalipsi.vue";
import { dog_data } from "@/services";
export default {
  props: ["id"],
  name: "dog-details",
  components: {
    nestalipsi,
  },
  data() {
    return {
      data: null,
      com: null,
    };
  },
  async mounted() {
    try {
      this.data = await dog_data.getmissingdogsid(this.id);
      this.com = await dog_data.getcomments(this.id);
    } catch (e) {
      console.log(e);
    }
  },
  methods: {
    back() {
      this.showcomments = false;
      window.history.back();
    },
  },
};
</script>
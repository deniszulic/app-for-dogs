<template>
<div>
    <div v-if="data">
        <button @click="back">Nazad</button>
        <nestalipsi :data="data" :showcomments="true" :com="com" />
    </div>
    <div v-else>
        among
    </div></div>
</template>
<script>
import nestalipsi from "@/components/nestalipsi.vue";
import { dog_data } from "@/services";
export default {
    props: ["id",],
    name: 'dog-details',
    components: {
    nestalipsi
  },
  data() {
    return {
      data: null,
      com:null
    };
  },
  async mounted(){
      this.data=await dog_data.getmissingdogsid(this.id)
      this.com=await dog_data.getcomments(this.id)
  },
  methods:{
      back(){
          this.showcomments=false
          window.history.back()
      }
  }
}
</script>
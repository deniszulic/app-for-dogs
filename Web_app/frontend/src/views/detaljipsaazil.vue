<template>
<div>
    <div v-if="data">
        <button @click="back" class="btn btn-primary" style="margin-top:5px;"><i class="fa-solid fa-arrow-left-long"></i> Nazad</button>
        <nestalipsiazil :data="data" :showcomments="true" :com="com" />
    </div>
    <div v-else>
        Something went wrong
    </div></div>
</template>
<script>
import nestalipsiazil from "@/components/nestalipsiazil.vue";
import { dog_data } from "@/services";
export default {
    props: ["id",],
    name: 'dog-details-shelter',
    components: {
    nestalipsiazil
  },
  data() {
    return {
      data: null,
      com:null
    };
  },
  async mounted(){
      try{
        this.data=await dog_data.getspecificdogshelter(this.id)
        this.com=await dog_data.getcomments(this.id)
      }catch(e){
          console.log(e)
      }
  },
  methods:{
      back(){
          this.showcomments=false
          window.history.back()
      }
  }
}
</script>
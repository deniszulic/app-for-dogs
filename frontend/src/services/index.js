import axios from "axios";
import store from "@/store"

let Service = axios.create({
  baseURL: "http://localhost:3000/",
  timeout: 1000,
});

let login_signup={
    async register_user(data) {
        return await Service.post("/register", data);
      },
      async register_asylum(data) {
        return await Service.post("/registerasylum", data);
      },
      async deleteuser(id){
        return await Service.delete(`delete/${id}`)
      },
      async login(data){
        let response= await Service.post("/login", data);
        let user=response.data
        localStorage.setItem("user", JSON.stringify(user))
      }
}
let dog_data={
  async missing_dog(data) {
    return await Service.post("/dogmissing", data);
  },
  async getmissingdogs(){
    let response = await Service.get('/getmissingdogs');
    return response.data
  }
}
let Auth = {
  getUser(){
    return JSON.parse(localStorage.getItem("user"))
},
logout(){
    localStorage.removeItem('user');
},
state:{
  get name(){
    let user=Auth.getUser();
    if(user){
      return user[0].ime;
    }
  },
  get surname(){
    let user=Auth.getUser();
    if(user){
      return user[0].prezime;
    }
  },
  get id(){
    let user=Auth.getUser();
    if(user){
      return user[0].id;
    }
  },
  get email(){
    let user=Auth.getUser();
    if(user){
      return user[0].email;
    }
  },
  get typeofuser(){
    let user=Auth.getUser();
    if(user){
      store.tipkorisnika=user[0].tipkorisnika;
      return user[0].tipkorisnika;
    }
  },
}
}

export {login_signup, Auth, dog_data};
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
  async adopt_dog(data) {
    return await Service.post("/adoptdog", data);
  },
  async getmissingdogs(){
    let response = await Service.get('/getmissingdogs');
    return response.data
  },
  async getadopteddogs(){
    let response = await Service.get('/getadopteddogs');
    return response.data
  },
  async getadopteddogsadmin(){
    let response = await Service.get('/getadopteddogsadmin');
    return response.data
  },
  async getadopteddogsadminshelter(){
    let response = await Service.get('/getadopteddogsadminshelter');
    return response.data
  },
  async getshelters(){
    let response = await Service.get('/getshelters');
    return response.data
  },
  async getmissingdogsadmin(){
    let response = await Service.get('/getmissingdogsadmin');
    return response.data
  },
  async getmissingdogsid(id){
    let response = await Service.get(`/getmissingdogs/${id}`);
    return response.data
  },
  async getspecificdogshelter(id){
    let response = await Service.get(`/getspecificdogshelter/${id}`);
    return response.data
  },
  async comments(data){
    return await Service.post("/getcommentsfordog", data);
  },
  async getcomments(id){
    let response = await Service.get(`/getcomments/${id}`);
    return response.data
  },
  async deletecomment(id){
    return await Service.delete(`deletecomment/${id}`)
  },
  async getmymissingdogs(email){
    let response = await Service.get(`/getmymissingdogs/${email}`);
    return response.data
  },
  async getmyadopteddogs(email){
    let response = await Service.get(`/getmyadopteddogs/${email}`);
    return response.data
  },
  async updatemydataadopteddog(id, data){
    return await Service.patch(`/updateadopteddog/${id}`, data)
  },
  async updatemydatamissingddog(id, data){
    return await Service.patch(`/updatemissingdog/${id}`, data)
  },
  async report_missing_dog(data) {
    return await Service.post("/reportmissingdog", data);
  },
  async adopt_dog_application(data) {
    return await Service.post("/adoptdogapplication", data);
  },
  async getmyreportdisapp(email) {
    let response = await Service.get(`/getmyreportdisapp/${email}`);
    return response.data
  },
  async getmyreportadoptdog(email) {
    let response = await Service.get(`/getmyreportadoptdog/${email}`);
    return response.data
  },
  async update_adoptdog_report(id, data){
    return await Service.patch(`/updateadoptdogreport/${id}`, data)
  },
  async reportsonmyadopteddogs(email) {
    let response = await Service.get(`/reportsonmyadopteddogs/${email}`);
    return response.data
  },
  async update_my_adoptdog_report(id, data){
    return await Service.patch(`/updatemyadopteddogreports/${id}`, data)
  },
  async update_my_missingdog_report(id, data){
    return await Service.patch(`/updatemymissingdogreport/${id}`, data)
  },
  async reportsonmymissingdogs(email) {
    let response = await Service.get(`/reportsonmymissingdogs/${email}`);
    return response.data
  },
  async sendreqshelter(data) {
    return await Service.post("/sendreqshelter", data);
  },
  async getmissingdogsshelterreports(email) {
    let response = await Service.get(`/getmissingdogsshelterreports/${email}`);
    return response.data
  },
  async updatereportmissingdog(id, data){
    return await Service.patch(`/updatereportmissingdog/${id}`, data)
  },
  async getacceptedmissingdogsshelterreports(email) {
    let response = await Service.get(`/getacceptedmissingdogsshelterreports/${email}`);
    return response.data
  },
  async getrejectedmissingdogsshelterreports(email) {
    let response = await Service.get(`/getrejectedmissingdogsshelterreports/${email}`);
    return response.data
  },
  async sendreqadopt(data) {
    return await Service.post("/sendreqadopt", data);
  },
  async getadopteddogsshelterreports(email) {
    let response = await Service.get(`/getadopteddogsshelterreports/${email}`);
    return response.data
  },
  async getacceptedadopteddogsshelterreports(email) {
    let response = await Service.get(`/getacceptedadopteddogsshelterreports/${email}`);
    return response.data
  },
  async getrejectedadopteddogsshelterreports(email) {
    let response = await Service.get(`/getrejectedadopteddogsshelterreports/${email}`);
    return response.data
  },
  async updatereportadopteddog(id, data){
    return await Service.patch(`/updatereportadopteddog/${id}`, data)
  },
  // async updateadoptad(id, data){
  //   return await Service.patch(`/updateadoptad/${id}`, data)
  // },
  async getadopteddogsshelter(){
    let response = await Service.get('/getadopteddogsshelter');
    return response.data
  },
  async getmissingdogsshelter(){
    let response = await Service.get('/getmissingdogsshelter');
    return response.data
  },
  async getmissingdogsadminshelter(){
    let response = await Service.get('/getmissingdogsadminshelter');
    return response.data
  },
  async updatedatamissingdogs(id, data){
    return await Service.patch(`/updatedatamissingdogs/${id}`, data)
  },
  async deletespecificdata(id){
    return await Service.delete(`/deletedata/${id}`)
  },
  async deleteadopted(id){
    return await Service.delete(`/deleteadopted/${id}`)
  }
  // async updatemissingad(id, data){
  //   return await Service.patch(`/updatemissingad/${id}`, data)
  // },
}
let profile={
  async changeuserdata(email){
    let response = await Service.get(`/changeuserdata/${email}`);
    return response.data
  },
  async updatemydata(id, data){
    return await Service.patch(`/updatemydata/${id}`, data)
  },
  async updatepass(id, data){
    return await Service.patch(`/updatepass/${id}`, data)
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

export {login_signup, Auth, dog_data, profile};
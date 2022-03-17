import axios from "axios";

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
      }
}

export {login_signup};
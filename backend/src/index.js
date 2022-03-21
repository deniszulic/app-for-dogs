import express from "express";
import cors from 'cors'
import dotenv from 'dotenv';

dotenv.config();

const app = express();
const port = 3000;
const db = require('./queries')
const bodyParser = require('body-parser')

app.use(cors())
app.use(bodyParser.json())
app.use(
  bodyParser.urlencoded({
    extended: true,
  })
)

app.post('/register', db.createUser)
app.post('/login', db.login)
app.post('/registerasylum', db.createasylum)
app.post('/dogmissing', db.reportmissingdog)
app.delete('/delete/:id', db.deleteuser)
app.get('/getmissingdogs', db.getmissingdogs)

app.listen(port, () => {
    console.log(`App running on port ${port}.`);
  });
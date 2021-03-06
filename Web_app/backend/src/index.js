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
app.post('/adoptdog', db.adoptdog)
app.post('/getcommentsfordog', db.commentsfordog)
app.post('/reportmissingdog', db.report_missing_dog)
app.post('/adoptdogapplication', db.adopt_dog_application)
app.post('/sendreqshelter', db.sendreqshelter)
app.post('/sendreqadopt', db.sendreqadopt)
app.delete('/delete/:id', db.deleteuser)
app.delete('/deletecomment/:id', db.deletecomment)
app.get('/getmissingdogs', db.getmissingdogs)
app.get('/getadopteddogsshelter', db.getadopteddogsshelter)
app.get('/getmissingdogsshelter', db.getmissingdogsshelter)
app.get('/getadopteddogs', db.getadopteddogs)
app.get('/getshelters', db.getshelters)
app.get('/getadopteddogsadmin', db.getadopteddogsadmin)
app.get('/getadopteddogsadminshelter', db.getadopteddogsadminshelter)
app.get('/getmissingdogsadmin', db.getmissingdogsadmin)
app.get('/getmissingdogsadminshelter', db.getmissingdogsadminshelter)
app.get('/getmissingdogs/:id', db.getspecificdog)
app.get('/getspecificdogshelter/:id', db.getspecificdogshelter)
app.get('/getcomments/:id', db.getcomments)
app.get('/getmymissingdogs/:email', db.getmymissingdogs)
app.get('/getmyadopteddogs/:email', db.getmyadopteddogs)
app.get('/getmyreportdisapp/:email', db.getmyreportdisapp)
app.get('/getmyreportadoptdog/:email', db.getmyreportadoptdog)
app.get('/reportsonmyadopteddogs/:email', db.reportsonmyadopteddogs)
app.get('/reportsonmymissingdogs/:email', db.reportsonmymissingdogs)
app.get('/changeuserdata/:email', db.changeuserdata)
app.get('/getmissingdogsshelterreports/:email', db.getmissingdogsshelterreports)
app.get('/getacceptedmissingdogsshelterreports/:email', db.getacceptedmissingdogsshelterreports)
app.get('/getrejectedmissingdogsshelterreports/:email', db.getrejectedmissingdogsshelterreports)
app.get('/getadopteddogsshelterreports/:email', db.getadopteddogsshelterreports)
app.get('/getacceptedadopteddogsshelterreports/:email', db.getacceptedadopteddogsshelterreports)
app.get('/getrejectedadopteddogsshelterreports/:email', db.getrejectedadopteddogsshelterreports)
app.patch('/updateadopteddog/:id', db.updateadopteddog)
app.patch('/updatemissingdog/:id', db.updatemissingdog)
app.patch('/updateadoptdogreport/:id', db.update_adoptdog_report)
app.patch('/updatemyadopteddogreports/:id', db.updatemyadopteddogreports)
app.patch('/updatemymissingdogreport/:id', db.update_my_missingdog_report)
app.patch('/updatereportmissingdog/:id', db.updatereportmissingdog)
app.patch('/updatereportadopteddog/:id', db.updatereportadopteddog)
app.patch('/updatemydata/:id', db.updatemydata)
app.patch('/updatepass/:id', db.updatepass)
app.delete('/deletedata/:id', db.deletedatamissingdog)
app.delete('/deleteadopted/:id', db.deletedataadopteddog)

app.listen(port, () => {
  console.log(`App running on port ${port}.`);
});
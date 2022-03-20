require("dotenv").config();
import bcrypt, { hash } from "bcrypt";
import jwt from "jsonwebtoken";

const Pool = require("pg").Pool;
const pool = new Pool({
  user: "postgres",
  host: "localhost",
  database: "dogdatabase",
  password: "admin",
  port: 5432,
});

const createUser = async (request, response) => {
    //console.log(request.body);
    const { email, datumreg, ime, prezime, lozinka, tipkorisnika } = request.body;
    const salt = bcrypt.genSaltSync(8);
    const hash = bcrypt.hashSync(lozinka, salt);
    pool.query(
      "INSERT INTO korisnik (email, lozinka, datumreg, ime, prezime, tipkorisnika) VALUES ($1, $2, $3, $4, $5, $6) RETURNING id",
      [email, hash, datumreg, ime, prezime, tipkorisnika],
      (error, results) => {
        try {
            console.log(results.rows[0].id)
          response.status(201).send(results.rows[0].id.toString());
        } catch (e) {
          console.log(e);
        }
      }
    );
  };

  const login = async (request, response) => {
    const { email, lozinka } = request.body;
    console.log(request.body)
    pool.query(
      "SELECT lozinka, tipkorisnika, email, id, ime, prezime FROM korisnik WHERE email=$1",
      [email],
      (error, results) => {
        try {
          bcrypt
            .compare(lozinka, results.rows[0].lozinka)
            .then(function (result) {
              if (result && results.rows[0].lozinka) {
                delete results.rows[0].lozinka;
                let token = jwt.sign(
                  email,
                  process.env.JWT_KEY,
                  { algorithm: "HS512" },
                  { expiresIn: "7d" }
                );
                response.status(200).json(results.rows);
              } else {
                throw new Error("Cannot authenticate");
              }
            });
        } catch (error) {
          console.log(error);
        }
      }
    );
  };

  const createasylum = async (request, response) => {
    //console.log(request.body);
    const { oib, ulica, kucnibr, grad, postanskibr, id } = request.body;
    pool.query(
        "INSERT INTO azil (oib, ulica, kucnibr, grad, postanskibr, korisnik_id) VALUES ($1, $2, $3, $4, $5, $6) RETURNING id",
        [parseInt(oib), ulica, kucnibr, grad, parseInt(postanskibr), parseInt(id)],
        (error, results) => {
          try {
            response.status(201).send(`User added with ID: ${results.rows[0].id}`);
          } catch (e) {
            console.log(e);
          }
        }
      );
  };

  const deleteuser = (request, response) => {
    const id = request.params.id;
    pool.query("DELETE FROM korisnik WHERE id=$1", [id], (error, results) => {
      try {
        response.status(200).json(results.rows);
      } catch (e) {
        console.log(e);
      }
    });
  };
  const reportmissingdog = async (request, response) => {
    console.log(request.body);
    const { ime, prezime, adresa, grad, postanski_broj, boja, starost, dlaka, vet_lokacija, ime_psa, spol, datum_izgubljen, napomena, postavljeno, Korisnik_id, url_slike } = request.body;
    pool.query(
      "INSERT INTO nestanak (ime, prezime, adresa, grad, postanski_broj, boja, starost, dlaka, vet_lokacija, ime_psa, spol, datum_izgubljen, napomena, postavljeno, Korisnik_id, url_slike) VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10, $11, $12, $13, $14, $15, $16) RETURNING id",
      [ime, prezime, adresa, grad, postanski_broj, boja, starost, dlaka, vet_lokacija, ime_psa, spol, datum_izgubljen, napomena, postavljeno, Korisnik_id, url_slike],
      (error, results) => {
        try {
          response.status(201).send(results.rows[0].id.toString());
        } catch (e) {
          console.log(e);
        }
      }
    );
  };

  module.exports = {
    createUser,
    createasylum,
    deleteuser,
    login,
    reportmissingdog
  };
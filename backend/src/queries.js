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
    console.log(request.body);
    const { email, admin, datumreg, ime, prezime, lozinka } = request.body;
    const salt = bcrypt.genSaltSync(8);
    const hash = bcrypt.hashSync(lozinka, salt);
    pool.query(
      "INSERT INTO korisnik (email, lozinka, admin, datumreg, ime, prezime) VALUES ($1, $2, $3, $4, $5, $6) RETURNING id",
      [email, hash, admin, datumreg, ime, prezime],
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

  const createasylum = async (request, response) => {
    console.log(request.body);
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

  module.exports = {
    createUser,
    createasylum,
    deleteuser
  };
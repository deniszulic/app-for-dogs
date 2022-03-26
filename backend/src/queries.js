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
            //console.log(results.rows[0].id)
          response.status(201).send(results.rows[0].id.toString());
        } catch (e) {
          console.log(e);
        }
      }
    );
  };

  const login = async (request, response) => {
    const { email, lozinka } = request.body;
    //console.log(request.body)
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
    //console.log(request.body);
    const { ime, prezime, adresa, grad, postanski_broj, boja, starost, dlaka, vet_lokacija, ime_psa, spol, datum_izgubljen, napomena, postavljeno, Korisnik_id, telefonskibr, pasmina, url_slike } = request.body;
    pool.query(
      "INSERT INTO nestanak (ime, prezime, adresa, grad, postanski_broj, boja, starost, dlaka, vet_lokacija, ime_psa, spol, datum_izgubljen, napomena, postavljeno, Korisnik_id, telefonskibr, pasmina, url_slike) VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10, $11, $12, $13, $14, $15, $16, $17, $18) RETURNING id",
      [ime, prezime, adresa, grad, postanski_broj, boja, starost, dlaka, vet_lokacija, ime_psa, spol, datum_izgubljen, napomena, postavljeno, Korisnik_id, telefonskibr, pasmina,  url_slike],
      (error, results) => {
        try {
          response.status(201).send(results.rows[0].id.toString());
        } catch (e) {
          console.log(e);
        }
      }
    );
  };
  const getmissingdogs = (request, response) => {
    pool.query("SELECT id, ime, prezime, adresa, telefonskibr, grad, postanski_broj, boja, starost, dlaka, vet_lokacija, ime_psa, spol, datum_izgubljen, napomena, url_slike, postavljeno, pasmina FROM nestanak ORDER BY postavljeno DESC;", (error, results) => {
      try {
        response.status(200).json(results.rows);
      } catch (e) {
        console.log(e);
      }
    });
  };
  const getspecificdog = (request, response) => {
    const id = request.params.id;
    pool.query("SELECT nestanak.id, nestanak.ime, nestanak.prezime, nestanak.adresa, nestanak.telefonskibr, nestanak.grad, nestanak.postanski_broj, nestanak.boja, nestanak.starost, nestanak.dlaka, nestanak.vet_lokacija, nestanak.ime_psa, nestanak.spol, nestanak.datum_izgubljen, nestanak.napomena, nestanak.url_slike, nestanak.postavljeno, nestanak.pasmina, korisnik.email FROM nestanak LEFT JOIN korisnik ON nestanak.korisnik_id=korisnik.id WHERE nestanak.id=$1 ORDER BY nestanak.postavljeno DESC", [id], (error, results) => {
      try {
        response.status(200).json(results.rows);
      } catch (e) {
        console.log(e);
      }
    });
  };

  const commentsfordog = async (request, response) => {
    //console.log(request.body)
    const { komentar, postavljeno, Nestanak_id, Korisnik_id } = request.body;
    pool.query(
        "INSERT INTO komentar (komentar, postavljeno, Nestanak_id, Korisnik_id) VALUES ($1, $2, $3, $4) RETURNING id",
        [komentar, postavljeno, Nestanak_id, Korisnik_id],
        (error, results) => {
          try {
            response.status(201).send(`User added with ID: ${results.rows[0].id}`);
          } catch (e) {
            console.log(e);
          }
        }
      );
  };

  const getcomments = (request, response) => {
    const id = request.params.id;
    pool.query("SELECT korisnik.email, komentar.id, komentar.komentar, komentar.postavljeno FROM korisnik LEFT JOIN komentar ON korisnik.id=komentar.korisnik_id WHERE komentar.nestanak_id=$1 ORDER BY komentar.postavljeno ASC", [id], (error, results) => {
      try {
        response.status(200).json(results.rows);
      } catch (e) {
        console.log(e);
      }
    });
  };

  const deletecomment = (request, response) => {
    const id = request.params.id;
    pool.query("DELETE FROM komentar WHERE id=$1", [id], (error, results) => {
      try {
        response.status(200).json(results.rows);
      } catch (e) {
        console.log(e);
      }
    });
  };

  const adoptdog = async (request, response) => {
    //console.log(request.body);
    const { ime, prezime, adresa, grad, postanski_broj, boja, starost, dlaka, vet_lokacija, ime_psa, spol, napomena, postavljeno, Korisnik_id, telefonskibr, pasmina, kg, kastrat, opasnost, url_slike } = request.body;
    pool.query(
      "INSERT INTO udomljavanje (ime, prezime, adresa, grad, postanski_broj, boja, starost, dlaka, vet_lokacija, ime_psa, spol, napomena, postavljeno, Korisnik_id, telefonskibr, pasmina, kilaza, kastrat, opasnost, url_slike) VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10, $11, $12, $13, $14, $15, $16, $17, $18, $19, $20) RETURNING id",
      [ime, prezime, adresa, grad, postanski_broj, boja, starost, dlaka, vet_lokacija, ime_psa, spol, napomena, postavljeno, Korisnik_id, telefonskibr, pasmina, kg, kastrat, opasnost, url_slike],
      (error, results) => {
        try {
          response.status(201).send(results.rows[0].id.toString());
        } catch (e) {
          console.log(e);
        }
      }
    );
  };

  const getadopteddogs = (request, response) => {
    pool.query("SELECT udomljavanje.id, udomljavanje.ime, udomljavanje.prezime, udomljavanje.adresa, udomljavanje.telefonskibr, udomljavanje.grad, udomljavanje.postanski_broj, udomljavanje.boja, udomljavanje.starost, udomljavanje.dlaka, udomljavanje.vet_lokacija, udomljavanje.ime_psa, udomljavanje.spol, udomljavanje.napomena, udomljavanje.url_slike, udomljavanje.postavljeno, udomljavanje.pasmina, udomljavanje.kilaza, udomljavanje.kastrat, udomljavanje.opasnost, korisnik.email FROM udomljavanje LEFT JOIN korisnik ON udomljavanje.korisnik_id=korisnik.id ORDER BY udomljavanje.postavljeno DESC", (error, results) => {
      try {
        response.status(200).json(results.rows);
      } catch (e) {
        console.log(e);
      }
    });
  };

  const getmymissingdogs = (request, response) => {
    const id = request.params.id;
    pool.query("SELECT nestanak.id, nestanak.ime, nestanak.prezime, nestanak.adresa, nestanak.telefonskibr, nestanak.grad, nestanak.postanski_broj, nestanak.boja, nestanak.starost, nestanak.dlaka, nestanak.vet_lokacija, nestanak.ime_psa, nestanak.spol, nestanak.datum_izgubljen, nestanak.napomena, nestanak.url_slike, nestanak.postavljeno, nestanak.pasmina FROM nestanak LEFT JOIN korisnik ON nestanak.korisnik_id=korisnik.id WHERE korisnik.id=$1 ORDER BY nestanak.postavljeno DESC;", [id],  (error, results) => {
      try {
        response.status(200).json(results.rows);
        //console.log(results.rows)
      } catch (e) {
        console.log(e);
      }
    });
  };

  const getmyadopteddogs = (request, response) => {
    const email = request.params.email;
    pool.query("SELECT udomljavanje.id, udomljavanje.ime, udomljavanje.prezime, udomljavanje.adresa, udomljavanje.telefonskibr, udomljavanje.grad, udomljavanje.postanski_broj, udomljavanje.boja, udomljavanje.starost, udomljavanje.dlaka, udomljavanje.vet_lokacija, udomljavanje.ime_psa, udomljavanje.spol, udomljavanje.napomena, udomljavanje.url_slike, udomljavanje.postavljeno, udomljavanje.pasmina, udomljavanje.kilaza, udomljavanje.kastrat, udomljavanje.opasnost FROM udomljavanje LEFT JOIN korisnik ON udomljavanje.korisnik_id=korisnik.id WHERE korisnik.email=$1 ORDER BY udomljavanje.postavljeno DESC", [email],  (error, results) => {
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
    deleteuser,
    login,
    reportmissingdog,
    getmissingdogs,
    getspecificdog,
    commentsfordog,
    getcomments,
    deletecomment,
    adoptdog,
    getadopteddogs,
    getmymissingdogs,
    getmyadopteddogs
  };
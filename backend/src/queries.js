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
    pool.query("SELECT nestanak.id, nestanak.ime, nestanak.prezime, nestanak.adresa, nestanak.telefonskibr, nestanak.grad, nestanak.postanski_broj, nestanak.boja, nestanak.starost, nestanak.dlaka, nestanak.vet_lokacija, nestanak.ime_psa, nestanak.spol, nestanak.datum_izgubljen, nestanak.napomena, nestanak.url_slike, nestanak.postavljeno, nestanak.pasmina, korisnik.email FROM nestanak LEFT JOIN korisnik ON nestanak.korisnik_id=korisnik.id ORDER BY postavljeno DESC;", (error, results) => {
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
    const email = request.params.email;
    pool.query("SELECT nestanak.id, nestanak.ime, nestanak.prezime, nestanak.adresa, nestanak.telefonskibr, nestanak.grad, nestanak.postanski_broj, nestanak.boja, nestanak.starost, nestanak.dlaka, nestanak.vet_lokacija, nestanak.ime_psa, nestanak.spol, nestanak.datum_izgubljen, nestanak.napomena, nestanak.url_slike, nestanak.postavljeno, nestanak.pasmina FROM nestanak LEFT JOIN korisnik ON nestanak.korisnik_id=korisnik.id WHERE korisnik.email=$1 ORDER BY nestanak.postavljeno DESC;", [email],  (error, results) => {
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

  const updateadopteddog = (request, response) => {
    const id = request.params.id;
    const { ime, prezime, adresa, telefonskibr, grad, postanski_broj, boja, starost, dlaka, vet_lokacija, ime_psa, spol, pasmina, kilaza, kastrat, opasnost, napomena } = request.body;
    pool.query("UPDATE udomljavanje SET ime=$1, prezime=$2, adresa=$3, telefonskibr=$4, grad=$5, postanski_broj=$6, boja=$7, starost=$8, dlaka=$9, vet_lokacija=$10, ime_psa=$11, spol=$12, pasmina=$13, kilaza=$14, kastrat=$15, opasnost=$16, napomena=$17 WHERE id=$18", [ime, prezime, adresa, telefonskibr, grad, postanski_broj, boja, starost, dlaka, vet_lokacija, ime_psa, spol, pasmina, kilaza, kastrat, opasnost, napomena, id],  (error, results) => {
      try {
        response.status(200).json(results.rows);
      } catch (e) {
        console.log(e);
      }
    });
  };

  const updatemissingdog = (request, response) => {
    const id = request.params.id;
    const { ime, prezime, adresa, telefonskibr, grad, postanski_broj, boja, starost, dlaka, vet_lokacija, ime_psa, spol, pasmina, napomena, datum_izgubljen } = request.body;
    pool.query("UPDATE nestanak SET ime=$1, prezime=$2, adresa=$3, telefonskibr=$4, grad=$5, postanski_broj=$6, boja=$7, starost=$8, dlaka=$9, vet_lokacija=$10, ime_psa=$11, spol=$12, pasmina=$13, napomena=$14, datum_izgubljen=$15 WHERE id=$16", [ime, prezime, adresa, telefonskibr, grad, postanski_broj, boja, starost, dlaka, vet_lokacija, ime_psa, spol, pasmina, napomena, datum_izgubljen, id],  (error, results) => {
      try {
        response.status(200).json(results.rows);
      } catch (e) {
        console.log(e);
      }
    });
  };

  const report_missing_dog = async (request, response) => {
    const { ime, prezime, napomena, adresa_pronalaska, adresa_za_pokupiti_psa, postavljeno, korisnik_id, nestanak_id, kontakt } = request.body;
    pool.query(
        "INSERT INTO prijava_nestanka (ime, prezime, napomena, adresa_pronalaska, adresa_za_pokupiti_psa, postavljeno, korisnik_id, nestanak_id, kontakt) VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9) RETURNING id",
        [ime, prezime, napomena, adresa_pronalaska, adresa_za_pokupiti_psa, postavljeno, korisnik_id, nestanak_id, kontakt],
        (error, results) => {
          try {
            response.status(201).send(`User added with ID: ${results.rows[0].id}`);
          } catch (e) {
            console.log(e);
          }
        }
      );
  };

  const adopt_dog_application = async (request, response) => {
    const { ime, prezime, adresa, grad, postanski_broj, kontakt, razlog_prijave, prihvaceno, postavljeno, udomljavanje_id, korisnik_id } = request.body;
    pool.query(
      "INSERT INTO prijava_na_udomljavanje (ime, prezime, adresa, grad, postanski_broj, kontakt, razlog_prijave, prihvaceno, postavljeno, udomljavanje_id, korisnik_id) VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10, $11) RETURNING id",
      [ime, prezime, adresa, grad, postanski_broj, kontakt, razlog_prijave, prihvaceno, postavljeno, udomljavanje_id, korisnik_id],
      (error, results) => {
        try {
          response.status(201).send(results.rows[0].id.toString());
        } catch (e) {
          console.log(e);
        }
      }
    );
  };

  const getmyreportdisapp = (request, response) => {
    const email = request.params.email;
    pool.query("SELECT prijava_nestanka.ime, prijava_nestanka.prezime, prijava_nestanka.adresa_pronalaska, prijava_nestanka.adresa_za_pokupiti_psa, prijava_nestanka.napomena, prijava_nestanka.postavljeno, prijava_nestanka.kontakt, prijava_nestanka.id, nestanak.ime as ime_nestanak, nestanak.prezime as prezime_nestanak, nestanak.adresa, nestanak.telefonskibr, nestanak.grad, nestanak.postanski_broj, nestanak.boja, nestanak.starost, nestanak.dlaka, nestanak.vet_lokacija, nestanak.ime_psa, nestanak.spol, nestanak.pasmina, nestanak.datum_izgubljen, nestanak.napomena as napomena_nestanak, nestanak.url_slike FROM prijava_nestanka LEFT JOIN nestanak ON prijava_nestanka.nestanak_id=nestanak.id LEFT JOIN korisnik ON prijava_nestanka.korisnik_id=korisnik.id WHERE korisnik.email=$1 ORDER BY prijava_nestanka.postavljeno DESC", [email],  (error, results) => {
      try {
        response.status(200).json(results.rows);
      } catch (e) {
        console.log(e);
      }
    });
  };

  const getmyreportadoptdog = (request, response) => {
    const email = request.params.email;
    pool.query("SELECT prijava_na_udomljavanje.ime, prijava_na_udomljavanje.prezime, prijava_na_udomljavanje.adresa, prijava_na_udomljavanje.grad, prijava_na_udomljavanje.postanski_broj, prijava_na_udomljavanje.kontakt, prijava_na_udomljavanje.razlog_prijave, prijava_na_udomljavanje.prihvaceno, prijava_na_udomljavanje.postavljeno, prijava_na_udomljavanje.napomena, prijava_na_udomljavanje.id, udomljavanje.ime as ime_udomljavanje, udomljavanje.prezime as prezime_udomljavanje, udomljavanje.adresa as adresa_udomljavanje, udomljavanje.telefonskibr, udomljavanje.grad as grad_udomljavanje, udomljavanje.postanski_broj as postanski_broj_udomljavanje, udomljavanje.boja, udomljavanje.starost, udomljavanje.dlaka, udomljavanje.vet_lokacija, udomljavanje.ime_psa, udomljavanje.spol, udomljavanje.pasmina, udomljavanje.kilaza, udomljavanje.kastrat, udomljavanje.opasnost, udomljavanje.napomena as napomena_udomljavanje, udomljavanje.url_slike FROM prijava_na_udomljavanje LEFT JOIN udomljavanje ON prijava_na_udomljavanje.udomljavanje_id=udomljavanje.id LEFT JOIN korisnik ON prijava_na_udomljavanje.korisnik_id=korisnik.id WHERE korisnik.email=$1 ORDER BY prijava_na_udomljavanje.postavljeno DESC", [email],  (error, results) => {
      try {
        response.status(200).json(results.rows);
      } catch (e) {
        console.log(e);
      }
    });
  };

  const update_adoptdog_report = (request, response) => {
    const id = request.params.id;
    const { ime, prezime, adresa, grad, kontakt, postanski_broj, razlog_prijave } = request.body;
    pool.query("UPDATE prijava_na_udomljavanje SET ime=$1, prezime=$2, adresa=$3, grad=$4, kontakt=$5, postanski_broj=$6, razlog_prijave=$7 WHERE id=$8", [ime, prezime, adresa, grad, kontakt, postanski_broj, razlog_prijave, id],  (error, results) => {
      try {
        response.status(200).json(results.rows);
      } catch (e) {
        console.log(e);
      }
    });
  };

  const reportsonmyadopteddogs = (request, response) => {
    const email = request.params.email;
    pool.query("SELECT prijava_na_udomljavanje.ime, prijava_na_udomljavanje.prezime, prijava_na_udomljavanje.adresa, prijava_na_udomljavanje.grad, prijava_na_udomljavanje.postanski_broj, prijava_na_udomljavanje.kontakt, prijava_na_udomljavanje.razlog_prijave, prijava_na_udomljavanje.prihvaceno, prijava_na_udomljavanje.postavljeno, prijava_na_udomljavanje.napomena, prijava_na_udomljavanje.id, udomljavanje.ime as udomljavanje_ime, udomljavanje.prezime as udomljavanje_prezime, udomljavanje.adresa as udomljavanje_adresa, udomljavanje.telefonskibr, udomljavanje.grad as udomljavanje_grad, udomljavanje.postanski_broj as udomljavanje_postanski_broj, udomljavanje.boja, udomljavanje.starost, udomljavanje.dlaka, udomljavanje.vet_lokacija, udomljavanje.ime_psa, udomljavanje.spol, udomljavanje.pasmina, udomljavanje.kilaza, udomljavanje.kastrat, udomljavanje.opasnost, udomljavanje.napomena as udomljavanje_napomena, udomljavanje.url_slike from prijava_na_udomljavanje left join udomljavanje ON prijava_na_udomljavanje.udomljavanje_id=udomljavanje.id left join korisnik on udomljavanje.korisnik_id=korisnik.id WHERE korisnik.email=$1", [email],  (error, results) => {
      try {
        response.status(200).json(results.rows);
      } catch (e) {
        console.log(e);
      }
    });
  };

  const updatemyadopteddogreports = (request, response) => {
    const id = request.params.id;
    const { napomena, prihvaceno } = request.body;
    pool.query("UPDATE prijava_na_udomljavanje SET napomena=$1, prihvaceno=$2 WHERE id=$3", [napomena, prihvaceno, id],  (error, results) => {
      try {
        response.status(200).json(results.rows);
      } catch (e) {
        console.log(e);
      }
    });
  };

  const update_my_missingdog_report = (request, response) => {
    const id = request.params.id;
    const { ime, prezime, adresa_pronalaska, adresa_za_pokupiti_psa, kontakt, napomena } = request.body;
    pool.query("UPDATE prijava_nestanka SET ime=$1, prezime=$2, adresa_pronalaska=$3, adresa_za_pokupiti_psa=$4, kontakt=$5, napomena=$6 WHERE id=$7", [ime, prezime, adresa_pronalaska, adresa_za_pokupiti_psa, kontakt, napomena, id],  (error, results) => {
      try {
        response.status(200).json(results.rows);
      } catch (e) {
        console.log(e);
      }
    });
  };

  const reportsonmymissingdogs = (request, response) => {
    const email = request.params.email;
    pool.query("SELECT prijava_nestanka.ime, prijava_nestanka.prezime, prijava_nestanka.adresa_pronalaska, prijava_nestanka.adresa_za_pokupiti_psa, prijava_nestanka.kontakt, prijava_nestanka.napomena, prijava_nestanka.postavljeno, prijava_nestanka.id, nestanak.ime as nestanak_ime, nestanak.prezime as nestanak_prezime, nestanak.adresa, nestanak.telefonskibr, nestanak.grad, nestanak.postanski_broj, nestanak.boja, nestanak.starost, nestanak.dlaka, nestanak.vet_lokacija, nestanak.ime_psa, nestanak.spol, nestanak.pasmina, nestanak.datum_izgubljen, nestanak.napomena as nestanak_napomena, nestanak.url_slike FROM prijava_nestanka LEFT JOIN nestanak ON prijava_nestanka.nestanak_id=nestanak.id LEFT JOIN korisnik ON nestanak.korisnik_id=korisnik.id WHERE korisnik.email=$1", [email],  (error, results) => {
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
    getmyadopteddogs,
    updateadopteddog,
    updatemissingdog,
    report_missing_dog,
    adopt_dog_application,
    getmyreportdisapp,
    getmyreportadoptdog,
    update_adoptdog_report,
    reportsonmyadopteddogs,
    updatemyadopteddogreports,
    update_my_missingdog_report,
    reportsonmymissingdogs
  };
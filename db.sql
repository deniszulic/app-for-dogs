CREATE TABLE Azil (
    id serial  NOT NULL,
    oib varchar(11)  NOT NULL,
    ulica varchar(50)  NOT NULL,
    kucnibr varchar(5)  NOT NULL,
    grad varchar(20)  NOT NULL,
    postanskibr varchar(5)  NOT NULL,
    naziv varchar(50)  NOT NULL,
    Korisnik_id int  NOT NULL,
    CONSTRAINT Azil_ak_1 UNIQUE (oib) NOT DEFERRABLE  INITIALLY IMMEDIATE,
    CONSTRAINT Azil_ak_2 UNIQUE (naziv) NOT DEFERRABLE  INITIALLY IMMEDIATE,
    CONSTRAINT Azil_pk PRIMARY KEY (id)
);

CREATE TABLE Azil_nestanak (
    id serial  NOT NULL,
    napomena text  NULL,
    prihvaceno varchar(6)  NOT NULL,
    postavljeno bigint  NOT NULL,
    Nestanak_id int  NOT NULL,
    Korisnik_id int  NOT NULL,
    CONSTRAINT Azil_nestanak_pk PRIMARY KEY (id)
);

CREATE TABLE Azil_udomljavanje (
    id serial  NOT NULL,
    napomena text  NULL,
    prihvaceno varchar(6)  NOT NULL,
    postavljeno bigint  NOT NULL,
    Korisnik_id int  NOT NULL,
    Udomljavanje_id int  NOT NULL,
    CONSTRAINT Azil_udomljavanje_pk PRIMARY KEY (id)
);

CREATE TABLE Korisnik (
    id serial  NOT NULL,
    ime varchar(50)  NOT NULL,
    prezime varchar(50)  NOT NULL,
    email varchar(50)  NOT NULL,
    lozinka varchar(60)  NOT NULL,
    tipkorisnika varchar(8)  NOT NULL,
    datumreg bigint  NOT NULL,
    CONSTRAINT Korisnik_ak_1 UNIQUE (email) NOT DEFERRABLE  INITIALLY IMMEDIATE,
    CONSTRAINT Korisnik_pk PRIMARY KEY (id)
);

CREATE TABLE Nestanak (
    id serial  NOT NULL,
    ime varchar(50)  NULL,
    prezime varchar(50)  NULL,
    adresa varchar(50)  NULL,
    telefonskibr varchar(13)  NOT NULL,
    grad varchar(20)  NULL,
    postanski_broj varchar(5)  NULL,
    boja varchar(10)  NOT NULL,
    starost varchar(2)  NOT NULL,
    dlaka varchar(6)  NOT NULL,
    vet_lokacija varchar(20)  NULL,
    ime_psa varchar(50)  NOT NULL,
    spol char(1)  NOT NULL,
    pasmina varchar(20)  NOT NULL,
    datum_izgubljen date  NOT NULL,
    napomena text  NULL,
    url_slike text  NULL,
    aktivan boolean  NOT NULL,
    oglas_azila boolean  NOT NULL,
    postavljeno bigint  NOT NULL,
    Korisnik_id int  NOT NULL,
    CONSTRAINT Nestanak_pk PRIMARY KEY (id)
);

CREATE TABLE Prijava_na_udomljavanje (
    id serial  NOT NULL,
    ime varchar(50)  NOT NULL,
    prezime varchar(50)  NOT NULL,
    adresa varchar(50)  NOT NULL,
    grad varchar(20)  NOT NULL,
    postanski_broj varchar(5)  NOT NULL,
    kontakt varchar(50)  NOT NULL,
    razlog_prijave text  NOT NULL,
    prihvaceno varchar(6)  NOT NULL,
    napomena text  NULL,
    postavljeno bigint  NOT NULL,
    naziv_azila varchar(50)  NULL,
    grad_azila varchar(20)  NULL,
    Udomljavanje_id int  NOT NULL,
    Korisnik_id int  NOT NULL,
    CONSTRAINT Prijava_na_udomljavanje_pk PRIMARY KEY (id)
);

CREATE TABLE Prijava_nestanka (
    id serial  NOT NULL,
    ime varchar(50)  NOT NULL,
    prezime varchar(50)  NOT NULL,
    adresa_pronalaska varchar(50)  NULL,
    adresa_za_preuzimanje_psa varchar(50)  NOT NULL,
    kontakt varchar(50)  NOT NULL,
    napomena text  NULL,
    postavljeno bigint  NOT NULL,
    naziv_azila varchar(50)  NULL,
    grad_azila varchar(20)  NULL,
    Korisnik_id int  NOT NULL,
    Nestanak_id int  NOT NULL,
    CONSTRAINT Prijava_nestanka_pk PRIMARY KEY (id)
);

CREATE TABLE Udomljavanje (
    id serial  NOT NULL,
    ime varchar(50)  NULL,
    prezime varchar(50)  NULL,
    adresa varchar(50)  NULL,
    telefonskibr varchar(13)  NULL,
    grad varchar(20)  NULL,
    postanski_broj varchar(5)  NULL,
    boja varchar(10)  NOT NULL,
    starost varchar(2)  NOT NULL,
    dlaka varchar(6)  NOT NULL,
    vet_lokacija varchar(20)  NULL,
    ime_psa varchar(50)  NOT NULL,
    spol char(1)  NOT NULL,
    pasmina varchar(20)  NOT NULL,
    kilaza varchar(2)  NOT NULL,
    kastrat char(2)  NOT NULL,
    opasnost char(2)  NOT NULL,
    napomena text  NULL,
    url_slike text  NULL,
    oglas_azila boolean  NOT NULL,
    aktivan boolean  NOT NULL,
    postavljeno bigint  NOT NULL,
    Korisnik_id int  NOT NULL,
    CONSTRAINT Udomljavanje_pk PRIMARY KEY (id)
);

CREATE TABLE komentar (
    id serial  NOT NULL,
    komentar text  NOT NULL,
    postavljeno bigint  NOT NULL,
    Nestanak_id int  NOT NULL,
    Korisnik_id int  NOT NULL,
    CONSTRAINT komentar_pk PRIMARY KEY (id)
);

ALTER TABLE Azil ADD CONSTRAINT Azil_Korisnik
    FOREIGN KEY (Korisnik_id)
    REFERENCES Korisnik (id)
    ON DELETE  CASCADE  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

ALTER TABLE Azil_nestanak ADD CONSTRAINT Azil_nestanak_Korisnik
    FOREIGN KEY (Korisnik_id)
    REFERENCES Korisnik (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

ALTER TABLE Azil_nestanak ADD CONSTRAINT Azil_nestanak_Nestanak
    FOREIGN KEY (Nestanak_id)
    REFERENCES Nestanak (id)
    ON DELETE  CASCADE  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

ALTER TABLE Azil_udomljavanje ADD CONSTRAINT Azil_udomljavanje_Korisnik
    FOREIGN KEY (Korisnik_id)
    REFERENCES Korisnik (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

ALTER TABLE Azil_udomljavanje ADD CONSTRAINT Azil_udomljavanje_Udomljavanje
    FOREIGN KEY (Udomljavanje_id)
    REFERENCES Udomljavanje (id)
    ON DELETE  CASCADE  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

ALTER TABLE Nestanak ADD CONSTRAINT Nestanak_Korisnik
    FOREIGN KEY (Korisnik_id)
    REFERENCES Korisnik (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

ALTER TABLE Prijava_na_udomljavanje ADD CONSTRAINT Prijava_na_udomljavanje_Korisnik
    FOREIGN KEY (Korisnik_id)
    REFERENCES Korisnik (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

ALTER TABLE Prijava_na_udomljavanje ADD CONSTRAINT Prijava_na_udomljavanje_Udomljavanje
    FOREIGN KEY (Udomljavanje_id)
    REFERENCES Udomljavanje (id)
    ON DELETE  CASCADE  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

ALTER TABLE Prijava_nestanka ADD CONSTRAINT Prijava_nestanka_Korisnik
    FOREIGN KEY (Korisnik_id)
    REFERENCES Korisnik (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

ALTER TABLE Prijava_nestanka ADD CONSTRAINT Prijava_nestanka_Nestanak
    FOREIGN KEY (Nestanak_id)
    REFERENCES Nestanak (id)
    ON DELETE  CASCADE  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

ALTER TABLE Udomljavanje ADD CONSTRAINT Udomljavanje_Korisnik
    FOREIGN KEY (Korisnik_id)
    REFERENCES Korisnik (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

ALTER TABLE komentar ADD CONSTRAINT komentar_Korisnik
    FOREIGN KEY (Korisnik_id)
    REFERENCES Korisnik (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

ALTER TABLE komentar ADD CONSTRAINT komentar_Nestanak
    FOREIGN KEY (Nestanak_id)
    REFERENCES Nestanak (id)
    ON DELETE  CASCADE  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

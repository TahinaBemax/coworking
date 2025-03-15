CREATE TABLE option
(
    code_option VARCHAR(50),
    option      TEXT             NOT NULL,
    prix        DECIMAL(15, 2) CHECK( >= 0),
    PRIMARY KEY (code_option),
    UNIQUE (option)
);

CREATE TABLE client
(
    client_id  SERIAL,
    numero_tel VARCHAR(15) NOT NULL,
    PRIMARY KEY (client_id),
    UNIQUE (numero_tel)
);

CREATE TABLE espace
(
    espace_id SERIAL,
    prix      DECIMAL(15, 2) CHECK( >= 0),
    nom       TEXT     NOT NULL,
    PRIMARY KEY (espace_id),
    UNIQUE (nom)
);

CREATE TABLE heure
(
    heure_id        SERIAL,
    heure_ouverture TIME NOT NULL,
    heure_fermeture TIME NOT NULL,
    PRIMARY KEY (heure_id)
);

CREATE TABLE statut
(
    code_statut VARCHAR(50),
    statut      TEXT NOT NULL,
    PRIMARY KEY (code_statut),
    UNIQUE (statut)
);

CREATE TABLE admin
(
    admin_id SERIAL,
    username TEXT NOT NULL,
    password TEXT NOT NULL,
    PRIMARY KEY (admin_id),
    UNIQUE (username)
);

CREATE TABLE reservation
(
    reservation_id   SERIAL,
    reference        TEXT     NOT NULL,
    annule_le        TIMESTAMPTZ                 DEFAULT CURRENT_TIMESTAMP,
    date_reservation DATE             NOT NULL DEFAULT CURRENT_DATE,
    heure_debut      TIME             NOT NULL DEFAULT CURRENT_TIME,
    heure_fin        TIME             NOT NULL DEFAULT CURRENT_TIME,
    prix_espace      DECIMAL(15, 2) CHECK( >= 0),
    code_option      VARCHAR(50)      NOT NULL,
    client_id        INTEGER          NOT NULL,
    espace_id        INTEGER          NOT NULL,
    PRIMARY KEY (reservation_id),
    UNIQUE (reference),
    FOREIGN KEY (code_option) REFERENCES option (code_option) ON DELETE CASCADE ,
    FOREIGN KEY (client_id) REFERENCES client (client_id) ON DELETE CASCADE ,
    FOREIGN KEY (espace_id) REFERENCES espace (espace_id) ON DELETE CASCADE
);

CREATE TABLE paiement
(
    paiement_id    SERIAL,
    date_paiement  DATE             NOT NULL,
    montant_paye   DECIMAL(15, 2) CHECK(montant_paye >= 0),
    ref_paiement   VARCHAR(255)     NOT NULL,
    reservation_id INTEGER          NOT NULL,
    PRIMARY KEY (paiement_id),
    FOREIGN KEY (reservation_id) REFERENCES reservation (reservation_id) ON DELETE CASCADE
);

CREATE TABLE statut_paiement
(
    paiement_id    INTEGER,
    code_statut    VARCHAR(50),
    updated_at     TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    admin_username TEXT      NOT NULL,
    PRIMARY KEY (paiement_id, code_statut),
    FOREIGN KEY (paiement_id) REFERENCES paiement (paiement_id) ON DELETE CASCADE ,
    FOREIGN KEY (code_statut) REFERENCES statut (code_statut) ON DELETE CASCADE
);

CREATE TABLE statut_espace
(
    espace_id   INTEGER,
    code_statut VARCHAR(50),
    updated_at  TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (espace_id, code_statut),
    FOREIGN KEY (espace_id) REFERENCES espace (espace_id) ON DELETE CASCADE ,
    FOREIGN KEY (code_statut) REFERENCES statut (code_statut) ON DELETE CASCADE
);

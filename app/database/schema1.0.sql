CREATE DOMAIN positive_amount AS DECIMAL(15, 2) NOT NULL CHECK (value >= 0);

CREATE TABLE option
(
    code_option   VARCHAR(50),
    option TEXT     NOT NULL,
    prix   POSITIVE_AMOUNT,
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

CREATE TABLE espace_statut
(
    code_espace_statut VARCHAR(50),
    statut             TEXT NOT NULL,
    PRIMARY KEY (code_espace_statut),
    UNIQUE (statut)
);

CREATE TABLE heure
(
    heure_id        SERIAL,
    heure_ouverture TIME NOT NULL,
    heure_fermeture TIME NOT NULL,
    PRIMARY KEY (heure_id)
);

CREATE TABLE statut_paiement
(
    code_statut_paiement VARCHAR(50),
    statut_paiement      TEXT NOT NULL,
    PRIMARY KEY (code_statut_paiement),
    UNIQUE (statut_paiement)
);

CREATE TABLE espace
(
    espace_id          SERIAL,
    prix               POSITIVE_AMOUNT,
    nom                TEXT    NOT NULL,
    code_espace_statut VARCHAR(50)     NOT NULL,
    PRIMARY KEY (espace_id),
    UNIQUE (nom),
    FOREIGN KEY (code_espace_statut) REFERENCES espace_statut (code_espace_statut) ON DELETE CASCADE
);

CREATE TABLE reservation
(
    reservation_id   SERIAL,
    reference        TEXT     NOT NULL,
    annule_le        TIMESTAMPTZ                 DEFAULT CURRENT_TIMESTAMP,
    date_reservation DATE             NOT NULL DEFAULT CURRENT_DATE,
    heure_debut      TIME             NOT NULL DEFAULT CURRENT_TIME,
    heure_fin        TIME             NOT NULL DEFAULT CURRENT_TIME,
    prix_espace      POSITIVE_AMOUNT,
    client_id        INTEGER          NOT NULL,
    espace_id        INTEGER          NOT NULL,
    code_option      VARCHAR(50)      NOT NULL,
    PRIMARY KEY (reservation_id),
    UNIQUE (reference),
    FOREIGN KEY (client_id) REFERENCES client (client_id) ON DELETE CASCADE ,
    FOREIGN KEY (espace_id) REFERENCES espace (espace_id) ON DELETE CASCADE ,
    FOREIGN KEY (code_option) REFERENCES option (code_option) ON DELETE CASCADE
);

CREATE TABLE paiement
(
    paiement_id          SERIAL,
    date_paiement        DATE             NOT NULL,
    montant_paye         POSITIVE_AMOUNT,
    ref_paiement         TEXT     NOT NULL,
    code_statut_paiement VARCHAR(50)     NOT NULL,
    reservation_id       INTEGER          NOT NULL,
    PRIMARY KEY (paiement_id),
    FOREIGN KEY (code_statut_paiement) REFERENCES statut_paiement (code_statut_paiement) ON DELETE CASCADE,
    FOREIGN KEY (reservation_id) REFERENCES reservation (reservation_id) ON DELETE CASCADE
);

--Table reservation
ALTER TABLE reservation ADD CONSTRAINT check_resevation_date_reseration CHECK(date_reservation >= CURRENT_DATE);
ALTER TABLE reservation ADD CONSTRAINT check_resevation_date_annulation CHECK(annule_le >= date_reservation);

--Table espace

--Table paiement

--Table client
ALTER  TABLE client ADD CONSTRAINT check_client_numero_tel CHECK (length(numero_tel) <= 15 );

--Table heure
ALTER TABLE heure ADD CONSTRAINT check_heure CHECK (heure_ouverture <= heure.heure_fermeture)


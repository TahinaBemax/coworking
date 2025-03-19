package itu.evaluation.s6.repository;

import itu.evaluation.s6.model.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementRepository extends JpaRepository<Paiement, Integer> {
}

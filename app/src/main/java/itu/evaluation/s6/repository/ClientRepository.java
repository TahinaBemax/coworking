package itu.evaluation.s6.repository;

import itu.evaluation.s6.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByNumeroTel(String phoneNumber);
}

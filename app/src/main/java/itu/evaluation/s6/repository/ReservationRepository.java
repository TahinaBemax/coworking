package itu.evaluation.s6.repository;

import itu.evaluation.s6.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}

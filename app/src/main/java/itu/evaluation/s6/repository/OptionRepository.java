package itu.evaluation.s6.repository;

import itu.evaluation.s6.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OptionRepository extends JpaRepository<Option, Integer> {

    Optional<Option> findById(String id);
}

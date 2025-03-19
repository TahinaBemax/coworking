package itu.evaluation.s6.csv;

import itu.evaluation.s6.repository.*;
import jakarta.validation.Validator;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class ImportCsvServiceDependence {
    private final OptionRepository optionRepository;
    private final Validator validator;
    private final EspaceRepository espaceRepository;
    private final ClientRepository clientRepository;
    private final ReservationRepository reservationRepository;
    private final PaiementRepository paiementRepository;
}

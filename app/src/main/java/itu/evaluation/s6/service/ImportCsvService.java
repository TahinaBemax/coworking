package itu.evaluation.s6.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import itu.evaluation.s6.csvdto.EspaceCSV;
import itu.evaluation.s6.csvdto.OptionCSV;
import itu.evaluation.s6.dto.ImportationCsvDto;
import itu.evaluation.s6.exception.ImportCsvException;
import itu.evaluation.s6.exception.TableNameNotFoundException;
import itu.evaluation.s6.model.Espace;
import itu.evaluation.s6.model.Option;
import itu.evaluation.s6.model.Paiement;
import itu.evaluation.s6.model.Reservation;
import itu.evaluation.s6.repository.EspaceRepository;
import itu.evaluation.s6.repository.OptionRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ImportCsvService {
    private final OptionRepository optionRepository;
    private final Validator validator;
    private final EspaceRepository espaceRepository;

    @Autowired
    public ImportCsvService(OptionRepository optionRepository, Validator validator, EspaceRepository espaceRepository) {
        this.optionRepository = optionRepository;
        this.validator = validator;
        this.espaceRepository = espaceRepository;
    }

    private <T> List<T> parseCSVFile(MultipartFile file, Class<T> type, String separator) throws IOException {
        if (separator == null)
            separator = ";";

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader)
                    .withType(type)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(separator.charAt(0))
                    .build();

            return csvToBean.parse();
        }
    }
    private <T> List<T> validateDataCsv(List<T> list) throws ImportCsvException {
        int ligne = 1;
        List<T> valides = new ArrayList<>();

        for (T t : list) {
            Set<ConstraintViolation<T>> violations = validator.validate(t);
            if (!violations.isEmpty()) {
                List<String> messages = new ArrayList<>();
                for (ConstraintViolation<T> violation : violations) {
                    messages.add(violation.getMessage());
                }

                throw new ImportCsvException(messages, ligne);
            }

            valides.add(t);
            ligne++;
        }

        return valides;
    }


    private List<Option> prepareOptionData(MultipartFile file, String separator) throws IOException, ImportCsvException {
        List<OptionCSV> options =  parseCSVFile(file, OptionCSV.class, separator);

        List<OptionCSV> optionCSVList = this.validateDataCsv(options);
        Set<Option> optionSet = new HashSet<>();

        for (OptionCSV optionCSV : optionCSVList) {
            Option temporaire = new Option();

            temporaire.setId(optionCSV.getCode());
            temporaire.setOption(optionCSV.getOption());
            temporaire.setPrix(new BigDecimal(optionCSV.getPrix()));

            optionSet.add(temporaire);
        }

        return optionSet.stream().toList();
    }
    private List<Espace> prepareEspaceData(MultipartFile file, String separator) throws IOException, ImportCsvException {
        List<EspaceCSV> espaces =  parseCSVFile(file, EspaceCSV.class, separator);

        List<EspaceCSV> espaceCSVList = this.validateDataCsv(espaces);
        Set<Espace> espaceSet = new HashSet<>();

        for (EspaceCSV espace : espaceCSVList) {
            Espace temporaire = new Espace();

            temporaire.setNom(espace.getNom());
            temporaire.setPrix(new BigDecimal(espace.getPrix()));

            espaceSet.add(temporaire);
        }

        return espaceSet.stream().toList();
    }


    private List<Option> uploadOptionsData(MultipartFile file, String separator) throws IOException, ImportCsvException {
        List<Option> options = this.prepareOptionData(file, separator);
        return this.optionRepository.saveAll(options);
    }
    private List<Espace> uploadEspaceData(MultipartFile file, String separator) throws IOException, ImportCsvException {
        List<Espace> espaces = this.prepareEspaceData(file, separator);
        return this.espaceRepository.saveAll(espaces);
    }
    private List<Paiement> uploadPaiementData(MultipartFile file, String separator) {
        return null;
    }
    private List<Reservation> uploadReservationData(MultipartFile file, String separator) {
        return null;
    }

    @Transactional
    public List<?> uploadCsvData(ImportationCsvDto info) throws ImportCsvException, IOException, TableNameNotFoundException {
        return switch (info.getTableName().toLowerCase()) {
            case "option" -> this.uploadOptionsData(info.getFile(), info.getSeparator());
            case "espace" -> this.uploadEspaceData(info.getFile(), info.getSeparator());
            case "reservation" -> this.uploadReservationData(info.getFile(), info.getSeparator());
            case "paiement" -> this.uploadPaiementData(info.getFile(), info.getSeparator());
            default -> throw new TableNameNotFoundException(info.getTableName());
        };
    }


}

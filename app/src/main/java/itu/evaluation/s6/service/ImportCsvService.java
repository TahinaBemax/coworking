package itu.evaluation.s6.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import itu.evaluation.s6.csvdto.OptionCSV;
import itu.evaluation.s6.model.Option;
import itu.evaluation.s6.repository.OptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ImportCsvService {
    private final OptionRepository optionRepository;

    @Autowired
    public ImportCsvService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    private List<OptionCSV> parseCSVFile(MultipartFile file) throws IOException {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<OptionCSV> csvToBean = new CsvToBeanBuilder<OptionCSV>(reader)
                    .withType(OptionCSV.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        }
    }

    private List<Option> prepareOptionEntityData(MultipartFile file) throws IOException{
        List<OptionCSV> optionCSVList = this.parseCSVFile(file);
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

    @Transactional
    public List<Option> uploadOptionsData(MultipartFile file) throws IOException {
        List<Option> options = this.prepareOptionEntityData(file);
        return this.optionRepository.saveAll(options);
    }
}

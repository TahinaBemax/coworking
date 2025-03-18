package itu.evaluation.s6.controller;

import itu.evaluation.s6.model.Option;
import itu.evaluation.s6.service.ImportCsvService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ImportCsvController {
    private final ImportCsvService importCsvService;

    public ImportCsvController(ImportCsvService importCsvService) {
        this.importCsvService = importCsvService;
    }

    @GetMapping("/uploads")
    public String importationPage(){
        return "importCsv/import_csv";
    }

    @PostMapping("/uploads/option")
    public ResponseEntity<?> uploadOption(@RequestParam("file") MultipartFile file) throws IOException {
        List<Option> options = importCsvService.uploadOptionsData(file);
        return new ResponseEntity<>("Data option téléversé", HttpStatus.OK);
    }
}

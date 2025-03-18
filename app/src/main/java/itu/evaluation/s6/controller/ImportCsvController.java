package itu.evaluation.s6.controller;

import itu.evaluation.s6.dto.ImportationCsvDto;
import itu.evaluation.s6.exception.ImportCsvException;
import itu.evaluation.s6.exception.TableNameNotFoundException;
import itu.evaluation.s6.service.ImportCsvService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class ImportCsvController {
    private final ImportCsvService importCsvService;

    public ImportCsvController(ImportCsvService importCsvService) {
        this.importCsvService = importCsvService;
    }

    @GetMapping("/uploads")
    public String importationPage(Model model){
        model.addAttribute("ImportationCsvDto", new ImportationCsvDto());
        return "importCsv/import_csv";
    }

    @PostMapping("/uploads")
    public String upload(@Validated
                                   @ModelAttribute("ImportationCsvDto") ImportationCsvDto importationCsvDto, BindingResult bindingResult, Model model) throws IOException {
        try {
            if (bindingResult.hasErrors()){
                return "importCsv/import_csv";
            }

            importCsvService.uploadCsvData(importationCsvDto);
            model.addAttribute("success", "Fichier csv importé avec succés");
        } catch (ImportCsvException e) {
            model.addAttribute("error", e.getMessages());
        } catch (TableNameNotFoundException e) {
            model.addAttribute("error", e.getMessage());
        }

        return importationPage(model);
    }

}

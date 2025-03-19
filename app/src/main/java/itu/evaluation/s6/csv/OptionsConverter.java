package itu.evaluation.s6.csv;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.bean.AbstractCsvConverter;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.util.Arrays;
import java.util.List;

public class OptionsConverter extends AbstractBeanField<List<String>, String> {
    @Override
    protected List<String> convert(String value) {
        // Ici, nous transformons la chaîne en une liste de String
        if (value != null && !value.isEmpty()) {
            return Arrays.asList(value.split(",\\s*"));
        }
        return null;  // Retourner null ou une liste vide selon le besoin
    }
}

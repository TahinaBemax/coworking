package itu.evaluation.s6.csv.csvdto;

import com.opencsv.bean.CsvBindByName;
import itu.evaluation.s6.validation.annotation.UniqueValue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EspaceCSV {
    @CsvBindByName(column = "nom")
    @NotNull(message = "{champ.notNull}")
    @NotBlank()
    @UniqueValue(message = "Le nom de l'espace doit étre unique!")
    String nom;

    @CsvBindByName(column = "prix_heure")
    @Min(value = 0, message = "{prix.positiveOrZero}")
    double prix;
}

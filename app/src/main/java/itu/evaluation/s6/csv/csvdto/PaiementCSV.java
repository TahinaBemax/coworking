package itu.evaluation.s6.csv.csvdto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;
import itu.evaluation.s6.csv.OptionsConverter;
import itu.evaluation.s6.validation.annotation.UniqueValue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class PaiementCSV {
    @CsvBindByName(column = "ref")
    @NotBlank()
    @NotNull(message = "{champ.notNull}")
    @UniqueValue(message = "La reference étre unique!")
    String ref;

    @CsvBindByName(column = "ref_paiement")
    @NotBlank()
    @NotNull(message = "{champ.notNull}")
    @UniqueValue(message = "La reference de paiement doit étre unique!")
    String ref_paiement;


    @CsvBindByName(column = "date")
    @NotNull(message = "{champ.notNull}")
    @CsvDate("dd/MM/yyyy")
    LocalDate dateReservation;
}

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
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class ReservationCSV {
    @CsvBindByName(column = "ref")
    @NotBlank()
    @NotNull(message = "{champ.notNull}")
    @UniqueValue(message = "La reference étre unique!")
    String ref;

    @CsvBindByName(column = "espace")
    @NotBlank(message = "{champ.notBlank}")
    @NotNull(message = "{champ.notNull}")
    String espaceName;

    @CsvBindByName(column = "client")
    @NotBlank(message = "{champ.notBlank}")
    @NotNull(message = "{champ.notNull}")
    String numeroTel;

    @CsvBindByName(column = "date")
    @NotNull(message = "{champ.notNull}")
    @CsvDate("dd/MM/yyyy")
    LocalDate dateReservation;

    @CsvBindByName(column = "heure_debut")
    @NotNull(message = "{champ.notNull}")
    @CsvDate("HH:mm")
    LocalTime heureDebut;

    @CsvBindByName(column = "duree")
    @Min(value = 1, message = "La valeur minimal de la durée est 1")
    @Max(value = 4, message = "La valeur maximal de la durée est 4")
    int duree;

    @NotNull(message = "{champ.notNull}")
    @CsvCustomBindByName(converter = OptionsConverter.class)
    List<String> option;
}

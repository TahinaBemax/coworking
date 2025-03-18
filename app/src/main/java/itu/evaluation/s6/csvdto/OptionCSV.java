package itu.evaluation.s6.csvdto;

import com.opencsv.bean.CsvBindByName;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import lombok.Setter;

@Setter
@Getter
public class OptionCSV {
    @CsvBindByName(column = "code")
    @NotNull(message = "{champ.notNull}")
    @NotBlank()
    String code;

    @CsvBindByName(column = "option")
    @NotNull(message = "{prix.notNull}")
    @NotBlank(message = "{champ.notNull}")
    String option;

    @CsvBindByName(column = "prix")
    @Min(value = 0, message = "{prix.positiveOrZero}")
    double prix;
}

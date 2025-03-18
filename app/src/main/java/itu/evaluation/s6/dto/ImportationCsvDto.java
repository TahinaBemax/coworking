package itu.evaluation.s6.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ImportationCsvDto {
    @NotNull(message = "{champ.notNull}")
    MultipartFile file;

    @NotNull(message = "{champ.notNull}")
    @NotBlank(message = "{champ.notBlank}")
    String separator;

    @NotNull(message = "{champ.notNull}")
    @NotBlank(message = "{champ.notBlank}")
    String tableName;
}

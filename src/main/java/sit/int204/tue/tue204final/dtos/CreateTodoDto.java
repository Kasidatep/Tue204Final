package sit.int204.tue.tue204final.dtos;



import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class CreateTodoDto {
    @NotNull
    @Size(min = 1, max = 255)
    private String title;
    @FutureOrPresent
    private ZonedDateTime completeDate;
    @Size(max = 50)
    private String owner;
}

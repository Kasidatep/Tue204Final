package sit.int204.tue.tue204final.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter @Setter
public class CreateTodoDto {
    private String title;
    private ZonedDateTime completeDate;
    private String owner;
}

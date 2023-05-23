package sit.int204.tue.tue204final.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class ReturnTodoPageDto {
    private Integer id;
    private String title;
    private ZonedDateTime completeDate;
    private String owner;

    public String getTitle() {
        return id +") " + title;
    }
}

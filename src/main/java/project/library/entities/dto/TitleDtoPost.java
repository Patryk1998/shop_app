package project.library.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@AllArgsConstructor
public class TitleDtoPost {
    private Long titleId;
    private String title;
    private String author;
    private int spendYear;
    private String type;
}

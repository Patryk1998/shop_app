package project.library.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RentDto {
    private String rentId;
    private String rentDate;
    private String bookName;
    private String userId;

}

package project.library.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PieceStatusEnum {
    AVAILABLE("available", true),
    DAMAGED_AVAILABLE("damaged", true),
    DAMAGED_UNAVAILABLE("damaged", false),
    ORDERED("ordered", false);

    private String status;
    private Boolean availability;
}

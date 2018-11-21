package project.library.entities;

import lombok.*;
import project.library.enums.PieceStatusEnum;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PIECE_STATUS")
public class PieceStatus {

    @Id
    @GeneratedValue
    @Column(name = "STATUS_ID")
    private Long id;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "AVAILABILITY")
    private Boolean availability;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "status")
    private List<Piece> pieces;
}

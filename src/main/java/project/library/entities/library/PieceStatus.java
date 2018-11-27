package project.library.entities.library;

import lombok.*;
import project.library.entities.library.Piece;

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

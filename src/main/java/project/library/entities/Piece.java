package project.library.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PIECES")
public class Piece {

    @Id
    @GeneratedValue
    @Column(name = "PIECE_ID")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "STATUS_OF_PIECE",
            joinColumns = {@JoinColumn(name = "PIECE_ID", referencedColumnName = "PIECE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "STATUS_ID", referencedColumnName = "STATUS_ID")}
    )
    private Set<PieceStatus> status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TITLE_ID")
    private Title title;
}

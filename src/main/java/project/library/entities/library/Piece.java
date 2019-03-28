package project.library.entities.library;

import lombok.*;
import project.library.entities.login.Role;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pieces")
public class Piece {

    @Id
    @GeneratedValue
    @Column(name = "piece_id")
    private Long id;

    @Column(name = "availability")
    private Boolean availability;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "title_id")
    private Title title;

    @OneToOne(mappedBy = "piece", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "rent_id")
    private Rent rent;

    public Piece(Boolean availability) {
        this.availability = availability;
    }
}

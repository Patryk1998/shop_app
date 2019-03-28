package project.library.entities.library;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "titles")
public class Title {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "title_id")
    private Long titleId;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "spend_date")
    private int spendYear;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "type")
    private Type type;

    @OneToMany(
            targetEntity = Piece.class,
            mappedBy = "title",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private Set<Piece> pieces = new HashSet<>();

    public void setPiece(Piece piece) {
        pieces.add(piece);
    }

    public Title(String title, String author, int spendYear) {
        this.title = title;
        this.author = author;
        this.spendYear = spendYear;
    }
}

package project.library.entities.library;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "types")
public class Type {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "type_id")
    private int typeId;

    @Column(name = "name")
    private String name;

    @OneToMany(
            targetEntity = Title.class,
            mappedBy = "type",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private Set<Title> titles = new HashSet<>();

    public Type(String name) {
        this.name = name;
    }

    public void setTitle(Title title) {
        titles.add(title);
    }
}

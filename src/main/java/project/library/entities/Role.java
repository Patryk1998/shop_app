package project.library.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy="role", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<User> users;

    public Role(String role) {
        this.role = role;
    }
    //@ManyToMany(mappedBy = "roles")
    //private List<User> users;

}

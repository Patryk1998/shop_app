package project.library.entities.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.library.entities.library.Piece;
import project.library.entities.library.Rent;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @NotNull
    @Column(name = "password")
    private String password;

    @Column(name = "reg_date")
    private LocalDate registrationDate;

    @Column(name = "active")
    private int active;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private Set<Rent> rents = new HashSet<>();

    public User(String email, String username, String name, String surname, String password) {
        this.email = email;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.registrationDate = LocalDate.now();
    }

    public User(Long id, String email, String username, String name, String surname, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.registrationDate = LocalDate.now();
    }

    public User(User user) {
        this.active = user.getActive();
        this.email = user.getEmail();
        this.registrationDate = LocalDate.now();
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

    public void setRent(Rent rent) {
        rents.add(rent);
    }
}

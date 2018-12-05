package project.library.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
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

    @NotNull
    @Column(name = "password")
    private String password;

    @Column(name = "reg_date")
    private LocalDate registrationDate;

    @Column(name = "active")
    private int active;

    @Column(name = "balance")
    private Integer balance;

    //    @ManyToMany
//    @JoinTable(
//            name = "user_roles",
//            joinColumns = @JoinColumn(
//                    name = "user_id", referencedColumnName = "user_id"),
//            inverseJoinColumns = @JoinColumn(
//                    name = "role_id", referencedColumnName = "role_id"))
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="role", nullable=false)    //@ElementCollection(targetClass=String.class)
    private Role role;

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
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
}

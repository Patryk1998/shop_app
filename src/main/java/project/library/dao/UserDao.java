package project.library.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.library.entities.login.User;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User save(User user);

    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

}

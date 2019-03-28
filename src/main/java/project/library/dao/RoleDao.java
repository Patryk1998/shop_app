package project.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import project.library.entities.login.Role;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Component
public interface RoleDao extends CrudRepository<Role, Long> {

    Optional<Role> findByRole(String role);
}

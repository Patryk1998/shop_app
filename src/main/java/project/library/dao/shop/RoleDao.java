package project.library.dao.shop;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import project.library.entities.Role;

import javax.transaction.Transactional;

@Transactional
@Component
public interface RoleDao extends CrudRepository<Role, Long> {
}

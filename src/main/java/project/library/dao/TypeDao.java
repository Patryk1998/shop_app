package project.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.library.entities.library.Type;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface TypeDao extends CrudRepository<Type, Long> {

    Optional<Type> findByName(String name);
}

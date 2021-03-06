package project.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.library.entities.library.Title;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface TitleDao extends CrudRepository<Title, Long> {

    Optional<Title> findByTitleId(Long id);
}

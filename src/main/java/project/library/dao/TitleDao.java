package project.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.library.entities.Title;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface TitleDao extends CrudRepository<Title, Long> {


}

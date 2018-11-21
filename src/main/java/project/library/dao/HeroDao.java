package project.library.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.library.entities.Hero;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface HeroDao extends CrudRepository<Hero, Integer> {

    List<Hero> findAll();

    Optional<Hero> findById(Integer id);
}

package project.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.library.entities.library.Piece;
import project.library.entities.library.Rent;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface RentDao extends CrudRepository<Rent, Long> {
}

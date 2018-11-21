package project.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.library.entities.Piece;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Transactional
@Repository
public interface PieceDao extends CrudRepository<Piece, Long> {
}

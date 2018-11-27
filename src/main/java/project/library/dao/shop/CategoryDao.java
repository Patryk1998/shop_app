package project.library.dao.shop;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.library.entities.shop.Category;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CategoryDao extends CrudRepository<Category, Long> {
}

package project.library.dao.shop;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.library.entities.shop.Product;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ProductDao extends CrudRepository<Product, Long> {
}

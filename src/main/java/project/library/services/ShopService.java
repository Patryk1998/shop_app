package project.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.library.dao.shop.CategoryDao;
import project.library.dao.shop.ProductDao;
import project.library.entities.shop.Category;
import project.library.entities.shop.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShopService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductDao productDao;

    public void createTest() {
        for(int i=0; i<10; i++) {
            Category category = new Category();
            category.setName("category" + i);
            categoryDao.save(category);

            Product product = new Product();
            product.setName("product" + i);
            product.setPrice(i);
            product.setQuantity(i);
            productDao.save(product);
        }
    }

    public List<Category> getCategories() {
        createTest();
        List<Category> categories = new ArrayList<>();
        categoryDao.findAll().forEach(category -> categories.add(category));
        return categories;
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        productDao.findAll().forEach(product -> products.add(product));
        return products;
    }
}

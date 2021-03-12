package codegym_ex_productmanager.DAO;

import codegym_ex_productmanager.model.Category;

public interface CategoryDAO {
    Iterable<Category> findAll();

    Category findById(Long id);

    void save(Category category);

    void remove(Long id);
}

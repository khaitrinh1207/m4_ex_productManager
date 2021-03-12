package codegym_ex_productmanager.DAO;

import codegym_ex_productmanager.model.Category;
import codegym_ex_productmanager.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryDAOImp implements CategoryDAO{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void remove(Long id) {
        categoryRepository.delete(id);
    }
}

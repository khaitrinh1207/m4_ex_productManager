package codegym_ex_productmanager.repository;

import codegym_ex_productmanager.model.Category;
import codegym_ex_productmanager.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {
    Iterable<Product> findAllByCategory(Category category);
    Page<Product> findAllByNameContaining(String name, Pageable pageable);
}

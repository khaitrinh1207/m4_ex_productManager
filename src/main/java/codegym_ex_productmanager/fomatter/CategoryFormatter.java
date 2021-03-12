package codegym_ex_productmanager.fomatter;

import codegym_ex_productmanager.DAO.CategoryDAO;
import codegym_ex_productmanager.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CategoryFormatter implements Formatter<Category> {
    private CategoryDAO categoryDAO;

    @Autowired
    public CategoryFormatter(CategoryDAO categoryDAO){
        this.categoryDAO = categoryDAO;
    }

    @Override
    public Category parse(String text, Locale locale) throws ParseException {
        return categoryDAO.findById(Long.parseLong(text));
    }

    @Override
    public String print(Category object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName() + "]";
    }
}

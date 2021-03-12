package codegym_ex_productmanager.controller;

import codegym_ex_productmanager.DAO.CategoryDAO;
import codegym_ex_productmanager.DAO.ProductDAO;
import codegym_ex_productmanager.model.Category;
import codegym_ex_productmanager.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @GetMapping
    public ModelAndView showListProduct(Pageable pageable) {
        Page<Product> products = productDAO.findAll(pageable);
        ModelAndView mav = new ModelAndView("/product/list", "products", products);
        return mav;
    }

    @GetMapping("/create-product")
    public ModelAndView showFormCreate() {
        ModelAndView mav = new ModelAndView("/product/create");
        mav.addObject("product", new Product());
        return mav;
    }

    @PostMapping("/create-product")
    public ModelAndView createProduct(@ModelAttribute Product product) {
        productDAO.save(product);
        ModelAndView mav = new ModelAndView("/product/create");
        mav.addObject("message", "New product created successfully");
        return mav;
    }

    @GetMapping("/edit-product/{id}")
    public ModelAndView showFormEdit(@PathVariable("id") Long id) {
        Product product = productDAO.findById(id);
        ModelAndView modelAndView;
        if (product != null) {
            modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("product", product);
        } else {
            modelAndView = new ModelAndView("/error.404");
        }
        return modelAndView;
    }

    @PostMapping("/edit-product")
    public ModelAndView editProduct(@ModelAttribute Product product){
        productDAO.save(product);
        ModelAndView mav = new ModelAndView("/product/edit");
        mav.addObject("product", product);
        mav.addObject("message", "Customer updated successfully");
        return mav;
    }

    @GetMapping("/delete-product/{id}")
    public ModelAndView showFormDel(@PathVariable Long id){
        Product product = productDAO.findById(id);
        if(product!=null){
            ModelAndView modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("product", product);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-product")
    public ModelAndView delProduct(@ModelAttribute Product product){
        productDAO.remove(product.getId());
        return new ModelAndView("redirect:/products");
    }


    //hiển thị category
    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return categoryDAO.findAll();
    }


    //search
    @GetMapping("/search")
    public ModelAndView listProducts(@RequestParam("s") Optional<String> s, Pageable pageable){
        Page<Product> products;
        if(s.isPresent()){
            products = productDAO.findAllByNameContaining(s.get(), pageable);
        } else {
            products = productDAO.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/product/search");
        modelAndView.addObject("products2", products);
        return modelAndView;
    }
}

package backendk24.bookstore.web;

import backendk24.bookstore.domain.Book;
import backendk24.bookstore.domain.Category;
import backendk24.bookstore.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import backendk24.bookstore.domain.BookRepository;
import backendk24.bookstore.web.BookController;

@Controller
public class BookController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BookRepository bookRepository;



    @RequestMapping(value = "/booklist", method = RequestMethod.GET)
    public String listBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }

    @RequestMapping(value = "/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll()); // Add categories to the model
        return "addbook";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("book") Book book, @RequestParam("category.id") Long categoryId) {

        Category selectedCategory = categoryRepository.findById(categoryId).orElseThrow();

        book.setCategory(selectedCategory);
        
        bookRepository.save(book);

        return "redirect:booklist";
    }




    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        bookRepository.deleteById(bookId);
        return "redirect:../booklist";
    }

    @RequestMapping(value= "/edit/{id}", method = RequestMethod.GET)
    public String showModBook(@PathVariable("id") Long bookId, Model model) {
        model.addAttribute(("book"), bookRepository.findById(bookId));
        return "editbook";
    }

    @RequestMapping(value = "categorylist", method = RequestMethod.GET)
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categorylist";
    }
    @RequestMapping(value = "/addcategory")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return ("addcategory");
    }
    @RequestMapping(value = "/savecategory", method = RequestMethod.POST)
    public String saveCategory(Category category) {
        categoryRepository.save(category);
        return "redirect:categorylist";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

}

package org.example.controllеrs;

import org.example.dao.BookDAO;
import org.example.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;

    @Autowired
    public BooksController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String booksMainPage(Model model) {
        model.addAttribute("books", bookDAO.getBooks());
        return "books/main";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String saveNewBook(@ModelAttribute("book") @Valid Book book,
                              BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        bookDAO.saveBook(book);

        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String getBook(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.getBookById(id));
        return "books/book";
    }

    @GetMapping("/{id}/edit")
    public String editBook(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.getBookById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult,
                             @PathVariable("id") int id)
    {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        bookDAO.updateBook(id, book);

        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.deleteBookById(id);
        return "redirect:/books";
    }
}

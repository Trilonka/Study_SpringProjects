package org.example.controllеrs;

import org.example.dao.BookDAO;
import org.example.dao.PersonDAO;
import org.example.models.Book;
import org.example.util.BookValidator;
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
    private final PersonDAO personDAO;
    private final BookValidator bookValidator;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO, BookValidator bookValidator) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String booksMainPage(Model model) {
        model.addAttribute("books", bookDAO.show());
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
        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        bookDAO.save(book);

        return "redirect:/books";
    }

    @GetMapping("/{book_id}")
    public String getBook(Model model, @PathVariable("book_id") int id) {
        model.addAttribute("book", bookDAO.getById(id));
        model.addAttribute("people", personDAO.show());
        model.addAttribute("person", personDAO.getById(
                bookDAO.getById(id).getPerson_id()));
        return "books/book";
    }

    @GetMapping("/{book_id}/edit")
    public String editBook(Model model, @PathVariable("book_id") int id) {
        model.addAttribute("book", bookDAO.getById(id));
        return "books/edit";
    }

    @PatchMapping("/{book_id}/updatePerson")
    public String updatePerson(Model model,
                               @ModelAttribute("book") Book book,
                               @PathVariable("book_id") int book_id)
    {
        bookDAO.updatePerson_id(book_id, book.getPerson_id());
        return "redirect:/books/" + book_id;
    }

    @PatchMapping("/{book_id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult,
                             @PathVariable("book_id") int id)
    {
        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        bookDAO.update(id, book);
        bookDAO.updatePerson_id(id, book.getPerson_id());

        return "redirect:/books/" + id;
    }

    @DeleteMapping("/{book_id}")
    public String deleteBook(@PathVariable("book_id") int id) {
        bookDAO.deleteById(id);
        return "redirect:/books";
    }
}

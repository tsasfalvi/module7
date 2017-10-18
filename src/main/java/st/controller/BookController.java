package st.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import st.entity.Book;
import st.repository.BookRepository;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

//@RestController
//@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(method = GET)
    public Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }
}

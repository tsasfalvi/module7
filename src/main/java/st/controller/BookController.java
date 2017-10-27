package st.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import st.dto.Book;
import st.dto.Borrow;
import st.dto.Handover;
import st.dto.ReturnBook;
import st.dto.User;
import st.service.BookService;
import st.service.BorrowFacade;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/book")
public class BookController {
    private BookService bookService;
    private BorrowFacade borrowFacade;

    public BookController(BookService bookService, BorrowFacade borrowFacade) {
        this.bookService = bookService;
        this.borrowFacade = borrowFacade;
    }

    @ResponseBody
    @RequestMapping(method = GET)
    public Iterable<Book> getAll() {
        return bookService.getAllBooks();
    }

    @ResponseBody
    @RequestMapping(method = POST)
    public ResponseEntity<Book> save(@RequestBody Book book) {
        Book result = bookService.saveOrUpdate(book);

        return new ResponseEntity<>(result, CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/{bookId}", method = PUT)
    public ResponseEntity<Book> update(@PathVariable long bookId, @RequestBody Book book) {
        book.setId(bookId);
        Book result = bookService.saveOrUpdate(book);

        return new ResponseEntity<>(result, OK);
    }

    @ResponseBody
    @RequestMapping(value = "/{bookId}", method = GET)
    public Book get(@PathVariable int bookId) {
        return bookService.getBook(bookId);
    }

    @ResponseBody
    @RequestMapping(value = "/{bookId}", method = DELETE)
    public ResponseEntity<Object> remove(@PathVariable int bookId) {
        bookService.delete(bookId);

        return new ResponseEntity<>(NO_CONTENT);
    }

    @ResponseBody
    @RequestMapping(value = "/borrow", method = POST)
    public ResponseEntity<User> borrow(@RequestBody @Valid Borrow borrow) {
        User result = borrowFacade.borrow(borrow);

        return new ResponseEntity<>(result, CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/borrow", method = PUT)
    public ResponseEntity<User> updateBorrow(@RequestBody @Valid Borrow borrow) {
        User result = borrowFacade.updateBorrowTime(borrow);

        return new ResponseEntity<>(result, CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/returnBook", method = DELETE)
    public ResponseEntity<User> returnBook(@RequestBody @Valid ReturnBook returnBook) {
        User result = borrowFacade.returnBook(returnBook);

        return new ResponseEntity<>(result, OK);
    }

    @ResponseBody
    @RequestMapping(value = "/handover", method = PUT)
    public ResponseEntity<User> updateBorrow(@RequestBody @Valid Handover handover) {
        User result = borrowFacade.handover(handover);

        return new ResponseEntity<>(result, OK);
    }
}

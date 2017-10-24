package st.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import st.dto.Book;
import st.dto.Borrow;
import st.entity.BookEntity;
import st.service.BookService;
import st.service.facade.BorrowFacade;
import st.service.facade.BorrowFacade.BorrowResult;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/book")
public class BookController {
    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;
    @Autowired
    private BorrowFacade borrowFacade;

    @ResponseBody
    @RequestMapping(method = GET)
    public Iterable<BookEntity> getAll() {
        return bookService.getAllBooks();
    }

    @ResponseBody
    @RequestMapping(method = POST)
    public Book save(@RequestBody Book book) {
        LOG.info("Getting book to save: {}, {}", book.getAuthor(), book.getTitle());
        return bookService.saveOrUpdate(book);
    }

    @ResponseBody
    @RequestMapping(value = "/{bookId}", method = PUT)
    public Book update(@RequestBody Book book) {
        LOG.info("Getting book to update: {}, {}, {}", book.getId(), book.getAuthor(), book.getTitle());
        return bookService.saveOrUpdate(book);
    }

    @ResponseBody
    @RequestMapping(value = "/{bookId}", method = GET)
    public Book getOne(@PathVariable int bookId) {
        return bookService.getBook(bookId);
    }

    @ResponseBody
    @RequestMapping(value = "/{bookId}", method = DELETE)
    public void remove(@PathVariable int bookId) {
        bookService.delete(bookId);
    }

    @ResponseBody
    @RequestMapping(value = "/borrow", method = POST)
    public BorrowResult borrow(@RequestBody @Valid Borrow borrow) {
        return borrowFacade.borrow(borrow);
    }
}

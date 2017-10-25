package st.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import st.dto.Book;
import st.dto.Borrow;
import st.dto.Handover;
import st.dto.ReturnBook;
import st.entity.BookEntity;
import st.service.BookService;
import st.service.BorrowFacade;
import st.service.BorrowFacade.BorrowResult;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/book")
public class BookController {
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
    public void save(@RequestBody Book book) {
        bookService.saveOrUpdate(book);
    }

    @ResponseBody
    @RequestMapping(value = "/{bookId}", method = PUT)
    public void update(@RequestBody Book book) {
        bookService.saveOrUpdate(book);
    }

    @ResponseBody
    @RequestMapping(value = "/{bookId}", method = GET)
    public Book get(@PathVariable int bookId) {
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

    @ResponseBody
    @RequestMapping(value = "/borrow", method = PUT)
    public boolean updateBorrow(@RequestBody @Valid Borrow borrow) {
        return borrowFacade.updateBorrowTime(borrow);
    }

    @ResponseBody
    @RequestMapping(value = "/returnBook", method = DELETE)
    public boolean returnBook(@RequestBody @Valid ReturnBook returnBook) {
        return borrowFacade.returnBook(returnBook);
    }

    @ResponseBody
    @RequestMapping(value = "/handover", method = PUT)
    public boolean updateBorrow(@RequestBody @Valid Handover handover) {
        return borrowFacade.handover(handover);
    }
}

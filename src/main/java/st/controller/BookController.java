package st.controller;

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

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
    @RequestMapping(value = "/{bookId}", method = GET)
    public Book subscribe(@PathVariable int bookId) {
        return bookService.getBook(bookId);
    }

    @ResponseBody
    @RequestMapping(value = "/borrow", method = POST)
    public BorrowResult borrow(@RequestBody @Valid Borrow borrow) {
        return borrowFacade.borrow(borrow);
    }
}

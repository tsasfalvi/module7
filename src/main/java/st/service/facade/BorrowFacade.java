package st.service.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import st.dto.Borrow;
import st.entity.BookEntity;
import st.entity.BorrowEntity;
import st.entity.UserEntity;
import st.repository.BookRepository;
import st.service.BookService;
import st.service.UserService;

import static st.service.facade.BorrowFacade.BorrowResult.FAILED;
import static st.service.facade.BorrowFacade.BorrowResult.SUCCESS;

@Service
public class BorrowFacade {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @Transactional
    public BorrowResult borrow(Borrow borrow) {
        try {
            BookEntity bookEntity = bookRepository.findOne(borrow.getBookId());
            UserEntity currentUser = userService.getCurrentUser();

            BorrowEntity borrowEntity = new BorrowEntity();
            borrowEntity.setUser(currentUser);
            borrowEntity.setBook(bookEntity);
            borrowEntity.setTill(borrow.getTill());

            bookEntity.getBorrows().add(borrowEntity);
            bookService.update(bookEntity);
        } catch (Exception e) {
            return FAILED;
        }

        return SUCCESS;
    }

    public enum BorrowResult {
        SUCCESS, FAILED
    }

}

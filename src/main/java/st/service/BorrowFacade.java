package st.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import st.dto.Borrow;
import st.dto.Handover;
import st.dto.ReturnBook;
import st.entity.BookEntity;
import st.entity.BorrowEntity;
import st.entity.UserEntity;
import st.repository.BookRepository;

import java.util.Objects;

import static st.service.BorrowFacade.BorrowResult.FAILED;
import static st.service.BorrowFacade.BorrowResult.SUCCESS;

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

    public boolean updateBorrowTime(Borrow borrow) {
        UserEntity currentUser = userService.getCurrentUser();
        currentUser
                .getBorrows()
                .stream()
                .filter(borrowEntity -> Objects.equals(borrowEntity.getBook().getId(), borrow.getBookId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Borrow not found"))
                .setTill(borrow.getTill());

        userService.update(currentUser);

        return true;
    }

    public boolean handover(Handover handover) {
        UserEntity user = userService.getUser(handover.getUser());
        user
                .getBorrows()
                .stream()
                .filter(borrowEntity -> Objects.equals(borrowEntity.getBook().getId(), handover.getBookId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Borrow not found"))
                .setHandedOver(true);

        userService.update(user);

        return true;
    }

    public boolean returnBook(ReturnBook returnBook) {
        UserEntity user = userService.getUser(returnBook.getUser());
        BorrowEntity borrow = user
                .getBorrows()
                .stream()
                .filter(borrowEntity -> Objects.equals(borrowEntity.getBook().getId(), returnBook.getBookId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Borrow not found"));

        borrow.setHandedOver(false);

        userService.update(user);

        return true;
    }

    public enum BorrowResult {
        SUCCESS, FAILED
    }

}

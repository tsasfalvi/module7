package st.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import st.dto.Borrow;
import st.dto.Handover;
import st.dto.ReturnBook;
import st.dto.User;
import st.entity.BookEntity;
import st.entity.BorrowEntity;
import st.entity.UserEntity;
import st.repository.BookRepository;
import st.repository.BorrowRepository;

import javax.persistence.EntityManager;
import java.util.Objects;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
public class BorrowFacade {
    private BookRepository bookRepository;
    private UserService userService;
    private BookService bookService;
    private final BorrowRepository borrowRepository;
    private final EntityManager entityManager;

    public BorrowFacade(BookRepository bookRepository, UserService userService, BookService bookService, BorrowRepository borrowRepository, EntityManager entityManager) {
        this.bookRepository = bookRepository;
        this.userService = userService;
        this.bookService = bookService;
        this.borrowRepository = borrowRepository;
        this.entityManager = entityManager;
    }

    @Transactional
    public User borrow(Borrow borrow) {
        BookEntity bookEntity = bookRepository.findOne(borrow.getBookId());
        UserEntity currentUser = userService.getCurrentUser();


        BorrowEntity borrowEntity = new BorrowEntity();
        borrowEntity.setUser(currentUser);
        borrowEntity.setBook(bookEntity);
        borrowEntity.setTill(borrow.getTill());

        currentUser.getBorrows().add(borrowEntity);
        return userService.update(currentUser);
    }

    public User updateBorrowTime(Borrow borrow) {
        UserEntity currentUser = userService.getCurrentUser();
        currentUser
                .getBorrows()
                .stream()
                .filter(borrowEntity -> Objects.equals(borrowEntity.getBook().getId(), borrow.getBookId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Borrow not found"))
                .setTill(borrow.getTill());

        return userService.update(currentUser);
    }

    public User handover(Handover handover) {
        UserEntity user = userService.getUserEntity(handover.getUser());
        user
                .getBorrows()
                .stream()
                .filter(borrowEntity -> Objects.equals(borrowEntity.getBook().getId(), handover.getBookId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Borrow not found"))
                .setHandedOver(true);

        return userService.update(user);
    }

    @Transactional(propagation = REQUIRED)
    public User returnBook(ReturnBook returnBook) {
        UserEntity user = userService.getUserEntity(returnBook.getUser());
        BorrowEntity borrow = user
                .getBorrows()
                .stream()
                .filter(borrowEntity -> Objects.equals(borrowEntity.getBook().getId(), returnBook.getBookId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Borrow not found"));

        borrow.setHandedOver(false);

        user.getBorrows().remove(borrow);
        User updated = userService.update(user);

        entityManager.remove(borrow);

        return updated;
    }
}

package st.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import st.dto.User;
import st.entity.BookEntity;
import st.entity.UserEntity;
import st.exception.SuspendedException;
import st.repository.BookRepository;

@Service
public class SubscriptionFacade {
    @Autowired
    private UserService userService;
    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public User subscribe(long bookId) throws SuspendedException {
        UserEntity currentUser = userService.getCurrentUser();

        if (currentUser.isSuspended()) {
            throw new SuspendedException("your account is suspended currently");
        }

        BookEntity bookEntity = bookRepository.findOne(bookId);
        currentUser.getSubscriptions().add(bookEntity);
        return userService.update(currentUser);
    }
}

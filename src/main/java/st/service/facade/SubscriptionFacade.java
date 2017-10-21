package st.service.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import st.entity.BookEntity;
import st.entity.UserEntity;
import st.repository.BookRepository;
import st.service.UserService;

@Service
public class SubscriptionFacade {
    @Autowired
    private UserService userService;
    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public boolean subscribe(long bookId) {
        try {
            UserEntity currentUser = userService.getCurrentUser();
            BookEntity bookEntity = bookRepository.findOne(bookId);
            currentUser.getSubscriptions().add(bookEntity);
            userService.update(currentUser);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}

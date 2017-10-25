package st.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import st.dto.Book;
import st.entity.BookEntity;
import st.repository.BookRepository;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
public class BookService {
    private static final Logger LOG = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Iterable<BookEntity> getAllBooks() {
        Iterable<BookEntity> all = bookRepository.findAll();
        return modelMapper.map(all, new TypeToken<List<Book>>() {
        }.getType());
    }

    public Book getBook(long id) {
        return modelMapper.map(bookRepository.findOne(id), Book.class);
    }

    @Transactional(propagation = REQUIRED)
    public void update(BookEntity bookEntity) {
        bookRepository.save(bookEntity);
    }

    @Transactional(propagation = REQUIRED)
    public void saveOrUpdate(Book book) {
        BookEntity bookEntity;
        if (book.getId() != null) {
            bookEntity = bookRepository.findOne(book.getId());
            LOG.info("Loaded bookEntity: {}", bookEntity);
        } else {
            bookEntity = new BookEntity();
        }
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthor(book.getAuthor());
        bookRepository.save(bookEntity);
    }

    public void delete(long bookId) {
        bookRepository.delete(bookId);
    }
}

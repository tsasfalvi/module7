package st.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import st.entity.BookEntity;

public interface BookRepository extends PagingAndSortingRepository<BookEntity, Long> {

}

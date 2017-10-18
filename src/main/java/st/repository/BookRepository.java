package st.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import st.entity.Book;

@RepositoryRestResource(collectionResourceRel = "book", path = "book")
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

}

package st.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import st.entity.UserEntity;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, String> {
}

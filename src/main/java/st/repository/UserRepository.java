package st.repository;

import org.springframework.data.repository.CrudRepository;
import st.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}

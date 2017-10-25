package st.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import st.entity.BorrowEntity;

import java.time.LocalDate;
import java.util.List;

public interface BorrowRepository extends JpaRepository<BorrowEntity, BorrowEntity.Pk> {
    @Query("select s from BorrowEntity s where s.handedOver = true and s.till < ?1")
    List<BorrowEntity> findAllOutdated(LocalDate localDate);
}

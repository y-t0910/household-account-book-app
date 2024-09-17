package jp.co.hoge.web.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.hoge.web.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId AND t.date BETWEEN :start AND :end")
    List<Transaction> findByUserIdAndDateBetween(@Param("userId") Long userId, 
                                                 @Param("start") LocalDate start, 
                                                 @Param("end") LocalDate end);
}

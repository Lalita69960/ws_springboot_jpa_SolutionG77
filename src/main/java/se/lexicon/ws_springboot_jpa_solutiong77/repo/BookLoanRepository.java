package se.lexicon.ws_springboot_jpa_solutiong77.repo;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.ws_springboot_jpa_solutiong77.entity.BookLoan;

import java.time.LocalDate;
import java.util.List;

public interface BookLoanRepository extends JpaRepository<BookLoan,Integer> {

//
    List<BookLoan> findByBorrowerId(int borrowerId);

    List<BookLoan> findByBookId(int bookId);

    List<BookLoan> findByReturnedFalse();

    List<BookLoan> findByDueDateBeforeAndReturnedFalse(LocalDate date);

    List<BookLoan> findByLoanDateBetween(LocalDate start, LocalDate end);

    @Transactional
    @Modifying
    @Query("update BookLoan b set b.returned = true where b.id = :id")
    int markAsReturnedById(@Param("id") int id);
}




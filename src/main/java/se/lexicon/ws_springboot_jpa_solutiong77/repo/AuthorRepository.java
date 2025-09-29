package se.lexicon.ws_springboot_jpa_solutiong77.repo;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.ws_springboot_jpa_solutiong77.entity.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Integer> {

    List<Author> findByFirstName(String firstName);

    List<Author> findByLastName(String lastName);

    List<Author> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String fn, String ln);

    @Query("select a from Author a join a.books b where b.id = :bookId")
    List<Author> findByBookId(@Param("bookId") int bookId);

    @Transactional
    @Modifying
    @Query("update Author a set a.firstName = :firstName, a.lastName = :lastName where a.id = :id")
    int updateNameById(@Param("id") int id,
                       @Param("firstName") String firstName,
                       @Param("lastName") String lastName);

    void deleteById(int id);





}

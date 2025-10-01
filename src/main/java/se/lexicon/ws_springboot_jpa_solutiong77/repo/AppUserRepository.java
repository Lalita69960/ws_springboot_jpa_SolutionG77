package se.lexicon.ws_springboot_jpa_solutiong77.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.ws_springboot_jpa_solutiong77.entity.AppUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    // Find by username
    Optional<AppUser> findByUsername(String username);

    // Find by registration date between two dates
    List<AppUser> findByRegDateBetween(LocalDate startDate, LocalDate endDate);

    // Find by Details id
    Optional<AppUser> findByUserDetails_Id(Integer detailsId);

    // Find by email inside Details (case-insensitive)
    Optional<AppUser> findByUserDetails_EmailIgnoreCase(String email);

}

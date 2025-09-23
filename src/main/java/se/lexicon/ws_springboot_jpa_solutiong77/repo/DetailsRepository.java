package se.lexicon.ws_springboot_jpa_solutiong77.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.ws_springboot_jpa_solutiong77.entity.AppUser;
import se.lexicon.ws_springboot_jpa_solutiong77.entity.Details;

import java.util.List;
import java.util.Optional;
@Repository
public interface DetailsRepository extends JpaRepository<Details, Integer> {
    // Find by email
    Optional<Details> findByEmail(String email);

    // Find by name containing a string
    List<Details> findByNameContains(String fragment);

    // Find by name ignoring case
    List<Details> findByNameIgnoreCase(String name);
}
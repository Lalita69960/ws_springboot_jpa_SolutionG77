package se.lexicon.ws_springboot_jpa_solutiong77.repo;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.ws_springboot_jpa_solutiong77.entity.AppUser;
import se.lexicon.ws_springboot_jpa_solutiong77.entity.Details;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private DetailsRepository detailsRepository;

    @Test
    @DisplayName("Find AppUser by username")
    void testFindByUsername() {
        Details details = new Details("john@example.com", "John Doe", LocalDate.of(1990, 5, 20));
        detailsRepository.save(details);

        AppUser user = new AppUser("johndoe", "secret123");
        user.setUserDetails(details);
        appUserRepository.save(user);

        Optional<AppUser> found = appUserRepository.findByUsername("johndoe");

        assertThat(found).isPresent();
        assertThat(found.get().getUsername()).isEqualTo("johndoe");

    }
    @Test
    void testFindByRegDateBetween() {
        // create a user whose regDate will be set by @PrePersist to LocalDate.now()
        AppUser u1 = new AppUser("user_reg_test", "pwd");
        appUserRepository.save(u1);

        // choose a range that includes today (PrePersist sets regDate = LocalDate.now())
        LocalDate start = LocalDate.now().minusDays(1);
        LocalDate end = LocalDate.now().plusDays(1);

        // find
        List<AppUser> users = appUserRepository.findByRegDateBetween(start, end);

        // IMPORTANT: this method returns List<AppUser> (not Optional) — assert on the list
        assertFalse(users.isEmpty(), "Expected at least one user in the date range");
        assertTrue(users.stream().anyMatch(u -> "user_reg_test".equals(u.getUsername())));


    }


    @Test
    void testFindByUserDetails_Id() {
        Details details = new Details("bob@example.com", "Bob Example", LocalDate.of(1990, 1, 1));
        detailsRepository.save(details);

        AppUser user = new AppUser("bob", "pw");
        user.setUserDetails(details);
        appUserRepository.save(user);

        Optional<AppUser> found = appUserRepository.findByUserDetails_Id(details.getId());
        assertTrue(found.isPresent(), "Expected to find AppUser by details id");
        assertEquals("bob", found.get().getUsername());
    }






    @Test
    void testFindByUserDetails_EmailIgnoreCase() {
        Details details = new Details("CaseEmail@Example.COM", "Case Test", LocalDate.of(1995, 5, 5));
        detailsRepository.save(details);

        AppUser user = new AppUser("caseuser", "pw");
        user.setUserDetails(details);
        appUserRepository.save(user);

        Optional<AppUser> found = appUserRepository.findByUserDetails_EmailIgnoreCase("caseemail@example.com");
        assertTrue(found.isPresent(), "Expected to find AppUser by email ignoring case");
        assertEquals("caseuser", found.get().getUsername());
    }




}
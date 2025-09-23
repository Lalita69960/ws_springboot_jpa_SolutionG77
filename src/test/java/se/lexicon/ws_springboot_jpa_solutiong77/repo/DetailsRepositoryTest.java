package se.lexicon.ws_springboot_jpa_solutiong77.repo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.ws_springboot_jpa_solutiong77.entity.Details;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DetailsRepositoryTest {

    @Autowired
    private DetailsRepository detailsRepository;

    @Test
    @DisplayName("findByEmail - returns matching Details")
    void testFindByEmail() {
        // Required-args constructor (Lombok @RequiredArgsConstructor) expects (email, name, birthDate)
        Details d = new Details("anna@example.com", "Anna Smith", LocalDate.of(1992, 3, 15));
        detailsRepository.save(d);

        Optional<Details> found = detailsRepository.findByEmail("anna@example.com");

        assertTrue(found.isPresent(), "Expected an entry for anna@example.com");
        assertEquals("Anna Smith", found.get().getName());


    }

    @Test
    @DisplayName("findByNameContains - finds entries whose name contains fragment")
    void testFindByNameContains() {
        Details a = new Details("anna.smith@example.com", "Anna Smith", LocalDate.of(1992, 3, 15));
        Details b = new Details("john.smith@example.com", "John Smith", LocalDate.of(1990, 1, 1));
        Details c = new Details("kate.brown@example.com", "Kate Brown", LocalDate.of(1988, 6, 6));
        detailsRepository.saveAll(List.of(a, b, c));

        List<Details> smiths = detailsRepository.findByNameContains("Smith");

        assertFalse(smiths.isEmpty(), "Expected at least one 'Smith'");
        // Assert that both expected names are present
        assertTrue(smiths.stream().anyMatch(d -> "Anna Smith".equals(d.getName())));
        assertTrue(smiths.stream().anyMatch(d -> "John Smith".equals(d.getName())));

    }


    @Test
    @DisplayName("findByNameIgnoreCase - exact name match ignoring case")
    void testFindByNameIgnoreCase() {
        Details d = new Details("case@example.com", "Case Test", LocalDate.of(1995, 5, 5));
        detailsRepository.save(d);

        List<Details> found = detailsRepository.findByNameIgnoreCase("case test");

        assertFalse(found.isEmpty(), "Expected to find 'Case Test' ignoring case");
        assertTrue(found.stream().anyMatch(p -> p.getEmail().equals("case@example.com")));
    }

}
package se.lexicon.ws_springboot_jpa_solutiong77.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.sql.results.spi.LoadContexts;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor

public class Details {

    @Id
    @GeneratedValue(strategy =jakarta.persistence.GenerationType.IDENTITY)
    int id;

    @Column(unique = true)
    @NonNull
    String email;

    @NonNull
    String name;
    //

    @NonNull
    LocalDate birthDate;


//

}

package se.lexicon.ws_springboot_jpa_solutiong77.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class AppUser {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private int id;

    @Setter
    @Column(unique = true)
    private String username;


@Setter
   private String password;

   private LocalDate regDate;


    @OneToOne
    @JoinColumn(name = "details_id")
    @Setter
   private Details userDetails;

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;

    }

   @PrePersist
    private void onCreate() {

       this.regDate = LocalDate.now();

   }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AppUser appUser)) return false;
        return id == appUser.id && Objects.equals(username, appUser.username) && Objects.equals(password, appUser.password) && Objects.equals(regDate, appUser.regDate) && Objects.equals(userDetails, appUser.userDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, regDate, userDetails);
    }


}

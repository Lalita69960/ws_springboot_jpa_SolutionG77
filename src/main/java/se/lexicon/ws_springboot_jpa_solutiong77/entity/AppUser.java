package se.lexicon.ws_springboot_jpa_solutiong77.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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


    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "details_id")
    @Setter
   private Details userDetails;

    @OneToMany(mappedBy = "borrower", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<BookLoan> loans = new HashSet<>();


    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;

    }

   @PrePersist
    private void onCreate() {

       this.regDate = LocalDate.now();

   }


    public void addLoan(BookLoan loan) {
        loans.add(loan);
        loan.setBorrower(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return id == appUser.id && Objects.equals(username, appUser.username) && Objects.equals(password, appUser.password) && Objects.equals(regDate, appUser.regDate) && Objects.equals(userDetails, appUser.userDetails) && Objects.equals(loans, appUser.loans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, regDate, userDetails, loans);
    }

    public void removeLoan(BookLoan loan) {
        loans.remove(loan);
        if (loan.getBorrower() == this) {
            loan.setBorrower(null);
        }


    }
}

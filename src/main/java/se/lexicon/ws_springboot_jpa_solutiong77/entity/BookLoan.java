package se.lexicon.ws_springboot_jpa_solutiong77.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Objects;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate loanDate;

    private  LocalDate dueDate;

    private boolean returned=false;

@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name="borrow_id",nullable = false)
    private AppUser borrower;

@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name="book_id",nullable = false)
    private Book book;


    private void onCreate() {
        if (loanDate == null) {
            loanDate = LocalDate.now();
        }
        if (book != null && dueDate == null) {
            dueDate = loanDate.plusDays(book.getMaxLoanDays());
        }

    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BookLoan bookLoan)) return false;
        return id == bookLoan.id && returned == bookLoan.returned && Objects.equals(loanDate, bookLoan.loanDate) && Objects.equals(dueDate, bookLoan.dueDate) && Objects.equals(borrower, bookLoan.borrower) && Objects.equals(book, bookLoan.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loanDate, dueDate, returned, borrower, book);
    }
}

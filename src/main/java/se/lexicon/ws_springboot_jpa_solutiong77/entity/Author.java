package se.lexicon.ws_springboot_jpa_solutiong77.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "books")

public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

   @ManyToMany
   @JoinTable( // OneToMany x2
           name = "students_courses", //name of the join Table
           joinColumns = @JoinColumn(name= "author_id"), // Owning Side
           inverseJoinColumns = @JoinColumn(name ="book_id") // Target Entity
   )
   private Set<Book> books = new HashSet<>();






}

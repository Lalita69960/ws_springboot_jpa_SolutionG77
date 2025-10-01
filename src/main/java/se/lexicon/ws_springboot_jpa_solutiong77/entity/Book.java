package se.lexicon.ws_springboot_jpa_solutiong77.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString


public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private  int id;


    @NonNull
    @Column(nullable = false,unique = true)
   private  String isbn;


    @NonNull
    @Column(nullable = false)
   private  String title;
//

    @NonNull
   private int maxLoanDays;



}

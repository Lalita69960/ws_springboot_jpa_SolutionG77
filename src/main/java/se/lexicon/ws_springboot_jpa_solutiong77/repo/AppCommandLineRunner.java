package se.lexicon.ws_springboot_jpa_solutiong77.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.ws_springboot_jpa_solutiong77.entity.*;

import java.time.LocalDate;


@Component
public class AppCommandLineRunner implements CommandLineRunner {
    AppUserRepository appUserRepository;
    AuthorRepository authorRepository;
    BookRepository bookRepository;
    BookLoanRepository bookLoanRepository;
    DetailsRepository detailsRepository;

    @Autowired
    public AppCommandLineRunner(AppUserRepository appUserRepository, AuthorRepository authorRepository, BookRepository bookRepository, BookLoanRepository bookLoanRepository, DetailsRepository detailsRepository) {
        this.appUserRepository = appUserRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.bookLoanRepository = bookLoanRepository;
        this.detailsRepository = detailsRepository;
    }

    @Override
    public void run(String... args) throws Exception {

             //------oneToone--------unidirection
        //AppUser appUser = new AppUser("pratima", "Lalita123456");
       // Details details = new Details("lalitabisht@gmail.com","pratima", LocalDate.of(87,12,13));
       // appUser.setUserDetails(details);
       // appUserRepository.save(appUser);
        //appUserRepository.deleteById(1);


          //-------manyTOone---oneTomany(not sucessing justbecause @Requiredargument)



                 //------many to many------unidirection
        Author author=new Author("pratima","Sharma");
        Book book1= new Book("A2000","Javabook2",20);
       // author.addBook(book1);

      authorRepository.save(author);

        author.removeBook(book1);


        //authorRepository.deleteById(1);








    }
}

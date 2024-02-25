package backendk24.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backendk24.bookstore.domain.Book;
import backendk24.bookstore.domain.BookRepository;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class BookstoreApplication {
private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}


	@Bean
	public CommandLineRunner BookRunner(BookRepository bookRepository) {
		return (args) -> {
			log.info("save");
			bookRepository.save(new Book("The Fellowship of the Ring: Being the First Part of The Lord of the Rings (The Lord of the Rings, 1)", "J.R.R. Tolkien", 1954, "9780358380238", 55.95));
			bookRepository.save(new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", 1997, "978-0747532743", 16.39));
			bookRepository.save(new Book("Linux Bible, 10th Edition", "Christopher Negus", 2020, "978-1-119-57889-5", 38.00));

			log.info("fetch");

			};

		};
}


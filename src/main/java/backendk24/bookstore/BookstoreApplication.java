package backendk24.bookstore;

import backendk24.bookstore.domain.*;
import backendk24.bookstore.domain.UserRepository;
import backendk24.bookstore.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}


	@Bean
	public CommandLineRunner bookRunner(BookRepository bookRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
		return (args) -> {
			log.info("save");

			Category category1 = new Category("Fantasy");
			categoryRepository.save(category1);

			Category category2 = new Category("Scifi");
			categoryRepository.save(category2);

			Category category3 = new Category("Comic");
			categoryRepository.save(category3);

			Category category4 = new Category("Education");
			categoryRepository.save(category4);

			Book book1 = new Book("The Fellowship of the Ring: Being the First Part of The Lord of the Rings (The Lord of the Rings, 1)", "J.R.R. Tolkien", 1954, "9780358380238", 55.95);
			book1.setCategory(category1);
			bookRepository.save(book1);

			Book book2 = new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", 1997, "978-0747532743", 16.39);
			book2.setCategory(category2);
			bookRepository.save(book2);

			Book book3 = new Book("Linux Bible, 10th Edition", "Christopher Negus", 2020, "978-1-119-57889-5", 38.00);
			book3.setCategory(category4);
			bookRepository.save(book3);

			// TESTIKÄYTTÄJÄT DATABASEEN MUISTA MYÖHEMMIN POISTAA SALASANAT


			User user1 = new User("user", "$2a$12$iWojeNpwIkHYmTLM/H2ySeGwjiTfuNHgcjtQ1jAcomtAwkNGQUCli", "user@book.com", "USER"); // pw user
			User user2 = new User("admin", "$2a$12$1SPIDgsGyFsc5/hkVFUxmec2Onm6L65l6POlq8Wkc1aX7pivjMYPi", "admin@book.com", "ADMIN"); // pw admin
			User user3 = new User("testeri", "$2a$12$geEmOFpD7zndA.GLsuPxlej3edBFA.K3mxONt2.6dD8oMm5iYMbV6", "testeri@book.com", "ADMIN"); // pw on testi
			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(user3);

			log.info("fetch");
		};

	}}
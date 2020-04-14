package guru.springframwork.spring5webapp.bootstrap;

import guru.springframwork.spring5webapp.model.Author;
import guru.springframwork.spring5webapp.model.Book;
import guru.springframwork.spring5webapp.repositories.AuthorRepository;
import guru.springframwork.spring5webapp.repositories.BookRepositoy;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DevBootstrap implements ApplicationListener <ContextRefreshedEvent>{

    private AuthorRepository authorRepository;
    private BookRepositoy bookRepositoy;

    public DevBootstrap(AuthorRepository authorRepository, BookRepositoy bookRepositoy) {
        this.authorRepository = authorRepository;
        this.bookRepositoy = bookRepositoy;
    }

    private void initData(){ ;
        Author eric = new Author("Eric","Evans");
        Book ddd = new Book("Domain Driven Desing","123","Oreally");
        eric.getBooks().add(ddd);
        bookRepositoy.save(ddd);
        authorRepository.save(eric);

        Author rod = new Author("Rod","Johnson");
        Book noEJB = new Book("J2EE Development without EJB","3242342","X publisher");
        rod.getBooks().add(noEJB);
        bookRepositoy.save(noEJB);
        authorRepository.save(rod);

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}

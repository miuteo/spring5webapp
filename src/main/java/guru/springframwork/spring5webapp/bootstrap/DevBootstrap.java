package guru.springframwork.spring5webapp.bootstrap;

import guru.springframwork.spring5webapp.model.Author;
import guru.springframwork.spring5webapp.model.Book;
import guru.springframwork.spring5webapp.model.Publisher;
import guru.springframwork.spring5webapp.repositories.AuthorRepository;
import guru.springframwork.spring5webapp.repositories.BookRepositoy;
import guru.springframwork.spring5webapp.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DevBootstrap implements ApplicationListener <ContextRefreshedEvent>{

    private AuthorRepository authorRepository;
    private BookRepositoy bookRepositoy;
    private PublisherRepository publisherRepository;

    @Autowired
    public DevBootstrap(AuthorRepository authorRepository, BookRepositoy bookRepositoy, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepositoy = bookRepositoy;
        this.publisherRepository = publisherRepository;
    }

    public DevBootstrap(AuthorRepository authorRepository, BookRepositoy bookRepositoy) {
        this.authorRepository = authorRepository;
        this.bookRepositoy = bookRepositoy;
    }

    private void initData(){ ;
        Author eric = new Author("Eric","Evans");
        Publisher publisher = new Publisher("Oreally","Oreally address");
        publisherRepository.save(publisher);
        Book ddd = new Book("Domain Driven Desing","123",publisher);
        eric.getBooks().add(ddd);
        bookRepositoy.save(ddd);
        authorRepository.save(eric);

        Author rod = new Author("Rod","Johnson");
        Publisher publisher2 = new Publisher("X publisher","X publisher address");
        Book noEJB = new Book("J2EE Development without EJB","3242342",publisher2);
        rod.getBooks().add(noEJB);
        publisherRepository.save(publisher2);
        bookRepositoy.save(noEJB);
        authorRepository.save(rod);

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}

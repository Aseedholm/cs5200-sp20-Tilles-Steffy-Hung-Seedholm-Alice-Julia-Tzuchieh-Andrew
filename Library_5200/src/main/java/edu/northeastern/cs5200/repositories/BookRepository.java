package edu.northeastern.cs5200.repositories;

import edu.northeastern.cs5200.models.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {



    @Query("SELECT book FROM Book AS book where book.title=:title")
    Book findBookByTitle(String title);


//    Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException:
//    Error creating bean with name 'adminController': Unsatisfied dependency expressed through field
//    'libraryDao';
//    nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException:
//    Error creating bean with name 'libraryImpl':
//    Unsatisfied dependency expressed through field
//    'bookRepository'; nested exception is org.springframework.beans.factory.BeanCreationException:
//    Error creating bean with name 'bookRepository':
//    Invocation of init method failed; nested exception
//    is java.lang.IllegalArgumentException: Validation failed for query for method public abstract
//    edu.northeastern.cs5200.models.Book
//    edu.northeastern.cs5200.repositories.BookRepository.findBookByTitle(java.lang.String)!



}

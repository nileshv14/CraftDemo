package com.example.CraftDemo.repository;

import com.example.CraftDemo.model.Book;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface bookRepository extends MongoRepository<Book,String> {

    @Query(value = "{ownerId : ?0 ,exchangeable : true }")
    List<Book> getExchangeableBooksOfUser(String userId);

    @Query(value = "{ownerId : ?0 ,borrowable : true }")
    List<Book> getBorrowableBooksOfUser(String userId);

    @Query(value = "{ownerId : ?0 }")
    List<Book> getAllBooksOfUser(String userId);

    @Query(value = "{bookId : ?0 }")
    Book getBook(String bookId);

    @Query(value = "{ownerId : ?0 ,bookName : ?1, publishingHouse : ?2}")
    Book getUniqueBook(String userId , String bookName , String publishingHouse);
}

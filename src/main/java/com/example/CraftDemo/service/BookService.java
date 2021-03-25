package com.example.CraftDemo.service;

import com.example.CraftDemo.model.Book;
import com.example.CraftDemo.repository.bookRepository;
import com.example.CraftDemo.repository.userRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private bookRepository bookRepository;

    @Autowired
    private userRepository userRepository;

    public List<Book> getBorrowableBooksPerUser(String userId){

        if(userRepository.checkVerifiedUser(userId)==null){
            return null;
        }
       return bookRepository.getBorrowableBooksOfUser(userId);
    }

    public List<Book> getExchangeableBooksPerUser(String userId){

        if(userRepository.checkVerifiedUser(userId)==null){
            return null;
        }

        return bookRepository.getExchangeableBooksOfUser(userId);
    }

    public Book addBook(Book book){

        book.setBookId(UUID.randomUUID().toString());
        book.setId(null);
        book.setDateAdded(System.currentTimeMillis());
        if(bookRepository.getUniqueBook(book.getOwnerId(),book.getBookName(),book.getPublishingHouse())!=null){
            return null;
        }
        bookRepository.save(book);

        return book;
    }

    public Book getBook(String id){
        if(id == null || id == ""){
            return  null;
        }
        return bookRepository.getBook(id);
    }
}

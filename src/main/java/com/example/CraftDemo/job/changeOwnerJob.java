package com.example.CraftDemo.job;

import com.example.CraftDemo.model.Book;
import com.example.CraftDemo.repository.bookRepository;
import com.example.CraftDemo.service.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class changeOwnerJob {

    @Autowired private bookRepository bookRepository;

    @Autowired
    private BookService bookService;

    //@Scheduled()
    public void changeOwners(){
        Long time = System.currentTimeMillis();
       List<Book>books = bookRepository.getBooksForOwnerChange(time);
        bookService.changeToPrevOwner(books);

    }
}

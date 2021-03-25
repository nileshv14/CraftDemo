package com.example.CraftDemo.controller;

import com.example.CraftDemo.model.Book;
import com.example.CraftDemo.service.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path =  "/book")
public class BookResource {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/getExchangeBooks")
    public List<Book> getExchangeableBooks(@RequestParam String userId){

       return bookService.getExchangeableBooksPerUser(userId);
    }

    @GetMapping(value = "/getBorrowBooks")
    public List<Book> getBorrowBooks(@RequestParam String userId){
        return bookService.getBorrowableBooksPerUser(userId);
    }


   @PutMapping(path = "/addBook")
    public Book addBook(@RequestBody Book book){

        return bookService.addBook(book);
   }

}

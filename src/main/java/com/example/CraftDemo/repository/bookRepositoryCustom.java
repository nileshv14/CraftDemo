package com.example.CraftDemo.repository;

import com.example.CraftDemo.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface bookRepositoryCustom {

    public Page<Book> searchBooks(String searchTerm, PageRequest pageRequest);
}

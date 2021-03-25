package com.example.CraftDemo.repository;

import com.example.CraftDemo.model.Book;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class bookRepositoryCustomimpl implements bookRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;


    @Override public Page<Book> searchBooks(String query , PageRequest pageRequest) {
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("bookName").regex(query, "i"),
            Criteria.where("authorName").regex(query, "i"),Criteria.where("publishingHouse").regex(query, "i"));
        Query mongoQuery = new Query(criteria);
        Query pageQuery = mongoQuery.with(pageRequest);
        long count = mongoTemplate.count(mongoQuery, Book.class);
        List<Book> books = mongoTemplate.find(pageQuery, Book.class);
        return new PageImpl<>(books, pageRequest, count);

    }
}

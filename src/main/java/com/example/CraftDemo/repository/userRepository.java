package com.example.CraftDemo.repository;

import com.example.CraftDemo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends MongoRepository<User,String> {

    @Query(value = "{userId : ?0}")
    User getByAppUserInfo(String userId);

    @Query(value = "{email : ?0}")
    User getUserByEmail(String email);


    @Query(value = "{email : ?0 , password : ?1}")
    User getUser(String email , String password);

    @Query(value = "{userId : ?0 , verified : true}")
    User checkVerifiedUser(String userId);

}

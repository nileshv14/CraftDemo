package com.example.CraftDemo.service;

import com.example.CraftDemo.model.User;
import com.example.CraftDemo.repository.userRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import com.example.CraftDemo.repository.userRepository;

@Service
public class UserService {

    @Autowired
    private userRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    public String saveUser(User user){

        if(userRepository.getUserByEmail(user.getEmail())!=null){
            return "Account already exists, please login";
        }
        user.setUserId(UUID.randomUUID().toString());
        userRepository.save(user);

        return "account created";
    }


    public User userLogin(String email , String password ){

        User user = userRepository.getUser(email,password);

        if(user!=null){
            return user;
        }else{
            return null;
        }

    }

    public String verifyUser(String email){

        User user = userRepository.getUserByEmail(email);

        if(user==null){
            return "user not found";
        }else{

            Query query = new Query();

            query.addCriteria(Criteria.where("email").is(email));
            Update update = new Update();
            update.set("verified",true);

            mongoTemplate.updateFirst(query, update, User.class);
        }
        return "user verified";
    }

    public void updateUsers(User requestor,User requestee){

        userRepository.save(requestee);
        userRepository.save(requestor);
    }

}

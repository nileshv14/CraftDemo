package com.example.CraftDemo.repository;

import com.example.CraftDemo.model.Request;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface requestRepository extends MongoRepository<Request,String> {

    @Query(value = "{requestorId : ?0 }")
    List<Request> getUserRequests(String userId);

    @Query(value = "{requesteeId : ?0}")
    List<Request> getRequestedList(String userId);

    @Query(value = "{requestId : ?0}")
    Request getRequest(String requestId);

    @Query(value = "{bookRequestedId : ?0 , requestorId : ?1 , requesteeId : ?2 , borrowRequest : ?3,requestClosed : false}")
    Request getRequest(String bookRequestId , String requestorId , String requesteeId,boolean borrow);


}

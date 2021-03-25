package com.example.CraftDemo.controller;

import com.example.CraftDemo.model.Request;
import com.example.CraftDemo.model.RequestType;
import com.example.CraftDemo.service.RequestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/request")
public class RequestResource {

    @Autowired
    private RequestService requestService;

    @PutMapping(value = "/addRequest")
    public Request addRequest(@RequestParam String bookId, @RequestParam String requestorId,
        @RequestParam String requesteeId, @RequestParam(required = false) Integer noOfDays,
        @RequestParam
            RequestType requestType) throws Exception {

       return requestService.addRequest(bookId, requestorId, requesteeId, noOfDays, requestType);
    }

    @GetMapping(value = "/getAllRequests")
    public List<Request> getRequests(@RequestParam String requesteeId) {

        return requestService.getRequestedList(requesteeId);
    }

    @GetMapping(value = "/getRequestedList")
    public List<Request> getRequestedList(@RequestParam String requestorId) {

        return requestService.getUserRequests(requestorId);
    }

    @GetMapping(value = "/acceptRequest")
    public Request acceptRequest(@RequestParam String requestId) throws Exception {

        return requestService.acceptRequest(requestId);
    }

    @GetMapping(value = "/rejectRequest")
    public Request rejectRequest(@RequestParam String requestId) {

        return requestService.rejectRequest(requestId);
    }

    @PatchMapping(value = "/selectBookForExchange")
    public  Request selectBookForExchange(@RequestParam String requestId){

        return requestService.modifyExchangeRequest(requestId);
    }
}

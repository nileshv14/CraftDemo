package com.example.CraftDemo.service;

import com.example.CraftDemo.model.Book;
import com.example.CraftDemo.model.Request;
import com.example.CraftDemo.model.RequestType;
import com.example.CraftDemo.model.User;
import com.example.CraftDemo.repository.bookRepository;
import com.example.CraftDemo.repository.requestRepository;
import com.example.CraftDemo.repository.userRepository;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import com.example.CraftDemo.repository.userRepository;
import org.springframework.util.CollectionUtils;

@Service
public class RequestService {

    @Autowired
    private requestRepository requestRepository;

    @Autowired
    private bookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private userRepository userRepository;

    @Autowired
    private UserService userService;

    public List<Request> getUserRequests(String userId) {

        return requestRepository.getUserRequests(userId);
    }

    public List<Request> getRequestedList(String userId) {

        return requestRepository.getRequestedList(userId);
    }

    public Request addRequest(String bookRequestedId, String requestorId, String requesteeId,
        Integer noOfDays
        , RequestType requestType) throws Exception {

        if (userRepository.checkVerifiedUser(requestorId) == null) {
            return null;
        }
        if (requestType.equals(RequestType.EXCHANGE)) {
            return addExchangeRequest(bookRequestedId, requestorId, requesteeId);
        } else if (requestType.equals(RequestType.BORROW)) {

            return addBorrowRequest(bookRequestedId, requestorId, requesteeId, noOfDays);
        } else {
            throw new Exception("not a valid request");
        }
    }

    public Request addExchangeRequest(String bookRequestedId, String requestorId,
        String requesteeId)
        throws Exception {

        Request userRequest =
            requestRepository.getRequest(bookRequestedId, requestorId, requesteeId, false);

        if (userRequest != null) {
            throw new Exception("request already exists");
        }

        userRequest = Request.Builder.request()
            .withRequestId(UUID.randomUUID().toString())
            .withRequestorId(requestorId)
            .withRequesteeId(requesteeId)
            .withBookRequestedId(bookRequestedId)
            .withBookOfferedId(null)
            .withRequestAccepted(false)
            .withBorrowRequest(false)
            .withNoOfDays(-1)
            .withRequestClosed(false)
            .withDateAdded(System.currentTimeMillis())
            .withDateCompleted(null)
            .build();
        requestRepository.save(userRequest);

        return userRequest;
    }

    public Request addBorrowRequest(String bookRequestedId, String requestorId, String requesteeId,
        int noOfDays)
        throws Exception {

        Request userRequest =
            requestRepository.getRequest(bookRequestedId, requestorId, requesteeId, true);

        if (userRequest != null) {
            throw new Exception("request already exists");
        }

        User requestor = userRepository.getByAppUserInfo(requestorId);

        if (requestor.getCoins() == 0) {
            throw new Exception("Insufficient coins");
        }

        userRequest = Request.Builder.request()
            .withRequestId(UUID.randomUUID().toString())
            .withRequestorId(requestorId)
            .withRequesteeId(requesteeId)
            .withBookRequestedId(bookRequestedId)
            .withBookOfferedId(null)
            .withRequestAccepted(false)
            .withBorrowRequest(true)
            .withNoOfDays(noOfDays)
            .withRequestClosed(false)
            .withDateAdded(System.currentTimeMillis())
            .withDateCompleted(null)
            .build();

        requestRepository.save(userRequest);

        return userRequest;
    }

    public Request modifyExchangeRequest(String requestId) {

        Request request = requestRepository.getRequest(requestId);

        if (request == null) {
            return null;
        }

        String requestorId = request.getRequestorId();

        List<Book> books = bookRepository.getAllBooksOfUser(requestorId);

        String bookIdForExchange = books.get(0).getBookId();

        request.setBookOfferedId(bookIdForExchange);

        requestRepository.save(request);

        return request;
    }

    public Request acceptRequest(String requestId) throws Exception {

        Request request = requestRepository.getRequest(requestId);

        Book bookRequested = bookService.getBook(request.getBookRequestedId());

        Book bookOffered = bookService.getBook(request.getBookOfferedId());

        User requestor = userRepository.getByAppUserInfo(request.getRequestorId());

        User requestee = userRepository.getByAppUserInfo(request.getRequesteeId());

        requestee.setCoins(requestee.getCoins() + 1);

        if (request.isBorrowRequest() == false) {
            requestor.setCoins(requestor.getCoins() + 1);
            bookOffered.setOwnerId(request.getRequesteeId());
            bookRepository.save(bookOffered);
        } else {
            if (requestor.getCoins() == 0) {
                throw new Exception("Coin value insufficient");
            }
            requestor.setCoins(requestor.getCoins() - 1);
            Long dateOfReturn =
                System.currentTimeMillis() + request.getNoOfDays() != 0 ? request.getNoOfDays()
                    * 86400
                    * 1000L :
                    5 * 86400 * 1000L;

            bookRequested.setDateOfReturn(dateOfReturn);
        }

        bookRequested.setOwnerId(request.getRequestorId());

        bookRepository.save(bookRequested);

        request.setRequestAccepted(true);
        request.setRequestClosed(true);
        request.setDateCompleted(System.currentTimeMillis());

        requestRepository.save(request);
        userService.updateUsers(requestor, requestee);

        return request;
    }

    public Request rejectRequest(String requestId) {

        Request request = requestRepository.getRequest(requestId);

        request.setRequestAccepted(false);
        request.setDateCompleted(System.currentTimeMillis());
        request.setRequestClosed(true);

        requestRepository.save(request);

        return request;
    }
}

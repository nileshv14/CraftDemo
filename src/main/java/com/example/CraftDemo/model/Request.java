package com.example.CraftDemo.model;

import org.springframework.data.annotation.Id;

public class Request {

    @Id
    private String id;

    private String requestId;

    private String requestorId;

    private String requesteeId;

    private String bookRequestedId;

    private String bookOfferedId;

    private boolean requestAccepted;

    private boolean borrowRequest;

    private int noOfDays;

    private boolean requestClosed;

    private Long dateAdded;

    private Long dateCompleted;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestorId() {
        return requestorId;
    }

    public void setRequestorId(String requestorId) {
        this.requestorId = requestorId;
    }

    public String getRequesteeId() {
        return requesteeId;
    }

    public void setRequesteeId(String requesteeId) {
        this.requesteeId = requesteeId;
    }

    public String getBookRequestedId() {
        return bookRequestedId;
    }

    public void setBookRequestedId(String bookRequestedId) {
        this.bookRequestedId = bookRequestedId;
    }

    public String getBookOfferedId() {
        return bookOfferedId;
    }

    public void setBookOfferedId(String bookOfferedId) {
        this.bookOfferedId = bookOfferedId;
    }

    public boolean isRequestAccepted() {
        return requestAccepted;
    }

    public void setRequestAccepted(boolean requestAccepted) {
        this.requestAccepted = requestAccepted;
    }

    public boolean isBorrowRequest() {
        return borrowRequest;
    }

    public void setBorrowRequest(boolean borrowRequest) {
        this.borrowRequest = borrowRequest;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isRequestClosed() {
        return requestClosed;
    }

    public void setRequestClosed(boolean requestClosed) {
        this.requestClosed = requestClosed;
    }

    public Long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Long getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Long dateCompleted) {
        this.dateCompleted = dateCompleted;
    }



    public Request(){

    }

    public Request(String requestId, String requestorId , String requesteeId , String bookRequestedId,
        String bookOfferedId , boolean requestAccepted, boolean borrowRequest, int noOfDays,
        boolean requestClosed , Long dateAdded , Long dateCompleted){

        this.requestId = requestId;
        this.requestorId = requestorId;
        this.requesteeId = requesteeId;
        this.bookRequestedId = bookRequestedId;
        this.bookOfferedId = bookOfferedId;
        this.requestAccepted = requestAccepted;
        this.borrowRequest = borrowRequest;
        this.noOfDays = noOfDays;
        this.dateAdded = dateAdded;
        this.dateCompleted = dateCompleted;
        this.requestClosed = requestClosed;
    }

    public static interface RequestIdStep {
        RequestorIdStep withRequestId(String requestId);
    }

    public static interface RequestorIdStep {
        RequesteeIdStep withRequestorId(String requestorId);
    }

    public static interface RequesteeIdStep {
        BookRequestedIdStep withRequesteeId(String requesteeId);
    }

    public static interface BookRequestedIdStep {
        BookOfferedIdStep withBookRequestedId(String bookRequestedId);
    }

    public static interface BookOfferedIdStep {
        RequestAcceptedStep withBookOfferedId(String bookOfferedId);
    }

    public static interface RequestAcceptedStep {
        BorrowRequestStep withRequestAccepted(boolean requestAccepted);
    }

    public static interface BorrowRequestStep {
        NoOfDaysStep withBorrowRequest(boolean borrowRequest);
    }

    public static interface NoOfDaysStep {
        RequestClosedStep withNoOfDays(int noOfDays);
    }

    public static interface RequestClosedStep {
        DateAddedStep withRequestClosed(boolean requestClosed);
    }

    public static interface DateAddedStep {
        DateCompletedStep withDateAdded(Long dateAdded);
    }

    public static interface DateCompletedStep {
        BuildStep withDateCompleted(Long dateCompleted);
    }

    public static interface BuildStep {
        Request build();
    }

    public static class Builder
        implements RequestIdStep, RequestorIdStep, RequesteeIdStep, BookRequestedIdStep,
        BookOfferedIdStep, RequestAcceptedStep, BorrowRequestStep, NoOfDaysStep, RequestClosedStep,
        DateAddedStep, DateCompletedStep, BuildStep {
        private String requestId;
        private String requestorId;
        private String requesteeId;
        private String bookRequestedId;
        private String bookOfferedId;
        private boolean requestAccepted;
        private boolean borrowRequest;
        private int noOfDays;
        private boolean requestClosed;
        private Long dateAdded;
        private Long dateCompleted;

        private Builder() {
        }

        public static RequestIdStep request() {
            return new Builder();
        }

        @Override
        public RequestorIdStep withRequestId(String requestId) {
            this.requestId = requestId;
            return this;
        }

        @Override
        public RequesteeIdStep withRequestorId(String requestorId) {
            this.requestorId = requestorId;
            return this;
        }

        @Override
        public BookRequestedIdStep withRequesteeId(String requesteeId) {
            this.requesteeId = requesteeId;
            return this;
        }

        @Override
        public BookOfferedIdStep withBookRequestedId(String bookRequestedId) {
            this.bookRequestedId = bookRequestedId;
            return this;
        }

        @Override
        public RequestAcceptedStep withBookOfferedId(String bookOfferedId) {
            this.bookOfferedId = bookOfferedId;
            return this;
        }

        @Override
        public BorrowRequestStep withRequestAccepted(boolean requestAccepted) {
            this.requestAccepted = requestAccepted;
            return this;
        }

        @Override
        public NoOfDaysStep withBorrowRequest(boolean borrowRequest) {
            this.borrowRequest = borrowRequest;
            return this;
        }

        @Override
        public RequestClosedStep withNoOfDays(int noOfDays) {
            this.noOfDays = noOfDays;
            return this;
        }

        @Override
        public DateAddedStep withRequestClosed(boolean requestClosed) {
            this.requestClosed = requestClosed;
            return this;
        }

        @Override
        public DateCompletedStep withDateAdded(Long dateAdded) {
            this.dateAdded = dateAdded;
            return this;
        }

        @Override
        public BuildStep withDateCompleted(Long dateCompleted) {
            this.dateCompleted = dateCompleted;
            return this;
        }

        @Override
        public Request build() {
            return new Request(
                this.requestId,
                this.requestorId,
                this.requesteeId,
                this.bookRequestedId,
                this.bookOfferedId,
                this.requestAccepted,
                this.borrowRequest,
                this.noOfDays,
                this.requestClosed,
                this.dateAdded,
                this.dateCompleted
            );
        }
    }
}

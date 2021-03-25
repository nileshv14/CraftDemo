package com.example.CraftDemo.model;

import org.springframework.data.annotation.Id;

public class Book {
    @Id
    private String id;

    private String bookId;

    private String ownerId;

    private String authorName;

    private String bookName;

    private String publishingHouse;

    private boolean exchangeable;

    private boolean borrowable;

    private Long dateOfReturn;

    private Long dateAdded;

    public Book() {

    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public boolean isExchangeable() {
        return exchangeable;
    }

    public void setExchangeable(boolean exchangeable) {
        this.exchangeable = exchangeable;
    }

    public boolean isBorrowable() {
        return borrowable;
    }

    public void setBorrowable(boolean borrowable) {
        this.borrowable = borrowable;
    }

    public Long getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(Long dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public Long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Book(String bookId, String ownerId, String authorName, String bookName,
        String publishingHouse,
        Long dateAdded,
        boolean borrowable, Long dateOfReturn) {

        this.bookId = bookId;
        this.ownerId = ownerId;
        this.authorName = authorName;
        this.bookName = bookName;
        this.publishingHouse = publishingHouse;
        this.borrowable = borrowable;
        this.dateOfReturn = dateOfReturn;
        this.dateAdded = dateAdded;
    }

    public static interface BookIdStep {
        OwnerIdStep withBookId(String bookId);
    }

    public static interface OwnerIdStep {
        AuthorNameStep withOwnerId(String ownerId);
    }

    public static interface AuthorNameStep {
        BookNameStep withAuthorName(String authorName);
    }

    public static interface BookNameStep {
        PublishingHouseStep withBookName(String bookName);
    }

    public static interface PublishingHouseStep {
        DateAddedStep withPublishingHouse(String publishingHouse);
    }

    public static interface DateAddedStep {
        BorrowableStep withDateAdded(Long dateAdded);
    }

    public static interface BorrowableStep {
        DateOfReturnStep withBorrowable(boolean borrowable);
    }

    public static interface DateOfReturnStep {
        BuildStep withDateOfReturn(Long dateOfReturn);
    }

    public static interface BuildStep {
        Book build();
    }

    public static class Builder
        implements BookIdStep, OwnerIdStep, AuthorNameStep, BookNameStep, PublishingHouseStep,
        DateAddedStep, BorrowableStep, DateOfReturnStep, BuildStep {
        private String bookId;
        private String ownerId;
        private String authorName;
        private String bookName;
        private String publishingHouse;
        private Long dateAdded;
        private boolean borrowable;
        private Long dateOfReturn;

        private Builder() {
        }

        public static BookIdStep book() {
            return new Builder();
        }

        @Override
        public OwnerIdStep withBookId(String bookId) {
            this.bookId = bookId;
            return this;
        }

        @Override
        public AuthorNameStep withOwnerId(String ownerId) {
            this.ownerId = ownerId;
            return this;
        }

        @Override
        public BookNameStep withAuthorName(String authorName) {
            this.authorName = authorName;
            return this;
        }

        @Override
        public PublishingHouseStep withBookName(String bookName) {
            this.bookName = bookName;
            return this;
        }

        @Override
        public DateAddedStep withPublishingHouse(String publishingHouse) {
            this.publishingHouse = publishingHouse;
            return this;
        }

        @Override
        public BorrowableStep withDateAdded(Long dateAdded) {
            this.dateAdded = dateAdded;
            return this;
        }

        @Override
        public DateOfReturnStep withBorrowable(boolean borrowable) {
            this.borrowable = borrowable;
            return this;
        }

        @Override
        public BuildStep withDateOfReturn(Long dateOfReturn) {
            this.dateOfReturn = dateOfReturn;
            return this;
        }

        @Override
        public Book build() {
            return new Book(
                this.bookId,
                this.ownerId,
                this.authorName,
                this.bookName,
                this.publishingHouse,
                this.dateAdded,
                this.borrowable,
                this.dateOfReturn
            );
        }
    }
}

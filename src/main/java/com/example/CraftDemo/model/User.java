package com.example.CraftDemo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class User {

    @Id
    private String id;

    private String userId;

    private String name;

    private String mobileNo;

    private boolean verified;

    @Indexed(unique = true)
    private String email;

    private int coins;

    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(){

    }

    public User(String userId,String name ,String mobileNo,boolean verified,String email,int coins,
        String password){

        this.userId = userId;
        this.name = name;
        this.mobileNo = mobileNo;
        this.verified = verified;
        this.email = email;
        this.coins = coins;
        this.password = password;
    }

    public static interface UserIdStep {
        NameStep withUserId(String userId);
    }

    public static interface NameStep {
        MobileNoStep withName(String name);
    }

    public static interface MobileNoStep {
        VerifiedStep withMobileNo(String mobileNo);
    }

    public static interface VerifiedStep {
        EmailStep withVerified(boolean verified);
    }

    public static interface EmailStep {
        CoinsStep withEmail(String email);
    }

    public static interface CoinsStep {
        PasswordStep withCoins(int coins);
    }

    public static interface PasswordStep {
        BuildStep withPassword(String password);
    }

    public static interface BuildStep {
        User build();
    }

    public static class Builder
        implements UserIdStep, NameStep, MobileNoStep, VerifiedStep, EmailStep, CoinsStep,
        PasswordStep, BuildStep {
        private String userId;
        private String name;
        private String mobileNo;
        private boolean verified;
        private String email;
        private int coins;
        private String password;

        private Builder() {
        }

        public static UserIdStep user() {
            return new Builder();
        }

        @Override
        public NameStep withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        @Override
        public MobileNoStep withName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public VerifiedStep withMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
            return this;
        }

        @Override
        public EmailStep withVerified(boolean verified) {
            this.verified = verified;
            return this;
        }

        @Override
        public CoinsStep withEmail(String email) {
            this.email = email;
            return this;
        }

        @Override
        public PasswordStep withCoins(int coins) {
            this.coins = coins;
            return this;
        }

        @Override
        public BuildStep withPassword(String password) {
            this.password = password;
            return this;
        }

        @Override
        public User build() {
            return new User(
                this.userId,
                this.name,
                this.mobileNo,
                this.verified,
                this.email,
                this.coins,
                this.password
            );
        }
    }
}

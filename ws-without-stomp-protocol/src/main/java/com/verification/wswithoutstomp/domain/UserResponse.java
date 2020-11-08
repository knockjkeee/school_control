package com.verification.wswithoutstomp.domain;

//@Data
//@AllArgsConstructor
public class UserResponse {
    String context;

    public UserResponse() {
    }

    public UserResponse(String context) {
        this.context = context;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}

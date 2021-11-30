package com.example.inventory.base;

public class Event {
    public static final int onLoginError = 0;
    public static final int onSignUpError = 1;
    public static final int onSignUpSuccess = 2;
    public static final int onLoginSuccess = 3;

    private int eventType;
    private String message;

    public int getEventType() { return eventType; }
    public String getMessage() { return message; }

    public void setEventType(int eventType) { this.eventType = eventType; }
    public void setMessage(String message) { this.message = message; }
}

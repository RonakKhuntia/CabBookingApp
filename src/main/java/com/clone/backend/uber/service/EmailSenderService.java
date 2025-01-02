package com.clone.backend.uber.service;

public interface EmailSenderService {
    public void sendEmail(String recipient, String subject, String body);
    void sendEmail(String[] recipients, String subject, String body);
}

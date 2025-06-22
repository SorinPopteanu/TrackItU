package com.trackitu.notification.dto;

/**
 * @param accountNumber
 * @param firstName
 * @param lastName
 * @param email
 */
public record AccountsNotificationDto(Long accountNumber, String firstName, String lastName, String email, String password) {

}

package com.trackitu.accounts.dto;

/**
 * @param accountNumber
 * @param firstName
 * @param lastName
 * @param email
 * @param password
 */
public record AccountsNotificationDto(Long accountNumber, String firstName, String lastName,
                                      String email, String password) {}
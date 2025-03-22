package com.trackitu.rooms.mapper;

import com.trackitu.rooms.dto.BookingsDto;
import com.trackitu.rooms.entity.Bookings;

public class BookingsMapper {

  public static BookingsDto mapToBookingsDto(Bookings bookings, BookingsDto bookingsDto) {
    bookingsDto.setRoomId(bookings.getRoomId());
    bookingsDto.setCustomerId(bookings.getCustomerId());
    bookingsDto.setBookingDate(bookings.getBookingDate());
    bookingsDto.setStartTime(bookings.getStartTime());
    bookingsDto.setEndTime(bookings.getEndTime());
    bookingsDto.setBookingStatus(bookings.getBookingStatus());
    return bookingsDto;
  }

  public static Bookings mapToBookings(BookingsDto bookingsDto, Bookings bookings) {
    bookings.setRoomId(bookingsDto.getRoomId());
    bookings.setCustomerId(bookingsDto.getCustomerId());
    bookings.setBookingDate(bookingsDto.getBookingDate());
    bookings.setStartTime(bookingsDto.getStartTime());
    bookings.setEndTime(bookingsDto.getEndTime());
    bookings.setBookingStatus(bookingsDto.getBookingStatus());
    return bookings;
  }
}

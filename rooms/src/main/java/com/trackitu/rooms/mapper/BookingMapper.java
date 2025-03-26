package com.trackitu.rooms.mapper;

import com.trackitu.rooms.dto.BookingDto;
import com.trackitu.rooms.entity.Booking;

public class BookingMapper {

  public static BookingDto mapToBookingsDto(Booking booking, BookingDto bookingDto) {
    bookingDto.setRoomId(booking.getRoomId());
    bookingDto.setCustomerId(booking.getCustomerId());
    bookingDto.setBookingDate(booking.getBookingDate());
    bookingDto.setStartTime(booking.getStartTime());
    bookingDto.setEndTime(booking.getEndTime());
    bookingDto.setBookingStatus(booking.getBookingStatus());
    return bookingDto;
  }

  public static Booking mapToBookings(BookingDto bookingDto, Booking booking) {
    booking.setRoomId(bookingDto.getRoomId());
    booking.setCustomerId(bookingDto.getCustomerId());
    booking.setBookingDate(bookingDto.getBookingDate());
    booking.setStartTime(bookingDto.getStartTime());
    booking.setEndTime(bookingDto.getEndTime());
    booking.setBookingStatus(bookingDto.getBookingStatus());
    return booking;
  }
}

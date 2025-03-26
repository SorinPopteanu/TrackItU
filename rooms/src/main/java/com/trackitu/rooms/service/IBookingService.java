package com.trackitu.rooms.service;

import com.trackitu.rooms.dto.BookingDto;
import java.util.List;

public interface IBookingService {

  /**
   * Create a booking
   * @param bookingDto - Insert booking details
   */
  void createBooking(BookingDto bookingDto);

  /**
   * @return List of all the bookings
   */
  List<BookingDto> fetchAllBookings();

  /**
   * @param bookingDto - BookingDto Object
   * @return boolean indicating if the update of the booking status is successful or not
   */
  boolean updateBookingStatus(BookingDto bookingDto);

  /**
   * @param bookingDto - Input BookingDto Object
   * @return boolean indicating if the deletion of the booking is successful or not
   */
  boolean deleteBooking(BookingDto bookingDto);
}

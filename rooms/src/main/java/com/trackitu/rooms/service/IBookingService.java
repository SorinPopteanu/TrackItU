package com.trackitu.rooms.service;

import com.trackitu.rooms.dto.booking.CreateBookingDto;
import com.trackitu.rooms.dto.booking.FetchBookingDto;
import com.trackitu.rooms.dto.booking.UpdateBookingDto;
import java.util.List;

public interface IBookingService {

  /**
   * Create a booking
   * @param createBookingDto - Insert booking details
   */
  void createBooking(CreateBookingDto createBookingDto);

  /**
   * @return List of all the bookings
   */
  List<FetchBookingDto> fetchAllBookings();

  /**
   * @param updateBookingDto - UpdateBookingDto Object
   * @return boolean indicating if the update of the booking status is successful or not
   */
  boolean updateStatus(UpdateBookingDto updateBookingDto);

  /**
   * @param id - Input booking id
   * @return boolean indicating if the deletion of the booking is successful or not
   */
  boolean deleteBooking(Long id);
}

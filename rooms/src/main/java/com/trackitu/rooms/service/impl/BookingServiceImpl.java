package com.trackitu.rooms.service.impl;

import com.trackitu.rooms.dto.BookingDto;
import com.trackitu.rooms.entity.Booking;
import com.trackitu.rooms.exception.BookingAlreadyExistsException;
import com.trackitu.rooms.exception.ResourceNotFoundException;
import com.trackitu.rooms.mapper.BookingMapper;
import com.trackitu.rooms.repository.BookingRepository;
import com.trackitu.rooms.service.IBookingService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements IBookingService {

  private BookingRepository bookingRepository;

  /**
   * Create a booking
   *
   * @param bookingDto - Insert booking details
   */
  @Override
  public void createBooking(BookingDto bookingDto) {
    Booking booking = BookingMapper.mapToBookings(bookingDto, new Booking());
    Optional<Booking> optionalBooking = bookingRepository.findByRoomIdAndBookingDateAndStartTimeAndEndTime(
        bookingDto.getRoomId(), bookingDto.getBookingDate(), bookingDto.getStartTime(),
        bookingDto.getEndTime());
    if (optionalBooking.isPresent()) {
      throw new BookingAlreadyExistsException(String.format(
          "Booking already exists for room id: %s, booking date: %s, start time: %s, end time: %s",
          bookingDto.getRoomId(), bookingDto.getBookingDate(), bookingDto.getStartTime(),
          bookingDto.getEndTime()));
    } else {
      bookingRepository.save(booking);
    }
  }

  /**
   * @return List of all the bookings
   */
  @Override
  public List<BookingDto> fetchAllBookings() {
    List<Booking> bookingList = bookingRepository.findAll();
    List<BookingDto> bookingDtoList = new ArrayList<>();

    for (Booking booking : bookingList) {
      bookingDtoList.add(BookingMapper.mapToBookingsDto(booking, new BookingDto()));
    }
    return bookingDtoList;
  }

  /**
   * @param bookingDto - BookingDto Object
   * @return boolean indicating if the update of the booking status is successful or not
   */
  @Override
  public boolean updateBookingStatus(BookingDto bookingDto) {
    boolean isUpdated = false;
    if (bookingDto != null) {
      ArrayList<String> details = new ArrayList<>();
      details.add(bookingDto.getRoomId().toString());
      details.add(bookingDto.getBookingDate().toString());
      details.add(bookingDto.getStartTime().toString());
      details.add(bookingDto.getEndTime().toString());
      Booking booking = bookingRepository.findByRoomIdAndBookingDateAndStartTimeAndEndTime(
              bookingDto.getRoomId(), bookingDto.getBookingDate(), bookingDto.getStartTime(),
              bookingDto.getEndTime())
          .orElseThrow(() -> new ResourceNotFoundException("Booking", "given information",
              details.toString()));
      BookingMapper.mapToBookings(bookingDto, booking);
      bookingRepository.save(booking);
      isUpdated = true;
    }
    return isUpdated;
  }

  /**
   * @param bookingDto - Input BookingDto Object
   * @return boolean indicating if the deletion of the booking is successful or not
   */
  @Override
  public boolean deleteBooking(BookingDto bookingDto) {
    Optional<Booking> optionalBooking = bookingRepository.findByRoomIdAndBookingDateAndStartTimeAndEndTime(
        bookingDto.getRoomId(), bookingDto.getBookingDate(), bookingDto.getStartTime(),
        bookingDto.getEndTime());
    if (optionalBooking.isPresent()) {
      bookingRepository.deleteById(optionalBooking.get().getBookingId());
      return true;
    } else {
      ArrayList<String> details = new ArrayList<>();
      details.add(bookingDto.getRoomId().toString());
      details.add(bookingDto.getBookingDate().toString());
      details.add(bookingDto.getStartTime().toString());
      details.add(bookingDto.getEndTime().toString());
      throw new ResourceNotFoundException("Booking", "given information", details.toString());
    }
  }

}

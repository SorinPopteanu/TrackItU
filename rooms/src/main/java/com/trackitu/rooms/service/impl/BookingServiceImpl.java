package com.trackitu.rooms.service.impl;

import com.trackitu.rooms.dto.booking.CreateBookingDto;
import com.trackitu.rooms.dto.booking.FetchBookingDto;
import com.trackitu.rooms.dto.booking.UpdateBookingDto;
import com.trackitu.rooms.entity.Booking;
import com.trackitu.rooms.entity.Room;
import com.trackitu.rooms.exception.BookingAlreadyExistsException;
import com.trackitu.rooms.exception.ResourceNotFoundException;
import com.trackitu.rooms.mapper.BookingMapper;
import com.trackitu.rooms.repository.BookingRepository;
import com.trackitu.rooms.repository.RoomRepository;
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
  private RoomRepository roomRepository;

  /**
   * Create a booking
   *
   * @param createBookingDto - Insert booking details
   */
  @Override
  public void createBooking(CreateBookingDto createBookingDto) {
    Room room = roomRepository.findById(createBookingDto.getRoomId()).orElseThrow(
        () -> new ResourceNotFoundException("Room", "id",
            createBookingDto.getRoomId().toString()));
    Booking booking = BookingMapper.mapToBooking(createBookingDto, new Booking(), room);
    Optional<Booking> optionalBooking = bookingRepository.findByRoomIdAndDateAndStartTimeAndEndTime(
        createBookingDto.getRoomId(), createBookingDto.getDate(), createBookingDto.getStartTime(),
        createBookingDto.getEndTime());
    if (optionalBooking.isPresent()) {
      throw new BookingAlreadyExistsException(String.format(
          "Booking already exists for room id: %s, booking date: %s, start time: %s, end time: %s",
          createBookingDto.getRoomId(), createBookingDto.getDate(), createBookingDto.getStartTime(),
          createBookingDto.getEndTime()));
    } else {
      bookingRepository.save(booking);
    }
  }

  /**
   * @return List of all the bookings
   */
  @Override
  public List<FetchBookingDto> fetchAllBookings() {
    List<Booking> bookingList = bookingRepository.findAll();
    List<FetchBookingDto> fetchBookingDtoList = new ArrayList<>();

    for (Booking booking : bookingList) {
      fetchBookingDtoList.add(BookingMapper.mapToFetchBookingDto(booking, new FetchBookingDto()));
    }
    return fetchBookingDtoList;
  }

  /**
   * @param updateBookingDto - CreateBookingDto Object
   * @return boolean indicating if the update of the booking status is successful or not
   */
  @Override
  public boolean updateStatus(UpdateBookingDto updateBookingDto) {
    boolean isUpdated = false;
    if (updateBookingDto != null) {
      Booking booking = bookingRepository.findById(updateBookingDto.getId()).orElseThrow(
          () -> new ResourceNotFoundException("Booking", "id",
              updateBookingDto.getId().toString()));
      BookingMapper.mapToBooking(updateBookingDto, booking);
      bookingRepository.save(booking);
      isUpdated = true;
    }
    return isUpdated;
  }

  /**
   * @param id - Input booking id
   * @return boolean indicating if the deletion of the booking is successful or not
   */
  @Override
  public boolean deleteBooking(Long id) {
    Booking booking = bookingRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Booking", "id", id.toString()));
    bookingRepository.deleteById(booking.getId());
    return true;
  }
}
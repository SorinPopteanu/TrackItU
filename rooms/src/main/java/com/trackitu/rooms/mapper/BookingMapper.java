package com.trackitu.rooms.mapper;

import com.trackitu.rooms.dto.booking.CreateBookingDto;
import com.trackitu.rooms.dto.booking.FetchBookingDto;
import com.trackitu.rooms.dto.booking.UpdateBookingDto;
import com.trackitu.rooms.dto.room.FetchRoomDto;
import com.trackitu.rooms.entity.Booking;
import com.trackitu.rooms.entity.Room;

public class BookingMapper {

  public static Booking mapToBooking(CreateBookingDto createBookingDto, Booking booking, Room room) {
    booking.setRoom(room);
    booking.setCustomerId(createBookingDto.getCustomerId());
    booking.setDate(createBookingDto.getDate());
    booking.setStartTime(createBookingDto.getStartTime());
    booking.setEndTime(createBookingDto.getEndTime());
    booking.setStatus(createBookingDto.getStatus());
    return booking;
  }

  public static void mapToBooking(UpdateBookingDto updateBookingDto, Booking booking) {
    booking.setStatus(updateBookingDto.getStatus());
  }

  public static FetchBookingDto mapToFetchBookingDto(Booking booking, FetchBookingDto fetchBookingDto) {
    fetchBookingDto.setId(booking.getId());
    fetchBookingDto.setRoomDto(RoomMapper.mapToFetchRoomDto(booking.getRoom(), new FetchRoomDto()));
    fetchBookingDto.setCustomerId(booking.getCustomerId());
    fetchBookingDto.setDate(booking.getDate());
    fetchBookingDto.setStartTime(booking.getStartTime());
    fetchBookingDto.setEndTime(booking.getEndTime());
    fetchBookingDto.setStatus(booking.getStatus());
    return fetchBookingDto;
  }


}

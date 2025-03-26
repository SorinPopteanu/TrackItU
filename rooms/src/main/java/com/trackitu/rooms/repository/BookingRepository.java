package com.trackitu.rooms.repository;

import com.trackitu.rooms.entity.Booking;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

  Optional<Booking> findByRoomIdAndBookingDateAndStartTimeAndEndTime(Long roomId,
      LocalDate bookingDate, LocalTime startTime, LocalTime endTime);

}

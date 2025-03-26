package com.trackitu.rooms.entity;

import com.trackitu.rooms.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookings")
public class Booking extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "booking_id")
  private Long bookingId;

  @Column(name = "room_id")
  private Long roomId;

  @Column(name = "customer_id")
  private Long customerId;

  @Column(name = "booking_date")
  private LocalDate bookingDate;

  @Column(name = "start_time")
  private LocalTime startTime;

  @Column(name = "end_time")
  private LocalTime endTime;

  @Column(name = "booking_status")
  @Enumerated(EnumType.STRING)
  private BookingStatus bookingStatus;
}

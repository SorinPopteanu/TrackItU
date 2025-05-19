package com.trackitu.rooms.entity;

import com.trackitu.rooms.enums.Status;
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
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "room_id", referencedColumnName = "id")
  private Room room;

  @Column(name = "customer_id")
  private Long customerId;

  @Column(name = "date")
  private LocalDate date;

  @Column(name = "start_time")
  private LocalTime startTime;

  @Column(name = "end_time")
  private LocalTime endTime;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private Status status;
}

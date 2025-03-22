package com.trackitu.rooms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "room_professor")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoomProfessor extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "room_professor_id")
  private Long id;

  @Column(name = "room_id")
  private Long roomId;

  @Column(name = "professor_id")
  private Long professorId;
}

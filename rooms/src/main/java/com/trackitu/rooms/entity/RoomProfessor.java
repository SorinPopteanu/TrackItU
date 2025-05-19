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
  @Column(name = "id")
  private Long roomProfessorId;

  @ManyToOne
  @JoinColumn(name = "room_id", referencedColumnName = "id")
  private Room room;

  @Column(name = "professor_id")
  private Long professorId;
}

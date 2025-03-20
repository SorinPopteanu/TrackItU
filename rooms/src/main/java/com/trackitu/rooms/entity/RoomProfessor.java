package com.trackitu.rooms.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "room_professor")
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class RoomProfessor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_professor_id")
    private Long id;

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "professor_id")
    private Long professorId;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;
}

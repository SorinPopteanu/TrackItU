package com.trackitu.rooms.repository;

import com.trackitu.rooms.entity.RoomProfessor;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomProfessorRepository extends JpaRepository<RoomProfessor, Long> {

  Optional<RoomProfessor> findByRoomProfessorId(Long roomProfessorId);

//  @Query("SELECT rp FROM RoomProfessor rp WHERE rp.id = :id AND rp.professorId = :professorId")
//  Optional<RoomProfessor> findByRoomIdAndProfessorId(@Param("id") Long id, @Param("professorId") Long professorId);

  Optional<RoomProfessor> findByRoomIdAndProfessorId(Long id, Long professorId);

  List<RoomProfessor> findByRoomId(Long id);

  List<RoomProfessor> findByProfessorId(Long professorId);

  @Transactional
  @Modifying
  void deleteByProfessorId(Long professorId);
}

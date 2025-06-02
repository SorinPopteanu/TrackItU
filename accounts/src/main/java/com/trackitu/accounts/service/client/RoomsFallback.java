package com.trackitu.accounts.service.client;

import com.trackitu.accounts.dto.rooms.FetchRoomProfessorDto;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RoomsFallback implements RoomsFeignClient {

  @Override
  public ResponseEntity<List<FetchRoomProfessorDto>> fetchRoomByProfessorId(String correlationId,
      Long professorId) {
    return null;
  }
}

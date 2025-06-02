package com.trackitu.assets.service.client;

import com.trackitu.assets.dto.rooms.RoomProfessorDto;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RoomsFallback implements RoomsFeignClient {

  @Override
  public ResponseEntity<List<RoomProfessorDto>> fetchProfessorByRoomId(Long id) {
    return null;
  }
}


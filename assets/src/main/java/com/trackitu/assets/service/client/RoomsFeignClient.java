package com.trackitu.assets.service.client;

import com.trackitu.assets.dto.rooms.RoomProfessorDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("rooms")
public interface RoomsFeignClient {

  @GetMapping("/api/v1/rooms/allocation/fetchByRoomId")
  ResponseEntity<List<RoomProfessorDto>> fetchProfessorByRoomId(@RequestParam Long id);
}

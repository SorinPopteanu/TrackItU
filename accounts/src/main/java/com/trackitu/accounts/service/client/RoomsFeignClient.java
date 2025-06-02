package com.trackitu.accounts.service.client;

import com.trackitu.accounts.dto.rooms.FetchRoomProfessorDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("rooms")
public interface RoomsFeignClient {

  @GetMapping(value = "/api/v1/allocation/fetchByProfessorId", consumes = "application/json")
  ResponseEntity<List<FetchRoomProfessorDto>> fetchRoomByProfessorId(
      @RequestHeader("trackItU-correlation-id") String correlationId,
      @RequestParam Long professorId);

}

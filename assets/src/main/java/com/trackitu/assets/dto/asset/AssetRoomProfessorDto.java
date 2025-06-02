package com.trackitu.assets.dto.asset;

import com.trackitu.assets.dto.accounts.CustomerDto;
import com.trackitu.assets.enums.Status;

import java.util.List;
import lombok.Data;

@Data
public class AssetRoomProfessorDto {

  private String roomCode;

  private Status status;

  private List<CustomerDto> professorDtoList;

}

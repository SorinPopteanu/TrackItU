package com.trackitu.assets.dto.asset;

import java.util.List;
import lombok.Data;

@Data
public class FetchAssetRoomProfessorDto {

  private Long assetTypeId;

  private String assetTypeName;

  private List<AssetRoomProfessorDto> assetRoomProfessorList;

}

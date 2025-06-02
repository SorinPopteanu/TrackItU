package com.trackitu.assets.mapper;

import com.trackitu.assets.dto.asset.FetchAssetRoomProfessorDto;
import com.trackitu.assets.dto.asset_type.CreateAssetTypeDto;
import com.trackitu.assets.dto.asset_type.FetchAssetTypeDto;
import com.trackitu.assets.dto.asset_type.UpdateAssetTypeDto;
import com.trackitu.assets.entity.AssetType;

public class AssetTypeMapper {

  public static AssetType mapToAssetType(CreateAssetTypeDto createAssetTypeDto, AssetType assetType) {
    assetType.setName(createAssetTypeDto.getName());
    assetType.setUnitOfMeasure(createAssetTypeDto.getUnitOfMeasure());
    assetType.setManagementType(createAssetTypeDto.getManagementType());
    return assetType;
  }

  public static void mapToAssetType(UpdateAssetTypeDto updateAssetTypeDto, AssetType assetType) {
    assetType.setName(updateAssetTypeDto.getName());
    assetType.setUnitOfMeasure(updateAssetTypeDto.getUnitOfMeasure());
    assetType.setManagementType(updateAssetTypeDto.getManagementType());
  }

  public static FetchAssetTypeDto mapToFetchAssetTypeDto(AssetType assetType, FetchAssetTypeDto fetchAssetTypeDto) {
    fetchAssetTypeDto.setId(assetType.getId());
    fetchAssetTypeDto.setName(assetType.getName());
    fetchAssetTypeDto.setUnitOfMeasure(assetType.getUnitOfMeasure());
    fetchAssetTypeDto.setManagementType(assetType.getManagementType());
    return fetchAssetTypeDto;
  }

  public static FetchAssetRoomProfessorDto mapToFetchAssetRoomProfessorDto(AssetType assetType, FetchAssetRoomProfessorDto fetchAssetRoomProfessorDto) {
    fetchAssetRoomProfessorDto.setAssetTypeId(assetType.getId());
    fetchAssetRoomProfessorDto.setAssetTypeName(assetType.getName());
    return fetchAssetRoomProfessorDto;
  }

}

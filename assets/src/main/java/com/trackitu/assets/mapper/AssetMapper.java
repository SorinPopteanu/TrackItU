package com.trackitu.assets.mapper;

import com.trackitu.assets.dto.AssetDto;
import com.trackitu.assets.entity.Asset;

public class AssetMapper {

  public static AssetDto mapToAssetDto(Asset asset, AssetDto assetDto) {
    assetDto.setInventoryNumber(asset.getInventoryNumber());
    assetDto.setAssetType(asset.getAssetType());
    assetDto.setFundingSource(asset.getFundingSource());
    assetDto.setAcquisitionDate(asset.getAcquisitionDate());
    assetDto.setPrice(asset.getPrice());
    assetDto.setRoomCode(asset.getRoomCode());
    assetDto.setStatus(asset.getStatus());
    return assetDto;
  }

  public static Asset mapToAsset(AssetDto assetDto, Asset asset) {
    asset.setInventoryNumber(assetDto.getInventoryNumber());
    asset.setAssetType(assetDto.getAssetType());
    asset.setFundingSource(assetDto.getFundingSource());
    asset.setAcquisitionDate(assetDto.getAcquisitionDate());
    asset.setPrice(assetDto.getPrice());
    asset.setRoomCode(assetDto.getRoomCode());
    asset.setStatus(assetDto.getStatus());
    return asset;
  }
}

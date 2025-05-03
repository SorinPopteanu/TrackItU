package com.trackitu.assets.mapper;

import com.trackitu.assets.dto.asset.CreateAssetDto;
import com.trackitu.assets.dto.asset.FetchAssetDto;
import com.trackitu.assets.dto.asset.UpdateAssetDto;
import com.trackitu.assets.entity.Asset;
import com.trackitu.assets.entity.AssetType;
import com.trackitu.assets.entity.FundingSource;

public class AssetMapper {

  public static Asset mapToAsset(CreateAssetDto assetDto, Asset asset, AssetType assetType, FundingSource fundingSource) {
    asset.setInventoryNumber(assetDto.getInventoryNumber());
    asset.setAssetType(assetType);
    asset.setFundingSource(fundingSource);
    asset.setAcquisitionDate(assetDto.getAcquisitionDate());
    asset.setPrice(assetDto.getPrice());
    asset.setRoomCode(assetDto.getRoomCode());
    asset.setStatus(assetDto.getStatus());
    return asset;
  }

  public static void mapToAsset(UpdateAssetDto assetDto, Asset asset, AssetType assetType, FundingSource fundingSource) {
    asset.setInventoryNumber(assetDto.getInventoryNumber());
    asset.setAssetType(assetType);
    asset.setFundingSource(fundingSource);
    asset.setAcquisitionDate(assetDto.getAcquisitionDate());
    asset.setPrice(assetDto.getPrice());
    asset.setRoomCode(assetDto.getRoomCode());
    asset.setStatus(assetDto.getStatus());
  }

  public static FetchAssetDto mapToFetchAssetDto(Asset asset, FetchAssetDto fetchAssetDto) {
    fetchAssetDto.setId(asset.getId());
    fetchAssetDto.setInventoryNumber(asset.getInventoryNumber());
    fetchAssetDto.setAssetType(asset.getAssetType());
    fetchAssetDto.setFundingSource(asset.getFundingSource());
    fetchAssetDto.setAcquisitionDate(asset.getAcquisitionDate());
    fetchAssetDto.setPrice(asset.getPrice());
    fetchAssetDto.setRoomCode(asset.getRoomCode());
    fetchAssetDto.setStatus(asset.getStatus());
    return fetchAssetDto;
  }
}

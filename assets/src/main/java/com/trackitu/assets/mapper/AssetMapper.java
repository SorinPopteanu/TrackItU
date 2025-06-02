package com.trackitu.assets.mapper;

import com.trackitu.assets.dto.asset.CreateAssetDto;
import com.trackitu.assets.dto.asset.FetchAssetDto;
import com.trackitu.assets.dto.asset.FetchAssetRoomProfessorDto;
import com.trackitu.assets.dto.asset.UpdateAssetDto;
import com.trackitu.assets.dto.asset_type.FetchAssetTypeDto;
import com.trackitu.assets.dto.funding_source.FetchFundingSourceDto;
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
    asset.setRoomId(assetDto.getRoomId());
    asset.setStatus(assetDto.getStatus());
    return asset;
  }

  public static void mapToAsset(UpdateAssetDto assetDto, Asset asset, AssetType assetType, FundingSource fundingSource) {
    asset.setInventoryNumber(assetDto.getInventoryNumber());
    asset.setAssetType(assetType);
    asset.setFundingSource(fundingSource);
    asset.setAcquisitionDate(assetDto.getAcquisitionDate());
    asset.setPrice(assetDto.getPrice());
    asset.setRoomId(assetDto.getRoomId());
    asset.setStatus(assetDto.getStatus());
  }

  public static FetchAssetDto mapToFetchAssetDto(Asset asset, FetchAssetDto fetchAssetDto) {
    fetchAssetDto.setId(asset.getId());
    fetchAssetDto.setInventoryNumber(asset.getInventoryNumber());
    fetchAssetDto.setAssetTypeDto(AssetTypeMapper.mapToFetchAssetTypeDto(asset.getAssetType(), new FetchAssetTypeDto()));
    fetchAssetDto.setFundingSourceDto(FundingSourceMapper.mapToFetchFundingSourceDto(asset.getFundingSource(), new FetchFundingSourceDto()));
    fetchAssetDto.setAcquisitionDate(asset.getAcquisitionDate());
    fetchAssetDto.setPrice(asset.getPrice());
    fetchAssetDto.setRoomId(asset.getRoomId());
    fetchAssetDto.setStatus(asset.getStatus());
    return fetchAssetDto;
  }
}

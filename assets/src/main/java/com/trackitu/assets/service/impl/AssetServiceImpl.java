package com.trackitu.assets.service.impl;

import com.trackitu.assets.dto.asset.CreateAssetDto;
import com.trackitu.assets.dto.asset.FetchAssetDto;
import com.trackitu.assets.dto.asset.UpdateAssetDto;
import com.trackitu.assets.entity.Asset;
import com.trackitu.assets.entity.AssetType;
import com.trackitu.assets.entity.FundingSource;
import com.trackitu.assets.exception.AssetAlreadyExistsException;
import com.trackitu.assets.exception.ResourceNotFoundException;
import com.trackitu.assets.mapper.AssetMapper;
import com.trackitu.assets.repository.AssetRepository;
import com.trackitu.assets.repository.AssetTypeRepository;
import com.trackitu.assets.repository.FundingSourceRepository;
import com.trackitu.assets.service.IAssetService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AssetServiceImpl implements IAssetService {

  private AssetTypeRepository assetTypeRepository;
  private FundingSourceRepository fundingSourceRepository;
  private AssetRepository assetRepository;

  /**
   * @param createAssetDto - CreateAssetDto Object
   */
  @Override
  public void createAsset(CreateAssetDto createAssetDto) {
    AssetType assetType = assetTypeRepository.findById(createAssetDto.getAssetTypeId()).orElseThrow(
        () -> new ResourceNotFoundException("Asset type", "id",
            createAssetDto.getAssetTypeId().toString()));
    FundingSource fundingSource = fundingSourceRepository.findById(
        createAssetDto.getFundingSourceId()).orElseThrow(
        () -> new ResourceNotFoundException("Funding source", "id",
            createAssetDto.getFundingSourceId().toString()));
    Asset asset = AssetMapper.mapToAsset(createAssetDto, new Asset(), assetType, fundingSource);
    Optional<Asset> optionalAsset = assetRepository.findByInventoryNumber(
        asset.getInventoryNumber());
    if (optionalAsset.isPresent()) {
      throw new AssetAlreadyExistsException(
          "Asset already exists with inventory number: "
              + createAssetDto.getInventoryNumber());
    } else {
      assetRepository.save(asset);
    }
  }

  /**
   * @return List of all the Assets
   */
  @Override
  public List<FetchAssetDto> fetchAllAssets() {
    List<Asset> assetList = assetRepository.findAll();
    List<FetchAssetDto> assetDtoList = new ArrayList<>();

    for (Asset asset : assetList) {
      assetDtoList.add(
          AssetMapper.mapToFetchAssetDto(asset, new FetchAssetDto()));
    }
    return assetDtoList;
  }

  /**
   * @param updateAssetDto - CreateAssetDto Object
   * @return boolean indicating if the update of the Asset details is successful or not
   */
  @Override
  public boolean updateAssetDetails(UpdateAssetDto updateAssetDto) {
    boolean isUpdated = false;
    if (updateAssetDto != null) {
      AssetType assetType = assetTypeRepository.findById(updateAssetDto.getAssetTypeId())
          .orElseThrow(
              () -> new ResourceNotFoundException("Asset type", "id",
                  updateAssetDto.getAssetTypeId().toString()));
      FundingSource fundingSource = fundingSourceRepository.findById(
          updateAssetDto.getFundingSourceId()).orElseThrow(
          () -> new ResourceNotFoundException("Funding source", "id",
              updateAssetDto.getFundingSourceId().toString()));
      Asset asset = assetRepository.findById(updateAssetDto.getId()).orElseThrow(
          () -> new ResourceNotFoundException("Asset", "id", updateAssetDto.getId().toString()));
      AssetMapper.mapToAsset(updateAssetDto, asset, assetType, fundingSource);
      assetRepository.save(asset);
      isUpdated = true;
    }
    return isUpdated;
  }

  /**
   * @param id - Input asset id
   * @return boolean indicating if the deletion of the Asset is successful or not
   */
  @Override
  public boolean deleteAsset(Long id) {
    Asset asset = assetRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Asset", "id", id.toString()));
    assetRepository.deleteById(asset.getId());
    return true;
  }
}

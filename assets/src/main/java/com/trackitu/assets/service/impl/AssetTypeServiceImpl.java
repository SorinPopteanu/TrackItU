package com.trackitu.assets.service.impl;

import com.trackitu.assets.dto.accounts.CustomerDto;
import com.trackitu.assets.dto.asset.AssetRoomProfessorDto;
import com.trackitu.assets.dto.asset.FetchAssetDto;
import com.trackitu.assets.dto.asset.FetchAssetRoomProfessorDto;
import com.trackitu.assets.dto.asset_type.CreateAssetTypeDto;
import com.trackitu.assets.dto.asset_type.FetchAssetTypeDto;
import com.trackitu.assets.dto.asset_type.UpdateAssetTypeDto;
import com.trackitu.assets.dto.rooms.RoomProfessorDto;
import com.trackitu.assets.entity.Asset;
import com.trackitu.assets.entity.AssetType;
import com.trackitu.assets.exception.AssetTypeAlreadyExistsException;
import com.trackitu.assets.exception.ResourceNotFoundException;
import com.trackitu.assets.mapper.AssetMapper;
import com.trackitu.assets.mapper.AssetTypeMapper;
import com.trackitu.assets.repository.AssetRepository;
import com.trackitu.assets.repository.AssetTypeRepository;
import com.trackitu.assets.service.IAssetTypeService;
import com.trackitu.assets.service.client.AccountsFeignClient;
import com.trackitu.assets.service.client.RoomsFeignClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AssetTypeServiceImpl implements IAssetTypeService {

  private final RoomsFeignClient roomsFeignClient;
  private final AccountsFeignClient accountsFeignClient;
  private final AssetTypeRepository assetTypeRepository;
  private final AssetRepository assetRepository;

  /**
   * @param createAssetTypeDto - CreateAssetTypeDto Object
   */
  @Override
  public void createAssetType(CreateAssetTypeDto createAssetTypeDto) {
    AssetType assetType = AssetTypeMapper.mapToAssetType(createAssetTypeDto, new AssetType());
    Optional<AssetType> optionalAssetType = assetTypeRepository.findByName(
        createAssetTypeDto.getName());
    if (optionalAssetType.isPresent()) {
      throw new AssetTypeAlreadyExistsException(
          "Asset type exists with name: " + createAssetTypeDto.getName());
    } else {
      assetTypeRepository.save(assetType);
    }
  }

  /**
   * @param id - Input asset type id
   * @return Asset type details based on the asset type id.
   */
  @Override
  public FetchAssetTypeDto fetchAssetTypeDetails(Long id) {
    AssetType assetType = assetTypeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Asset type", "id", id.toString()));
    return AssetTypeMapper.mapToFetchAssetTypeDto(assetType, new FetchAssetTypeDto());
  }

  /**
   * @return List of all the Asset types
   */
  @Override
  public List<FetchAssetTypeDto> fetchAllAssetTypes() {
    List<AssetType> assetTypeList = assetTypeRepository.findAll();
    List<FetchAssetTypeDto> assetTypeDtoList = new ArrayList<>();

    for (AssetType assetType : assetTypeList) {
      assetTypeDtoList.add(
          AssetTypeMapper.mapToFetchAssetTypeDto(assetType, new FetchAssetTypeDto()));
    }
    return assetTypeDtoList;
  }

  /**
   * @param assetTypeId - Input asset type id
   * @return List of all the rooms and the corresponding professors in which the asset exists
   */
  @Override
  public FetchAssetRoomProfessorDto fetchAssetRoomProfessor(Long assetTypeId) {
    AssetType assetType = assetTypeRepository.findById(assetTypeId)
        .orElseThrow(() -> new ResourceNotFoundException("AssetType", "id", assetTypeId.toString()));

    List<Asset> assets = assetRepository.findByAssetType(assetType);
    if (assets.isEmpty()) {
      throw new ResourceNotFoundException("Asset", "assetTypeId", assetTypeId.toString());
    }

    FetchAssetRoomProfessorDto fetchAssetRoomProfessorDto = AssetTypeMapper.mapToFetchAssetRoomProfessorDto(
        assetType, new FetchAssetRoomProfessorDto());
    List<AssetRoomProfessorDto> assetRoomProfessorDtoList = new ArrayList<>();

    for (Asset asset : assets) {
      AssetRoomProfessorDto assetRoomProfessorDto = new AssetRoomProfessorDto();
      assetRoomProfessorDto.setStatus(asset.getStatus());

      ResponseEntity<List<RoomProfessorDto>> roomProfessorDtoResponseEntity = roomsFeignClient.fetchProfessorByRoomId(asset.getRoomId());
      if (roomProfessorDtoResponseEntity.getBody() != null) {
        List<CustomerDto> customerDtoList = new ArrayList<>();
        for (RoomProfessorDto roomProfessorDto : roomProfessorDtoResponseEntity.getBody()) {
          assetRoomProfessorDto.setRoomCode(roomProfessorDto.getRoomDto().getRoomCode());

          ResponseEntity<CustomerDto> customerDtoResponseEntity = accountsFeignClient.fetchCustomer(roomProfessorDto.getProfessorId());
          if (customerDtoResponseEntity.getBody() != null) {
            customerDtoList.add(customerDtoResponseEntity.getBody());
          }
        }
        assetRoomProfessorDto.setProfessorDtoList(customerDtoList);
        assetRoomProfessorDtoList.add(assetRoomProfessorDto);
      }
    }

    // Set the list in the DTO
    fetchAssetRoomProfessorDto.setAssetRoomProfessorList(assetRoomProfessorDtoList);

    return fetchAssetRoomProfessorDto;
  }


  /**
   * @param updateAssetTypeDto - CreateAssetTypeDto Object
   * @return boolean indicating if the update of the Asset type details is successful or not
   */
  @Override
  public boolean updateAssetTypeDetails(UpdateAssetTypeDto updateAssetTypeDto) {
    boolean isUpdated = false;
    if (updateAssetTypeDto != null) {
      AssetType assetType = assetTypeRepository.findById(updateAssetTypeDto.getId()).orElseThrow(
          () -> new ResourceNotFoundException("Asset type", "id",
              updateAssetTypeDto.getId().toString()));
      AssetTypeMapper.mapToAssetType(updateAssetTypeDto, assetType);
      assetTypeRepository.save(assetType);
      isUpdated = true;
    }
    return isUpdated;
  }

  /**
   * @param id - Input Asset type id
   * @return boolean indicating if the deletion of the Asset type is successful or not
   */
  @Override
  public boolean deleteAssetType(Long id) {
    AssetType assetType = assetTypeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Asset type", "id", id.toString()));
    assetTypeRepository.deleteById(assetType.getId());
    return true;
  }

}

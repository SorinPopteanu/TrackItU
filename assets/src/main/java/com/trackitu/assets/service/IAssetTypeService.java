package com.trackitu.assets.service;

import com.trackitu.assets.dto.asset.FetchAssetRoomProfessorDto;
import com.trackitu.assets.dto.asset_type.CreateAssetTypeDto;
import com.trackitu.assets.dto.asset_type.FetchAssetTypeDto;
import com.trackitu.assets.dto.asset_type.UpdateAssetTypeDto;
import jakarta.validation.Valid;
import java.util.List;

public interface IAssetTypeService {

  /**
   * @param createAssetTypeDto - CreateAssetTypeDto Object
   */
  void createAssetType(CreateAssetTypeDto createAssetTypeDto);

  /**
   * @param id - Input asset type id
   * @return Asset type details based on the asset type id.
   */
  FetchAssetTypeDto fetchAssetTypeDetails(Long id);

  /**
   * @return List of all the Asset types
   */
  List<FetchAssetTypeDto> fetchAllAssetTypes();

  /**
   *
   * @param updateAssetTypeDto - UpdateAssetTypeDto Object
   * @return boolean indicating if the update of the Asset type details is successful or not
   */
  boolean updateAssetTypeDetails(@Valid UpdateAssetTypeDto updateAssetTypeDto);

  /**
   * @param id - Input Asset type id
   * @return boolean indicating if the deletion of the Asset type is successful or not
   */
  boolean deleteAssetType(Long id);

  /**
   * @param id - Input asset id
   * @return the room and the corresponding professors in which the asset exists
   */
  FetchAssetRoomProfessorDto fetchAssetRoomProfessor(Long id);
}


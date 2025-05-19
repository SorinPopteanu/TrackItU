package com.trackitu.assets.service;

import com.trackitu.assets.dto.asset.CreateAssetDto;
import com.trackitu.assets.dto.asset.FetchAssetDto;
import com.trackitu.assets.dto.asset.UpdateAssetDto;
import jakarta.validation.Valid;
import java.util.List;

public interface IAssetService {

  /**
   * @param createAssetDto - CreateAssetDto Object
   */
  void createAsset(CreateAssetDto createAssetDto);

  /**
   * @return List of all the Assets
   */
  List<FetchAssetDto> fetchAllAssets();

  /**
   * @param updateAssetDto - CreateAssetDto Object
   * @return boolean indicating if the update of the Asset details is successful or not
   */
  boolean updateAssetDetails(@Valid UpdateAssetDto updateAssetDto);

  /**
   * @param id - Input asset id
   * @return boolean indicating if the deletion of the Asset is successful or not
   */
  boolean deleteAsset(Long id);
}

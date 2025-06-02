package com.trackitu.assets.controller;

import com.trackitu.assets.constants.AssetsConstants;
import com.trackitu.assets.dto.asset.FetchAssetRoomProfessorDto;
import com.trackitu.assets.dto.asset_type.CreateAssetTypeDto;
import com.trackitu.assets.dto.ResponseDto;
import com.trackitu.assets.dto.asset_type.FetchAssetTypeDto;
import com.trackitu.assets.dto.asset_type.UpdateAssetTypeDto;
import com.trackitu.assets.service.IAssetTypeService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/asset-type")
@AllArgsConstructor
@Validated
public class AssetTypeController {

  private IAssetTypeService iAssetTypeService;

  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createAssetType(
      @Valid @RequestBody CreateAssetTypeDto createAssetTypeDto) {
    iAssetTypeService.createAssetType(createAssetTypeDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(AssetsConstants.STATUS_201, AssetsConstants.MESSAGE_201));
  }

  @GetMapping("/fetch")
  public ResponseEntity<FetchAssetTypeDto> fetchAssetTypeDetails(@RequestParam Long id) {
    FetchAssetTypeDto fetchAssetTypeDto = iAssetTypeService.fetchAssetTypeDetails(id);
    return ResponseEntity.status(HttpStatus.OK).body(fetchAssetTypeDto);
  }

  @GetMapping("/fetchAll")
  public ResponseEntity<List<FetchAssetTypeDto>> fetchAllAssetTypes() {
    List<FetchAssetTypeDto> assetTypeDtoList = iAssetTypeService.fetchAllAssetTypes();
    return ResponseEntity.status(HttpStatus.OK).body(assetTypeDtoList);
  }

  @GetMapping("fetchRoomProfessor")
  public ResponseEntity<FetchAssetRoomProfessorDto> fetchRoomProfessor(@RequestParam Long id) {
    FetchAssetRoomProfessorDto fetchAssetRoomProfessorDto = iAssetTypeService.fetchAssetRoomProfessor(id);
    return ResponseEntity.status(HttpStatus.OK).body(fetchAssetRoomProfessorDto);
  }


  @PutMapping("/update")
  public ResponseEntity<ResponseDto> updateAssetTypeDetails(
      @Valid @RequestBody UpdateAssetTypeDto updateAssetTypeDto) {
    boolean isUpdated = iAssetTypeService.updateAssetTypeDetails(updateAssetTypeDto);
    if (isUpdated) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(AssetsConstants.STATUS_200, AssetsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseDto(AssetsConstants.STATUS_500, AssetsConstants.MESSAGE_500));
    }
  }

  @DeleteMapping("/delete")
  public ResponseEntity<ResponseDto> deleteAssetType(@RequestParam Long id) {
    boolean isDeleted = iAssetTypeService.deleteAssetType(id);
    if (isDeleted) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(AssetsConstants.STATUS_200, AssetsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseDto(AssetsConstants.STATUS_500, AssetsConstants.MESSAGE_500));
    }
  }
}

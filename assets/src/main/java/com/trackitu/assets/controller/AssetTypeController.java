package com.trackitu.assets.controller;

import com.trackitu.assets.constants.AssetsConstants;
import com.trackitu.assets.dto.asset.FetchAssetRoomProfessorDto;
import com.trackitu.assets.dto.asset_type.CreateAssetTypeDto;
import com.trackitu.assets.dto.ResponseDto;
import com.trackitu.assets.dto.asset_type.FetchAssetTypeDto;
import com.trackitu.assets.dto.asset_type.UpdateAssetTypeDto;
import com.trackitu.assets.service.IAssetTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "CRUD REST APIs pentru tipurile de bunuri", description = "Rest APIs care permit crearea, actualizarea, stergerea si preluarea detaliilor categoriilor de bunuri")
@RestController
@RequestMapping("/api/v1/asset-type")
@AllArgsConstructor
@Validated
public class AssetTypeController {

  private IAssetTypeService iAssetTypeService;

  @Operation(summary = "REST API pentru crearea unei categorii de bunuri", description = "Adauga o noua categorie de elemente de inventar")
  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createAssetType(
      @Valid @RequestBody CreateAssetTypeDto createAssetTypeDto) {
    iAssetTypeService.createAssetType(createAssetTypeDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(AssetsConstants.STATUS_201, AssetsConstants.MESSAGE_201));
  }

  @Operation(summary = "REST API pentru preluarea detaliilor categoriei de bunuri", description = "REST API pentru preluarea detaliilor categoriei de bunuri pe baza id-ului")
  @GetMapping("/fetch")
  public ResponseEntity<FetchAssetTypeDto> fetchAssetTypeDetails(@RequestParam Long id) {
    FetchAssetTypeDto fetchAssetTypeDto = iAssetTypeService.fetchAssetTypeDetails(id);
    return ResponseEntity.status(HttpStatus.OK).body(fetchAssetTypeDto);
  }

  @Operation(summary = "REST API pentru preluarea tuturor categoriilor de bunuri", description = "REST API pentru preluarea tuturor categoriilor de bunuri si a detaliilor acestora")
  @GetMapping("/fetchAll")
  public ResponseEntity<List<FetchAssetTypeDto>> fetchAllAssetTypes() {
    List<FetchAssetTypeDto> assetTypeDtoList = iAssetTypeService.fetchAllAssetTypes();
    return ResponseEntity.status(HttpStatus.OK).body(assetTypeDtoList);
  }

  @GetMapping("/fetchRoomProfessor")
  public ResponseEntity<FetchAssetRoomProfessorDto> fetchAssetRoomProfessor(@RequestParam Long id) {
    FetchAssetRoomProfessorDto fetchAssetRoomProfessorDto = iAssetTypeService.fetchAssetRoomProfessor(id);
    return ResponseEntity.status(HttpStatus.OK).body(fetchAssetRoomProfessorDto);
  }

  @Operation(summary = "REST API pentru actualizarea detaliilor unei categorii de bunuri", description = "REST API pentru actualizarea detaliilor unei categorii de bunuri pe baza id-ului")
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

  @Operation(summary = "REST API pentru stergerea detaliilor unei categorii de bunuri", description = "REST API pentru stergerea detaliilor unei categorii de bunuri pe baza id-ului")
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

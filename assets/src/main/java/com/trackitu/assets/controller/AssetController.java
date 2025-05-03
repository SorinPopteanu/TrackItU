package com.trackitu.assets.controller;

import com.trackitu.assets.constants.AssetsConstants;
import com.trackitu.assets.dto.ResponseDto;
import com.trackitu.assets.dto.asset.CreateAssetDto;
import com.trackitu.assets.dto.asset.FetchAssetDto;
import com.trackitu.assets.dto.asset.UpdateAssetDto;
import com.trackitu.assets.service.IAssetService;
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
@RequestMapping(path = "/api/v1/assets/asset")
@AllArgsConstructor
@Validated
public class AssetController {

  private IAssetService iAssetService;

  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createAsset(
      @RequestBody CreateAssetDto createAssetDto) {
    iAssetService.createAsset(createAssetDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(AssetsConstants.STATUS_201, AssetsConstants.MESSAGE_201));
  }

  @GetMapping("/fetchAll")
  public ResponseEntity<List<FetchAssetDto>> fetchAllAssets() {
    List<FetchAssetDto> fetchAssetDtoList = iAssetService.fetchAllAssets();
    return ResponseEntity.status(HttpStatus.OK).body(fetchAssetDtoList);
  }

  @PutMapping("/update")
  public ResponseEntity<ResponseDto> updateAssetDetails(
      @Valid @RequestBody UpdateAssetDto updateAssetDto) {
    boolean isUpdated = iAssetService.updateAssetDetails(updateAssetDto);
    if (isUpdated) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(AssetsConstants.STATUS_200, AssetsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status((HttpStatus.INTERNAL_SERVER_ERROR))
          .body(new ResponseDto(AssetsConstants.STATUS_500, AssetsConstants.MESSAGE_500));
    }
  }

  @DeleteMapping("/delete")
  public ResponseEntity<ResponseDto> deleteAsset(@RequestParam Long id) {
    boolean isDeleted = iAssetService.deleteAsset(id);
    if (isDeleted) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(AssetsConstants.STATUS_200, AssetsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status((HttpStatus.INTERNAL_SERVER_ERROR))
          .body(new ResponseDto(AssetsConstants.STATUS_500, AssetsConstants.MESSAGE_500));
    }
  }
}

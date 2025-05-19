package com.trackitu.assets.controller;

import com.trackitu.assets.constants.AssetsConstants;
import com.trackitu.assets.dto.funding_source.CreateFundingSourceDto;
import com.trackitu.assets.dto.ResponseDto;
import com.trackitu.assets.dto.funding_source.FetchFundingSourceDto;
import com.trackitu.assets.dto.funding_source.UpdateFundingSourceDto;
import com.trackitu.assets.service.IFundingSourceService;
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
@RequestMapping(path = "/api/v1/assets/funding-source")
@AllArgsConstructor
@Validated
public class FundingSourceController {

  private IFundingSourceService iFundingSourceService;

  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createFundingSource(
      @RequestBody CreateFundingSourceDto createFundingSourceDto) {
    iFundingSourceService.createFundingSource(createFundingSourceDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(AssetsConstants.STATUS_201, AssetsConstants.MESSAGE_201));
  }

  @GetMapping("/fetchAll")
  public ResponseEntity<List<FetchFundingSourceDto>> fetchAllFundingSources() {
    List<FetchFundingSourceDto> fundingSourceDtoList = iFundingSourceService.fetchAllFundingSources();
    return ResponseEntity.status(HttpStatus.OK).body(fundingSourceDtoList);
  }

  @PutMapping("/update")
  public ResponseEntity<ResponseDto> updateFundingSourceDetails(
      @Valid @RequestBody UpdateFundingSourceDto updateFundingSourceDto) {
    boolean isUpdated = iFundingSourceService.updateFundingSourceDetails(updateFundingSourceDto);
    if (isUpdated) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(AssetsConstants.STATUS_200, AssetsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status((HttpStatus.INTERNAL_SERVER_ERROR))
          .body(new ResponseDto(AssetsConstants.STATUS_500, AssetsConstants.MESSAGE_500));
    }
  }

  @DeleteMapping("/delete")
  public ResponseEntity<ResponseDto> deleteFundingSource(@RequestParam Long id) {
    boolean isDeleted = iFundingSourceService.deleteFundingSource(id);
    if (isDeleted) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(AssetsConstants.STATUS_200, AssetsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status((HttpStatus.INTERNAL_SERVER_ERROR))
          .body(new ResponseDto(AssetsConstants.STATUS_500, AssetsConstants.MESSAGE_500));
    }
  }
}

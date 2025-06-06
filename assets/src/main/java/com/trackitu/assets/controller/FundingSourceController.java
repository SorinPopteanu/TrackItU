package com.trackitu.assets.controller;

import com.trackitu.assets.constants.AssetsConstants;
import com.trackitu.assets.dto.funding_source.CreateFundingSourceDto;
import com.trackitu.assets.dto.ResponseDto;
import com.trackitu.assets.dto.funding_source.FetchFundingSourceDto;
import com.trackitu.assets.dto.funding_source.UpdateFundingSourceDto;
import com.trackitu.assets.service.IFundingSourceService;
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

@Tag(name = "CRUD REST APIs pentru sursele de finantare", description = "Rest APIs care permit crearea, actualizarea, stergerea si preluarea detaliilor surselor de finantare")
@RestController
@RequestMapping(path = "/api/v1/funding-source")
@AllArgsConstructor
@Validated
public class FundingSourceController {

  private IFundingSourceService iFundingSourceService;

  @Operation(summary = "REST API pentru crearea unei surse de finantare", description = "Adauga o noua sursa de finantare")
  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createFundingSource(
      @RequestBody CreateFundingSourceDto createFundingSourceDto) {
    iFundingSourceService.createFundingSource(createFundingSourceDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(AssetsConstants.STATUS_201, AssetsConstants.MESSAGE_201));
  }

  @Operation(summary = "REST API pentru preluarea tuturor surselor de finantare", description = "REST API pentru preluarea tuturor surselor de finantare si a detaliilor acestora")
  @GetMapping("/fetchAll")
  public ResponseEntity<List<FetchFundingSourceDto>> fetchAllFundingSources() {
    List<FetchFundingSourceDto> fundingSourceDtoList = iFundingSourceService.fetchAllFundingSources();
    return ResponseEntity.status(HttpStatus.OK).body(fundingSourceDtoList);
  }

  @Operation(summary = "REST API pentru actualizarea detaliilor unei surse de finantare", description = "REST API pentru actualizarea detaliilor unei surse de finantare pe baza id-ului")
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

  @Operation(summary = "REST API pentru stergerea unei surse de finantare", description = "REST API pentru stergerea unei surse de finantare pe baza id-ului")
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

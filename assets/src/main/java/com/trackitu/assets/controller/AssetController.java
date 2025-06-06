package com.trackitu.assets.controller;

import com.trackitu.assets.constants.AssetsConstants;
import com.trackitu.assets.dto.AssetsContactInfoDto;
import com.trackitu.assets.dto.ErrorResponseDto;
import com.trackitu.assets.dto.ResponseDto;
import com.trackitu.assets.dto.asset.CreateAssetDto;
import com.trackitu.assets.dto.asset.FetchAssetDto;
import com.trackitu.assets.dto.asset.FetchAssetRoomProfessorDto;
import com.trackitu.assets.dto.asset.UpdateAssetDto;
import com.trackitu.assets.service.IAssetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
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

@Tag(name = "CRUD REST APIs pentru elementele de inventar", description = "Rest APIs care permit crearea, actualizarea, stergerea si preluarea detaliilor elementelor de inventar")
@RestController
@RequestMapping(path = "/api/v1/asset")
@Validated
public class AssetController {

  private final IAssetService iAssetService;
  private final AssetsContactInfoDto assetsContactInfoDto;
  private final Environment environment;

  public AssetController(IAssetService iAssetService, AssetsContactInfoDto assetsContactInfoDto, Environment environment) {
    this.iAssetService = iAssetService;
    this.assetsContactInfoDto = assetsContactInfoDto;
    this.environment = environment;
  }

  @Value("${build.version}")
  private String buildVersion;

  @Operation(summary = "REST API pentru crearea unui element de inventar", description = "Adauga un nou element de inventar")
  @ApiResponse(responseCode = "201", description = "HTTP Status CREATED")
  @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createAsset(
      @RequestBody CreateAssetDto createAssetDto) {
    iAssetService.createAsset(createAssetDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(AssetsConstants.STATUS_201, AssetsConstants.MESSAGE_201));
  }

  @Operation(summary = "REST API pentru preluarea tuturor bunurilor", description = "REST API pentru preluarea tuturor elementelor de inventar si a detaliilor acestora")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @GetMapping("/fetchAll")
  public ResponseEntity<List<FetchAssetDto>> fetchAllAssets() {
    List<FetchAssetDto> fetchAssetDtoList = iAssetService.fetchAllAssets();
    return ResponseEntity.status(HttpStatus.OK).body(fetchAssetDtoList);
  }

  @Operation(summary = "REST API pentru actualizarea detaliilor unui bun", description = "REST API pentru actualizarea detaliilor unui element de inventar pe baza id-ului")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
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

  @Operation(summary = "REST API pentru stergerea unui bun", description = "REST API pentru stergerea unui element de inventar pe baza numarului de identificare")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
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

  @Operation(summary = "Informatii de contact", description = "Detalii de contact la care se poate apela in caz de probleme")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @GetMapping("/contact-info")
  public ResponseEntity<AssetsContactInfoDto> getContactInfo() {
    return ResponseEntity.status(HttpStatus.OK).body(assetsContactInfoDto);
  }

  @Operation(summary = "Informatii despre versiune", description = "Informatii despre versiunea microserviciului assets")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @GetMapping("/build-info")
  public ResponseEntity<String> getBuildInfo() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(buildVersion);
  }

  @Operation(summary = "Versiunea Java", description = "Detalii despre versiunea Java instalata in microserviciul assets")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @GetMapping("/java-version")
  public ResponseEntity<String> getJavaVersion() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(environment.getProperty("JAVA_HOME"));
  }
}

package com.trackitu.assets.dto.asset;


import com.trackitu.assets.enums.Status;
import java.time.LocalDate;
import lombok.Data;

@Data
public class UpdateAssetDto {

  private Long id;

  private String inventoryNumber;

  private Long assetTypeId;

  private Long fundingSourceId;

  private LocalDate acquisitionDate;

  private double price;

  private String roomCode;

  private Status status;

}

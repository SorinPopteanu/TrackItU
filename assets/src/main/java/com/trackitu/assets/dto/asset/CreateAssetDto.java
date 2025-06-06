package com.trackitu.assets.dto.asset;

import com.trackitu.assets.enums.Status;
import java.time.LocalDate;
import lombok.Data;

@Data
public class CreateAssetDto {

  private String inventoryNumber;

  private Long assetTypeId;

  private Long fundingSourceId;

  private LocalDate acquisitionDate;

  private double price;

  private Long roomId;

  private Status status;
}

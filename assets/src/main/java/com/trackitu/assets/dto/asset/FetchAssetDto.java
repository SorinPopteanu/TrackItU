package com.trackitu.assets.dto.asset;

import com.trackitu.assets.dto.asset_type.FetchAssetTypeDto;
import com.trackitu.assets.dto.funding_source.FetchFundingSourceDto;
import com.trackitu.assets.enums.Status;
import java.time.LocalDate;
import lombok.Data;

@Data
public class FetchAssetDto {

  private Long id;

  private String inventoryNumber;

  private FetchAssetTypeDto assetTypeDto;

  private FetchFundingSourceDto fundingSourceDto;

  private LocalDate acquisitionDate;

  private double price;

  private String roomCode;

  private Status status;
}

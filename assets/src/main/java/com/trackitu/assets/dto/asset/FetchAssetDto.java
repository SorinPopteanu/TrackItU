package com.trackitu.assets.dto.asset;

import com.trackitu.assets.entity.AssetType;
import com.trackitu.assets.entity.FundingSource;
import com.trackitu.assets.enums.Status;
import java.time.LocalDate;
import lombok.Data;

@Data
public class FetchAssetDto {

  private Long id;

  private String inventoryNumber;

  private AssetType assetType;

  private FundingSource fundingSource;

  private LocalDate acquisitionDate;

  private double price;

  private String roomCode;

  private Status status;
}

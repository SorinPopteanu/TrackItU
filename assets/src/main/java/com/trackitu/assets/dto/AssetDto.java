package com.trackitu.assets.dto;

import com.trackitu.assets.entity.AssetType;
import com.trackitu.assets.entity.FundingSource;
import com.trackitu.assets.enums.Status;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AssetDto {

  private String inventoryNumber;

  private AssetType assetType;

  private FundingSource fundingSource;

  private LocalDateTime acquisitionDate;

  private double price;

  private String roomCode;

  private Status status;
}

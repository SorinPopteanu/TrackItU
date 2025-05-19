package com.trackitu.assets.dto.asset_type;

import com.trackitu.assets.enums.ManagementType;
import lombok.Data;

@Data
public class CreateAssetTypeDto {

  private String name;

  private String unitOfMeasure;

  private ManagementType managementType;

}
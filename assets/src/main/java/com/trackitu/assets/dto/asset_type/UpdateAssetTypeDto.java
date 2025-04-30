package com.trackitu.assets.dto.asset_type;

import com.trackitu.assets.enums.ManagementType;
import lombok.Data;

@Data
public class UpdateAssetTypeDto {

  private Long id;

  private String name;

  private String unitOfMeasure;

  private ManagementType managementType;

}

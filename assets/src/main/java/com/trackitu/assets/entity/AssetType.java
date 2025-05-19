package com.trackitu.assets.entity;

import com.trackitu.assets.enums.ManagementType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "asset_types")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AssetType extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String unitOfMeasure;

  @Enumerated(EnumType.STRING)
  private ManagementType managementType;

}

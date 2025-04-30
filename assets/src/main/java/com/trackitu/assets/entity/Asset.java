package com.trackitu.assets.entity;

import com.trackitu.assets.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "assets")
public class Asset extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "asset_id")
  private Long id;

  private String inventoryNumber;

  @ManyToOne
  @JoinColumn(name = "asset_type_id")
  private AssetType assetType;

  @ManyToOne
  @JoinColumn(name = "funding_source_id")
  private FundingSource fundingSource;

  private LocalDateTime acquisitionDate;

  private double price;

  private String roomCode;

  @Enumerated(EnumType.STRING)
  private Status status;

}

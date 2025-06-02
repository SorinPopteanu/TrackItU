package com.trackitu.assets.repository;

import com.trackitu.assets.entity.Asset;
import com.trackitu.assets.entity.AssetType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

  Optional<Asset> findByInventoryNumber(String inventoryNumber);

  List<Asset> findByAssetType(AssetType assetType);
}

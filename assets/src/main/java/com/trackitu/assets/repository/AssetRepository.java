package com.trackitu.assets.repository;

import com.trackitu.assets.entity.Asset;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

  Optional<Asset> findByInventoryNumber(String inventoryNumber);
}

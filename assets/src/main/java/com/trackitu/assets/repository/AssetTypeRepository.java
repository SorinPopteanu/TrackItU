package com.trackitu.assets.repository;

import com.trackitu.assets.entity.AssetType;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetTypeRepository extends JpaRepository<AssetType, Long> {

  Optional<AssetType> findByName(String name);

  @Transactional
  @Modifying
  void deleteById(@NonNull Long id);


}

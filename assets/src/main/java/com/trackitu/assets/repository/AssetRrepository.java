package com.trackitu.assets.repository;

import com.trackitu.assets.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRrepository extends JpaRepository<Asset, Long> {

}

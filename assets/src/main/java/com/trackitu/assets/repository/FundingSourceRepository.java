package com.trackitu.assets.repository;

import com.trackitu.assets.entity.FundingSource;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundingSourceRepository extends JpaRepository<FundingSource, Long> {

  Optional<FundingSource> findByName(String name);
}

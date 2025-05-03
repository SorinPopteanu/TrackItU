package com.trackitu.assets.mapper;

import com.trackitu.assets.dto.funding_source.CreateFundingSourceDto;
import com.trackitu.assets.dto.funding_source.FetchFundingSourceDto;
import com.trackitu.assets.dto.funding_source.UpdateFundingSourceDto;
import com.trackitu.assets.entity.FundingSource;

public class FundingSourceMapper {

  public static FundingSource mapToFundingSource(CreateFundingSourceDto createFundingSourceDto, FundingSource fundingSource) {
    fundingSource.setName(createFundingSourceDto.getName());
    return fundingSource;
  }

  public static void mapToFundingSource(UpdateFundingSourceDto updateFundingSourceDto, FundingSource fundingSource) {
    fundingSource.setName(updateFundingSourceDto.getName());
  }

  public static FetchFundingSourceDto mapToFetchFundingSourceDto(FundingSource fundingSource, FetchFundingSourceDto fetchFundingSourceDto) {
    fetchFundingSourceDto.setId(fundingSource.getId());
    fetchFundingSourceDto.setName(fundingSource.getName());
    return fetchFundingSourceDto;
  }
}

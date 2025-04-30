package com.trackitu.assets.service.impl;

import com.trackitu.assets.dto.funding_source.CreateFundingSourceDto;
import com.trackitu.assets.dto.funding_source.FetchFundingSourceDto;
import com.trackitu.assets.dto.funding_source.UpdateFundingSourceDto;
import com.trackitu.assets.entity.FundingSource;
import com.trackitu.assets.exception.FundingSourceAlreadyExistsException;
import com.trackitu.assets.exception.ResourceNotFoundException;
import com.trackitu.assets.mapper.FundingSourceMapper;
import com.trackitu.assets.repository.FundingSourceRepository;
import com.trackitu.assets.service.IFundingSourceService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FundingSourceServiceImpl implements IFundingSourceService {

  private FundingSourceRepository fundingSourceRepository;

  /**
   * @param createFundingSourceDto - CreateFundingSourceDto Object
   */
  @Override
  public void createFundingSource(CreateFundingSourceDto createFundingSourceDto) {
    FundingSource fundingSource = FundingSourceMapper.mapToFundingSource(createFundingSourceDto,
        new FundingSource());
    Optional<FundingSource> optionalFundingSource = fundingSourceRepository.findByName(
        createFundingSourceDto.getName());
    if (optionalFundingSource.isPresent()) {
      throw new FundingSourceAlreadyExistsException(
          "Funding Source already exists with funding source name: "
              + createFundingSourceDto.getName());
    } else {
      fundingSourceRepository.save(fundingSource);
    }
  }

  /**
   * @return List of all the Funding Sources
   */
  @Override
  public List<FetchFundingSourceDto> fetchAllFundingSources() {
    List<FundingSource> fundingSourceList = fundingSourceRepository.findAll();
    List<FetchFundingSourceDto> fundingSourceDtoList = new ArrayList<>();

    for (FundingSource fundingSource : fundingSourceList) {
      fundingSourceDtoList.add(
          FundingSourceMapper.mapToFetchFundingSourceDto(fundingSource,
              new FetchFundingSourceDto()));
    }
    return fundingSourceDtoList;
  }

  /**
   * @param updateFundingSourceDto - CreateFundingSourceDto Object
   * @return boolean indicating if the update of the Funding Source details is successful or not
   */
  @Override
  public boolean updateFundingSourceDetails(UpdateFundingSourceDto updateFundingSourceDto) {
    boolean isUpdated = false;
    if (updateFundingSourceDto != null) {
      FundingSource fundingSource = fundingSourceRepository.findById(
          updateFundingSourceDto.getId()).orElseThrow(
          () -> new ResourceNotFoundException("Funding Source", "id",
              updateFundingSourceDto.getId().toString()));
      FundingSourceMapper.mapToFundingSource(updateFundingSourceDto, fundingSource);
      fundingSourceRepository.save(fundingSource);
      isUpdated = true;
    }
    return isUpdated;
  }
}

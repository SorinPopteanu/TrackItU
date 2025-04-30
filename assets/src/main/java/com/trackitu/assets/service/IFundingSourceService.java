package com.trackitu.assets.service;

import com.trackitu.assets.dto.funding_source.CreateFundingSourceDto;
import com.trackitu.assets.dto.funding_source.FetchFundingSourceDto;
import com.trackitu.assets.dto.funding_source.UpdateFundingSourceDto;
import jakarta.validation.Valid;
import java.util.List;

public interface IFundingSourceService {

  /**
   * @param createFundingSourceDto - CreateFundingSourceDto Object
   */
  void createFundingSource(CreateFundingSourceDto createFundingSourceDto);

  /**
   * @return List of all the Funding Sources
   */
  List<FetchFundingSourceDto> fetchAllFundingSources();

  /**
   *
   * @param updateFundingSourceDto - CreateFundingSourceDto Object
   * @return boolean indicating if the update of the Funding Source details is successful or not
   */
  boolean updateFundingSourceDetails(@Valid UpdateFundingSourceDto updateFundingSourceDto);
}

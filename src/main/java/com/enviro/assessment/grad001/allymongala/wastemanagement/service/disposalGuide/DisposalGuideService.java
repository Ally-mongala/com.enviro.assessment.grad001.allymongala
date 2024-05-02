package com.enviro.assessment.grad001.allymongala.wastemanagement.service.disposalGuide;


import com.enviro.assessment.grad001.allymongala.wastemanagement.dto.DisposalGuideDto;

import com.enviro.assessment.grad001.allymongala.wastemanagement.entity.Category;
import com.enviro.assessment.grad001.allymongala.wastemanagement.exceptions.DisposalGuideNotFoundException;

import java.util.List;

public interface DisposalGuideService {
    DisposalGuideDto createDisposalGuide(DisposalGuideDto disposalGuideDto, Category category);
    DisposalGuideDto getDisposalGuideById(Long id) throws DisposalGuideNotFoundException;
    List<DisposalGuideDto> getAllDisposalGuides();
    DisposalGuideDto updateDisposalGuide(Long id, DisposalGuideDto disposalGuideDto) throws DisposalGuideNotFoundException;
    void deleteDisposalGuide(Long id) throws DisposalGuideNotFoundException;
}

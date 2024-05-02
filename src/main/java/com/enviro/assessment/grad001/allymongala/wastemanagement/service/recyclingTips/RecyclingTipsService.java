package com.enviro.assessment.grad001.allymongala.wastemanagement.service.recyclingTips;

import com.enviro.assessment.grad001.allymongala.wastemanagement.dto.DisposalGuideDto;
import com.enviro.assessment.grad001.allymongala.wastemanagement.dto.RecyclingTipsDto;
import com.enviro.assessment.grad001.allymongala.wastemanagement.entity.Category;
import com.enviro.assessment.grad001.allymongala.wastemanagement.exceptions.DisposalGuideNotFoundException;
import com.enviro.assessment.grad001.allymongala.wastemanagement.exceptions.RecyclingTipsException;

import java.util.List;

public interface RecyclingTipsService {
    RecyclingTipsDto createRecyclingTip(RecyclingTipsDto recyclingTipsDto, Category category);
    RecyclingTipsDto getRecyclingTipById(Long id) throws RecyclingTipsException;
    List<RecyclingTipsDto> getAllRecyclingTip();
    RecyclingTipsDto updateRecyclingTip(Long id, RecyclingTipsDto recyclingTipsDto) throws RecyclingTipsException;
    void deleteRecyclingTip(Long id) throws RecyclingTipsException;
}

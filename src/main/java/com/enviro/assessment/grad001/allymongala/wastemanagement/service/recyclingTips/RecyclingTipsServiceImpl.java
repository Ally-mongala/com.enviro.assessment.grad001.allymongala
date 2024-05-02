package com.enviro.assessment.grad001.allymongala.wastemanagement.service.recyclingTips;

import com.enviro.assessment.grad001.allymongala.wastemanagement.dto.RecyclingTipsDto;
import com.enviro.assessment.grad001.allymongala.wastemanagement.entity.Category;
import com.enviro.assessment.grad001.allymongala.wastemanagement.entity.RecyclingTips;
import com.enviro.assessment.grad001.allymongala.wastemanagement.exceptions.RecyclingTipsException;
import com.enviro.assessment.grad001.allymongala.wastemanagement.repository.RecyclingTipsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
@Service
public class RecyclingTipsServiceImpl implements RecyclingTipsService{
    @Autowired
    private RecyclingTipsRepository recyclingTipsRepository;

    @Override
    public RecyclingTipsDto createRecyclingTip(RecyclingTipsDto recyclingTipsDto, Category category) {
        RecyclingTips recyclingTip = new RecyclingTips();
        recyclingTip.setTip(recyclingTipsDto.getTips());
        recyclingTip.setDayCreated(LocalDate.now());
        recyclingTip.setCategory(category);

        // Save the new recycling tip entity
        RecyclingTips savedRecyclingTip = recyclingTipsRepository.save(recyclingTip);

        // Map and return the saved entity as a DTO
        return mapToRecyclingTipsDto(savedRecyclingTip);
    }

    @Override
    public RecyclingTipsDto getRecyclingTipById(Long id) throws RecyclingTipsException {
        RecyclingTips recyclingTip = recyclingTipsRepository.findById(id)
                .orElseThrow(() -> new RecyclingTipsException("Recycling tip not found with id: " + id));
        return mapToRecyclingTipsDto(recyclingTip);
    }

    @Override
    public List<RecyclingTipsDto> getAllRecyclingTip() {
        List<RecyclingTips> recyclingTipsList = recyclingTipsRepository.findAll();
        return recyclingTipsList.stream()
                .map(this::mapToRecyclingTipsDto)
                .collect(Collectors.toList());
    }

    @Override
    public RecyclingTipsDto updateRecyclingTip(Long id, RecyclingTipsDto recyclingTipsDto) throws RecyclingTipsException {
        RecyclingTips existingRecyclingTip = recyclingTipsRepository.findById(id)
                .orElseThrow(() -> new RecyclingTipsException("Recycling tip not found with id: " + id));

        existingRecyclingTip.setTip(recyclingTipsDto.getTips());
        existingRecyclingTip.setCategory(recyclingTipsDto.getCategory());

        RecyclingTips updatedRecyclingTip = recyclingTipsRepository.save(existingRecyclingTip);

        return mapToRecyclingTipsDto(updatedRecyclingTip);
    }

    @Override
    public void deleteRecyclingTip(Long id) throws RecyclingTipsException {
        RecyclingTips recyclingTip = recyclingTipsRepository.findById(id)
                .orElseThrow(() -> new RecyclingTipsException("Recycling tip not found with id: " + id));

        recyclingTipsRepository.delete(recyclingTip);
    }

    private RecyclingTipsDto mapToRecyclingTipsDto(RecyclingTips recyclingTip) {
        return RecyclingTipsDto.builder()
                .id(recyclingTip.getId())
                .tips(recyclingTip.getTip())
                .dayCreated(recyclingTip.getDayCreated())
                .category(recyclingTip.getCategory())
                .build();
    }
}

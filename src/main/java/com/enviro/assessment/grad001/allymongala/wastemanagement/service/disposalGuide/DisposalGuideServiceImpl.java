package com.enviro.assessment.grad001.allymongala.wastemanagement.service.disposalGuide;

import com.enviro.assessment.grad001.allymongala.wastemanagement.dto.DisposalGuideDto;
import com.enviro.assessment.grad001.allymongala.wastemanagement.entity.Category;
import com.enviro.assessment.grad001.allymongala.wastemanagement.entity.DisposalGuide;
import com.enviro.assessment.grad001.allymongala.wastemanagement.exceptions.DisposalGuideNotFoundException;
import com.enviro.assessment.grad001.allymongala.wastemanagement.repository.DisposalGuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class DisposalGuideServiceImpl implements DisposalGuideService{
    @Autowired
    private DisposalGuideRepository disposalGuideRepository;
    @Override
    public DisposalGuideDto createDisposalGuide(DisposalGuideDto disposalGuideDto, Category category) {
        DisposalGuide disposalGuide = new DisposalGuide();
        disposalGuide.setGuide(disposalGuideDto.getGuide());
        disposalGuide.setDayCreated(LocalDate.now());
        disposalGuide.setCategory(category); // Set the Category from the parameter

        // Save the new disposal guide entity
        DisposalGuide savedDisposalGuide = disposalGuideRepository.save(disposalGuide);

        // Map and return the saved entity as a DTO
        return mapToDisposalGuideDto(savedDisposalGuide);
    }
    private DisposalGuideDto mapToDisposalGuideDto(DisposalGuide disposalGuide) {
        return DisposalGuideDto.builder()
                .id(disposalGuide.getId())
                .guide(disposalGuide.getGuide())
                .dayCreated(disposalGuide.getDayCreated())
                .category(disposalGuide.getCategory())
                .build();
    }
    @Override
    public DisposalGuideDto getDisposalGuideById(Long id) throws DisposalGuideNotFoundException {
        DisposalGuide disposalGuide = disposalGuideRepository.findById(id)
                .orElseThrow(() -> new DisposalGuideNotFoundException("Disposal guide not found with id: " + id));
        return mapToDisposalGuideDto(disposalGuide);
    }
    @Override
    public List<DisposalGuideDto> getAllDisposalGuides() {
        List<DisposalGuide> disposalGuides = disposalGuideRepository.findAll();
        return disposalGuides.stream()
                .map(this::mapToDisposalGuideDto)
                .collect(Collectors.toList());
    }
    @Override
    public DisposalGuideDto updateDisposalGuide(Long id, DisposalGuideDto disposalGuideDto) throws DisposalGuideNotFoundException {
        DisposalGuide existingDisposalGuide = disposalGuideRepository.findById(id)
                .orElseThrow(() -> new DisposalGuideNotFoundException("Disposal guide not found with id: " + id));

        existingDisposalGuide.setGuide(disposalGuideDto.getGuide());
        existingDisposalGuide.setCategory(disposalGuideDto.getCategory());

        DisposalGuide updatedDisposalGuide = disposalGuideRepository.save(existingDisposalGuide);

        return mapToDisposalGuideDto(updatedDisposalGuide);
    }
    @Override
    public void deleteDisposalGuide(Long id) throws DisposalGuideNotFoundException {
        DisposalGuide disposalGuide = disposalGuideRepository.findById(id)
                .orElseThrow(() -> new DisposalGuideNotFoundException("Disposal guide not found with id: " + id));

        disposalGuideRepository.delete(disposalGuide);
    }


}
